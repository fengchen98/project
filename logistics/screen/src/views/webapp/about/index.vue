<template>
  <div class="container">
    <div class="banner">
      <img src="/static/images/about_banner.png" alt="">
    </div>
    <div class="msgBox">
      <msg />
    </div>
    <div class="mainBox">
      <service />
      <advantage />
    </div>
  </div>
</template>

<script>
export default {
  components: {
    msg: () => import('./components/message.vue'),
    service: () => import('./components/service.vue'),
    advantage: () => import('./components/advantage.vue')
  }
}
</script>

<style lang="scss" scoped>
.container {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  width: 100%;
  transition: all 0.5s;
}
.banner {
  position: relative;
  img {
    width: 100%;
  }
}
.msgBox {
  position: absolute;
  width: 100%;
  top: 10%;
}
.mainBox {
  transform: translateY(15%);
  margin-bottom: 10%;
}
</style>
