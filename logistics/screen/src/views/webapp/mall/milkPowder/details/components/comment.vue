<template>
  <div class="container">
    <span>商品ID {{ product.id }}</span>
    <span>评论功能正在开发中……</span>
  </div>
</template>

<script>
export default {
  props: {
    product: {
      type: Object,
      default: null
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
    width: 100%;
    height: 500px;
}
</style>
