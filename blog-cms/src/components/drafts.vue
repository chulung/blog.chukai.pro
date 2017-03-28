<template>
  <div class="col-md-10">
    <div class="content-box-large">
      <div class="panel-heading">
        <div class="panel-title">文章列表</div>
      </div>
      <div class="panel-body">
        <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="articleTable">
          <thead>
          <tr>
            <th>ID</th>
            <th>文章ID</th>
            <th>标题</th>
            <th><select v-model="columnId">
              <option value="">全部</option>
              <option v-for="item in columns" :value="item.id">{{item.cnName}}</option>
            </select>栏目
            </th>
            <th>作者</th>
            <th>创建日期</th>
            <th>修改日期</th>
            <th>发布状态</th>
            <th>版本</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="item in articleDrafts">
            <th>{{item.id}}</th>
            <th>{{item.articleId}}</th>
            <th>{{item.title}}</th>
            <th>{{item.columnName}}</th>
            <th>{{item.author}}</th>
            <th>{{item.createTime}}</th>
            <th>{{item.updateTime}}</th>
            <th @click="changePublish">{{item.isPublish}}</th>
            <th>{{item.version}}</th>
            <th>
              <router-link :to="'/editor?id='+item.id" >编辑</router-link>
            </th>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
<script>
  import axios from 'axios'
  export default{
    data () {
      return {articleDrafts: null, columns: {}, columnId: ''}
    },
    created () {
      axios.get('/columns').then(response => {
        this.columns = response.data
      })
      this.fetchArticleData()
    },
    watch: {
      columnId () {
        this.fetchArticleData()
      }
    },
    methods: {
      fetchArticleData () {
        axios.get('/articleDrafts', {
          params: {
            columnId: this.columnId
          }
        }).then(response => {
          this.articleDrafts = response.data.list
        })
      },
      changePublish () {
      }
    }
  }
</script>
