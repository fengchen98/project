<template>
  <section class="app-main">
    <transition appear name="fade" mode="out-in">
      <keep-alive>
        <router-view :key="key" />
      </keep-alive>
    </transition>
  </section>
</template>

<script>
export default {
  name: 'MainContent',
  computed: {
    key() {
      // console.log(this.$route)
      return this.$route.path
    }
  }
}
</script>

<style scoped>
.app-main {
  /*50 = navbar  */
  padding: 0;
  margin: 0;
  min-height: calc(100vh - 261px);
  position: relative;
  width: 100%;
  min-width: 1200px;
  position: relative;
  overflow: hidden;
  /* background-color: rbga(244, 244, 244, 1); */
}
.fixed-header + .app-main {
  padding-top: 50px;
}
</style>

<style lang="scss">
// fix css style bug in open el-dialog
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 15px;
  }
}
</style>
