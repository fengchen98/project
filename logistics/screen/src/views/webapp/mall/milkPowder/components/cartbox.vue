<template>
  <div>
    <div class="cartbox" @click="pushRouter('/webapp/userspace/accountCenter')">
      <i class="el-icon-user" />
    </div>
    <div class="cartbox" @click="pushRouter('/webapp/cart')">
      <el-badge :value="value" :max="max_value">
        <i class="el-icon-shopping-cart-full" />
      </el-badge>
    </div>
  </div>
</template>

<script>
import { get_cartstuff_number } from '@/api/cart'
import { Message } from 'element-ui'

export default {
  props: {
    timestamp: {
      type: Number,
      default: () => ''
    }
  },
  data() {
    return {
      value: 0,
      min_value: 0,
      max_value: 99
    }
  },
  watch: {
    timestamp(newValue, oldValue) {
      this.getData()
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      get_cartstuff_number()
        .then((res) => {
          if (res.code === 20000) {
            this.value = res.data
          } else {
            Message.error(res.msg)
          }
        })
        .catch((err) => {
          Message.error(err + '')
        })
    },
    pushRouter(url) {
      console.log('点击了: ', url)
      this.$router.push(url)
    }
  }
}
</script>

<style lang="scss" scoped>
.cartbox {
  cursor: pointer;
  width: 3rem;
  height: 3rem;
  background-color: rgba(189, 195, 199, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  // border-radius: 5px;
  color: #fff;
  &:hover {
    // color: #fff;
    background-color: rgba(230, 126, 34, 1);
  }
  i {
    position: relative;
    font-size: 2rem;
    overflow: hidden;
  }
}
</style>
