// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import navigation from './components/navigation.vue'
import sitefooter from './components/siteFooter.vue'
import copyright from './components/copyright.vue'
import router from './router'
import axios from 'axios'
import Vuex from 'Vuex'
import VeeValidate from 'vee-validate'
Vue.config.productionTip = false
axios.defaults.baseURL = 'https://blog.chulung.com/api'
Vue.use(VeeValidate)
Vue.use(Vuex)
const store = new Vuex.Store({
  strict: process.env.NODE_ENV !== 'production',
  state: {},
  mutations: {}
})
/* eslint-disable no-new */
new Vue({
  router,
  store,
  components: {navigation, sitefooter, copyright}
}).$mount('#app')
