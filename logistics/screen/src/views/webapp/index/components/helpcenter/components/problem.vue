<template>
  <div class="container">
    <div class="mainWrapper">
      <div class="top-box">
        <el-input v-model="input" placeholder="请输入搜索问题" />
        <el-button slot="append" icon="el-icon-search" />
      </div>
      <div class="bottom-box">
        <div v-for="item in problemLists" :key="item.typeid" class="item">
          <div class="item-top">
            <svg-icon :icon-class="item.icon" />
            <span v-text="item.title" />
          </div>
          <div class="item-bottom">
            <div v-for="p in item.list" :key="p.id">
              <a
                :href="'#/webapp/problem/' + p.id"
                target="__blank"
                v-text="p.title"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { titleList } from '@/api/problem'

export default {
  data() {
    return {
      input: '',
      problemLists: [
        {
          icon: 'shuoming',
          title: '下单说明',
          list: []
        },
        {
          icon: 'qujian',
          title: '取件投递',
          list: []
        },
        {
          icon: 'shuijin',
          title: '清关和税金',
          list: []
        },
        {
          icon: 'zhifu',
          title: '支付相关',
          list: []
        },
        {
          icon: 'fanli',
          title: '营销返利',
          list: []
        },
        {
          icon: 'shouhou',
          title: '售后问题',
          list: []
        },
        {
          icon: 'xingli',
          title: '行李寄存',
          list: []
        }
      ]
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      titleList().then((res) => {
        if (res.code === 20000) {
          res.data.forEach((o) => {
            for (let i = 0; i < this.problemLists.length; i += 1) {
              if (o.type === this.problemLists[i].title) {
                this.problemLists[i].list.push(o)
                break
              }
            }
          })
        }
      })
      console.log(this.problemLists)
      // axios
      //   .get('/static/data/problem/problemList.json')
      //   .then((res) => {
      //     this.problemList = res.data
      //   })
      //   .catch((err) => {
      //     console.log(err)
      //   })
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  padding: 0;
  margin: 0;
}
.mainWrapper {
  position: relative;
  width: 100%;
  // height: 600px;
  padding: 2rem;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  background-color: rgba(244, 244, 244, 0.5);
}
.bottom-box {
  position: relative;
  width: 100%;
  display: flex;
  justify-content: space-between;
  padding: 2rem;
  .item-top {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    margin-bottom: 4rem;
    .svg-icon {
      width: 4rem;
      height: 4rem;
      margin-right: 1rem;
    }
    span {
      font-size: 1.8rem;
      font-weight: 700;
    }
  }
  .item-bottom {
    a {
      overflow: hidden;
      white-space: nowrap;
      width: 100px;
      text-overflow: ellipsis;
      display: inline-block;
      text-decoration: none;
      cursor: pointer;
      font-size: 1rem;
      margin: 1rem 0;
      color: grey;
      &:hover {
        color: #000;
      }
    }
  }
}
.item {
  flex: 1;
}
.top-box {
  width: 60%;
  display: flex;
  margin-bottom: 3rem;
  .el-button {
    transform: translateX(-80%);
    border: none;
    background-color: transparent;
    &:active {
      // font-size: 5rem;
      transform: translateX(-80%) scale(1.2);
    }
  }
}
.el-input {
  display: block;
  //   width: 60%;
  margin: 0 auto;
}
</style>
