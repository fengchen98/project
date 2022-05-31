<template>
  <div class="container">
    <div class="banner" />
    <div class="warp">
      <div class="navbox">
        <div class="navbox01">
          <h1 class="p1">气泡保护类</h1>
          <p>CHOOSE NOW<i class="el-icon-success" /></p>
        </div>
        <div class="navbox02" @click="jump02">
          <h1>防水保护封装类</h1>
          <p>CHOOSE NOW<i class="el-icon-success" /></p>
        </div>
        <div class="navbox03" @click="jump03">
          <h1>包装纸箱类</h1>
          <p>CHOOSE NOW<i class="el-icon-success" /></p>
        </div>
      </div>
      <h1>气泡保护类</h1>
      <div class="package01">
        <div
          v-for="item in package1"
          :key="item.id"
          class="package_con"
          @click="handleboxClick(item.id)"
        >
          <img :src="item.url" alt="">
          <div class="con_message">
            <p class="pri" v-text="item.price" />
            <p class="nam" v-text="item.name" />
            <p class="siz" v-text="item.size" />
            <button>查看详情</button>
          </div>
        </div>
      </div>
    </div>
    <div class="warp">
      <h1 id="p2">防水保护封装类</h1>
      <div class="tab">
        <div class="hdtab">
          <div
            class="tabname01"
            data-id="1"
            :class="{ choose: tab == 1 }"
            @click="tabChange"
          >
            胶带纸
          </div>
          <div
            class="tabname02"
            data-id="2"
            :class="{ choose: tab == 2 }"
            @click="tabChange"
          >
            气柱
          </div>
        </div>
      </div>
      <div v-show="tab == 1" class="package02">
        <div v-for="item in package2" :key="item.id" class="package_con">
          <img :src="item.url" alt="">
          <div class="con_message">
            <p class="pri" v-text="item.price" />
            <p class="nam" v-text="item.name" />
            <p class="siz" v-text="item.mess" />
            <button>查看详情</button>
          </div>
        </div>
      </div>
      <div v-show="tab == 2" class="package03">
        <div v-for="item in package3" :key="item.id" class="package_con">
          <img :src="item.url" alt="">
          <div class="con_message">
            <p class="pri" v-text="item.price" />
            <p class="nam" v-text="item.name" />
            <p class="siz" v-text="item.mess" />
            <button>查看详情</button>
          </div>
        </div>
      </div>
      <h1 id="p3" class="p3">纸箱</h1>
      <div class="package03">
        <div v-for="item in package4" :key="item.id" class="package_con">
          <img :src="item.url" alt="">
          <div class="con_message">
            <p class="pri" v-text="item.price" />
            <p class="nam" v-text="item.name" />
            <p class="siz" v-text="item.mess" />
            <button>查看详情</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Mall',
  data() {
    return {
      package1: [
        {
          id: 1,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FBM1-2.jpg',
          name: '可封口小气泡袋 BM1',
          price: '£ 2.99',
          size: '尺寸：100mm x 165mm + 30mm'
        },
        {
          id: 2,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FBM1-2.jpg',
          name: '可封口小气泡袋 BM2',
          price: '£ 3.59',
          size: '尺寸：130mm x 205mm + 30mm'
        },
        {
          id: 3,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FBM1-2.jpg',
          name: '可封口小气泡袋 BM3',
          price: '£ 3.69',
          size: '尺寸：180mm x 280mm + 30mm'
        },
        {
          id: 4,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FBM1-2.jpg',
          name: '可封口小气泡袋 BM4',
          price: '£ 3.99',
          size: '尺寸：230mm x 280mm + 30mm'
        },
        {
          id: 5,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FBM1-2.jpg',
          name: '可封口小气泡袋 BM5',
          price: '£ 4.29',
          size: '尺寸：280mm x 355mm + 30mm'
        },
        {
          id: 6,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FBM1-2.jpg',
          name: '可封口小气泡袋 BM6',
          price: '£ 4.69',
          size: '尺寸：300mm x 430mm + 30mm'
        },
        {
          id: 7,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FBM1-2.jpg',
          name: '可封口小气泡袋 BM7',
          price: '£ 5.99',
          size: '尺寸：380mm x 430mm + 30mm'
        }
      ],
      package2: [
        {
          id: 1,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FClear_tapes_1.jpg',
          name: '48mmx66m 透明胶带',
          price: '£ 5.99',
          mess: '已到货 欢迎订购'
        },
        {
          id: 2,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FClear_tapes_1.jpg',
          name: '48mmx91m Fragile胶带',
          price: '£ 5.79',
          mess: '易碎品警示胶带'
        },
        {
          id: 3,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FClear_tapes_1.jpg',
          name: '48mmx150m 透明胶带',
          price: '£ 9.99',
          mess: '已到货 欢迎订购'
        },
        {
          id: 4,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FClear_tapes_1.jpg',
          name: '48mmx91m 透明胶带',
          price: '£ 6.99',
          mess: '已到货 欢迎订购'
        },
        {
          id: 5,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FClear_tapes_1.jpg',
          name: '25mmx66m 透明胶带',
          price: '£ 3.49',
          mess: '已到货 欢迎订购'
        }
      ],
      package3: [
        {
          id: 1,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2Fs-l1600_1.jpg',
          name: '奶粉专用气柱袋（10柱）',
          price: '£ 16.69',
          mess: '10柱双联排气柱'
        }
      ],
      package4: [
        {
          id: 1,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FShoe_box_2.jpg',
          name: '鞋箱（单双鞋）',
          price: '£ 16.69',
          mess: '10柱双联排气柱'
        },
        {
          id: 2,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FShoe_box_2.jpg',
          name: '1公斤杂货小纸箱',
          price: '£ 16.69',
          mess: '暂时缺货 2021年9月3号到货'
        },
        {
          id: 3,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FShoe_box_2.jpg',
          name: 'SD4 - 4罐奶粉箱（加强版 带印刷）',
          price: '£ 16.69',
          mess: '10柱双联排气柱'
        },
        {
          id: 4,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FShoe_box_2.jpg',
          name: 'SD1 - 6罐奶粉箱（加强版 带印刷）',
          price: '£ 16.69',
          mess: '10柱双联排气柱'
        },
        {
          id: 5,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FShoe_box_2.jpg',
          name: 'SD10 - 2罐奶粉箱 (经济版 带印刷)',
          price: '£ 16.69',
          mess: '目前缺货 请勿下单！！！！'
        },
        {
          id: 6,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FShoe_box_2.jpg',
          name: 'SD10 - 2罐奶粉箱 (加强版 带印刷)',
          price: '£ 16.69',
          mess: '已到货 欢迎下单'
        },
        {
          id: 7,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FShoe_box_2.jpg',
          name: 'LOGO - 40*50*60加强版行李纸箱',
          price: '£ 16.69',
          mess: '适用归国留学行李，搬家'
        },
        {
          id: 8,
          url: 'https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FShoe_box_2.jpg',
          name: 'SD20 - 40*50*60大纸箱',
          price: '£ 16.69',
          mess: '缺货中 28.01.2021到货'
        }
      ],
      tab: 1
    }
  },
  methods: {
    tabChange: function(e) {
      const tabId = e.target.dataset.id
      this.tab = tabId
    },
    jump02() {
      document.querySelector('#p2').scrollIntoView()
    },
    jump03() {
      document.querySelector('#p3').scrollIntoView()
    },
    handleboxClick(arg) {
      this.$router.push(`/materialdetail/${arg}`)
    }
  }
}
</script>

<style scoped>
.container {
  width: 100%;
  background-color: #f4f4f4;
}
.warp {
  margin: 0 auto;
  width: 60%;
}
.banner {
  width: 100%;
  height: 300px;
  background-image: url('/static/images/material_banner.jpg');
  background-size: cover;
  background-position: center;
  margin-bottom: 35px;
}
.navbox {
  display: flex;
  width: 100%;
  height: 270px;
  justify-content: space-between;
  margin-bottom: 70px;
}
.navbox01,
.navbox02,
.navbox03 {
  position: relative;
  cursor: pointer;
  width: 32%;
  height: 270px;
  background-color: bisque;
  background-size: cover;
  background-position: center;
  background-image: url(/static/images/navbox02.jpg);
}
.navbox01::before,
.navbox02::before,
.navbox03::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  border-top: 270px solid rgb(255, 53, 0, 0.7);
  border-right: 270px solid transparent;
  border-left: 30px solid rgb(255, 53, 0, 0.7);
}
.navbox h1 {
  position: absolute;
  top: 40px;
  left: 22px;
  color: #fff;
  font-size: 27px;
  font-weight: 600;
}
.navbox p {
  position: absolute;
  top: 100px;
  left: 15px;
  color: #fff;
  font-size: 1.7rem;
}
.package01,
.package02,
.package03 {
  display: flex;
  flex-wrap: wrap;
}
.package_con {
  padding: 1rem;
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 23%;
  height: 50vh;
  margin-left: 15px;
  margin-bottom: 20px;
  box-shadow: 0px 1px 3px 0px rgba(0, 0, 0, 0.3);
  transition: all 0.4s ease;
}
.package_con:hover {
  cursor: pointer;
  box-shadow: 0px 1px 15px 0px rgba(0, 0, 0, 0.7);
}
.package_con img {
  width: 100%;
  height: 30vh;
}
.con_message {
  position: relative;
  width: 100%;
  height: 20vh;
}

.package_con .pri {
  position: absolute;
  top: 10%;
  color: #ff7e00;
  font-size: 15px;
  margin: 0;
}
.package_con .nam {
  position: absolute;
  top: 20%;
  font-size: 1.5rem;
}
.package_con .siz {
  position: absolute;
  top: 61%;
  color: #adadc5;
  font-size: 13px;
  margin: 0;
}
.package_con button {
  position: absolute;
  top: 61%;
  margin: 0 auto;
  width: 200px;
  height: 40px;
  color: #fff;
  font-size: 20px;
  background-color: #ff631a;
  border-color: #ff631a;
  border-radius: 4px;
  opacity: 0;
  transition: all 0.4s ease;
}
.package_con button:hover {
  background-color: #ff863c;
  border-color: #ff863c;
  cursor: pointer;
}
.package_con:hover button {
  opacity: 1;
  transition: all 0.4s ease;
}
h1 {
  color: #347cd7;
  font-size: 35px;
  text-align: center;
  font-weight: 500;
  margin-bottom: 35px;
}
.tab {
  width: 100%;
  background-color: turquoise;
}
.hdtab {
  height: 40px;
  background-color: #fff;
  display: flex;
  justify-content: center;
  align-content: center;
  margin-bottom: 20px;
}
.tabname01,
.tabname02 {
  width: 97px;
  height: 40px;
  border: 1px solid #e3e7ed;
  align-items: center;
  text-align: center;
  line-height: 40px;
  font-size: 14px;
  cursor: pointer;
}
.tabname01:hover,
.tabname02:hover {
  color: #58a2ff;
}
.tabname01 {
  border-right: none;
}
.choose {
  background-color: #00c2f3;
  color: #fff !important;
}
.p3 {
  margin-top: 35px;
}
</style>
