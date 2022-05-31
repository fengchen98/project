<template>
  <div class="container">
    <div class="infoBox">
      <div class="avatarBox">
        <el-upload
          ref="upload"
          class="upload-demo"
          action="fake"
          :auto-upload="false"
          :http-request="submitUpload"
          :limit="1"
          :file-list="fileList"
          :show-file-list="false"
          :on-change="handleFileListChange"
          :multiple="false"
          :before-upload="beforeAvatarUpload"
          :on-success="uploadSuccess"
          :on-error="uploadError"
        >
          <img :src="userinfo.avatar || defaultAvatar" alt="avatar">
          <div slot="tip" class="el-upload__tip">
            只能上传jpg/png文件, 且不超过500kb
          </div>
        </el-upload>
      </div>
      <!-- <el-button @click="submitUpload">上传头像</el-button> -->
      <div class="toSafe"><p @click="toSafe">账号安全中心</p></div>
      <div class="basicInfo">
        <div>
          <span>国家</span><el-input v-model="userinfo.country" disabled />
        </div>
        <div>
          <span>账号</span><el-input v-model="userinfo.username" disabled />
        </div>
        <div><span>昵称</span><el-input v-model="userinfo.nickname" /></div>
        <div>
          <span>个性签名</span><el-input v-model="userinfo.signature" />
        </div>
        <div><span>电话</span><el-input v-model="userinfo.phone" /></div>
        <div>
          <span>电子邮件</span><el-input v-model="userinfo.email" disabled />
        </div>
        <div><span>邮编</span><el-input v-model="userinfo.postcode" /></div>
        <div><span>地区</span><el-input v-model="userinfo.area" /></div>
        <div><span>QQ</span><el-input v-model="userinfo.qq" /></div>
        <div><span>微信</span><el-input v-model="userinfo.wechat" /></div>
        <!-- <div>
          <span>密码</span><el-input v-model="userinfo.password" type="password" />
        </div>
        <div>
          <span>确认密码</span><el-input
            v-model="userinfo.repeatpassword"
            type="password"
            @blur="avalidatePasswd"
          />
        </div> -->
        <div class="warningBox">
          <span v-show="warning.show" style="color: red" v-text="warning.msg" />
        </div>
        <div>
          <span>订阅邮件</span><el-switch
            v-model="userinfo.subscribe"
            active-color="#13ce66"
            inactive-color="#ff4949"
          />
        </div>
        <div>
          <button class="modifyBtn" @click="updateEdit">确认修改</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Message } from 'element-ui'
import { mapGetters } from 'vuex'
import { update } from '@/api/user'
import { uploadImageDefault } from '@/api/uploadImage'

export default {
  data() {
    return {
      defaultAvatar:
        'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fp3.itc.cn%2Fimages03%2F20200516%2F0346a117a87b453fbd6d7b1d6698923d.jpeg&refer=http%3A%2F%2Fp3.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1652066159&t=0f0450558b1e6afe784fbb8463a9722d',
      fileList: [],
      warning: {
        show: false,
        msg: '两次输入密码不一致'
      },
      userinfo: {
        id: '',
        avatar: '',
        country: '',
        name: '',
        phone: '',
        email: '',
        postcode: '',
        area: '',
        qq: '',
        wechat: '',
        subscribe: ''
      }
    }
  },
  computed: {
    ...mapGetters(['authinfo', 'baseinfo']),
    uploadImageURL() {
      return 'http://192.168.1.174:8888/webapp/upload/upload/avatar'
    }
  },
  created() {
    this.getData()
  },
  methods: {
    toSafe() {
      this.$router.push('/safe')
    },
    updateEdit() {
      update(this.userinfo)
        .then((res) => {
          if (res.code === 20000) {
            Message.success('修改成功')
            this.$store.dispatch('admin/getInfo').then(() => {
              this.getData()
            })
          }
        })
        .catch((err) => {
          Message.error(err)
        })
    },
    getData() {
      this.userinfo = JSON.parse(JSON.stringify(this.baseinfo))
      this.userinfo.subscribe = this.userinfo.subscribe === 'true'
      this.userinfo['id'] = this.authinfo.id
      // console.log(this.userinfo)
      // {
      //   id: this.authinfo.id,
      //   avatar: this.baseinfo.avatar,
      //   country: this.baseinfo.country,
      //   name: this.baseinfo.name,
      //   nickname: this.baseinfo.nickname,
      //   signature: this.baseinfo.signature,
      //   phone: this.baseinfo.phone,
      //   email: this.baseinfo.email,
      //   postcode: this.baseinfo.postcode,
      //   area: this.baseinfo.area,
      //   qq: this.baseinfo.qq,
      //   wechat: this.baseinfo.wechat,
      //   subscribe: this.baseinfo.subscribe === 'true'
      // }
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        Message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        Message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    handleFileListChange(file, fileList) {
      // console.log('触发一次头像的更新')
      this.fileList = fileList
      this.submitUpload()
    },
    submitUpload() {
      const formData = new FormData()
      // console.log(this.fileList)
      formData.append('file', this.fileList[0].raw)
      uploadImageDefault(formData, 'avatar')
        .then((res) => {
          this.$refs.upload.clearFiles()
          if (res.code === 20000) {
            Message.success('上传成功')
            this.$store.dispatch('admin/getInfo').then(() => {
              this.getData()
            })
          }
        })
        .catch(() => {
          this.$refs.upload.clearFiles()
        })
    },
    uploadSuccess(response, file, fileList) {
      console.log('uploadSuccess: ', response, file, fileList)
    },
    uploadError(response, file, fileList) {
      console.log('uploadError: ', response, file, fileList)
    },
    avalidatePasswd() {
      if (this.userinfo.password !== this.userinfo.repeatpassword) {
        this.warning.show = true
        return false
      } else {
        this.warning.show = false
      }
      return true
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
.infoBox {
  text-align: center;
  .toSafe {
    margin: 0 auto;
    p {
      cursor: pointer;
      font-size: 1.5rem;
      color: #3498db;
      &:hover {
        color: #2980b9;
      }
    }
  }
  .basicInfo {
    width: 50%;
    margin: 0 auto;
    div {
      display: flex;
      align-items: center;
      margin: 0.2rem;
      span {
        width: 10rem;
        text-align: center;
      }
      .el-switch {
        height: 4rem;
      }
    }
    .warningBox {
      width: 100%;
      span {
        text-align: left;
        padding-left: 8.5rem;
        width: 100%;
      }
    }
  }
}
.avatarBox {
  position: relative;
  width: 20rem;
  height: 20rem;
  margin: 2rem auto 2rem auto;
  img {
    width: 15rem;
    height: 15rem;
    object-fit: cover;
    border-radius: 50%;
  }
}
.modifyBtn {
  width: 60%;
  position: relative;
  margin: 1rem auto;
  padding: 1rem;
  color: #fff;
  border: 1px solid #27ae60;
  font-weight: 600;
  background-color: rgba(46, 204, 113, 1);
}
</style>
