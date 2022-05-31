<template>
  <div class="container">
    <div class="statusBox">
      <div class="box">
        <div class="status normalStatus" />
        <span>近期时效正常</span>
      </div>
      <div class="box">
        <div class="status delayStatus" />
        <span>近期小量延误</span>
      </div>
      <div class="box">
        <div class="status faultStatus" />
        <span>近期较多延误</span>
      </div>
    </div>
    <div class="tableBox">
      <el-table
        :data="tableData"
        border
        :cell-style="rowStyle"
        :header-cell-style="headerStyle"
      >
        <el-table-column
          prop="weight"
          label="重量(kg)/货币(₤)"
          align="center"
          width="90"
        />
        <el-table-column
          prop="ParcelForce自送邮局"
          label="ParcelForce自送邮局"
          align="center"
        />
        <el-table-column
          prop="ParcelForce上门取件"
          label="ParcelForce上门取件"
          align="center"
        />
        <el-table-column
          prop="ParcelForce自送Depot"
          label="ParcelForce自送Depot"
          align="center"
        />
        <el-table-column
          prop="UPS文件特快"
          label="UPS文件特快"
          align="center"
        />
        <el-table-column
          prop="ParcelForce优先包"
          label="ParcelForce优先包"
          align="center"
        />
        <el-table-column
          prop="ParcelForce经济包"
          label="ParcelForce经济包"
          align="center"
        />
        <el-table-column
          prop="ParcelForce小包"
          label="ParcelForce小包"
          align="center"
        />
        <el-table-column
          prop="Bpost比利时邮政"
          label="Bpost比利时邮政"
          align="center"
        />
        <el-table-column prop="六罐奶粉" label="六罐奶粉" align="center" />
        <el-table-column prop="四罐奶粉" label="四罐奶粉" align="center" />
        <el-table-column prop="杂货包税" label="333杂货包税" align="center" />
        <el-table-column prop="法国邮政" label="法国邮政" align="center" />
        <el-table-column prop="杂货小包" label="杂货小包" align="center" />
        <el-table-column prop="护肤品小包" label="护肤品小包" align="center" />
        <el-table-column prop="套装专线" label="套装专线" align="center" />
        <el-table-column prop="Plus杂货" label="333Plus杂货" align="center" />
        <el-table-column
          prop="海运行李"
          label="海运行李（服务暂停）"
          align="center"
        />
        <el-table-column prop="空运行李" label="空运行李" align="center" />
        <el-table-column prop="两罐奶粉" label="两罐奶粉" align="center" />
      </el-table>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      normalStatus: [4, 5, 7, 9, 10, 11, 13, 14, 15, 16, 19],
      delayStatus: [1, 2, 3, 8],
      faultStatus: [6, 12, 18],
      stopStatus: [17],
      tableData: [
        {
          weight: '0.01-0.50',
          ParcelForce自送邮局: 30.54,
          ParcelForce上门取件: 32.55,
          ParcelForce自送Depot: 14.01,
          UPS文件特快: 28.5,
          ParcelForce优先包: 27.96,
          ParcelForce经济包: 19.63,
          ParcelForce小包: 9.68,
          Bpost比利时邮政: 9.66,
          六罐奶粉: 28.0,
          四罐奶粉: 17.5,
          杂货包税: 8.93,
          法国邮政: 13.3,
          杂货小包: 13.4,
          护肤品小包: 15.02,
          套装专线: 13.4,
          Plus杂货: 8.93,
          海运行李: '',
          空运行李: 76.46,
          两罐奶粉: 11.0
        },
        {
          weight: '0.51-1.00',
          ParcelForce自送邮局: 30.54,
          ParcelForce上门取件: 32.55,
          ParcelForce自送Depot: 14.01,
          UPS文件特快: 28.5,
          ParcelForce优先包: 30.56,
          ParcelForce经济包: 19.71,
          ParcelForce小包: 11.49,
          Bpost比利时邮政: 11.86,
          六罐奶粉: 28.0,
          四罐奶粉: 17.5,
          杂货包税: 8.93,
          法国邮政: 14.55,
          杂货小包: 13.4,
          护肤品小包: 15.02,
          套装专线: 13.4,
          Plus杂货: 8.93,
          海运行李: '',
          空运行李: 76.46,
          两罐奶粉: 11.0
        },
        {
          weight: '1.01-1.50',
          ParcelForce自送邮局: 30.54,
          ParcelForce上门取件: 32.55,
          ParcelForce自送Depot: 16.26,
          UPS文件特快: '',
          ParcelForce优先包: 33.16,
          ParcelForce经济包: 19.78,
          ParcelForce小包: 13.3,
          Bpost比利时邮政: 12.62,
          六罐奶粉: 28.0,
          四罐奶粉: 17.5,
          杂货包税: 10.54,
          法国邮政: 16.86,
          杂货小包: 15.8,
          护肤品小包: 17.72,
          套装专线: 15.8,
          Plus杂货: 10.54,
          海运行李: '',
          空运行李: 76.46,
          两罐奶粉: 11.0
        },
        {
          weight: '1.51-2.00',
          ParcelForce自送邮局: 30.54,
          ParcelForce上门取件: 32.55,
          ParcelForce自送Depot: 16.26,
          UPS文件特快: '',
          ParcelForce优先包: 35.75,
          ParcelForce经济包: 19.86,
          ParcelForce小包: 15.12,
          Bpost比利时邮政: 14.58,
          六罐奶粉: 28.0,
          四罐奶粉: 17.5,
          杂货包税: 11.51,
          法国邮政: 16.94,
          杂货小包: 17.26,
          护肤品小包: 19.35,
          套装专线: 17.26,
          Plus杂货: 11.51,
          海运行李: '',
          空运行李: 76.46,
          两罐奶粉: 11.0
        },
        {
          weight: '2.01-2.50',
          ParcelForce自送邮局: 30.54,
          ParcelForce上门取件: 32.55,
          ParcelForce自送Depot: 18.51,
          UPS文件特快: '',
          ParcelForce优先包: 38.35,
          ParcelForce经济包: 19.94,
          ParcelForce小包: 16.94,
          Bpost比利时邮政: 16.38,
          六罐奶粉: 28.0,
          四罐奶粉: 17.5,
          杂货包税: 13.11,
          法国邮政: 19.24,
          杂货小包: 19.66,
          护肤品小包: 22.05,
          套装专线: 19.66,
          Plus杂货: 13.11,
          海运行李: '',
          空运行李: 76.46,
          两罐奶粉: 11.0
        },
        {
          weight: '2.51-3.00',
          ParcelForce自送邮局: 30.54,
          ParcelForce上门取件: 32.55,
          ParcelForce自送Depot: 18.51,
          UPS文件特快: '',
          ParcelForce优先包: 40.88,
          ParcelForce经济包: 20.01,
          ParcelForce小包: 18.75,
          Bpost比利时邮政: 16.46,
          六罐奶粉: 28.0,
          四罐奶粉: 17.5,
          杂货包税: 14.08,
          法国邮政: 19.32,
          杂货小包: 21.12,
          护肤品小包: 23.68,
          套装专线: 21.12,
          Plus杂货: 14.08,
          海运行李: '',
          空运行李: 76.46,
          两罐奶粉: 11.0
        }
      ]
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      axios
        .get('/static/data/freightTable.json')
        .then((res) => {
          console.log(res)
          this.tableData = res.data
        })
        .catch((err) => {
          console.log(err)
        })
    },
    rowStyle(column) {
      if (column.columnIndex === 0) {
        return 'background-color: #3498db;color: #fff;'
      }
    },
    headerStyle(column) {
      if (this.normalStatus.includes(column.columnIndex)) {
        return 'background-color: #27ae60; color: #fff;'
      } else if (this.delayStatus.includes(column.columnIndex)) {
        return 'background-color: #f39c12; color: #fff;'
      } else if (this.faultStatus.includes(column.columnIndex)) {
        return 'background-color: #c0392b; color: #fff;'
      } else if (this.stopStatus.includes(column.columnIndex)) {
        return 'background-color: #95a5a6; color: #fff;'
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  position: relative;
  width: 100%;
  box-sizing: border-box;
  margin: 3rem 0;
}
.tableClass .cell {
  padding: 0 !important;
  height: 40px;
  line-height: 30px !important;
  text-align: center;
}
.statusBox {
  width: 85%;
  padding: 0.5rem 1rem;
  margin: 1rem auto;
  display: flex;
  .box {
    display: flex;
    align-items: center;
    font-size: 1.5rem;
    color: #34495e;
  }
  .status {
    width: 2rem;
    height: 2rem;
    border-radius: 50%;
    margin: 0 1rem;
  }
  .normalStatus {
    background-color: #27ae60;
  }
  .delayStatus {
    background-color: #f39c12;
  }
  .faultStatus {
    background-color: #c0392b;
  }
}
.tableBox {
  width: 86%;
  padding: 0.5rem 1rem;
  margin: 0 auto;
}
</style>
