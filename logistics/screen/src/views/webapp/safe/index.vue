<template>
  <div class="container">
    <div class="header">
      <div class="boxwrapper">
        <img src="/static/images/shenzhoulogo_white.png" alt="">
        <a class="homepage" @click="handleToHome">首页</a>
      </div>
    </div>
    <div class="content">
      <router-view :userinfo="userinfo" @getCodeInfo="getCodeInfo" />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userinfo: {}
    }
  },
  methods: {
    getCodeInfo(arg) {
      this.userinfo = arg
    },
    handleToHome() {
      this.$router.push('/')
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  width: 100%;
  height: 100%;
  padding: 0;
  margin: 0;
}
.header {
  width: 100%;
  height: 50vh;
  padding: 1rem 3rem;
  background-color: #3498db;
  background: url('/static/images/safe_bg_1.png');
  .boxwrapper {
    display: flex;
    justify-content: space-between;
    align-items: center;
    img {
      width: 320px;
      height: 90px;
    }
    .homepage {
      color: #fff;
      line-height: 2rem;
      font-size: 2rem;
      margin-right: 30rem;
      // text-decoration: underline;
      &:hover {
        color: #e67e22;
      }
    }
  }
}
.content {
  z-index: 1000;
  display: flex;
  justify-content: center;
}
</style>
