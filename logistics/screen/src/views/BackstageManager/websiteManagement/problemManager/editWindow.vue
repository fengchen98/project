<template>
  <div class="container">
    <div class="mdWrapper">
      <el-form
        ref="form"
        :inline="true"
        label-width="80px"
        :model="current"
        :rules="rules"
        class="form"
      >
        <el-form-item label="标题" prop="title">
          <el-input v-model="current.title" class="transparent_el-input" />
        </el-form-item>
        <el-form-item label="标签" prop="type">
          <el-select v-model="current.type" placeholder="请选择问题分类">
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
        class="markdown"
        @imgAdd="uploadImages"
        @change="flushContent"
      />
      <div class="btnWrapper">
        <el-button
          class="saveBtn"
          type="primary"
          @click="edit"
        >确认修改</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import { update, uploadImage } from '@/api/problem'
import request from '@/utils/request'
import { Message } from 'element-ui'

export default {
  components: {
    mavonEditor
  },
  props: {
    content: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      current: this.content,
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
  watch: {
    content(newValue, oldValue) {
      this.current = newValue
      this.$refs.markdown.d_value = this.current.content
      this.$refs.markdown.d_render = this.current.html
    }
  },
  mounted() {
    this.$refs.markdown.d_value = this.current.content
    this.$refs.markdown.d_render = this.current.html
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
        }
      })
    },
    edit() {
      this.$refs.form.validate((isOk) => {
        if (isOk) {
          update(this.current).then((res) => {
            if (res.code === 20000) {
              this.$emit('edit', 'success')
            } else {
              Message.error('添加失败')
            }
          })
        }
      })
    },
    flushContent(value, render) {
      this.current.content = value
      this.current.html = render
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
  .form {
    width: 500px;
    margin-left: 2rem;
    .el-input,
    .el-select {
      width: 300px;
    }
  }
  .markdown-body {
    margin: 0 auto;
    width: 90%;
    z-index: 10;
    // height: 600px;
  }
  div {
    display: flex;
    align-items: center;
    p {
      margin: 2rem 0;
      width: 40px;
    }
  }
  .btnWrapper {
    width: 100%;
    padding: 2rem 5rem;
    display: flex;
    justify-content: flex-end;
  }
}
</style>
