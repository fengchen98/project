<template>
  <div class="detailContainer">
    <el-form
      ref="form"
      class="form"
      :model="tableData"
      label-width="100px"
      :inline="true"
      label-position="right"
    >
      <el-form-item label="商品品牌" size="mini" class="demos">
        <el-input v-model="tableData.brand" :disabled="true" />
      </el-form-item>
      <el-form-item label="商品名称" size="mini" class="demos">
        <el-input v-model="tableData.title" :disabled="true" />
      </el-form-item>

      <el-form-item label="商品价格" size="mini" class="demos">
        <el-input v-model="tableData.price" :disabled="true" />
      </el-form-item>
      <!-- 分割线 -->
      <el-divider content-position="center">高级选项设置</el-divider>
      <el-form-item label="商品段位" size="mini" class="demos">
        <el-input v-model="tableData.milk_rank" :disabled="true" />
      </el-form-item>

      <el-form-item label="商品剂型" size="mini" class="demos">
        <el-input v-model="tableData.dosage_form" :disabled="true" />
      </el-form-item>
      <el-form-item label="商品产地" size="mini" class="demos">
        <el-input v-model="tableData.origin_place" :disabled="true" />
      </el-form-item>
      <el-form-item label="适用年龄" size="mini" class="demos">
        <el-input v-model="tableData.suit_age" :disabled="true" />
      </el-form-item>
      <el-form-item label="规格类型" size="mini" class="demos">
        <el-input v-model="tableData.spec_type" :disabled="true" />
      </el-form-item>
      <el-form-item label="包装形式" size="mini" class="demos">
        <el-input v-model="tableData.package_type" :disabled="true" />
      </el-form-item>
      <el-form-item label="成分特点" size="mini" class="demos">
        <el-input v-model="tableData.ingredient" :disabled="true" />
      </el-form-item>
      <el-form-item label="商品净含量" size="mini" class="demos">
        <el-input v-model="tableData.net_weight" :disabled="true" />
      </el-form-item>
      <el-form-item label="商品蓝帽标识" size="mini" class="demos">
        <el-input v-model="tableData.blue_cap_mark" :disabled="true" />
      </el-form-item>
      <el-dialog :visible.sync="dialogVisible" size="tiny">
        <img width="100%" :src="dialogImageUrl" alt>
      </el-dialog>
      <el-form-item label="备注" class="moreInput" size="mini">
        <el-input v-model="tableData.more" :disabled="true" />
      </el-form-item>
      <el-form-item label="商品描述" class="moreInput" size="mini">
        <el-input v-model="tableData.simple_desc" :disabled="true" />
      </el-form-item>
      <el-divider content-position="center">商品主图</el-divider>
      <div class="mainPic">
        <img
          v-for="(url, index) in tableData.images"
          :key="index"
          style="width: 100px; height: 100px; margin: 0 5px"
          :src="url"
        >
      </div>
      <el-divider content-position="center">商品详情页图片</el-divider>
      <div class="mainPic">
        <img
          v-for="(url, index) in tableData.introduce_images"
          :key="index"
          :src="url"
        >
      </div>
    </el-form>
    <!-- <span slot="footer" class="dialog-footer">
      <el-button
        style="margin-left: 3%; margin-top: 2%"
        @click="closeDetail"
      >确 定</el-button>
    </span> -->
  </div>
</template>

<script>
export default {
  name: 'PopWindow',
  props: {
    currentdata: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      // url: {
      //   list: '/specialservice/list'
      // },
      dialogImageUrl: '',
      urls: this.currentdata.images,
      xurls: this.currentdata.introduce_images,
      dialogVisible: false,
      multipleSelection: [],
      paginationConfig: {
        pageSize: 20,
        total: 1000
      },
      tableData: this.currentdata
    }
  },
  watch: {
    currentdata(newValue, oldValue) {
      if (oldValue !== newValue) {
        this.tableData = newValue
      }
    }
  },
  methods: {
    closeDetail() {
      this.$emit('detailwindow')
    }
  }
}
</script>

<style lang="scss" scoped>
.form {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  .demos {
    width: 30%;
    display: flex;
    .el-input,
    .el-select {
      width: 180px !important;
    }
  }
  .activeForm {
    width: 100%;
    margin-left: 3%;
  }
}
.moreInput {
  width: 91.5%;
}
.mainPic {
  position: relative;
  width: 100%;
  height: 30%;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  // border: 1px solid rgb(15, 29, 65);
  border-radius: 3%;
  padding: 5px 32px;
  img {
    width: 60%;
    // height: 100px;
    margin: 0 5px;
    margin-top: 15px;
    border: 2px solid #ccc;
    border-radius: 5px;
    object-fit: contain;
    // width: 80%;
  }
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
>
