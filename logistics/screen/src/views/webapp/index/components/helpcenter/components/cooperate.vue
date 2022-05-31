<template>
  <div class="container">
    <div class="mainWrapper">
      <img src="/static/images/coo.jpg" alt="">
      <div class="form-box">
        <el-form
          ref="ruleForm"
          :model="ruleForm"
          label-width="100px"
          class="ruleForm"
        >
          <el-form-item label="" prop="name">
            <el-input v-model="ruleForm.name" placeholder="您的名字" />
          </el-form-item>
          <el-form-item label="" prop="tel">
            <el-input v-model="ruleForm.tel" placeholder="手机号码" />
          </el-form-item>
          <el-form-item label="" prop="email">
            <el-input v-model="ruleForm.email" placeholder="邮箱地址" />
          </el-form-item>
          <el-form-item label="" prop="subject">
            <el-input v-model="ruleForm.subject" placeholder="主题" />
          </el-form-item>
          <el-form-item label="" prop="content">
            <el-input
              v-model="ruleForm.content"
              :rows="6"
              type="textarea"
              placeholder="内容"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm">提交</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import { save } from '@/api/concat'
import { Message } from 'element-ui'
import { mapGetters } from 'vuex'

export default {
  data() {
    return {
      ruleForm: {
        name: '',
        type: '加盟合作',
        tel: '',
        email: '',
        subject: '',
        content: ''
      }
    }
  },
  computed: {
    ...mapGetters(['baseinfo'])
  },
  methods: {
    submitForm() {
      this.ruleForm.username = this.baseinfo?.username
      save(this.ruleForm).then((res) => {
        if (res.code === 20000) {
          Message.success('信息已提交')
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  padding: 0;
  margin: 0;
}
.mainWrapper {
  position: relative;
  width: 100%;
  // height: 600px;
  padding: 2rem;
  background-color: rgba(244, 244, 244, 0.5);
}
.form-box {
  margin-top: 2rem;
  position: relative;
  height: 200px;
}
.el-form {
  // position: relative;
  height: 100%;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: space-around;
  .el-form-item {
    margin-bottom: 1rem;
    .el-input {
      width: 300px;
    }
    .el-textarea {
      width: 400px;
    }
    .el-button {
      width: 400px;
    }
  }
}
</style>
