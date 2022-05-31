<template>
  <div class="container">
    <div class="footerWrap">
      <div class="top">
        <div v-for="nav in navs" :key="nav.title" class="top-box">
          <p v-text="nav.title" />
          <div v-for="subnav in nav.child" :key="subnav.title">
            <p class="sub_title" v-text="subnav.title" />
          </div>
        </div>
      </div>
      <div class="middle">
        <div class="pic" />
        <div class="middle-text-box">
          <p>在线客服 Live Chat | 点击立即交谈</p>
          <p>在线时间：周一至周五 09:00 - 17:00</p>
        </div>
      </div>
      <div class="bottom">
        <div v-for="li in list" :key="li.name">
          <span v-text="li.name" />
          <span class="split-line" />
        </div>
      </div>
      <div class="copyright">
        <p>陕ICP备20002864号-1</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FooterBar',
  data() {
    return {
      navs: [
        {
          title: '服务与产品',
          child: [
            {
              title: '快递直邮',
              path: ''
            },
            {
              title: '大宗出口',
              path: ''
            },
            {
              title: '海淘转运',
              path: ''
            },
            {
              title: '代发商城',
              path: ''
            }
          ]
        },
        {
          title: '新手帮助',
          child: [
            {
              title: '如何邮寄包裹',
              path: ''
            },
            {
              title: '如何选择邮寄路线',
              path: ''
            },
            {
              title: '预约取件',
              path: ''
            },
            {
              title: '禁运物品',
              path: ''
            },
            {
              title: '如何支付订单',
              path: ''
            }
          ]
        },
        {
          title: '售后小贴士',
          child: [
            {
              title: '取消订单',
              path: ''
            },
            {
              title: '超重补款',
              path: ''
            },
            {
              title: '状态无法更新',
              path: ''
            },
            {
              title: '包裹丢失',
              path: ''
            }
          ]
        },
        {
          title: '合作招聘',
          child: [
            {
              title: '速递招聘',
              path: ''
            },
            {
              title: '校园代理',
              path: ''
            },
            {
              title: '实体店加盟',
              path: ''
            },
            {
              title: '商家媒体合作',
              path: ''
            }
          ]
        }
      ],
      list: [
        {
          name: '神州物流',
          path: ''
        },
        {
          name: '奶粉商城',
          path: ''
        },
        {
          name: '包装商城',
          path: ''
        },
        {
          name: '联系我们',
          path: ''
        },
        {
          name: '加盟合作',
          path: ''
        },
        {
          name: '隐私政策',
          path: ''
        },
        {
          name: '条款与条约',
          path: ''
        }
      ]
    }
  },
  methods: {
    handleclick(arg) {
      window.location.hash = `#/website/${arg}`
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  width: 100%;
  background-color: rgb(36, 47, 50);
  display: flex;
  justify-content: center;
}
.footerWrap {
  width: 50%;
  /* height: 15vh; */
  // height: 40vh;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  padding: 2rem;
}
.top {
  width: 100%;
  position: relative;
  display: flex;
  .top-box {
    flex: 1;
    p {
      font-size: 2rem;
      font-weight: 600;
      color: #fff;
      &.sub_title {
        cursor: pointer;
        font-size: 1rem;
        font-weight: 400;
        color: #fff;
        &:hover {
          color: #ff9f43;
        }
      }
    }
  }
}
.middle {
  width: 100%;
  display: flex;
  justify-content: flex-start;
  margin-top: 3rem;
  .pic {
    width: 120px;
    height: 120px;
    background-color: #fff;
  }
  .middle-text-box {
    margin-left: 2rem;
    p {
      font-size: 1.5rem;
      color: #fff;
    }
  }
}
.bottom {
  margin-top: 4rem;
  width: 100%;
  display: flex;
  div {
    display: flex;
  }
  span {
    font-size: 1.5rem;
    color: #fff;
  }
  .split-line {
    height: 90%;
    margin: 0 1rem;
    border-right: 1px solid #fff;
  }
  div {
    span:nth-child(1) {
      cursor: pointer;
      &:hover {
        color: #ff9f43;
      }
    }
  }
  div:nth-last-child(1) {
    span:nth-last-child(1) {
      border-right: none;
    }
  }
}
.copyright{
  color: #fff;
  margin: 2rem 0 0 0;
  font-size: 1rem;
}
</style>
