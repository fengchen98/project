<template>
  <div class="mainWrapper">
    <div class="btnWrapper">
      <el-button size="mini" @click="addPackage">增加包装袋</el-button>
      <el-button size="mini" @click="delPackage">删除包装袋</el-button>
    </div>
    <div class="setBoxStyle">
      <div class="left">
        <div class="leftBox">
          <template>
            <div
              v-for="(item, index) in transferData"
              :key="item.label + index"
              class="packageItem"
              :style="'width:100px;height:50px'"
              @click.stop="putBox($event, index)"
            >
              <p class="packageName">{{ item.label }}</p>
              <div
                v-for="(it, index) in item.set"
                :key="'it' + index"
                class="itClassName"
                :style="
                  'width:' +
                    parseFloat(it.length) / 2 +
                    'px;height:' +
                    parseFloat(it.width) / 2 +
                    'px;'
                "
              />
            </div>
          </template>
        </div>
        <p>待处理的包裹</p>
      </div>
      <div class="right">
        <template v-for="(item, index) in packageArray">
          <div :key="index" class="boxWrapper">
            <p class="packageName">{{ '包裹' + (index + 1) }}</p>
            <div class="rightBox" @click.stop="selectBox($event, index)">
              <div
                v-for="(i, idx) in item.value"
                :key="i.name + idx"
                class="items"
                :style="'width:' + i.length + 'px;height:' + i.width + 'px'"
                @click.stop="goBackLeft($event, idx)"
                v-text="i.name"
              />
            </div>
          </div>
        </template>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    boxArray: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      titlesArray: [],
      transferData: [],
      packageArray: []
    }
  },
  watch: {
    boxArray(newValue, oldValue) {
      this.transferData = this.boxArray.map((o, index) => {
        return {
          key: index,
          label: `包裹${index + 1}`,
          disabled: false,
          set: o.set
        }
      })
      console.log(this.transferData)
    }
  },
  created() {
    console.log('created bagModule', this.boxArray)
  },
  methods: {
    addPackage() {
      this.titlesArray.push([
        '包裹清单',
        `包装袋${this.titlesArray.length + 1}`
      ])
      this.packageArray.push({
        name: `包装袋${this.packageArray.length + 1}`,
        data: this.transferData.slice(0),
        value: []
      })
    },
    handleTransferChange(data, direction, key) {
      if (direction === 'right') {
        // console.log('right==>', key)
        this.packageArray.forEach((o) => {
          o.data = o.data.filter((o_k) => {
            return key.indexOf(o_k.key) === -1
          })
          //   console.log('o.data  after:', o.data)
        })
      }
      if (direction === 'left') {
        // console.log('left==>', key)
      }
    },
    delPackage() {
      this.packageArray.pop()
    }
  }
}
</script>

<style lang="scss" scoped>
.mainWrapper {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.btnWrapper {
  display: flex;
  justify-content: flex-start;
}
.setBoxStyle {
  .leftBox {
    box-sizing: content-box;
    width: 205px;
    height: 105px;
    background-color: lightyellow;
    display: flex;
    flex-wrap: wrap;
    .packageItem {
      position: relative;
      box-sizing: content-box;
      width: 50px;
      height: 30px;
      background-color: rgb(253, 227, 222);
      border: 1px solid #000;
      display: flex;
      flex-wrap: wrap;
      .packageName {
        user-select: none;
        position: absolute;
        font-size: 2rem;
        font-weight: 200;
        text-align: center;
        line-height: 50px;
        margin: 0;
        padding: 0;
        width: 100%;
        height: 100%;
      }
      .itClassName {
        box-sizing: border-box;
        background-color: rgb(85, 239, 196);
        border: 1px solid rgb(128, 128, 128);
        font-size: 2px;
      }
    }
  }
}
</style>
