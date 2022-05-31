<template>
  <div class="container">
    <div class="loginBar"><loginBar /></div>
    <div class="carousel"><carousel /></div>
    <div class="search1"><search1 /></div>
    <div class="search2"><search2 /></div>
    <div class="goodsbox">
      <div class="hotGoods"><hotGoods /></div>
      <div class="goods"><goods @flushCartbox="flushCartBox" /></div>
    </div>
    <div class="page">
      <el-pagination background layout="prev, pager, next" :total="1000" />
    </div>
    <div class="cartbox">
      <cartbox :timestamp="timestamp" />
    </div>
  </div>
</template>

<script>
import { getToken } from '@/utils/auth.js'
export default {
  beforeRouteEnter(to, from, next) {
    next((vm) => {
      if (getToken()) {
        this.$store.dispatch('admin/getInfo')
        next()
      } else {
        vm.$router.push('/login')
      }
    })
  },
  components: {
    loginBar: () => import('@/views/webapp/layout/components/loginBar'),
    // carousel: () => import('@/views/webapp/index/components/carousel.vue'),
    carousel: () =>
      import('@/views/webapp/mall/milkPowder/components/carousel'),
    search1: () => import('@/views/webapp/mall/milkPowder/components/search1'),
    search2: () => import('@/views/webapp/mall/milkPowder/components/search2'),
    goods: () => import('@/views/webapp/mall/milkPowder/components/goods'),
    hotGoods: () =>
      import('@/views/webapp/mall/milkPowder/components/hotGoods'),
    cartbox: () => import('@/views/webapp/mall/milkPowder/components/cartbox')
  },
  data() {
    return {
      timestamp: null
    }
  },
  methods: {
    // handleClickCart() {
    //   window.location.hash = '/webapp/cart'
    // },
    flushCartBox() {
      this.timestamp = new Date().getTime()
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  // width: 100%;
  min-width: 1600px;
  position: relative;
  margin: 0 auto;
  &::-webkit-scrollbar {
    display: none;
  }
}
.carousel {
  position: relative;
  width: 100%;
  height: 31vh;
  margin-bottom: 7vh;
  /* background-color: aqua; */
}
.search1 {
  position: relative;
  width: 72%;
  height: 3%;
  // background-color: brown;
  margin: 0 auto;
}
.search2 {
  position: relative;
  width: 72%;
  height: 7.5%;
  // background-color: rgb(42, 120, 165);
  margin: 0 auto;
  padding-bottom: 1.2%;
}
.goodsbox {
  position: relative;
  width: 72%;
  // background-color: rgb(80, 34, 34);
  display: flex;
  margin: 0 auto;
}
.hotGoods {
  position: relative;
  width: 14%;
  border: 1px solid rgb(197, 195, 195);
  box-sizing: border-box;
}
.goods {
  position: relative;
  width: 86%;
  // background-color: rgb(91, 165, 42);
  margin: 0 auto;
}
.page {
  position: relative;
  width: 22vw;
  margin: 0 auto;
  padding-top: 3vh;
  padding-bottom: 3vh;
  // background-color: rgb(146, 36, 36);
}
.cartbox {
  position: fixed;
  right: 1.5%;
  top: 50%;
}
</style>
