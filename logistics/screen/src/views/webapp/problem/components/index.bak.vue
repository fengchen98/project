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
            <div v-for="pli in problemList" :key="pli.typeid">
              <span class="subtitle" v-text="pli.type" />
              <span
                v-for="p in pli.list"
                :key="p.id"
                class="question"
                :title="p.question"
                @click="handleClickquestion(pli.typeid, p.id, $event)"
                v-text="p.question"
              />
            </div>
          </div>
        </div>
        <div class="body-right">
          <template>
            <div class="markbar">
              <span>
                <i class="el-icon-warning-outline" />问题中心 >>
                <span v-text="answer.question" />
              </span>
              <hr>
            </div>
            <template v-for="(li, index) in answer.content">
              <div
                v-if="/loadPicture/.test(li)"
                :key="index"
                class="msgimgWrapper"
              >
                <img :src="'/static/images/' + li.split('/')[1]">
              </div>
              <li
                v-else
                :key="index"
                :class="
                  li[0] === ':' ? 'smalltitle' : li[0] === '@' ? 'bigtitle' : ''
                "
                v-html="li.slice(1)"
              />
            </template>
          </template>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  props: {
    typeid: {
      type: String,
      default: '1'
    },
    id: {
      type: String,
      default: '1'
    }
  },
  data() {
    return {
      problemList: [],
      answer: {},
      questiontype: this.typeid,
      questionid: this.id
    }
  },
  mounted() {
    setTimeout(() => {
      this.$nextTick(() => this.highlightQuestion())
    }, 50)
  },
  created() {
    this.getData()
    this.getAnswer()
  },
  methods: {
    getData() {
      axios
        .get('/static/data/problem/problemList.json')
        .then((res) => {
          this.problemList = res.data
        })
        .catch((err) => {
          console.log(err)
        })
    },
    handleClickquestion(questiontype, questionid, event) {
      this.questiontype = questiontype
      this.questionid = questionid
      event.currentTarget.classList.add('avtice')
      location.hash = `/webapp/problem/${questiontype}/${questionid}`
      // this.getAnswer()
    },
    getAnswer() {
      axios
        .get(
          `/static/data/problem/${this.questiontype}-${this.questionid}.json`
        )
        .then((res) => {
          this.answer = res.data
        })
        .catch((err) => {
          console.log(err)
        })
    },
    highlightQuestion() {
      document.querySelectorAll('.question').forEach((el) => {
        el.classList.remove('active')
      })
      const listWrapper = document.querySelector('.listWrapper')
      const divList = listWrapper.querySelectorAll('div')
      const questionList =
        divList[this.typeid - 1].querySelectorAll('.question')
      questionList[this.id - 1].classList.add('active')
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
      .msgimgWrapper {
        width: 100%;
        text-align: center;

        img {
          margin: 4rem 0;
          width: 80%;
        }
      }
      .smalltitle {
        list-style: none;
        font-size: 1.5rem;
        margin: 1.5rem 0;
      }
      .bigtitle {
        list-style: none;
        font-size: 1.5rem;
        font-weight: 700;
        margin: 2rem 0;
      }
      a {
        color: #3498db;
      }

      // background-color: blue;
    }
  }
}
</style>
