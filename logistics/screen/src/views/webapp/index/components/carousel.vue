<template>
  <div ref="main_container" class="container">
    <el-carousel :interval="2000" arrow="always" height="50vh">
      <el-carousel-item v-for="item in initData" :key="item.id">
        <img class="carousel-item-img" :src="item.images">
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
import { onlineList } from '@/api/carousel'
import { Message } from 'element-ui'
import request from '@/utils/request'

export default {
  data() {
    return {
      initData: []
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      onlineList().then((res) => {
        if (res.code === 20000) {
          this.initData = res.data
          this.initData.forEach((o) => {
            o.images = request.defaults.baseURL + o.images
          })
        } else {
          Message.error('轮播图加载失败')
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  width: 100%;
  min-width: 1200px;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.carousel-item-img {
  width: 100%;
}
</style>
