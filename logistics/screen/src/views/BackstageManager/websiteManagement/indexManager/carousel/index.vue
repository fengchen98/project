<template>
  <div class="container">
    <div class="searchWrap">
      <el-form
        :inline="true"
        :model="formInline"
        size="small"
        class="demo-form-inline"
      >
        <el-form-item label="发布状态">
          <el-select
            v-model="status"
            placeholder="发布状态"
            @change="changeData"
          >
            <el-option label="全部" value="all" />
            <el-option label="已发布" value="online" />
            <el-option label="未发布" value="offline" />
          </el-select>
        </el-form-item>
        <el-button
          type="success"
          @click.native="addDialogVisible = true"
        >新增图片</el-button>
        <el-button type="warning" style="visibility: hidden" />
      </el-form>
    </div>
    <div class="tableWrap">
      <el-table ref="multipleTable" :data="initData" stripe style="width: 100%">
        <el-table-column type="index" label="序号" align="center" />
        <el-table-column prop="create_time" label="上传日期" align="center" />
        <el-table-column prop="title" label="图片名称" align="center" />
        <el-table-column label="轮播图" align="center">
          <template slot-scope="scope">
            <img
              :src="scope.row.images"
              alt=""
              style="width: 200px; height: 100px"
            >
          </template>
        </el-table-column>
        <el-table-column label="发布状态" align="center">
          <template slot-scope="scope">
            <el-popconfirm
              v-if="scope.row.status === '1'"
              title="是否确认下架该商品,下架后商品将不能被购买"
              @onConfirm="changeStatus('offline', [scope.row.id])"
            >
              <el-tag slot="reference" type="success">已发布</el-tag>
            </el-popconfirm>

            <el-popconfirm
              v-else
              title="是否确认上架该商品"
              @onConfirm="changeStatus('online', [scope.row.id])"
            ><el-tag
              slot="reference"
              type="danger"
            >已下线</el-tag></el-popconfirm>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button>
            <el-popconfirm
              title="是否确认上架该商品"
              @onConfirm="handleDelete(scope.row.id)"
            >
              <el-button
                slot="reference"
                size="mini"
                type="danger"
              >删除</el-button></el-popconfirm>
          </template>
        </el-table-column>
        <el-table-column prop="mark" label="备注" width="300" align="center" />
      </el-table>
    </div>
    <el-dialog title="提示" :visible.sync="addDialogVisible" width="60%">
      <addWindow @addWindow="handleAddWindow" @handleCancel="handleCancel" />
    </el-dialog>
  </div>
</template>

<script>
import {
  onlineList,
  offlineList,
  allList,
  online,
  offline,
  del
} from '@/api/carousel'
import { Message } from 'element-ui'
import request from '@/utils/request'

export default {
  components: {
    addWindow: () => import('./addWindow.vue')
  },
  data() {
    return {
      status: 'all',
      initData: [],
      addDialogVisible: false,
      formInline: {
        name: '',
        region: '',
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
  created() {
    this.getData()
  },
  methods: {
    getData() {
      allList().then((res) => {
        this.initData = res.data
        this.transImgSrc()
      })
    },
    transImgSrc() {
      this.initData.forEach((o) => {
        o.images = request.defaults.baseURL + o.images
      })
    },
    changeData() {
      if (this.status === 'all') {
        allList().then((res) => {
          this.initData = res.data
          this.transImgSrc()
        })
      }
      if (this.status === 'online') {
        onlineList().then((res) => {
          this.initData = res.data
          this.transImgSrc()
        })
      }
      if (this.status === 'offline') {
        offlineList().then((res) => {
          this.initData = res.data
          this.transImgSrc()
        })
      }
    },
    handleAddWindow(arg) {
      if (arg === 'success') {
        this.addDialogVisible = false
        Message.success('上传成功')
        this.getData()
      }
    },
    handleCancel() {
      this.addDialogVisible = false
    },
    changeStatus(type, id) {
      if (type === 'online') {
        online(id).then((res) => {
          if (res.code === 20000) {
            Message.success('发布成功')
            this.getData()
          }
        })
      }
      if (type === 'offline') {
        offline(id).then((res) => {
          if (res.code === 20000) {
            Message.success('下线成功')
            this.getData()
          }
        })
      }
    },
    handleDelete(id) {
      del(id).then((res) => {
        if (res.code === 20000) {
          Message.success('图片已删除')
          this.getData()
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  box-sizing: border-box;
  padding: 1vw;
  width: 100%;
}
.demo-form-inline {
  overflow: hidden;
  &::-webkit-scrollbar {
    display: none;
  }
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
is-scrolling-left {
  overflow: hidden;
}
.tableWrap {
  .el-table__body-wrapper {
    overflow: hidden;
    &::-webkit-scrollbar {
      display: none;
    }
  }
}
.el-tag {
  cursor: pointer;
}
</style>
