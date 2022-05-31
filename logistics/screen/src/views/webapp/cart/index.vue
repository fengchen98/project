<template>
  <div class="container">
    <component
      :is="currentComponent"
      :check-order="checkOrder"
      @submitPay="handleCheckOrder"
    />
  </div>
</template>

<script>
export default {
  components: {
    mycart: () => import('./mycart/index.vue'),
    checkorder: () => import('./checkorder/index.vue')
  },
  data() {
    return {
      currentComponent: 'mycart',
      checkOrder: []
    }
  },
  methods: {
    handleCheckOrder(val) {
      console.log('父组件拿到数据', val)
      this.checkOrder = val
      this.currentComponent = 'checkorder'
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  width: 100%;
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
</style>
