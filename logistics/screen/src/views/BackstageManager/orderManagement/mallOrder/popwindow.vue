<template>
  <div class="container">
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
            <div v-if="orderObj.status === '1'">
              <p
                :class="
                  orderObj.status == 0
                    ? 'pedding'
                    : orderObj.status == 1
                      ? 'resolve'
                      : 'reject'
                "
              >
                订单已支付
              </p>
            </div>
            <div v-else>
              <p class="cancelText">
                订单已于<span v-text="orderObj.finish_time" />取消
              </p>
            </div>
            <!-- <button>取消订单</button> -->
          </div>
        </div>
      </div>
      <div v-if="orderNumber" class="row-2">
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
            <span>收货人: </span><span
              v-text="orderObj.address ? orderObj.address.split(' ')[0] : null"
            />
          </div>
          <div>
            <span>联系电话: </span><span
              v-text="orderObj.address ? orderObj.address.split(' ')[1] : null"
            />
          </div>
          <div>
            <span>收货地址: </span><span
              v-text="orderObj.address ? orderObj.address.split(' ')[2] : null"
            />
          </div>
          <div>
            <span>下单时间: </span><span v-text="orderObj.create_time || null" />
          </div>
          <div>
            <span>订单总价: </span><span v-text="orderObj.total_price + '元' || null" />
          </div>
          <div>
            <span>支付时间: </span><span v-text="orderObj.pay_time || '无'" />
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
          <div>
            <span>订单备注: </span><span v-text="orderObj.remark || '无'" />
          </div>
          <div v-if="orderObj.status === '2'">
            <span>取消时间: </span><span v-text="orderObj.finish_time" />
          </div>
        </div>
      </div>
      <div v-if="orderObj.ptype !== 'recharge' && orderNumber" class="row-3">
        <p class="row-title">订单详情</p>
        <!-- 商品列表 -->
        <div class="detailTable">
          <el-table :data="orderDetailList" stripe style="width: 100%">
            <el-table-column prop="date" label="选购商品信息" width="500">
              <template slot-scope="scope">
                <div class="goodsIntro">
                  <img :src="scope.row.imgurl" alt="">
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
      <div v-if="orderObj.ptype !== 'recharge' && orderNumber" class="row-4">
        <p class="row-title">物流信息</p>
        <div>
          <el-steps direction="vertical">
            <el-step
              title="待发货"
              icon="el-icon-circle-plus"
              description="等待商家发货"
            />
            <!-- <el-step title="步骤 2" icon="el-icon-circle-plus" />
            <el-step
              title="步骤 3"
              description="这是一段很长很长很长的描述性文字"
               icon="el-icon-circle-plus"
            /> -->
          </el-steps>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { queryOrder, orderStatus, queryOrderDetail } from '@/api/order'
import axios from 'axios'
import { Message } from 'element-ui'

export default {
  props: {
    orderNumber: {
      type: String,
      default: () => ''
    }
  },
  data() {
    return {
      orderObj: {},
      orderDetailList: []
    }
  },
  watch: {
    orderNumber(newValue, oldValue) {
      // console.log('进入watch')
      if (oldValue !== newValue && newValue) {
        // console.log('触发watch')
        this.getData()
      }
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      const fromdata = new FormData()
      // console.log('执行getData', this.orderNumber)
      fromdata.append('orderNumber', this.orderNumber)
      axios
        .all([queryOrder(fromdata), queryOrderDetail(fromdata)])
        .then((res) => {
          if (res[0].data) {
            this.orderObj = res[0].data
          } else {
            Message.error('未查询到订单:', this.orderNumber)
            this.orderObj = {}
          }
          this.orderDetailList = res[1].data
          // console.log('订单详情', this.orderDetailList)
        })
    },
    statusTotext(index) {
      return orderStatus(index)
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  width: 100%;
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
  .row-4 {
    margin-top: 2rem;
    padding: 1rem;
    border: 1px solid #bdc3c7;
    border-top: 0.5rem solid #2980b9;
  }
}
.el-steps {
  .el-step__icon-inner {
    font-size: 5px !important;
  }
}
</style>
