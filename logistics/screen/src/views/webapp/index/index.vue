<template>
  <div class="container">
    <div class="carouselWrapper">
      <div class="actionWrapper">
        <action />
      </div>
      <carousel />
    </div>
    <div class="toolsbarWrapper">
      <toolsbar />
    </div>
    <div class="specialservicebarWrapper">
      <specialservice />
    </div>
    <div class="directmailWrapper">
      <directmail />
    </div>
    <div class="helpcenterWrapper">
      <helpcenter />
    </div>
    <div class="partnerWrapper">
      <partner />
    </div>
  </div>
</template>

<script>
export default {
  name: 'IndexCom',
  components: {
    // carousel: () => import('@/views/webapp/index/components/carousel.vue'),
    carousel: (resolve) =>
      require(['@/views/webapp/index/components/carousel.vue'], resolve),
    action: () => import('@/views/webapp/index/components/action.vue'),
    toolsbar: () => import('@/views/webapp/index/components/toolsbar.vue'),
    specialservice: () =>
      import('@/views/webapp/index/components/specialservice.vue'),
    directmail: () => import('@/views/webapp/index/components/directmail.vue'),
    helpcenter: () => import('@/views/webapp/index/components/helpcenter'),
    partner: () => import('@/views/webapp/index/components/partner')
  }
}
</script>

<style lang="scss" scoped>
.container {
  margin: 0 auto;
  &::-webkit-scrollbar {
    display: none;
  }
}
.carouselWrapper {
  position: relative;
  margin: 0 auto;
}
.actionWrapper {
  position: absolute;
  z-index: 10;
  top: 43%;
  width: 100%;
}
@media screen and (min-width: 2000px) {
  .actionWrapper {
  position: absolute;
    top: 50%;
  }
}
</style>
