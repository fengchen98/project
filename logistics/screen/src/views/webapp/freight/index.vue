<template>
  <div class="container">
    <div class="banner">
      <p>运费与报价</p>
      <img src="/static/images/cost_banner.png" alt="">
    </div>
    <div class="mainBox">
      <div class="freightSearch">
        <freightSearch />
      </div>
      <div>
        <freightTable />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {
    freightSearch: () => import('./components/freightSearch.vue'),
    freightTable: () => import('./components/freightTable.vue')
  }
}
</script>

<style lang="scss" scoped>
.container {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  width: 100%;
  transition: 0s;
}
.banner {
  position: relative;
  p {
    position: absolute;
    font-size: 3rem;
    font-weight: 700;
    color: #fff;
    transform: translateX(25vw);
  }
  img {
    width: 100%;
  }
}
.freightSearch {
  position: absolute;
  width: 100%;
  top: 15%;
  transition: top .5s ease;
}
</style>
