<template>
  <div class="container">
    <div class="main">
      <h1>运费与报价</h1>
      <div class="boxWrapper">
        <div class="box">
          <p v-text="initData.title" />
          <li v-for="li in initData.instructionlist" :key="li" v-text="li" />
          <br>
          <li v-for="li in initData.insurancelist" :key="li" v-text="li" />
          <br>
          <span>正常时效：</span><span class="danger" v-text="initData.timeliness" />
          <span>国内承运：</span><span class="danger" v-text="initData.carriage" />
        </div>
        <div class="choiceBar">
          <button
            class="active"
            @click="handleComponentsChange('freight', $event)"
          >
            运费报价
          </button>
          <button @click="handleComponentsChange('delivery', $event)">
            送件方式
          </button>
          <button @click="handleComponentsChange('package', $event)">
            包裹准备
          </button>
          <button @click="handleComponentsChange('more', $event)">
            更多信息
          </button>
        </div>
        <div class="componentsWrapper">
          <keep-alive>
            <component :is="currentComponent" :init-data="initData" />
          </keep-alive>
        </div>
      </div>
      <div class="footerComponent">
        <freightSearch />
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  components: {
    freight: () => import('./components/freight.vue'),
    delivery: () => import('./components/delivery.vue'),
    package: () => import('./components/package.vue'),
    more: () => import('./components/more.vue'),
    freightSearch: () => import('./components/freightSearch.vue')
  },
  props: {
    id: {
      type: String,
      default: '1'
    }
  },
  data() {
    return {
      initData: {},
      currentComponent: 'freight'
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      axios.get(`/static/serviceData/${this.id}.json`).then((res) => {
        this.initData = res.data
      })
    },
    handleComponentsChange(component, event) {
      document
        .querySelectorAll('button')
        .forEach((item) => item.classList.remove('active'))
      event.currentTarget.classList.add('active')
      this.currentComponent = component
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  width: 100%;
}
.main {
  width: 50%;
  margin: 0 auto;
  padding: 2rem 0;
  h1 {
    font-size: 3rem;
    font-weight: 700;
    color: #2980b9;
  }
  .boxWrapper {
    padding: 2rem 0;
    background-color: rgba(243, 249, 250, 0.897);
    .box {
      width: 60%;
      margin: 0 auto;
      p {
        font-size: 2.7rem;
        font-weight: 700;
        color: #46a2df;
      }
      li {
        font-size: 1.8rem;
        font-weight: 200;
        margin: 0.5rem 0;
      }
      span {
        font-size: 1.5rem;
        font-weight: 200;
        margin: 0.5rem 0;
      }
      .danger {
        font-size: 1.5rem;
        font-weight: 200;
        color: #e67e22;
        margin: 0.5rem 2rem 0.5rem 0;
      }
    }
    .choiceBar {
      width: 95%;
      margin: 2rem auto;
      background-color: #e1e4e6;
      display: flex;
      justify-content: center;
      button {
        cursor: pointer;
        width: 12rem;
        height: 4rem;
        border: none;
        background: transparent;
        &:hover {
          color: #3498db;
        }
        &.active {
          color: #2980b9;
          background-color: rgba(243, 249, 250, 0.897);
          border-top: 3px solid #2980b9;
        }
      }
    }
  }
  .footerComponent {
    margin: 2rem auto;
  }
}
</style>
