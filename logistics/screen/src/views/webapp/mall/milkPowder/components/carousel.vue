<template>
  <div ref="main_container" class="container">
    <el-carousel :interval="2000" arrow="always" height="30vh">
      <el-carousel-item v-for="item in dataSource" :key="item.name">
        <img class="carousel-item-img" :src="item.img">
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
// import mixinInterface from '@/mixin/interface'

export default {
  name: 'Carousel',
  // mixins: [mixinInterface],
  data() {
    return {
      // dataSource: [
      //   {
      //     id: 0,
      //     src: 'https://p3.lefile.cn/fes/cms/2021/11/15/lfomj18t0ozui06aom0omx4gcslfqh692967.jpg'
      //   },
      //   {
      //     id: 1,
      //     src: 'https://p3.lefile.cn/fes/cms/2021/11/15/oat8obgf99qdck8j3hdf1hrs8815kw734497.jpg'
      //   },
      //   {
      //     id: 2,
      //     src: 'https://p1.lefile.cn/fes/cms/2021/11/15/kl8sdwjxe7ua44h1hnv1sdbqzugymr864769.jpg'
      //   },
      //   {
      //     id: 3,
      //     src: 'https://p3.lefile.cn/fes/cms/2021/11/16/yotb4twv5qayu42ea352bcmbu7hpsn389856.jpg'
      //   },
      //   {
      //     id: 4,
      //     src: 'https://p2.lefile.cn/fes/cms/2021/11/15/0b7fuo9xxitsnq11esk5zwa63ckefy798333.jpg'
      //   }
      // ],
      url: {
        list: 'carousel/list'
      },
      dataSource: [
        {
          date: '2021/10/5',
          img: '/static/images/brandintro_bg_1.png',
          status: '已发布',
          name: 'roll1',
          more: '此活动持续到12月底'
        },
        {
          date: '2021/10/5',
          img: '/static/images/brandintro_bg_2.png',
          status: '已发布',
          name: 'roll2',
          more: '此活动持续到12月底'
        }
      ]
    }
  },
  computed: {},
  mounted() {}
}
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  width: 100%;
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
