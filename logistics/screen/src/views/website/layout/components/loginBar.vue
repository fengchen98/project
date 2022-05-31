<template>
  <div class="container">
    <div class="loginWrap">
      <span class="text">登录</span>
      <span class="text">&nbsp; / &nbsp;</span>
      <span class="text registertext">注册</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LoginBar'
}
</script>

<style scoped>
.container {
  width: 100%;
  min-width: 1200px;
}
.loginWrap {
  height: 3vh;
  background-color: #636e72;
  display: flex;
  color: #fff;
  justify-content: flex-end;
  align-items: center;
}
.text {
  cursor: pointer;
  font-size: 0.5rem;
}
.text:hover {
  color: #fab1a0;
}
.registertext {
  margin-right: 26.5%;
}
</style>
