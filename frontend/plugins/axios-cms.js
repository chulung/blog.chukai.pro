import axios from 'axios'
import Vuex from 'vuex'
export default axios.create({
  baseURL: process.env.baseUrl + '/api/cms'
})
// 登录拦截
axios.interceptors.response.use(response => {
  return response
}, error => {
  if (error.response && error.response.status === 401) {
    Vuex.Store.commit('changeLoginedStatus', false)
  }
  return Promise.reject(error)
})
