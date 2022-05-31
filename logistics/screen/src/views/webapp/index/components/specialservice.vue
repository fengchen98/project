<template>
  <div class="container">
    <div class="main">
      <div class="title">
        <p>特色服务</p>
        <p>Special Service</p>
      </div>
      <div class="boxWrapper">
        <div
          v-for="item in initData"
          :key="item.title"
          class="box"
          @click="handleClick(item.tag)"
        >
          <svg-icon :icon-class="item.icon" />
          <p v-text="item.service" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import mixinInterface from '@/mixin/interface'

export default {
  name: 'Specialservice',
  // mixins: [mixinInterface],
  data() {
    return {
      initData: [
        {
          service: '奶粉商城',
          icon: 'naifen',
          link: '/webapp/service/introduce/',
          more: '暂无',
          tag: 'milkpowder'
        },
        {
          service: '中国转运',
          icon: 'zhuanyun',
          link: '/webapp/service/introduce/',
          more: '暂无',
          tag: 'transport'
        },
        {
          service: '行李直接下单',
          icon: 'xingli',
          link: '/webapp/service/introduce/',
          more: '暂无',
          tag: 'package'
        },
        {
          service: 'SAD退税文件申请',
          icon: 'tuishui',
          link: '/webapp/service/introduce/',
          more: '暂无',
          tag: 'sad'
        }
      ]
    }
  },
  methods: {
    handleClick(tag) {
      window.location.hash = `/webapp/service/introduce/${tag}`
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
  width: 60%;
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
  justify-content: space-between;
  flex-wrap: wrap;
  .box {
    cursor: pointer;
    background-color: #fff;
    margin: 10px;
    padding: 10px;
    width: 23%;
    display: flex;
    flex-direction: column;
    align-items: center;
    transform: scale(1);
    transition: transform 0.5s;
    .svg-icon {
      width: 6rem;
      height: 6rem;
    }
    p {
      margin-top: 2rem;
    }
    &:hover {
      box-shadow: 2px 2px 5px 3px rgba(0, 0, 0, 0.1);
      transform: scale(1.1);
    }
  }
}
</style>
