<template>
  <!-- 组件外容器 -->
  <div class="container">
    <!--  图表容器  -->
    <div class="titleWrap">
      <p class="first-text" style="text-align: center">服务介绍</p>
      <p class="first-text2" style="text-align: center">Service description</p>
    </div>
    <div class="mainWrap">
      <div v-for="item in initData" :key="item.title" class="items">
        <div class="item-left">
          <i :class="item.icon" />
        </div>
        <div class="item-right">
          <div class="title" v-text="item.title" />
          <div class="content" v-text="item.content" />
          <a :href="item.link" class="link">查看更多>></a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ServiceBox',
  data() {
    return {
      initData: [
        {
          icon: 'el-icon-watch-1',
          title: '贵重物品',
          content:
            '为贵重物品量身定制的奢侈品包税专线，无物品价值限制，可额外买..',
          link: '#'
        },
        {
          icon: 'el-icon-takeaway-box',
          title: '大件物品',
          content:
            '请下单前先通过下列尺寸检查器检查物品尺寸。',
          link: '#'
        },
        {
          icon: 'el-icon-present',
          title: '奶粉包税',
          content:
            '神州物流奶粉包税专线是全英最稳定，网络最全的奶粉...',
          link: '#'
        },
        {
          icon: 'el-icon-suitcase-1',
          title: '行李专线',
          content:
            '为时效快：7-10天空运到家，邮政绿色清关(非正常的清关延误不...',
          link: '#'
        },
        {
          icon: 'el-icon-lollipop',
          title: '杂货包税',
          content:
            '适合邮寄低价食品/保健品/洗护日用品，尤其适合邮寄成人奶粉/...',
          link: '#'
        },
        {
          icon: 'el-icon-box',
          title: '小包裹包税',
          content:
            '请下单前先通过下列尺寸检查器检查物品尺寸。',
          link: '#'
        }
      ]
    }
  },
  mounted() {
  },
  destorys() {
  },
  methods: {}
}
</script>

<style scoped>
.container {
  width: 80vw;
  height: 70vh;
  /* background-color: teal; */
  margin: 0 auto;
  position: relative;
}
.titleWrap {
  width: 20vw;
  height: 5vh;
  margin: 0 auto;
  position: relative;
  margin-bottom: 2%;
}
.titleWrap::after {
  content: '';
  width: 2px;
  height: 20px;
  background-color: rgb(20, 152, 240);
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}
.first-text {
  font-size: 23px;
  font-weight: 540;
  color: rgb(27, 27, 27);
}
.first-text2 {
  font-size: 16px;
  color: rgb(59, 59, 59);
  margin-top: -20px;
}

.mainWrap {
  position: relative;
  display: flex;
  width: 80%;
  height: 25vw;
  justify-content: space-around;
  align-content: space-around;
  flex-wrap: wrap;
  margin: 0 auto;
  /* background-color: rgb(212, 21, 21); */
}
.items {
  display: flex;
  flex: 26%;
  height: 30%;
  border-radius: 25px;
  cursor: pointer;
}
.items:hover {
  background-color: #17c0eb;
  color: #fff;
}
.item-left {
  flex: 1;
  font-size: 5em;
  /* color: #5e5959; */
  position: relative;
  margin: 0 auto;
  display: flex;
  justify-content: center;
  align-items: center;
}
.item-right {
  flex: 2;
  width: 50%;
  padding: 0 20px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}
.title {
  font-size: 1.2rem;
  font-weight: 600;
}
.content {
  font-size: 0.7rem;
  /* color: #a6a2a2; */
}
.link {
  color: #e99f4f;
  font-size: 12px;
}
</style>
