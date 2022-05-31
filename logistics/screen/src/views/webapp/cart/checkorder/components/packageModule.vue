<template>
  <div class="layout">
    <div class="setBoxStyle">
      <div class="left">
        <div class="leftBox">
          <template>
            <div
              v-for="(item, index) in initData"
              :key="item.name + index"
              class="items"
              :style="'width:' + item.length + 'px;height:' + item.width + 'px'"
              @click.stop="putBox($event, index)"
              v-text="item.name"
            />
          </template>
        </div>
        <p>未添加包裹的商品</p>
        <div class="btnWrapper">
          <button class="addBoxBtn" @click="addBox">添加包裹</button>
          <button class="delBoxBtn" @click="delBox">移除包裹</button>
        </div>
      </div>
      <div class="right">
        <template v-for="(item, index) in boxArray">
          <div :key="index" class="boxWrapper">
            <p class="packageName">{{ '包裹' + (index + 1) }}</p>
            <div class="rightBox" @click.stop="selectBox($event, index)">
              <div
                v-for="(i, idx) in item.set"
                :key="i.name + idx"
                class="items"
                :style="'width:' + i.length + 'px;height:' + i.width + 'px'"
                @click.stop="goBackLeft($event, idx)"
                v-text="i.name"
              />
            </div>
            <addressModule
              class="addressModule"
              @selectAddress="selectAddress($event, index)"
            />
          </div>
        </template>
      </div>
    </div>
  </div>
</template>

<script>
import { Message } from 'element-ui'
export default {
  components: {
    addressModule: () => import('./addressModule.vue')
  },
  props: {
    orderData: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      boxArray: [],
      initData: [],
      currentBox: 0,
      MAX_BOX_NUM: 0
    }
  },
  created() {
    this.orderData.forEach((o) => {
      for (let i = 0; i < o.amount; i += 1) {
        const tmp = JSON.parse(JSON.stringify(o))
        tmp.amount = 1
        tmp.totalprice = tmp.unitprice * tmp.amount
        this.initData.push(tmp)
      }
    })
    this.MAX_BOX_NUM = this.initData.length
  },
  methods: {
    addBox() {
      for (let i = 0; i < this.boxArray.length; i++) {
        if (this.boxArray[i].set.length === 0) {
          this.$alert(`包裹${i + 1}为空`, ' 空包裹提示', {
            confirmButtonText: '确定'
          })
          return
        }
      }
      if (this.initData.length === 0) {
        this.$alert('没有商品可以被添加到包裹', '包裹提示', {
          confirmButtonText: '确定'
        })
      } else {
        const box = {
          amount: 1,
          id: 'box_1',
          imgurl:
            'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsem.g3img.com%2Fsite%2F50020930%2Fimage%2Fc2_20190110123943_82784.jpg%3FimageView2%2F2%2Fw%2F350&refer=http%3A%2F%2Fsem.g3img.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651550018&t=7b64bd48f2f2197c5fc06bbb67c8ce78',
          unitprice: 16.69,
          title: '包装盒',
          totalprice: 16.69,
          set: [],
          address: null
        }
        this.boxArray.push(box)
        this.currentBox = this.boxArray.length - 1
        // this.$nextTick(() => {
        //   document
        //     .querySelectorAll('.rightBox')
        //     [this.currentBox].classList.add('active')
        // })
        // 向订单更新包装盒信息
        this.$emit('addbox', this.boxArray.slice(0))
        this.$emit('boxArray', this.boxArray.slice(0))
        // console.log(this.boxArray)
      }
    },
    delBox() {
      if (this.boxArray.length > 0) {
        // this.initData.push(...this.boxArray.pop().set)
        this.initData.push(...this.boxArray[this.currentBox].set)
        this.boxArray.splice(this.currentBox, 1)
        this.currentBox = this.boxArray.length - 1
        this.$emit('addbox', this.boxArray.slice(0))
        this.$emit('boxArray', this.boxArray.slice(0))
      } else {
        alert('没有包装盒可以删除')
      }
    },
    putBox(event, index) {
      if (
        typeof this.boxArray[this.currentBox] !== 'undefined' &&
        this.boxArray[this.currentBox].set
      ) {
        this.boxArray[this.currentBox].set.push(this.initData[index])
        this.initData.splice(index, 1)
        const curBox = document.querySelectorAll('.rightBox')[this.currentBox]
        this.$nextTick(() => {
          if (curBox.scrollHeight > curBox.clientHeight) {
            // this.$alert('当前包装盒已满,请选择其他包装盒', '', {
            //   confirmButtonText: '确定'
            // })
            this.initData.push(this.boxArray[this.currentBox]['set'].pop())
            Message.warning('当前包裹已满,请选择其他包裹')
          }
        })
        this.$emit('boxArray', this.boxArray.slice(0))
      } else {
        this.$alert('没有可以放置的包裹,请添加包裹后操作', '', {
          confirmButtonText: '确定'
        })
      }
    },
    goBackLeft(event, index) {
      const pathArr = event.path
      for (let i = 0; i < pathArr.length; i += 1) {
        // console.log(pathArr[i].classList);
        if ([...pathArr[i].classList].includes('rightBox')) {
          pathArr[i].click()
          break
        }
      }
      this.initData.push(this.boxArray[this.currentBox]['set'][index])
      this.boxArray[this.currentBox]['set'].splice(index, 1)
      // console.log(this.boxArray)
    },
    selectBox(event, index) {
      document.querySelectorAll('.rightBox').forEach((o) => {
        o.classList.remove('active')
      })
      this.currentBox = index
      document.querySelectorAll('.rightBox')[index].classList.add('active')
    },
    selectAddress(event, index) {
      console.log('触发selectAddress')
      // console.log('selectAddress', event, index)
      this.boxArray[index].address = event
      this.$emit('boxArray', this.boxArray.slice(0))
    }
  }
}
</script>

<style lang="scss" scoped>
.btnWrapper {
  display: flex;
  justify-content: center;
  .addBoxBtn {
    width: 100%;
    display: block;
    margin: 2rem;
    padding: 0.5rem 2rem;
    border: 1px solid rgba(41, 128, 185, 1);
    color: #000;
    border-radius: 0.5rem;
    background-color: rgba(52, 152, 219, 0.05);
  }
  .delBoxBtn {
    width: 100%;
    display: block;
    margin: 2rem;
    padding: 0.5rem 2rem;
    border: 1px solid rgba(231, 76, 60, 1);
    color: #000;
    border-radius: 0.5rem;
    background-color: rgba(231, 76, 60, 0.05);
  }
}
#box {
  position: absolute;
  width: 100px;
  height: 100px;
  background-color: red;
}
.layout {
  position: relative;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  // justify-content: center;
}

.items {
  box-sizing: border-box;
  cursor: pointer;
  color: #f9feff;
  background-color: #55efc4;
  border: 1px solid grey;
  overflow: hidden;
  text-overflow: ellipsis;
  text-align: center;
  // line-height: 50px;
}
.setBoxStyle {
  width: 100%;
  display: flex;
  justify-content: center;
  .left {
    flex: 4;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    .leftBox {
      position: relative;
      width: 210px;
      height: 100px;
      // margin-right: 3rem;
      background-color: #ffeaa7;
      display: flex;
      flex-wrap: wrap;
      overflow: hidden;
      // overflow-x: scroll;
      overflow-y: scroll;
      &::-webkit-scrollbar {
        // display: none;
        width: 5px;
        // height: 6px;
      }
      &::-webkit-scrollbar-track {
        background-color: transparent;
      }
      &::-webkit-scrollbar-thumb {
        background-image: linear-gradient(180deg, #d0368a 0%, #708ad4 99%);
        box-shadow: inset 2px 2px 5px 0 rgba(#fff, 0.5);
        border-radius: 100px;
      }
    }
  }
  .right {
    border-left: 1px solid #000;
    flex: 4;
    display: flex;
    flex-direction: column;
    // flex-wrap: wrap;
    .boxWrapper {
      position: relative;
      margin-bottom: 1rem;
      display: flex;
      // justify-content: space-around;
      align-items: center;
      .packageName,
      .addressModule {
        margin-left: 3rem;
      }
      .rightBox {
        cursor: pointer;
        margin-left: 3rem;
        position: relative;
        width: 202px;
        height: 102px;
        background-color: #fab0a059;
        display: flex;
        flex-wrap: wrap;
        overflow: hidden;
      }
      .active {
        border: 1px solid #c0392b;
      }
    }
  }
}
</style>
