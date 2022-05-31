<template>
  <div class="mainWrapper">
    <div class="bannerWrapper">
      <img src="static/images/cid-banner.png" alt="">
      <div class="banner-msg">
        <p>个人物品邮寄清关</p>
        <p>身份证件上传验证系统</p>

        <span>根据中国海关总署修订后的《中华人民共和国海关对进出境快件监管办法》第二十二条规定,
          入境到中国大陆的个人包裹经海关查验需向海关提供收件人身份证件 (彩色)
          等相关信息。</span>
        <span>前往
          <a
            href="http://www.gov.cn/gongbao/content/2007/content_588177.htm"
            target="_blank"
          >中华人民共和国海关总署官方网站</a>
          查看原文</span>
      </div>
    </div>
    <div class="uploadWrapper">
      <div class="msg-row">
        <p>上传收件人身份证照片</p>
        <p v-if="authinfo.confirm_idnumber == '1'" style="color: #2ecc71">
          (信息已确认,请勿修改)
        </p>
        <p v-else style="color: #e74c3c">(信息未确认,等待管理员审核)</p>
      </div>
      <div class="upload-info-row">
        <el-form ref="form" label-width="80px" :model="form" :rules="formRules">
          <el-form-item label="真实姓名" prop="real_name">
            <el-input
              v-model="form.real_name"
              placeholder="真实姓名"
              :clearable="true"
            />
          </el-form-item>
          <el-form-item label="证件号码" prop="cid_number">
            <el-input
              v-model="form.cid_number"
              placeholder="证件号码"
              :clearable="true"
            />
          </el-form-item>
        </el-form>
      </div>
      <div class="upload-img-row">
        <el-upload
          class="upload-1"
          drag
          action="fake"
          :auto-upload="false"
          :on-change="handleFrontUploadChange"
          :limit="2"
          :show-file-list="false"
        >
          <img :src="cid_front_img || 'static/images/cid_front.png'" alt="">
        </el-upload>
        <el-upload
          class="upload-1"
          drag
          action="fake"
          :auto-upload="false"
          :on-change="handleReverseUploadChange"
          :limit="2"
          :show-file-list="false"
        >
          <img
            :src="cid_reverse_img || 'static/images/cid_reverse.png'"
            alt=""
          >
        </el-upload>
      </div>
    </div>
    <div class="messageWrapper">
      <span>上传须知以及注意事项：</span>
      <span>1、请使用有效期内二代身份证, 请确保所上传信息真实有效,
        上传后姓名和身份证号码会自动识别。</span>
      <span>2、您可以在身份证上添加类似“仅供海关清关使用”的文字或水印,但不能遮盖身份证上的文字和头像。</span>
      <span>3、所有上传的身份证信息将会被数据加密仅作清关使用直接提交给海关进行查验。</span>
    </div>
    <div class="buttonWrapper">
      <el-button type="info" @click="clearForm">重置</el-button>
      <el-button type="primary" @click="submit">提交</el-button>
    </div>
  </div>
</template>

<script>
import { getToken } from '@/utils/auth'
import { stringToArray } from '@/utils/index'
import { Message } from 'element-ui'
import { get, save, upload } from '@/api/cid'
import { mapGetters } from 'vuex'

export default {
  beforeRouteEnter(to, from, next) {
    next((vm) => {
      if (getToken()) {
        vm.$store.dispatch('admin/getInfo')
        next()
      } else {
        vm.$router.push('/login')
      }
    })
  },
  data() {
    const validCid = (rule, value, callback) => {
      if (value && value === '') {
        callback(new Error('证件号码不能为空'))
      }
      const RegExp = /([0-9]{17})([0-9xX]{1})/
      if (RegExp.test(value)) {
        callback()
      } else {
        callback(new Error('证件号码格式有误'))
      }
    }
    return {
      form: {
        real_name: '',
        cid_number: ''
      },
      formRules: {
        real_name: [{ required: true, message: '姓名不能为空' }],
        cid_number: [{ validator: validCid }]
      },
      cid_front_img: null,
      cid_reverse_img: null,
      cid_front_fileList: [],
      cid_reverse_fileList: []
    }
  },
  computed: {
    ...mapGetters(['authinfo'])
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      get(this.authinfo.id).then((res) => {
        if (res.code === 20000) {
          res.data.real_name ? (this.form.real_name = res.data.real_name) : ''
          res.data.cid_number
            ? (this.form.cid_number = res.data.cid_number)
            : ''
          this.cid_front_img = stringToArray(res.data.cid_front)
          this.cid_reverse_img = stringToArray(res.data.cid_reverse)
        }
      })
    },
    clearForm() {
      Object.keys(this.form).forEach((e) => (this.form[e] = ''))
    },
    handleFrontUploadChange(file, fileList) {
      fileList.length > 1 ? fileList.shift() : ''
      this.cid_front_fileList = fileList
      const formData = new FormData()
      formData.append('file', this.cid_front_fileList[0].raw)
      upload(formData, 'front').then((res) => {
        this.cid_front_img = stringToArray(res.data.cid_front)
      })
    },
    handleReverseUploadChange(file, fileList) {
      fileList.length > 1 ? fileList.shift() : ''
      this.cid_reverse_fileList = fileList
      const formData = new FormData()
      formData.append('file', this.cid_reverse_fileList[0].raw)
      upload(formData, 'reverse').then((res) => {
        this.cid_reverse_img = stringToArray(res.data.cid_reverse)
      })
    },
    submit() {
      this.$refs['form'].validate((isOk) => {
        if (isOk) {
          console.log(this.form)
          save(this.form).then((res) => {
            if (res.code === 20000) {
              Message.success('信息已提交,等待管理员审核')
            }
          })
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.mainWrapper {
  margin: 3rem 0;
  width: 60%;
  margin-inline: auto;
  // background-color: lightblue;
  .bannerWrapper {
    position: relative;
    img {
      width: 100%;
      object-fit: cover;
    }
    .banner-msg {
      width: 50%;
      padding: 2rem 6rem;
      position: absolute;
      top: 0;
      color: #fff;
      p {
        font-size: 2.4rem;
        font-weight: 600;
      }
      span {
        display: block;
        font-size: 1.5rem;
        &:nth-child(3) {
          margin-top: 4rem;
        }
        &:nth-child(4) {
          margin-top: 4rem;
          a {
            color: #f39c12;
            text-decoration: underline;
          }
        }
      }
    }
  }
  .uploadWrapper {
    .msg-row {
      display: flex;
      p {
        font-size: 2rem;
        font-weight: bolder;
        &:nth-child(1) {
          font-size: 2rem;
          font-weight: bolder;
        }
      }
    }
    .upload-info-row {
      .el-form {
        padding-left: 3rem;
        // display: flex;
        // justify-content: space-around;
        .el-form-item {
          width: 40%;
        }
        .el-input {
          width: 360px;
        }
      }
    }
    .upload-img-row {
      display: flex;
      justify-content: space-around;
      align-items: center;
      ::v-deep .el-upload-dragger {
        border: none;
        width: 360px;
        height: auto;
        img {
          width: 100%;
          object-fit: contain;
        }
      }
    }
  }
  .messageWrapper {
    margin-top: 3rem;
    span {
      font-size: 1.2rem;
      line-height: 2rem;
      display: block;
    }
  }
  .buttonWrapper {
    text-align: center;
    margin-top: 3rem;
    .el-button {
      width: 20rem;
    }
  }
}
</style>
