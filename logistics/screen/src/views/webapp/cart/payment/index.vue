<template>
  <div class="container">
    <!-- 过程提示 -->
    <div class="step">
      <el-steps :active="3" align-center>
        <el-step
          title="购物车"
          icon="el-icon-shopping-cart-full"
          description=""
        />
        <el-step title="核对信息" icon="el-icon-s-order" description="" />
        <el-step title="支付订单" icon="el-icon-bank-card" description="" />
        <el-step title="完成支付" icon="el-icon-circle-check" description="" />
      </el-steps>
    </div>
    <hr>
    <div class="orderBox">
      <div class="row-1">
        <div class="row-1-box">
          <div class="left">
            <p v-text="'订单号: ' + orderObj.order_number" />
            <p
              :class="
                orderObj.status == 0
                  ? 'pedding'
                  : orderObj.status == 1
                    ? 'resolve'
                    : 'reject'
              "
              v-text="statusTotext(orderObj.status)"
            />
            <p v-text="orderObj.create_time" />
          </div>
          <div class="right">
            <div v-if="orderObj.status === '0'">
              <button class="payBtn" @click="handlePayOrder">立即支付</button>
              <el-popconfirm
                title="确认取消订单？"
                @onConfirm="handleCancelOrder"
              >
                <button slot="reference" class="cancelBtn">取消订单</button>
              </el-popconfirm>
            </div>
            <div v-else>
              <div v-if="orderObj.status === '1'">
                <p class="cancelText" style="color: #2ecc71">订单已支付</p>
              </div>
              <p v-else class="cancelText">
                订单已于<span v-text="orderObj.finish_time" />取消
              </p>
            </div>
            <!-- <button>取消订单</button> -->
          </div>
        </div>
      </div>
      <div class="row-2">
        <p class="row-title">订单信息</p>
        <div class="orderInfoBox">
          <div>
            <span>订单编号: </span><span v-text="orderObj.order_number" />
          </div>
          <div>
            <span>订单名称: </span><span v-text="orderObj.order_name" />
          </div>
          <div>
            <span>订单描述: </span><span v-text="orderObj.order_desc" />
          </div>
          <div><span>下单用户: </span><span v-text="orderObj.user_name" /></div>
          <div>
            <span>收货人: </span><span v-text="orderObj.address.split(' ')[0]" />
          </div>
          <div>
            <span>联系电话: </span><span v-text="orderObj.address.split(' ')[1]" />
          </div>
          <div>
            <span>收货地址: </span><span v-text="orderObj.address.split(' ')[2]" />
          </div>
          <div>
            <span>下单时间: </span><span v-text="orderObj.create_time" />
          </div>
          <div>
            <span>订单总价: </span><span v-text="orderObj.total_price + '元'" />
          </div>
          <div>
            <span>订单运费: </span><span v-text="orderObj.freight_price + '元'" />
          </div>
          <div>
            <span>优惠金额: </span><span v-text="orderObj.discount + '元'" />
          </div>
          <div>
            <span>支付金额: </span><span v-text="orderObj.real_price + '元'" />
          </div>
          <div><span>订单备注: </span><span v-text="orderObj.remark" /></div>
          <div v-if="orderObj.status === '2'">
            <span>取消时间: </span><span v-text="orderObj.finish_time" />
          </div>
        </div>
      </div>
      <div class="row-3">
        <p class="row-title">订单详情</p>
        <!-- 商品列表 -->
        <div class="detailTable">
          <el-table :data="orderDetailList" stripe style="width: 100%">
            <el-table-column prop="date" label="选购商品信息" width="500">
              <template slot-scope="scope">
                <div class="goodsIntro">
                  <img :src="scope.row.images[0]" alt="">
                  <p v-text="scope.row.name" />
                </div>
              </template>
            </el-table-column>
            <el-table-column prop="unitprice" label="单价" align="center" />
            <el-table-column prop="amount" label="数量" align="center" />
            <el-table-column prop="totalprice" label="金额" align="center" />
          </el-table>
        </div>
      </div>
      <div>
        <el-dialog
          title="支付宝扫码支付"
          :visible.sync="payWindowVisible"
          width="350px"
        >
          <payWindow :imgsrc="imgSrc" @checkStatus="checkStatus" />
        </el-dialog>
      </div>
      <div>
        <el-dialog
          :visible.sync="checkStatusVisible"
          width="300px"
          center
          top="40vh"
        >
          <div class="checkStatusBox">
            <i v-if="orderObj.status === '0'" class="el-icon-loading" />
            <i
              v-if="orderObj.status === '1'"
              class="el-icon-circle-check"
              style="font-size: 150px; color: #2ecc71"
            />
            <span v-text="checkStatusMessage" />
          </div>
        </el-dialog>
      </div>
    </div>
  </div>
</template>

<script>
import {
  queryOrder,
  orderStatus,
  queryOrderDetail,
  cancelOrder
} from '@/api/order'
import { Message } from 'element-ui'
import { stringToArray } from '@/utils/index'
import request from '@/utils/request'
import axios from 'axios'

export default {
  //   beforeRouteEnter(to, from, next) {
  //     next((vm) => {
  //       if (/(webapp\/order)[0-9]*/.test(from.fullPath)) {
  //         next()
  //       } else {
  //         vm.$router.push('/webapp/cart')
  //       }
  //     })
  //   },
  components: {
    payWindow: () => import('./payWindow.vue')
  },
  props: {
    orderNumber: {
      type: String,
      default: () => ''
    }
  },
  data() {
    return {
      orderObj: {},
      orderDetailList: [],
      payWindowVisible: false,
      checkStatusVisible: false,
      checkStatusMessage: '正在检查订单支付状态...',
      imgSrc: '',
      timer: null
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      const fromdata = new FormData()
      fromdata.append('orderNumber', this.orderNumber)
      axios
        .all([queryOrder(fromdata), queryOrderDetail(fromdata)])
        .then((res) => {
          this.orderObj = res[0].data
          if (this.orderObj.status !== '0') {
            // console.log(this.orderObj.status)
            this.$router.push(`/webapp/finishorder/${this.orderNumber}`)
          }
          this.orderDetailList = res[1].data
          this.orderDetailList.forEach((o) => {
            o.images = stringToArray(o.images || [], request.defaults.baseURL)
          })
          console.log(this.orderDetailList)
        })
    },
    statusTotext(index) {
      return orderStatus(index)
    },
    checkStatus(arg) {
      if (arg === 0) {
        let count = 10
        const fromdata = new FormData()
        fromdata.append('orderNumber', this.orderNumber)
        this.payWindowVisible = false
        this.checkStatusVisible = true
        this.timer = setInterval(() => {
          queryOrder(fromdata).then((res) => {
            if (this.checkStatusVisible === false) {
              clearInterval(this.timer)
            }
            if (res.data.status === '1') {
              this.orderObj = res.data
              // this.checkStatusMessage = '订单支付完成'
              this.checkStatusMessage = ''
              setTimeout(() => {
                clearInterval(this.timer)
                window.location.reload()
                this.checkStatusVisible = false
                //   this.$router.push(
                //     `/webapp/finishorder/${this.orderObj.order_number}`
                //   )
              }, 2000)
            }
            if (count <= 0) {
              this.checkStatusMessage = '订单检查超时'
              this.checkStatusVisible = false
              setTimeout(() => {
                clearInterval(this.timer)
              }, 2000)
              window.location.reload()
            }
            count--
          })
        }, 1000)
      } else {
        this.orderObj = arg
        this.payWindowVisible = false
        this.checkStatusVisible = true
        this.checkStatusMessage = ''
        setTimeout(() => {
          clearInterval(this.timer)
          window.location.reload()
          this.checkStatusVisible = false
          //   this.$router.push(
          //     `/webapp/finishorder/${this.orderObj.order_number}`
          //   )
        }, 2000)
      }
    },
    handlePayOrder() {
      this.imgSrc = `${request.defaults.baseURL}alipay/pay/${this.orderObj.order_number}`
      //   this.imgSrc = `${request.defaults.baseURL}alipay/payDemo`
      this.payWindowVisible = true
    },
    handleCancelOrder() {
      const fromdata = new FormData()
      fromdata.append('orderNumber', this.orderNumber)
      cancelOrder(fromdata).then((res) => {
        if (res.code === 20000) {
          Message.success('订单已取消')
          window.location.reload()
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  width: 60%;
  margin: 2rem auto;
}
.step {
  width: 100%;
  margin: 5rem auto 3rem;
}
.pedding {
  font-size: 1.5rem;
  font-weight: 600;
  color: #e74c3c;
}
.resolve {
  font-size: 1.5rem;
  font-weight: 600;
  color: #2ecc71;
}
.reject {
  font-size: 1.5rem;
  font-weight: 600;
  color: #95a5a6;
}
hr {
  border: none;
  width: 100%;
  margin: 0 auto;
  border-bottom: 1px solid grey;
}
.orderBox {
  width: 100%;
  display: flex;
  flex-direction: column;
  margin-top: 1rem;
  .row-title {
    font-size: 1.5rem;
    font-weight: 600;
  }
  .row-1 {
    padding: 1rem;
    border: 1px solid #bdc3c7;
    border-top: 0.5rem solid #2980b9;
    .row-1-box {
      display: flex;
      justify-content: space-around;
      .left {
        width: 40%;
        display: flex;
        flex-direction: column;
        align-items: center;
        .order-status {
          font-size: 1.5rem;
          font-weight: 600;
          &.on {
            color: #2ecc71;
          }
          &.off {
            color: #e74c3c;
          }
        }
      }
      .right {
        width: 60%;
        display: flex;
        justify-content: center;
        align-items: center;
        button {
          color: #fff;
          padding: 0.5rem 1rem;
          border-radius: 5px;
          // border: none;
          &.payBtn {
            background-color: rgba(192, 57, 43, 0.8);
          }
          &.cancelBtn {
            margin-left: 5rem;
            background-color: rgba(127, 140, 141, 1);
          }
        }
        .cancelText {
          font-size: 1.5rem;
          font-weight: 600;
          color: #9ca3a8ee;
        }
      }
    }
  }
  .row-2 {
    margin-top: 2rem;
    padding: 1rem;
    border: 1px solid #bdc3c7;
    border-top: 0.5rem solid #2980b9;
    .orderInfoBox {
      display: flex;
      flex-wrap: wrap;
      div {
        width: 50%;
        display: flex;
        justify-content: flex-start;
        span {
          font-size: 1.3rem;
          line-height: 2rem;
          &:nth-child(1) {
            text-align: right;
            width: 15%;
            justify-self: self-end;
          }
          &:nth-child(2) {
            margin-left: 1rem;
          }
        }
      }
    }
  }
  .row-3 {
    margin-top: 2rem;
    padding: 1rem;
    border: 1px solid #bdc3c7;
    border-top: 0.5rem solid #2980b9;
    .detailTable {
      .goodsIntro {
        width: 100%;
        display: flex;
        img {
          width: 80px;
          height: 80px;
          margin-right: 2rem;
        }
      }
    }
  }
}
.checkStatusBox {
  display: flex;
  justify-content: center;
  font-size: 1.5rem;
  font-weight: 600;
  i {
    margin-right: 1rem;
  }
}
</style>
