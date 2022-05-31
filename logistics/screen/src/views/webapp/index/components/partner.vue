<template>
  <div class="container">
    <div class="main">
      <div class="title">
        <p>合作伙伴</p>
        <p>Partner</p>
      </div>
      <div class="boxWrapper">
        <div v-for="item in items" :key="item.src" class="box">
          <img :src="item.src" alt="">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'DirectMail',
  data() {
    return {
      items: [
        // {
        //   title: 'PARCEL FORCE',
        //   src: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fimages%2Fparcelforce.2e16d0ba.fill-160x70.jpg'
        // },
        // {
        //   title: 'bpost',
        //   src: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fimages%2FBpost.2e16d0ba.fill-160x70.jpg'
        // },
        // {
        //   title: 'EMS',
        //   src: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fimages%2FEMS.2e16d0ba.fill-160x70.jpg'
        // },
        {
          title: 'ups',
          src: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fimages%2FUPS.2e16d0ba.fill-160x70.jpg'
        }
        // {
        //   title: '中国邮政',
        //   src: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fimages%2Fzhongguo-you-zheng.2e16d0ba.fill-160x70.jpg'
        // },
        // {
        //   title: '菜鸟裹裹',
        //   src: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fimages%2FCai_Niao_Guo_Guo_.2e16d0ba.fill-160x70.jpg'
        // },
        // {
        //   title: '淘宝网',
        //   src: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fimages%2FTao_Bao_Wang_.2e16d0ba.fill-160x70.jpg'
        // },
        // {
        //   title: '顺丰速运',
        //   src: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fimages%2FShun_Feng_Su_Di_.2e16d0ba.fill-160x70.jpg'
        // }
      ]
    }
  }
}
</script>
"
<style lang="scss" scoped>
.container {
  position: relative;
  width: 100%;
  background-color: rgb(244, 244, 244);
}
.main {
  position: relative;
  padding: 20px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  justify-content: center;
  p:nth-child(1) {
    color: #0984e3;
    text-align: center;
    font-size: 3rem;
    // font-weight: 700;
    margin: 1.5rem 0;
  }
  p:nth-last-child(1) {
    text-align: center;
    font-size: 1.5rem;
    font-weight: 700;
  }
}
.boxWrapper {
  margin-top: 2rem;
  position: relative;
  width: 100%;
  display: flex;
  // justify-content: space-between;
  justify-content: center;
  overflow-x: scroll;
  overflow-y: hidden;
  &::-webkit-scrollbar {
    display: none;
  }
  .box {
    cursor: pointer;
    background-color: #fff;
    margin: 10px;
    padding: 10px;
    width: 20%;
    display: flex;
    flex-direction: column;
    align-items: center;
    transform: scale(1);
    transition: transform 0.5s;
    &:hover {
      box-shadow: 2px 2px 5px 3px rgba(0, 0, 0, 0.1);
      transform: scale(1.1);
    }
  }
}
</style>
