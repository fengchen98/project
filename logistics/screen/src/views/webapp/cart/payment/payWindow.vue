<template>
  <div class="container">
    <el-image :src="imgsrc">
      <div slot="error" class="qrcode">
        <i class="el-icon-loading" />
      </div>
    </el-image>
    <!-- <img :src="imgsrc" alt="二维码加载失败" class="qrcode"> -->
    <button class="BtnBox" @click="handleClickFinishPay">我已支付</button>
  </div>
</template>

<script>
import { queryOrder } from '@/api/order'

export default {
  props: {
    imgsrc: {
      type: String,
      default: () => ''
    }
  },
  data() {
    return {
      count: 15
    }
  },
  mounted() {
    this.checkOrderPayStatus(0)
  },
  methods: {
    handleClickFinishPay() {
      this.$emit('checkStatus', 0)
    },
    checkOrderPayStatus() {
      const urlArr = this.imgsrc.split('/')
      const orderNumber = urlArr[urlArr.length - 1]
      const fromdata = new FormData()
      fromdata.append('orderNumber', orderNumber)
      this.loopQuery(fromdata)
    },
    loopQuery(fromdata) {
      this.count = 15
      // var count = 15
      const timer = setInterval(() => {
        queryOrder(fromdata).then((res) => {
          if (res.data.status === '1') {
            this.$emit('checkStatus', res.data)
            clearInterval(timer)
          }
          this.count--
        })
      }, 1000)
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  .el-image {
    width: 300px;
    height: 300px;
    display: flex;
    justify-content: center;
    align-items: center;
    .qrcode {
      object-fit: cover;
      i {
        font-size: 3rem;
      }
    }
  }
  .BtnBox {
    width: 150px;
    padding: 0.5rem 1rem;
    color: #fff;
    border: none;
    border-radius: 5px;
    margin-top: 2rem;
    background-color: rgba(39, 174, 96, 0.8);
  }
}
</style>
