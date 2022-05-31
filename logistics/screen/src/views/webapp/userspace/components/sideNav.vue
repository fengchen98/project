<template>
  <div class="container">
    <div class="sideNav">
      <div v-for="cloumn in cloumns" :key="cloumn.title" class="itemBox">
        <div class="title">
          <i :class="cloumn.icon" />
          <p v-text="cloumn.title" />
        </div>
        <div class="itemWrapper">
          <span
            v-for="item in cloumn.items"
            :key="item.name"
            @click="activeStatus(item.link, $event)"
          >{{ item.name }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      cloumns: [
        {
          title: '基本信息',
          icon: 'el-icon-user-solid',
          items: [
            {
              name: '个人中心',
              link: 'accountCenter'
            },
            {
              name: '用户信息',
              link: 'accountInfo'
            },
            {
              name: '账户管理',
              link: 'accountManager/recharge'
            },
            {
              name: '地址管理',
              link: 'addressManager'
            }
          ]
        },
        {
          title: '我的订单',
          icon: 'el-icon-s-order',
          items: [
            {
              name: '订单查询',
              link: 'orderQuery'
            },
            {
              name: '面单管理',
              link: 'labels'
            },
            {
              name: '代发订单',
              link: 'shopOrders'
            }
          ]
        },
        {
          title: '商业用户',
          icon: 'el-icon-s-custom',
          items: [
            {
              name: '公司信息',
              link: 'companyInfo'
            },
            {
              name: '发票下载',
              link: 'Invoice'
            },
            {
              name: '商业报关',
              link: 'clearance'
            }
          ]
        },
        {
          title: '我的行李',
          icon: 'el-icon-suitcase',
          items: [
            {
              name: '入库行李',
              link: 'luggage'
            }
          ]
        },
        {
          title: '转运包裹',
          icon: 'el-icon-box',
          items: [
            {
              name: '转运订单',
              link: 'transport'
            }
          ]
        },
        {
          title: '优惠信息',
          icon: 'el-icon-message-solid',
          items: [
            {
              name: '我的返利',
              link: 'rebate'
            },
            {
              name: '我的卡券',
              link: 'coupons'
            },
            {
              name: '我的红包',
              link: 'redenvelope'
            }
          ]
        },
        {
          title: '常用工具',
          icon: 'el-icon-s-tools',
          items: [
            {
              name: '物品申报',
              link: 'declare'
            },
            {
              name: '站内信',
              link: 'letter'
            },
            {
              name: '偏好设置',
              link: 'preference'
            }
          ]
        }
      ]
    }
  },
  mounted() {
    // (function() {
    //   document.querySelectorAll('span')[0].classList.add('active')
    // })()
  },
  methods: {
    activeStatus(link, event) {
      window.location.hash = `/webapp/userspace/${link}`
    //   event.currentTarget.classList.add('active')
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
.mainWrapper {
  width: 100%;
  .sideNav {
    width: 100%;
    padding: 2rem;
    background-color: rgba(243, 249, 250, 0.95);
    .itemBox {
      display: flex;
      flex-direction: column;
      margin-bottom: 2rem;
      .title {
        display: flex;
        font-size: 1.5rem;
        justify-content: flex-start;
        align-items: center;
        padding-left: 1rem;
        i {
          margin-right: 0.2rem;
        }
      }
      .itemWrapper {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        font-size: 1.5rem;
        padding-left: 1rem;
        span {
          cursor: pointer;
          margin: 0.5rem 0;
          padding: 0 1rem 0 1.5rem;
          &:hover {
            color: #e67e22;
          }
          &.active {
            border-right: 3px solid #2980b9;
          }
        }
      }
    }
  }
}
</style>
