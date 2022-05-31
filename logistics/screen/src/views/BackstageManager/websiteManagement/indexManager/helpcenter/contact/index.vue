<template>
  <div class="cooperate">
    <el-tabs type="border-card">
      <el-tab-pane label="图标管理">
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
              <svg-icon
                :icon-class="scope.row.img"
                style="width: 70px; height: 70px"
              />
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
      <el-tab-pane label="信息管理">
        <el-table :data="tableData2" stripe style="width: 100%" class="addPic">
          <el-table-column type="selection" width="90" align="center" />
          <el-table-column
            type="index"
            label="序号"
            align="center"
          />
          <el-table-column
            prop="date"
            label="上传日期"
            align="center"
          />
          <el-table-column
            prop="name"
            label="类型"
            align="center"
          />
          <el-table-column
            prop="number"
            label="手机号码"
            align="center"
          />
          <el-table-column
            prop="mail"
            label="邮箱地址"
            align="center"
          />
          <el-table-column
            prop="theme"
            label="主题"
            align="center"
          />
          <el-table-column
            prop="content"
            label="内容"
            align="center"
          />
          <el-table-column label="操作" width="300" align="center" fixed="right">
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
        <el-form-item label="类型" prop="name">
          <el-input v-model="joinForm.name" />
        </el-form-item>
        <el-form-item label="手机号码" prop="number">
          <el-input v-model="joinForm.number" />
        </el-form-item>
        <el-form-item label="邮箱地址" prop="mail">
          <el-input v-model="joinForm.mail" />
        </el-form-item>
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
          name: '电话',
          img: 'dianhua'
        },
        {
          date: '2016-05-02',
          name: '邮箱',
          img: 'youxiang'
        }
      ],
      tableData2: [
        {
          date: '2016-05-02',
          name: '奇怪',
          number: '18577777777',
          mail: '555555555@qq.com',
          theme: '加盟信息',
          content: '有意加盟'
        },
        {
          date: '2016-05-02',
          name: '奇怪',
          number: '18577777777',
          mail: '555555555@qq.com',
          theme: '加盟信息',
          content: '有意加盟'
        },
        {
          date: '2016-05-02',
          name: '奇怪',
          number: '18577777777',
          mail: '555555555@qq.com',
          theme: '加盟信息',
          content: '有意加盟'
        },
        {
          date: '2016-05-02',
          name: '奇怪',
          number: '18577777777',
          mail: '555555555@qq.com',
          theme: '加盟信息',
          content: '有意加盟'
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
