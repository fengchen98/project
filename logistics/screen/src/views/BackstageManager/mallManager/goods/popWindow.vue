<template>
  <div class="popContainer">
    <el-form
      ref="form"
      class="form"
      :model="currentdata"
      label-width="100px"
      :inline="true"
    >
      <el-form-item label="品牌名称" size="mini">
        <el-input v-model="currentdata.brand" />
      </el-form-item>
      <el-form-item label="商品段位" size="mini" class="selectFloor">
        <el-select v-model="currentdata.milk_rank" placeholder="段位选择">
          <el-option label="1段" value="1段" />
          <el-option label="2段" value="2段" />
          <el-option label="3段" value="3段" />
          <el-option label="4段" value="4段" />
          <el-option label="4段以上" value="4段以上+" />
        </el-select>
      </el-form-item>
      <el-form-item label="商品价格" size="mini" class="goodPrice">
        <el-input v-model="currentdata.price" />
      </el-form-item>
      <el-form-item label="商品剂型" size="mini" class="goodType">
        <el-select v-model="currentdata.dosage_form" placeholder="剂型选择">
          <el-option label="粉剂" value="粉剂" />
          <el-option label="滴剂" value="滴剂" />
          <el-option label="口服溶液" value="口服溶液" />
          <el-option label="胶囊" value="胶囊" />
          <el-option label="片剂" value="片剂" />
        </el-select>
      </el-form-item>
      <el-form-item label="商品产地" size="mini" class="goodArea">
        <el-select v-model="currentdata.origin_place" placeholder="产地选择">
          <el-option label="欧洲" value="欧洲" />
          <el-option label="中国大陆" value="中国大陆" />
          <el-option label="德国" value="德国" />
          <el-option label="法国" value="法国" />
          <el-option label="英国" value="英国" />
        </el-select>
      </el-form-item>
      <el-form-item label="适用年龄" size="mini" class="goodAge">
        <el-select v-model="currentdata.suit_age" placeholder="年龄选择">
          <el-option label="0-6个月" value="0-6个月" />
          <el-option label="0-3个月" value="0-3个月" />
          <el-option label="新生儿" value="新生儿" />
          <el-option label="3-6个月" value="3-6个月" />
          <el-option label="6岁以上" value="6岁以上" />
        </el-select>
      </el-form-item>
      <el-form-item label="规格类型" size="mini" class="Spec">
        <el-select v-model="currentdata.spec_type" placeholder="规格类型">
          <el-option label="便携装" value="便携装" />
          <el-option label="组合装" value="组合装" />
        </el-select>
      </el-form-item>
      <el-form-item label="国产/进口" size="mini" class="Di">
        <el-select v-model="currentdata.origin_place" placeholder="国产/进口">
          <el-option label="国产" value="国产" />
          <el-option label="进口" value="进口" />
        </el-select>
      </el-form-item>
      <el-form-item label="包装形式" size="mini" class="packType">
        <el-select v-model="currentdata.package_type" placeholder="包装形式">
          <el-option label="礼盒装" value="礼盒装" />
          <el-option label="袋装" value="袋装" />
        </el-select>
      </el-form-item>
      <el-form-item label="成分特点" size="mini" class="compositionCharac">
        <el-select v-model="currentdata.ingredient" placeholder="成分特点">
          <el-option label="高钙" value="高钙" />
          <el-option label="高铁" value="高铁" />
        </el-select>
      </el-form-item>
      <el-form-item label="商品净含量" size="mini" class="netContent">
        <el-select v-model="currentdata.net_weight" placeholder="净含量选择">
          <el-option label="0-500g" value="0-500g" />
          <el-option label="0-400g" value="0-400g" />
          <el-option label="501-1kg" value="501-1kg" />
          <el-option label="401-800g" value="401-800g" />
          <el-option label="1.1-2kg" value="1.1-2kg" />
        </el-select>
      </el-form-item>
      <el-form-item label="商品蓝帽标识" size="mini" class="blueHat">
        <el-select v-model="currentdata.blue_cap_mark" placeholder="蓝帽标识">
          <el-option label="保健食品" value="保健食品" />
          <el-option label="营养膳食补充剂" value="营养膳食补充剂" />
          <el-option label="机能食品" value="机能食品" />
        </el-select>
      </el-form-item>
      <el-form-item label="添加备注" class="moreInput">
        <el-input v-model="currentdata.more" type="textarea" :rows="1" />
      </el-form-item>
      <el-form-item label="商品描述" class="moreInput">
        <el-input v-model="currentdata.simple_desc" type="textarea" :rows="1" />
      </el-form-item>
      <div class="editPics">
        <div class="editPic">
          <el-upload
            class="upload-demo1"
            drag
            action="fake"
            multiple
            :limit="10"
            :file-list="fileList1"
            :auto-upload="false"
            :show-file-list="true"
            :http-request="submitUpload"
            :on-change="handleFileListChange"
            :on-success="handleImagesSuccess"
            :on-error="handleErrorSuccess"
            :on-exceed="handleExceed"
            :before-upload="beforeImagesUpload"
          >
            <i class="el-icon-upload" />
            <div class="el-upload__text">
              将文件拖到此处，或<em>点击上传</em>
            </div>
            <div slot="tip" class="el-upload__tip">
              只能上传jpg/png文件，且不超过500kb
            </div>
          </el-upload>
          <el-button
            size="mini"
            @click="submitUpload((apply = 'images'))"
          >上传商品主图</el-button>
        </div>
        <div class="editPic">
          <el-upload
            class="upload-demo2"
            drag
            action="fake"
            multiple
            :limit="10"
            :file-list="fileList2"
            :auto-upload="false"
            :show-file-list="true"
            :http-request="submitUpload"
            :on-change="handleFileListChange"
            :on-success="handleImagesSuccess"
            :on-error="handleErrorSuccess"
            :on-exceed="handleExceed"
            :before-upload="beforeImagesUpload"
          >
            <i class="el-icon-upload" size="mini" />
            <div class="el-upload__text">
              将文件拖到此处，或<em>点击上传</em>
            </div>
            <div slot="tip" class="el-upload__tip">
              只能上传jpg/png文件，且不超过500kb
            </div>
          </el-upload>
          <el-button
            size="mini"
            @click="submitUpload((apply = 'introduce_images'))"
          >上传商品详情图</el-button>
        </div>
      </div>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button class="elbtn" @click="closePop">取 消</el-button>
      <el-button
        spec_type="primary"
        class="elbtn"
        @click="reEdit"
      >确 定</el-button>
    </span>
  </div>
</template>

<script>
import { uploadProductImageDefault } from '@/api/uploadImage'
import { Message } from 'element-ui'

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
      dialogImageUrl: '',
      dialogVisible: false,
      paginationConfig: {
        pageSize: 20,
        total: 1000
      },
      fileList: [],
      fileList1: [],
      fileList2: []
    }
  },
  methods: {
    handleFileListChange(file, fileList) {
      this.fileList = fileList
    },
    submitUpload(apply) {
      const formData = new FormData()
      this.fileList.forEach((o, index) => {
        formData.append('files', o.raw)
      })

      uploadProductImageDefault(
        formData,
        'product',
        this.currentdata.id,
        apply
      ).then((res) => {
        Message.success('图片上传成功')
      })
    },
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    handleImagesSuccess() {
      Message.success('图片上传成功')
    },
    handleErrorSuccess() {
      Message.error('图片上传失败')
    },
    handleExceed() {
      Message.warning('最大上传数量不能超过10张图片')
    },
    beforeImagesUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isJPG) {
        Message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        Message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    reEdit() {
      const addObj = {
        id: this.currentdata.id,
        brand: this.currentdata.brand,
        price: this.currentdata.price,
        milk_rank: this.currentdata.milk_rank,
        blue_cap_mark: this.currentdata.blue_cap_mark,
        content: this.currentdata.content,
        dosage_form: this.currentdata.dosage_form,
        origin_place: this.currentdata.origin_place,
        suit_age: this.currentdata.suit_age,
        spec_type: this.currentdata.spec_type,
        package_type: this.currentdata.package_type,
        ingredient: this.currentdata.ingredient,
        images: [this.currentdata.images],
        simple_desc: this.currentdata.simple_desc,
        more: this.currentdata.more
      }
      this.$emit('editwindow', addObj)
    },
    closePop() {
      this.$emit('closepop')
    }
  }
}
</script>

<style lang="scss" scoped>
.form {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  .el-form-item {
    width: 30%;
    display: flex;
    .el-input,
    .el-select {
      width: 180px !important;
    }
  }
  .moreInput {
    width: 91.5%;
  }
}
.editPics {
  width: 87%;
  margin: 30px 0;
  // background-color: rgb(75, 34, 34);
  display: flex;
  justify-content: space-around;
  .editPic {
    width: 38%;
  }
}
.dialog-footer {
  display: block;
  width: 100%;
  height: 50px;
  margin-top: 15px;
  padding-left: 70%;
  // .elbtn {
  //   display: flex;
  // }
}
.upload-demo1 {
  margin-bottom: 10px;
}
.upload-demo2 {
  margin-bottom: 10px;
}
.el-textarea {
  width: 920px;
}
.avatar-uploader .el-upload {
  width: 100%;
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
  font-size: 20px;
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
