<template>
  <div class="container">
    <div class="mdWrapper">
      <el-form :inline="true" label-width="80px" :model="info" :rules="rules">
        <el-form-item label="标题" prop="title">
          <el-input v-model="info.title" class="transparent_el-input" />
        </el-form-item>
        <el-form-item label="标签" prop="type">
          <el-select v-model="info.type" placeholder="请选择问题分类">
            <el-option
              v-for="(item, index) in options"
              :key="index"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <mavonEditor
        ref="markdown"
        @imgAdd="uploadImages"
        @change="flushContent"
      />
      <el-button @click="save">上传</el-button>
    </div>
    <div v-html="info.html" />
  </div>
</template>

<script>
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import { save, uploadImage } from '@/api/problem'
import request from '@/utils/request'

export default {
  components: {
    mavonEditor
  },
  data() {
    return {
      rules: {
        title: [{ required: true, message: '请输入活动名称', trigger: 'blur' }],
        type: [{ required: true, message: '请输入活动名称', trigger: 'change' }]
      },
      defaultValue: '1',
      options: [
        {
          value: '下单说明',
          label: '下单说明'
        },
        {
          value: '取件投递',
          label: '取件投递'
        },
        {
          value: '清关和税金',
          label: '清关和税金'
        },
        {
          value: '支付相关',
          label: '支付相关'
        },
        {
          value: '售后问题',
          label: '售后问题'
        },
        {
          value: '行李寄存',
          label: '行李寄存'
        }
      ],
      info: {
        title: '',
        type: '',
        content: '',
        html: ''
      }
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
        }
      })
    },
    save() {
      save(this.info).then((res) => {
        console.log(res.data)
      })
    },
    flushContent(value, render) {
      this.info.content = value
      this.info.html = render
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
  width: 80%;
  margin: 0 auto;
  margin-top: 5rem;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  div {
    display: flex;
    align-items: center;
    p {
      margin: 2rem 0;
      width: 40px;
    }
  }
}
</style>
