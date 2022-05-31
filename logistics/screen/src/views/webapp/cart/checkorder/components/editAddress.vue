<template>
  <div class="editWrapper">
    <el-form label-width="80px" :model="form">
      <el-form-item label="姓名">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="form.phone" />
      </el-form-item>
      <el-form-item label="身份证号">
        <el-input v-model="form.idnumber" />
      </el-form-item>
      <el-form-item label="地区">
        <el-input v-model="form.area" />
      </el-form-item>
      <el-form-item label="详细地址">
        <el-input v-model="form.address" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click.native.stop="edit">修改</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { updateRecipient } from '@/api/recipient'
export default {
  props: {
    form: {
      type: Object,
      default: () => {}
    }
  },
  methods: {
    edit() {
      updateRecipient(this.form).then((res) => {
        if (res.code === 20000) {
          this.$emit('editCallback', 'success')
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.editWrapper {
  padding: 2rem;
}
</style>
