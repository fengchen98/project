<template>
  <div class="container">
    <div class="btnBox">
      <el-button
        type="primary"
        @click="dialogVisible = true"
      >添加收件地址</el-button>
      <el-dialog title="添加收件地址" :visible.sync="dialogVisible" width="40%">
        <div class="infoBox">
          <div>
            <span>姓名:</span>
            <el-input v-model="addItem.name" placeholder="请输入内容" />
          </div>
          <div>
            <span>电话:</span>
            <el-input v-model="addItem.phone" placeholder="请输入内容" />
          </div>
          <div>
            <span>区域:</span>
            <el-cascader
              v-model="addItem.area"
              size="large"
              :options="selectedAreaoptions"
              placeholder="请选择收件区域"
            />
          </div>
          <div>
            <span>地址:</span>
            <el-input v-model="addItem.address" placeholder="请输入内容" />
          </div>
          <div>
            <span>邮编:</span>
            <el-input v-model="addItem.postcode" placeholder="请输入内容" />
          </div>
          <div>
            <span>身份证号:</span>
            <el-input v-model="addItem.idnumber" placeholder="请输入内容" />
          </div>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleAdd">确 定</el-button>
        </span>
      </el-dialog>
    </div>
    <div class="tableBox">
      <el-table :data="tableData" stripe style="width: 100%">
        <el-table-column prop="name" label="姓名" align="center" />
        <el-table-column prop="phone" label="电话" align="center" width="120" />
        <el-table-column prop="area" label="区域" align="center">
          <!-- <template slot-scope="scope">
            <span>{{ handleCodeToText(scope.row.area) }}</span>
          </template> -->
        </el-table-column>
        <el-table-column
          prop="address"
          label="地址"
          align="center"
          width="180"
        />
        <el-table-column prop="postcode" label="邮编" align="center" />
        <el-table-column label="身份证号" align="center" width="180">
          <template slot-scope="scope">
            <div class="identityBox">
              <span>{{ scope.row.idnumber }}</span>
              <i
                :class="
                  scope.row.isconfirm
                    ? 'el-icon-circle-check'
                    : 'el-icon-circle-close'
                "
                :title="scope.row.isconfirm ? '证件已确认' : '证件号码未确认'"
              />
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="isdefault" label="是否默认" align="center">
          <template slot-scope="scope">
            <div>
              <span>{{ scope.row.isdefault ? '是' : '否' }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="operate" label="操作" align="center" width="60">
          <template slot-scope="scope">
            <div class="coopBox">
              <i
                class="el-icon-edit-outline"
                @click="handleClickEdit(scope.row)"
              />
              <el-dialog
                title="添加收件地址"
                :visible.sync="editDialogVisible"
                width="40%"
              >
                <div class="infoBox">
                  <div>
                    <span>姓名:</span>
                    <el-input
                      v-model="editItem.name"
                      placeholder="请输入内容"
                    />
                  </div>
                  <div>
                    <span>电话:</span>
                    <el-input
                      v-model="editItem.phone"
                      placeholder="请输入内容"
                    />
                  </div>
                  <div>
                    <span>区域:</span>
                    <el-cascader
                      v-model="editItem.area"
                      size="large"
                      :options="selectedAreaoptions"
                      placeholder="请选择收件区域"
                    />
                  </div>
                  <div>
                    <span>地址:</span>
                    <el-input
                      v-model="editItem.address"
                      placeholder="请输入内容"
                    />
                  </div>
                  <div>
                    <span>邮编:</span>
                    <el-input
                      v-model="editItem.postcode"
                      placeholder="请输入内容"
                    />
                  </div>
                  <div>
                    <span>身份证号:</span>
                    <el-input
                      v-model="editItem.idnumber"
                      placeholder="请输入内容"
                    />
                  </div>
                </div>
                <span slot="footer" class="dialog-footer">
                  <el-button
                    @click="editDialogVisible = false"
                  >取 消</el-button>
                  <el-button
                    type="primary"
                    @click="updateEdit"
                  >确 定</el-button>
                </span>
              </el-dialog>
              <i class="el-icon-delete" @click="handleRemove(scope.row.id)" />
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { regionData, CodeToText } from 'element-china-area-data'
import {
  getRecipient,
  saveRecipient,
  removeRecipient,
  updateRecipient
} from '@/api/recipient'
import { Message } from 'element-ui'

export default {
  data() {
    return {
      dialogVisible: false,
      editDialogVisible: false,
      selectedAreaoptions: regionData,
      tableData: [
        {
          name: '王小虎',
          tel: '18800008888',
          area: ['120000', '120100', '120103'],
          address: '金沙江路1518弄',
          postcode: '200000',
          identity: '200000199001010011',
          isconfirm: true,
          isdefault: true
        }
      ],
      addItem: {
        name: '',
        phone: '',
        area: '',
        address: '',
        postcode: '',
        idnumber: '',
        isconfirm: false,
        isdefault: false
      },
      editItem: {
        name: '',
        phone: '',
        area: '',
        address: '',
        postcode: '',
        idnumber: '',
        isconfirm: false,
        isdefault: false
      }
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      getRecipient()
        .then((res) => {
          if (res.code === 20000) {
            this.tableData = res.data
          }
        })
        .catch((error) => {
          Message({
            type: 'error',
            message: error
          })
        })
    },
    handleAdd() {
      this.dialogVisible = false
      this.addItem.area = this.handleCodeToText(this.addItem.area)
      saveRecipient(this.addItem).then((response) => {
        if (response.code === 20000) {
          Message({
            type: 'success',
            message: '添加成功'
          })
          this.getData()
          Object.keys().map((e) => (this.addItem[e] = ''))
        }
      })
    },
    handleClickEdit(item) {
      console.log(item)
      // item.area = item.area.map((o) => CodeToText[o])
      this.editDialogVisible = true
      this.editItem = item
    },
    updateEdit() {
      this.editDialogVisible = false
      updateRecipient(this.editItem)
        .then((res) => {
          if (res.code === 20000) {
            Message({
              type: 'success',
              message: '修改成功'
            })
            this.getData()
          }
        })
        .catch((err) => {
          Message({
            type: 'error',
            message: err
          })
        })
    },
    handleCodeToText(arr) {
      return arr.map((s) => CodeToText[s]).join('/')
    },
    handleRemove(id) {
      removeRecipient(id)
        .then((res) => {
          if (res.code === 20000) {
            Message({
              type: 'success',
              message: '已删除'
            })
            this.getData()
          }
        })
        .catch((err) => {
          Message({
            type: 'error',
            message: err
          })
        })
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  padding: 2rem 0;
}
.coopBox {
  font-size: 2rem;
}
.el-icon-circle-check {
  color: green;
}
.el-icon-circle-close {
  color: red;
}
.infoBox {
  width: 100%;
  position: relative;
  display: flex;
  flex-wrap: wrap;
  div {
    display: flex;
    width: 50%;
    justify-content: space-around;
    align-items: center;
    margin: 5px 0;
    span {
      width: 60px;
      text-align: center;
    }
    div {
      width: 80%;
    }
  }
}
.identityBox {
  i {
    cursor: pointer;
  }
}
.coopBox {
  display: flex;
  justify-content: space-around;
  i {
    &:hover {
      cursor: pointer;
    }
  }
}
</style>
