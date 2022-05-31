<template>
  <div class="container">
    <div class="toolsbar">
      <div class="toolsbar-item">
        <svg-icon icon-class="toolsbar-1" />
        <span @click="handleClick('freight')">运费查询</span>
      </div>
      <div class="split-line" />
      <div class="toolsbar-item">
        <svg-icon icon-class="toolsbar-2" />
        <span
          style="color: #e74c3c"
          @click="handleClick('cid')"
        >身份证上传</span>
      </div>
      <div class="split-line" />
      <div class="toolsbar-item">
        <svg-icon icon-class="toolsbar-3" />
        <span>自营班车取件范围</span>
      </div>
      <div class="split-line" />
      <div class="toolsbar-item">
        <svg-icon icon-class="toolsbar-4" />
        <span @click="handleClick('sku')">物品列表查询</span>
      </div>
      <div class="split-line" />
      <div class="toolsbar-item toolsbar-item-end">
        <svg-icon icon-class="toolsbar-5" />
        <span @click="handleClick('problem/0')">常见问题汇总</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Toolsbar',
  methods: {
    handleClick(path) {
      window.location.hash = `/webapp/${path}`
    }
  }
}
</script>

<style lang="scss" scoped>
.toolsbar {
  position: relative;
  display: flex;
  justify-content: center;
  padding: 2rem 0;
  .toolsbar-item {
    cursor: pointer;
    margin: 0 2rem;
    // border-right: 1px solid grey;
    display: flex;
    align-items: center;
    transform: translateY(0px);
    transition: transform 0.5s;
    span,
    a {
      margin: 0 2rem 0 1rem;
      font-size: 1.5rem;
      font-weight: 700;
    }
    &:hover {
      transform: translateY(-2px) scale(1.1);
      span {
        color: #ff7675;
      }
    }
  }
  .toolsbar-item-end {
    border-right: none;
  }
}
.svg-icon {
  width: 4.5rem;
  height: 4.5rem;
}
.split-line {
  width: 1px;
  height: 4.5rem;
  border-right: 1px solid grey;
  transform: translateX(-1rem);
}
</style>
