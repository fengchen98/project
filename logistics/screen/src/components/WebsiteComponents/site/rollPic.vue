<template>
  <!-- 组件外容器 -->
  <div class="container">
    <!--  图表容器  -->
    <el-carousel :interval="1500" height="100%" indicator-position>
      <el-carousel-item v-for="item in initData" :key="item.img">
        <img :src="item.img" class="image">
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
import { getcarousel } from '@/api/carousel'

export default {
  name: 'RollPic',
  data() {
    return {
      initData: null,
      imagebox: [
        { id: 0, img: require('/public/static/images/roll1.jpg') },
        { id: 1, img: require('/public/static/images/roll2.jpg') },
        { id: 2, img: require('/public/static/images/roll3.jpg') },
        { id: 3, img: require('/public/static/images/roll4.jpg') }
      ]
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      getcarousel().then((response) => {
        this.initData = response.data.items
      })
    }
  }
}
</script>

<style scoped>
.container {
  position: relative;
  width: 100%;
}
.el-carousel {
  height: 55vh;
}
/* .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
}
   */
.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
img {
  width: 100%;
  height: inherit;
}
</style>
