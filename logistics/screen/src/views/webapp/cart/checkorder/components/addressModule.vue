<template>
  <!-- 收货地址 -->
  <div class="mainWrapper">
    <div class="adressBox">
      <div v-if="address">
        <span>姓名:{{ address.name }}</span><br>
        <span>电话:{{ address.phone }}</span><br>
        <span>地区:{{ address.area }}</span><br>
        <span>地址:{{ address.address }}</span><br>
        <span>邮编:{{ address.postcode }}</span>
      </div>
      <el-button
        size="mini"
        type="primary "
        @click.native="dialogVisible = !dialogVisible"
      >{{ address ? '修改收获地址' : '选择收获地址' }}</el-button>
    </div>
    <el-dialog title="选择收获地址" :visible.sync="dialogVisible" width="60%">
      <popAddress @selectAddress="selectAddress" />
    </el-dialog>
  </div>
</template>

<script>
export default {
  components: {
    popAddress: () => import('./popAddressSelect.vue')
  },
  data() {
    return {
      dialogVisible: false,
      show: true,
      address: null,
      addressLists: []
    }
  },
  methods: {
    selectAddress(val) {
      this.$emit('selectAddress', val)
      this.address = val
      this.dialogVisible = false
    }
  }
}
</script>

<style lang="scss" scoped>
.adressBox {
  font-size: 0.5rem;
  width: 100%;
  overflow: hidden;
  text-overflow: ellipsis;
  span {
      white-space: nowrap;
  }
}
</style>
