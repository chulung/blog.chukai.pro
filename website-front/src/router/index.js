import Vue from 'vue'
import Router from 'vue-router'
import indexList from '@/components/contents/indexList.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: indexList
    }
  ]
})
