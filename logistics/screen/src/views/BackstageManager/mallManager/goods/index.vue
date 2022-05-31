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
          <el-select
            v-model="currentTable"
            placeholder="请选择"
            @change="changeValue"
          >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
          <el-button type="warning" style="visibility: hidden" />
          <el-button type="success" @click="addGood">新增商品</el-button>
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
        <el-table-column type="index" label="序号" align="center" />
        <el-table-column prop="img" label="商品主图" width="130" align="center">
          <template slot-scope="scope">
            <img
              :src="scope.row.images ? scope.row.images[0] : ''"
              alt
              style="width: 110px; height: 60px; object-fit: contain"
            >
          </template>
        </el-table-column>
        <el-table-column prop="brand" label="商品品牌" align="center" />
        <el-table-column
          prop="title"
          label="商品名称"
          align="center"
          width="360"
        />
        <el-table-column prop="is_shelves" label="上架状态" align="center">
          <template slot-scope="scope">
            <el-popconfirm
              v-if="scope.row.is_shelves === 1"
              title="是否确认下架该商品,下架后商品将不能被购买"
              @onConfirm="is_shelves('offSale', [scope.row.id])"
            >
              <el-tag slot="reference" type="success">已上架</el-tag>
            </el-popconfirm>

            <el-popconfirm
              v-else
              title="是否确认上架该商品"
              @onConfirm="is_shelves('onSale', [scope.row.id])"
            ><el-tag
              slot="reference"
              type="danger"
            >已下架</el-tag></el-popconfirm>
          </template>
        </el-table-column>
        <el-table-column prop="price" label="商品单价" align="center" />
        <el-table-column prop="more" label="备注" align="center" />
        <el-table-column prop="sales_count" label="销量" align="center" />
        <el-table-column label="详情" align="center" width="80">
          <template slot-scope="scope">
            <p @click="showDetail(scope.row)">商品详情</p>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="200">
          <template slot-scope="scope">
            <el-button size="mini" @click="showPop(scope.row)">编辑</el-button>
            <el-popconfirm
              title="确定删除该商品,删除操作无法撤回？"
              @onConfirm="handleDelete(scope.$index, scope.row)"
            >
              <el-button
                slot="reference"
                size="mini"
                type="danger"
                class="delBtn"
              >删除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 编辑功能 -->
    <el-dialog
      title="编辑商品信息"
      :visible.sync="editVisible"
      width="65%"
      top="10vh"
    >
      <popWindow
        ref="popwindow"
        :currentdata="currentData"
        @editwindow="submitAdd"
        @closepop="offPop"
      />
    </el-dialog>
    <!-- 增加功能 -->
    <el-dialog
      title="新增商品信息"
      :visible.sync="addVisible"
      width="65%"
      top="10vh"
    >
      <addWindow ref="addwindow" @addwindow="addAdd" @closeadd="offAdd" />
    </el-dialog>
    <!-- 查看详情功能 -->
    <el-dialog
      title="商品详情信息"
      :visible.sync="detailVisible"
      width="65%"
      top="10vh"
    >
      <detailPage :currentdata="currentData" @detailwindow="closeDetail" />
    </el-dialog>
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
// import mixinInterface from '@/mixin/interface'
import {
  getProduct,
  getOnSaleProduct,
  getOffSaleProduct,
  updateProduct,
  delProduct,
  onSale,
  offSale
} from '@/api/product'
import { Message } from 'element-ui'
import { stringToArray } from '@/utils/index'

export default {
  name: 'Pickup',
  components: {
    // 按需加载
    popWindow: () => import('./popWindow'),
    addWindow: () => import('./addWindow'),
    detailPage: () => import('./detailPage')
  },
  data() {
    return {
      allid: '',
      dialogImageUrl: '',
      editVisible: false,
      addVisible: false,
      detailVisible: false,
      currentData: {},
      formInline: {
        approver: '',
        recipient: '',
        waybillCode: '',
        trackCode: '',
        status: ''
      },
      options: [
        {
          value: 'all',
          label: '所有商品'
        },
        {
          value: 'onSale',
          label: '在售商品'
        },
        {
          value: 'offSale',
          label: '下架商品'
        }
      ],
      tableData: [],
      multipleSelection: [],
      paginationConfig: {
        pageSize: 20,
        total: 1000
      },
      currentTable: 'all'
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      getProduct()
        .then((res) => {
          if (res.code === 20000) {
            this.tableData = res.data
            this.handleImgURL()
          }
        })
        .catch(() => {})
    },
    handleImgURL() {
      for (let i = 0; i < this.tableData.length; i += 1) {
        this.tableData[i].images = stringToArray(this.tableData[i].images)
        this.tableData[i].introduce_images = stringToArray(
          this.tableData[i].introduce_images
        )
      }
    },
    editData(data) {
      updateProduct(data).then((res) => {
        if (res.code === 20000) {
          Message.success('编辑已完成')
        }
      })
    },
    deleteData(ids) {
      delProduct(ids).then((res) => {
        if (res.code === 20000) {
          // console.log(res)
          Message.success('删除已完成')
        }
      })
    },
    changeValue() {
      if (this.currentTable === 'all') {
        // 展示在售商品信息
        this.getData()
      }
      if (this.currentTable === 'onSale') {
        // 展示上架商品
        getOnSaleProduct().then((res) => {
          if (res.code === 20000) {
            this.tableData = res.data
            this.handleImgURL()
          }
        })
      }
      if (this.currentTable === 'offSale') {
        // 展示下架商品
        getOffSaleProduct().then((res) => {
          if (res.code === 20000) {
            this.tableData = res.data
            this.handleImgURL()
          }
        })
      }
    },
    changePage(page) {
      console.log('', page)
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
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    edit(row) {
      this.isShow = true
      this.addObj = {
        brand: row.brand,
        link: row.link,
        date: new Date().toLocaleDateString(),
        more: row.more
      }
      this.allid = row.id
      this.dialogTitle = 'edit'
    },
    // 编辑按钮
    showPop(item) {
      this.currentData = item
      this.editVisible = true
    },
    submitAdd(addObj) {
      this.editVisible = false
      this.editData(addObj)
    },
    offPop() {
      this.editVisible = false
    },
    // 新增商品
    addGood() {
      this.addVisible = true
    },
    addAdd(arg) {
      if (arg === 'success') {
        this.addVisible = false
        Message.success('商品添加成功')
        this.getData()
      } else {
        Message.error('添加商品出现异常')
      }
    },
    offAdd() {
      this.addVisible = false
    },
    // 详情界面
    showDetail(item) {
      this.currentData = item
      this.detailVisible = true
    },
    closeDetail() {
      this.detailVisible = false
    },
    // 删除信息
    handleDelete(index, item) {
      this.tableData.splice(index, 1)
      this.deleteData([item.id])
    },
    // 上下架商品
    is_shelves(type, ids) {
      if (type === 'onSale') {
        onSale(ids).then((res) => {
          if (res.code === 20000) {
            Message.success('商品已上架')
            this.getData()
          }
        })
      } else if (type === 'offSale') {
        offSale(ids).then((res) => {
          if (res.code === 20000) {
            Message.success('商品已下架')
            this.getData()
          }
        })
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  position: relative;
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
.editBox {
  position: relative;
  margin: -55% auto;
  padding: 5%;
  width: 50%;
  height: 40%;
  background-color: rgb(255, 255, 255);
  border: rgb(31, 27, 27) 1px solid;
  z-index: 5;
}
.el-tag {
  cursor: pointer;
}
p {
  cursor: pointer;
  color: #3498db;
}
.delBtn {
  margin: 0 1rem;
}
</style>
