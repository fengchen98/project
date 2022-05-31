<template>
  <div class="container-body">
    <div v-for="item in initData" :key="item.id" class="outLine">
      <div class="top-pic">
        <img
          class="pics"
          :src="item.images ?item.images[0] : null "
          @click="handleboxClick(item.id)"
        >
      </div>
      <span class="text1">{{ item.title }}</span>
      <div class="textBox">
        <div class="text2">{{ item.brand }}</div>
        <div class="text3">{{ '￡' + item.price.toFixed(2) }}</div>
      </div>
      <el-row class="row-botton">
        <el-input-number
          v-model="item.model"
          class="inputBar"
          size="mini"
          :min="1"
        />
        <el-button
          class="inputBtn"
          type="submit"
          @click="handleSaveCart(item)"
        ><svg-icon icon-class="buycar" />加入购物车</el-button>
      </el-row>
    </div>
  </div>
</template>

<script>
import { getOnSaleProduct } from '@/api/product'
import { save_cartstuff } from '@/api/cart'
import { Message } from 'element-ui'
import request from '@/utils/request'

export default {
  name: 'Search1',
  data() {
    return {
      num: 1,
      initData: [],
      showData: []
    }
  },
  created() {
    this.getData()
  },
  methods: {
    imgSrc(images) {
      const imagesArr = JSON.parse(images).map(
        (o) => request.defaults.baseURL + o
      )
      // console.log(imagesArr)
      return imagesArr
    },
    getData() {
      getOnSaleProduct().then((response) => {
        // console.log(response)
        this.initData = response.data
        this.initData.forEach((o) => {
          o.images = this.imgSrc(o.images)
        })
      })
    },
    handleboxClick(id) {
      window.location.hash = `/milkPowder/${id}`
    },
    handleSaveCart(item) {
      save_cartstuff(item.id, item.model).then((res) => {
        if (res.code === 20000) {
          Message.success('已添加购物车')
          this.$emit('flushCartbox', 0)
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.container-body {
  position: relative;
  /* background-color: rgb(165, 29, 29); */
  display: flex;
  flex-wrap: wrap;
  // justify-content: space-between;
}
.outLine {
  cursor: pointer;
  position: relative;
  // width: 23.5rem;
  height: 33rem;
  width: 20%;
  border-color: #ecf0f1;
  border-width: 1px;
  border-style: solid;
  padding: 1rem;
  // background-color: rgb(24, 121, 64);
  &:hover {
    border-color: #d35400;
  }
}
.top-pic {
  position: relative;
  width: 95%;
  height: 60%;
  // background-color: rgb(185, 74, 74);
  background-color: #fff;
  border: 1px solid rgba(204, 204, 204, 0.589);
  margin: 0 auto;
  margin-bottom: 6%;
}
.pics {
  position: relative;
  object-fit: cover;
  object-position: center;
  height: 100%;
  width: 100%;
}
.text1 {
  display: block;
  width: 100%;
  height: 10%;
  font-size: 2px;
  padding-left: 0.7rem;
  padding-right: 0.35rem;
  margin-top: 2rem;
  // background-color: rgb(190, 65, 65);
}
.textBox {
  position: relative;
  width: 100%;
  height: 8%;
  // background-color: royalblue;
  display: flex;
  justify-content: space-between;
  margin-top: 3%;
  margin-bottom: 5%;
  padding: 0 1rem;
}
.text2 {
  position: relative;
  width: 15rem;
  height: 90%;
  // background-color: seagreen;
  font-size: 1.4 rem;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.text3 {
  position: relative;
  width: 40%;
  height: 90%;
  // background-color: seagreen;
  font-size: 2rem;
  color: rgb(209, 9, 9);
}
.row-botton {
  position: relative;
  width: 100%;
  height: 12%;
  display: flex;
  justify-content: space-between;
}
.inputBar {
  width: 45%;
  margin-right: 1rem;
  // height: 2vh;
}
.inputBtn {
  padding: 0;
  width: 10rem;
  height: 2.9rem;
  text-align: center;
  color: #e4393c;
  &:hover {
    border: rgb(209, 9, 9) 1px solid;
    background-color: #fff;
  }
}
.svg-icon {
  width: 1.5rem;
  height: 1.5rem;
  margin-right: 0.5rem;
}
</style>
