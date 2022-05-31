<template>
  <div class="container">
    <div class="main">
      <h2>我们的优势</h2>
      <div class="box-wrapper">
        <div v-for="d in dataSource" :key="d.icon" class="box">
          <div class="box-left">
            <svg-icon :icon-class="d.icon" />
          </div>
          <div class="box-right">
            <p v-text="d.title" />
            <li v-for="li in d.list" :key="li" v-text="li" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dataSource: [
        {
          icon: 'service',
          title: '追求品质 用户第一',
          list: [
            '提供优质线下门店体验和服务',
            '追求快递服务时效和稳定，不辜负用户所托',
            '24/7技术支持，全天候客服在线服务'
          ]
        },
        {
          icon: 'quality',
          title: '自主创新 聚焦精细化运营',
          list: [
            '24/7智能下单，一秒出单，客户一键打印',
            '包裹全程追踪，一单到底，实时数据反馈',
            '自主研发智能化物流运营系统，追求服务体验'
          ]
        },
        {
          icon: 'chuangxin',
          title: '产品丰富 渠道稳定',
          list: [
            '服务覆盖全类型个人物品和电商产品',
            '国际航空货运资源丰富，国内多清关口岸布局',
            '闪电出货，快速清关，时效稳定'
          ]
        }
      ]
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  width: 100%;
  .main {
    width: 50%;
    margin: 0 auto;
    text-align: center;
    h2 {
      font-size: 3rem;
      color: rgb(3, 69, 153);
    }
    .box-wrapper {
      display: flex;
      flex-direction: column;
      justify-content: space-around;
      .box {
        width: 100%;
        // padding: 3rem;
        display: flex;
        justify-content: space-between;
        margin: 1rem 0;
        .box-left {
          width: 30%;
          display: flex;
          justify-content: center;
          align-items: center;
          background-color: rgba(243, 249, 250, 0.897);
          .svg-icon {
            width: 15rem;
            height: 15rem;
          }
        }
        .box-right {
          width: 65%;
          padding: 2rem;
          background-color: rgba(243, 249, 250, 0.897);
          text-align: start;
          p {
            color: rgb(3, 69, 153);
            font-size: 1.8rem;
            margin: 0;
          }
          li {
            margin: 1.5rem;
            font-size: 1.5rem;
            color: rgba(127, 140, 141, 1);
          }
        }
      }
    }
  }
}
</style>
