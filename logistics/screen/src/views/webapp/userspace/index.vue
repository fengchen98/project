<template>
  <div class="container">
    <div class="mainWrapper">
      <div class="sideNavWrapper">
        <sideNav />
      </div>
      <div class="rightWrapper">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
import { getToken } from '@/utils/auth.js'

export default {
  beforeRouteEnter(to, from, next) {
    next((vm) => {
      if (getToken()) {
        vm.$store.dispatch('admin/getInfo')
        next()
      } else {
        vm.$router.push('/login')
      }
    })
  },
  components: {
    sideNav: () => import('./components/sideNav.vue')
  }
}
</script>

<style lang="scss" scoped>
.container {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  width: 100%;
}
.mainWrapper {
  display: flex;
  width: 60%;
  margin: 0 auto;
  padding: 2rem;
  .sideNavWrapper {
    width: 15%;
    margin-right: 2rem;
  }
  .rightWrapper {
    // background-color: rgba(243, 249, 250, 0.95);
    width: 85%;
    // height: 600px;
  }
}
</style>
