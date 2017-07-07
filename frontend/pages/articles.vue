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
    'search': '/search',
    'tag-tagName': '/tag'
  }
  const columnNames = {
    'depth': '深度',
    'breadth': '广度',
    'sensibility': '心悟',
    'amateur': '生活'
  }
  function getParams ({params, query}) {
    return {
      column: params.columnName,
      tag: params.tagName,
      page: query.page,
      year: query.year,
      month: query.month,
      word: query.word
    }
  }
  function getPageUrl ({path, name}, params, pageNum) {
    params['page'] = pageNum
    if (name === 'column-columnName') {
      console.log(name)
      params['column'] = ''
    }
    return `${path}?${common.toParamString(params)}`
  }
  export default{
    head () {
      return {
        title: this.title
      }
    },
    data () {
      return {
        currentView: 'defaultView',
        articles: [],
        columnId: null,
        page: [],
        preHref: '#',
        nextHref: '#',
        title: '首页'
      }
    },
    asyncData ({route, params, error}) {
      return axios.get(paths[route.name], {
        params: getParams(route)
      }).then((res) => {
        let params = getParams(route)
        return {
          articles: res.data.list,
          page: res.data,
          preHref: getPageUrl(route, params, res.data.prePage),
          nextHref: getPageUrl(route, params, res.data.nextPage)
        }
      }
      )
    },
    components: {defaultView, manualView, travelView},
    created () {
      this.updateTitle()
    },
    watch: {
      articles: function () {
        this.updateTitle()
      }
    },
    methods: {
      updateTitle () {
        if (this.$route.name === 'column-columnName') {
          console.log(this.$route.params.columnName)
          this.title = columnNames[this.$route.params.columnName]
        }
      }
    }
  }
</script>
