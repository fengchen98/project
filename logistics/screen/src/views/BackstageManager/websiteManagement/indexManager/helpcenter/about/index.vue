<template>
  <div class="cooperate">
    <el-tabs type="border-card">
      <el-tab-pane label="图片管理">
        <el-table :data="tableData1" stripe style="width: 100%" class="addPic">
          <el-table-column type="selection" width="90" align="center" />
          <el-table-column
            type="index"
            width="90"
            label="序号"
            align="center"
          />
          <el-table-column
            prop="date"
            label="上传日期"
            width="180"
            align="center"
          />
          <el-table-column
            prop="name"
            label="图片名称"
            width="180"
            align="center"
          />
          <el-table-column prop="img" label="图片" align="center">
            <template slot-scope="scope">
              <img
                :src="scope.row.img"
                alt
                style="width: 250px; height: 150px"
              >
            </template>
          </el-table-column>
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
      <el-tab-pane label="内容管理">
        <el-table :data="tableData2" stripe style="width: 100%" class="addPic">
          <el-table-column type="selection" width="80" align="center" />
          <el-table-column
            type="index"
            label="序号"
            width="80"
            align="center"
          />
          <el-table-column
            prop="date"
            label="上传日期"
            width="95"
            align="center"
          />
          <el-table-column
            prop="theme"
            label="主题"
            width="150"
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
        v-show="tag == 1"
        ref="addFormRef"
        label-width="80px"
        :model="addForm"
        :rules="addFormrules"
      >
        <el-form-item label="名称" prop="name">
          <el-input v-model="addForm.name" />
        </el-form-item>
        <el-form-item label="图片路径" prop="img">
          <el-input v-model="addForm.img" />
        </el-form-item>
      </el-form>
      <el-form
        v-show="tag == 2"
        ref="addFormRef"
        label-width="80px"
        :model="joinForm"
        :rules="addFormrules"
      >
        <el-form-item label="主题" prop="theme">
          <el-input v-model="joinForm.img" />
        </el-form-item>
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
        <el-form-item label="图片名称" prop="name">
          <el-input v-model="editForm.name" />
        </el-form-item>
        <el-form-item label="图片路径" prop="img">
          <el-input v-model="editForm.img" />
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
          name: '关于1',
          img: '/static/images/1.jpg'
        },
        {
          date: '2016-05-02',
          name: '关于2',
          img: '/static/images/2.jpg'
        },
        {
          date: '2016-05-02',
          name: '关于3',
          img: '/static/images/3.jpg'
        }
      ],
      tableData2: [
        {
          date: '2016-05-02',
          theme: '最稳定，最负责，最安心的国际物流',
          content:
            '神州物流是2013年由美国硅谷投资，在英国注册的跨境物流公司。主营核心服务是欧洲至中国的国际快递，海淘转运和大宗货物空运。自成立以来，我们已服务英国及欧洲的数百万客户，覆盖留学生，海淘买手以及跨境电商。我们每年承运的包裹数量百万件， 包含各类个人物品，文件， 海淘商品，归国行李等等。<br/>神州物流业务范围覆盖英国，德国，爱尔兰，意大利，法国和西班牙等多个欧洲国家。我们不仅有重点城市的线下实体店， 完备的自营取件车队， 还有多个现代化运营中心， 数千个第三方合作的投递点。我们可在中国多个清关口岸办理清关，为我们的客户提供最快速的门到门跨境体验。神州物流与国际巨头英国皇家邮政Roymail, UPS, DHL, bpost, DPD 等合作多年，并在2017，成为英国唯一的阿里巴巴旗下菜鸟物流的官方指定合作物流商，为各类淘宝天猫海外商家提供助力！<br/>神州物流不忘初心，我们坚持提供最稳定，最负责，最安心的服务，我们愿与您一路同行，是您最佳的物流选择！'
        }
      ],
      addDialogVisible: false,
      editDialogVisible: false,
      tag: 0,
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
