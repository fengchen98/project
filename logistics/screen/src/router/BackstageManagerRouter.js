import Layout from '@/layout'

const BackstageManager = [
  // {
  //   path: '/BackstageManager/login',
  //   component: () => import('@/views/BackstageManager/login/index'),
  //   hidden: true
  // },
  // 返回网站
  {
    path: '/BackstageManager/backhome',
    children: [
      {
        path: 'backhome',
        name: 'backhome',
        redirect: '/webapp',
        meta: { title: '门户', icon: 'el-icon-s-home' }
      }
    ]
  },
  // 控制台
  {
    path: '/BackstageManager',
    component: Layout,
    redirect: '/BackstageManager/dashboard',
    children: [
      {
        path: 'dashboard',
        name: '首页',
        component: () => import('@/views/BackstageManager/dashboard/index'),
        meta: { title: '首页', icon: 'dashboard' }
      }
    ]
  },
  // 个人中心
  {
    path: '/BackstageManager/userspace',
    component: Layout,
    redirect: '/BackstageManager/userspace/usercenter',
    name: 'back_userspace',
    hidden: true,
    meta: { title: '个人中心', icon: 'el-icon-user' },
    children: [
      {
        path: 'usercenter',
        name: 'usercenter',
        component: () =>
          import('@/views/BackstageManager/personalcenter/index'),
        meta: { title: '个人中心', icon: 'el-icon-user' }
      }
    ]
  },
  // 订单管理
  {
    path: '/BackstageManager/order',
    component: Layout,
    redirect: '/BackstageManager/order/mallOrder',
    name: 'pack',
    meta: { title: '订单管理', icon: 'el-icon-notebook-2' },
    children: [
      // {
      //   path: 'example',
      //   name: 'example',
      //   component: () =>
      //     import('@/views/BackstageManager/packageManagement/index'),
      //   meta: { title: '样例', icon: 'el-icon-star-on' }
      // },
      {
        path: 'mallOrder',
        name: 'mallOrder',
        component: () =>
          import('@/views/BackstageManager/orderManagement/mallOrder'),
        meta: { title: '商城订单', icon: 'el-icon-goods' }
      },
      {
        path: 'websiteOrder',
        name: 'websiteOrder',
        component: () =>
          import('@/views/BackstageManager/orderManagement/websiteOrder'),
        meta: { title: '网站订单', icon: 'el-icon-price-tag' }
      }
    ]
  },
  // 包裹管理
  {
    path: '/BackstageManager/pack',
    component: Layout,
    redirect: '/BackstageManager/pack/example',
    name: 'pack',
    meta: { title: '包裹管理', icon: 'el-icon-box' },
    children: [
      // {
      //   path: 'example',
      //   name: 'example',
      //   component: () =>
      //     import('@/views/BackstageManager/packageManagement/index'),
      //   meta: { title: '样例', icon: 'el-icon-star-on' }
      // },
      {
        path: 'pickup',
        name: 'Pickup',
        component: () =>
          import('@/views/BackstageManager/packageManagement/pickup'),
        meta: { title: '取件服务管理', icon: 'table' }
      },
      {
        path: 'warehousing',
        name: 'Warehousing',
        component: () => import('@/views/BackstageManager/tree/index'),
        meta: { title: '入库管理', icon: 'el-icon-s-home' }
      },
      {
        path: 'stock',
        name: 'Stock',
        component: () => import('@/views/BackstageManager/tree/index'),
        meta: { title: '库存管理', icon: 'el-icon-receiving' }
      },
      {
        path: 'customs',
        name: 'Customs',
        component: () => import('@/views/BackstageManager/tree/index'),
        meta: { title: '海关报备单', icon: 'skill' }
      },
      {
        path: 'tree',
        name: 'Tree',
        component: () => import('@/views/BackstageManager/tree/index'),
        meta: { title: '出库管理', icon: 'el-icon-truck' }
      },
      {
        path: 'outstock',
        name: 'Outstock',
        component: () => import('@/views/BackstageManager/tree/index'),
        meta: { title: '机场入库管理', icon: 'international' }
      },
      {
        path: 'clearance',
        name: 'Clearance',
        component: () => import('@/views/BackstageManager/tree/index'),
        meta: { title: '清关管理', icon: 'tab' }
      }
    ]
  },
  // 收件人管理
  {
    path: '/BackstageManager/recipients',
    component: Layout,
    redirect: '/BackstageManager/recipients/import',
    name: '收件人管理',
    meta: { title: '客户管理', icon: 'peoples' },
    children: [
      {
        path: 'import',
        name: 'Import',
        component: () =>
          import('@/views/BackstageManager/recipientManagement/customInfo'),
        meta: { title: '客户信息', icon: 'el-icon-user-solid' }
      },
      {
        path: 'export',
        name: 'Export',
        component: () =>
          import('@/views/BackstageManager/recipientManagement/index'),
        meta: { title: '导出信息', icon: 'link' }
      }
    ]
  },
  // 留言管理
  {
    path: '/BackstageManager/message',
    component: Layout,
    redirect: '/BackstageManager/message/join',
    name: '留言管理',
    meta: { title: '留言管理', icon: 'el-icon-chat-dot-square' },
    children: [
      {
        path: 'join',
        name: 'join',
        component: () =>
          import('@/views/BackstageManager/messageManagement/concatManager'),
        meta: { title: '联系我们', icon: 'el-icon-phone-outline' }
      },
      {
        path: 'contact',
        name: 'contact',
        component: () =>
          import('@/views/BackstageManager/messageManagement/joinManager'),
        meta: { title: '加盟合作', icon: 'el-icon-s-cooperation' }
      }
    ]
  },
  // 网站管理
  {
    path: '/BackstageManager/webapp',
    component: Layout,
    redirect: '/BackstageManager/webapp/carousel',
    name: '网站管理',
    meta: { title: '网站管理', icon: 'website' },
    children: [
      {
        path: 'carousel',
        name: 'carousel',
        component: () =>
          import(
            '@/views/BackstageManager/websiteManagement/indexManager/carousel'
          ),
        meta: { title: '轮播图', icon: 'websitecarousel' }
      },
      // {
      //   path: 'webindex',
      //   name: 'webindex',
      //   component: () =>
      //     import('@/views/BackstageManager/websiteManagement/indexManager'),
      //   meta: { title: '首页管理', icon: 'websiteback' },
      //   redirect: '/BackstageManager/webapp/index/carousel',
      //   children: [
      //     {
      //       path: 'carousel',
      //       name: 'carousel',
      //       component: () =>
      //         import(
      //           '@/views/BackstageManager/websiteManagement/indexManager/carousel'
      //         ),
      //       meta: { title: '轮播图', icon: 'websitecarousel' }
      //     },
      //     {
      //       path: 'specialservice',
      //       name: 'specialservice',
      //       component: () =>
      //         import(
      //           '@/views/BackstageManager/websiteManagement/indexManager/specialservice'
      //         ),
      //       meta: { title: '特色服务', icon: 'websitespecialservice' }
      //     },
      //     {
      //       path: 'directmail',
      //       name: 'directmail',
      //       component: () =>
      //         import(
      //           '@/views/BackstageManager/websiteManagement/indexManager/directmail'
      //         ),
      //       meta: { title: '直邮服务', icon: 'websitedirectmail' }
      //     },
      //     {
      //       path: 'helpcenter',
      //       name: 'helpcenter',
      //       component: () =>
      //         import(
      //           '@/views/BackstageManager/websiteManagement/indexManager/helpcenter/index'
      //         ),
      //       meta: { title: '帮助中心', icon: 'websitehelp' },
      //       children: [
      //         {
      //           path: 'helpabout',
      //           component: () =>
      //             import(
      //               '@/views/BackstageManager/websiteManagement/indexManager/helpcenter/about'
      //             ),
      //           name: 'helpabout',
      //           meta: { title: '关于我们', icon: 'websiteabout' }
      //         },
      //         {
      //           path: 'helpproblem',
      //           component: () =>
      //             import(
      //               '@/views/BackstageManager/websiteManagement/indexManager/helpcenter/problem'
      //             ),
      //           name: 'helpproblem',
      //           meta: { title: '常见问题', icon: 'el-icon-warning-outline' }
      //         },
      //         // {
      //         //   path: 'helpannounce',
      //         //   component: () =>
      //         //     import(
      //         //       '@/views/BackstageManager/websiteManagement/indexManager/helpcenter/announce'
      //         //     ),
      //         //   name: 'helpannounce',
      //         //   meta: { title: '最新公告', icon: 'websiteannounce' }
      //         // },
      //         // {
      //         //   path: 'helpactivity',
      //         //   name: 'helpactivity',
      //         //   component: () =>
      //         //     import(
      //         //       '@/views/BackstageManager/websiteManagement/indexManager/helpcenter/activity'
      //         //     ),
      //         //   meta: { title: '最新活动', icon: 'websiteactivity' }
      //         // },
      //         {
      //           path: 'helpcooperate',
      //           component: () =>
      //             import(
      //               '@/views/BackstageManager/websiteManagement/indexManager/helpcenter/cooperate'
      //             ),
      //           name: 'helpcooperate',
      //           meta: { title: '加盟合作', icon: 'websitecoop' }
      //         },
      //         {
      //           path: 'helpcontact',
      //           name: 'helpcontact',
      //           component: () =>
      //             import(
      //               '@/views/BackstageManager/websiteManagement/indexManager/helpcenter/contact'
      //             ),
      //           meta: { title: '联系我们', icon: 'websitecontact' }
      //         }
      //       ]
      //     }
      //   ]
      // },
      // {
      //   path: 'webservice',
      //   name: 'webservice',
      //   component: () =>
      //     import(
      //       '@/views/BackstageManager/websiteManagement/serviceManager/index'
      //     ),
      //   meta: { title: '服务与产品管理', icon: 'el-icon-shopping-bag-1' }
      // },
      // {
      //   path: 'webabout',
      //   name: 'webabout',
      //   component: () =>
      //     import(
      //       '@/views/BackstageManager/websiteManagement/aboutManager/index'
      //     ),
      //   meta: { title: '关于我们', icon: 'websiteabout' }
      // },
      // {
      //   path: 'webnews',
      //   name: 'webnews',
      //   component: () =>
      //     import(
      //       '@/views/BackstageManager/websiteManagement/newsManager/index'
      //     ),
      //   meta: { title: '新闻中心', icon: 'websiteannounce' },
      //   children: [
      //     {
      //       path: 'service',
      //       component: () =>
      //         import(
      //           '@/views/BackstageManager/websiteManagement/newsManager/service'
      //         ),
      //       name: 'news_service',
      //       meta: { title: '服务公告', icon: 'websiteabout' }
      //     },
      //     {
      //       path: 'news',
      //       component: () =>
      //         import(
      //           '@/views/BackstageManager/websiteManagement/newsManager/news'
      //         ),
      //       name: 'news_info',
      //       meta: { title: '新闻资讯', icon: 'el-icon-warning-outline' }
      //     },
      //     {
      //       path: 'goods',
      //       component: () =>
      //         import(
      //           '@/views/BackstageManager/websiteManagement/newsManager/goods'
      //         ),
      //       name: 'goods',
      //       meta: { title: '热门商品', icon: 'websiteannounce' }
      //     },
      //     {
      //       path: 'discount',
      //       name: 'discount',
      //       component: () =>
      //         import(
      //           '@/views/BackstageManager/websiteManagement/newsManager/discount'
      //         ),
      //       meta: { title: '优惠活动', icon: 'websiteactivity' }
      //     }
      //   ]
      // },
      {
        path: 'newsManager',
        name: 'newsManager',
        component: () =>
          import(
            '@/views/BackstageManager/websiteManagement/newsManager/index'
          ),
        meta: { title: '新闻中心', icon: 'el-icon-share' }
      },
      {
        path: 'problem',
        name: 'problem',
        component: () =>
          import(
            '@/views/BackstageManager/websiteManagement/problemManager/index'
          ),
        meta: { title: '问题中心', icon: 'el-icon-warning-outline' }
      },
      {
        path: 'webfreight',
        name: 'webfreight',
        component: () =>
          import(
            '@/views/BackstageManager/websiteManagement/freightManager/index'
          ),
        meta: { title: '运费报价', icon: 'el-icon-coin' }
      }
    ]
  },
  // 商城管理
  {
    path: '/BackstageManager/webmall',
    name: '/BackstageManager/webmall',
    component: Layout,
    // component: () => import('@/views/BackstageManager/mallManager/index'),
    meta: { title: '商城管理', icon: 'el-icon-shopping-cart-full' },
    children: [
      {
        path: 'mallgoods',
        name: 'mallgoods',
        component: () => import('@/views/BackstageManager/mallManager/goods'),
        meta: { title: '商品管理', icon: 'el-icon-goods' }
      },
      {
        path: 'mallmaterial',
        name: 'mallmaterial',
        component: () =>
          import('@/views/BackstageManager/mallManager/material'),
        meta: { title: '包装材料管理', icon: 'el-icon-box' }
      }
    ]
  },
  // 折叠菜单
  {
    path: '/BackstageManager/nested',
    component: Layout,
    redirect: '/nested/menu1',
    name: 'Nested',
    meta: { title: '多级菜单样例', icon: 'nested' },
    children: [
      {
        path: 'menu1',
        component: () => import('@/views/BackstageManager/nested/menu1/index'), // Parent router-view
        name: 'Menu1',
        meta: { title: 'Menu1' },
        children: [
          {
            path: 'menu1-1',
            component: () =>
              import('@/views/BackstageManager/nested/menu1/menu1-1'),
            name: 'Menu1-1',
            meta: { title: 'Menu1-1' }
          },
          {
            path: 'menu1-2',
            component: () =>
              import('@/views/BackstageManager/nested/menu1/menu1-2'),
            name: 'Menu1-2',
            meta: { title: 'Menu1-2' },
            children: [
              {
                path: 'menu1-2-1',
                component: () =>
                  import(
                    '@/views/BackstageManager/nested/menu1/menu1-2/menu1-2-1'
                  ),
                name: 'Menu1-2-1',
                meta: { title: 'Menu1-2-1' }
              },
              {
                path: 'menu1-2-2',
                component: () =>
                  import(
                    '@/views/BackstageManager/nested/menu1/menu1-2/menu1-2-2'
                  ),
                name: 'Menu1-2-2',
                meta: { title: 'Menu1-2-2' }
              }
            ]
          },
          {
            path: 'menu1-3',
            component: () =>
              import('@/views/BackstageManager/nested/menu1/menu1-3'),
            name: 'Menu1-3',
            meta: { title: 'Menu1-3' }
          }
        ]
      },
      {
        path: 'menu2',
        component: () => import('@/views/BackstageManager/nested/menu2/index'),
        name: 'Menu2',
        meta: { title: 'menu2' }
      }
    ]
  }
]

export default BackstageManager
