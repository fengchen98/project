import Layout from '@/views/webapp/layout'

const SiteRouter = [
  {
    path: '/website',
    component: Layout,
    redirect: '/website/home',
    children: [
      {
        path: 'home',
        name: 'home',
        component: () => import('@/views/website/home'),
        meta: { title: '首页', icon: 'el-icon-menu' }
      },
      {
        path: 'about',
        name: 'about',
        component: () => import('@/views/website/about'),
        meta: { title: '关于我们', icon: 'el-icon-menu' }
      },
      {
        path: 'business',
        name: 'business',
        component: () => import('@/views/website/business'),
        meta: { title: '业务管理', icon: 'el-icon-menu' }
      },
      {
        path: 'material',
        name: 'material',
        component: () => import('@/views/website/material'),
        meta: { title: '包装材料', icon: 'el-icon-menu' }
      },
      {
        path: 'news',
        name: 'news',
        component: () => import('@/views/website/news'),
        meta: { title: '新闻动态', icon: 'el-icon-menu' }
      },
      {
        path: 'mall',
        name: 'mall',
        component: () => import('@/views/website/mall'),
        meta: { title: '速递商城', icon: 'el-icon-menu' }
      },
      {
        path: 'problem',
        name: 'problem',
        component: () => import('@/views/website/problem'),
        meta: { title: '常见问题', icon: 'el-icon-menu' }
      },
      {
        path: 'user',
        name: 'user',
        component: () => import('@/views/website/user'),
        meta: { title: '个人中心', icon: 'el-icon-menu' }
      }
    ]
  },
  {
    path: '/site/test',
    component: () => import('@/views/website/layout/index'),
    name: '组件测试'
  }
]
export default SiteRouter
