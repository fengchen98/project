<template>
  <div class="container">
    <div class="action-container">
      <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
        <el-tab-pane label="国际快递" name="first">
          <div class="action-box">
            <div class="row first-row">
              <el-form
                :inline="true"
                :model="international_express"
                class="form-inline"
              >
                <el-form-item label="发往">
                  <el-select
                    v-model="international_express.destination"
                    class="el-form-item-select"
                    placeholder="国家/地区"
                  >
                    <el-option label="中国大陆" value="shanghai" />
                    <el-option label="其他地区" value="beijing" />
                  </el-select>
                </el-form-item>
                <el-form-item label="">
                  <el-input
                    v-model="international_express.weight"
                    placeholder="重量"
                    class="express_input"
                    type="number"
                    autocomplete="off"
                  /><span>KG</span>
                </el-form-item>
                <el-form-item label="">
                  <el-input
                    v-model="international_express.length"
                    placeholder="长度"
                    class="express_input"
                    type="number"
                    autocomplete="off"
                  /><span>CM</span>
                </el-form-item>
                <el-form-item label="">
                  <el-input
                    v-model="international_express.width"
                    placeholder="宽度"
                    class="express_input"
                    type="number"
                    autocomplete="off"
                  /><span>CM</span>
                </el-form-item>
                <el-form-item label="">
                  <el-input
                    v-model="international_express.high"
                    placeholder="高度"
                    class="express_input"
                    type="number"
                    autocomplete="off"
                  /><span>CM</span>
                </el-form-item>
                <el-form-item>
                  <el-button
                    class="express_button"
                    type="primary"
                    @click="international_express_submit"
                  >立即下单</el-button>
                </el-form-item>
              </el-form>
            </div>
            <hr>
            <div class="row second-row">
              <span>从中国发往英国 [ 中国转运只收运费：超低运费 / 免费代收 /
                免费打包 / 到达英国送货上门 ]</span>
            </div>
            <hr>
            <div class="row third-row">
              最新公告 <span v-text="latestannounce" />
              <span
                style="display: inline-block; position: relative; left: 47rem"
                @click="handleClickMore"
              >查看更多>></span>
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane label="内陆服务" name="second">
          <div class="action-box" />
        </el-tab-pane>
        <el-tab-pane label="批量下单" name="third">
          <div class="action-box" />
        </el-tab-pane>
        <el-tab-pane label="行李寄存" name="fourth">
          <div class="action-box" />
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ActionCom',
  data() {
    return {
      activeName: 'first',
      international_express: {
        destination: '',
        weight: '',
        length: '',
        width: '',
        high: ''
      },
      latestannounce: '[2021-12-01]2021-2022 圣诞元旦假期安排'
    }
  },
  methods: {
    handleClick(tab, event) {
      // console.log(tab, event)
    },
    international_express_submit() {},
    handleClickMore() {
      window.location.hash = '/webapp/news'
    }
  }
}
</script>

<style lang="scss" scoped>
.el-input__inner {
  &::placeholder {
    font-size: 1.5rem;
  }
}
.el-inpit input {
  &::-webkit-input-placeholder {
    font-size: 1.5rem;
  }
  &::-moz-input-placeholder {
    font-size: 1.5rem;
  }
  &::-ms-input-placeholder {
    font-size: 1.5rem;
  }
}

// 不同浏览器样式兼容
.container {
  position: absolute;
  width: 100%;
  min-width: 1200px;
}
.el-tabs {
  width: 100%;
}
.el-tabs__nav {
  border: none !important;
}
.el-tabs__item {
  width: 10rem;
  height: 4rem;
  text-align: center;
  line-height: 4rem;
  margin-right: 0.2rem;
  border: none !important;
  font-size: 1.5rem;
  color: #fff;
  background-color: rgba(255, 255, 255, 0.4) !important;
  &.is-active {
    color: #000;
    background-color: rgba(255, 255, 255, 0.9) !important;
  }
  &:nth-child(2) {
    padding: 0 10px !important;
  }
}
.el-tabs__header {
  margin-bottom: 0;
}
.action-container {
  position: relative;
  width: 50%;
  // height: auto;
  margin: 0 auto;
}
.action-box {
  position: relative;
  width: 100%;
  height: auto;
  background-color: rgba(255, 255, 255, 0.9);
  padding: 1rem 2rem;
}
.el-form {
  position: relative;
  display: flex;
  justify-content: space-between;
  //   flex-wrap: wrap;
}
.el-form-item {
  display: flex !important;
  margin-bottom: 0.5rem;
  .el-form-item__content {
    display: flex;
    align-items: center;
  }
  span {
    line-height: 1rem;
    font-size: 1.5rem;
  }
  .el-form-item__label {
    line-height: 4rem;
    padding: 0 1rem 0 0;
  }
  label {
    display: inline;
    font-size: 1.5rem;
  }
  .el-form-item__content {
    display: flex;
  }
  .el-input__inner {
    border-radius: 0 !important;
    height: 4rem;
  }
}
.express_input {
  flex: 1;
  width: 8rem;
  margin-right: 0.5rem;
}
.express_button {
  margin-left: 2rem;
}
hr {
  height: 1px;
  border: none;
  background-color: grey;
}
.row {
  color: #606266;
  line-height: 5rem;
  font-size: 1.5rem;
  font-weight: 700;
  span:hover {
    cursor: pointer;
    color: #e67e22;
  }
}
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  appearance: none;
  margin: 0;
}
</style>
