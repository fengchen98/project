import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'

// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API || 'https://andretech.cn/api/',
  //  || 'http://192.168.1.174:8888/',
  // baseURL: process.env.VUE_APP_BASE_API, // url = base url + request  url
  // baseURL: 'https://andretech.cn/api', // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 30000 // request timeout
})

service.defaults.withCredentials = false // 跨域请求是否携带凭证
// request interceptor
service.interceptors.request.use(
  (config) => {
    // do something before request is sent

    if (store.getters.token) {
      // let each request carry token
      // ['X-Token'] is a custom headers key
      // please modify it according to the actual situation
      config.headers['token'] = getToken()
    }
    // config.headers['Access-Control-Allow-Origin'] = '*'
    return config
  },
  (error) => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
   */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  (response) => {
    // 从请求体取出data
    const res = response.data

    // if the custom code is not 20000, it is judged as an error.
    if (res.code !== 20000) {
      // Message({
      //   message: res.message || 'Error',
      //   type: 'error',
      //   duration: 5 * 1000
      // })
      if (res.code === 14000) {
        Message({
          type: 'error',
          message: res.msg
        })
      }
      // 用户登录过期
      if (res.code === 14004) {
        Message({
          type: 'error',
          message: res.msg
        })
        this.$store.action('admin/logout')
        this.$router.push('/login')
      }

      // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
      if (
        res.code === 15002 ||
        res.code === 15003 ||
        res.code === 15004 ||
        res.code === 15005
      ) {
        Message({
          type: 'error',
          message: res.msg
        })
      }

      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        // to re-login
        MessageBox.confirm(
          'You have been logged out, you can cancel to stay on this page, or log in again',
          'Confirm logout',
          {
            confirmButtonText: 'Re-Login',
            cancelButtonText: 'Cancel',
            type: 'warning'
          }
        ).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      }
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res
    }
    // console.log('requset response === ', res);
    // return response
  },
  (error) => {
    console.log('err' + error) // for debug
    // Message({
    //   message: error.message,
    //   type: 'error',
    //   duration: 5 * 1000
    // })
    return Promise.reject(error)
  }
)

export default service
