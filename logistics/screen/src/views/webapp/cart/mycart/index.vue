<template>
  <div class="container">
    <!-- <div class="iconStyle">
      <template v-for="(li, index) in iconLists">
        <div
          :key="li.title"
          :class="li.status ? 'iconItem active' : 'iconItem'"
        >
          <i :class="li.icon" />
          <p v-text="li.title" />
        </div>
        <i :key="index" class="el-icon-arrow-right" />
      </template>
    </div> -->
    <div class="step">
      <el-steps :active="1" align-center>
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
      <div class="summary">
        <p>我的购物车</p>
        <p v-text="'(' + goods_number + ')'" />
      </div>
      <div class="table1">
        <el-table
          :data="initData"
          stripe
          style="width: 100%"
          @selection-change="handleSelectionGoods"
        >
          <el-table-column type="selection" width="55" />
          <el-table-column prop="date" label="选购商品信息" width="450">
            <template slot-scope="scope">
              <div class="goodsIntro">
                <img :src="scope.row.images[0]" alt="">
                <p v-text="scope.row.name" />
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="unitprice" label="单价" align="center" />
          <el-table-column
            prop="amount"
            label="数量"
            align="center"
            width="200"
          >
            <template slot-scope="scope">
              <el-input-number
                v-model="scope.row.amount"
                size="mini"
                :min="1"
                :max="99"
                @change="handleAmountChange(scope.row)"
              />
            </template>
          </el-table-column>
          <el-table-column prop="totalprice" label="金额" align="center" />
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <p class="removeBtn" @click="delgoods(scope.row)">删除</p>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <!-- <div class="table2">
        <el-table
          :data="goodsInfo.packLists"
          stripe
          style="width: 100%"
          @selection-change="handleSelectionPacks"
        >
          <el-table-column type="selection" width="55" />
          <el-table-column prop="date" label="包装材料信息" width="500">
            <template slot-scope="scope">
              <div class="goodsIntro">
                <img :src="scope.row.imgurl" alt="">
                <p v-text="scope.row.title" />
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="uprice" label="单价" align="center" />
          <el-table-column prop="amount" label="数量" align="center" />
          <el-table-column prop="price" label="金额" align="center" />
          <el-table-column prop="name" label="操作" align="center">
            <template slot-scope="scope">
              <p @click="delgoods(scope.row.id)">删除</p>
            </template>
          </el-table-column>
        </el-table>
      </div> -->
    </div>
    <div class="payBox">
      <div class="content">
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
        <button @click="handleClickPay">去结算</button>
      </div>
    </div>
  </div>
</template>

<script>
import { Message } from 'element-ui'
import {
  get_cartstuff,
  remove_cartstuff,
  setNumber_cartstuff
} from '@/api/cart'
import { stringToArray } from '@/utils/index'
import request from '@/utils/request'

export default {
  data() {
    return {
      initData: [],
      selectionGoods: [],
      selectionPacks: [],
      selectionAll: [],
      payInfo: {
        selectAmount: 0,
        total: 0,
        price: 0,
        freight: 0,
        taxes: 0
      },
      iconLists: [
        {
          title: '1.我的购物车',
          icon: 'el-icon-shopping-cart-1',
          status: true
        },
        {
          title: '2.核对订单信息',
          icon: 'el-icon-tickets',
          status: false
        },
        {
          title: '3.提交支付',
          icon: 'el-icon-bank-card',
          status: false
        },
        {
          title: '4.完成订单',
          icon: 'el-icon-circle-check',
          status: false
        }
      ]
    }
  },
  computed: {
    goods_number() {
      let temp = 0
      this.initData.forEach((o) => (temp += o.amount))
      return temp
    }
  },
  created() {
    this.getData()
  },
  methods: {
    // getData() {
    //   this.$store.dispatch('cart/getCartStuff').then((response) => {
    //     this.initData = response
    //     // console.log('initdata', this.initData)
    //   })
    // },
    getData() {
      get_cartstuff().then((response) => {
        if (response.code === 20000) {
          this.initData = response.data
          this.initData.forEach((o) => {
            o.images = stringToArray(o.images, request.defaults.baseURL)
          })
        }
      })
    },
    delgoods(item) {
      remove_cartstuff(item.serial_id).then((response) => {
        if (response.code === 20000) {
          Message.success('商品已移除')
          this.getData()
        }
      })
    },
    handleSelectionGoods(val) {
      this.selectionGoods = val
      this.flushSelectionAll()
    },
    // handleSelectionPacks(val) {
    //   this.selectionPacks = val
    //   this.flushSelectionAll()
    // },
    flushSelectionAll() {
      this.selectionAll = []
      this.selectionAll.push(...this.selectionGoods, ...this.selectionPacks)
      if (this.selectionAll.length > 0) {
        this.payInfo.selectAmount = this.selectionAll.length
        this.payInfo.price = this.selectionAll
          .map((o) => o.totalprice)
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
    handleClickPay() {
      if (this.selectionAll.length > 0) {
        // console.log(this.selectionAll)
        // this.$store.dispatch('cart/setOrderStuff', this.selectionAll)
        window.sessionStorage.setItem(
          'orderStuff',
          JSON.stringify(this.selectionAll)
        )
        const timestamp = new Date().getTime()
        window.location.hash = `/webapp/order/${timestamp}`
        // this.$emit('submitPay', this.selectionAll)
      } else {
        Message.error('未选择任何商品')
      }
    },
    handleAmountChange(item) {
      setNumber_cartstuff(
        { userid: item.userid, serial_id: item.serial_id },
        item.amount
      ).then((response) => {
        if (response.code === 20000) {
          Message.success('商品数量已修改')
          this.getData()
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
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
    .removeBtn {
      cursor: pointer;
      &:hover {
        color: #d35400;
      }
    }
  }
}
.payBox {
  width: 100%;
  height: 80px;
  background-color: #ecf0f1;
  .content {
    width: 60%;
    margin: 0 auto;
    display: flex;
    justify-content: flex-end;
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
      height: 80px;
      border: none;
      background-color: #da4736;
      font-size: 1.5rem;
      font-weight: 600;
      color: #fff;
    }
  }
}
</style>
