import Vue from 'vue'
import Router from 'vue-router'
import content from '../components/content.vue'
import article from '@/components/contents/article.vue'
import summaries from '../components/contents/summaries.vue'
Vue.use(Router)
export default new Router({
  routes: [
    // content
    {
      path: '/',
      component: content,
      children: [
        {
          path: '',
          name: 'index',
          component: summaries
        }
      ]
    },
    // about
    {
      path: '/article/20',
      name: 'about',
      component: article
    },
    // article
    {
      path: '/article/:id',
      component: content,
      children: [{
        path: '', name: 'article', component: article
      }]
    }
  ]
})
