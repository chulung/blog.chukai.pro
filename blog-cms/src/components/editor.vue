<template>
  <div class="col-md-12">
    <div class="panel-heading">
      <div class="panel-title">Markdown</div>
      <div class="panel-title">
        <lable>标题</lable>
        <input id="title" class="title" type="text"/>
        <input id="isPublish" type="checkBox" checked>发布</input>
        <select id="typeId">
          <#list articleTypes as type>
            <option value="${type.id}">${type.cnName}</option>
          </#list>
        </select>
        <input id="pushBlog" type="checkBox">推送</input>
        <button id="btn-save" data-articledraftid="${articleDraftId!}">保存</button>
      </div>
      <div>
        <lable>license</lable>
        <input id="license" type="text"/>
        <lable>tags</lable>
        <input id="tags" type="text"/>
      </div>
    </div>
    <div id="editor-div" class="panel-body">
    </div>
    <link rel="stylesheet" href="https://chulung.github.com/assets/assets/markdown/css/css/editormd.css"/>
  </div>
</template>
<script src="https://chulung.github.io/assets/assets/markdown/editormd.min.js"/>
<script>
  import jQuery from 'jQuery'
  export default{
    data () {
      return {}
    },
    created () {
      jQuery(function () {
        editormd("editor-div", {
          width: "100%",
          height: 740,
          path: '/assets/markdown/lib/',
          theme: "dark",
          previewTheme: "",// 预览主题
          editorTheme: "eclipse",// 代码主题
          markdown: "",
          codeFold: true,
          // syncScrolling : false,
          saveHTMLToTextarea: true, // 保存 HTML 到 Textarea
          searchReplace: true,
          htmlDecode: "style,script,iframe|on*", // 开启 HTML
          toolbarAutoFixed: false,// 工具栏浮动
          emoji: true,
          taskList: true,
          tocm: true, // Using [TOCM]
          tex: false, // 开启科学公式TeX语言支持，默认关闭
          flowChart: false, // 开启流程图支持，默认关闭
          sequenceDiagram: false, // 开启时序/序列图支持，默认关闭,
          imageUpload: true,
          imageFormats: ["jpg", "jpeg", "gif", "png", "bmp"],
          imageUploadURL: "/fileUpload/file",
          toolbar: true,
          onload: function () {
            editor = this;
          }
        });
      })
    }
  }
</script>
