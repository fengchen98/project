<template>
  <div class="container">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane
        label="收件人管理"
        name="first"
        class="tabs_items"
      ><receive /></el-tab-pane>
      <el-tab-pane
        label="寄件人管理"
        name="second"
        class="tabs_items"
      ><send /></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  components: {
    receive: () => import('./components/receive.vue'),
    send: () => import('./components/send.vue')
  },
  data() {
    return {
      activeName: 'first'
    }
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    }
  }
}
</script>

<style lang="scss" scoped>
.tabs_items {
  color: #000;
}
</style>
