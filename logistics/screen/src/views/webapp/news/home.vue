<template>
  <div class="container">
    <div class="banner">
      <img src="/static/images/news_banner.png" alt="">
    </div>
    <div class="btnBox">
      <button @click="handleClickChangeType('*')">全部</button>
      <button @click="handleClickChangeType('服务公告')">服务公告</button>
      <button @click="handleClickChangeType('优惠活动')">优惠活动</button>
      <!-- <button @click="changeComponent('news')">新闻资讯</button> -->
      <button @click="handleClickChangeType('热门商品')">热门商品</button>
    </div>
    <div v-loading="loading" class="mainContainer">
      <!-- <transition name="fade-transform" mode="out-in">
        <keep-alive>
          <component :is="currentComponent" />
        </keep-alive>
      </transition> -->
      <div v-for="(item, index) in initData" :key="index" class="box">
        <div class="box-img">
          <img :src="item.image">
        </div>
        <div class="box-info">
          <p v-text="item.title" />
          <div>
            <i class="el-icon-user" />
            <span v-text="item.username" />
            <i class="el-icon-time" />
            <span>{{ item.create_time.split(' ')[0] }}</span>
            <i class="el-icon-collection-tag" />
            <span v-text="item.type" />
            <button class="btn" @click="linktopage(item.id)">阅读原文</button>
          </div>
        </div>
      </div>
      <div class="pagination">
        <el-pagination
          background
          layout="prev, pager, next"
          :total="pageTotal"
          :page-size="psize"
          @current-change="pageChange"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { stringToArray } from '@/utils/index'
import { titleList, titleListByType } from '@/api/news'

export default {
  // components: {
  //   all: () => import('./components/all.vue'),
  //   service: () => import('./components/service.vue'),
  //   news: () => import('./components/news.vue'),
  //   goods: () => import('./components/goods.vue'),
  //   discount: () => import('./components/discount.vue')
  // },
  data() {
    return {
      loading: true,
      currentComponent: 'all',
      initData: [],
      pageTotal: null,
      psize: 10,
      showData: null,
      pageindex: 1
    }
  },
  created() {
    this.getData()
  },
  methods: {
    handleClickChangeType(arg) {
      this.loading = true
      if (arg === '*') {
        this.getData()
      } else {
        titleListByType(arg).then((res) => {
          if (res.code === 20000) {
            this.initData = res.data
            this.initData.forEach((o) => {
              o.image = stringToArray(o.image)
            })
            this.loading = false
          }
        })
      }
    },
    changeComponent(cur) {
      this.currentComponent = cur
    },
    getData() {
      titleList().then((res) => {
        if (res.code === 20000) {
          this.initData = res.data
          this.initData.forEach((o) => {
            o.image = stringToArray(o.image)
          })
          this.loading = false
        }
        // console.log(this.initData)
      })
    },
    pageChange(arg) {
      this.pageindex = arg
      this.updateData()
    },
    updateData() {
      this.showData = this.initData.slice(
        (this.pageindex - 1) * this.psize,
        this.pageindex * this.psize
      )
    },
    linktopage(id) {
      // console.log(`${location.protocol}//${location.host}/#/webapp/news/news-page/${id}`)
      window.open(`${location.protocol}//${location.host}/#/webapp/news/news-page/${id}`)
      // this.$router.push(`/webapp/news/${id}`)
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  width: 100%;
}
.box {
  width: 100%;
  padding: 1rem;
  border-bottom: 1px solid rgb(211, 205, 205);
  display: flex;
  justify-content: space-between;
  .box-img {
    width: 30%;
    img {
      width: 100%;
    }
  }
  .box-info {
    position: relative;
    width: 67%;
    display: flex;
    flex-direction: column;
    p {
      font-size: 1.8rem;
      font-weight: 700;
      margin: 0.5rem 0 1.5rem 0;
    }
    span {
      margin-right: 1rem;
      font-size: 1rem;
    }
    .btn {
      cursor: pointer;
      position: absolute;
      bottom: 2rem;
      right: 2rem;
      border: none;
      width: 8rem;
      height: 3rem;
      color: #fff;
      background-color: #2980b9;
      border-radius: 5px;
      &:hover {
        background-color: #3498db;
      }
      &:active {
        transform: scale(0.95);
      }
    }
  }
}
.pagination {
  width: 100%;
  text-align: center;
}
.banner {
  position: relative;
  p {
    position: absolute;
    font-size: 3rem;
    font-weight: 700;
    color: #fff;
    transform: translateX(25vw);
  }
  img {
    width: 100%;
  }
}
.btnBox {
  width: 50%;
  margin: 0.5rem auto;
  button {
    cursor: pointer;
    border: none;
    width: 8rem;
    height: 3rem;
    margin-right: 1rem;
    color: #fff;
    background-color: rgba(41, 128, 185, 1);
    &:hover {
      background-color: #3498db;
    }
    &:active {
      transform: scale(0.95);
    }
  }
}
.mainContainer {
  width: 50%;
  margin: 0.5rem auto;
}
</style>
