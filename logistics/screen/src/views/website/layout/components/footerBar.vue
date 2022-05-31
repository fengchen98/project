<template>
  <div class="container">
    <div class="footerWrap">
      <div class="textWrap">
        <div class="linkWrap">
          <span class="text" @click="handleclick('home')">网站首页</span><span>&nbsp;|&nbsp;</span>
          <span class="text" @click="handleclick('about')">关于我们</span><span>&nbsp;|&nbsp;</span>
          <span class="text" @click="handleclick('business')">业务介绍</span><span>&nbsp;|&nbsp;</span>
          <span class="text" @click="handleclick('material')">包装材料</span><span>&nbsp;|&nbsp;</span>
          <span class="text" @click="handleclick('news')">速递商城</span><span>&nbsp;|&nbsp;</span>
          <span class="text" @click="handleclick('mall')">常见问题</span><span>&nbsp;|&nbsp;</span>
          <span class="text" @click="handleclick('user')">个人中心</span>
        </div>
        <div class="copyrightWrap">
          <span>COPYRIGHT © 2018 Ors Int Ltd. ALL RIGHTS RESERVED</span>
        </div>
        <div class="telWrap">
          <span>服务热线:</span>
        </div>
      </div>
      <div class="codeWrap">
        <div class="imgWrap">
          <img src="/static/images/shenzhoulogo.png" alt="">
        </div>
        <span>扫一扫加关注</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FooterBar',
  methods: {
    handleclick(arg) {
      window.location.hash = `#/website/${arg}`
    }
  }
}
</script>

<style scoped>
.container {
  position: relative;
  width: 100%;
  background-color: #636e72;
  display: flex;
  justify-content: center;
}
.footerWrap {
  width: 75%;
  height: 15vh;
  display: flex;
  justify-content: center;
}
.textWrap {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.textWrap div {
  margin: 1.5% 0;
}
.text {
  cursor: pointer;
}
.text:hover {
  color: bisque;
}
span {
  color: #fff;
  font-size: 14px;
}
.codeWrap {
  height: 75%;
  margin-left: 20%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
}
.codeWrap .imgWrap {
  height: 80%;
}
</style>
