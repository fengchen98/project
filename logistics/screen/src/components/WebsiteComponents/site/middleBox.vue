<template>
  <!-- 组件外容器 -->
  <div class="container">
    <!--  图表容器  -->
    <div class="middle-1">
      <div class="middle-1-top">
        <i class="el-icon-time" />
      </div>
      <span class="text-1">01、24小时自助下单</span>
    </div>
    <div class="middle-2">
      <div class="middle-2-top">
        <i class="el-icon-shopping-cart-1" />
      </div>
      <span class="text-1">02、在线支付，自动出单</span>
    </div>
    <div class="middle-3">
      <div class="middle-3-top">
        <i class="el-icon-box" />
      </div>
      <span class="text-1">03、自行打包，取件/自送</span>
    </div>
    <div class="middle-4">
      <div class="middle-4-top">
        <i class="el-icon-truck" />
      </div>
      <span class="text-1">04、收到包裹，发往中国</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MiddleBox'
}
</script>

<style scoped>
.container {
  position: relative;
  width: 100%;
  height: 27vh;
  background-image: url('/static/images/ba2.png');
  background-repeat: no-repeat;
  display: flex;
}
.middle-1 {
  width: 10vw;
  height: 18vh;
  position: relative;
  top: 6vh;
  left: 23vw;
  /* background-color: rgb(148, 38, 38); */
}
.middle-1-top {
  width: 5vw;
  height: 5vw;
  position: relative;
  top: 1rem;
  left: 2vw;
  background-color: #01bff1;
  border-color: white;
  border: 1px solid white;
  border-radius: 20px;
  z-index: 2;
  display: flex;
  justify-content: center;
  align-items: center;
}
.el-icon-time {
  font-size: 250%;
  color: white;
  position: relative;
}
.text-1 {
  color: white;
  position: relative;
  top: 1.4vw;
}
.middle-2 {
  width: 10vw;
  height: 18vh;
  position: relative;
  top: 6vh;
  left: 28vw;
  /* background-color: rgb(148, 38, 38); */
}
.middle-2-top {
  width: 5vw;
  height: 5vw;
  position: relative;
  top: 1rem;
  left: 2vw;
  background-color: #01bff1;
  border-color: white;
  border: 1px solid white;
  border-radius: 20px;
  z-index: 2;
  display: flex;
  justify-content: center;
  align-items: center;
}
.el-icon-shopping-cart-1 {
  font-size: 250%;
  color: white;
  position: relative;
}
.middle-3 {
  width: 10vw;
  height: 18vh;
  position: relative;
  top: 6vh;
  left: 33vw;
  /* background-color: rgb(148, 38, 38); */
}
.middle-3-top {
  width: 5vw;
  height: 5vw;
  position: relative;
  top: 1rem;
  left: 2vw;
  background-color: #01bff1;
  border-color: white;
  border: 1px solid white;
  border-radius: 20px;
  z-index: 2;
  display: flex;
  justify-content: center;
  align-items: center;
}
.el-icon-box {
  font-size: 250%;
  color: white;
  position: relative;
}
.middle-4 {
  width: 10vw;
  height: 18vh;
  position: relative;
  top: 6vh;
  left: 38vw;
  /* background-color: rgb(148, 38, 38); */
}
.middle-4-top {
  width: 5vw;
  height: 5vw;
  position: relative;
  top: 1rem;
  left: 2vw;
  background-color: #01bff1;
  border-color: white;
  border: 1px solid white;
  border-radius: 20px;
  z-index: 2;
  display: flex;
  justify-content: center;
  align-items: center;
}
.el-icon-truck {
  font-size: 250%;
  color: white;
  position: relative;
}
</style>
