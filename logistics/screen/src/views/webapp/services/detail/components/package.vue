<template>
  <div class="container">
    <div class="box">
      <div class="box-left">
        <svg-icon icon-class="bigpackage" />
      </div>
      <div class="box-right">
        <p
          v-for="(li, index) in initData.Preparepackage"
          :key="li"
          v-text="index + 1 + '. ' + li"
        />
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
  }
}
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  width: 100%;
  box-sizing: border-box;
}
.box {
  width: 95%;
  margin: 0 auto;
  position: relative;
  display: flex;
  justify-content: center;
  .box-left {
    margin-right: 2rem;
    .svg-icon {
      width: 15rem;
      height: 15rem;
    }
  }
  .box-right {
    p {
      font-size: 1.5rem;
      font-weight: 500;
    }
  }
}
</style>
