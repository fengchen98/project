<template>
  <div class="container">
    <div class="banner">
      <img src="/static/images/problem_banner.png" alt="">
    </div>
    <div class="mainWrapper">
      <div class="header">
        <h1 class="title">常见问题</h1>
        <p class="slogan">用户至上 极致服务</p>
        <p class="slogan">最稳定 最负责 最安心</p>
        <div class="searchBox">
          <input type="text" placeholder="搜索问题">
          <button><i class="el-icon-search" />搜索</button>
        </div>
      </div>
      <div class="bodyWrapper">
        <div class="body-left">
          <h1>常见问题分类</h1>
          <div class="listWrapper">
            <div
              v-for="(catalog, index) in Object.keys(classification)"
              :key="index"
            >
              <span class="subtitle" v-text="catalog" />
              <span
                v-for="p in classification[catalog]"
                :key="p.id"
                :class="p.id === id ? 'question active' : 'question'"
                :title="p.title"
                @click="handleClickQuestion(p.id)"
                v-text="p.title"
              />
            </div>
          </div>
        </div>
        <div class="body-right">
          <div class="markbar">
            <span>
              <i class="el-icon-warning-outline" />问题中心 >>
              <span v-text="current.title" />
            </span>
            <hr>
          </div>
          <div class="content" v-html="current.html" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { titleList, query } from '@/api/problem'

export default {
  props: {
    id: {
      type: String,
      default: () => ''
    }
  },
  data() {
    return {
      problemList: [],
      classification: {},
      current: {}
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      titleList()
        .then((res) => {
          this.problemList = res.data
          if (this.id === '0') {
            this.$router.push(`/webapp/problem/${this.problemList[0].id}`)
          }
          this.handleCatalog()
          this.getContent()
        })
        .catch((err) => {
          console.log(err)
        })
    },
    getContent() {
      query(this.id).then((res) => {
        if (res.code === 20000) {
          this.current = res.data
        }
      })
    },
    handleCatalog() {
      const tmp = {}
      this.problemList.forEach((o) => {
        if (tmp[o.type]) {
          tmp[o.type].push(o)
        } else {
          tmp[o.type] = [o]
        }
      })
      this.classification = tmp
    },
    handleClickQuestion(id) {
      this.$router.push(`/webapp/problem/${id}`)
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
  transition: all 0.5s;
}
.banner {
  position: absolute;
  z-index: -1;
  img {
    width: 100%;
  }
}
.mainWrapper {
  width: 55%;
  padding-top: 4rem;
  margin: 0 auto;
  .header {
    padding: 7rem 0;
    .title {
      margin: 0 0 3rem 0;
      font-size: 5rem;
      font-weight: 400;
      color: #fff;
    }
    .slogan {
      color: #fff;
      padding: 0 20rem;
      font-size: 2.5rem;
      margin: 3rem 0;
    }
    .searchBox {
      padding: 0 20rem;
      display: flex;
      justify-content: center;
      align-items: center;
      input {
        height: 4rem;
        width: 60rem;
        outline: none;
        border: none;
      }
      button {
        height: 4rem;
        width: 8rem;
        border: none;
        background-color: #2980b9;
        color: #fff;
        font-size: 1.5rem;
        i {
          margin: 0 0.5rem;
        }
        &:hover {
          background-color: #3498db;
        }
      }
    }
  }
  .bodyWrapper {
    display: flex;
    background-color: rgba(243, 249, 250, 0.897);
    padding: 2rem;
    .body-left {
      width: 25%;
      // background-color: red;
      border-right: 1px solid #e5e8eb;
      .listWrapper {
        padding: 1rem;
        text-overflow: ellipsis;
        span {
          display: flex;
          flex-direction: column;
        }
        .subtitle {
          position: relative;
          padding: 1rem;
          margin: 1rem 0;
          color: #fff;
          background-color: #2980b9;
          font-size: 1.5rem;
          &::after {
            position: absolute;
            content: '';
            width: 1rem;
            height: 1rem;
            border: none;
            border-top: 1px solid #fff;
            border-right: 1px solid #fff;
            right: 2rem;
            transform: translateY(40%) rotate(45deg);
          }
        }
        .question {
          position: relative;
          cursor: pointer;
          display: inline-block;
          white-space: nowrap;
          white-space: nowrap; /*强制单行显示*/
          text-overflow: ellipsis; /*超出部分省略号表示*/
          overflow: hidden; /*超出部分隐藏*/
          width: 100%; /*设置显示的最大宽度*/
          padding-left: 2rem;
          font-size: 1.5rem;
          line-height: 3rem;
          &:hover {
            color: #e67e22;
          }
          &.active {
            background-color: #000;
            color: #fff;
          }
        }
      }
    }
    .body-right {
      position: relative;
      width: 80%;
      padding: 2rem;
      font-size: 1.5rem;
      line-height: 2.5rem;
      .markbar {
        font-size: 1.5rem;
        span {
          &:nth-child(1) {
            margin-right: 1rem;
          }
        }
        hr {
          border: none;
          border-bottom: 1px solid #bdc3c7;
          margin-bottom: 3rem;
        }
      }
      .content ::v-deep {
        padding: 0 2rem;
        width: 100%;
        p {
          img {
            margin: 0 auto;
            width: 100% !important;
          }
        }
      }
      // background-color: blue;
    }
  }
}
</style>
