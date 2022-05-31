<template>
  <div class="searchList">
    <div class="searchList1">
      <span class="searchSpan">品牌：</span>
      <ul class="searchList1Name">
        <li>
          <a href="">爱他美（Aptamil）</a>
        </li>
        <li>
          <a href="">美素佳儿（Friso）</a>
        </li>
        <li>
          <a href=""> 伊利</a>
        </li>
        <li>
          <a href="">美赞臣（MeadJohnson）</a>
        </li>
        <li>
          <a href="">君乐宝（JUNLEBAO）</a>
        </li>
        <li>
          <a href="">惠氏（Wyeth）</a>
        </li>
        <li>
          <a href="">雀巢（Nestle）</a>
        </li>
        <li>
          <a href="">蒙牛</a>
        </li>
      </ul>
      <el-dropdown trigger="click">
        <span class="el-dropdown-link">
          更多<i class="el-icon-arrow-down el-icon--right" />
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>雅培（Abbott）</el-dropdown-item>
          <el-dropdown-item>a2</el-dropdown-item>
          <el-dropdown-item>安佳（Anchor）</el-dropdown-item>
          <el-dropdown-item disabled>完达山（暂时缺货）</el-dropdown-item>
          <el-dropdown-item>荷兰乳牛</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-popover ref="popover4" width="400" trigger="click">
        <el-checkbox
          v-model="checkAll"
          :indeterminate="isIndeterminate"
          @change="handleCheckAllChange"
        >全选</el-checkbox>
        <div style="margin: 15px 0" />
        <el-checkbox-group
          v-model="checkedCities"
          @change="handleCheckedCitiesChange"
        >
          <el-checkbox v-for="city in cities" :key="city" :label="city">{{
            city
          }}</el-checkbox>
        </el-checkbox-group>
      </el-popover>
      <el-button v-popover:popover4 type="text" size="mini">多选</el-button>
    </div>
    <div class="searchList2">
      <span class="searchSpan">价格：</span>
      <ul class="searchList2Name">
        <li>
          <a href="">0-200</a>
        </li>
        <li>
          <a href="">200-300</a>
        </li>
        <li>
          <a href=""> 300-500</a>
        </li>
        <li>
          <a href="">500-1000</a>
        </li>
        <li>
          <a href="">1000-1900</a>
        </li>
        <li>
          <a href="">1900-16600</a>
        </li>
      </ul>
      <div class="inputBox">
        <el-input v-model="input8" size="mini" placeholder="最低价" />
        <el-col class="line" :span="2">-</el-col>
        <el-input v-model="input9" size="mini" placeholder="最高价" />
      </div>
      <el-button size="mini" type="text">确定</el-button>
    </div>
    <div class="searchList3">
      <span class="searchSpan">高级选项：</span>
      <el-dropdown>
        <span class="el-dropdown-link">
          段位<i class="el-icon-arrow-down el-icon--right" />
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>1段</el-dropdown-item>
          <el-dropdown-item>2段</el-dropdown-item>
          <el-dropdown-item>3段</el-dropdown-item>
          <el-dropdown-item>4段</el-dropdown-item>
          <el-dropdown-item>4段以上</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-dropdown>
        <span class="el-dropdown-link">
          蓝帽标识<i class="el-icon-arrow-down el-icon--right" />
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>保健食品</el-dropdown-item>
          <el-dropdown-item>营养膳食补充剂</el-dropdown-item>
          <el-dropdown-item>机能食品</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-dropdown>
        <span class="el-dropdown-link">
          净含量<i class="el-icon-arrow-down el-icon--right" />
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>0-500g</el-dropdown-item>
          <el-dropdown-item>0-400g</el-dropdown-item>
          <el-dropdown-item>501g-1kg</el-dropdown-item>
          <el-dropdown-item>401-800g</el-dropdown-item>
          <el-dropdown-item>1.1-2kg</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-dropdown>
        <span class="el-dropdown-link">
          剂型<i class="el-icon-arrow-down el-icon--right" />
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>粉剂</el-dropdown-item>
          <el-dropdown-item>滴剂</el-dropdown-item>
          <el-dropdown-item>口服溶液</el-dropdown-item>
          <el-dropdown-item>胶囊</el-dropdown-item>
          <el-dropdown-item>片剂</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-dropdown>
        <span class="el-dropdown-link">
          产地<i class="el-icon-arrow-down el-icon--right" />
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>欧洲</el-dropdown-item>
          <el-dropdown-item>中国大陆</el-dropdown-item>
          <el-dropdown-item>德国</el-dropdown-item>
          <el-dropdown-item>法国</el-dropdown-item>
          <el-dropdown-item>英国</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-dropdown>
        <span class="el-dropdown-link">
          适用年龄<i class="el-icon-arrow-down el-icon--right" />
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>0-6个月</el-dropdown-item>
          <el-dropdown-item>0-3个月</el-dropdown-item>
          <el-dropdown-item>新生儿</el-dropdown-item>
          <el-dropdown-item>3-6个月</el-dropdown-item>
          <el-dropdown-item>6岁以上</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-dropdown>
        <span class="el-dropdown-link">
          规格类型<i class="el-icon-arrow-down el-icon--right" />
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>便携装</el-dropdown-item>
          <el-dropdown-item>组合装</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-dropdown>
        <span class="el-dropdown-link">
          国产/进口<i class="el-icon-arrow-down el-icon--right" />
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>国产</el-dropdown-item>
          <el-dropdown-item>进口</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-dropdown>
        <span class="el-dropdown-link">
          包装形式<i class="el-icon-arrow-down el-icon--right" />
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>礼盒装</el-dropdown-item>
          <el-dropdown-item>袋装</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-dropdown>
        <span class="el-dropdown-link">
          成分特点<i class="el-icon-arrow-down el-icon--right" />
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>高钙</el-dropdown-item>
          <el-dropdown-item>高铁</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
const cityOptions = ['雅培（Abbott）', 'a2', '安佳（Anchor）', '荷兰乳牛']
export default {
  name: 'Search2',
  data() {
    return {
      checkAll: false,
      checkedCities: ['雅培（Abbott）', 'a2'],
      cities: cityOptions,
      isIndeterminate: true,
      input8: '',
      input9: ''
    }
  },
  methods: {
    handleCheckAllChange(val) {
      this.checkedCities = val ? cityOptions : []
      this.isIndeterminate = false
    },
    handleCheckedCitiesChange(value) {
      const checkedCount = value.length
      this.checkAll = checkedCount === this.cities.length
      this.isIndeterminate =
        checkedCount > 0 && checkedCount < this.cities.length
    }
  }
}
</script>

<style lang="scss" scoped>
.searchList {
  width: 100%;
  height: 100%;
}
.searchSpan {
  display: block;
  width: 10%;
  height: 100%;
  background-color: #f3f3f3;
  font-size: 14px;
  font-weight: bold;
  line-height: 2.5vw;
  padding-left: 1vw;
  white-space: nowrap;
  color: #666;
}
.searchList1 {
  width: 100%;
  border-top: 1px solid #ddd;
  box-sizing: border-box;
  display: flex;
  align-items: center;
}
.searchList1Name {
  width: 80%;
  height: 100%;
  padding: 0;
  margin: 0;
  list-style: none;
  display: flex;
  line-height: 2.5vw;
  li {
    padding-right: 1vw;
    padding-left: 0.5vw;
    a {
      text-decoration: none;
      cursor: pointer;
      zoom: 1;
      white-space: nowrap;
      color: #005aa0;
      &:hover {
        color: rgb(109, 8, 58);
      }
    }
  }
}

.searchList2 {
  width: 100%;
  height: 2.5vw;
  border-top: 1px solid #ddd;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  .inputBox {
    width: 20%;
    display: flex;
    align-items: center;
    text-align: center;
  }
}
.searchList2Name {
  width: 42%;
  height: 100%;
  padding: 0;
  margin: 0;
  list-style: none;
  display: flex;
  line-height: 2.5vw;
  li {
    padding-right: 1vw;
    padding-left: 0.5vw;
    a {
      text-decoration: none;
      cursor: pointer;
      zoom: 1;
      white-space: nowrap;
      color: #005aa0;
      &:hover {
        color: rgb(109, 8, 58);
      }
      &.line {
        margin: 5px -4px 0 3px;
      }
    }
  }
}

.searchList3 {
  width: 100%;
  // height: 2.5vw;
  border-top: 1px solid #ddd;
  border-bottom: 1px solid #ddd;
  box-sizing: border-box;
  display: flex;
  align-items: center;
}
.el-dropdown-link {
  font-size: 1rem;
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 0.5rem 1rem;
  margin: 0 1rem;
  color: #005aa0;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
  i {
    display: flex;
    align-items: center;
  }
}
</style>
