<template>
  <div class="container">
    <div class="main">
      <div v-for="item in showData" :key="item.title" class="box">
        <div class="box-img">
          <img :src="'/static/newsimg/' + item.src + '.png'" :alt="item.src">
        </div>
        <div class="box-info">
          <p v-text="item.title" />
          <div>
            <i class="el-icon-user" />
            <span v-text="item.author" />
            <i class="el-icon-time" />
            <span v-text="item.date" />
            <i class="el-icon-collection-tag" />
            <span v-text="item.tag" />
            <button class="btn" @click="linktopage(item.link)">阅读原文</button>
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
import axios from 'axios'
import { Message } from 'element-ui'

export default {
  data() {
    return {
      initData: null,
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
    getData() {
      axios
        .get('/static/data/newsnewsData.json')
        .then((res) => {
          this.initData = res.data
          this.pageTotal = this.initData.length
          this.updateData()
        })
        .catch((err) => {
          Message.error(err)
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
    linktopage(arg) {
      this.$router.push(arg)
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
  background-color: rgb(244, 244, 244);
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
</style>
