<template>
  <div class="container">
    <div class="balanceBox">
      <span>您目前的账户余额: </span>
      <div>
        <span class="number" v-text="accountinfo.balance" /><span>英镑</span>
      </div>
    </div>
    <div class="commissionBox">
      <span>您目前的返佣余额:
        <el-popover
          placement="top-start"
          title=""
          width="220"
          trigger="hover"
          content="返佣余额到达20镑后可申请提现"
        >
          <i slot="reference" class="el-icon-warning-outline" />
        </el-popover>
      </span>

      <div>
        <span class="number" v-text="accountinfo.rebate" /><span>英镑</span>
      </div>
    </div>
    <div class="rechargeBOx">
      <span>充值金额:</span>
      <div class="buttonBox">
        <button
          v-for="li in buttonLists"
          :key="li.amount"
          @click="handleClickButton(li.amount, $event)"
          v-text="li.msg"
        />
      </div>
      <div>
        <span>充值金额:</span><el-input
          v-model="amount.num"
          size="mini"
          :disabled="amount.edit ? true : false"
        />
      </div>
      <button class="rechargeBtn" @click="handleRecharge">去充值</button>
    </div>
    <el-dialog
      title="支付宝扫码充值"
      :visible.sync="rechargeDialogVisible"
      width="25%"
    >
      <div class="rechargePopWindow">
        <img v-if="status === '0'" :src="imgSrc">
        <span v-if="status === '0'" v-text="'请在' + count + '秒内支付'" />
        <i
          v-if="status === '1'"
          class="el-icon-circle-check"
          style="color: #2ecc71; font-size: 20rem"
        />
        <span v-if="status === '2'" v-text="订单已取消" />
      </div>
      <!-- <span slot="footer" class="dialog-footer"> 111 </span> -->
    </el-dialog>
  </div>
</template>

<script>
import { Message } from 'element-ui'
import { mapGetters } from 'vuex'
import { createRechangeOrder, queryOrder } from '@/api/order'
import request from '@/utils/request'

export default {
  data() {
    return {
      rechargeDialogVisible: false,
      buttonLists: [
        {
          amount: 100,
          msg: '100 英镑'
        },
        {
          amount: 200,
          msg: '200 英镑'
        },
        {
          amount: 300,
          msg: '300 英镑'
        },
        {
          amount: 500,
          msg: '500 英镑'
        },
        {
          amount: 0,
          msg: '自定义'
        }
      ],
      amount: {
        edit: true,
        num: 0
      },
      orderNumber: null,
      count: 0,
      status: '0'
    }
  },
  computed: {
    ...mapGetters(['accountinfo']),
    imgSrc() {
      return request.defaults.baseURL + `/alipay/pay/${this.orderNumber}`
    }
  },
  mounted() {
    setTimeout(() => {
      document.querySelector('.buttonBox').querySelector('button').click()
    }, 50)
  },
  methods: {
    handleClickButton(amount, event) {
      document
        .querySelectorAll('button')
        .forEach((el) => el.classList.remove('active'))
      event.currentTarget.classList.add('active')
      if (amount === 0) {
        this.amount.edit = false
      } else {
        this.amount.edit = true
        this.amount.num = amount
        // console.log(this.amount)
      }
    },
    handleRecharge() {
      const formData = new FormData()
      formData.append('money', this.amount.num)
      createRechangeOrder(formData).then((response) => {
        Message.success('充值订单创建成功')
        this.orderNumber = response.data
        const formData = new FormData()
        formData.append('orderNumber', this.orderNumber)
        this.rechargeDialogVisible = true
        this.count = 60
        const timer = setInterval(() => {
          // 倒计时结束停止请求
          if (this.count === 0) {
            clearInterval(timer)
          }
          // 窗口关闭停止请求
          if (this.rechargeDialogVisible === false) {
            clearInterval(timer)
          }
          queryOrder(formData).then((res) => {
            if (res.data.status === '1') {
              this.status = res.data.status
              clearInterval(timer)
              setTimeout(() => {
                this.rechargeDialogVisible = false
                window.location.reload()
              }, 2000)
            }
          })
          this.count--
        }, 1000)
      })
      // Message({
      //   message: `充值金额为${this.amount.num}`,
      //   type: 'warning',
      //   duration: 1000,
      //   customClass: 'message-override'
      // })
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  padding: 1rem;
  display: flex;
  flex-direction: column;
  font-size: 1.5rem;
  color: #7f8c8d;
  .balanceBox,
  .commissionBox {
    div {
      margin: 2rem 0;
      span {
        &:nth-child(2) {
          font-size: 1.5rem;
          font-weight: 600;
          color: #2c3e50;
        }
      }
    }
  }
  .rechargeBOx {
    span {
      width: 7rem;
    }
    div {
      &:nth-child(3) {
        display: flex;
        align-items: center;
        .el-input {
          width: 80px;
        }
      }
    }
    .buttonBox {
      padding: 2rem 0;
      width: 100%;
      display: flex;
      //   justify-content: space-around;
      button {
        width: 130px;
        height: 80px;
        background-color: transparent;
        border: 1px solid #bdc3c7;
        margin-right: 2rem;
        &.active {
          border: 1px solid #d35400;
          //   &::before {
          //     content: '√';
          //     color: #fff;
          //     position: absolute;
          //     width: 0;
          //     height: 0;
          //     border-top: 2rem solid transparent;
          //     border-left: 2rem solid transparent;
          //     border-right: 2rem solid #d35400;
          //     border-bottom: 2rem solid #d35400;
          //     transform: translate(calc(100% + 1.5rem), .8rem);
          //   }
        }
      }
    }
    .rechargeBtn {
      border: none;
      margin: 2rem 0;
      width: 20rem;
      height: 4rem;
      color: #fff;
      border-radius: 5px;
      background-color: #e67e22;
    }
  }
}
.number {
  font-size: 5rem;
  font-weight: 400;
  color: #f39c12;
}
.message-override {
  z-index: 10000;
}
.rechargePopWindow {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
}
</style>
