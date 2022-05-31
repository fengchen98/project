<template>
  <div class="selectAddress">
    <el-table
      :data="addressLists"
      stripe
      style="width: 100%"
      highlight-current-row
      row-class-name="row-class-name"
      @current-change="handleCurrentChange"
    >
      <el-table-column
        prop="name"
        label="收货人姓名"
        align="center"
        width="100"
      />
      <el-table-column prop="phone" label="手机号" align="center" width="150" />
      <el-table-column
        prop="idnumber"
        label="身份证号"
        align="center"
        width="180"
      />
      <el-table-column prop="area" label="地区" align="center" />
      <el-table-column prop="address" label="详细地址" align="center" />
      <el-table-column prop="name" label="操作" align="center" width="80">
        <template slot-scope="scope" align="center">
          <div class="coopBox">
            <el-button
              size="mini"
              @click.native.stop="handleEdit(scope.row)"
            >编辑</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="编辑收货地址"
      :visible.sync="dialogVisible"
      width="60%"
      :modal="false"
    >
      <editAddress :form="editForm" @editCallback="editCallback" />
    </el-dialog>
  </div>
</template>

<script>
import { getRecipient } from '@/api/recipient'
import { Message } from 'element-ui'

export default {
  components: {
    editAddress: () => import('./editAddress.vue')
  },
  data() {
    return {
      editForm: null,
      addressLists: [],
      selectionPacks: null,
      dialogVisible: false
    }
  },
  created() {
    this.getData()
  },
  methods: {
    tableRowClassName({ row, rowIndex }) {
      return 'row-class-name'
    },
    handleEdit(item) {
      this.editForm = item
      this.dialogVisible = true
      //   console.log(item)
    },
    getData() {
      getRecipient().then((res) => {
        if (res.code === 20000) {
          this.addressLists = res.data
        }
      })
    },
    handleCurrentChange(val) {
      this.$emit('selectAddress', val)
      //   console.log('handleCurrentChange', val)
    },
    editCallback(arg) {
      if (arg === 'success') {
        Message.success('修改成功')
        this.dialogVisible = false
        this.getData()
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.el-dialog__wrapper {
  ::v-deep .el-dialog__wrapper {
    z-index: 3000;
  }
}
.row-class-name {
  cursor: pointer;
  background-color: red;
}
</style>
