<template>
  <div class="container">
    <p>批量上传测试页面</p>
    <el-upload
      class="upload-demo"
      drag
      multiple
      :limit="10"
      action="fake"
      list-type="picture-card"
      :auto-upload="false"
      :file-list="fileList"
      :show-file-list="true"
      :on-exceed="handleExceed"
      :http-request="submitUpload"
      :on-error="handleErrorSuccess"
      :on-change="handleFileListChange"
      :on-success="handleImagesSuccess"
      :before-upload="beforeImagesUpload"
    >
      <!-- <i class="el-icon-upload" /> -->
      <!-- <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div> -->
    </el-upload>
    <el-button type="primary" @click="submitUpload">上传图片</el-button>
  </div>
</template>

<script>
import { uploadProductImageDefault } from '@/api/uploadImage'
import { Message } from 'element-ui'

export default {
  data() {
    return {
      imageUrl: '',
      fileList: []
    }
  },
  methods: {
    handleFileListChange(file, fileList) {
      this.fileList = fileList
      // console.log(this.fileList)
    },
    submitUpload() {
      if (this.fileList.length > 0) {
        const formData = new FormData()
        this.fileList.forEach((o) => {
          formData.append('files', o.raw)
        })
        // console.log(formData)
        formData.append('files', formData)
        uploadProductImageDefault(
          formData,
          'product',
          'default',
          'images'
        ).then((res) => {
          console.log('批量上传图片回调:', res.data)
        })
      } else {
        Message.error('No resources found')
      }
    },
    handleImagesSuccess() {
      Message.success('图片上传成功')
    },
    handleErrorSuccess() {
      Message.error('图片上传失败')
    },
    handleExceed() {
      Message.warning('最大上传数量不能超过10张图片')
    },
    beforeImagesUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isJPG) {
        Message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        Message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  width: 100%;
  padding: 2rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  .upload-demo {
    width: 50%;
    display: flex;
    flex-wrap: wrap;
  }
  .el-upload {
  }
  p {
    font-size: 2rem;
    font-weight: 600;
  }
  .el-button {
    margin-top: 5rem;
  }
  .el-upload-list__item-thumbnail {
    img {
      width: 75px !important;
      height: 70px !important;
      object-fit: contain;
    }
  }
}
</style>
