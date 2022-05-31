<template>
  <div class="container">
    <div class="mainWrapper">
      <div class="left-box">
        <div class="left-top">
          <svg-icon
            icon-class="dianhua"
            class="concatIcon active"
            @click="handleClickIcon(0)"
          />
          <svg-icon
            icon-class="youxiang"
            class="concatIcon"
            @click="handleClickIcon(1)"
          />
        </div>
        <div class="left-bottom">
          <div v-show="show" class="contact-box">
            <p>客服电话 | &nbsp;&nbsp;01293537035</p>
            <p>工作时间 |（英国时间）周一至周五 9:00 - 17:00</p>
          </div>
          <div v-show="!show" class="contact-box">
            <p>咨询邮箱 | enquiries@send2china.co.uk</p>
            <p>工作时间 |（英国时间）周一至周五 9:00 - 17:00</p>
          </div>
        </div>
      </div>
      <div class="right-box">
        <el-form
          ref="ruleForm"
          :model="ruleForm"
          label-width="100px"
          class="ruleForm"
        >
          <el-form-item label="" prop="type">
            <el-select v-model="ruleForm.type" placeholder="类型">
              <el-option label="问题反馈" value="suggest" />
              <el-option label="事件投诉" value="complaint" />
            </el-select>
          </el-form-item>
          <el-form-item label="" prop="phone">
            <el-input v-model="ruleForm.tel" placeholder="手机号码" />
          </el-form-item>
          <el-form-item label="" prop="email">
            <el-input v-model="ruleForm.email" placeholder="邮箱地址" />
          </el-form-item>
          <el-form-item label="" prop="topic">
            <el-input v-model="ruleForm.subject" placeholder="主题" />
          </el-form-item>
          <el-form-item label="" prop="content">
            <el-input
              v-model="ruleForm.content"
              type="textarea"
              :rows="4"
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
      show: true,
      ruleForm: {
        type: '',
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
    handleClickIcon(index) {
      const es = [...document.querySelectorAll('.concatIcon')]
      es.forEach((e) => {
        e.classList.remove('active')
      })
      es[index].classList.add('active')
      this.show = !this.show
    },
    submitForm() {
      this.ruleForm.username = this.baseinfo?.username
      save(this.ruleForm).then((res) => {
        if (res.code === 20000) {
          Message.success('信息已提交')
        }
      })
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
  display: flex;
  background-color: rgba(244, 244, 244, 0.5);
  .left-box {
    flex: 2;
    // background-color: red;
    .left-top {
      padding: 2rem;
      display: flex;
      justify-content: space-around;
      .svg-icon {
        width: 8rem;
        height: 8rem;
        cursor: pointer;
        color: rgba(0, 0, 0, 0.1);
        &:hover {
          color: skyblue;
        }
        &.active {
          color: skyblue;
        }
        &:active {
          transform: scale(1.1);
        }
      }
    }
    .left-bottom {
      position: relative;
      margin: 0 auto;
      width: 100%;
      padding: 2rem;
      background-color: rgba(0, 0, 0, 0.1);
      p {
        font-size: 1.5rem;
        font-weight: 600;
      }
    }
  }
  .right-box {
    flex: 3;
    .el-form {
      display: flex;
      flex-wrap: wrap;
      .el-form-item {
        flex: 50%;
        .el-input {
          width: 20rem;
        }
        .el-textarea {
          width: 55rem;
        }
        .el-button {
          width: 55rem;
        }
      }
    }
  }
}
</style>
