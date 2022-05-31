<template>
  <div class="container">
    <div class="navbar">
      <ul>
        <li>包装材料</li>
        <li>气泡保护类</li>
        <li>防水保护封装类</li>
        <li>包装纸箱类</li>
      </ul>
    </div>
    <div class="w">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/">活动管理</a></el-breadcrumb-item>
        <el-breadcrumb-item>活动列表</el-breadcrumb-item>
        <el-breadcrumb-item>活动详情</el-breadcrumb-item>
      </el-breadcrumb>
      <div class="product_intro">
        <product :id="id" />
      </div>
      <div class="product_info">
        <productinfo :id="id" />
      </div>
    </div>
  </div>
</template>

<script>
import product from './components/product.vue'
import productinfo from './components/productinfo.vue'
export default {
  components: {
    product,
    productinfo
  },
  props: {
    id: {
      type: String,
      default: ''
    }
  }
}
</script>

<style lang="scss" scoped>
.banner {
  width: 100%;
  height: 300px;
  background-image: url('/static/images/material_banner.jpg');
  background-size: cover;
  background-position: center;
  margin-bottom: 35px;
}
.w {
  width: 60%;
  margin: 10px auto;
}
.navbar {
  width: 100%;
  height: 4vh;
  background-color: #ff3500;
  ul {
    display: flex;
    justify-content: space-between;
    justify-items: center;
    height: 100%;
    width: 60%;
    margin: 0 auto;
  }
  li {
    list-style: none;
    width: 23%;
    color: #f4f4f4;
    font-size: 18px;
    line-height: 100%;
    padding: 5px;
  }
}
.product_intro {
  margin-top: 10px;
  margin-bottom: 30px;
}
</style>
