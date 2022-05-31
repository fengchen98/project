<template>
  <div class="container">
    <div class="searchWrap">
      <el-form :inline="true" :model="formInline" size="small" class="demo-form-inline">
        <el-form-item label="">
          <el-input v-model="formInline.approver" placeholder="审批人" />
        </el-form-item>
        <el-form-item label="">
          <el-input v-model="formInline.recipient" placeholder="收件人" />
        </el-form-item>
        <el-form-item label="">
          <el-input v-model="formInline.waybillCode" placeholder="国内运单号" />
        </el-form-item>
        <el-form-item label="">
          <el-input v-model="formInline.trackCode" placeholder="国际追踪号" />
        </el-form-item>
        <el-form-item label="">
          <el-select v-model="formInline.status" placeholder="请选择">
            <el-option label="待取件" value="pedding" />
            <el-option label="已取件" value="resolve" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
          <el-button type="primary" @click="clearIpt()">清空</el-button>
          <el-button type="warning" style="visibility: hidden;" />
          <el-button type="success" @click.native="alertSelected">批量收件</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div class="tableWrap">
      <el-table ref="multipleTable" :data="tableData" stripe style="width: 100%" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="50" align="center" />
        <el-table-column type="index" label="序号" width="50" align="center" />
        <el-table-column prop="account" label="下单账号" width="120" align="center" />
        <el-table-column prop="category" label="服务类型" width="120" align="center" />
        <el-table-column prop="recipient" label="收件人" width="80" align="center" />
        <el-table-column prop="address" label="地址" align="center" />
        <el-table-column prop="tel" label="电话" align="center" />
        <el-table-column prop="idcard" label="身份证号" align="center" />
        <el-table-column prop="track" label="国际追踪号" align="center" />
        <el-table-column prop="date" label="下单时间" align="center" />
        <el-table-column prop="status" label="包裹状态" width="80" align="center" />
        <el-table-column prop="receive" label="是否收件" width="80" align="center" />
        <el-table-column label="操作" align="center" width="80">
          <a style="color: #74b9ff;">打印面单</a>
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
  </div>
</template>

<script>
export default {
  name: 'Pickup',
  data() {
    return {
      formInline: {
        approver: '',
        recipient: '',
        waybillCode: '',
        trackCode: '',
        status: ''
      },
      tableData: [
        {
          account: '7895590941',
          category: '四罐奶粉包税',
          recipient: '张三丰',
          address: '上海市普陀区金沙江路 1518 弄',
          tel: '13032145678',
          idcard: '610113197312151613',
          track: 'SZ202102210099',
          date: '2021/2/21 16:34:16',
          status: '待取件',
          receive: '待取件'
        },
        {
          account: '7895590941',
          category: '四罐奶粉包税',
          recipient: '张丰',
          address: '上海市普陀区金沙江路 1518 弄',
          tel: '13032145678',
          idcard: '610113197312151613',
          track: 'SZ202102210099',
          date: '2021/2/21 16:34:16',
          status: '待取件',
          receive: '待取件'
        },
        {
          account: '7895590941',
          category: '四罐奶粉包税',
          recipient: '鲁智深',
          address: '上海市普陀区金沙江路 1518 弄',
          tel: '13032145678',
          idcard: '610113197312151613',
          track: 'SZ202102210099',
          date: '2021/2/21 16:34:16',
          status: '待取件',
          receive: '待取件'
        },
        {
          account: '7895590941',
          category: '四罐奶粉包税',
          recipient: '鲁智深',
          address: '上海市普陀区金沙江路 1518 弄',
          tel: '13032145678',
          idcard: '610113197312151613',
          track: 'SZ202102210099',
          date: '2021/2/21 16:34:16',
          status: '待取件',
          receive: '待取件'
        },
        {
          account: '7895590941',
          category: '四罐奶粉包税',
          recipient: '宋江',
          address: '上海市普陀区金沙江路 1518 弄',
          tel: '13032145678',
          idcard: '610113197312151613',
          track: 'SZ202102210099',
          date: '2021/2/21 16:34:16',
          status: '待取件',
          receive: '待取件'
        },
        {
          account: '7895590941',
          category: '四罐奶粉包税',
          recipient: '李大虫',
          address: '上海市普陀区金沙江路 1518 弄',
          tel: '13032145678',
          idcard: '610113197312151613',
          track: 'SZ202102210099',
          date: '2021/2/21 16:34:16',
          status: '待取件',
          receive: '待取件'
        },
        {
          account: '7895590941',
          category: '四罐奶粉包税',
          recipient: '张三丰',
          address: '上海市普陀区金沙江路 1518 弄',
          tel: '13032145678',
          idcard: '610113197312151613',
          track: 'SZ202102210099',
          date: '2021/2/21 16:34:16',
          status: '待取件',
          receive: '待取件'
        },
        {
          account: '7895590941',
          category: '四罐奶粉包税',
          recipient: '张三丰',
          address: '上海市普陀区金沙江路 1518 弄',
          tel: '13032145678',
          idcard: '610113197312151613',
          track: 'SZ202102210099',
          date: '2021/2/21 16:34:16',
          status: '待取件',
          receive: '待取件'
        },
        {
          account: '7895590941',
          category: '四罐奶粉包税',
          recipient: '张三丰',
          address: '上海市普陀区金沙江路 1518 弄',
          tel: '13032145678',
          idcard: '610113197312151613',
          track: 'SZ202102210099',
          date: '2021/2/21 16:34:16',
          status: '待取件',
          receive: '待取件'
        },
        {
          account: '7895590941',
          category: '四罐奶粉包税',
          recipient: '张三丰',
          address: '上海市普陀区金沙江路 1518 弄',
          tel: '13032145678',
          idcard: '610113197312151613',
          track: 'SZ202102210099',
          date: '2021/2/21 16:34:16',
          status: '待取件',
          receive: '待取件'
        }
      ],
      multipleSelection: [],
      paginationConfig: {
        pageSize: 20,
        total: 1000
      }
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!')
    },
    clearIpt() {
      this.formInline = {
        approver: '',
        recipient: '',
        waybillCode: '',
        trackCode: '',
        status: ''
      }
    },
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
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
    alertSelected() {
      this.$message({
        type: this.multipleSelection.length > 0 ? 'success' : 'warning',
        message: this.multipleSelection.length > 0 ? `${[...new Set(this.multipleSelection.map((item) => item.recipient))]}的快递被收取` : '未选择任何订单'
      })
      this.toggleSelection()
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

<style scoped>
.container {
  padding: 1vw;
}
.searchWrap{
  /* padding: 1vw; */
  margin: .5vh 0;
}
.pagination{
  display: flex;
  margin: 1vh 0;
  justify-content: center;
}
</style>
