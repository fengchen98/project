<template>
  <div class="container">
    <div class="btnBox">
      <el-button
        type="primary"
        @click="dialogVisible = true"
      >添加寄件地址</el-button>
      <el-dialog title="添加寄件地址" :visible.sync="dialogVisible" width="40%">
        <div class="infoBox">
          <div>
            <span>姓名:</span>
            <el-input v-model="addItem.name" placeholder="请输入内容" />
          </div>
          <div>
            <span>电话:</span>
            <el-input v-model="addItem.tel" placeholder="请输入内容" />
          </div>
          <div>
            <span>区域:</span>
            <el-cascader
              v-model="addItem.area"
              size="large"
              :options="selectedAreaoptions"
              placeholder="请选择寄件区域"
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
            <el-input v-model="addItem.identity" placeholder="请输入内容" />
          </div>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button
            type="primary"
            @click="dialogVisible = false"
          >确 定</el-button>
        </span>
      </el-dialog>
    </div>
    <div class="tableBox">
      <el-table :data="tableData" stripe style="width: 100%">
        <el-table-column prop="name" label="姓名" align="center" />
        <el-table-column prop="tel" label="电话" align="center" width="120" />
        <el-table-column prop="area" label="区域" align="center">
          <template slot-scope="scope">
            <span>{{ handleCodeToText(scope.row.area) }}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="address"
          label="地址"
          align="center"
          width="180"
        />
        <el-table-column prop="postcode" label="邮编" align="center" />
        <el-table-column
          prop="identity"
          label="身份证号"
          align="center"
          width="180"
        >
          <template slot-scope="scope">
            <div class="identityBox">
              <span>{{ scope.row.identity }}</span>
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
                title="添加寄件地址"
                :visible.sync="edutDialogVisible"
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
                    <el-input v-model="editItem.tel" placeholder="请输入内容" />
                  </div>
                  <div>
                    <span>区域:</span>
                    <el-cascader
                      v-model="editItem.area"
                      size="large"
                      :options="selectedAreaoptions"
                      placeholder="请选择寄件区域"
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
                      v-model="editItem.identity"
                      placeholder="请输入内容"
                    />
                  </div>
                </div>
                <span slot="footer" class="dialog-footer">
                  <el-button
                    @click="edutDialogVisible = false"
                  >取 消</el-button>
                  <el-button
                    type="primary"
                    @click="edutDialogVisible = false"
                  >确 定</el-button>
                </span>
              </el-dialog>
              <i class="el-icon-delete" />
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { regionData, CodeToText } from 'element-china-area-data'

export default {
  data() {
    return {
      dialogVisible: false,
      edutDialogVisible: false,
      selectedAreaoptions: regionData,
      tableData: [
        {
          name: '张小红',
          tel: '18088880000',
          area: ['130000', '130100', '130101'],
          address: '北大街150号',
          postcode: '130000',
          identity: '200000199606160016',
          isconfirm: true,
          isdefault: true
        }
      ],
      addItem: {
        name: '',
        tel: '',
        area: '',
        address: '',
        postcode: '',
        identity: '',
        isconfirm: true,
        isdefault: true
      },
      editItem: {
        name: '',
        tel: '',
        area: '',
        address: '',
        postcode: '',
        identity: '',
        isconfirm: true,
        isdefault: true
      }
    }
  },
  methods: {
    handleClickEdit(item) {
      console.log(item)
      // item.area = item.area.map((o) => CodeToText[o])
      this.edutDialogVisible = true
      this.editItem = item
    },
    handleCodeToText(arr) {
      return arr.map((s) => CodeToText[s]).join('/')
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
