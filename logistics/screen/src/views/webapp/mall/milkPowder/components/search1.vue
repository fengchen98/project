<template>
  <el-form :inline="true" :model="formInline" class="box">
    <div>
      <el-form-item label="">
        <el-link :underline="false" icon="el-icon-s-unfold">全部商品</el-link>
      </el-form-item>
      <el-form-item
        label=""
      ><el-link :underline="false">奶粉</el-link>
      </el-form-item>
    </div>

    <!-- <el-button type="warning" style="visibility: hidden" />
    <el-button type="warning" style="visibility: hidden" />
    <el-button type="warning" style="visibility: hidden" />
    <el-button type="warning" style="visibility: hidden" />
    <el-button type="warning" style="visibility: hidden" />
    <el-button type="warning" style="visibility: hidden" />
    <el-button type="warning" style="visibility: hidden" />
    <el-button type="warning" style="visibility: hidden" />
    <el-button type="warning" style="visibility: hidden" />
    <el-button type="warning" style="visibility: hidden" />
    <el-button type="warning" style="visibility: hidden" />
    <el-button type="warning" style="visibility: hidden" />
    <el-button type="warning" style="visibility: hidden" />
    <el-button type="warning" style="visibility: hidden" /> -->
    <el-input v-model="input3" placeholder="搜索全站" class="input-with-select">
      <el-button slot="append" icon="el-icon-search" />
    </el-input>
  </el-form>
</template>

<script>
export default {
  name: 'Search1',
  data() {
    return {
      input3: '',
      sizeForm: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      formInline: {
        user: '',
        region: ''
      }
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!')
    }
  }
}
</script>

<style lang="scss" scoped>
.box {
  display: flex;
  justify-content: space-between;
}
.input-with-select {
  width: 18vw;
  height: 4vh;
}
</style>
