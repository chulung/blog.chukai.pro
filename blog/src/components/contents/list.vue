<template xmlns:v-on="http://www.w3.org/1999/xhtml">
  <div class="page-default">
    <component :is="currentView" :articles="articles">
      <!-- 组件在 vm.currentview 变化时改变！展示不同样式的列表 -->
    </component>
    <nav class="navigation posts-navigation" role="navigation" >
      <div class="nav-links" style="z-index: -1">
        <div class="nav-next" v-if="nextPage">
          <a @click="loadMore">加载更多<i class="fa fa-spinner" :class="{'fa-pulse':loading}"></i></a>
        </div>
      </div>
    </nav><!-- .navigation -->
  </div><!-- .col-md-8 -->
</template>
<script>
  import axios from 'axios'
  import defaultView from './list/default.vue'
  import manualView from './list/manual.vue'
  import travelView from './list/travel.vue'
  const Velocity = require('Velocity')
  const paths = {
    'index': '/articles',
    'articles': '/articles',
    'tag': '/tag/:tag',
    'column': '/articles',
    'search': '/search'
  }
  export default{
    data () {
      return {currentView:'defaultView',articles: [], nextPage: null, columnId: null, loading: false, preDelay: 0}
    },
    components:{c,manualView,travelView},
    created () {
      this.fetchArticleData()
    },
    watch: {
      $route (to, from) {
        this.fetchArticleData()
      }
    },
    methods: {
      loadMore () {
        this.fetchArticleData(true)
      },
      fetchArticleData (loadMore) {
        this.loading = true
        let path = paths[this.$route.name].replace(':tag', this.$route.params.tag)
        axios.get(path, {
          params: {
            page: loadMore ? this.nextPage : this.$route.query.page,
            column: this.$route.params.column,
            year: this.$route.query.year,
            month: this.$route.query.month
          }
        }).then(response => {
          if (loadMore) {
            this.articles.push.apply(this.articles, response.data.list)
          } else {
            this.articles = response.data.list
          }
          this.nextPage = response.data.nextPage
          this.loading = false
        }).catch(e => {
          console.log(e)
        })
      }
    }
  }
</script>
