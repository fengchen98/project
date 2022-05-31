import Layout from '@/views/webapp/layout'

const webapprouter = [
  {
    path: '/webapp',
    component: Layout,
    name: 'webapp',
    redirect: '/webapp/index',
    children: [
      // 首页
      {
        path: 'index',
        name: 'index',
        component: () => import('@/views/webapp/index'),
        meta: { title: '首页', icon: 'el-icon-menu', color: 'info' }
      },
      // 所有服务
      {
        path: 'service/',
        name: 'service',
        component: () => import('@/views/webapp/services'),
        props: true,
        meta: { title: '服务与产品', icon: 'el-icon-menu', color: 'info' }
      },
      // 产品与服务
      {
        path: 'service/freight/:id',
        name: 'service_freight',
        component: () => import('@/views/webapp/services/detail'),
        props: true,
        meta: { title: '服务与产品', icon: 'el-icon-menu', color: 'info' }
      },
      // 特色服务介绍
      {
        path: 'service/introduce/:tag',
        name: 'introduce',
        component: () => import('@/views/webapp/services/introduce'),
        props: true,
        meta: { title: '服务介绍', icon: 'el-icon-menu', color: 'info' }
      },
      // 直邮服务介绍
      {
        path: 'service/introduce/express/:tag',
        name: 'introduce_express',
        component: () =>
          import('@/views/webapp/services/introduce/pages/express.vue'),
        props: true,
        meta: { title: '服务介绍', icon: 'el-icon-menu', color: 'info' }
      },
      // 个人中心
      {
        path: 'userspace',
        name: 'userspace',
        redirect: 'userspace/accountCenter',
        component: () => import('@/views/webapp/userspace'),
        meta: { title: '个人中心', icon: 'el-icon-menu', color: 'info' },
        children: [
          {
            path: 'accountCenter',
            name: 'accountCenter',
            component: () =>
              import(
                '@/views/webapp/userspace/components/basicInfo/accountCenter'
              ),
            meta: { title: '个人中心', icon: 'el-icon-menu', color: 'info' }
          },
          {
            path: 'accountInfo',
            name: 'accountInfo',
            component: () =>
              import(
                '@/views/webapp/userspace/components/basicInfo/accountInfo'
              ),
            meta: { title: '用户信息', icon: 'el-icon-menu', color: 'info' }
          },
          {
            path: 'accountManager/:tab',
            name: 'accountManager',
            props: true,
            component: () =>
              import(
                '@/views/webapp/userspace/components/basicInfo/accountManager'
              ),
            meta: { title: '账户管理', icon: 'el-icon-menu', color: 'info' }
          },
          {
            path: 'addressManager',
            name: 'addressManager',
            component: () =>
              import(
                '@/views/webapp/userspace/components/basicInfo/addressManager'
              ),
            meta: { title: '地址管理', icon: 'el-icon-menu', color: 'info' }
          },
          {
            path: 'orderQuery',
            name: 'orderQuery',
            component: () =>
              import(
                '@/views/webapp/userspace/components/orderInfo/orderQuery'
              ),
            meta: { title: '订单查询', icon: 'el-icon-menu', color: 'info' }
          }
        ]
      },
      // 商城
      {
        path: 'mall',
        name: 'mall',
        component: () => import('@/views/webapp/mall'),
        meta: { title: '商城', icon: 'el-icon-menu', color: 'info' },
        children: [
          // 包装材料商城
          {
            path: 'material',
            name: 'material',
            component: () => import('@/views/webapp/mall/material'),
            meta: { title: '包装材料', icon: 'el-icon-menu', color: 'info' },
            children: [
              {
                path: 'm',
                name: 'materialDetail',
                component: () => import('@/views/webapp/mall/material'),
                meta: { title: '详情', icon: 'el-icon-menu', color: 'info' }
              }
            ]
          }
        ]
      },
      // 关于我们
      {
        path: 'about',
        name: 'about',
        component: () => import('@/views/webapp/about'),
        meta: { title: '关于我们', icon: 'el-icon-menu', color: 'info' }
      },
      // 新闻中心
      {
        path: 'news',
        name: 'news',
        component: () => import('@/views/webapp/news'),
        redirect: 'news/news-home',
        meta: { title: '新闻中心', icon: 'el-icon-menu', color: 'info' },
        children: [
          {
            path: 'news-home',
            name: 'news-home',
            component: () => import('@/views/webapp/news/home'),
            meta: { title: '新闻中心', icon: 'el-icon-menu', color: 'info' }
          },
          {
            path: 'news-page/:id',
            name: 'news-page',
            props: true,
            component: () => import('@/views/webapp/news/components/newsPage'),
            meta: { title: '新闻详情', icon: 'el-icon-menu', color: 'info' }
          }
        ]
      },
      // {
      //   path: 'news/:id',
      //   name: 'newsPage',
      //   props: true,
      //   component: () => import('@/views/webapp/news/components/newsPage'),
      //   meta: { title: '新闻中心', icon: 'el-icon-menu', color: 'info' }
      // },
      // 运费报价
      {
        path: 'freight',
        name: 'freight',
        component: () => import('@/views/webapp/freight'),
        meta: { title: '运费报价', icon: 'el-icon-menu', type: 'red' }
      },
      // 问题中心
      {
        path: 'problem/:id',
        name: 'problem',
        props: true,
        component: () => import('@/views/webapp/problem'),
        meta: { title: '问题中心', icon: 'el-icon-menu', type: 'red' }
      },
      // sku
      {
        path: 'sku',
        name: 'sku',
        props: true,
        component: () => import('@/views/webapp/sku'),
        meta: { title: '货品详单', icon: 'el-icon-menu', type: 'red' }
      },
      // 购物车
      {
        path: 'cart',
        name: 'cart',
        component: () => import('@/views/webapp/cart/mycart'),
        meta: { title: '购物车', icon: 'el-icon-menu', type: 'red' }
      },
      // 创建订单
      {
        path: 'order/:timestamp',
        name: 'order',
        props: true,
        component: () => import('@/views/webapp/cart/checkorder'),
        meta: { title: '确认订单', icon: 'el-icon-menu', type: 'red' }
      },
      // 支付订单
      {
        path: 'pay/:orderNumber',
        name: 'payOrder',
        props: true,
        component: () => import('@/views/webapp/cart/payment'),
        meta: { title: '支付订单', icon: 'el-icon-menu', type: 'red' }
      },
      // 订单完成
      {
        path: 'finishorder/:orderNumber',
        name: 'finishorder',
        props: true,
        component: () => import('@/views/webapp/cart/finishOrder'),
        meta: { title: '订单完成', icon: 'el-icon-menu', type: 'red' }
      },
      {
        path: 'cid',
        name: 'cid',
        component: () => import('@/views/webapp/CID'),
        meta: { title: '身份认证中心', icon: 'el-icon-menu', color: 'info' }
      },
      // 页面测试
      {
        path: 'test',
        name: 'test',
        component: () =>
          import('@/components/WebsiteComponents/module/textInput'),
        meta: { title: '模块测试', icon: 'el-icon-menu', type: 'red' }
      }
    ]
  },
  // 安全中心
  {
    path: '/safe',
    name: 'safe',
    component: () => import('@/views/webapp/safe'),
    redirect: 'safe/index',
    meta: { title: '安全中心', icon: 'el-icon-menu', color: 'info' },
    children: [
      {
        path: 'index',
        name: 'safeindex',
        component: () => import('@/views/webapp/safe/components'),
        meta: { title: '安全中心', icon: 'el-icon-menu', color: 'info' }
      },
      {
        path: 'findpassword',
        name: 'findpassword',
        component: () => import('@/views/webapp/safe/components/findpassword'),
        meta: { title: '找回密码', icon: 'el-icon-menu', color: 'info' }
      },
      {
        path: 'changepassword',
        name: 'changepassword',
        component: () =>
          import('@/views/webapp/safe/components/changepassword'),
        meta: { title: '修改莫玛', icon: 'el-icon-menu', color: 'info' }
      }
    ]
  },
  // 奶粉商城
  {
    path: '/milkPowder',
    name: 'milkPowder',
    component: () => import('@/views/webapp/mall/milkPowder'),
    meta: { title: '奶粉商城', icon: 'el-icon-menu', color: 'info' }
  },
  // 奶粉详情页面
  {
    path: '/milkPowder/:id',
    name: 'detail',
    props: true,
    component: () => import('@/views/webapp/mall/milkPowder/details'),
    meta: { title: '商品详情', icon: 'el-icon-menu', color: 'info' }
  },
  // 奶粉详情页面
  {
    path: '/materialdetail/:id',
    name: 'materialdetail',
    props: true,
    component: () => import('@/views/webapp/mall/material/details'),
    meta: { title: '商品详情', icon: 'el-icon-menu', color: 'info' }
  }
]
export default webapprouter
