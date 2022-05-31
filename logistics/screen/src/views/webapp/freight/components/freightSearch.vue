<template>
  <div class="container">
    <div class="searchBox">
      <el-select
        v-model="international_express.destination"
        placeholder="目的地"
        :filterable="true"
        :allow-create="true"
      >
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        />
      </el-select>
      <input
        v-model="international_express.weight"
        type="text"
        placeholder="重量 KG"
        required
      >
      <input
        v-model="international_express.length"
        type="text"
        placeholder="长度 CM"
        required
      >
      <input
        v-model="international_express.width"
        type="text"
        placeholder="宽度 CM"
        required
      >
      <input
        v-model="international_express.high"
        type="text"
        placeholder="高度 CM"
        required
      >
      <button @click="searchFreight">运费查询</button>
    </div>
    <el-dialog title="搜索结果" :visible.sync="dialogTableVisible" custom-class="dialogStyle">
      <el-table :data="searchResult">
        <el-table-column
          property="dist"
          label="目的地"
          align="center"
          width="100"
        />
        <el-table-column
          property="transport"
          label="运输方式"
          align="center"
          width="180"
        />
        <el-table-column
          property="cost"
          label="运费"
          align="center"
          width="100"
        >
          <template slot-scope="scope">
            {{ `￡${scope.row.cost}` }}
          </template>
        </el-table-column>
        <el-table-column
          property="time"
          label="运送时效"
          header-align="center"
          align="start"
          width="120"
        >
          <template slot-scope="scope">
            <div :class="`status ${scope.row.status}`" />
            {{ `${scope.row.time}` }}
          </template></el-table-column>
        <el-table-column property="remark" label="线路特点" align="center" />
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios'
import { Message } from 'element-ui'

export default {
  data() {
    return {
      options: [
        {
          value: '中国大陆',
          label: '中国大陆'
        },
        {
          value: '其他地区',
          label: '其他地区'
        }
      ],
      international_express: {
        destination: '',
        weight: '',
        length: '',
        width: '',
        high: ''
      },
      dialogTableVisible: false,
      searchResult: null
    }
  },
  methods: {
    searchFreight() {
      axios
        .get('/static/data/freightSearch.json')
        .then((res) => {
          this.searchResult = res.data
          this.dialogTableVisible = true
        })
        .catch((err) => {
          Message.error(err)
        })

      // if (Object.values(this.international_express).slice(1).join('') === '') {
      //   this.showresult = false
      // } else {
      //   if (Object.values(this.international_express).includes('')) {
      //     Message.error('物品规格填写不完全，请确认填写内容')
      //   } else {
      //     Message.success('正在查询，请稍后……')
      //     this.showresult = true
      //   }
      // }
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  width: 100%;
  box-sizing: border-box;
}
.searchBox {
  width: 50%;
  padding: 0.5rem 1rem;
  background-color: rgba(255, 255, 255, 0.8);
  margin: 0 auto;
  display: flex;
  justify-content: space-around;
  align-items: center;
  * {
    margin: 1rem 2rem;
  }
  input {
    width: 6rem;
    height: 4rem;
    border: none;
    outline: none;
    background: transparent;
    border-bottom: 1px solid rgb(127, 140, 141);
  }
  button {
    cursor: pointer;
    width: 10rem;
    height: 4rem;
    color: #fff;
    background-color: rgba(41, 128, 185, 1);
    border: none;
    &:active {
      transform: scale(0.9);
    }
    &:hover {
      background-color: rgba(52, 152, 219, 1);
    }
  }
}
.dialogStyle {
  width: 1800px;
}
.searchResult {
  color: #fff;
  width: 50%;
  height: 600px;
  margin: 0 auto;
  padding: 0.5rem 1rem;
  background-color: red;
}
.status {
  width: 1.5rem;
  height: 1.5rem;
  border-radius: 50%;
  margin: 0 1rem;
  display: inline-block;
}
.normal {
  background-color: #27ae60;
}
.delay {
  background-color: #f39c12;
}
.fault {
  background-color: #c0392b;
}
</style>
