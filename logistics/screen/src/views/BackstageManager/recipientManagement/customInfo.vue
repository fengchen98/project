<template>
  <div class="container">
    <div class="searchWrap">
      <el-form
        :inline="true"
        :model="formInline"
        size="small"
        class="demo-form-inline"
      >
        <el-form-item label="">
          <el-input v-model="formInline.username" placeholder="用户名" />
        </el-form-item>
        <el-form-item label="">
          <el-input v-model="formInline.real_name" placeholder="姓名" />
        </el-form-item>
        <el-form-item label="">
          <el-input v-model="formInline.phone" placeholder="电话" />
        </el-form-item>
        <el-form-item label="">
          <el-input v-model="formInline.idnumber" placeholder="身份证" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="search">查询</el-button>
          <el-button
            type="primary"
            @click="
              Object.keys(formInline).forEach((o) => (formInline[o] = ''))
              getData()
            "
          >清空</el-button>
          <el-button type="warning" style="visibility: hidden" />
          <!-- <el-button
            type="success"
            @click.native="alertSelected"
          >导出联系人</el-button> -->
        </el-form-item>
      </el-form>
    </div>
    <div class="tableWrap">
      <el-table
        ref="multipleTable"
        :data="tableData"
        stripe
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="index" label="序号" width="60" align="center" />
        <el-table-column prop="id" label="ID" width="60" align="center" />
        <el-table-column prop="username" label="用户名" align="center" />
        <el-table-column prop="nickname" label="昵称" align="center" />
        <!-- <el-table-column prop="email" label="邮箱" align="center" /> -->
        <el-table-column prop="signature" label="签名" align="center" />
        <el-table-column prop="phone" label="联系电话" align="center" />
        <el-table-column prop="email" label="电子邮箱" align="center" />
        <el-table-column prop="balance" label="余额" align="center" />
        <el-table-column label="身份证号" align="center" width="190">
          <template slot-scope="scope">
            <span v-text="scope.row.idnumber" />
            <i
              v-if="scope.row.confirm_idnumber == 0"
              class="el-icon-circle-close"
              title="证件信息未确认"
            />
            <i v-else class="el-icon-circle-check" title="证件信息已确认" />
          </template>
        </el-table-column>
        <el-table-column prop="consumption" label="消费金额" align="center" />
        <el-table-column label="创建时间" align="center" width="100">
          <template slot-scope="scope">
            {{ new Date(scope.row.create_time).toLocaleString() }}
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="80">
          <template slot-scope="scope">
            <span
              type="primary"
              class="editSpan"
              size="mini"
              @click.stop="handleConfirmInfo(scope.row)"
            >编辑</span>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="pagination">
      <el-pagination
        layout="prev, pager, next"
        :page-size="paginationConfig.pageSize"
        :total="paginationConfig.total"
        @current-change="changePage"
      />
    </div>

    <el-dialog
      title="用户身份认证"
      :visible.sync="ConfirmDialogVisible"
      width="60%"
    >
      <confirmWindow
        :confirm-dialog-data="ConfirmDialogData"
        @confirm="handleConfirmChange"
      />
    </el-dialog>
  </div>
</template>

<script>
import { getUserList, dimQuery } from '@/api/user'
import { Message } from 'element-ui'

export default {
  name: 'CustomInfo',
  components: {
    confirmWindow: () => import('./components/confirmWindow.vue')
  },
  data() {
    return {
      ConfirmDialogVisible: false,
      ConfirmDialogData: {},
      formInline: {
        username: '',
        real_name: '',
        phone: '',
        idnumber: ''
      },
      tableData: null,
      multipleSelection: [],
      paginationConfig: {
        pageSize: 20,
        total: 1000
      }
    }
  },
  created() {
    this.getData()
  },
  methods: {
    handleConfirmInfo(item) {
      this.ConfirmDialogData = item
      this.ConfirmDialogVisible = true
    },
    handleConfirmChange(arg) {
      if (arg === 'success') {
        Message.success('状态已变更')
        this.ConfirmDialogVisible = false
        this.getData()
      }
    },
    getData() {
      getUserList().then((response) => {
        if (response.code === 20000) {
          this.tableData = response.data
        }
      })
    },
    search() {
      if (Object.values(this.formInline).join('') === '') {
        this.getData()
      } else {
        dimQuery(this.formInline).then((res) => {
          if (res.code === 20000) {
            this.tableData = res.data
          }
        })
      }
    },
    toggleSelection(rows) {
      if (rows) {
        rows.forEach((row) => {
          this.$refs.multipleTable.toggleRowSelection(row)
        })
      } else {
        this.$refs.multipleTable.clearSelection()
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
      console.log(this.multipleSelection)
    },
    changePage(arg) {
      this.$message({
        type: 'success',
        message: `当前为第${arg}页`,
        duration: 1000
        // center: true
      })
    }
  }
}
</script>
<style lang="scss" scoped>
.container {
  padding: 1vw;
}
.searchWrap {
  /* padding: 1vw; */
  margin: 0.5vh 0;
}
.pagination {
  display: flex;
  margin: 1vh 0;
  justify-content: center;
}
.el-icon-circle-close {
  color: #e74c3c;
}
.el-icon-circle-check {
  color: #2ecc71;
}
.el-icon-circle-close,
.el-icon-circle-check {
  cursor: pointer;
}
.editSpan {
  color: #74b9ff;
  cursor: pointer;
}
</style>
