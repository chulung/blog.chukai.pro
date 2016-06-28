requirejs([ "jquery","treeview"], function($) {
	$.getJSON("/backend/ciki/category/list", function(rt) {
		if (rt.code = 1) {
			var exportTitel = function(nodes) {
				$.each(nodes, function() {
					this.text = "<span data-id='"
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
					console.log(type!="ARTICLE");
					if (type=="CIKI_CATE" || type=="BLOG_CATE") {
						$("#lb-cate").text("新建"+$($("#editor-tree").treeview("getParent",node).text).text()+"-"+$(node.text).text());
					}
					if (type=="ARTICLE" || type=="CIKI_TEXT") {
						$("#lb-cate").text("编辑"+$($("#editor-tree").treeview("getParent",node).text).text()+"-"+$(node.text).text());
						$.getJSON("/backend/articleDraft/"+$(node.text).data("id"), function(rt) {
							editor.setMarkdown(rt.context);
							$("#lb-cate").val($(node))
							$('#isPublish').prop("checked",
									rt.isPublish == 'PUBLISHED');
							$('#title').val(rt.title)
							$('#articleType').val(rt.typeId);
						});
					}
				}
			})
		}
	});
});