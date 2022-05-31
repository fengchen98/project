<template>
  <div class="inputBox">
    <p>找回密码</p>
    <el-input v-model="input.username" placeholder="请输入用户名" />
    <el-input v-model="input.email" placeholder="请输入注册邮箱" />
    <div class="verifyBox">
      <el-input v-model="input.code" placeholder="验证码" />
      <button
        :disabled="emailBtnInfo.disabled"
        @click="sendEmail"
        v-text="emailBtnInfo.msg"
      >
        发送验证码
      </button>
    </div>
    <button @click="validateCode">下一步</button>
  </div>
</template>

<script>
import { Message } from 'element-ui'
import { sendEmail, validateEmailCode } from '@/api/findPassword'
import { validInput, validEmail } from '@/utils/validate'

export default {
  beforeRouteEnter(to, from, next) {
    next((vm) => {
      if (from.fullPath === '/safe/index') {
        next()
      } else {
        vm.$router.push('/safe')
      }
    })
  },
  data() {
    return {
      emailBtnInfo: {
        msg: '发送验证码',
        disabled: false
      },
      input: {
        username: '',
        email: '',
        code: ''
      }
    }
  },
  methods: {
    validInputInfo() {
      return validInput(this.input.username) && validEmail(this.input.email)
    },
    sendEmail() {
      if (this.validInputInfo()) {
        sendEmail(this.input).then((res) => {
          console.log(res)
          if (res.code === 20000) {
            let time = 30
            this.emailBtnInfo.disabled = true
            if (timer) clearInterval(timer)
            const timer = setInterval(() => {
              if (time > 0) {
                this.emailBtnInfo.msg = `${time--}后重新发送`
              } else {
                this.emailBtnInfo = {
                  disabled: false,
                  msg: '发送验证码'
                }
                clearInterval(timer)
              }
            }, 1000)
            Message({
              type: 'success',
              message: '验证码已发送,请在邮箱中查看.'
            })
          }
        })
      } else {
        Message({
          type: 'warning',
          message: '用户名或邮箱输入不合法'
        })
      }
    },
    validateCode() {
      if (Object.values(this.input).includes('')) {
        Message({
          type: 'warning',
          message: '填写信息不能为空'
        })
      } else {
        validateEmailCode(this.input).then((res) => {
          if (res.code === 20000) {
            Message({
              type: 'success',
              message: '邮箱验证通过'
            })
            this.$emit('getCodeInfo', this.input)
            this.$router.push('/safe/changepassword')
          } else {
            Message({
              type: 'error',
              message: '验证码错误'
            })
          }
        })
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.inputBox {
  padding: 3rem;
  width: 450px;
  height: 320px;
  background-color: #fff;
  border-radius: 5px;
  // display: flex;
  // flex-direction: column;
  // align-items: center;
  box-shadow: 0 0 20px 1px #3498db50;
  transform: translateY(-150px);
  p {
    font-size: 1.5rem;
    font-weight: 600;
    padding-left: 2rem;
    border-left: 5px solid #3498db;
  }
  .el-input {
    margin: 0.5rem 0;
  }
  button {
    margin-top: 2rem;
    width: 100%;
    height: 4rem;
    border: none;
    color: #fff;
    font-size: 1.5rem;
    font-weight: 600;
    border-radius: 5px;
    background-color: #3498dbc2;
  }
  .verifyBox {
    display: flex;
    align-items: center;
    justify-content: space-between;
    .el-input {
      width: 65%;
    }
    button {
      width: 30%;
      margin: 0;
    }
  }
}
</style>
