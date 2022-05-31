<template>
  <div class="container">
    <div class="tableBox">
      <el-table
        :data="initData.freight"
        stripe
        border
        width="200"
        max-height="500"
      >
        <el-table-column prop="weight" label="重量" align="center" width="100">
          <template slot-scope="scope">
            {{ scope.row.weight + ' KG' }}
          </template>
        </el-table-column>
        <el-table-column
          prop="freight"
          label="国际运费(₤)"
          align="center"
          width="100"
        />
      </el-table>
    </div>
  </div>
</template>

<script>
// import axios from 'axios'

export default {
  props: {
    initData: {
      type: Object,
      default: () => {}
    }
  }
}
</script>

<style scoped>
.container {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  width: 100%;
}
.tableBox {
  width: 201px;
  margin: 0 auto;
  display: flex;
  justify-content: center;
}
</style>
