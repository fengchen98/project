<template>
  <div class="mainWrap">
    <div v-if="showAdv" class="adv">
      该系统为测试平台, 不代表最终上线版本
      <!-- <a href=""> 即可加入</a> -->
      <span
        style="
          position: absolute;
          right: 200px;
          font-size: 3rem;
          cursor: pointer;
          font-weight: 100;
        "
        @click="showAdv = false"
      >×</span>
    </div>
    <loginBar ref="loginbar" class="loginbar-container" />
    <navbar class="navbar-container" />
    <mainContent class="mainContent-container" />
    <footerBar class="footerBar-container" />
  </div>
</template>

<script>
export default {
  name: 'WebsiteLayout',
  components: {
    loginBar: () => import('./components/loginBar.vue'),
    navbar: () => import('./components/navBar.vue'),
    mainContent: () => import('./components/mainContent.vue'),
    footerBar: () => import('./components/footerBar.vue')
  },
  data() {
    return {
      showAdv: true
    }
  }
}
</script>

<style lang="scss" scoped>
.mainWrap {
  margin: 0 auto;
  min-width: 1600px;
  .adv {
    position: sticky;
    display: block;
    top: 0;
    width: 100%;
    height: 50px;
    background-color: rgba(41, 128, 185, 0.7);
    z-index: 1000;
    font-size: 1.8rem;
    line-height: 50px;
    text-align: center;
    color: #fff;
    a {
      text-decoration: underline;
    }
  }
}
.mainContent {
  background-color: rbga(244, 244, 244, 1);
}
@media (screen) and (max-width: 2560px) {
  .mainWrap {
    margin: 0 auto;
    min-width: 1600px;
    // max-width: 100vw;
  }
}
</style>
