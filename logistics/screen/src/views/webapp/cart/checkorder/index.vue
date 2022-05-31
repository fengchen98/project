<template>
  <div class="container">
    <!-- 过程提示 -->
    <div class="step">
      <el-steps :active="2" align-center>
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
    <div class="mainBox">
      <!-- 收货地址 -->
      <!-- <div class="selectAddress">
        <p>选择收获地址</p>
        <el-table
          :data="addressLists"
          stripe
          style="width: 100%"
          @selection-change="handleSelectionPacks"
        >
          <el-table-column label="#" width="55">
            <template slot-scope="scope">
              <el-radio v-model="selectAddress" :label="scope.$index" />
            </template>
          </el-table-column>
          <el-table-column
            prop="name"
            label="收货人姓名"
            align="center"
            width="100"
          />
          <el-table-column
            prop="phone"
            label="手机号"
            align="center"
            width="150"
          />
          <el-table-column
            prop="idnumber"
            label="身份证号"
            align="center"
            width="180"
          />
          <el-table-column prop="area" label="地区" align="center" />
          <el-table-column prop="address" label="详细地址" align="center" />
          <el-table-column prop="name" label="操作" align="center" width="240">
            <template slot-scope="scope" align="center">
              <div class="coopBox">
                <el-button
                  size="mini"
                  @click="setAddressDefault(scope.row.id)"
                >设为默认</el-button>
              </div>
            </template>
          </el-table-column>
        </el-table>
      </div> -->
      <!-- 商品列表 -->
      <div class="summary">
        <!-- <p v-text="'(' + goodsInfo.total + ')'" /> -->
        <p>商品信息</p>
      </div>
      <!-- 商品列表 -->
      <div class="table1">
        <el-table
          :data="initData"
          stripe
          style="width: 100%"
          @selection-change="handleSelectionGoods"
        >
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
      <!-- 附加产品列表 -->
      <div class="table1">
        <el-table
          :data="boxData"
          stripe
          style="width: 100%"
          @selection-change="handleSelectionGoods"
        >
          <el-table-column prop="date" label="附加产品列表" width="500">
            <template slot-scope="scope">
              <div class="goodsIntro">
                <img :src="scope.row.imgurl" alt="">
                <p v-text="scope.row.title" />
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="unitprice" label="单价" align="center" />
          <el-table-column prop="amount" label="数量" align="center" />
          <el-table-column prop="totalprice" label="金额" align="center" />
        </el-table>
      </div>
      <!-- 包裹分配 -->
      <div class="choicePack">
        <p>包裹分配</p>
        <packageModule
          :order-data="initData"
          @addbox="addbox"
          @boxArray="handleBoxArray"
        />
      </div>
      <!-- 包装袋分配 -->
      <div class="bagWrapper">
        <p>包装袋分配</p>
        <bagModule :box-array="boxArray" />
      </div>
      <!-- 备注 -->
      <div class="messageBox">
        <p>添加备注</p>
        <el-input
          v-model="remark"
          type="textarea"
          :rows="2"
          placeholder="请输入内容"
        />
      </div>
    </div>

    <!-- 服务条款 -->
    <div class="content">
      <div class="contentLeft">
        <p>服务条款</p>
        <p>1.本产品为跨境海外商品</p>
        <p>2.跨境产品采用当地国原包装，无中文标签</p>
        <p>3.为保障顺利通关，请确保订单中的收件人姓名和身份证号码填写无误</p>
      </div>
      <div class="contentRight">
        <div class="price">
          <p>
            已选商品<span v-text="payInfo.selectAmount" />件, 到手价: ￡<span
              v-text="payInfo.total"
            />
          </p>
          <p>
            商品金额: ￡<span v-text="payInfo.price" />, 运费: ￡<span
              v-text="payInfo.freight"
            />, 税费: ￡<span v-text="payInfo.taxes" />
          </p>
        </div>
        <button @click="handleClickPay">提交订单</button>
      </div>
    </div>
    <el-dialog title="购买限制提示" :visible.sync="dialogVisible" width="30%">
      <p>
        用户信息未认证,请先上传证件信息<strong
          @click="handleDeniedPay"
        >点击此处前往身份认证中心</strong>
      </p>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleDeniedPay">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { Message } from 'element-ui'
import { CodeToText } from 'element-china-area-data'
import { getRecipient, setDefaultRecipient } from '@/api/recipient'
import { createOrder } from '@/api/order'
import { mapGetters } from 'vuex'

export default {
  components: {
    packageModule: () => import('./components/packageModule.vue'),
    bagModule: () => import('./components/bagModule.vue')
  },
  props: {
    timestamp: {
      type: String,
      default: () => ''
    }
  },
  // beforeRouteEnter(to, from, next) {
  //   // console.log('beforRouteEnter')
  //   // console.log(this) // 结果为undefined，因为在执行beforRouteEnter时候，组件还没有被创建出来；先执行beforRouteEnter，再执行beforeCreate
  //   next((vm) => {
  //     // 参数vm就是当前组件的实例。
  //     // console.log(vm.test) // 改变之前
  //     // 在这里面去判断用户是否登录，是否从购物车跳转来，没有登陆就跳走
  //     if (from.fullPath === '/webapp/cart') {
  //       // console.log(from.fullPath === ' /webapp/cart')
  //       next()
  //     } else {
  //       vm.$router.push('/webapp/cart')
  //     }
  //   })
  // },
  data() {
    return {
      dialogVisible: false,
      remark: '',
      initData: [],
      boxData: [],
      boxArray: [],
      allGoods: [],
      addressLists: [],
      selectAddress: 0,
      payInfo: {
        selectAmount: 0,
        total: 0,
        price: 0,
        freight: 0,
        taxes: 0
      }
    }
  },
  computed: {
    ...mapGetters(['authinfo'])
  },
  created() {
    this.getData()
    // this.getData()
  },
  mounted() {
    // setTimeout(() => {
    //   const node = document.querySelector('.iconStyle').querySelectorAll('i')[7]
    //   node.parentNode.removeChild(node)
    // }, 50)
    this.flushSelectionAll()
  },
  methods: {
    setAddressDefault(id) {
      setDefaultRecipient(id).then((response) => {
        if (response.code === 20000) {
          Message({
            type: 'success',
            message: '设置成功'
          })
        } else {
          Message({
            type: 'success',
            message: '设置失败'
          })
        }
      })
    },
    addbox(arg) {
      if (arg.length > 0) {
        const tempBox = arg[0]
        tempBox.amount = arg.length
        tempBox.totalprice = (
          parseFloat(tempBox.amount) * parseFloat(tempBox.unitprice)
        ).toFixed(2)
        this.boxData = [tempBox]
      } else {
        this.boxData = []
      }
      this.flushSelectionAll()
    },
    getData() {
      // this.$store.dispatch('cart/getOrderStuff').then((response) => {
      //   console.log(response)
      // })
      getRecipient()
        .then((response) => {
          console.log('response: ', response)
          if (response.code === 20000) {
            this.addressLists = response.data
            this.addressLists.forEach((o, index) => {
              // console.log(o, index)
              if (o.isdefault === true) {
                this.selectAddress = index
              }
            })
          }
        })
        .catch((error) => {
          Message({
            type: 'error',
            message: error
          })
        })
      this.initData = JSON.parse(window.sessionStorage.getItem('orderStuff'))
      // this.$store.dispatch('cart/getOrderStuff')
    },
    handleCodeToText(arr) {
      return arr.map((s) => CodeToText[s]).join('/')
    },
    handleSelectionGoods(val) {
      this.selectionGoods = val
      this.flushSelectionAll()
    },
    handleSelectionPacks(val) {
      this.selectionPacks = val
      this.flushSelectionAll()
    },
    flushSelectionAll() {
      this.allGoods = [...this.initData, ...this.boxData]
      // console.log('allGoods == > ', this.allGoods)
      if (this.allGoods.length > 0) {
        this.payInfo.selectAmount = this.allGoods.length
        this.payInfo.price = this.allGoods
          .map((o) => parseFloat(o.totalprice))
          .reduce((a, b) => a + b)
          .toFixed(2)
        this.payInfo.freight = 0
        this.payInfo.taxes = 0
        this.payInfo.total = parseFloat(
          this.payInfo.price + this.payInfo.freight + this.payInfo.taxes
        ).toFixed(2)
      } else {
        this.payInfo = {
          selectAmount: 0,
          total: 0,
          price: 0,
          freight: 0,
          taxes: 0
        }
      }
      // console.log(this.payInfo)
    },
    confirmOrderInfo() {
      for (let i = 0; i < this.boxArray.length; i += 1) {
        if (
          !this.boxArray[i].address ||
          typeof this.boxArray[i].address !== 'object'
        ) {
          Message.warning(`包裹${i + 1}未选择收货地址`)
          return false
        }
      }
      return true
    },
    handleClickPay() {
      if (!this.confirmOrderInfo()) {
        return
      }
      if (this.authinfo.confirm_idnumber === '1') {
        this.payInfo.address = this.addressLists[this.selectAddress]
        // console.log(
        //   '收件信息:' + JSON.stringify(this.addressLists[this.selectAddress])
        // )
        // console.log('购买商品信息:' + JSON.stringify([...this.initData, ...this.boxData]))
        // // console.log('附加物品信息:' + JSON.stringify(this.boxData))
        // console.log('支付信息:' + JSON.stringify(this.payInfo))
        // Message.success('订单信息已在控制台打印')
        // const requestData = {
        //   address: JSON.stringify(this.addressLists[this.selectAddress]),
        //   list: JSON.stringify(this.initData),
        //   addition: JSON.stringify(this.boxData),
        //   payinfo: JSON.stringify(this.payInfo)
        // }
        const formData = new FormData()
        this.addressLists[this.selectAddress].remark = this.remark
        formData.append(
          'address',
          JSON.stringify(this.addressLists[this.selectAddress])
        )
        // formData.append('ids', [...this.initData, ...this.boxData].map((o) => o.id))
        formData.append(
          'goodsids',
          [...this.initData].map((o) => o.id)
        )
        // formData.append('addition', JSON.stringify(this.boxData))
        // formData.append('payinfo', JSON.stringify(this.payInfo))
        createOrder(formData)
          .then((response) => {
            if (response.code === 20000) {
              Message.success('订单提交成功')
              this.$router.push(`/webapp/pay/${response.data}`)
            }
          })
          .catch((err) => {
            console.log('测试订单信息的发送返回的异常数据', err)
          })
      } else {
        this.dialogVisible = true
      }
    },
    handleDeniedPay() {
      this.dialogVisible = false
      this.$router.push('/webapp/cid')
    },
    handleBoxArray(val) {
      // console.log('handleBoxArray', val)
      this.boxArray = val
    }
  }
}
</script>

<style lang="scss" scoped>
strong {
  cursor: pointer;
  color: #3498db;
}
.container {
  width: 100%;
  margin: 0 auto;
}
.step {
  width: 60%;
  margin: 5rem auto 3rem;
}
hr {
  border: none;
  width: 60%;
  margin: 0 auto;
  border-bottom: 1px solid grey;
}
.iconStyle {
  display: flex;
  width: 60%;
  margin: 5rem auto;
  font-size: 8rem;
  justify-content: space-around;
  align-items: center;
  .iconItem {
    flex: 1;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    p {
      font-size: 1.8rem;
    }
    &.active {
      color: #d35400;
    }
  }
  .el-icon-arrow-right {
    font-size: 5rem;
  }
}
.mainBox {
  width: 60%;
  margin: 2rem auto;
  .selectAddress,
  .messageBox,
  .summary {
    font-size: 1.8rem;
    font-weight: 600;
    display: flex;
    p {
      &:nth-child(2) {
        color: #d35400;
      }
    }
  }
  .selectAddress {
    display: flex;
    flex-direction: column;
    .coopBox {
      display: flex;
      justify-content: center;
    }
  }
  .messageBox {
    display: flex;
    flex-direction: column;
    .el-textarea {
      width: 500px;
    }
  }
  .table1,
  .table2 {
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
.choicePack {
  p {
    font-size: 1.8rem;
    font-weight: 600;
  }
}
.bagWrapper {
  p {
    font-size: 1.8rem;
    font-weight: 600;
  }
}
.content {
  width: 60%;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  background-color: rgb(247, 247, 247);
  padding: 2rem;
  .contentLeft {
    display: flex;
    flex-direction: column;
  }
  .contentRight {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: flex-end;
    .price {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-content: flex-end;
      p {
        margin: 5px 2rem;
        font-size: 1.2rem;
      }
      span {
        color: #e67e22;
        font-size: 2rem;
        font-weight: 600;
        margin: 0 0.5rem;
      }
    }
    button {
      width: 150px;
      height: 50px;
      border: none;
      background-color: #da4736;
      font-size: 1.5rem;
      font-weight: 600;
      color: #fff;
    }
  }
}
</style>
