<template>
  <!-- 组件外容器 -->
  <div class="container">
    <!--  图表容器  -->
    <div class="container-left">
      <div class="left-header">
        <el-tabs
          v-model="activeName"
          type="card"
          class="guide"
          @tab-click="handleClick"
        >
          <el-tab-pane label="立即下单" name="0" />
          <el-tab-pane label="包裹查询" name="1" />
          <el-tab-pane label="订单查询" name="2" />
        </el-tabs>
      </div>
      <div class="left-main">
        <div v-if="isShowDiv[0]" class="first">
          <div class="first-body">
            <div class="first-body-top">
              <el-form
                :inline="true"
                :model="formInline"
                class="demo-form-inline"
              >
                <el-form-item label="发往地">
                  <el-select
                    v-model="formInline.region"
                    placeholder="请选择"
                    :clearable="true"
                  >
                    <el-option label="中国大陆" value="dalu" />
                    <el-option label="中国香港" value="xianggang" />
                    <el-option label="中国台湾" value="taiwan" />
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-button
                    type="primary"
                    class="text-2"
                    @click="onSubmit"
                  >立即下单</el-button>
                </el-form-item>
              </el-form>
              <span class="text-1">批量下单</span>
            </div>
            <div class="first-body-down">
              <span class="text-3">神州物流竭诚为您服务！！！</span>
            </div>
          </div>
        </div>
        <div v-if="isShowDiv[1]" class="second">
          <div class="second-body">
            <el-input v-model="input.packageCode" placeholder="输入包裹单号" />
            <span class="text-4">例如：SD123456789GB</span>
            <el-button type="primary" class="selectButton">包裹查询</el-button>
          </div>
        </div>
        <div v-if="isShowDiv[2]" class="third">
          <div class="third-body">
            <el-input v-model="input.orderCode" placeholder="输入订单号" />
            <el-button type="primary" class="selectButton">订单查询</el-button>
          </div>
        </div>
      </div>
    </div>
    <!-- **************** -->
    <div class="container-right">
      <span class="right-1 text">公告通知</span>
      <span class="right-2 text">1【通知】受台风天气影响 部分包裹..</span>
      <span class="right-3 text">2【公告】关于行李专线的重要通知</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SelectBox',
  data() {
    return {
      isShowDiv: [true, false, false],
      activeName: '0',
      formInline: {
        user: '',
        region: ''
      },
      input: {
        packageCode: null,
        orderCode: null
      }
    }
  },
  methods: {
    handleClick(tab) {
      console.log(tab)
      this.isShowDiv = [false, false, false]
      this.isShowDiv[tab.name] = true
    },
    onSubmit() {
      console.log('submit!')
    }
  }
}
</script>

<style scoped>
.el-input{
  width: 80%;
}
.el-form-item{
  margin: 0;
}
.container {
  position: relative;
  width: 100%;
  /* height: 43vh; */
  background-color: #fff;
  display: flex;
  box-shadow: 5px 5px 20px 0 #636e72;
}
.container-left {
  position: relative;
  flex: 1.5;
  /* width: 33vw; */
  /* height: 100%; */
  /* background-color: #fff; */
  /* background: url('/static/images/global.jpg') no-repeat ; */
  opacity: .8;
}
.guide {
  position: relative;
  width: 33vw;
  left: 2vw;
  top: 1vh;
}
.container-left::after {
  content: '';
  flex: 1.5;
  width: 100%;
  height: 97%;
  position: absolute;
  /* background-color: rgb(173, 202, 144); */
  background-image: url('/static/images/global.jpg');
  opacity: 0.5;
  /* left: 1.5vw; */
  top: 1vh;
  z-index: -1;
}

.first-body {
  width: 33vw;
  height: 20vh;
  /* background-color: rgb(99, 186, 197); */
  position: relative;
  padding: 2vh;
  display: flex;
  flex-direction: column;
}
.second{
  width: 100%;
  position: relative;
}
.second-body {

  width: 100%;
  height: 20vh;
  /* background-color: rgb(99, 186, 197); */
  position: relative;
  top: 2vh;
  left: 2vw;
  display: flex;
  flex-direction: column;
}
.first-body-top {
  width: 33vw;
  /* height: 13vh; */
  /* background-color: rgb(177, 228, 94);  */
  display: flex;
  justify-content: space-around ;
  align-items: center;
}
.first-body-down{
  position: relative;
  top: 10%;
  left: 7.5%;
}
.text-1 {
  cursor: pointer;
  color: rgb(36, 113, 201);
}
.text-1:hover {
  color: #e17055;
}
.text-2 {
  margin-left: 30%;
}
.text-3 {
  color: rgb(36, 113, 201);
}
.text-4 {
  position: relative;
  top: 1.5vh;
}
.selectButton {
  position: relative;
  top: 4vh;
  width: 8vw;
  height: 5vh;
  border: moccasin;
  background-color: rgb(230, 170, 68);
}
.third-body {
  width: 33vw;
  height: 20vh;
  /* background-color: rgb(99, 186, 197); */
  position: relative;
  top: 2vh;
  left: 2vw;
  display: flex;
  flex-direction: column;
}
.text{
  color: #fff;
}
.container-right {
  flex: 1;
  width: 26vw;
  height: 43vh;
  background-image: url('/static/images/notice.png');
  display: flex;
  flex-direction: column;
}
.right-1 {
  position: relative;
  width: 20vw;
  height: 5vh;
  left: 11.5vw;
  top: 10vh;
  font-size: 21px;
  font-weight: 700;
  color: #fff200;
}
.right-2 {
  width: 30vw;
  height: 5vh;
  position: relative;
  top: 12vh;
  left: 6vw;
  font-size: 17px;
}
.right-3 {
  width: 30vw;
  height: 5vh;
  position: relative;
  left: 6vw;
  top: 13vh;
  font-size: 17px;
}
</style>
