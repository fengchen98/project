<template>
  <div>
    <div>
      <el-form
        ref="ruleForm"
        :model="ruleForm"
        status-icon
        :rules="rules"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="图片名称" prop="title">
          <el-input v-model="ruleForm.title" type="text" autocomplete="off" />
        </el-form-item>
        <el-form-item label="图片备注" prop="mark">
          <el-input v-model="ruleForm.mark" type="text" autocomplete="off" />
        </el-form-item>
      </el-form>
    </div>
    <div class="upload-demo">
      <el-upload
        ref="upload"
        drag
        action="fake"
        :auto-upload="false"
        :http-request="submitUpload"
        :limit="1"
        :file-list="fileList"
        :show-file-list="true"
        :on-change="handleFileListChange"
        :multiple="false"
      >
        <i class="el-icon-upload" />
        <div class="el-upload__text">将文件拖到此处,或<em>点击上传</em></div>
        <div slot="tip" class="el-upload__tip">
          只能上传jpg/png文件,且不超过500kb
        </div>
      </el-upload>
    </div>
    <div class="btnWrapper">
      <el-button @click="handleCancel">取消</el-button>
      <el-button type="primary" @click="submitUpload">上传</el-button>
    </div>
  </div>
</template>

<script>
import { upload, add } from '@/api/carousel'

export default {
  data() {
    return {
      fileList: [],
      ruleForm: {
        title: '',
        mark: ''
      },
      rules: {
        title: [{ required: true, message: '请输入邮箱地址', trigger: 'blur' }]
      }
    }
  },
  methods: {
    handleFileListChange(file, fileList) {
      this.fileList = fileList
    },
    submitUpload() {
      this.$refs.ruleForm.validate((isOk) => {
        if (isOk) {
          const formData = new FormData()
          formData.append('file', this.fileList[0].raw)
          upload('carousel', formData).then((res) => {
            if (res.code === 20000) {
            //   console.log(res)
              this.ruleForm.images = res.data.path
              add(this.ruleForm).then((res) => {
                if (res.code === 20000) {
                  this.$refs.upload.clearFiles()
                  this.$emit('addWindow', 'success')
                }
              })
            }
          })
        }
      })
    },
    handleCancel() {
      this.$emit('handleCancel', 1)
    }
  }
}
</script>

<style lang="scss" scoped>
.upload-demo {
  display: flex;
  justify-content: center;
  align-items: center;
  div {
    text-align: center;
  }
}
.btnWrapper {
  display: flex;
  justify-content: flex-end;
}
</style>
