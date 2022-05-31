<template>
  <div class="Ten-tab">
    <div class="borstyle01" />
    <div class="tab">
      <div class="hdtab">
        <div
          class="tabname01"
          data-id="1"
          :class="{ choose: tab == 1 }"
          @click="tabChange"
        >
          取件服务
        </div>
        <div
          class="tabname02"
          data-id="2"
          :class="{ choose: tab == 2 }"
          @click="tabChange"
        >
          自送投递点
        </div>
      </div>
      <div v-show="tab == 1" class="tab01con">
        <p class="contitle">取件范围覆盖全英, 24小时上门取件</p>
        <div class="con01">
          <div class="one">
            <img src="/static/images/tab01-1.png" alt="">
            <p class="name">UPS上门取件</p>
            <button>立即查看</button>
          </div>
          <div class="two">
            <img src="/static/images/tab01-2.png" alt="">
            <p class="name">批量托盘取件</p>
            <button>立即查看</button>
          </div>
          <div class="three">
            <img src="/static/images/tab01-3.png" alt="">
            <p class="name">建设中</p>
            <button>立即查看</button>
          </div>
          <div class="four">
            <img src="/static/images/tab01-4.png" alt="">
            <p class="name">建设中</p>
            <button>立即查看</button>
          </div>
        </div>
      </div>
      <div v-show="tab == 2" class="tab02con">
        <p class="contitle">自投点范围覆盖全英，24小时可投递件</p>
        <div class="con02">
          <div class="one">
            <img src="/static/images/tab02-1.png" alt="">
            <p class="name">自送仓库</p>
            <button>立即查看</button>
          </div>
          <div class="two">
            <img src="/static/images/tab02-2.png" alt="">
            <p class="name">UPS投递点</p>
            <button>立即查看</button>
          </div>
          <div class="three">
            <img src="/static/images/tab02-3.png" alt="">
            <p class="name">自助下单</p>
            <button>立即查看</button>
          </div>
          <div class="four">
            <img src="/static/images/tab02-4.png" alt="">
            <p class="name">自行打包自送</p>
            <button>立即查看</button>
          </div>
        </div>
      </div>
    </div>
    <div class="borstyle02" />
  </div>
</template>

<script>
export default {
  data() {
    return {
      tab: 1
    }
  },
  methods: {
    tabChange: function(e) {
      const tabId = e.target.dataset.id
      this.tab = tabId
    }
  }
}
</script>

<style scoped>
.Ten-tab {
  position: relative;
  height: 450px;
  width: 100%;
  background-color: #f5f5f5;
}
.borstyle02 {
  width: 0;
  height: 0;
  border-top: 30px solid #f5f5f5;
  border-left: 50vw solid #fff;
  border-right: 50vw solid #fff;
}
.borstyle01 {
  width: 0;
  height: 0;
  border-top: 30px solid #fff;
  border-left: 50vw solid transparent;
  border-right: 50vw solid transparent;
  border-bottom: 390px solid transparent;
}
.tab {
  position: absolute;
  top: 50px;
  width: 82%;
  height: 355px;
  margin-left: 9%;
}
.hdtab {
  height: 40px;
  background-color: #fff;
  display: flex;
  justify-content: center;
  align-content: center;
}
.tabname01,
.tabname02 {
  width: 97px;
  height: 40px;
  border: 1px solid #e3e7ed;
  align-items: center;
  text-align: center;
  line-height: 40px;
  font-size: 14px;
  cursor: pointer;
}
.tabname01:hover,
.tabname02:hover {
  color: #58a2ff;
}
.tabname01 {
  border-right: none;
}
.choose {
  background-color: #00c2f3;
  color: #fff !important;
}

.tab01con {
  justify-content: center;
  width: 100%;
  height: 315px;
  position: absolute;
  top: 40px;
  flex-wrap: wrap;
}
.contitle {
  width: 100%;
  height: 80px;
  line-height: 80px;
  text-align: center;
  font-size: 14px;
  color: #999999;
}
.con01 {
  margin: 0 auto;
  display: flex;
  width: 960px;
  height: 220px;
  border: 1px solid #999;
}
.one,
.two,
.three,
.four {
  width: 25%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
img {
  width: 68px;
  height: 68px;
  margin-top: 40px;
}
.name {
  font-size: 14px;
  color: #999;
  margin-top: 12px;
  margin-bottom: 12px;
}
button {
  width: 110px;
  height: 32px;
  border-radius: 7%;
  font-size: 14px;
  background-color: #00c2f3;
  color: white;
  border: none;
  cursor: pointer;
}
.one,
.two,
.three {
  border-right: 1px solid #999;
}

.tab02con {
  width: 100%;
  height: 315px;
}
.contitle {
  width: 100%;
  height: 80px;
  line-height: 80px;
  text-align: center;
  font-size: 14px;
  color: #999999;
}
.con02 {
  margin: 0 auto;
  display: flex;
  width: 960px;
  height: 220px;
  border: 1px solid #999;
}
.one,
.two,
.three,
.four {
  width: 25%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
img {
  width: 68px;
  height: 68px;
  margin-top: 40px;
}
.name {
  font-size: 14px;
  color: #999;
  margin-top: 12px;
  margin-bottom: 12px;
}
button {
  width: 110px;
  height: 32px;
  border-radius: 7%;
  font-size: 14px;
  background-color: #00c2f3;
  color: white;
  border: none;
  cursor: pointer;
}
.one,
.two,
.three {
  border-right: 1px solid #999;
}
</style>
