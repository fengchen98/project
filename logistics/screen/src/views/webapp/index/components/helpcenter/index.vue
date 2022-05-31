<template>
  <div class="container">
    <div class="main">
      <div class="title">
        <p>帮助中心</p>
        <p>Help Center</p>
      </div>
      <div class="boxWrapper">
        <ul>
          <li
            v-for="item in componentsList"
            :key="item.title"
            @mouseover="currentComponent = item.component"
          >
            <i :class="item.icon" />
            <span v-text="item.title" />
          </li>
        </ul>
        <div class="box">
          <keep-alive>
            <component :is="currentComponent" />
          </keep-alive>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import about from './components/about.vue'
import activity from './components/activity.vue'
import announce from './components/announce.vue'
import contact from './components/contact.vue'
import cooperate from './components/cooperate.vue'
import problem from './components/problem.vue'

export default {
  name: 'DirectMail',
  components: {
    about,
    activity,
    announce,
    contact,
    cooperate,
    problem
  },
  data() {
    return {
      currentComponent: 'about',
      componentsList: [
        {
          title: '关于我们',
          icon: 'el-icon-s-home',
          component: 'about'
        },
        {
          title: '常见问题',
          icon: 'el-icon-question',
          component: 'problem'
        },
        {
          title: '最新公告',
          icon: 'el-icon-info',
          component: 'announce'
        },
        {
          title: '最新活动',
          icon: 'el-icon-s-opportunity',
          component: 'activity'
        },
        {
          title: '加盟合作',
          icon: 'el-icon-s-custom',
          component: 'cooperate'
        },
        {
          title: '联系我们',
          icon: 'el-icon-phone',
          component: 'contact'
        }
      ]
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  background-color: #fff;
}
.main {
  position: relative;
  padding: 20px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 60%;
  p:nth-child(1) {
    color: #0984e3;
    text-align: center;
    font-size: 3rem;
    // font-weight: 700;
    margin: 1.5rem 0;
  }
  p:nth-last-child(1) {
    text-align: center;
    font-size: 1.5rem;
    font-weight: 700;
  }
}
.boxWrapper {
  position: relative;
  width: 100%;
  margin-top: 3rem;
  ul {
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: space-between;
    li {
      cursor: pointer;
      display: inline-block;
      background-color: rgba(244, 244, 244);
      margin: 0 1rem;
      flex: 1;
      // width: 30px;
      padding: 1rem 0;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      align-items: center;
      $color1: rgb(173, 173, 173);
      color: darken($color1, 25%);
      transform: scale(1);
      transition: transform 0.3s ease-in-out;
      i {
        font-size: 5rem;
      }
      span {
        font-size: 1.5rem;
        font-weight: 700;
        margin: 1rem 0;
      }
      &:hover {
        box-shadow: 2px 2px 5px 2px rgba(0, 0, 0, 0.1);
        transform: scale(1.1);
        background-color: skyblue;
        color: darken($color1, 100%);
      }
    }
  }
}
.box {
  position: relative;
  width: 100%;
  margin-top: 1rem;
  padding: 1rem 1rem;
}
</style>
