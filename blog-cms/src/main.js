// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import router from './router'
import axios from 'axios'
import login from '@/components/login.vue'
Vue.config.productionTip = false
axios.defaults.baseURL = 'https://blog.chulung.com/api/cms'
/* eslint-disable no-new */
new Vue({
  router,
  components: {login}
}).$mount('#app')
