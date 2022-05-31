<template>
  <div class="mainWrapper">
    <div class="left">
      <header>
        <p v-text="initData.title" />
      </header>
      <div class="content-info">
        <span><i class="el-icon-user" /> {{ initData.username }}</span>
        <span><i class="el-icon-time" />
          {{ initData.create_time.slice(0, -2) }}</span>
        <span><i class="el-icon-collection-tag" /> {{ initData.type }}</span>
      </div>
      <hr>
      <div class="contentWrapper" v-html="initData.html" />
    </div>
    <div class="right">
      <header>
        <p v-text="'相关推荐'" />
      </header>
      <div class="newsWrapper">
        <div v-for="(li, index) in newsList" :key="index" class="newsLists-row">
          <a :href="'#/webapp/news/news-page/' + li.id" v-text="li.title" />
          <span>{{ li.create_time.split(' ')[0] }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { query, titleListByType } from '@/api/news'
export default {
  props: {
    id: {
      type: String,
      default: () => ''
    }
  },
  data() {
    return {
      initData: {},
      newsList: []
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      query(this.id).then((res) => {
        if (res.code === 20000) {
          this.initData = res.data
          this.getNewsLists()
        }
      })
    },
    getNewsLists() {
      titleListByType(this.initData.type).then((res) => {
        if (res.code === 20000) {
          this.newsList = res.data
            .slice(0, 20)
            .filter((o) => o.id !== this.initData.id)
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.mainWrapper {
  margin: 2rem 0;
  background-color: rgba(243, 249, 250, 0.95);
  //   border-radius: 2rem;
  position: relative;
  width: 60%;
  min-height: 600px;
  margin-inline: auto;
  display: flex;
  .left {
    // background-color: lightblue;
    width: 800px;
    hr {
      width: 90%;
      border: none;
      border-bottom: 1px solid rgba(189, 195, 199, 1);
    }
    header {
      word-wrap: break-word;
      width: 100%;
      text-align: center;
      padding: 2rem;
      font-size: 2rem;
      font-weight: bold;
    }
    .content-info {
      text-align: center;
      span {
        margin: 0 1rem;
      }
    }
    .contentWrapper {
      padding: 2rem;
      position: relative;
      width: 100%;
      font-size: 1.2rem;
      ::v-deep img {
        width: 100%;
      }
    }
  }
  .right {
    flex: 1;
    margin: 5rem 0;
    border-left: 1px solid #ccc;
    padding: 2rem;
    header {
      display: block;
      p {
        text-align: center;
        font-size: 1.5rem;
        font-weight: bold;
        margin-bottom: 3rem;
      }
    }
    .newsWrapper {
      .newsLists-row {
        display: flex;
        justify-content: space-between;
        font-size: 1.2rem;
        margin: 1rem 0;
        a {
          display: block;
          width: 20rem;
          overflow: hidden;
          text-overflow: ellipsis;

          //   white-space: nowrap;
        }
      }
    }
    // background-color: red;
  }
}
</style>
