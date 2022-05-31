<template>
  <div class="container">
    <div class="search">
      <el-select
        v-model="current"
        placeholder="请选择问题分类"
        @change="getData"
      >
        <el-option
          v-for="(item, index) in options"
          :key="index"
          :label="item.label"
          :value="item.value"
        />
      </el-select>
      <el-button
        type="success"
        @click="addDialogVisible = true"
      >新增问题</el-button>
    </div>
    <div class="tableWrapper">
      <el-table :data="tableData" stripe style="width: 100%">
        <el-table-column type="index" label="序号" width="180" align="center" />
        <el-table-column prop="type" label="分类" align="center" />
        <el-table-column prop="title" label="问题" align="center" />
        <el-table-column prop="status" label="发布情况" align="center">
          <template slot-scope="scope">
            <el-popconfirm
              v-if="scope.row.status === '1'"
              title="是否确认下架该商品,下架后商品将不能被购买"
              @onConfirm="changeStatus('offline', scope.row.id)"
            >
              <el-tag slot="reference" type="success">已上架</el-tag>
            </el-popconfirm>

            <el-popconfirm
              v-else
              title="是否确认上架该商品"
              @onConfirm="changeStatus('online', scope.row.id)"
            ><el-tag
              slot="reference"
              type="danger"
            >已下架</el-tag></el-popconfirm>
          </template>
        </el-table-column>
        <el-table-column label="内容" width="180" align="center">
          <template slot-scope="scope">
            <span
              class="content"
              @click="
                contentDialogVisible = true
                content = scope.row
              "
            >内容预览</span>
          </template>
        </el-table-column>
        <el-table-column prop="create_time" label="创建时间" align="center" />
        <el-table-column label="操作" width="180" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.row)"
            >编辑</el-button>
            <el-popconfirm
              title="是否删除该问题,删除后不可恢复"
              @onConfirm="handleDel(scope.row.id)"
            ><el-button
              slot="reference"
              class="delBtn"
              size="mini"
              type="danger"
            >删除</el-button></el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog
      title="问题详情"
      :visible.sync="contentDialogVisible"
      width="800px"
    >
      <content-page :content="content" />
    </el-dialog>
    <el-dialog title="添加问题" :visible.sync="addDialogVisible" width="60%">
      <addWindow @add="handleAdd" />
    </el-dialog>
    <el-dialog title="编辑问题" :visible.sync="editDialogVisible" width="60%">
      <editWindow :content="editObj" @edit="receiveEdit" />
    </el-dialog>
  </div>
</template>

<script>
import { alllist, changeStatus, ListByType, remove } from '@/api/problem'
import { Message } from 'element-ui'

export default {
  components: {
    contentPage: () => import('./contentPage.vue'),
    addWindow: () => import('./addWindow.vue'),
    editWindow: () => import('./editWindow.vue')
  },
  data() {
    return {
      current: '所有问题',
      options: [
        {
          value: '所有问题',
          label: '所有问题'
        },
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
      tableData: [],
      contentDialogVisible: false,
      addDialogVisible: false,
      editDialogVisible: false,
      content: {},
      editObj: {}
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      if (this.current === '所有问题') {
        alllist().then((res) => {
          if (res.code === 20000) {
            this.tableData = res.data
            //   console.log(this.tableData)
          }
        })
      } else {
        ListByType(this.current).then((res) => {
          if (res.code === 20000) {
            this.tableData = res.data
          }
        })
      }
    },
    changeStatus(type, id) {
      changeStatus(type, id).then((res) => {
        if (res.code === 20000) {
          if (type === 'online') {
            Message.success('发布成功')
          } else if (type === 'offline') {
            Message.success('问题已下线')
          }
          this.getData()
        }
      })
    },
    handleAdd(arg) {
      if (arg === 'success') {
        Message.success('上传成功')
        this.addDialogVisible = false
        this.$router.push('/BackstageManager/webapp/problem')
        this.getData()
      }
    },
    handleDel(id) {
      remove(id).then((res) => {
        if (res.code === 20000) {
          Message.success('已删除该条目')
          this.getData()
        }
      })
    },
    handleEdit(item) {
      this.editObj = item
      this.editDialogVisible = true
    },
    receiveEdit(arg) {
      if (arg === 'success') {
        Message.success('修改成功')
        this.editDialogVisible = false
        this.$router.push('/BackstageManager/webapp/problem')
        this.getData()
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  padding: 2rem;
}
.search {
  .el-button {
    margin-left: 2rem;
  }
}
.content {
  cursor: pointer;
  color: #3498db;
}
.el-tag {
  cursor: pointer;
}
.delBtn {
  margin: 0 1rem;
}
</style>
