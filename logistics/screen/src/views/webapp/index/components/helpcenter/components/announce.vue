<template>
  <div class="container">
    <div class="mainWrapper">
      <el-carousel
        :interval="4000"
        type="card"
        height="300px"
        :autoplay="false"
      >
        <el-carousel-item v-for="item in annLists" :key="item.id">
          <div class="card">
            <span>{{ item.create_time.split(' ')[0] }}</span>
            <p v-text="item.title" />
            <hr>
            <span class="content" v-text="item.description" />
          </div>
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
</template>

<script>
import { titleListByType } from '@/api/news'

export default {
  data() {
    return {
      // annLists: [
      //   {
      //     id: '100001',
      //     date: '2021-08-04',
      //     title: '2021-2022圣诞元旦假期安排',
      //     content: '暂时没有什么安排，写这些就是为了让文本区看起来没那么空'
      //   },
      //   {
      //     id: '100002',
      //     date: '2021-09-14',
      //     title: '航空燃油附加费调整',
      //     content:
      //       '尊敬的客户：11月即将进入一年中最忙碌的冬季航季，航空仓位异常紧张，航空公司对我司的航空价格已经上调。因此，我司不得已跟进更新航空燃油附加费，上调20p/kg。此调整针对以下服务并将于1...'
      //   },
      //   {
      //     id: '100003',
      //     date: '2021-09-16',
      //     title: '邮寄白金1/2段奶粉服务更新',
      //     content:
      //       '尊敬的用户， 根据口岸最新规定，爱他美白金版奶粉1段、2段将全部按照完税价200元申报。这也就意味着邮寄白金奶粉1段、2段会多出80多元的税金。上述新政会陆续对各个口岸执行。'
      //   },
      //   {
      //     id: '100004',
      //     date: '2021-9-18',
      //     title: '调价通知',
      //     content:
      //       '尊敬的各位用户，由于目前航空仓位紧张导致了货运价格上涨，决定暂时性对以下路线价格进行上调：1, 四罐奶粉线路2, 六罐奶粉线路3， 四罐奶粉-EMS全程追踪路线4， 333/333plu...'
      //   },
      //   {
      //     id: '100005',
      //     date: '2021-10-01',
      //     title: '疫情影响目前部分地区包裹派送通知',
      //     content:
      //       '尊敬的各位用户：接国内邮政通知，受疫情影响以下地区的包裹可能暂缓或延缓派送，待恢复后第一时间寄出。新疆-全省内蒙- 二连浩特 阿拉善盟 满洲里市河北-廊坊市大厂县贵州省-仁怀市黑龙江省-黑河市...'
      //   },
      //   {
      //     id: '100006',
      //     date: '2021-11-26',
      //     title: '广州/北京庆祝活动期间包裹派送通知',
      //     content:
      //       '尊敬的各位用户：接国内邮政通知，在建党百年庆祝活动期间，即日起至7月15日，对寄往7个庆祝活动举办地（北京、上海、浙江嘉兴、江西吉安、贵州遵义、陕西延安、河北石家庄）的快递包裹，英国可正常下单，...'
      //   }
      // ],
      annLists: []
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      titleListByType('服务公告').then((res) => {
        if (res.code === 20000) {
          this.annLists = res.data.slice(0, 8)
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  padding: 0;
  margin: 0;
  width: 100%;
}
.mainWrapper {
  position: relative;
  width: 100%;
  // height: 600px;
  padding: 2rem;
  // display: flex;
  background-color: rgb(244, 244, 244);
}
.card {
  background-color: #fff;
  padding: 2rem;
  width: 100%;
  height: 100%;
  span {
    font-size: 1.5rem;
  }
  p {
    color: #2e86de;
    font-weight: 700;
    font-size: 2rem;
  }
  .content {
    display: block;
    font-size: 1.7rem;
    line-height: 3rem;
  }
}
.el-carousel {
  width: 100%;
}
.el-carousel__item {
  // border: 1px solid rgba(0, 0, 0, 0.2);
  background-color: #fff;
}
</style>
