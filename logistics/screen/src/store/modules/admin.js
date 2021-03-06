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
          // console.log('vuex login ?????????res:', response)
          const token = response.data
          // console.log('token??????: ', token);
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
            // ??????????????????
            id: data.id,
            roles: data.level_id, // ????????????
            confirm_idnumber: data.confirm_idnumber // ????????????
          }
          const accountinfo = {
            // ??????????????????
            balance: data.balance, // ??????
            rebate: data.rebate, // ????????????
            couponcount: data.couponcount // ???????????????
          }
          const baseinfo = {
            // ????????????
            username: data.username, // ?????????
            nickname: data.nickname, // ??????
            avatar: process.env.VUE_APP_BASE_API + data.avatar, // ??????
            signature: data.signature, // ????????????
            phone: data.phone, // ??????
            email: data.email, // ??????
            postcode: data.postcode, // ??????
            city: data.city, //  ??????
            area: data.area, // ??????
            qq: data.qq, // qq
            wechat: data.wechat, // ??????,
            country: data.country, // ??????
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
