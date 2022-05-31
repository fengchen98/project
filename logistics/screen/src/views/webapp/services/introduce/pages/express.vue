<template>
  <div class="container">
    <div class="banner">
      <img src="/static/images/banner.jpg" alt="banner">
    </div>
    <div class="content">
      <h1 v-text="initData.title" />
      <div class="boxWrapper">
        <div class="img-box">
          <img :src="'/static/images/' + initData.imgSrc" alt="">
        </div>
        <hr>
        <div class="linkStyle">
          <li>参考链接: <a href="#/webapp/freight">点击查看运费报价</a></li>
          <li>
            参考链接: <a href="#/webapp/service/">查看所有服务路线规则</a>
          </li>
        </div>
        <hr>
        <template v-for="i in initData.introduces">
          <div :key="i.title" class="box">
            <p class="title" v-text="i.title" />
            <template v-for="li in i.list">
              <div
                v-if="/loadPicture/.test(li)"
                :key="li"
                class="msgimgWrapper"
              >
                <img :src="'/static/images/' + li.split('/')[1]">
              </div>
              <li
                v-else
                :key="li"
                :class="
                  li[0] === ':' ? 'smalltitle' : li[0] === '@' ? 'bigtitle' : ''
                "
                v-text="li.slice(1)"
              />
            </template>
          </div>
          <hr :key="i.title + 'hr'">
        </template>
        <button class="btn">立即下单</button>
      </div>
      <div class="orderBox">
        <el-select
          v-model="international_express.destination"
          placeholder="目的地"
          :filterable="true"
          :allow-create="true"
        >
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
        <input
          v-model="international_express.weight"
          type="text"
          placeholder="重量 KG"
          required
        >
        <input
          v-model="international_express.length"
          type="text"
          placeholder="长度 CM"
          required
        >
        <input
          v-model="international_express.width"
          type="text"
          placeholder="宽度 CM"
          required
        >
        <input
          v-model="international_express.high"
          type="text"
          placeholder="高度 CM"
          required
        >
        <button @click="purchase">立即下单</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { Message } from 'element-ui'

export default {
  props: {
    tag: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      initData: {},
      international_express: {},
      options: [
        {
          value: '中国大陆',
          label: '中国大陆'
        },
        {
          value: '其他地区',
          label: '其他地区'
        }
      ]
    }
  },
  created() {
    this.getData()
  },
  mounted() {
    this.reduceChar()
  },
  methods: {
    getData() {
      axios.get(`/static/data/express_${this.tag}.json`).then((res) => {
        if (res.data) this.initData = res.data
      })
    },
    reduceChar() {
      document.querySelectorAll('.smalltitle').forEach((item) => {
        item.innerHTML = item.innerHTML.slice(0, -1)
      })
    },
    purchase() {
      Message.warning('订单系统接入中……')
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
.banner {
  //   transform: translateY(-20px);
  position: absolute;
  z-index: -1;
}
.content {
  position: relative;
  width: 50%;
  margin: 0 auto;
  padding-top: 10vh;
  h1 {
    font-size: 3rem;
    font-weight: 400;
    color: #fff;
    margin: 0;
    margin-bottom: 5rem;
  }
  .linkStyle {
    li {
      font-size: 1.5rem;
      line-height: 2.5rem;
      a {
        color: #3498db;
      }
    }
  }
  .boxWrapper {
    background-color: rgba(243, 249, 250, 0.95);
    padding: 2rem;
    hr {
      margin: 2rem 0;
      border: none;
      border-bottom: 1px solid #bdc3c7;
    }
    .img-box {
      padding: 2rem;
      img {
        width: 100%;
      }
    }
    .box {
      padding: 1rem;
      font-size: 1.5rem;
      line-height: 2rem;
      .smalltitle {
        list-style: none;
        font-size: 1.5rem;
        margin: 1.5rem 0;
      }
      .bigtitle {
        list-style: none;
        font-size: 2.5rem;
        font-weight: 700;
        margin: 2rem 0;
      }
      .title {
        text-align: center;
        font-size: 2.5rem;
        font-weight: 600;
        color: #2980b9;
      }
      img {
        margin: 4rem 0;
        width: 100%;
      }
      p,
      li {
        line-height: 3rem;
        span {
          font-weight: 700;
          color: #e67e22;
        }
        h4 {
          display: inline;
        }
      }
    }
    .btn {
      color: #fff;
      width: 12rem;
      height: 4rem;
      border: none;
      background-color: #e67e22;
      border-radius: 15rem;
      margin-left: 50%;
      transform: translateX(-50%);
    }
  }
  .orderBox {
    width: 100%;
    padding: 0.5rem 1rem;
    background-color: rgba(243, 249, 250, 0.95);
    margin: 3rem 0;
    display: flex;
    justify-content: space-around;
    align-items: center;
    * {
      margin: 1rem 2rem;
    }
    input {
      width: 6rem;
      height: 4rem;
      border: none;
      outline: none;
      background: transparent;
      border-bottom: 1px solid rgb(127, 140, 141);
    }
    button {
      cursor: pointer;
      width: 10rem;
      height: 4rem;
      color: #fff;
      background-color: rgba(41, 128, 185, 1);
      border: none;
      &:active {
        transform: scale(0.9);
      }
      &:hover {
        background-color: rgba(52, 152, 219, 1);
      }
    }
  }
}
</style>
