<template>
  <div class="mainWrapper">
    <div class="info-wrapper">
      <div>
        <span>用户名: </span>
        <el-input
          v-model="obj.username"
          class="transparent_el-input"
          disabled
        />
      </div>
      <div>
        <span>真实姓名: </span>
        <el-input
          v-model="obj.real_name"
          class="transparent_el-input"
          disabled
        />
      </div>
      <div>
        <span>证件号码: </span>
        <el-input
          v-model="obj.idnumber"
          class="transparent_el-input"
          disabled
        />
      </div>
      <el-popconfirm title="确认是否修改用户状态?" @onConfirm="handleConfirm">
        <el-tag
          slot="reference"
          :type="obj.confirm_idnumber == 1 ? 'success' : 'danger'"
        >
          {{ obj.confirm_idnumber == 1 ? '信息已确认' : '信息未确认' }}
        </el-tag>
      </el-popconfirm>
    </div>
    <div class="img-wrapper">
      <div>
        <span>证件图片(正面)</span>
        <el-image
          :src="cid.cid_front || ''"
          :preview-src-list="[...cid.cid_front]"
        >
          <div slot="error" class="image-slot">用户未上传证件图片</div>
        </el-image>
      </div>
      <div>
        <span>证件图片(反面)</span>
        <el-image
          :src="cid.cid_reverse || ''"
          :preview-src-list="[...cid.cid_reverse]"
        >
          <div slot="error" class="image-slot">用户未上传证件图片</div>
        </el-image>
      </div>
    </div>
  </div>
</template>

<script>
import { get, confirm } from '@/api/cid'
import { stringToArray } from '@/utils/index'

export default {
  props: {
    confirmDialogData: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      obj: this.confirmDialogData,
      cid: {}
    }
  },
  watch: {
    confirmDialogData(newValue, oldValue) {
      if (newValue !== oldValue) {
        this.obj = newValue
        this.getData()
      }
    }
  },
  created() {
    this.getData()
  },
  methods: {
    handleConfirm() {
      confirm(this.obj.id).then((res) => {
        if (res.code === 20000) {
          this.$emit('confirm', 'success')
        }
      })
    },
    getData() {
      get(this.obj.id).then((res) => {
        if (res.code === 20000) {
          this.cid = res.data ? res.data : {}
          this.cid.cid_front = stringToArray(this.cid?.cid_front)
          this.cid.cid_reverse = stringToArray(this.cid?.cid_reverse)
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.mainWrapper {
  padding: 2rem;
  .info-wrapper {
    position: relative;
    display: flex;
    flex-direction: column;
    div {
      display: flex;
      align-items: center;
      & > span {
        text-align: right;
        display: block;
        width: 80px;
      }
    }
    .el-input {
      width: 360px;
    }
    .el-tag {
      user-select: none;
      cursor: pointer;
      position: absolute;
      width: 100px;
      text-align: center;
      top: 50%;
      right: 20rem;
      transform: translateY(-50%);
    }
  }
  .img-wrapper {
    width: 100%;
    margin: 2rem;
    display: flex;
    justify-content: space-around;
    align-items: center;
    div {
      display: flex;
      flex-direction: column;
      align-items: center;
      span {
        margin: 1rem;
      }
    }
    .el-image {
      width: 360px;
      min-height: 120px;
      background-color: #f5f7fa;
      ::v-deep .image-slot {
        color: #c0c4cc;
        line-height: 120px;
      }
    }
  }
}
</style>
