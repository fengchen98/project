<template>
  <div class="container">
    <div class="first">
      <div class="avaterBox">
        <img :src="baseinfo.avatar || defaultAvatar" alt="">
      </div>
      <div class="infoBox">
        <!-- <p v-text="userInfo.username" /> -->
        <p v-text="baseinfo.nickname" />
        <p v-text="'个性签名: ' + baseinfo.signature" />
      </div>
      <div class="balanceBox">
        <p v-text="accountinfo.balance" />
        <p>账户余额</p>
        <button @click="handleClickRecharge('recharge')">充值</button>
      </div>
      <div class="rebateBox">
        <p v-text="accountinfo.rebate" />
        <p>优惠返利</p>
        <button @click="handleClickRecharge('details')">明细</button>
      </div>
      <div class="couponBox">
        <p v-text="accountinfo.couponcount" />
        <p>可用优惠券</p>
        <button>&nbsp;</button>
      </div>
    </div>
    <div class="second">
      <img :src="bannerSrc" alt="userspace_banner.png">
    </div>
    <div class="third">
      <div class="third-left">
        <p class="title">站内信件</p>
        <hr>
      </div>
      <div class="third-right">
        <p class="title">新闻资讯</p>
        <hr>
        <div class="content">
          <span
            v-for="n in news.slice(0, 5)"
            :key="n.title"
            v-text="n.date + ' ' + n.title"
          />
        </div>
      </div>
    </div>
    <div class="fourth">
      <p class="title">包裹状态</p>
      <hr>
      <div class="packageStatusTable">
        <el-table :data="orderStatus" stripe border style="width: 100%">
          <el-table-column prop="dist" label="目的地" align="center" />
          <el-table-column
            prop="transport"
            label="运输方式"
            align="center"
            width="200"
          />
          <el-table-column
            prop="order"
            label="订单号"
            align="center"
            width="200"
          />
          <el-table-column prop="recipient" label="收件人" align="center" />
          <el-table-column prop="status" label="订单状态" align="center" />
        </el-table>
      </div>
    </div>
    <div class="fifth">
      <div class="contact">
        <div class="contact-left">
          <svg-icon icon-class="dianhua" />
          <p>客服邮箱 | 123456789@qq.com</p>
        </div>
        <div class="contact-right">
          <svg-icon icon-class="youxiang" />
          <p>工作时间 | 周一至五 00:00-23:59</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import axios from 'axios'

export default {
  data() {
    return {
      defaultAvatar:
        'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fp3.itc.cn%2Fimages03%2F20200516%2F0346a117a87b453fbd6d7b1d6698923d.jpeg&refer=http%3A%2F%2Fp3.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1652066159&t=0f0450558b1e6afe784fbb8463a9722d',
      bannerSrc: '/static/images/userspace_banner.png',
      letter: [],
      news: [],
      userpackage: [],
      orderStatus: [
        {
          dist: '中国大陆',
          transport: '法国邮政',
          order: '956000000102567194',
          recipient: '韩梅梅',
          status: '运输中'
        },
        {
          dist: '中国大陆',
          transport: 'ParcelForce自送邮局',
          order: '956000000102567256',
          recipient: '韩梅梅',
          status: '运输中'
        }
      ]
    }
  },
  computed: {
    ...mapGetters(['baseinfo', 'accountinfo'])
  },
  created() {
    this.getData()
  },

  methods: {
    getData() {
      axios.get('/static/data/newsnewsData.json').then((res) => {
        this.news = res.data
      })
    },
    handleClickRecharge(tab) {
      this.$router.push(`/webapp/userspace/accountManager/${tab}`)
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
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}
.first {
  display: flex;
  justify-content: space-between;
  padding: 2rem 4rem;
  background-color: rgba(243, 249, 250, 0.95);
  margin-bottom: 1rem;
  button {
    cursor: pointer;
    border: none;
    width: 5rem;
    height: 2rem;
    color: #fff;
    background-color: #d35400;
    border-radius: 5px;
    &:hover {
      background-color: #e67e22;
    }
    &:active {
      transform: scale(0.95);
    }
  }
  .avaterBox {
    width: 15rem;
    height: 15rem;
    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
      border-radius: 50%;
    }
  }
  .infoBox {
    width: 20%;
    margin-right: 2rem;
    p {
      font-size: 1.8rem;
      font-weight: 700;
      color: #2c3e50;
      &:nth-child(2) {
        display: inline-block;
        font-size: 0.5rem;
      }
    }
  }
  .balanceBox {
    width: 15%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    p {
      margin: 0;
      margin-bottom: 1.5rem;
      &:nth-child(1) {
        font-size: 3rem;
        font-weight: 500;
        color: #3498db;
        // margin-bottom: 2rem;
      }
      &:nth-child(2) {
        font-size: 1.5rem;
        font-weight: 200;
        // margin-bottom: 2rem;
      }
    }
  }
  .rebateBox {
    width: 15%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    p {
      margin: 0;
      margin-bottom: 1.5rem;
      &:nth-child(1) {
        font-size: 3rem;
        font-weight: 500;
        color: #3498db;
        // margin-bottom: 2rem;
      }
      &:nth-child(2) {
        font-size: 1.5rem;
        font-weight: 200;
        // margin-bottom: 2rem;
      }
    }
  }
  .couponBox {
    width: 15%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    p {
      margin: 0;
      margin-bottom: 1.5rem;
      &:nth-child(1) {
        font-size: 3rem;
        font-weight: 500;
        color: #3498db;
        // margin-bottom: 2rem;
      }
      &:nth-child(2) {
        font-size: 1.5rem;
        font-weight: 200;
        // margin-bottom: 2rem;
      }
    }
    button {
      visibility: hidden;
    }
  }
}
.second {
  width: 100%;
  margin-bottom: 1rem;
  img {
    width: 100%;
  }
}
.third {
  padding: 0 2rem;
  display: flex;
  justify-content: space-between;
  height: 180px;
  background-color: rgba(243, 249, 250, 0.95);
  hr {
    border: 1px solid #2980b9;
  }
  .third-left {
    width: 48%;
    p {
      font-size: 1.2rem;
      &:nth-child(1) {
        padding: 0 1rem;
        line-height: 1.5rem;
        border-left: 5px solid #2980b9;
      }
    }
  }
  .third-right {
    width: 48%;
    p {
      font-size: 1.2rem;
      &:nth-child(1) {
        padding: 0 1rem;
        line-height: 1.5rem;
        border-left: 5px solid #2980b9;
      }
    }
    .content {
      display: flex;
      flex-direction: column;
      span {
        font-size: 1.3rem;
        margin: 0.5rem 0;
      }
    }
  }
}
.fourth {
  width: 100%;
  padding: 0 2rem;
  height: 330px;
  background-color: rgba(243, 249, 250, 0.95);
  margin-bottom: 1rem;
  p {
    font-size: 1.2rem;
    &:nth-child(1) {
      padding: 0 1rem;
      line-height: 1.5rem;
      border-left: 5px solid #2980b9;
    }
  }
  hr {
    border: 1px solid #2980b9;
  }
}
.fifth {
  width: 100%;
  padding: 13px 2rem;
  height: 86px;
  background-color: rgba(243, 249, 250, 0.95);
  .contact {
    display: flex;
    justify-content: space-between;
    align-items: center;
    div {
      flex: 1;
      display: flex;
      justify-content: flex-start;
      align-items: center;
      .svg-icon {
        width: 60px;
        height: 60px;
        color: #3498db;
        margin-right: 1rem;
      }
      p {
        font-size: 1.5rem;
      }
    }
  }
}
</style>
