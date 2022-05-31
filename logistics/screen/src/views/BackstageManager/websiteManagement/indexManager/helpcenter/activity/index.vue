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
          <el-button type="success" @click="add()">新增活动</el-button>
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
        :data="tableData"
        stripe
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" align="center" />
        <el-table-column type="index" label="序号" align="center" />
        <el-table-column prop="theme" label="活动主题" align="center" />
        <el-table-column prop="startDate" label="活动开始时间" align="center" />
        <el-table-column prop="endDate" label="活动结束时间" align="center" />
        <el-table-column
          prop="img"
          label="图片缩略图"
          width="300"
          align="center"
        >
          <template slot-scope="scope">
            <img :src="scope.row.img" alt style="width: 200px; height: 100px">
          </template>
        </el-table-column>
        <el-table-column prop="link" label="跳转链接" align="center" />
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini" @click="edit(scope.row)">编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="more" label="备注" align="center" />
      </el-table>
      <div v-show="isShow" class="editBox">
        <el-form ref="form" :model="addObj" label-width="80px">
          <el-form-item label="活动主题">
            <el-input v-model="addObj.theme" />
          </el-form-item>
          <el-form-item label="开始时间">
            <el-col :span="11">
              <el-date-picker
                v-model="addObj.startDate"
                type="date"
                placeholder="选择日期"
                style="width: 100%"
              />
            </el-col>
          </el-form-item>
          <el-form-item label="结束时间">
            <el-col :span="11">
              <el-date-picker
                v-model="addObj.endDate"
                type="date"
                placeholder="选择日期"
                style="width: 100%"
              />
            </el-col>
          </el-form-item>
          <el-form-item label="上传图片">
            <el-upload
              action="https://jsonplaceholder.typicode.com/posts/"
              list-type="picture-card"
              :on-preview="handlePictureCardPreview"
              :on-remove="handleRemove"
            >
              <i class="el-icon-plus" />
            </el-upload>
          </el-form-item>
          <el-dialog :visible.sync="dialogVisible" size="tiny">
            <img width="100%" :src="dialogImageUrl" alt>
          </el-dialog>
          <el-form-item label="跳转链接">
            <el-input v-model="addObj.link" />
          </el-form-item>
          <el-form-item label="添加备注">
            <el-input v-model="addObj.more" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit()">提交</el-button>
            <el-button @click="isShow = false">取消</el-button>
          </el-form-item>
        </el-form>
      </div>
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
      allid: '',
      dialogImageUrl: '',
      dialogVisible: false,
      isShow: false,
      addObj: {
        theme: ' ',
        startDate: '',
        endDate: '',
        link: '',
        more: ''
      },
      formInline: {
        approver: '',
        recipient: '',
        waybillCode: '',
        trackCode: '',
        status: ''
      },
      tableData: [
        {
          theme: '国庆献礼！小程序全新上线！',
          startDate: ' 2021/ 10/ 1',
          endDate: ' 2021/ 10/ 8',
          img: '/static/images/active1.jpg',
          link: 'http://localhost:8088/#/BackstageManager/webapp/webservice/activeManager',
          more: '此活动持续到12月底'
        },
        {
          theme: '海运行李仅需49镑！',
          startDate: ' 2021/ 10/ 1',
          endDate: ' 2021/ 10/ 8',
          img: '/static/images/active2.jpg',
          link: 'http://localhost:8088/#/BackstageManager/webapp/webservice/activeManager',
          more: '此活动持续到12月底'
        },
        {
          theme: '人人可领！行李空运10英镑优惠券！',
          startDate: ' 2021/ 10/ 1',
          endDate: ' 2021/ 10/ 8',
          img: '/static/images/active3.jpg',
          link: 'http://localhost:8088/#/BackstageManager/webapp/webservice/activeManager',
          more: '此活动持续到12月底'
        },
        {
          theme: '新用户注册即送8.8.8大礼包！',
          startDate: ' 2021/ 10/ 1',
          endDate: ' 2021/ 10/ 8',
          img: '/static/images/active4.jpg',
          link: 'http://localhost:8088/#/BackstageManager/webapp/webservice/activeManager',
          more: '此活动持续到12月底'
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
      console.log(this.multipleSelection)
    },
    edit(row) {
      this.isShow = true
      this.addObj = {
        theme: row.theme,
        startDate: row.startDate,
        endDate: row.endDate,
        link: row.link,
        more: row.more
      }
      this.allid = id
      this.dialogTitle = 'edit'
    },
    add() {
      this.isShow = true
      ;(this.addObj = {
        theme: ' ',
        startDate: '',
        endDate: '',
        link: '',
        more: ''
      }),
      (this.dialogTitle = 'add')
    },
    // onSubmit() {
    //   if (this.dialogTitle == "add"){
    //     this.tableData.push(this.addObj)
    //   }else{
    //     this.tableData = this.addObj
    //     };
    //   this.isShow = false
    // },
    onSubmit() {
      if (!this.allid) {
        this.tableData.push(this.addObj)
      } else {
        const id = this.allid
      }
      this.isShow = false
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
    handleDelete(index) {
      this.tableData.splice(index, 1)
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
  margin: -35% auto;
  padding: 5%;
  width: 50%;
  height: 40%;
  background-color: rgb(255, 255, 255);
  border: rgb(31, 27, 27) 1px solid;
  z-index: 5;
}
</style>
