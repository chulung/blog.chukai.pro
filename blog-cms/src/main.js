// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import router from './router'
import axios from 'axios'
import Vuex from 'vuex'
import login from '@/components/login.vue'
import VeeValidate from 'VeeValidate'
Vue.use(VeeValidate)
Vue.config.productionTip = false
axios.defaults.baseURL = 'https://chulung.com/api/cms'
axios.toJson = function (data) {
  return JSON.parse(JSON.stringify(data))
}
//登录拦截
axios.interceptors.response.use(response => {
  return response
}, error => {
  if (error.response && error.response.status === 401) {
    store.commit('changeLoginedStatus', false)
  }
  return Promise.reject(error)
})

Vue.use(Vuex)
const store = new Vuex.Store({
  strict: process.env.NODE_ENV !== 'production',
  state: {
    logined: false
  },
  mutations: {
    changeLoginedStatus (state) {
      state.logined = state
    }
  }
})
new Vue({
  router,
  store,
  components: {login}
}).$mount('#app')
