<template>
  <div class="container">
    <div class="tableBox">
      <el-table :data="orderList" stripe style="width: 100%" height="1000px">
        <el-table-column
          prop="create_time"
          label="日期"
          width="100"
          align="center"
        />
        <el-table-column
          prop="order_number"
          label="订单号"
          width="180"
          align="center"
        />
        <el-table-column
          prop="address"
          label="收货人"
          align="center"
          width="80"
        >
          <template slot-scope="scope">
            {{ scope.row.address.split(' ')[0] }}
          </template>
        </el-table-column>
        <el-table-column
          prop="address"
          label="联系电话"
          align="center"
          width="120"
        >
          <template slot-scope="scope">
            {{ scope.row.address.split(' ')[1] }}
          </template></el-table-column>
        <el-table-column prop="address" label="收货地址" align="center">
          <template slot-scope="scope">
            {{ scope.row.address.split(' ')[2].replaceAll('/', '') }}
          </template></el-table-column>
        <el-table-column
          prop="real_price"
          label="支付金额"
          align="center"
          width="120"
        />
        <el-table-column prop="status" label="状态" align="center" width="80">
          <template slot-scope="scope">
            <span
              :class="
                scope.row.status == 0
                  ? 'pedding'
                  : scope.row.status == 1
                    ? 'resolve'
                    : 'reject'
              "
              v-text="statusTotext(scope.row.status)"
            />
          </template>
        </el-table-column>
        <el-table-column label="查看详情" align="center" width="80">
          <template slot-scope="scope">
            <el-button
              type="info"
              size="mini"
              @click="handleClickDetail(scope.row)"
            >详情</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { queryUserOrders, orderStatus } from '@/api/order'

export default {
  data() {
    return {
      orderList: []
    }
  },
  created() {
    this.getData()
  },
  methods: {
    statusTotext(index) {
      return orderStatus(index)
    },
    getData() {
      queryUserOrders().then((res) => {
        if (res.data) {
          this.orderList = res.data.reverse().filter((o) => o.ptype === 'alipay')
          // console.log(this.orderList)
        }
      })
    },
    handleClickDetail(item) {
      console.log(item)
      if (item.status === '0') {
        console.log('进入支付页面')
        this.$router.push(`/webapp/pay/${item.order_number}`)
      } else {
        console.log('进入第4环节')
        this.$router.push(`/webapp/finishorder/${item.order_number}`)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  width: 100%;
  height: 100%;
}
.pedding {
  color: #e74c3c;
}
.resolve {
  color: #2ecc71;
}
.reject {
  color: #95a5a6;
}
</style>
