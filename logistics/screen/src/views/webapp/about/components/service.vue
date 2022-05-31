<template>
  <div class="container">
    <div class="main">
      <h2>我们的服务</h2>
      <div class="box-wrapper">
        <div v-for="d in dataSource" :key="d.icon" class="box">
          <svg-icon :icon-class="d.icon" />
          <span v-text="d.title" />
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
          icon: 'scgl',
          title: '物流生产管理系统'
        },
        {
          icon: 'wlys',
          title: '跨境物流运输系统'
        },
        {
          icon: 'wlzz',
          title: '物流实时追踪系统'
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
      justify-content: space-between;
      .box {
        width: 30%;
        height: 20rem;
        padding: 3rem;
        background-color: rgba(243, 249, 250, 0.897);
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        .svg-icon {
          width: 15rem;
          height: 15rem;
        }
        span {
          margin-top: 3rem;
          font-size: 1.5rem;
          color: rgba(127, 140, 141, 1);
        }
      }
    }
  }
}
</style>
