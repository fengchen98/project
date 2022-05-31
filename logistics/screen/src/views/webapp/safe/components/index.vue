<template>
  <div class="container">
    <div class="box" @click="findPassword">
      <div class="iconBox">
        <i class="el-icon-lock" />
      </div>
      <p>找回密码</p>
      <span>点击这里修改账户密码</span>
    </div>
    <div class="box" @click="findPayPassword">
      <div class="iconBox">
        <i class="el-icon-money" />
      </div>
      <p>修改支付密码</p>
      <span>点击这里修改支付密码</span>
    </div>
  </div>
</template>

<script>
import { Message } from 'element-ui'
export default {
  data() {
    return {
      userinfo: {
        username: '',
        email: '',
        code: ''
      }
    }
  },
  methods: {
    findPassword() {
      this.$router.push('/safe/findpassword')
    },
    findPayPassword() {
      Message.warning('正在接入')
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  width: 100%;
  display: flex;
  justify-content: center;
}
.box {
  margin: 0 2rem;
  cursor: pointer;
  padding: 3rem;
  width: 240px;
  height: 240px;
  background-color: #fff;
  border-radius: 10px;
  transition: all 0.2s;
  transform: translateY(-60px);
  display: flex;
  flex-direction: column;
  align-items: center;
  box-shadow: 0 0 20px 1px #3498db50;
  &:hover {
    transform: translateY(-80px);
    border-bottom: 12px solid #3498db;
  }
  .iconBox {
    width: 5rem;
    height: 5rem;
    transform: rotate(45deg);
    background-color: #3498db;
    display: flex;
    border-radius: 5rem;
    justify-content: center;
    align-items: center;
    i {
      color: #fff;
      transform: rotate(-45deg);
      font-size: 3rem;
    }
  }
  p {
    margin-top: 5rem;
    font-size: 1.8rem;
    font-weight: 600;
  }
  span {
    font-weight: 0.8rem;
    color: #7f8c8d;
  }
}
</style>
