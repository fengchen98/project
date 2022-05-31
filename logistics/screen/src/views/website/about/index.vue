<template>
  <div class="container">
    <div class="titleWrap">
      <h1>关于我们</h1>
      <span>About us</span>
    </div>
    <div class="contentWrap">
      <p v-for="data in initData" :key="data.slice(0, 20)">
        <span class="indent" />
        <span class="text" v-text="data" />
      </p>
    </div>
  </div>
</template>

<script>
import { getAbout } from '@/api/getAbout'

export default {
  name: 'Aboutus',
  data() {
    return {
      initData: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      getAbout().then((response) => {
        // console.log(response.data.items)
        this.initData = response.data.items
      })
    }
  }
}
</script>

<style scoped>
.hr {
  width: 80%;
}
.container {
  position: relative;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.titleWrap {
  position: relative;
  padding: 20px;
  width: 60vw;
  border-bottom: 1px solid #dfe6e9;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.contentWrap {
  position: relative;
  padding: 0 20px;
  width: 60vw;
}
.indent {
  padding-left: 2em;
}
.text {
  color: #636e72;
}
</style>
