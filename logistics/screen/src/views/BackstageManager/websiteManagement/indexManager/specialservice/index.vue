<template>
  <div class="container">
    <div class="searchWrap">
      <el-form
        :inline="true"
        :model="formInline"
        size="small"
        class="demo-form-inline"
      >
        <el-form-item>
          <el-button type="warning" style="visibility: hidden" />
          <el-button
            type="success"
            @click.native="alertSelected"
          >新增服务</el-button>
          <el-button type="warning" style="visibility: hidden" />
          <el-button
            type="success"
            @click.native="alertSelected"
          >批量操作</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div class="tableWrap">
      <el-table
        ref="multipleTable"
        :data="dataSource"
        stripe
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="150" align="center" />
        <el-table-column type="index" label="序号" width="150" align="center" />
        <el-table-column
          prop="service"
          label="服务名称"
          width="250"
          align="center"
        />
        <el-table-column prop="icon" label="图标" width="250" align="center">
          <template slot-scope="scope">
            <svg-icon :icon-class="scope.row.icon" />
          </template>
        </el-table-column>
        <el-table-column
          prop="link"
          label="跳转链接"
          width="300"
          align="center"
        />
        <el-table-column label="操作" align="center" width="300">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="more" label="备注" align="center" />
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
import mixinInterface from '@/mixin/interface'
export default {
  name: 'Pickup',
  mixins: [mixinInterface],
  data() {
    return {
      url: {
        list: '/specialservice/list'
      },
      formInline: {
        approver: '',
        recipient: '',
        waybillCode: '',
        trackCode: '',
        status: ''
      },
      multipleSelection: [],
      paginationConfig: {
        pageSize: 20,
        total: 1000
      }
    }
  },
  methods: {
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
    alertSelected() {
      this.$message({
        type: this.multipleSelection.length > 0 ? 'success' : 'warning',
        message:
          this.multipleSelection.length > 0
            ? `${[
              ...new Set(this.multipleSelection.map((item) => item.recipient))
            ]}的快递被收取`
            : '未选择任何订单'
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

<style lang="scss" scoped>
.container {
  padding: 1vw;
}
.box {
  width: 6vw;
  height: 6vw;
  background-color: aqua;
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
.tableWrap {
  .svg-icon {
    width: 4rem;
    height: 4rem;
  }
}
</style>
