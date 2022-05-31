<template>
  <div class="layout">
    <div id="left">
      <template>
        <div
          v-for="item in nums"
          :key="item.id"
          class="items"
          :style="
            'width:' +
              item.param.width +
              'px;height:' +
              item.param.height +
              'px'
          "
          @click="movebox($event)"
        />
      </template>
    </div>
    <div id="right" />
  </div>
</template>

<script>
export default {
  data() {
    return {
      nums: [
        {
          id: '1',
          name: '四罐奶粉',
          param: {
            width: '50',
            height: '50'
          }
        },
        {
          id: '2',
          name: '六罐奶粉',
          param: {
            width: '75',
            height: '50'
          }
        },
        {
          id: '3',
          name: '四罐奶粉',
          param: {
            width: '50',
            height: '50'
          }
        },
        {
          id: '4',
          name: '四罐奶粉',
          param: {
            width: '50',
            height: '50'
          }
        },
        {
          id: '5',
          name: '六罐奶粉',
          param: {
            width: '75',
            height: '50'
          }
        },
        {
          id: '6',
          name: '六罐奶粉',
          param: {
            width: '75',
            height: '50'
          }
        }
      ]
    }
  },
  mounted() {},
  methods: {
    watchMouse() {
      const wm = window.addEventListener('mousemove', () => {
        const e = window.event
        const item = document.getElementById('box')
        item.style.top = e.clientY - 200 + 'px'
        item.style.left = e.clientX + 'px'
        console.log(e.clientX, e.clientY)
      })
      window.addEventListener('mouseup', () => window.removeEventListener(wm))
    },
    movebox(event) {
      const rightEl = document.getElementById('right')

      const currentEl = event.path[0]
      const fromBoxId = currentEl.parentNode.id
      const toBoxId = fromBoxId === 'right' ? 'left' : 'right'
      currentEl.parentNode.removeChild(currentEl)
      document.getElementById(toBoxId).appendChild(currentEl)
      if (rightEl.scrollHeight > rightEl.clientHeight) {
        alert('放不下了')
        currentEl.click()
      }
    }
  }
}
</script>

<style lang="scss" scoped>
#box {
  position: absolute;
  width: 100px;
  height: 100px;
  background-color: red;
}
.layout {
  display: flex;
  justify-content: center;
}
.items {
  cursor: pointer;
  background-color: #55efc4;
  border: 1px solid grey;
}
#left {
  position: relative;
  width: 200px;
  // height: 100px;
  background-color: #ffeaa7;
  display: flex;
  flex-wrap: wrap;
}
#right {
  position: relative;
  width: 200px;
  height: 100px;
  background-color: #fab1a0;
  display: flex;
  flex-wrap: wrap;
  overflow: hidden;
}
</style>
