<template>
  <div class="container">
    <div class="mainWrapper">
      <div class="left-box">
        <h2>最稳定，最负责，最安心的国际物流</h2>
        <p>
          神州物流是2013年由美国硅谷投资，在英国注册的跨境物流公司。主营核心服务是欧洲至中国的国际快递，海淘转运和大宗货物空运。自成立以来，我们已服务英国及欧洲的数百万客户，覆盖留学生，海淘买手以及跨境电商。我们每年承运的包裹数量百万件，
          包含各类个人物品，文件， 海淘商品，归国行李等等。
        </p>
        <p>
          神州物流业务范围覆盖英国，德国，爱尔兰，意大利，法国和西班牙等多个欧洲国家。我们不仅有重点城市的线下实体店，
          完备的自营取件车队， 还有多个现代化运营中心，
          数千个第三方合作的投递点。我们可在中国多个清关口岸办理清关，为我们的客户提供最快速的门到门跨境体验。神州物流与国际巨头英国皇家邮政Roymail,
          UPS, DHL, bpost, DPD
          等合作多年，并在2017，成为英国唯一的阿里巴巴旗下菜鸟物流的官方指定合作物流商，为各类淘宝天猫海外商家提供助力！
        </p>
        <p>
          神州物流不忘初心，我们坚持提供最稳定，最负责，最安心的服务，我们愿与您一路同行，是您最佳的物流选择！
        </p>
        <div v-for="item in advs" :key="item.icon" class="adv-box">
          <svg-icon :icon-class="item.icon" />
          <span v-text="item.content" />
        </div>
      </div>
      <div class="right-box">
        <el-carousel height="58rem">
          <el-carousel-item v-for="item in imgArr" :key="item.src">
            <img :src="item.src" alt="">
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      imgArr: [
        {
          src: '/static/images/1.jpg'
        },
        {
          src: '/static/images/2.jpg'
        },
        {
          src: '/static/images/3.jpg'
        }
      ],
      advs: [
        {
          content: '最稳定 | 欧洲覆盖最广，国内多清关口岸布局',
          icon: 'wending'
        },
        {
          content: '最负责 | 为您的包裹保驾护航',
          icon: 'anquan'
        },
        {
          content: '最安心 | 售后客服永远在线为您解忧',
          icon: 'kekao'
        }
      ]
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
.mainWrapper {
  display: flex;
  .left-box {
    flex: 1;
    padding: 1rem 0rem;
    padding-right: 2rem;
    h2 {
      color: #2e86de;
      margin-top: 0;
      font-size: 3rem;
    }
    p {
      font-size: 1.4rem;
      font-weight: 200;
      line-height: 3rem;
    }
  }
  .right-box {
    flex: 1;
    position: relative;
    padding: 1rem 1rem;
    img {
      width: 100%;
      height: 100%;
    }
  }
}
.adv-box {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding: 0.5rem 0;
  .svg-icon {
    width: 3rem;
    height: 3rem;
    margin-right: 1rem;
  }
  span {
    color: grey;
    font-size: 1.6rem;
  }
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
