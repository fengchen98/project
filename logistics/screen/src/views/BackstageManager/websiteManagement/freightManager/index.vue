<template>
  <div class="container">
    <!-- <div class="searchWrap">
      <el-form
        :inline="true"
        :model="formInline"
        size="small"
        class="demo-form-inline"
      >
        <el-form-item>
          <el-button type="warning" style="visibility: hidden" />
          <el-button type="success" @click="add()">新增服务</el-button>
          <el-button type="warning" style="visibility: hidden" />
          <el-button
            type="success"
            @click.native="alertSelected"
          >批量操作</el-button>
        </el-form-item>
      </el-form>
    </div> -->

    <div class="tableWrap">
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column
          fixed
          prop="weight"
          label="重量(kg)/货币"
          width="150"
        />
        <el-table-column prop="name1" label="ParcelForce自送邮局" width="120" />
        <el-table-column prop="name2" label="ParcelForce上门取件" width="120" />
        <el-table-column
          prop="name3"
          label="ParcelForce自送Depot"
          width="120"
        />
        <el-table-column prop="name4" label="UPS文件特快" width="120" />
        <el-table-column prop="name5" label="ParcelForce优先包" width="120" />
        <el-table-column prop="name6" label="ParcelForce经济包" width="120" />
        <el-table-column prop="name7" label="ParcelForce小包" width="120" />
        <el-table-column prop="name8" label="Bpost比利时邮政" width="120" />
        <el-table-column prop="name9" label="六罐奶粉" width="120" />
        <el-table-column prop="name10" label="四罐奶粉" width="120" />
        <el-table-column prop="name11" label="333杂货包税" width="120" />
        <el-table-column prop="name12" label="法国邮政" width="120" />
        <el-table-column prop="name13" label="杂货小包" width="120" />
        <el-table-column prop="name14" label="护肤品小包" width="120" />
        <el-table-column prop="name15" label="套装专线" width="120" />
        <el-table-column prop="name16" label="333Plus杂货" width="120" />
        <el-table-column
          prop="name17"
          label="海运行李（暂停服务）"
          width="120"
        />
        <el-table-column prop="name18" label="空运行李" width="120" />
        <el-table-column prop="name19" label="两罐奶粉" width="120" />
        <el-table-column fixed="right" label="操作" width="120">
          <template slot-scope="scope">
            <el-button size="mini" type="text" @click="edit(scope.row)">新增</el-button>
            <el-button size="mini" type="text" @click="edit(scope.row)">编辑</el-button>
            <el-button
              size="mini"
              type="text"
              @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button>
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
      dialogImageUrl: '',
      dialogVisible: false,
      isShow: false,
      addObj: {
        theme: ' ',
        date: new Date().toLocaleDateString(),
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
          weight: '0.01-0.50',
          name1: '30.54',
          name2: '32.55',
          name3: '14.01',
          name4: '28.5',
          name5: '27.96',
          name6: '19.63',
          name7: '9.68',
          name8: '28',
          name9: '17.5',
          name10: '8.93',
          name11: '13.3',
          name12: '13.4',
          name13: '15.02',
          name14: '13.4',
          name15: '8.93',
          name16: '',
          name17: '76.46',
          name18: '',
          name19: '11'
        },
        {
          weight: '0.51-1.00',
          name1: '30.54',
          name2: '32.55',
          name3: '14.01',
          name4: '28.5',
          name5: '27.96',
          name6: '19.63',
          name7: '9.68',
          name8: '28',
          name9: '17.5',
          name10: '8.93',
          name11: '13.3',
          name12: '13.4',
          name13: '15.02',
          name14: '13.4',
          name15: '8.93',
          name16: '',
          name17: '76.46',
          name18: '',
          name19: '11'
        },
        {
          weight: '1.01-1.50',
          name1: '30.54',
          name2: '32.55',
          name3: '14.01',
          name4: '28.5',
          name5: '27.96',
          name6: '19.63',
          name7: '9.68',
          name8: '28',
          name9: '17.5',
          name10: '8.93',
          name11: '13.3',
          name12: '13.4',
          name13: '15.02',
          name14: '13.4',
          name15: '8.93',
          name16: '',
          name17: '76.46',
          name18: '',
          name19: '11'
        },
        {
          weight: '1.51-2.00',
          name1: '30.54',
          name2: '32.55',
          name3: '14.01',
          name4: '28.5',
          name5: '27.96',
          name6: '19.63',
          name7: '9.68',
          name8: '28',
          name9: '17.5',
          name10: '8.93',
          name11: '13.3',
          name12: '13.4',
          name13: '15.02',
          name14: '13.4',
          name15: '8.93',
          name16: '',
          name17: '76.46',
          name18: '',
          name19: '11'
        },
        {
          weight: '2.01-2.50',
          name1: '30.54',
          name2: '32.55',
          name3: '14.01',
          name4: '28.5',
          name5: '27.96',
          name6: '19.63',
          name7: '9.68',
          name8: '28',
          name9: '17.5',
          name10: '8.93',
          name11: '13.3',
          name12: '13.4',
          name13: '15.02',
          name14: '13.4',
          name15: '8.93',
          name16: '',
          name17: '76.46',
          name18: '',
          name19: '11'
        },
        {
          weight: '2.51-3.00',
          name1: '30.54',
          name2: '32.55',
          name3: '14.01',
          name4: '28.5',
          name5: '27.96',
          name6: '19.63',
          name7: '9.68',
          name8: '28',
          name9: '17.5',
          name10: '8.93',
          name11: '13.3',
          name12: '13.4',
          name13: '15.02',
          name14: '13.4',
          name15: '8.93',
          name16: '',
          name17: '76.46',
          name18: '',
          name19: '11'
        },
        {
          weight: '3.01-3.50',
          name1: '30.54',
          name2: '32.55',
          name3: '14.01',
          name4: '28.5',
          name5: '27.96',
          name6: '19.63',
          name7: '9.68',
          name8: '28',
          name9: '17.5',
          name10: '8.93',
          name11: '13.3',
          name12: '13.4',
          name13: '15.02',
          name14: '13.4',
          name15: '8.93',
          name16: '',
          name17: '76.46',
          name18: '',
          name19: '11'
        },
        {
          weight: '3.51-4.00',
          name1: '30.54',
          name2: '32.55',
          name3: '14.01',
          name4: '28.5',
          name5: '27.96',
          name6: '19.63',
          name7: '9.68',
          name8: '28',
          name9: '17.5',
          name10: '8.93',
          name11: '13.3',
          name12: '13.4',
          name13: '15.02',
          name14: '13.4',
          name15: '8.93',
          name16: '',
          name17: '76.46',
          name18: '',
          name19: '11'
        },
        {
          weight: '4.01-4.50',
          name1: '30.54',
          name2: '32.55',
          name3: '14.01',
          name4: '28.5',
          name5: '27.96',
          name6: '19.63',
          name7: '9.68',
          name8: '28',
          name9: '17.5',
          name10: '8.93',
          name11: '13.3',
          name12: '13.4',
          name13: '15.02',
          name14: '13.4',
          name15: '8.93',
          name16: '',
          name17: '76.46',
          name18: '',
          name19: '11'
        },
        {
          weight: '4.51-5.00',
          name1: '30.54',
          name2: '32.55',
          name3: '14.01',
          name4: '28.5',
          name5: '27.96',
          name6: '19.63',
          name7: '9.68',
          name8: '28',
          name9: '17.5',
          name10: '8.93',
          name11: '13.3',
          name12: '13.4',
          name13: '15.02',
          name14: '13.4',
          name15: '8.93',
          name16: '',
          name17: '76.46',
          name18: '',
          name19: '11'
        },
        {
          weight: '5.01-5.50',
          name1: '30.54',
          name2: '32.55',
          name3: '14.01',
          name4: '28.5',
          name5: '27.96',
          name6: '19.63',
          name7: '9.68',
          name8: '28',
          name9: '17.5',
          name10: '8.93',
          name11: '13.3',
          name12: '13.4',
          name13: '15.02',
          name14: '13.4',
          name15: '8.93',
          name16: '',
          name17: '76.46',
          name18: '',
          name19: '11'
        },
        {
          weight: '5.51-6.00',
          name1: '30.54',
          name2: '32.55',
          name3: '14.01',
          name4: '28.5',
          name5: '27.96',
          name6: '19.63',
          name7: '9.68',
          name8: '28',
          name9: '17.5',
          name10: '8.93',
          name11: '13.3',
          name12: '13.4',
          name13: '15.02',
          name14: '13.4',
          name15: '8.93',
          name16: '',
          name17: '76.46',
          name18: '',
          name19: '11'
        },
        {
          weight: '6.01-6.50',
          name1: '30.54',
          name2: '32.55',
          name3: '14.01',
          name4: '28.5',
          name5: '27.96',
          name6: '19.63',
          name7: '9.68',
          name8: '28',
          name9: '17.5',
          name10: '8.93',
          name11: '13.3',
          name12: '13.4',
          name13: '15.02',
          name14: '13.4',
          name15: '8.93',
          name16: '',
          name17: '76.46',
          name18: '',
          name19: '11'
        },
        {
          weight: '6.51-7.00',
          name1: '30.54',
          name2: '32.55',
          name3: '14.01',
          name4: '28.5',
          name5: '27.96',
          name6: '19.63',
          name7: '9.68',
          name8: '28',
          name9: '17.5',
          name10: '8.93',
          name11: '13.3',
          name12: '13.4',
          name13: '15.02',
          name14: '13.4',
          name15: '8.93',
          name16: '',
          name17: '76.46',
          name18: '',
          name19: '11'
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
        link: row.link,
        date: new Date().toLocaleDateString(),
        more: row.more
      }
      this.allid = row.id
      this.dialogTitle = 'edit'
    },
    add() {
      this.isShow = true
      this.addObj = {
        theme: ' ',
        // startDate: '',
        // endDate: '',
        date: new Date().toLocaleDateString(),
        link: '',
        more: ''
      }
      this.dialogTitle = 'add'
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
        // const id = this.allid
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
