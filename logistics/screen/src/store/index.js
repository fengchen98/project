import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import app from './modules/app'
import settings from './modules/settings'
import admin from './modules/admin'
import cart from './modules/cart'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    settings,
    admin,
    cart
  },
  getters
})

export default store
