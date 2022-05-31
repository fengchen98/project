<template>
  <div class="container">
    <!--  @tab-click="handleClick" -->
    <el-tabs v-model="activeName">
      <el-tab-pane
        label="余额信息"
        name="recharge"
        class="tabs_items"
      ><recharge /></el-tab-pane>
      <el-tab-pane
        label="账户明细"
        name="details"
        class="tabs_items"
      ><detail /></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  components: {
    recharge: () => import('./components/recharge.vue'),
    detail: () => import('./components/details.vue')
  },
  props: {
    tab: {
      type: String,
      default: () => ''
    }
  },
  data() {
    return {
      activeName: this.tab
    }
  },
  watch: {
    // tab(newValue, oldValue) {
    //   // console.log(oldValue, newValue)
    //   // this.activeName = newValue
    // }
  }
  // methods: {
  //   handleClick(tab) {
  //     console.log(tab.name)
  //     this.$router.push(`/webapp/userspace/accountManager/${tab.name}`)
  //   }
  // }
}
</script>

<style lang="scss" scoped></style>
