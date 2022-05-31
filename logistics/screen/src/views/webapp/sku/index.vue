<template>
  <div class="container">
    <div class="searchBox">
      <el-input
        v-model="searchipt"
        placeholder="请输入内容"
        class="input-with-select"
      >
        <el-select slot="prepend" v-model="select" placeholder="请选择服务">
          <el-option
            v-for="li in serviceoptions"
            :key="li.label"
            :label="li.label"
            :value="li.label"
          />
        </el-select>
        <el-button slot="append" icon="el-icon-search" />
      </el-input>
      <div v-if="alertContent.isShow" class="alertBox">
        <div class="title">
          <i class="el-icon-warning-outline" />
          <p v-text="alertContent.title" />
          <i
            class="closeIcon el-icon-close"
            @click="alertContent.isShow = false"
          />
        </div>
        <span v-text="alertContent.description" />
      </div>
    </div>
    <div class="tableBox">
      <el-table
        :data="dataSource.list"
        style="width: 100%"
        :header-cell-style="headerStyle"
      >
        <el-table-column prop="skuid" label="sku" width="80" align="center" />
        <el-table-column prop="catalog" label="分类" width="80" align="center" />
        <el-table-column prop="brand" label="品牌" width="120" align="center" />
        <el-table-column prop="skucname" label="名称" align="center" />
        <el-table-column prop="skuename" label="英文名称" align="center" />
        <el-table-column prop="price" label="单价" width="80" align="center" />
      </el-table>
      <el-pagination
        :background="true"
        layout="prev, pager, next"
        :total="total"
        :page-size="psize"
        @current-change="handleClickpagination"
      />
    </div>
  </div>
</template>

<script>
import mixinInterface from '@/mixin/interface'

export default {
  mixins: [mixinInterface],
  data() {
    return {
      serviceoptions: [
        {
          label: '奶粉包税'
        },
        {
          label: '万国邮政'
        },
        {
          label: '行李文件'
        },
        {
          label: '杂货包税'
        },
        {
          label: '贵重物品'
        }
      ],
      searchipt: '',
      select: '',
      alertContent: {
        isShow: true,
        title: '您可以在此页面查询系统内允许邮寄的物品',
        description:
          '如要搜索某个物品是否已在系统内，您可以先选择您要查询的国际服务，再输入关键来进行搜索物品，系统支持多个关键 字搜索，用空格区分即可，例如：果泥 30g; 搜索结果就会显示所有包含果泥以及30g的物品。如果您想要邮寄的物品不在 此产品列表内，您可以进入【我的账户】-【物品申报】进行申请添加, 点击此处立即申报。'
      },
      initData: [],
      showData: [],
      total: null,
      psize: 20,
      pageIndex: 0,
      url: {
        list: '/webapp/sku/list'
      }
    }
  },
  created() {
    console.log('sku dataSource', this.dataSource)
    // this.getData()
  },
  methods: {
    // getData() {
    //   axios.get('/webapp/data/skuList.json').then((res) => {
    //     this.initData = res.data
    //     this.total = this.initData.length
    //     this.changeShowData()
    //   })
    // },
    handleClickpagination(page) {
      this.pageIndex = page - 1
      this.changeShowData()
    },
    changeShowData() {
      this.showData = this.initData.slice(
        this.pageIndex * this.psize,
        (this.pageIndex + 1) * this.psize
      )
    },
    headerStyle(column) {
      return 'border-radius: 5px;background-color: #2980b9; color: #fff;'
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
.searchBox {
  position: relative;
  padding: 2rem;
  width: 60%;
  margin: 2rem auto;
  background-color: rgba(243, 249, 250, 0.897);
  .input-with-select {
    padding: 0 20%;
    .el-select {
      width: 150px;
      background-color: #fff;
      border-radius: 5px;
      border: 1px solid #dde0e2;
    }
    .el-input {
      .el-input__inner {
        border-radius: 5px;
        border: 1px solid #dde0e2;
        background-color: #fff !important;
      }
    }
    .el-button {
      color: #fff;
      background-color: #2980b9;
    }
  }
  .alertBox {
    position: relative;
    background-color: rgba(250, 239, 223, 0.85);
    padding: 0 1rem 1rem 1rem;
    border-radius: 5px;
    margin-top: 2rem;
    color: #41566b;
    .title {
      display: flex;
      justify-content: flex-start;
      align-items: center;
      font-size: 1.8rem;
      i {
        margin-right: 1rem;
        &:nth-child(1) {
          font-size: 3rem;
          font-weight: 600;
          color: rgba(243, 156, 18, 1);
        }
      }
      .closeIcon {
        cursor: pointer;
        position: absolute;
        right: 2rem;
        &:hover {
          color: rgb(134, 139, 143);
        }
      }
    }
    span {
      font-size: 1.5rem;
      line-height: 2.5rem;
      padding: 2rem;
      padding-left: 2rem;
    }
  }
}
.tableBox {
  position: relative;
  //   padding: 2rem;
  width: 60%;
  margin: 2rem auto;
  background-color: rgba(243, 249, 250, 0.897);
}
</style>
