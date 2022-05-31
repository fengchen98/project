const Mock = require('mockjs')

const tokens = {
  admin: {
    token: 'admin-token'
  },
  editor: {
    token: 'editor-token'
  }
}

const users = {
  'admin-token': {
    roles: ['admin'],
    introduction: 'I am a super administrator',
    // avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    avatar:
      'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F9150e4e5ly1fn0xivl3t9g206m08c761.gif&refer=http%3A%2F%2Fwww.sina.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639899915&t=3b02c812a977f908870abccafb7f9f7f',
    name: 'Super Admin'
  },
  'editor-token': {
    roles: ['editor'],
    introduction: 'I am an editor',
    avatar:
      'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Normal Editor'
  }
}

const custominfo_list = Mock.mock({
  'items|20': [
    {
      nickname: '@first',
      username: '@last',
      email: '@email',
      password: '@string(16)',
      realname: '@cname',
      tel: '@natural(10000000000, 19999999999)',
      postcode: '@zip',
      idnumber: 'number|18',
      address: '@province',
      detailinfo: '@city(true)',
      'hasidimg|1': ['已上传', '未上传'],
      createdate: '@date("yyyy-MM-dd HH:mm:ss")'
    }
  ]
})

const table_data = Mock.mock({
  'items|10': [
    {
      id: '@id',
      title: '@sentence(10, 20)',
      'status|1': ['published', 'draft', 'deleted'],
      author: 'name',
      display_time: '@datetime',
      pageviews: '@integer(300, 5000)'
    }
  ]
})

module.exports = [
  // user login
  {
    url: '/logistic-test-api/user/login',
    type: 'post',
    response: (config) => {
      const { username } = config.body
      const token = tokens[username]

      // mock error
      if (!token) {
        return {
          code: 60204,
          message: 'Account and password are incorrect.'
        }
      }

      return {
        code: 20000,
        data: token
      }
    }
  },

  // get user info
  {
    url: '/logistic-test-api/user/info.*',
    type: 'get',
    response: (config) => {
      const { token } = config.query
      const info = users[token]

      // mock error
      if (!info) {
        return {
          code: 50008,
          message: 'Login failed, unable to get user details.'
        }
      }

      return {
        code: 20000,
        data: info
      }
    }
  },

  // user logout
  {
    url: '/logistic-test-api/user/logout',
    type: 'post',
    response: (_) => {
      return {
        code: 20000,
        data: 'success'
      }
    }
  },

  {
    url: '/logistic-test-api/custominfo/list',
    type: 'get',
    response: (config) => {
      const items = custominfo_list.items
      return {
        code: 20000,
        data: {
          total: items.length,
          items: items
        }
      }
    }
  },
  {
    url: '/logistic-test-api/table/list',
    type: 'get',
    response: (config) => {
      const items = table_data.items
      return {
        code: 20000,
        data: {
          total: items.length,
          items: items
        }
      }
    }
  }
]
