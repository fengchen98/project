<template>
  <div class="container">
    <div class="main">
      <div class="main-title">神州物流</div>
      <div class="box">
        <div class="login-box">
          <p>登录</p>
          <input
            id="account"
            ref="username"
            v-model="loginForm.username"
            placeholder="用户名/邮箱"
            @blur="validLoginInput"
          >
          <input
            id="passwd"
            ref="password"
            v-model="loginForm.password"
            :type="passwordType"
            name="password"
            placeholder="密码"
            @blur="validLoginInput"
          >

          <span class="show-pwd" @click="showPwd">
            <svg-icon
              :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'"
            />
          </span>
          <div class="remember-box">
            <el-checkbox v-model="rememberPasswd">记住密码</el-checkbox>
            <a @click.prevent="handleForgetPwd">忘记密码</a>
          </div>
          <el-button
            type="primary"
            @click.native.prevent="handleLogin"
          >登录</el-button>
        </div>
        <div class="register-box">
          <p>注册</p>
          <input v-model="registerForm.userName" placeholder="用户名">
          <input v-model="registerForm.email" placeholder="邮箱">
          <div class="verify-box">
            <input v-model="registerForm.emailCode" placeholder="验证邮箱">
            <button
              class="emailCodeBtn"
              type="info"
              :disabled="emailBtnInfo.disabled"
              @click="sendEmail"
              v-text="emailBtnInfo.msg"
            />
          </div>
          <input v-model="registerForm.password" placeholder="密码">
          <input v-model="registerForm.repassword" placeholder="确认密码">
          <!-- <div class="verify-box">
            <input
              v-model="registerForm.code"
              class="verify-code"
              placeholder="验证码"
            >
            <el-button class="get-verify-code">获取验证码</el-button>
            <img class="getVerify" :src="verifySrc" @click="flushCode">
          </div> -->
          <el-button
            type="primary"
            @click.native.prevent="handleRegister"
          >注册</el-button>
        </div>
        <div class="mask-box open">
          <div class="content">
            <p>欢迎登录</p>
            <span>如果您还无法登陆，为保证您的用户体验，请点击下方按钮注册账号。</span>
          </div>
          <el-button
            id="maskBtn"
            type="info"
            @click="moveMask"
            v-text="maskStatus"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { validInput, validEmail } from '@/utils/validate'
import { Message } from 'element-ui'
import request from '@/utils/request'
import NProgress from 'nprogress' // progress bar

export default {
  name: 'Login',
  data() {
    // const validateUsername = (rule, value, callback) => {
    //   if (!validUsername(value)) {
    //     callback(new Error('Please enter the correct user name'))
    //   } else {
    //     callback()
    //   }
    // }
    // const validatePassword = (rule, value, callback) => {
    //   if (value.length < 6) {
    //     callback(new Error('The password can not be less than 6 digits'))
    //   } else {
    //     callback()
    //   }
    // }
    return {
      emailBtnInfo: {
        disabled: false,
        msg: '发送验证码'
      },
      maskStatus: '注册',
      rememberPasswd: false,
      loginForm: {
        username: '',
        password: ''
      },
      registerForm: {
        userName: '',
        email: '',
        emailCode: '',
        password: '',
        repassword: ''
      },
      // loginRules: {
      //   username: [
      //     { required: true, trigger: 'blur', validator: validateUsername }
      //   ],
      //   password: [
      //     { required: true, trigger: 'blur', validator: validatePassword }
      //   ]
      // },
      loading: false,
      passwordType: 'password',
      redirect: undefined,
      codeSeed: 0
    }
  },
  computed: {
    verifySrc() {
      return `${request.defaults.baseURL}/user/verify?timestamp=${this.codeSeed}`
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  created() {
    this.rememberAccount()
  },
  methods: {
    validLoginInput() {
      return (
        validInput(this.loginForm.username) &&
        validInput(this.loginForm.password)
      )
    },
    validRegisterInput() {
      return (
        validInput(this.registerForm.userName) &&
        validEmail(this.registerForm.email) &&
        validInput(this.registerForm.password) &&
        this.registerForm.password === this.registerForm.repassword
      )
    },
    sendEmail() {
      if (validEmail(this.registerForm.email)) {
        request
          .get(`/user/sendRegisterEmailCode?email=${this.registerForm.email}`)
          .then((res) => {
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
          message: '输入邮箱不合法'
        })
      }
    },
    rememberAccount() {
      const hasLoginForm = window.localStorage.getItem(
        'shenzhouRememberAccount'
      )
      if (hasLoginForm) {
        this.loginForm = JSON.parse(hasLoginForm)
      }
    },
    flushCode() {
      this.codeSeed = new Date().getTime()
    },
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleRegister() {
      if (this.validRegisterInput()) {
        this.loading = true
        this.$store
          .dispatch('admin/register', this.registerForm)
          .then((res) => {
            if (res.code === 20000) {
              Message({
                type: 'success',
                message: '注册成功'
              })
              // this.$router.push('/login')
              this.loginForm = {
                username: this.registerForm.userName,
                password: this.registerForm.password
              }
              Object.keys(this.registerForm).map(
                (key) => (this.registerForm[key] = '')
              )
              document.getElementById('maskBtn').click()
            }
          })
          .catch(() => {
            this.loading = false
          })
      }
    },
    handleLogin() {
      if (this.validLoginInput && this.loginForm) {
        this.loading = true
        NProgress.start()
        this.$store
          .dispatch('admin/login', this.loginForm)
          .then((res) => {
            // console.log(res)
            if (res.code === 20000) {
              if (this.rememberPasswd) {
                window.localStorage.setItem(
                  `shenzhouRememberAccount`,
                  JSON.stringify(this.loginForm)
                )
              }
              Message({
                type: 'success',
                message: '登录成功'
              })
              NProgress.done()
              this.$router.push('/webapp/index')
              // window.location.hash = '/webapp/index'
              // this.$router.push({ path: this.redirect || '/' })
              this.loading = false
            }
          })
          .catch(() => {
            this.loading = false
          })
      } else {
        Message.error('输入信息有误')
        console.log('error submit!!')
        return false
      }
    },
    moveMask() {
      const mask = document.querySelector('.mask-box')
      const loginbox = document.querySelector('.login-box')
      const registerbox = document.querySelector('.register-box')
      this.maskStatus = this.maskStatus === '注册' ? '登录' : '注册'
      if ([...mask.classList].includes('open')) {
        mask.classList.remove('open')
        loginbox.classList.add('quit')
        loginbox.classList.remove('enter')
        registerbox.classList.add('enter')
        registerbox.classList.remove('quit')
      } else {
        mask.classList.add('open')
        loginbox.classList.add('enter')
        loginbox.classList.remove('quit')
        registerbox.classList.add('quit')
        registerbox.classList.remove('enter')
      }
    },
    handleForgetPwd() {
      this.$router.push('/safe')
    }
  }
}
</script>

<style lang="scss" scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
.container {
  position: relative;
  width: 100%;
  height: 100vh;
  background: url('https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fuserimages15.51sole.com%2F20170527%2Fb_3979874_201705271142028839.jpg&refer=http%3A%2F%2Fuserimages15.51sole.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1641178273&t=5d63fc2b7a0ec9ea9782d2f97c8c4017')
    no-repeat;
  // background: url('/static/images/pub.jpg')
  //   no-repeat;
}
.main {
  position: absolute;
  left: 50%;
  top: 20%;
  height: 45%;
  width: 768px;
  max-width: 100%;
  min-height: 480px;
  // background-color: skyblue;
  transform: translateX(-50%);
  border-radius: 10px;
  // overflow: hidden;
  .main-title {
    padding: 1rem;
    transform: translateY(-50%);
    text-align: center;
    color: #fff;
    font-size: 3rem;
    font-weight: 700;
  }
  p {
    position: relative;
    display: block;
    text-align: center;
    font-size: 2rem;
    font-weight: 600;
    margin-bottom: 2rem;
  }
}
.box {
  position: relative;
  width: 100%;
  height: 100%;
  background-color: rgba(244, 244, 244, 0.9);
  display: flex;
  box-shadow: 0 14px 28px rgb(0 0 0 / 25%), 0 10px 10px rgb(0 0 0 / 22%);
  border-radius: 10px;
  p {
    text-align: center;
    font-size: 2rem;
    font-weight: 800;
  }
}
.el-button {
  position: relative;
  display: block;
  margin: 0 auto;
  width: 30%;
  height: 4rem;
  border-radius: 25px;
}

input {
  padding: 1rem 0;
  display: block;
  width: 70%;
  margin: 0 auto;
  background: transparent;
  margin-bottom: 2rem;
  border: none;
  border-bottom: 1px solid #bdc3c7;
}
.login-box {
  position: relative;
  width: 50%;
  height: 100%;
  // background-color: red;
  display: flex;
  flex-direction: column;
  justify-content: center;
  &.enter {
    animation: loginboxEnter 0.5s;
  }
  &.quit {
    animation: loginboxQuit 0.5s;
  }
  // backgroundloginbox .5s;

  p {
    margin-bottom: 3rem;
  }
  .show-pwd {
    position: absolute;
    right: 16%;
    top: 49%;
    font-size: 1.5rem;
    cursor: pointer;
    user-select: none;
  }
  .remember-box {
    display: flex;
    justify-content: space-around;
    align-items: center;
    padding: 10px;
    margin-bottom: 1rem;
  }
}
.register-box {
  position: relative;
  width: 50%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  &.enter {
    animation: registerboxEnter 0.5s;
  }
  &.quit {
    animation: registerboxQuit 0.5s;
  }
  // background-color: orange;
  p {
    margin-bottom: 3rem;
  }
  .verify-box {
    width: 70%;
    margin: 0 auto;
    display: flex;
    .el-button {
      margin-left: 1rem;
      width: 50%;
      border-radius: 5px;
      background-color: rgba(241, 242, 246, 1);
    }
  }
  .emailCodeBtn {
    cursor: pointer;
    margin-left: 1rem;
    width: 110px;
    height: 35px;
    color: #000;
  }
  .getVerify {
    cursor: pointer;
    margin-left: 1rem;
    width: 100px;
    height: 35px;
    border-radius: 5px;
    background-color: rgba(241, 242, 246, 1);
  }
}
.mask-box {
  position: absolute;
  width: 50%;
  height: 100%;
  // background-color: black;
  border-radius: 10px 0 0 10px;
  z-index: 2;
  left: 0;
  background-image: linear-gradient(to bottom right, #2980b9, #53aae4);
  transition: left 0.5s ease-in-out, border-radius 0.5s ease-in-out;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: #fff;
  &.open {
    left: 50%;
    border-radius: 0 10px 10px 0;
  }
  .el-button {
    border: 1px solid #fff;
    background-color: transparent;
    padding: 10px 20px;
    // top: 68%;
    &:active {
      transform: scale(0.9);
    }
  }
  .content {
    width: 75%;
    text-align: center;
    p,
    span {
      display: block;
      margin-bottom: 4rem;
    }
  }
}

@keyframes loginboxEnter {
  0% {
    border-radius: 50%;
    transform: translateX(30%) scale(0);
    opacity: 0;
  }
  100% {
    border-radius: 10px;
    transform: translateX(0%) scale(1);
    opacity: 1;
  }
}
@keyframes loginboxQuit {
  100% {
    border-radius: 50%;
    transform: translateX(30%) scale(0);
    opacity: 0;
  }
}

@keyframes registerboxEnter {
  0% {
    border-radius: 50%;
    transform: translateX(-30%) scale(0);
    opacity: 0;
  }
  100% {
    border-radius: 10px;
    transform: translateX(0%) scale(1);
    opacity: 1;
  }
}
@keyframes registerboxQuit {
  100% {
    border-radius: 50%;
    transform: translateX(-30%) scale(0);
    opacity: 0;
  }
}
</style>
