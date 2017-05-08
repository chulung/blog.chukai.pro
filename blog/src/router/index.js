import Vue from 'vue'
import Router from 'vue-router'
import content from '../components/content.vue'
import article from '../components/contents/article.vue'
import list from '../components/contents/list.vue'
import notFound from '../components/404.vue'
Vue.use(Router)
export default new Router({
  mode: 'history',
  scrollBehavior (to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return {x: 0, y: 0}
    }
  },
  routes: [
    // content
    {
      path: '/',
      component: content,
      children: [
        {
          path: '',
          name: 'articles',
          component: list,
          alias: '/articles'
        }
      ]
    },
    // tag
    {
      path: '/tag/:tag',
      component: content,
      children: [
        {
          path: '',
          name: 'tag',
          component: list
        }
      ]
    },
    // search
    {
      path: '/search',
      component: content,
      children: [
        {
          path: '',
          name: 'search',
          component: list
        }
      ]
    },
    // column
    {
      path: '/column/:column',
      component: content,
      children: [
        {
          path: '',
          name: 'column',
          component: list
        }
      ]
    },
    // about
    {
      path: '/about',
      component: content,
      children: [{
        path: '', component: article, meta: {id: 20}
      }]
    },
    // article
    {
      path: '/article/:uri',
      component: content,
      children: [{
        path: '', name: 'article', component: article
      }]
    },
    // 404
    {
      path: '*',
      component: notFound
    }
  ]
})
