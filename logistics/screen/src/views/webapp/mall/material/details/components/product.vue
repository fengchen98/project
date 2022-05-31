<template>
  <div class="introduction">
    <div class="preview">
      <div class="preview_img">
        <img :src="product.mainpic" alt="">
      </div>
      <div class="preview_list">
        <i class="el-icon-arrow-left" />
        <ul class="item_list">
          <li v-for="imgs in product.listpic" :key="imgs.id">
            <img class="list_img" :src="imgs.src" alt="">
          </li>
        </ul>
        <i class="el-icon-arrow-right" />
      </div>
    </div>
    <div class="iteminfo">
      <div class="name">可封口小气泡袋 BM1</div>
      <div class="summary">
        <dl class="price">
          <dt>价格</dt>
          <dd>
            <div class="pl">£ 2.99</div>
          </dd>
        </dl>
        <dl class="price size">
          <dt>尺寸</dt>
          <dd>
            100mm x 165mm + 30mm<br>
            厚实型
          </dd>
        </dl>
        <dl class="choose">
          <dt>选择组合</dt>
          <dd><el-button>10件装</el-button></dd>
          <dd><el-button>50件装</el-button></dd>
          <dd><el-button>100件装</el-button></dd>
        </dl>
        <dl class="choose">
          <dt />
          <dd><el-button>200件装</el-button></dd>
          <dd><el-button>500件装</el-button></dd>
          <dd><el-button>750件装</el-button></dd>
        </dl>
        <div class="choose_btns">
          <div class="amount">
            <el-input-number
              v-model="num"
              :min="1"
              :max="10"
              label="描述文字"
              @change="handleChange"
            />
          </div>
          <div class="addcar">
            <el-button>加入购物车</el-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    id: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      product: {
        mainpic:
          'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FBM1-2.jpg',
        listpic: [
          {
            id: 1,
            src: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FBM1-2.jpg'
          },
          {
            id: 2,
            src: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FBM1-2.jpg'
          },
          {
            id: 3,
            src: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FBM1-2.jpg'
          }
        ]
      },
      num: 1
    }
  },
  methods: {
    handleChange(value) {
      console.log(value)
    }
  }
}
</script>

<style lang="scss" scoped>
.introduction {
  width: 100%;
  display: flex;
  justify-content: space-between;
}
.preview {
  width: 40%;
  height: 65vh;
  .preview_img {
    height: 75%;
    border: 1px solid #ccc;
    img {
      width: 100%;
      height: 100%;
    }
  }
  .preview_list {
    margin-top: 20px;
    height: 20%;
    position: relative;
    .item_list {
      position: absolute;
      top: 0;
      margin: 0;
      left: 10%;
      height: 100%;
      width: 80%;
      padding: 0;
      li {
        float: left;
        list-style: none;
        margin: 5px 7px;
        width: 25%;
        height: 90%;
        border: 2px solid #ccc;
        .list_img {
          width: 100%;
          height: 100%;
        }
      }
    }
    .el-icon-arrow-left,
    .el-icon-arrow-right {
      position: absolute;
      font-size: 25px;
      top: 40%;
    }
    .el-icon-arrow-left {
      left: 0;
    }
    .el-icon-arrow-right {
      right: 0;
    }
  }
}
.iteminfo {
  width: 55%;
}
.name {
  padding: 10px 0;
  font-size: 22px;
  height: 50px;
  font-weight: 700px;
  color: #333;
}
.summary {
  width: 100%;
  .price {
    background-color: #fcc095;
    padding: 10px 0;
    display: flex;
    justify-items: center;
    margin: 0;
    color: #fdfbfb;
    .pl {
      font-size: 22px;
      color: #ed1317;
    }
  }
  .choose {
    margin-top: 20px;
    color: #333;
    display: flex;
    flex-wrap: wrap;
    dt {
      padding-top: 10px;
    }
    .el-button {
      margin-right: 20px;
      width: 100px;
    }
  }
  dt {
    width: 20%;
    padding-left: 10px;
    font-size: 18px;
  }
  dd {
    font-size: 18px;
    margin: 0;
  }
}
.size {
  padding-top: 0;
  line-height: 30px;
}
.choose_btns {
  display: flex;
  margin-top: 30px;
  margin-left: 20%;
}
.amount {
  padding-top: 7px;
  padding-right: 7px;
  margin-right: 25px;
  .el-input-number {
    width: 10vw;
    height: 7vh;
  }
}

.addcar {
  .el-button {
    width: 12vw;
    height: 7vh;
    background-color: #ed1317;
    color: #fdfbfb;
    font-size: 19px;
    font-weight: 600;
  }
}
</style>
