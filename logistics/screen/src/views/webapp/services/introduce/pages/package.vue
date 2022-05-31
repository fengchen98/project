<template>
  <div class="container">
    <div class="banner">
      <img src="/static/images/banner.jpg" alt="banner">
    </div>
    <div class="content">
      <h1>行李直接下单</h1>
      <div class="boxWrapper">
        <div class="box-1">
          <p class="title">第一步: 行李邮寄准备</p>
          <div class="box-flex">
            <div class="box-left">
              <svg-icon icon-class="bigpackage" />
              <button @click="handleClickBox">购买纸箱</button>
            </div>
            <div class="box-right">
              <p>1、打包行李</p>
              <p>
                物流提供空运，海运行李邮寄方式，邮寄前建议将行李装进纸箱内进行邮寄，行李邮寄纸箱可自行准备或在包装材料商城购买。
              </p>
              <p>2、包裹重量</p>
              <p>
                尽量控制行李总重量在25KG内，超出25KG可补差价继续邮寄，最大重量不允许超过30KG，否则将会退运。
              </p>
              <p>1、打包行李</p>
              <p>
                在打包下单后，需要您自行打印快递面单粘贴至外箱上，否则将无法取件成功。
              </p>
            </div>
          </div>
        </div>
        <hr>
        <div class="box-2">
          <p class="title">第二步: 选择行李邮寄方案</p>
          <div class="box-flex">
            <div class="box-left">
              <svg-icon icon-class="xingli" />
              <p>空运行李</p>
            </div>
            <div class="box-middle">
              <p>取件方式: 全英范围支持取件（伦敦、曼城部分地区免费取件）</p>
              <p>
                运费计价:
                25KG内运费低至<span>￡72.71</span>，超出25KG需补差价，重量上限不超过30KG
              </p>
              <p>
                尺寸要求: 单边尺寸最长不超过1米，三遍之和加起来不能超过150厘米
              </p>
              <p>
                服务时效:
                <span>约7-14天</span> （受疫情影响目前时效存在3-7天延误）
              </p>
            </div>
            <div class="box-right">
              <button @click="handleClickService(7)">了解详情</button>
            </div>
          </div>
          <div class="box-flex">
            <div class="box-left">
              <svg-icon icon-class="service_8" />
              <p>海运行李</p>
            </div>
            <div class="box-middle">
              <p>取件方式: 全英范围支持取件（伦敦、曼城部分地区免费取件）</p>
              <p>运费计价: 30KG内运费低至<span>￡49.00</span></p>
              <p>
                尺寸要求: 单边尺寸最长不超过1米，三遍之和加起来不能超过150厘米
              </p>
              <p>服务时效: <span>约45-65天</span></p>
            </div>
            <div class="box-right">
              <button @click="handleClickService(8)">了解详情</button>
            </div>
          </div>
        </div>
        <hr>
        <div class="box-3">
          <p class="title">第三步: 行李邮寄须知&售后服务</p>
          <p>注意事项</p>
          <li>
            1、行李专线适用于个人使用过的衣物和生活用品，
            为了清关顺畅，请不要放置任何崭新奢侈品，保健品，首饰，礼品等，更不要放到未拆封的包装盒内。
          </li>
          <li>
            2、请确保行李包裹中没有违禁品：常见违禁品例如喷雾,
            脱毛膏，含酒精的液体，指甲油，珠宝现金，香水，口红，喷雾剂等，违禁品详细列表
          </li>
          <li>
            3、包裹取件后一般会在2个工作日内更新，若包裹邮寄后3个工作日都没有更新，请通过以下任一联系客服咨询。
          </li>
          <p>关于取件</p>
          <li>
            1、包裹出单成功后，并确认将所有Label（面单）牢固的黏贴在纸箱最大面积的一面。
          </li>
          <li>
            2、除了自营班车取件服务以外，其他服务均属于第三方公司上门取件;
          </li>
          <li>
            第三方取件公司上门取件时不会打电话也无法指定时间上门取件，默认取件时间为9.00-17.00，这些公司的取件服务需在取件地址等待一整天;
          </li>
          <li>
            3、如果取件地址为学生公寓或者取件地址有Reception，平常英国派送包裹都只会送到前台，那么只需要将包裹贴好面单放在Reception即可，司机只会去Reception查看是否有包裹需要取件，不会打电话通知，也不会去具体的房号敲门。
          </li>
          <li>
            4、重磅推出自营班车取件，与第三方取件公司不同的是，我们的司机可实时查询位置，提前预估取件时间，且找不到地址也会电话通知，不会错过取件；包裹取件时自带实时扫描枪，实时更新物流；取件后当天就可以回到英国主仓安排发出。
          </li>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    handleClickBox() {
      this.$router.push('/webapp/mall/material')
    },
    handleClickService(id) {
      this.$router.push(`/webapp/service/freight/${id}`)
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
  width: 55%;
  margin: 0 auto;
  padding: 10vh 0;
  h1 {
    font-size: 3rem;
    font-weight: 400;
    color: #fff;
    margin: 0;
    margin-bottom: 5rem;
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
    .box-1 {
      padding: 1rem 5%;
      font-size: 1.5rem;
      line-height: 2rem;
      .title {
        font-size: 2.5rem;
        font-weight: 600;
        color: #2980b9;
      }
      .box-flex {
        display: flex;
        justify-content: space-around;
        .box-left {
          display: flex;
          flex-direction: column;
          justify-content: space-around;
          align-items: center;
          width: 20%;
          //   text-align: center;
          .svg-icon {
            width: 15rem;
            height: 15rem;
          }
          button {
            cursor: pointer;
            border: none;
            background-color: #e67e22;
            padding-top: 2rem 4rem;
            width: 12rem;
            height: 4rem;
            border-radius: 5px;
            color: #fff;
            margin: 0 auto;
            &:hover {
              background-color: #f39c12;
            }
          }
        }
        .box-right {
          width: 60%;
          p {
            margin: 0;
            line-height: 2.5rem;
          }
        }
      }
    }
    .box-2 {
      span {
        color: #e67e22;
      }
      padding: 1rem 5%;
      font-size: 1.5rem;
      line-height: 2rem;
      .title {
        font-size: 2.5rem;
        font-weight: 600;
        color: #2980b9;
      }
      .box-flex {
        display: flex;
        justify-content: space-between;
        border: 1px solid #bdc3c7;
        padding: 2rem;
        margin: 2rem 0;
        .box-left {
          display: flex;
          flex-direction: column;
          justify-content: space-around;
          align-items: center;
          width: 15%;
          .svg-icon {
            width: 8rem;
            height: 8rem;
          }
        }
        .box-middle {
          width: 70%;
          padding: 0 2rem;
          p {
            margin: 0;
            line-height: 2.8rem;
          }
        }
        .box-right {
          width: 15%;
          text-align: center;
          button {
            cursor: pointer;
            border: none;
            background-color: #e67e22;
            width: 10rem;
            height: 4rem;
            border-radius: 5px;
            color: #fff;
            &:hover {
              background-color: #f39c12;
            }
          }
        }
      }
    }
    .box-3 {
      padding: 1rem 5%;
      font-size: 1.5rem;
      line-height: 2rem;
      .title {
        font-size: 2.5rem;
        font-weight: 600;
        color: #2980b9;
      }
      li {
        line-height: 3rem;
        list-style: none;
        padding-left: 2rem;
      }
    }
  }
}
</style>
