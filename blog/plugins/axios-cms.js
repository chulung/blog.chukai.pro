import axios from 'axios'
import https from 'https'
const ax = axios.create({
  baseURL: process.env.baseUrl + '/api/cms',
  httpsAgent: new https.Agent({
    rejectUnauthorized: false
  })
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
