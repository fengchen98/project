<template>
  <div class="inputBox">
    <p>找回密码</p>
    <el-input
      id="pwdipt"
      v-model="input.password"
      placeholder="请输入新密码"
      type="password"
    />
    <el-input
      id="repwdipt"
      v-model="input.repassword"
      placeholder="请再次输入新密码"
      type="password"
      @blur="validatePassword"
    />
    <span v-if="!iptSame" class="validateText" v-text="'输入密码不一致'" />
    <button @click="submit">修改</button>
  </div>
</template>

<script>
import { Message } from 'element-ui'
import { changePasswordByEmail } from '@/api/user'
import { getToken } from '@/utils/auth'
import { validInput } from '@/utils/validate'

export default {
  props: {
    userinfo: {
      type: Object,
      default: () => {}
    }
  },
  beforeRouteEnter(to, from, next) {
    next((vm) => {
      if (from.fullPath === '/safe/findpassword') {
        next()
      } else {
        vm.$router.push('/safe')
      }
    })
  },
  data() {
    return {
      input: {
        password: '',
        repassword: ''
      },
      iptSame: true
    }
  },
  methods: {
    validatePassword() {
      return (
        validInput(this.input.password) &&
        this.input.password === this.input.repassword
      )
    },
    submit() {
      if (this.validatePassword()) {
        const requestData = {
          userName: this.userinfo.username,
          email: this.userinfo.email,
          code: this.userinfo.code,
          password: this.input.password
        }
        changePasswordByEmail(requestData).then((res) => {
          if (res.code === 20000) {
            Message({
              type: 'success',
              message: '密码修改成功'
            })
            this.$router.push('/login')
            if (getToken()) {
              this.$store.dispatch('admin/logout').then(() => {
                this.$router.push('/login')
              })
            }
          } else {
            Message({
              type: 'error',
              message: res.msg
            })
          }
        })
      } else {
        Message({
          type: 'error',
          message: '密码输入有误'
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
  height: 280px;
  background-color: #fff;
  border-radius: 5px;
  //   display: flex;
  //   flex-direction: column;
  //   align-items: center;
  box-shadow: 0 0 20px 1px #3498db50;
  transform: translateY(-150px);
  font-size: 1.5rem;
}
.validateText {
  font-size: 0.8rem;
  color: #e74c3c;
}
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
</style>
