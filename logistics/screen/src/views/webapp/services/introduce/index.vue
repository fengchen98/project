<template>
  <div class="container">
    <component :is="currentPage" />
  </div>
</template>

<script>
export default {
  components: {
    milkpowder: () => import('./pages/milkpowder.vue'),
    transport: () => import('./pages/transport.vue'),
    package: () => import('./pages/package.vue'),
    sad: () => import('./pages/sad.vue')
  },
  props: {
    tag: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      currentPage: this.tag
    }
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
</style>
