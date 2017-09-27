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
              <router-link :to="'/cms/editor?id='+item.id">编辑</router-link>
            </th>
          </tr>
          </tbody>
        </table>
        <nav aria-label="Page navigation" class="page">
          <ul class="pagination pagination-lg">
            <li>
              <router-link v-show="page.prePage"
                           :to="{path:'/cms/drafts',query:{columnId: columnId,pageNum:page.prePage}}"
                           aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
              </router-link>
            </li>
            <li>
              <router-link :to="{path:'/cms/drafts',query:{columnId: columnId,pageNum:n}}" v-for="n in page.totalPage"
                           :key="n">{{n}}
              </router-link>
            </li>
            <li>
              <router-link v-show="page.nextPage"
                           :to="{path:'/cms/drafts',query:{columnId: columnId,pageNum:page.nextPage}}"
                           aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
              </router-link>
            </li>
          </ul>
        </nav>
      </div>
    </div>

  </div>
</template>
<script>
  import axios from '~/plugins/axios-cms'
  export default{
    middleware: 'authenticated',
    layout: 'cms',
    data () {
      return {articleDrafts: null, columns: {}, columnId: '', page: {}}
    },
    created () {
      if (!process.browser) return
      axios.get('/columns').then(response => {
        this.columns = response.data
      }).catch(e => {
        console.log(e)
      })
      this.fetchArticleData()
    },
    watch: {
      $route () {
        this.fetchArticleData()
      }
    },
    methods: {
      fetchArticleData () {
        if (!process.browser) return
        axios.get('/articleDrafts', {
          params: {
            columnId: this.columnId,
            pageNum: this.$route.query.pageNum
          }
        }).then(response => {
          this.articleDrafts = response.data.list
          this.page = response.data
        }).catch((e) => {
          console.log(e)
        })
      },
      changePublish () {
      }
    }
  }
</script>
<style>
  .page {
    float: right;
  }
</style>
