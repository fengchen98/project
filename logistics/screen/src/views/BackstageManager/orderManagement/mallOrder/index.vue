<template>
  <div class="container">
    <div class="searchBox">
      <div class="searchItem">
        <p>订单类型</p>
        <el-select
          v-model="defaultPayOption"
          placeholder="请选择"
          @change="changeShowData"
        >
          <el-option
            v-for="item in payOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </div>
      <div class="searchItem">
        <p>订单状态</p>
        <el-select
          v-model="defaultStatusOption"
          placeholder="请选择"
          @change="changeShowData"
        >
          <el-option
            v-for="item in statusOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </div>
      <div class="searchItem">
        <p>下单账号</p>
        <el-input v-model="defaultAccount" @change="changeShowData" />
      </div>
    </div>
    <div class="tableBox">
      <el-table :data="showList" style="width: 100%">
        <el-table-column label="序号" width="80" align="center">
          <template slot-scope="scope">
            {{ scope.$index }}
          </template>
        </el-table-column>
        <el-table-column
          label="下单时间"
          prop="create_time"
          align="center"
          width="180"
        >
          <template slot-scope="scope">
            {{ scope.row.create_time.slice(0, -2) }}
          </template>
        </el-table-column>
        <el-table-column
          label="订单编号"
          prop="order_number"
          align="center"
          width="360"
        />
        <el-table-column
          label="下单账号"
          prop="user_name"
          align="center"
          width="180"
        />
        <el-table-column label="订单状态" align="center" width="120">
          <template slot-scope="scope">
            <span
              :class="statusToClass(scope.row.status)"
              v-text="statusToText(scope.row.status)"
            />
          </template>
        </el-table-column>
        <el-table-column
          label="实际付款"
          prop="real_price"
          align="center"
          width="120"
        />
        <el-table-column label="订单类型" prop="ptype" align="center">
          <template slot-scope="scope">
            {{
              scope.row.ptype === 'alipay'
                ? '支付宝支付'
                : scope.row.ptype === 'recharge'
                  ? '充值余额'
                  : '余额支付'
            }}
          </template>
        </el-table-column>
        <el-table-column label="订单备注" prop="remark" align="center" />
        <el-table-column label="操作" align="center" width="150">
          <template slot-scope="scope">
            <el-button
              class="coopBtn"
              type="primary"
              @click="handleClickDetailBtn(scope.row)"
            >订单详情</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div>
      <el-dialog title="订单详情" :visible.sync="popWindowVisible" width="60%">
        <popWindow :order-number="currentOrderNumber" />
      </el-dialog>
    </div>
  </div>
</template>

<script>
import { allOrder, orderStatus } from '@/api/order'

export default {
  components: {
    popWindow: () => import('./popwindow.vue')
  },
  data() {
    return {
      orderList: [],
      showList: [],
      defaultPayOption: '',
      payOptions: [
        {
          value: '',
          label: '所有订单'
        },
        {
          value: 'alipay',
          label: '支付宝支付'
        },
        {
          value: 'recharge',
          label: '余额充值'
        },
        {
          value: 'balancePay',
          label: '余额支付'
        }
      ],
      defaultStatusOption: '',
      statusOptions: [
        {
          value: '',
          label: '所有订单'
        },
        {
          value: '0',
          label: '未支付'
        },
        {
          value: '1',
          label: '已支付'
        },
        {
          value: '2',
          label: '已取消'
        }
      ],
      defaultAccount: '',
      popWindowVisible: false,
      currentOrderNumber: ''
    }
  },
  created() {
    this.getData()
  },
  methods: {
    changeShowData() {
      this.showList = this.orderList.filter(
        (o) =>
          o.ptype.includes(this.defaultPayOption) &&
          o.status.includes(this.defaultStatusOption) &&
          o.user_name.includes(this.defaultAccount)
      )
    },
    statusToText(index) {
      return orderStatus(index)
    },
    statusToClass(index) {
      switch (index) {
        case '0':
          return 'pedding'
        case '1':
          return 'resolve'
        case '2':
          return 'reject'
      }
    },
    getData() {
      allOrder().then((res) => {
        if (res.code === 20000) {
          this.orderList = res.data
          this.showList = this.orderList.reverse()
        }
      })
    },
    // handleClickBtn(item) {
    //   const formData = new FormData()
    //   formData.append('orderNumber', item.order_number)
    //   queryOrder(formData).then((res) => {
    //     if (res.code === 20000) {
    //       console.log(res.data)
    //     }
    //   })
    // },
    handleClickDetailBtn(item) {
      console.log('点击编辑按钮: ', item.order_number)
      this.currentOrderNumber = item.order_number
      this.popWindowVisible = true
    }
  }
}
</script>

<style lang="scss" scoped>
.searchBox {
  padding: 2rem;
  padding-bottom: 0;
  display: flex;
  .searchItem {
    display: flex;
    align-items: center;
    margin-right: 2rem;
    p {
      margin: 0;
      padding: 0;
      // width: 10rem;
      font-size: 1.5rem;
      font-weight: 600;
      margin-right: 1rem;
    }
    &:nth-child(3) {
      p {
        width: 10rem;
      }
    }
  }
}
.tableBox {
  padding: 2rem;
  .pedding {
    color: #e74c3c;
  }
  .resolve {
    color: #2ecc71;
  }
  .reject {
    color: #7f8c8d;
  }
  .pedding,
  .resolve,
  .reject {
    font-weight: 600;
  }
}
.coopBtn {
  padding: 0.5rem 1rem;
}
</style>
