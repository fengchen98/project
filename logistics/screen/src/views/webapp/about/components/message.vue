<template>
  <div class="msgBox">
    <h2 v-text="msg.title" />
    <p v-for="i in msg.p" :key="i" v-text="i" />
  </div>
</template>

<script>
export default {
  data() {
    return {
      msg: {
        title: '关于神州物流',
        p: [
          '神州物流是2013年由美国硅谷投资，在英国注册的跨境物流公司。主营核心服务是欧洲至中国的国际快递，海淘转运和大宗货物空运。自成立以来，我们已服务英国及欧洲的数百万客户，覆盖留学生，海淘买手以及跨境电商。我们每年承运的包裹数量百万件， 包含各类个人物品，文件， 海淘商品，归国行李等等。',
          '神州物流业务范围覆盖英国，德国，爱尔兰，意大利，法国和西班牙等多个欧洲国家。我们不仅有重点城市的线下实体店， 完备的自营取件车队， 还有多个现代化运营中心， 数千个第三方合作的投递点。我们可在中国多个清关口岸办理清关，为我们的客户提供最快速的门到门跨境体验。神州物流与国际巨头英国皇家邮政Roymail, UPS, DHL, bpost, DPD 等合作多年，并在2017，成为英国唯一的阿里巴巴旗下菜鸟物流的官方指定合作物流商，为各类淘宝天猫海外商家提供助力！',
          '神州物流不忘初心，我们坚持提供最稳定，最负责，最安心的服务，我们愿与您一路同行，是您最佳的物流选择！'
        ]
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.msgBox {
  position: relative;
  width: 50%;
  // height: 600px;
  background-color: rgba(243, 249, 250, 0.95);
  margin: 0 auto;
  // transform: translate(-50%, -40%);
  text-align: center;
  padding: 2rem 4rem;
  font-family: -apple-system, Helvetica Neue, Arial, PingFang SC,
    Hiragino Sans GB, STHeiti, Microsoft YaHei, Microsoft JhengHei,
    Source Han Sans SC, Noto Sans CJK SC, Source Han Sans CN, Noto Sans SC,
    Source Han Sans TC, Noto Sans CJK TC, WenQuanYi Micro Hei, SimSun,
    sans-serif;
  h2 {
    font-size: 3rem;
    font-weight: 800;
    margin-bottom: 4rem;
    color: rgb(3, 69, 153);
  }
  p {
    text-align: start;
    font-size: 1.5rem;
    line-height: 2.5rem;
    margin: 1rem 0;
    color: rgba(127, 140, 141, 1);
  }
}
</style>
