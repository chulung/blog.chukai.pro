import axios from 'axios'
const ax = axios.create({
  baseURL: process.env.baseUrl + '/api/cms'
})
// 登录拦截
ax.interceptors.response.use(response => {
  return response
}, error => {
  if (error.response && error.response.status === 401) {
    if (process.browser) {
      window.$nuxt.$router.push(`/cms/login?path=${window.$nuxt.$route.path}`)
    }
  }
  return Promise.reject(error)
})
export default ax
