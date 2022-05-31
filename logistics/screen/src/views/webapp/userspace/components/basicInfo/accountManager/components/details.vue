<template>
  <div class="container">
    <div class="searchBox">
      <div>
        <el-radio v-model="searchDateReach" label="all">全部</el-radio>
        <el-radio v-model="searchDateReach" label="currentDay">本日</el-radio>
        <el-radio v-model="searchDateReach" label="currentWeek">本周</el-radio>
        <el-radio v-model="searchDateReach" label="currentMonth">本月</el-radio>
        <el-radio v-model="searchDateReach" label="currentYear">本年</el-radio>
      </div>
      <div class="block">
        <el-date-picker
          v-model="searchDateReach"
          type="daterange"
          align="right"
          unlink-panels
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :picker-options="pickerOptions"
        />
      </div>
    </div>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column
        prop="create_time"
        label="日期"
        width="180"
        align="center"
      />
      <el-table-column
        prop="real_price"
        label="金额"
        width="180"
        align="center"
      />
      <el-table-column prop="balance" label="余额" align="center" />
      <!-- <el-table-column prop="method" label="支付方式" align="center" /> -->
      <el-table-column prop="ptype" label="消费类型" align="center">
        <template slot-scope="scope">
          {{ scope.row.ptype === 'recharge' ? '余额充值' : '余额消费' }}
        </template>
      </el-table-column>
      <el-table-column prop="remark" label="消费备注" align="center" />
    </el-table>
  </div>
</template>

<script>
import { queryUserOrders } from '@/api/order'

export default {
  data() {
    return {
      tableData: [
        {
          date: '2016-05-02 19:23:05',
          amount: '59.99',
          balance: '40.01',
          method: '余额支付',
          type: '商城消费',
          remark: '商城购买2罐奶粉'
        }
      ],
      pickerOptions: {
        shortcuts: [
          {
            text: '最近一周',
            onClick(picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
              picker.$emit('pick', [start, end])
            }
          },
          {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
              picker.$emit('pick', [start, end])
            }
          },
          {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
              picker.$emit('pick', [start, end])
            }
          }
        ]
      },
      searchDateReach: ''
    }
  },
  watch: {
    searchDateReach(newValue, oldValue) {
      console.log(newValue)
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      queryUserOrders().then((res) => {
        if (res.data) {
          this.tableData = res.data
            .reverse()
            .filter((o) => o.ptype !== 'alipay' && o.status === '1')
          // console.log(this.orderList)
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.searchBox {
  width: 100%;
  padding: 1rem 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
