<template>
  <div class="cooperate">
    <el-tabs type="border-card">
      <!--下单说明 -->
      <el-tab-pane label="下单说明">
        <el-table :data="tableData1" stripe style="width: 100%" class="addPic">
          <el-table-column type="selection" width="90" align="center" />
          <el-table-column
            type="index"
            label="序号"
            width="140"
            align="center"
          />
          <el-table-column
            prop="date"
            label="上传日期"
            width="170"
            align="center"
          />
          <el-table-column prop="content" label="内容" align="center" />
          <el-table-column label="操作" width="400" align="center">
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="
                  showEditDialog(scope.row.id)
                  tag = 1
                "
              >编辑</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="
                  handleDelete(scope.$index, scope.row)
                  tag = 1
                "
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-button
          icon="el-icon-plus"
          size="mini"
          type="success"
          style="width: 110px; height: 40px; font-size: 14px"
          @click="
            addDialogVisible = true
            tag = 1
          "
        >新增内容</el-button>
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          style="width: 110px; height: 40px; font-size: 14px"
        >批量删除</el-button></el-tab-pane>
      <!-- 取件投递 -->
      <el-tab-pane label="取件投递">
        <el-table :data="tableData2" stripe style="width: 100%" class="addPic">
          <el-table-column type="selection" width="90" align="center" />
          <el-table-column
            type="index"
            label="序号"
            width="140"
            align="center"
          />
          <el-table-column
            prop="date"
            label="上传日期"
            width="170"
            align="center"
          />
          <el-table-column prop="content" label="内容" align="center" />
          <el-table-column
            label="操作"
            width="300"
            align="center"
            fixed="right"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="
                  showEditDialog(scope.row.id)
                  tag = 2
                "
              >编辑</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="
                  handleDelete(scope.$index)
                  tag = 2
                "
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-button
          icon="el-icon-plus"
          size="mini"
          type="success"
          style="width: 110px; height: 40px; font-size: 14px"
          @click="
            addDialogVisible = true
            tag = 2
          "
        >新增内容</el-button>
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          style="width: 110px; height: 40px; font-size: 14px"
        >批量删除</el-button></el-tab-pane>
      <!-- 清关和税金 -->
      <el-tab-pane label="清关和税金">
        <el-table :data="tableData3" stripe style="width: 100%" class="addPic">
          <el-table-column type="selection" width="90" align="center" />
          <el-table-column
            type="index"
            label="序号"
            width="140"
            align="center"
          />
          <el-table-column
            prop="date"
            label="上传日期"
            width="170"
            align="center"
          />
          <el-table-column prop="content" label="内容" align="center" />
          <el-table-column
            label="操作"
            width="300"
            align="center"
            fixed="right"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="
                  showEditDialog(scope.row.id)
                  tag = 2
                "
              >编辑</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="
                  handleDelete(scope.$index)
                  tag = 2
                "
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-button
          icon="el-icon-plus"
          size="mini"
          type="success"
          style="width: 110px; height: 40px; font-size: 14px"
          @click="
            addDialogVisible = true
            tag = 2
          "
        >新增内容</el-button>
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          style="width: 110px; height: 40px; font-size: 14px"
        >批量删除</el-button></el-tab-pane>
      <!-- 支付相关 -->
      <el-tab-pane label="支付相关">
        <el-table :data="tableData4" stripe style="width: 100%" class="addPic">
          <el-table-column type="selection" width="90" align="center" />
          <el-table-column
            type="index"
            label="序号"
            width="140"
            align="center"
          />
          <el-table-column
            prop="date"
            label="上传日期"
            width="170"
            align="center"
          />
          <el-table-column prop="content" label="内容" align="center" />
          <el-table-column
            label="操作"
            width="300"
            align="center"
            fixed="right"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="
                  showEditDialog(scope.row.id)
                  tag = 2
                "
              >编辑</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="
                  handleDelete(scope.$index)
                  tag = 2
                "
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-button
          icon="el-icon-plus"
          size="mini"
          type="success"
          style="width: 110px; height: 40px; font-size: 14px"
          @click="
            addDialogVisible = true
            tag = 2
          "
        >新增内容</el-button>
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          style="width: 110px; height: 40px; font-size: 14px"
        >批量删除</el-button></el-tab-pane>
      <!-- 售后问题 -->
      <el-tab-pane label="售后问题">
        <el-table :data="tableData5" stripe style="width: 100%" class="addPic">
          <el-table-column type="selection" width="90" align="center" />
          <el-table-column
            type="index"
            label="序号"
            width="140"
            align="center"
          />
          <el-table-column
            prop="date"
            label="上传日期"
            width="170"
            align="center"
          />
          <el-table-column prop="content" label="内容" align="center" />
          <el-table-column
            label="操作"
            width="300"
            align="center"
            fixed="right"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="
                  showEditDialog(scope.row.id)
                  tag = 2
                "
              >编辑</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="
                  handleDelete(scope.$index)
                  tag = 2
                "
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-button
          icon="el-icon-plus"
          size="mini"
          type="success"
          style="width: 110px; height: 40px; font-size: 14px"
          @click="
            addDialogVisible = true
            tag = 2
          "
        >新增内容</el-button>
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          style="width: 110px; height: 40px; font-size: 14px"
        >批量删除</el-button></el-tab-pane>
      <!-- 行李寄存 -->
      <el-tab-pane label="行李寄存">
        <el-table :data="tableData6" stripe style="width: 100%" class="addPic">
          <el-table-column type="selection" width="90" align="center" />
          <el-table-column
            type="index"
            label="序号"
            width="140"
            align="center"
          />
          <el-table-column
            prop="date"
            label="上传日期"
            width="170"
            align="center"
          />
          <el-table-column prop="content" label="内容" align="center" />
          <el-table-column
            label="操作"
            width="300"
            align="center"
            fixed="right"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="
                  showEditDialog(scope.row.id)
                  tag = 2
                "
              >编辑</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="
                  handleDelete(scope.$index)
                  tag = 2
                "
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-button
          icon="el-icon-plus"
          size="mini"
          type="success"
          style="width: 110px; height: 40px; font-size: 14px"
          @click="
            addDialogVisible = true
            tag = 2
          "
        >新增内容</el-button>
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          style="width: 110px; height: 40px; font-size: 14px"
        >批量删除</el-button></el-tab-pane>
    </el-tabs>

    <el-dialog
      title="添加内容"
      :visible.sync="addDialogVisible"
      width="30%"
      @close="addDialogClosed"
    >
      <el-form
        ref="addFormRef"
        label-width="80px"
        :model="joinForm"
        :rules="addFormrules"
      >
        <el-form-item label="内容" prop="content">
          <el-input v-model="joinForm.content" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addContent">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="修改信息"
      :visible.sync="editDialogVisible"
      width="30%"
      @close="editDialogclosed"
    >
      <el-form
        ref="editFormRef"
        :model="editForm"
        :rules="editFormRules"
        label-width="30%"
      >
        <el-form-item label="内容" prop="content">
          <el-input v-model="joinForm.content" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="editDialogVisible = false"
        >确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData1: [
        {
          date: '2016-05-02',
          content: '如何邮寄包裹'
        },
        {
          date: '2016-05-02',
          content: '如何选择邮寄路线'
        },
        {
          date: '2016-05-02',
          content: '如何支付订单运费'
        },
        {
          date: '2016-05-02',
          content: '包裹上要贴什么文件'
        },
        {
          date: '2016-05-02',
          content: '下单时填错怎么办'
        },
        {
          date: '2016-05-02',
          content: '如何取消订单'
        },
        {
          date: '2016-05-02',
          content: '没有称如何知道包裹重量'
        },
        {
          date: '2016-05-02',
          content: '可以把运费给取件司机吗'
        },
        {
          date: '2016-05-02',
          content: '想寄的产品不在申报列表内'
        }
      ],
      tableData2: [
        {
          date: '2016-05-02',
          content: '预约上门取件'
        },
        {
          date: '2016-05-02',
          content: '错过司机取件怎么办'
        },
        {
          date: '2016-05-02',
          content: '司机可以同时取件和送箱子吗'
        },
        {
          date: '2016-05-02',
          content: '自送网点去哪里投递'
        },
        {
          date: '2016-05-02',
          content: '包裹投递/取件后物流没更新'
        }
      ],
      tableData3: [
        {
          date: '2016-05-02',
          content: '包裹邮寄中国会有税金吗'
        },
        {
          date: '2016-05-02',
          content: '哪些是航空禁运品'
        },
        {
          date: '2016-05-02',
          content: '包清关需要额外收费吗'
        },
        {
          date: '2016-05-02',
          content: '可以税金代缴吗'
        }
      ],
      tableData4: [
        {
          date: '2016-05-02',
          content: '如何支付订单'
        },
        {
          date: '2016-05-02',
          content: '扣款后订单没有成功怎么办'
        },
        {
          date: '2016-05-02',
          content: '订单取消后退款在哪'
        },
        {
          date: '2016-05-02',
          content: '重复/错误扣款'
        },
        {
          date: '2016-05-02',
          content: '不同支付方式可以同时使用吗'
        },
        {
          date: '2016-05-02',
          content: '账户余额如何退款'
        },
        {
          date: '2016-05-02',
          content: '可以提供出口证明吗'
        }
      ],
      tableData5: [
        {
          date: '2016-05-02',
          content: '如何查询物流'
        },
        {
          date: '2016-05-02',
          content: '包裹多久能到达中国'
        },
        {
          date: '2016-05-02',
          content: '超重补款通知是什么'
        },
        {
          date: '2016-05-02',
          content: '物流长时间无更新'
        },
        {
          date: '2016-05-02',
          content: '包裹破损怎么办'
        },
        {
          date: '2016-05-02',
          content: '包裹丢失怎么办'
        },
        {
          date: '2016-05-02',
          content: '包裹退运怎么办'
        },
        {
          date: '2016-05-02',
          content: '包裹未如实申报的后果'
        }
      ],
      tableData6: [
        {
          date: '2016-05-02',
          content: '如何准备寄存行李'
        },
        {
          date: '2016-05-02',
          content: '寄存的重量上限'
        },
        {
          date: '2016-05-02',
          content: '哪些物品可以寄存'
        },
        {
          date: '2016-05-02',
          content: '如何计算寄存费用'
        },
        {
          date: '2016-05-02',
          content: '行李寄存的流程'
        }
      ],
      addDialogVisible: false,
      editDialogVisible: false,
      addForm: {
        date: new Date().toLocaleDateString(),
        name: '',
        img: ''
      },
      joinForm: {
        date: new Date().toLocaleDateString(),
        name: '',
        img: ''
      },
      editForm: {
        name: '',
        img: ''
      },
      addFormrules: {
        name: [{ required: true, message: '请输入名称', trigger: 'blur' }],
        img: [{ required: true, message: '请输入路径', trigger: 'blur' }]
      },
      editFormrules: {
        name: [{ required: true, message: '请输入名称', trigger: 'blur' }],
        img: [{ required: true, message: '请输入路径', trigger: 'blur' }]
      }
    }
  },
  methods: {
    async handleDelete(index) {
      const res = await this.$confirm(
        '此操作将永久删除该内容, 是否继续?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch((err) => err)
      if (res !== 'confirm') return this.$message.info('已取消删除')
      console.log(this.tag)
      if (this.tag === 1) this.tableData1.splice(index, 1)
      this.tableData2.splice(index, 1)
    },
    async showEditDialog(id) {
      this.addDialogVisible = true
    },
    addDialogClosed() {
      this.$refs.addFormRef.resetFields()
    },
    editDialogclosed() {
      this.$refs.editFormRef.resetFields()
    },
    addContent() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return
        if (this.tag === 1) this.tableData1.push(this.addForm)
        this.tableData2.push(this.joinForm)
        this.addDialogVisible = false
      })
    }
  }
}
</script>

<style scoped>
.addPic {
  margin-bottom: 15px;
}
</style>
