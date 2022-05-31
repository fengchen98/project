<template>
  <div class="container">
    <div class="navWrap">
      <div class="logoWrap">
        <img
          src="/static/images/shenzhoulogo.png"
          alt="failed to download image"
        >
      </div>
      <div class="navListItem active">
        <router-link to="/webapp/index">
          <span class="navItem">首页</span>
        </router-link>
      </div>
      <div class="navListItem">
        <el-popover
          placement="bottom-start"
          width="440"
          trigger="hover"
          :visible-arrow="false"
        >
          <span slot="reference">
            <span class="navItem">服务与产品</span>
            <i class="el-icon-arrow-down" />
          </span>
          <div class="popContainer">
            <div class="servicePop">
              <div
                v-for="items in servicePopData"
                :key="items.title"
                class="serviceCol"
              >
                <span class="serviceTitle" v-text="items.title" />
                <router-link
                  v-for="item in items.children"
                  :key="item.name"
                  :to="item.link"
                  class="serviceItem"
                >
                  <span v-text="item.name" />
                </router-link>
              </div>
            </div>
            <div class="serviceBtnContainer">
              <router-link
                v-for="btn in btnData"
                :key="btn.title"
                :to="btn.link"
              >
                <button class="serviceBtn" v-text="btn.title" />
              </router-link>
            </div>
          </div>
        </el-popover>
      </div>
      <div class="navListItem">
        <el-popover
          placement="bottom-start"
          trigger="hover"
          :visible-arrow="false"
        >
          <span slot="reference">
            <span class="navItem">商城</span>
            <i class="el-icon-arrow-down" />
          </span>
          <div class="popContainer">
            <div class="popItem">
              <a href="#/milkPowder" target="_blank">奶粉商城</a>
            </div>
            <div class="popItem">
              <a href="#/webapp/mall/material" target="_self">包装材料</a>
            </div>
          </div>
        </el-popover>
      </div>
      <div class="navListItem">
        <router-link to="/webapp/about">
          <span class="navItem">关于我们</span>
        </router-link>
      </div>
      <div class="navListItem">
        <router-link to="/webapp/news">
          <span class="navItem">新闻中心</span>
        </router-link>
      </div>
      <div class="navListItem">
        <router-link to="/webapp/freight">
          <span class="navItem">运费报价</span>
        </router-link>
      </div>
    </div>
    <!-- <transition name="fade" mode="out-in">
      <div v-show="showBanner" class="advBanner">
        <div class="textWrap">
          <div class="leftText">
            <span
              class="bannerText aboutus"
              @click="handleCilckAbout"
              v-text="currentRoute"
            />
            <span class="bannerText">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
            <span class="bannerText">欢迎您登录 Ors Int Ltd. 官网</span>
          </div>
          <div class="rightText">
            <span class="bannerText">当前位置：首页 >> </span>
            <span class="bannerText" v-text="currentRoute" />
          </div>
        </div>
      </div>
    </transition> -->
  </div>
</template>

<script>
import webapprouter from '@/router/webapprouter'
export default {
  name: 'Navbar',
  data() {
    return {
      activeIndex: 'index',
      showBanner: false,
      currentRoute: null,
      servicePopData: [
        {
          title: '万国邮政',
          children: [
            {
              name: '法国邮政',
              link: '/webapp/service/freight/1'
            }
            // {
            //   name: 'Bpost比利时邮政',
            //   link: 'index'
            // },
            // {
            //   name: 'ParcelForce小包',
            //   link: 'index'
            // },
            // {
            //   name: 'ParcelForce经济包',
            //   link: 'index'
            // },
            // {
            //   name: 'ParcelForce优先包',
            //   link: 'index'
            // }
          ]
        },
        {
          title: '奶粉包税',
          children: [
            {
              name: '两罐奶粉',
              link: '/webapp/service/freight/2'
            },
            {
              name: '四罐奶粉',
              link: '/webapp/service/freight/3'
            },
            {
              name: '六罐奶粉',
              link: '/webapp/service/freight/4'
            }
          ]
        },
        {
          title: '杂货报税',
          children: [
            // {
            //   name: '333杂货包税',
            //   link: 'index'
            // },
            // {
            //   name: '333PLUS杂货',
            //   link: 'index'
            // },
            {
              name: '杂货小包',
              link: '/webapp/service/freight/5'
            },
            // {
            //   name: '护肤品小包',
            //   link: 'index'
            // },
            {
              name: '套装专线',
              link: '/webapp/service/freight/6'
            }
          ]
        },
        {
          title: '特色专线',
          children: [
            {
              name: '空运行李',
              link: '/webapp/service/freight/7'
            },
            {
              name: '海运行李',
              link: '/webapp/service/freight/8'
            },
            {
              name: 'UPS文件特快',
              link: '/webapp/service/freight/9'
            },
            {
              name: '全球邮寄服务',
              link: '/webapp/service/introduce/express/global_service'
            }
          ]
        }
      ],
      btnData: [
        {
          title: '查看所有服务',
          link: '/webapp/service/'
        }
        // {
        //   title: '下单教程',
        //   link: '/'
        // },
      ]
    }
  },
  computed: {
    routes() {
      const pathReg = /^(\/webapp)/
      const routeMap = webapprouter.filter((item) => pathReg.test(item.path))
      return routeMap[0].children
    }
  },
  watch: {
    $route(to) {
      this.$nextTick(() => {
        this.currentRoute = to.meta.title
        // console.log(to.fullPath)
        if (/(\/webapp\/index)/.test(to.fullPath)) {
          this.showBanner = false
        } else {
          this.showBanner = true
        }
      })
    }
  },
  created() {
    this.onloadPage()
  },
  mounted() {
    this.addEvents()
  },
  methods: {
    onloadPage() {
      // console.log(this.$router.history.current)
      this.activeIndex = this.$router.history.current.name
      this.currentRoute = this.$router.history.current.meta.title
      this.showBanner = this.activeIndex !== 'index'
    },
    handleSelect(arg) {
      // console.log(arg)
    },
    handleCilckNav(arg) {
      // window.location.hash = `#/website/about`
      document.querySelectorAll('.navListItem').forEach((item) => {
        item.classList.remove('active')
      })
    },
    addEvents() {
      // console.log(document.querySelectorAll('.navListItem'))
      document.querySelectorAll('.navListItem').forEach((item) => {
        item.addEventListener('click', this.handleCilckNav)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  width: 100%;
  min-width: 1200px;
  display: flex;
  justify-content: center;
  box-shadow: 1px 1px 5px 3px #636e72;
}
a {
  color: initial;
  text-decoration: none;
}
.navWrap {
  width: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #fff;
}
.navItem {
  font-size: 1.8rem;
  color: #000;
}
.servicePop {
  display: flex;
  justify-content: space-between;
  .serviceTitle {
    font-size: 1.5rem;
    padding: 0.5rem 0;
    margin-bottom: 1rem;
    border-bottom: 2px solid rgb(113, 163, 227);
  }
  .serviceCol {
    display: flex;
    flex-direction: column;
    .serviceItem {
      font-size: 1.2rem;
      margin: 0.5rem 0;
      &:hover {
        color: #ff7675;
      }
    }
  }
}
.serviceBtnContainer {
  margin: 1rem 0;
}
.serviceBtn {
  cursor: pointer;
  box-sizing: border-box;
  padding: 0.8rem;
  border: none;
  color: #fff;
  background-color: #0984e3;
  margin-right: 4rem;
  &:hover {
    background-color: #74b9ff;
  }
  &:active {
    transform: scale(0.95);
  }
}
.popItem {
  font-size: 1.3rem;
  margin: 1rem 0;
  &:hover {
    color: #e67e22;
  }
}
.navListItem {
  cursor: pointer;
  display: flex;
  align-items: center;
  height: 60%;
  box-sizing: border-box;
  margin: 0 1.5rem;
  i {
    font-size: 1.5rem;
    transform: rotate(0deg);
    transition: transform 0.5s ease;
  }
  &:hover {
    i {
      transform: rotate(180deg);
    }
  }
  // &.active {
  //   border-bottom: 2px solid #0984e3;
  // }
}
@media screen and (min-width: 2000px) {
  .navItem {
    margin: 0 1rem;
    font-size: 1.5rem;
  }
}
.advBanner {
  width: 100%;
  background: url('/static/images/advBanner.jpg') no-repeat;
  background-position-x: center;
  background-size: cover;
  display: flex;
  align-items: flex-end;
}
.aboutus {
  cursor: pointer;
}
.aboutus:hover {
  color: bisque;
}
.bannerText {
  color: #fff;
}
.textWrap {
  width: 100%;
  padding: 0;
  margin: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.el-menu-demo {
  display: flex;
  align-items: center;
}
</style>
