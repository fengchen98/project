<template>
  <div class="container">
    <div class="detailInfo">
      <div>商品名称: <span v-text="product.title" /></div>
      <div>商品品牌: <span v-text="product.brand" /></div>
      <!-- <div>产地: <span v-text="product.origin" /></div> -->
      <div>奶粉段位: <span v-text="product.milk_rank" /></div>
      <div>蓝帽标识: <span v-text="product.blue_cap_mark" /></div>
      <div>重量: <span v-text="product.weight + 'kg'" /></div>
      <div>净含量: <span v-text="product.net_weight" /></div>
      <div>剂型: <span v-text="product.dosage_form" /></div>
      <div>原产地: <span v-text="product.origin_place" /></div>
      <div>适合年龄: <span v-text="product.suit_age" /></div>
      <div>包装规格: <span v-text="product.spec_type" /></div>
      <div>包装类型: : <span v-text="product.package_type" /></div>
      <div>原料: <span v-text="product.ingredient" /></div>
      <div>描述: <span v-text="product.description" /></div>
    </div>
    <div class="imgBox">
      <img
        v-for="(src, index) in product.introduce_images"
        :key="index"
        :src="src"
        alt="商品介绍"
      >
    </div>
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
  box-sizing: border-box;
  width: 100%;
  height: 100%;
  padding: 2rem;
}
.detailInfo {
  display: flex;
  flex-flow: wrap;
  border-bottom: 1px solid #7f8c8d;
  padding-bottom: 1rem;
  div {
    min-width: 45%;
    max-width: 50%;
    color: #000;
    padding: 0.5rem;
    margin-right: 2rem;
    font-size: 1rem;
  }
}

.imgBox {
  margin-top: 2rem;
  img {
    width: 100%;
  }
}
</style>
