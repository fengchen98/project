<template>
  <div class="container">
    <div class="mainWrapper">
      <el-carousel
        :interval="5000"
        arrow="always"
        heiht="600px"
        indicator-position="outside"
      >
        <el-carousel-item v-for="item in actLists" :key="item.src">
          <div class="box">
            <img :src="item.image" :alt="item.title">
            <div class="text-box">
              <p v-text="item.title" />
              <span>{{ item.create_time.split(' ')[0] }}</span>
              <span v-text="item.description" />
              <!-- <span v-text="'截止日期：' + item.date" /> -->
            </div>
          </div>
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
</template>

<script>
import { titleListByType } from '@/api/news'
import { stringToArray } from '@/utils/index'

export default {
  data() {
    return {
      // actLists: [
      //   {
      //     src: '/static/images/4.jpg',
      //     title: '活动1活动1活动1活动1活动1活动',
      //     content:
      //       '活动1活动1活动1活动1活动1活动1活动1活动1活动1活动1活动1活动1活动1',
      //     date: '2021-12-31'
      //   },
      //   {
      //     src: '/static/images/5.jpg',
      //     title: '活动2活动2活动2活动2活动2',
      //     content:
      //       '活动2活动2活动2活动2活动2活动2活动2活动2活动2活动2活动2活动2活动2',
      //     date: '2021-12-30'
      //   },
      //   {
      //     src: '/static/images/7.jpg',
      //     title: '活动3活动3活动3活动3活动3活动3',
      //     content:
      //       '活动3活动3活动3活动3活动3活动3活动3活动3活动3活动3活动3活动3活动3',
      //     date: '2021-12-31'
      //   },
      //   {
      //     src: '/static/images/8.jpg',
      //     title: '活动4活动4活动4活动4活动4活动4',
      //     content:
      //       '活动4活动4活动4活动4活动4活动4活动4活动4活动4活动4活动4活动4活动4',
      //     date: '2021-12-31'
      //   }
      // ],
      actLists: []
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      titleListByType('优惠活动').then((res) => {
        if (res.code === 20000) {
          this.actLists = res.data.slice(0, 8)
          this.actLists.forEach((o) => {
            o.image = stringToArray(o.image)
          })
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  padding: 0;
  margin: 0;
}
.mainWrapper {
  position: relative;
  width: 100%;
  // height: 600px;
  padding: 2rem;
  background-color: rgba(244, 244, 244);
}
.box {
  background-color: rgba(244, 244, 244);
  display: flex;
  justify-content: space-between;
  height: 100%;
  .text-box {
    position: relative;
    flex: 1;
    padding: 2rem;
    height: 100%;
    P {
      position: absolute;
      color: #2e86de;
      font-size: 2rem;
      font-weight: 700;
      margin: 0;
    }
    span {
      &:nth-child(2) {
        position: absolute;
        font-size: .8rem;
        top: 5rem;
      }
      &:nth-child(3) {
        position: absolute;
        font-size: .8rem;
        top: 7rem;
      }
    }
  }
}
.el-carousel,
.el-carousel-item {
  background-color: transparent !important;
}
</style>
