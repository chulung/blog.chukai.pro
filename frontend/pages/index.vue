<template>
  <div class="page-default">
    <component :is="currentView" :articles="articles">
      <!-- 组件在 vm.currentview 变化时改变！展示不同样式的列表 -->
    </component>
    <nav class="navigation posts-navigation" role="navigation">
      <h2 class="screen-reader-text">Posts navigation</h2>
      <div class="nav-links">
        <div class="nav-previous" :class="{ none : !page.prePage}">
          <a :href="preHref">上一页</a>
        </div>
        <div class="nav-next" :class="{ none : !page.nextPage}"><a :href="nextHref">下一页</a></div>
      </div>
    </nav><!-- .navigation -->
  </div><!-- .col-md-8 -->
</template>
<script>
  import axios from '~plugins/axios'
  import defaultView from '~components/list/default.vue'
  import manualView from '~components/list/manual.vue'
  import travelView from '~components/list/travel.vue'
  import common from '~plugins/common'
  const paths = {
    'index': '/articles',
    'articles': '/articles',
    'column-columnName': '/articles',
    'search': '/search'
  }
  export default{
    data () {
      return {
        currentView: 'defaultView',
        articles: [],
        columnId: null,
        page: [],
        preHref: '#',
        nextHref: '#'
      }
    },
    components: {defaultView, manualView, travelView},
    created () {
      this.fetchArticleData()
    },
    watch: {
      $route (to, from) {
        this.fetchArticleData()
      }
    },
    methods: {
      getPageUrl: function (params, page) {
        params['page'] = page
        if (this.$route.path.indexOf('/column/') > -1) {
          params['column'] = ''
        }
        return `${this.$route.path}?${common.toParamString(params)}`
      },
      fetchArticleData () {
        axios.get(paths[this.$route.name], {
          params: this.getParams()
        }).then(response => {
          this.articles = response.data.list
          this.page = response.data
          let params = this.getParams()
          this.preHref = this.getPageUrl(params, this.page.prePage)
          this.nextHref = this.getPageUrl(params, this.page.nextPage)
        }).catch(e => {
          console.log(e)
        })
      },
      getParams () {
        return {
          column: this.$route.params.columnName,
          page: this.$route.query.page,
          year: this.$route.query.year,
          month: this.$route.query.month,
          word: this.$route.query.word
        }
      }
    }
  }
</script>
