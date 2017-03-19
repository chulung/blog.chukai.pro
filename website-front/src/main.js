// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import navigation from './components/navigation.vue'
import sidebar from './components/sidebar.vue'
import sitefooter from './components/siteFooter.vue'
import copyright from './components/copyright.vue'
import router from './router'
import axios from 'axios'
Vue.prototype.ajax = axios
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  router,
  components: {navigation, sidebar, sitefooter, copyright}
}).$mount('#app')
