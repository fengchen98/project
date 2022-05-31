<template>
  <div class="container">
    <div class="mainWrapper">
      <p class="title">服务介绍</p>
      <div class="boxWrapper">
        <p
          v-for="li in boxList"
          :key="li.title"
          class="box"
          @click="switchData(li.tag, $event)"
          v-text="li.title"
        />
      </div>
      <div class="cardWrapper">
        <div v-for="data in showData" :key="data.title" class="card">
          <div class="cardtitle">
            <svg-icon :icon-class="data.icon" />
            <p v-text="data.title" />
          </div>
          <hr>
          <div class="msg">
            <li
              v-for="inst in data.instructionlist"
              :key="inst"
              v-text="inst"
            />
            <br>
            <li v-for="insu in data.insurancelist" :key="insu" v-text="insu" />
          </div>
          <div class="warning">
            <span>正常时效: </span>
            <span class="warningData" v-text="data.timeliness" />
            <span>国内承运: </span>
            <span class="warningData" v-text="data.carriage" />
          </div>
          <div class="footer">
            <a :href="'#/webapp/service/freight/' + data.serviceid">运费报价</a>
            <a :href="'#/webapp/service/freight/' + data.serviceid">送件方式</a>
            <a :href="'#/webapp/service/freight/' + data.serviceid">包裹准备</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      boxList: [
        {
          title: '全部',
          link: '',
          tag: ''
        },
        {
          title: '奶粉包税',
          link: '',
          tag: '奶粉包税'
        },
        {
          title: '万国邮政',
          link: '',
          tag: '万国邮政'
        },
        {
          title: '行李文件',
          link: '',
          tag: '行李文件'
        },
        {
          title: '杂货包税',
          link: '',
          tag: '杂货包税'
        },
        {
          title: '贵重物品',
          link: '',
          tag: '贵重物品'
        }
      ],
      initData: {},
      showData: {}
    }
  },
  created() {
    this.getData()
  },
  mounted() {
    (function() {
      document.querySelectorAll('.box')[0].classList.add('active')
    })()
  },
  methods: {
    getData() {
      axios.get('/static/data/services.json').then((res) => {
        this.initData = res.data
        this.showData = this.initData
        console.log(this.initData)
      })
    },
    switchData(tag, event) {
      document
        .querySelectorAll('.box')
        .forEach((item) => item.classList.remove('active'))
      event.currentTarget.classList.add('active')
      this.showData = this.initData.filter((item) => item.classify.includes(tag))
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
.mainWrapper {
  padding: 2rem;
  width: 50%;
  margin: 0 auto;
  .title {
    font-size: 3rem;
    font-weight: 400;
    color: #2980b9;
  }
  .boxWrapper {
    width: 100%;
    padding: 1rem 2rem;
    display: flex;
    justify-content: space-around;
    background-color: rgba(243, 249, 250, 0.897);
    .box {
      cursor: pointer;
      color: #2c3e50;
      width: 13rem;
      height: 10rem;
      font-size: 2rem;
      padding: 0rem 4rem;
      border: 1px solid #bdc3c7;
      background-color: #ecf0f1;
      display: flex;
      justify-content: center;
      align-items: center;
      &:hover {
        color: #3498db;
      }
      &.active {
        color: #fff;
        border: 1px solid #2980b9;
        background-color: #3498db;
      }
    }
  }
  .cardWrapper {
    width: 100%;
    display: flex;
    justify-content: flex-start;
    flex-wrap: wrap;
    background-color: rgba(243, 249, 250, 0.897);
    padding: 0 4%;
    .card {
      width: 30%;
      margin: 1.5%;
      border: 1px solid #bddbf0;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      // padding: 1rem;
      .cardtitle {
        padding: 1rem;
        display: flex;
        justify-content: flex-start;
        align-items: center;
        .svg-icon {
          width: 4rem;
          height: 4rem;
          margin-right: 1rem;
        }
        p {
          margin: 0;
          font-size: 2.5rem;
        }
      }
      hr {
        width: 90%;
        border: none;
        border-bottom: 1px dotted #3498db;
      }
      .msg {
        padding: 1rem ;
        height: 25rem;
        li {
          font-size: 1.3rem;
          line-height: 2.2rem;
        }
      }
      .warning {
        font-size: 1.3rem;
        margin: 1rem 0;
        padding: 0 1rem;
        .warningData {
          color: #e67e22;
        }
        span {
          &:nth-child(2) {
            margin-right: 1rem;
          }
        }
      }
      .footer {
        padding: 2rem;
        display: flex;
        justify-content: space-around;
        color: #fff;
        background-color: #3498db;
      }
    }
  }
}
</style>
