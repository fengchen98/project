<template>
  <div class="wrapper" v-html="html" />
</template>

<script>
export default {
  props: {
    content: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      html: this.content.html
    }
  },
  watch: {
    content(newValue, oldValue) {
      this.html = newValue.html
    }
  }
}
</script>

<style lang="scss" scoped>
.wrapper {
  margin: 0 auto;
  width: 755px;
  padding: 2rem;
}
::v-deep img {
  width: 100% !important;
  margin: 0 auto;
}
</style>
