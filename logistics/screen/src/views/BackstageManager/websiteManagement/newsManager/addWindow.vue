<template>
  <div class="container">
    <div class="mdWrapper">
      <div class="topLayout">
        <el-upload
          ref="upload"
          class="upload-demo"
          drag
          action="fake"
          :limit="1"
          :auto-upload="false"
          :file-list="fileList"
          :on-change="handleChange"
          :show-file-list="false"
          list-type="picture"
          multiple
        >
          <!-- <p>新闻封面</p> -->
          <img v-if="fileList[0]" :src="fileList[0].url" alt="">
          <div v-else class="upload_icon">
            <i class="el-icon-upload" />
            <div class="el-upload__text">
              <span>上传新闻封面图片,<em>点击或拖动到此处上传</em></span>
            </div>
          </div>
        </el-upload>
        <el-form
          ref="form"
          :inline="true"
          label-width="50px"
          :model="info"
          :rules="rules"
          class="form"
        >
          <el-form-item label="标题" prop="title">
            <el-input v-model="info.title" class="transparent_el-input" />
          </el-form-item>
          <el-form-item label="标签" prop="type">
            <el-select
              v-model="info.type"
              placeholder="请选择问题分类"
              filterable
              allow-create
            >
              <el-option
                v-for="(item, index) in options"
                :key="index"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="简介" prop="description">
            <el-input
              v-model="info.description"
              type="textarea"
              class="transparent_el-input"
              maxlength="120"
              show-word-limit
              :rows="6"
            />
          </el-form-item>
        </el-form>
      </div>

      <mavonEditor
        ref="markdown"
        class="markdown"
        @imgAdd="uploadImages"
        @change="flushContent"
      />
      <div class="btnWrapper">
        <el-button class="saveBtn" type="primary" @click="save">上传</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import { save, uploadImage } from '@/api/news'
import request from '@/utils/request'
import { Message } from 'element-ui'

export default {
  components: {
    mavonEditor
  },
  data() {
    return {
      rules: {
        title: [{ required: true, message: '请输入新闻标题', trigger: 'blur' }],
        description: [
          { required: true, message: '请输入新闻简介', trigger: 'blur' }
        ],
        type: [{ required: true, message: '请选择新闻类别', trigger: 'change' }]
      },
      defaultValue: '1',
      options: [
        {
          value: '服务公告',
          label: '服务公告'
        },
        {
          value: '优惠活动',
          label: '优惠活动'
        },
        {
          value: '热门商品',
          label: '热门商品'
        }
      ],
      info: {
        title: '',
        type: '',
        description: '',
        image: '',
        content: '',
        html: ''
      },
      fileList: []
    }
  },
  methods: {
    uploadImages(pos, file) {
      const formData = new FormData()
      formData.append('file', file)
      uploadImage(formData).then((res) => {
        if (res.code === 20000) {
          const reg = new RegExp(`\\!\\[${file.name}\\]\\(${pos}\\)`)
          this.$refs.markdown.d_value = this.$refs.markdown.d_value.replace(
            reg,
            `![](${request.defaults.baseURL + res.data.path})`
          )
          // $vm.$img2Url(pos, request.defaults.baseURL + res.data.path)
        } else {
          Message.error('图片上传失败')
        }
      })
    },
    save() {
      this.$refs.form.validate((isOk) => {
        console.log('新闻表单校验:', isOk)
        if (isOk) {
          // 上传图片
          const formData = new FormData()
          formData.append('file', this.fileList[0].raw)
          uploadImage(formData).then((res) => {
            if (res.code === 20000) {
              this.info.image = res.data.path
              this.$refs.upload.clearFiles()
              save(this.info).then((res) => {
                if (res.code === 20000) {
                  this.$emit('add', 'success')
                } else {
                  Message.error('添加失败')
                }
              })
            }
          })
        }
      })
    },
    flushContent(value, render) {
      this.info.content = value
      this.info.html = render
    },
    handleChange(file, fileList) {
      this.fileList = fileList
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
.mdWrapper {
  width: 100%;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  .topLayout {
    width: 100%;
    display: flex;
    justify-content: space-between;
    padding: 5rem;
    padding-bottom: 0;
    ::v-deep .el-upload-dragger {
      position: relative;
      margin-left: 2.5%;
      width: 95%;
      height: 260px;
      .upload_icon {
        margin-top: 10%;
        // transform: translateY(-100%);
      }
    }
    .upload-demo {
      width: 49%;
      img {
        width: 100%;
        object-fit: contain;
      }
      // flex: 50%;
      // margin-left: 8rem;
      // display: flex;
      // justify-content: center;
      ::v-deep .el-upload {
        width: 100% !important;
      }
    }
    .form {
      // flex: 50%;
      width: 49%;
      // margin-left: 2rem;
      .el-form-item {
        width: 100%;
        ::v-deep .el-form-item__content {
          width: 85%;
        }
      }
      .el-input,
      .el-textarea,
      .el-select {
        width: 100%;
      }
      .el-upload__text {
        text-align: center;
      }
    }
  }
  .markdown-body {
    margin: 0 auto;
    width: 90%;
    z-index: 10;
    // height: 600px;
  }
  // div {
  //   display: flex;
  //   align-items: center;
  //   p {
  //     margin: 2rem 0;
  //     width: 40px;
  //   }
  // }
  .btnWrapper {
    width: 100%;
    padding: 2rem 5rem;
    display: flex;
    justify-content: flex-end;
  }
}
</style>
