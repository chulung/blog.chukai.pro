requirejs([ "jquery","treeview","contextMenu","bootstrap"], function($) {
	$.getJSON("/backend/ciki/category/list", function(rt) {
		if (rt.code = 1) {
			var exportTitel = function(nodes) {
				$.each(nodes, function() {
					this.text = "<span class='cate' data-id='"
							+ this.id + "' data-type='" + this.type + "'>"
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
					var type=$(node.text).data("type");
					if (type=="CIKI_TEXT") {
						$.getJSON("/backend/ciki/"+$(node.text).data("id"), function(rt) {
							if (rt.code!=1) {
								alert(rt.msg);
								return;
							}
							editor.setMarkdown(rt.result.markdown);
							$("#btn-save").data("id",rt.result.id);
							$("#lb-cate").text($($("#editor-tree").treeview("getParent",node).text).text()+"-"+$(node.text).text()+"-"+rt.result.title);
						});
					}
				}
			});
			$("#editor-tree").contextmenu({
				  target:'#context-menu',
				  before: function (e, element, target) {
				      e.preventDefault();
				      var li=$(e.target).is("li")?$(e.target):$(e.target).parent();
				      console.log(li);
				      $("input[name='id']").val(li.find(".cate").data("id"));
				      return true;
				  },onItem: function(context, e) {
					  $("form").attr("method",$(e.target).data("method"));
					  $('#modal').modal();
				  }
			});
		}
	});
});