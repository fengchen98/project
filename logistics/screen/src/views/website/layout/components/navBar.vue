<template>
  <div class="container">
    <div class="navWrap">
      <div class="logoWrap">
        <img
          src="/static/images/shenzhoulogo.png"
          alt="failed to download image"
        >
      </div>
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        :router="true"
        @select="handleSelect"
      >
        <el-menu-item
          v-for="route in routes"
          :key="route.path"
          :item="route"
          :base-path="route.path"
          :index="route.path"
          v-text="route.meta.title"
        >首页</el-menu-item>
      </el-menu>
    </div>
    <transition name="fade" mode="out-in">
      <div v-if="showBanner" class="advBanner">
        <div class="textWrap">
          <div class="leftText">
            <span
              class="bannerText aboutus"
              @click="handleCilckAbout"
            >关于我们</span>
            <span class="bannerText">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
            <span class="bannerText">欢迎您登录 Ors Int Ltd. 官网</span>
          </div>
          <div class="rightText">
            <span class="bannerText">当前位置：首页 >> </span>
            <span class="bannerText" v-text="currentRoute" />
          </div>
        </div>
      </div>
    </transition>
  </div>
</template>

<script>
import SiteRouter from '@/router/SiteRouter'
export default {
  name: 'Navbar',
  data() {
    return {
      activeIndex: 'home',
      showBanner: false,
      currentRoute: null
    }
  },
  computed: {
    routes() {
      const pathReg = /^(\/website)/
      const routeMap = SiteRouter.filter((item) => pathReg.test(item.path))
      return routeMap[0].children
    }
  },
  watch: {
    $route(to) {
      this.currentRoute = to.meta.title
      if (/(\/website\/home)/.test(to.fullPath)) {
        this.showBanner = false
      } else {
        this.showBanner = true
      }
    }
  },
  created() {
    this.onloadPage()
  },
  methods: {
    onloadPage() {
      // console.log(this.$router.history.current)
      this.activeIndex = this.$router.history.current.name
      this.currentRoute = this.$router.history.current.meta.title
      this.showBanner = this.activeIndex !== 'home'
    },
    handleSelect(arg) {
      // console.log(arg)
    },
    handleCilckAbout() {
      window.location.hash = `#/website/about`
    }
  }
}
</script>

<style scoped>
.container {
  position: relative;
  width: 100%;
  height: 100%;
}
.navWrap {
  width: 100%;
  display: flex;
  justify-content: center;
  background-color: #fff;
}
.advBanner {
  width: 100%;
  height: 30vh;
  background: url('/static/images/advBanner.jpg') no-repeat;
  background-position-x: center;
  background-size: cover;
  display: flex;
  align-items: flex-end;
}
.aboutus {
  cursor: pointer;
}
.aboutus:hover {
  color: bisque;
}
.bannerText {
  color: #fff;
}
.textWrap {
  width: 100%;
  height: 5vh;
  padding: 0;
  margin: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.el-menu-demo {
  display: flex;
  align-items: center;
}
</style>
