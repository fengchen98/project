import Vue from 'vue'
import SvgIcon from '@/components/BackstageManagerComponents/SvgIcon'// svg component

// register globally
Vue.component('svg-icon', SvgIcon)

// const req = require.context('./svg', false, /\.svg$/)
const req = require.context('./svg/default', false, /\.svg$/)
const req2 = require.context('./svg/indexIcon', false, /\.svg$/)
const req3 = require.context('./svg/back', false, /\.svg$/)
const requireAll = requireContext => requireContext.keys().map(requireContext)
requireAll(req)
requireAll(req2)
requireAll(req3)
