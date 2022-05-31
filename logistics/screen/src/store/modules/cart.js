import { get_cartstuff } from '@/api/cart'

const state = {
  cartStuff: [],
  orderStuff: []
}

const mutations = {
  GET_CARTSTUFF: (state) => {
    return state.cartStuff
  },
  SET_CARTSTUFF: (state, data) => {
    state.cartStuff = data
  },
  GET_ORDERSTUFF: (state) => {
    return state.orderStuff
  },
  SET_ORDERSTUFF: (state, data) => {
    state.cartStuff = data
  }
}

const actions = {
  getCartStuff({ commit }) {
    // console.log('在vuex中执行了操作')
    return new Promise((resolve, reject) => {
      get_cartstuff()
        .then((response) => {
          // console.log('vux中接收到api的返回', response)
          const data = response.data
          if (!data) {
            return reject('Failed to get shopping cart information.')
          }
          commit('SET_CARTSTUFF', data)
          resolve(data)
        })
        .catch((error) => {
          reject(error)
        })
    })
  },
  setOrderStuff({ commit }, data) {
    // console.log('setOrderStuff接收到的:', data)
    commit('SET_ORDERSTUFF', data)
  },
  getOrderStuff({ commit }) {
    // console.log('getOrderStuff接收到指令', commit('GET_ORDERSTUFF'))
    return commit('GET_ORDERSTUFF')
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
