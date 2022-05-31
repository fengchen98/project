<template>
  <div class="container">
    <div class="boxWrapper">
      <div v-for="item in initData.delivery" :key="item.title" class="box">
        <div class="titleBox">
          <svg-icon :icon-class="item.icon" />
          <p v-text="item.title" />
        </div>
        <div v-for="li in item.list" :key="li" class="listBox">
          <i class="el-icon-check" />
          <span v-text="li" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    initData: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {}
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
.boxWrapper {
  width: 95%;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  .box {
    width: 32%;
    background: rgba(232, 246, 254, 1);
    padding: 1rem;
    .titleBox {
      display: flex;
      align-items: center;
      margin: 1rem 1rem 2rem 1rem;
      .svg-icon {
        width: 3rem;
        height: 3rem;
      }
      p {
        font-size: 1.8rem;
        font-weight: 400;
        margin: 1rem;
      }
    }
    .listBox {
      padding: 0 1rem;
      color: #0984e3;
      i {
        font-size: 2rem;
        margin-right: 1rem;
      }
      span {
        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
        font-size: 1rem;
        line-height: 1.5rem;
      }
    }
  }
}
</style>
