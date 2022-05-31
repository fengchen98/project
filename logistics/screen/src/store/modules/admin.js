import { login, logout, getInfo, register } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'

const getDefaultState = () => {
  return {
    token: getToken(),
    authInfo: '',
    // roles: '',
    accountInfo: '',
    // balance: 0,
    // rebate: 0,
    // couponCount: 0,
    baseInfo: ''
    // name: '',
    // avatar: '',
    // signature: ''
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  // SET_NAME: (state, name) => {
  //   state.name = name
  // },
  // SET_AVATAR: (state, avatar) => {
  //   state.avatar = avatar
  // },
  // SET_ROLES: (state, roles) => {
  //   state.roles = roles
  // },
  // SET_SIGNATURE: (state, signature) => {
  //   state.signature = signature
  // },
  // SET_BALANCE: (state, balance) => {
  //   state.balance = balance
  // },
  // SET_REBATE: (state, rebate) => {
  //   state.rebate = rebate
  // },
  // SET_COUPONCOUNT: (state, couponCount) => {
  //   state.couponCount = couponCount
  // },
  SET_AUTHINFO: (state, data) => {
    state.authInfo = data
  },
  SET_ACCOUNTINFO: (state, data) => {
    state.accountInfo = data
  },
  SET_BASEINFO: (state, data) => {
    state.baseInfo = data
  }
}

const actions = {
  // user register
  register({ commit }, userInfo) {
    return new Promise((resolve, reject) => {
      register(userInfo)
        .then((response) => {
          // if (response.code == 20000) {
          //   login({ username: userInfo.username, password: userInfo.password })
          //     .then((response) => {
          //       const { data } = response
          //       setToken(data.token)
          //       commit('SET_TOKEN', data.token)
          //       resolve(data)
          //     })
          //     .catch((error) => {
          //       reject(error)
          //     })
          // }
          resolve(response)
        })
        .catch((error) => {
          reject(error)
        })
    })
  },
  // user login
  login({ commit }, userInfo) {
    // const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      // login({ username: username.trim(), password: password })
      login(userInfo)
        .then((response) => {
          // console.log('vuex login 拿到的res:', response)
          const token = response.data
          // console.log('token值为: ', token);
          setToken(token)
          commit('SET_TOKEN', token)
          resolve(response)
        })
        .catch((error) => {
          reject(error)
        })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo()
        // getInfo(state.token)
        .then((response) => {
          const data = response.data
          // console.log('get data: ', data)
          if (!data) {
            removeToken()
            commit('RESET_STATE')
            return reject('Verification failed, please Login again.')
          }
          const authinfo = {
            // 权限相关信息
            id: data.id,
            roles: data.level_id, // 角色等级
            confirm_idnumber: data.confirm_idnumber // 确认信息
          }
          const accountinfo = {
            // 账户相关信息
            balance: data.balance, // 余额
            rebate: data.rebate, // 优惠返利
            couponcount: data.couponcount // 优惠券数量
          }
          const baseinfo = {
            // 基本信息
            username: data.username, // 用户名
            nickname: data.nickname, // 昵称
            avatar: process.env.VUE_APP_BASE_API + data.avatar, // 头像
            signature: data.signature, // 个性签名
            phone: data.phone, // 电话
            email: data.email, // 邮箱
            postcode: data.postcode, // 邮编
            city: data.city, //  城市
            area: data.area, // 地区
            qq: data.qq, // qq
            wechat: data.wechat, // 微信,
            country: data.country, // 国家
            subscribe: data.subscribe
          }
          commit('SET_AUTHINFO', authinfo)
          commit('SET_ACCOUNTINFO', accountinfo)
          commit('SET_BASEINFO', baseinfo)
          // console.log(state);
          resolve(data)
        })
        .catch((error) => {
          removeToken()
          commit('RESET_STATE')
          reject(error)
        })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token)
        .then((res) => {
          // console.log(res)
          removeToken() // must remove  token  first
          // resetRouter()
          commit('RESET_STATE')
          resolve(res)
          // return res
        })
        .catch((error) => {
          reject(error)
        })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise((resolve) => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
