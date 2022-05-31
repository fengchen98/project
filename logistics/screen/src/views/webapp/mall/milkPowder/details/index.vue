<template>
  <div class="container">
    <div>
      <loginBar />
    </div>
    <div class="banner" />
    <div class="noticeBar">
      <a href="#/milkPowder">奶粉商城 >> </a>
      <span v-text="product.title" />
    </div>
    <div class="detailContainer">
      <div class="proIntro">
        <div class="introLeft">
          <div class="mainImg">
            <img :src="product.images[mainImgSrc]" alt="">
          </div>
          <div class="thumb">
            <div
              v-for="(imgs, index) in product.images"
              :key="index"
              class="thumbPic"
            >
              <img
                :src="imgs"
                alt="thumb picture"
                @click="mainImgSrc = index"
              >
            </div>
          </div>
        </div>

        <div class="introRight">
          <div class="proInfo">
            <div class="proTitle">
              <p v-text="'|   ' + product.brand" />
              <p v-text="product.title" />
            </div>

            <div class="priceBox">
              <span>价格: </span>
              <span class="priceNumber" v-text="'￡' + product.price" />
            </div>
            <div class="numberBox">
              <span>数量: </span>
              <el-input-number
                v-model="product.access_count"
                :min="1"
                :max="99"
                size="mini"
                label="购买数量"
              />
            </div>
            <div class="buttonBox">
              <button @click="handleSaveCart">加入购物车</button>
              <!-- <button>立即购买</button> -->
            </div>
          </div>
        </div>
      </div>
      <div class="recommendPro">
        <div class="recommendTitle">
          <button>店长推荐</button>
        </div>
        <div class="recommendContent">
          <div
            v-for="recom in recommendProduct"
            :key="recom.id"
            class="recomproduct"
          >
            <img :src="recom.src" alt="product">
            <span v-text="'￡' + recom.price" />
          </div>
        </div>
      </div>
      <div class="bottom">
        <div class="bottomLeft">
          <div class="leftBtn">
            <button>猜你喜欢</button>
          </div>
          <div class="guess">
            <div v-for="i in recommendProduct" :key="i.id" class="guessPro">
              <img :src="i.src" alt="猜你喜欢商品">
              <span v-text="i.name" />
              <span v-text="'价格: ￡' + i.price" />
            </div>
          </div>
        </div>
        <div class="bottomRight">
          <div class="tabsBox">
            <button class="active" name="detail" @click="handleTabsClick">
              商品详情
            </button>
            <button name="comment" @click="handleTabsClick">用户评价</button>
            <button name="aftersale" @click="handleTabsClick">售后保障</button>
          </div>
          <div class="bottombox">
            <keep-alive>
              <component :is="bottomComponent" :product="product" />
            </keep-alive>
          </div>
        </div>
      </div>
    </div>
    <div class="pageFooter">版权所有@神州物流</div>
  </div>
</template>

<script>
import { getProductInfo } from '@/api/product'
import { save_cartstuff } from '@/api/cart'
import request from '@/utils/request'
import { Message } from 'element-ui'

export default {
  components: {
    loginBar: () => import('@/views/webapp/layout/components/loginBar'),
    detail: () => import('./components/detail'),
    comment: () => import('./components/comment'),
    aftersale: () => import('./components/aftersale')
  },
  props: {
    id: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      activeName: 'detail',
      product: {},
      mainImgSrc: 0,
      buyNumber: 1,
      recommendProduct: [
        {
          id: 1,
          name: '4罐装||英国爱他美白金版  1段 0-6月 200G ',
          src: '/static/pimg/1100001/1100001_1.jpg',
          price: '59.8'
        },
        {
          id: 2,
          name: '5罐装||英国爱他美白金版  1段 0-6月 400G ',
          src: '/static/pimg/1100001/1100001_1.jpg',
          price: '15.9'
        },
        {
          id: 3,
          name: '6罐装||英国爱他美白金版  1段 0-6月 600G ',
          src: '/static/pimg/1100001/1100001_1.jpg',
          price: '79.0'
        },
        {
          id: 4,
          name: '7罐装||英国爱他美白金版  1段 0-6月 800G ',
          src: '/static/pimg/1100001/1100001_1.jpg',
          price: '85.5'
        },
        {
          id: 5,
          name: '8罐装||英国爱他美白金版  1段 0-6月 1000G ',
          src: '/static/pimg/1100001/1100001_1.jpg',
          price: '95.0'
        },
        {
          id: 6,
          name: '9罐装||英国爱他美白金版  1段 0-6月 1200G ',
          src: '/static/pimg/1100001/1100001_1.jpg',
          price: '98.8'
        }
      ],
      bottomComponent: 'detail'
    }
  },
  // mounted() {
  //   this.mainImgSrc = this.product.img[0].src
  // },
  created() {
    this.getData()
  },
  methods: {
    imgSrc(images) {
      const imagesArr = JSON.parse(images).map(
        (o) => request.defaults.baseURL + o
      )
      // console.log(imagesArr)
      return imagesArr
    },
    handleSaveCart() {
      save_cartstuff(this.product.id, this.product.access_count).then((res) => {
        if (res.code === 20000) {
          Message.success('已添加购物车')
          this.$emit('flushCartbox', 0)
        }
      })
    },
    getData() {
      getProductInfo(this.id).then((res) => {
        if (res.code === 20000) {
          this.product = res.data
          this.product.images = this.imgSrc(this.product.images)
          this.product.introduce_images = this.imgSrc(
            this.product.introduce_images
          )
        }
      })
    },
    handleTabsClick(arg) {
      document.querySelectorAll('.tabsBox button').forEach((el) => {
        el.classList.remove('active')
      })
      const btn = arg.path[0]
      btn.classList.add('active')
      this.bottomComponent = btn.name
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  box-sizing: border-box;
  width: 100%;
  height: 100%;
}
.banner {
  width: 100%;
  height: 150px;
  background: url('/static/images/detailBanner.png') no-repeat;
  background-position-y: top;
}
.noticeBar {
  width: 100%;
  padding: 1rem calc(20% + 1rem);
  // height: 5rem;
  color: #2d3436;
  font-size: 1.3rem;
  background-color: rgb(242, 242, 242);
  margin: 1rem 0;
}
.detailContainer {
  width: 60%;
  // background-color: red;
  margin: 1rem auto;
}
.proIntro {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: space-between;
  margin-top: 2rem;
  // background-color: cornflowerblue;
  .introLeft {
    flex: 30%;
    .mainImg {
      position: relative;
      border: 1px solid #b5bec2;
      width: 450px;
      height: 450px;
      display: flex;
      justify-content: center;
      align-items: center;
      // padding: 1px;
      overflow: hidden;
      img {
        width: 450px;
        height: 450px;
        object-fit: contain;
      }
    }
    .thumb {
      position: relative;
      width: 45rem;
      margin-top: 2rem;
      display: flex;
      flex-flow: wrap;
      justify-content: space-between;
      .thumbPic {
        cursor: pointer;
        border: 1px solid #ccc;
        border-radius: 5px;
        width: 10rem;
        height: 10rem;
        margin: 0.5rem 0;
        display: flex;
        align-items: center;
        img {
          width: 100%;
          height: 100%;
          object-fit: contain;
        }
      }
    }
  }
  .introRight {
    flex: 60%;
    margin-left: 3rem;
    padding: 0 2rem;
    .proTitle {
      // display: flex;
      color: #636e72;
      p:nth-child(1) {
        color: #d35400;
        font-size: 1.8rem;
        font-weight: 600;
        margin: 1rem 1.5rem 1rem 0;
      }
      p:nth-child(2) {
        font-size: 1.8rem;
        font-weight: 600;
        margin: 1rem 0;
      }
    }
    .priceBox {
      width: 100%;
      // height: 30vh;
      margin-top: 2rem;
      background-color: #dfe5e7;
      padding: 4rem 2rem;
      span {
        color: #636e72;
        font-size: 2rem;
        font-weight: 800;
      }
      .priceNumber {
        color: #d63031;
        font-size: 2.5rem;
        font-weight: 800;
      }
    }

    .numberBox {
      margin: 3rem 0;
      span {
        margin-right: 1rem;
      }
    }
    .buttonBox {
      width: 100%;
      display: flex;
      justify-content: flex-start;
      button {
        background-color: #e74c3c;
        cursor: pointer;
        margin: 0 2rem;
        width: 150px;
        color: #fff;
        font-size: 1.8rem;
        font-weight: 800;
        padding: 1rem;
        border: none;
        &:hover {
          background-color: #c0392b;
        }
        &:active {
          transform: scale(0.95);
        }
      }
    }
  }
}

.recommendPro {
  margin-top: 3rem;
  width: 100%;
  background-color: rgb(242, 242, 242);
  .recommendTitle {
    border-bottom: 1px solid #c0392b;
    button {
      height: 100%;
      border: 1px solid #fff;
      padding: 0.5rem 2rem;
      font-size: 1.5rem;
      color: #fff;
      background-color: #c0392b;
    }
  }
  .recommendContent {
    height: 15rem;
    display: flex;
    .recomproduct {
      width: 12rem;
      padding: 0.5rem;
      display: flex;
      flex-direction: column;
      justify-content: space-around;
      align-items: center;
      img {
        width: 100%;
      }
      span {
        margin-top: 0.2rem;
        font-size: 1.5rem;
        font-weight: 800;
        color: #c0392b;
      }
    }
  }
}
.bottom {
  margin-top: 3rem;
  width: 100%;
  // background-color: rgb(242, 242, 242);
  display: flex;
  .bottomLeft {
    flex: 20%;
    // height: 500px;
    padding: 0 2rem;
    // border: 1px solid grey;
    .leftBtn {
      border-bottom: 1px solid #c0392b;
      button {
        height: 100%;
        border: 1px solid #fff;
        padding: 0.5rem 2rem;
        font-size: 1.5rem;
        color: #fff;
        background-color: #c0392b;
      }
    }
    .guess {
      display: flex;
      flex-direction: column;
      align-items: center;
      .guessPro {
        margin: 1rem 0;
        width: 100%;
        // height: 15rem;
        padding: 1rem;
        border: 1px solid #95a5a6;
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        align-items: center;
        img {
          width: 100%;
        }
        span:nth-child(2) {
          font-size: 1.2rem;
          font-weight: 400;
        }
        span:nth-child(3) {
          margin-top: 0.5rem;
          font-size: 1.5rem;
          font-weight: 600;
          color: #c0392b;
        }
      }
    }
  }
  .bottomRight {
    flex: 80%;
    height: 100%;
    // border-left: 1px solid #bdc3c7;
    // border-right: 1px solid #bdc3c7;
    .tabsBox {
      border-bottom: 1px solid #c0392b;
      button {
        cursor: pointer;
        color: #000;
        font-size: 1.5rem;
        font-weight: 600;
        border: none;
        padding: 1.5rem 4rem;
        &.active {
          color: #fff;
          background-color: #c0392b;
        }
      }
    }
  }
}

.pageFooter {
  width: 100%;
  color: #ecf0f1;
  padding: 1rem 0;
  text-align: center;
  background-color: #000;
}
</style>
