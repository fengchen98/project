<template>
  <div ref="main_container" class="outer">
    <h2>热卖商品</h2>
    <div v-for="item in dataSource" :key="item.id" class="hotgoods" @click="handleboxClick(index)">
      <div class="goodsPic"><img :src="item.img" alt=""></div>
      <div class="goodsPrice">{{ item.price }}</div>
      <div class="goodsText">{{ item.text }}</div>
      <div class="goodsEvaluate">{{ item.evaluate }}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HotGoods',
  data() {
    return {
      dataSource: [
        {
          id: '1',
          img: '/static/images/hotmilk1.jpg',
          price: '¥429.00',
          text: '美赞臣(Meadjohnson)第二代蓝臻幼儿配方奶粉3段(12-36',
          evaluate: '已有468143人评价'
        },
        {
          id: '2',
          img: '/static/images/hotmilk2.jpg',
          price: '¥429.00',
          text: '美赞臣(Meadjohnson)第二代蓝臻幼儿配方奶粉3段(12-36',
          evaluate: '已有468143人评价'
        },
        {
          id: '3',
          img: '/static/images/hotmilk3.jpg',
          price: '¥429.00',
          text: '美赞臣(Meadjohnson)第二代蓝臻幼儿配方奶粉3段(12-36',
          evaluate: '已有468143人评价'
        }
      ]
    }
  },
  methods: {
    handleboxClick(arg) {
      window.location.hash = `/milkPowder/${arg}`
    }
  }
}
</script>

<style lang="scss" scoped>
.outer {
  position: relative;
  width: 100%;
  height: 100%;
}
.outer h2 {
  font-size: 20px;
  font-weight: bold;
  margin-left: 10%;
  color: rgb(243, 92, 92);
}
.hotgoods {
  position: relative;
  width: 100%;
  height: 28%;
  // background-color: rgb(143, 60, 60);
  // margin-top: 15%;
  padding-top: 10%;
}
.hotgoods:hover{
  box-shadow: 0 0 5px rgb(63, 63, 63);
}
.goodsPic {
  position: relative;
  // width: 83.5%;
  width:16rem;
  height: 16rem;
  margin: 0 auto;
  background: rgb(221, 147, 10);
}
.goodsPrice {
  font-size: 15px;
  font-weight: bold;
  color: #e5423c;
  padding-left: 10%;
  padding-top: 5%;
}
.goodsText {
  font-size: 12px;
  padding-left: 10%;
  padding-right: 5%;
  padding-top: 5%;
  color: #666;
}
.goodsEvaluate {
  font-size: 12px;
  padding-left: 10%;
  padding-top: 5%;
  color: #666;
}
</style>
