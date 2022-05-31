<template>
  <div class="container">
    <div class="loginWrap">
      <div class="user-box">
        <div class="box-left">
          <a class="toTag" href="/">
            [<i class="el-icon-s-home" /> 返回首页 ]</a>
        </div>
        <div v-if="baseinfo" class="box-right">
          <span>欢迎您！ </span>
          <span class="text" v-text="baseinfo.nickname || baseinfo.name" />
          <span
            v-if="showBackend"
            class="toTag"
            @click="toBackend"
          >[ <i class="el-icon-help" /> 控制台 ]</span>
          <span
            class="toTag"
            @click="toCart"
          >[ <i class="el-icon-shopping-cart-full" /> 购物车 ]</span>
          <a
            class="toTag"
            href="#/webapp/userspace"
          >[ <i class="el-icon-user-solid" /> 个人中心 ]</a>
          <!-- <span style="margin: 0 0.5rem">|</span> -->
          <!-- <span style="margin: 0 .5rem" class="text">[个人中心]</span> -->
          <span class="toTag" @click="logout">退出</span>
        </div>
        <span
          v-else
          class="toTag"
          @click="toLogin"
        >登录&nbsp; / &nbsp;注册</span>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'LoginBar',
  data() {
    return {}
  },
  computed: {
    ...mapGetters(['authinfo', 'baseinfo']),
    showBackend() {
      let flag = false
      if (this.authinfo.roles > 1) {
        flag = true
      }
      return flag
    }
  },
  created() {
    // console.log(this.$store.state.admin)
  },
  methods: {
    toLogin() {
      window.location.hash = '/login'
    },
    toBackend() {
      window.location.hash = '/BackstageManager'
    },
    toCart() {
      window.location.hash = '/webapp/cart'
    },
    async logout() {
      await this.$store.dispatch('admin/logout')
      // console.log('[login bar]', logoutInfo)
      this.$message({
        type: 'success',
        message: '您已退出登录'
      })
      this.$router.push('/')
      // this.$router.push(`/BackstageManagerlogin?redirect=${this.$route.fullPath}`)
      // window.location.reload()
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  width: 100%;
  min-width: 1200px;
}
.user-box {
  width: 100%;
  display: flex;
  justify-content: space-between;
  font-size: 1.3rem;
}
.toTag {
  cursor: pointer;
  font-size: 1.3rem;
  margin: 0 0.5rem;
  &:hover {
    color: #fab1a0;
  }
}
.loginWrap {
  padding: 0 22%;
  display: flex;
  justify-content: space-betwee;
  align-items: center;
  height: 3rem;
  background-color: #000;
  color: #fff;
  a {
    text-decoration: none;
  }
}
.registertext {
  margin-right: 26.5%;
}
</style>
