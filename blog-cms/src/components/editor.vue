<template>
  <div>
    <link rel="stylesheet" href="https://chulung.github.com/assets/markdown/css/editormd.min.css"/>
    <div class="col-md-12">
      <div class="panel-heading">
        <div class="panel-title">
          <span>标题</span>
          <input id="title" class="title" type="text" v-model="draft.title"/>
          <input id="isPublish" type="checkBox" checked>发布</input>
          <select v-model="draft.columnId">
            <option value="">全部</option>
            <option v-for="item in columns" :value="item.id">{{item.cnName}}</option>
          </select>
          <input type="checkBox" v-model="pushBlog">推送</input>
          <span>tags</span>
          <input type="text" v-model="draft.tags"/>
          <button @click="save">保存</button>
        </div>
      </div>
      <div id="editor-div" class="panel-body">
      </div>
    </div>
  </div>
</template>
<!--<script src="https://chulung.github.io/assets/assets/markdown/editormd.min.js"/>-->
<script>
  import axios from 'axios'
  import {mapMutations} from 'vuex'
  export default{
    data () {
      return {draft: {}, columns: {}, pushBlog: false}
    },
    created () {
      let script = document.createElement('script')
      script.src = 'https://chulung.github.com/assets/markdown/editormd.min.js'
      script.onload = function () {
        script = document.createElement('script')
        script.src = 'http://localhost:8086/static/editor.js'
        document.body.appendChild(script)
      }
      document.body.appendChild(script)
      this.initEditor()
      axios.get('/columns').then(response => {
        this.columns = response.data
      })
    },
    methods: {
      initEditor () {
        if (window.editor) {
          this.draft.id = this.$route.query.id
          if (this.draft.id) {
            axios.get('/articleDraft/' + this.draft.id).then(response => {
              this.draft = response.data
              window.editor.setMarkdown(this.draft.content)
            })
          }
        } else {
          setTimeout(this.initEditor, 1000)
        }
      },
      save () {
        this.draft.content = window.editor.getMarkdown()
        this.draft.htmlContent = window.editor.getHTML()
      },
      ...mapMutations(['changeLogined'])
    }
  }
</script>
