<template>
  <div class="container">
    <div class="boxWrapper">
      <div v-for="i in initData.info" :key="i.title" class="box">
        <p class="title" v-text="i.title" />
        <div class="msgWrapper">
          <template v-for="li in i.list">
            <div v-if="/loadPicture/.test(li)" :key="li" class="msgimgWrapper">
              <img :src="'/static/serviceData/' + li.split('/')[1]">
            </div>
            <p
              v-else
              :key="li"
              :class="li.slice(-1) === ':' ? 'subtitle' : 'msg'"
              v-text="li"
            />
          </template>
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
  .box {
    margin: 2rem 0;
    .title {
      font-size: 1.5rem;
      display: inline-block;
      padding: 0.5rem 2rem;
      color: #fff;
      background-color: #2980b9;
    }
    .msgWrapper {
      font-size: 1.2rem;
      .subtitle {
        margin: 2rem 0;
      }
      .msg {
        padding: 0 4rem;
        font-size: 1.2rem;
        line-height: 2rem;
      }
      .msgimgWrapper {
        width: 100%;
        text-align: center;
      }
    }
  }
}
</style>
