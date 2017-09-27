function init (callBack) {
  if (!process.browser) return
  function editorInit () {
    if (!window.editormd) {
      setTimeout(editorInit, 1000)
      return
    }
    window.editormd('editor-div', {
      width: '100%',
      height: 740,
      path: 'https://static.chulung.com/assets/markdown/lib/',
      theme: 'dark',
      previewTheme: '',
      editorTheme: 'eclipse',
      markdown: '',
      codeFold: true,
      saveHTMLToTextarea: true,    // 保存 HTML 到 Textarea
      searchReplace: true,
      htmlDecode: 'style,script,iframe|on*',            // 开启 HTML 标签解析，为了安全性，默认不开启
      emoji: true,
      taskList: true,
      tocm: true,         // Using [TOCM]
      tex: true,                   // 开启科学公式TeX语言支持，默认关闭
      flowChart: true,             // 开启流程图支持，默认关闭
      sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
      imageUpload: true,
      imageFormats: ['jpg', 'jpeg', 'gif', 'png', 'bmp', 'webp'],
      imageUploadURL: process.env.baseUrl + '/api/fileUpload/file',
      onload: function () {
        callBack(this)
      }
    })
  }

  editorInit()
}

export default {init}
