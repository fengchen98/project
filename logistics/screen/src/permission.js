import router from './router'
import store from './store'
// import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken } from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({ showSpinner: true }) // NProgress Configuration

// const whiteList = ['/login', '/webapp'] // no redirect whitelist

router.beforeEach(async(to, from, next) => {
  // start progress bar
  NProgress.start()
  // 校验token
  if (getToken()) {
    await store.dispatch('admin/getInfo')
    NProgress.done()
  }
  // set page title
  document.title = getPageTitle(to.meta.title)
  // determine whether the user has logged in
  // 后台校验管理员状态
  if (/(BackstageManager)/.test(to.fullPath)) {
    const hasGetUserRoles = parseInt(store.getters.authinfo.roles) > 99 // 判断用户角色权限
    // console.log(hasGetUserRoles)
    if (hasGetUserRoles) {
      // 用户有权限进入后台
      next()
    } else {
      // if (whiteList.indexOf(to.path) !== -1) {
      //   console.log('whiteList.indexOf(to.path) !== -1')
      //   next()
      // } else {
      //   // other pages that do not have permission to access are redirected to the login page.
      //   // next(`/BackstageManager/login?redirect=${to.path}`)
      //   // next(`/login?redirect=${to.path}`)
      //   next('/')
      //   NProgress.done()
      // }
      next('/')
      NProgress.done()
    }
  } else {
    next()
    NProgress.done()
  }
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})
