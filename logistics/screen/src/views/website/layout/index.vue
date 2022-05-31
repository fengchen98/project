<template>
  <div class="mainWrap">
    <loginBar class="loginbar-container" />
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
  }
}
</script>

<style></style>
