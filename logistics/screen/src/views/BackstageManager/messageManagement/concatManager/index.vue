<template>
  <div class="mainWrapper">
    <div class="searchWrapper">
      <el-input v-model="searchText" placeholder="输入信息查询" />
      <el-button type="primary" @click="handleSearch">搜索</el-button>
      <el-button type="primary" @click="getData">重置</el-button>
    </div>
    <div class="tableWrapper">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column type="index" label="序号" width="180" align="center" />
        <el-table-column prop="username" label="用户名" align="center" />
        <el-table-column prop="type" label="反馈类型" align="center" />
        <el-table-column prop="name" label="姓名" align="center" />
        <el-table-column prop="tel" label="电话" align="center" />
        <el-table-column prop="email" label="邮箱" align="center" />
        <el-table-column prop="subject" label="主题" align="center" />
        <el-table-column prop="content" label="内容" align="center" />
        <el-table-column prop="create_time" label="创建时间" align="center" width="100">
          <template slot-scope="scope">{{
            scope.row.create_time.slice(0, -2)
          }}</template>
        </el-table-column>
        <el-table-column prop="status" label="状态" align="center">
          <template slot-scope="scope">
            <span :class="scope.row.status == 0 ? 'undeal' : 'deal'">{{
              scope.row.status === '0' ? '未处理' : '已处理'
            }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="180">
          <template slot-scope="scope">
            <div class="coopBox">
              <el-popconfirm
                title="确认是否受理记录?"
                @onConfirm="handleClickDeal(scope.row.id)"
              >
                <el-button
                  slot="reference"
                  type="primary"
                  size="mini"
                >受理</el-button>
              </el-popconfirm>
              <el-popconfirm
                title="确认删除?删除后不可恢复"
                @onConfirm="handleClickDelete(scope.row.id)"
              >
                <el-button
                  slot="reference"
                  type="danger"
                  size="mini"
                >删除</el-button>
              </el-popconfirm>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { get, remove, deal, search } from '@/api/concat'
import { Message } from 'element-ui'

export default {
  data() {
    return {
      searchText: '',
      tableData: []
    }
  },
  created() {
    this.getData()
  },
  methods: {
    handleSearch() {
      if (this.searchText !== '') {
        search(this.searchText).then((res) => {
          this.tableData = res.data.filter((o) => o.type !== '加盟合作')
        })
      } else {
        this.getData()
      }
    },
    handleClickDelete(id) {
      remove(id).then((res) => {
        if (res.code === 20000) {
          Message.success('记录已删除')
        }
      })
    },
    handleClickDeal(id) {
      deal(id).then((res) => {
        if (res.code === 20000) {
          Message.success('记录已处理')
          this.handleSearch()
        }
      })
    },
    getData() {
      get().then((res) => {
        if (res.code === 20000) {
          this.tableData = res.data.filter((o) => o.type !== '加盟合作')
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.mainWrapper {
  position: relative;
  width: 100%;
}
.searchWrapper {
  margin: 2rem 4rem 0 2rem;
  .el-input {
    width: 200px;
    margin: 0 2rem;
  }
}
.tableWrapper {
  position: relative;
  padding: 2rem;
  .undeal {
    color: #e74c3c;
  }
  .deal {
    color: #2ecc71;
  }
  .coopBox {
    display: flex;
    justify-content: space-around;
  }
}
</style>
