<template>
  <div class="col-md-12">
    <div class="panel-heading">
      <div class="panel-title">
        <span>标题</span>
        <input class="title" type="text" v-validate="'required'" name="title" v-model="draft.title"/>
        <input type="checkbox" v-model="publishCheck">发布</input>
        <select v-model="draft.columnId">
          <option v-for="item in columns" :value="item.id">{{item.cnName}}</option>
        </select>
        <input type="checkbox" v-model="pushBlog">推送</input>
        <span>tags</span>
        <input type="text" v-model="draft.tags" name="tags" v-validate="{ rules: { regex: /(\s+(,\s+)*)?$/} }"/>
        <span>uri</span>
        <input type="text" v-validate="'required'" name="uri" v-model="uri"/>
        <button @click="validateBeforeSubmit">保存</button>
      </div>
    </div>
    <div id="editor-div" class="panel-body editormd editormd-vertical editormd-theme-dark">
    </div>
  </div>
</template>
<script>
  import axios from '~/plugins/axios-cms'
  import common from '~/plugins/common'
  import md from '~/plugins/markdown'
  let editor
  export default{
    middleware: 'authenticated',
    layout: 'cms',
    head: {
      script: [
        {src: 'https://blog.chukai.pro/assets/markdown/editormd.min.js'}
      ],
      link: [
        {rel: 'stylesheet', href: 'https://blog.chukai.pro/assets/markdown/css/editormd.min.css'}
      ]
    },
    data () {
      return {
        draft: {
          isPublish: 'N',
          title: '',
          uri: '',
          tags: '',
          columnId: '1'
        },
        columns: {},
        pushBlog: false,
        publishCheck: false,
        uri: ''
      }
    },
    mounted () {
      if (!process.browser) return
      md.init(this.initEditor)
      axios.get('/columns').then(response => {
        this.columns = response.data
      })
    },
    watch: {
      publishCheck: function (newVal) {
        this.draft.isPublish = newVal ? 'Y' : 'N'
      },
      pushBlog: function (newVal) {
        this.draft.pushBlog = newVal ? '1' : '0'
      },
      uri: function (newVal) {
        this.uri = newVal.trim().toLowerCase().replace(/\s+/, '-')
        this.draft.uri = this.uri
      }
    },
    methods: {
      initEditor (ed) {
        editor = ed
        this.draft.id = this.$route.query.id || this.draft.id
        if (this.draft.id) {
          axios.get('/articleDraft/' + this.draft.id).then(response => {
            this.draft = response.data
            this.publishCheck = this.draft.isPublish === 'Y'
            this.uri = this.draft.uri
            editor.setMarkdown(this.draft.content)
          })
        }
      },
      validateBeforeSubmit () {
        this.$validator.validateAll().then(result => {
          if (!result) {
            for (const k of Object.keys(this.draft)) {
              let msg = this.errors.first(k)
              if (msg) {
                window.$(document.getElementsByName(k)[0]).attr('title', msg).tooltip().focus()
                break
              }
            }
            return
          }
          console.log(this.draft.id)
          this.draft.content = editor.getMarkdown()
          this.draft.htmlContent = editor.getHTML()
          const ajax = this.draft.id ? axios.put : axios.post
          const param = common.toJson(this.draft)
          ajax('/articleDraft', param).then(response => {
            this.draft.id = response.data.id || this.draft.id
            console.log(this.draft.id)
            alert('保存成功')
          }).catch(error => {
            console.log(error)
            alert(`message:${error.message},data:${error.response.data}`)
          })
        })
      }
    }
  }
</script>
