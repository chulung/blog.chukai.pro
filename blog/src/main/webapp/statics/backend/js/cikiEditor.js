requirejs([ "jquery","treeview","contextMenu","bootstrap"], function($) {
	var exports={};
	$("#submit-ciki").click(function(){
		$.ajax({
			url:$('#form-ciki').attr("action"),
			type:$('#form-ciki').attr("method"),
			data:$('#form-ciki').serialize(),
			success:function(result){
				if (result.code) {
					exports.initTree();
					 $('#modal').modal('hide');
				}
			}
		});
	});
	$('#btn-save').click(function() {
		$.ajax({
				type :  "PUT" ,
				url : "/backend/ciki",
				data : {
					"id" : $(this).data("id"),
					"markdown" : editor.getMarkdown()
				},
				dataType : "json",
				success : function(data) {
					if (data.code = 1) {
						alert('保存成功');
					}
				},
				error : function() {
					alert("服务器异常");
				}
		});
	});
	
	exports.initTree=function(){
		$.getJSON("/backend/ciki/category/list", function(rt) {
			if (rt.code = 1) {
				var exportTitel = function(nodes) {
					$.each(nodes, function() {
						this.text = "<span class='cate' data-id='"
								+ this.id + "' data-cate-level='" + this.cateLevel + "'>"
								+ this.text + "</span>";
						if (this.nodes) {
							exportTitel(this.nodes);
						}
					})
					return nodes;
				}
				$("#editor-tree").treeview({
					data : exportTitel(rt.result),
					onNodeSelected : function(event, node) {
						var cateLevel=$(node.text).data("cate-level");
						if (cateLevel=="ITEM") {
							$.getJSON("/backend/ciki/"+$(node.text).data("id"), function(rt) {
								if (rt.code!=1) {
									alert(rt.msg);
									return;
								}
								editor.setMarkdown(rt.result.markdown);
								$("#btn-save").data("id",rt.result.id);
								$("#lb-cate").text($($("#editor-tree").treeview("getParent",node).text).text()+"-"+$(node.text).text());
								$(".panel-heading").show();
							});
						}
					}
				});
				$("#editor-tree").contextmenu({
					  target:'#context-menu',
					  before: function (e, element, target) {
					      e.preventDefault();
					      var li=$(e.target).is("li")?$(e.target):$(e.target).parent();
					      $("input[name='id']").val(li.find(".cate").data("id"));
					      return true;
					  },onItem: function(context, e) {
						  $("form").attr("method",$(e.target).data("method"));
						  $('#modal').modal();
					  }
				});
			}
		});
	};
	exports.initTree();
	return exports;
});