<template>
  <div class="popContainer">
    <el-form
      ref="form"
      class="form"
      :model="currentdata"
      label-width="100px"
      :inline="true"
      :rules="rules"
    >
      <el-form-item label="商品名称" size="mini" prop="title">
        <el-input v-model="currentdata.title" />
      </el-form-item>
      <el-form-item label="商品品牌" size="mini" prop="brand">
        <el-input v-model="currentdata.brand" />
      </el-form-item>
      <el-form-item label="商品价格" size="mini" class="goodPrice" prop="price">
        <!-- <el-input v-model="currentdata.price" /> -->
        <el-input-number
          v-model="currentdata.price"
          :precision="2"
          :step="1"
          style="width: 88%"
        />
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
      <el-form-item
        label="商品剂型"
        size="mini"
        class="goodType"
        prop="dosage_form"
      >
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
      <el-dialog :visible.sync="dialogVisible" size="tiny">
        <img width="100%" :src="dialogImageUrl" alt>
      </el-dialog>
      <el-form-item label="商品描述">
        <el-input v-model="currentdata.simple_desc" />
      </el-form-item>
      <el-form-item label="添加备注" style="width: 97%">
        <el-input v-model="currentdata.more" />
      </el-form-item>
    </el-form>
    <div class="editPics">
      <div class="editPic">
        <el-upload
          ref="uploadRef1"
          class="upload-demo1"
          drag
          action="fake"
          multiple
          :limit="10"
          list-type="text"
          :file-list="fileList1"
          :auto-upload="false"
          :show-file-list="true"
          :on-change="
            (file, fileList) => {
              fileList1 = fileList
            }
          "
          :on-exceed="handleExceed"
        >
          <i class="el-icon-upload" />
          <div class="el-upload__text">
            <em>此处上传</em>商品缩略图<br>(商品主图为第一张缩略图)
          </div>
          <div slot="tip" class="el-upload__tip">
            只能上传jpg/png文件, 且不超过500kb
          </div>
        </el-upload>
        <!-- <el-button
          size="mini"
          @click="submitUpload((apply = 'images'))"
        >上传商品主图</el-button> -->
      </div>
      <div class="editPic">
        <el-upload
          ref="uploadRef2"
          class="upload-demo2"
          drag
          action="fake"
          multiple
          :limit="10"
          list-type="text"
          :file-list="fileList2"
          :auto-upload="false"
          :show-file-list="true"
          :on-change="
            (file, fileList) => {
              fileList2 = fileList
            }
          "
          :on-exceed="handleExceed"
        >
          <i class="el-icon-upload" size="mini" />
          <div class="el-upload__text"><em>此处上传</em>商品介绍图片</div>
          <div slot="tip" class="el-upload__tip">
            只能上传jpg/png文件, 且不超过500kb
          </div>
        </el-upload>
        <!-- <el-button
          size="mini"
          @click="submitUpload((apply = 'introduce_images'))"
        >上传商品详情图</el-button> -->
      </div>
    </div>
    <span slot="footer" class="dialog-footer">
      <el-button @click="closeAdd">取 消</el-button>
      <el-button spec_type="primary" @click="sureBtn">确 定</el-button>
    </span>
  </div>
</template>

<script>
import { saveProduct } from '@/api/product'
import { uploadProductImageDefault } from '@/api/uploadImage'
import { Message } from 'element-ui'
import axios from 'axios'
// import axios from 'axios'

export default {
  name: 'AddWindow',
  data() {
    return {
      rules: {
        title: [{ required: true }],
        brand: [{ required: true }],
        price: [{ required: true, trigger: 'blur' }]
      },
      dialogImageUrl: '',
      dialogVisible: false,
      currentdata: {
        title: '',
        brand: '',
        milk_rank: '',
        price: '',
        dosage_form: '',
        origin_place: '',
        suit_age: '',
        spec_type: '',
        package_type: '',
        ingredient: '',
        content: '',
        blue_cap_mark: '',
        more: '',
        simple_desc: ''
      },
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
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    handleExceed() {
      Message.warning('最大上传数量不能超过10张图片')
    },
    validFileList() {
      const Reg = /image/
      if (this.fileList1.length === 0) {
        Message.error('商品缩略图不能为空')
        return false
      } else {
        for (let i = 0; i < this.fileList1.length; i += 1) {
          if (!Reg.test(this.fileList1[i].raw.type)) {
            Message.error(
              '缩略图 ' + this.fileList1[i].name + ' 不是合法图片格式'
            )
            return false
          }
        }
      }
      if (this.fileList2.length === 0) {
        Message.error('商品介绍图不能为空')
        return false
      } else {
        for (let i = 0; i < this.fileList2.length; i += 1) {
          if (!Reg.test(this.fileList2[i].raw.type)) {
            Message.error(
              '商品介绍 ' + this.fileList2[i].name + '不是合法图片格式'
            )
            return false
          }
        }
      }
      return true
    },
    sureBtn() {
      this.$refs.form.validate((isOk) => {
        if (isOk && this.validFileList()) {
          saveProduct(this.currentdata).then((res) => {
            if (res.code === 20000) {
              const id = res.data.id
              // 添加images数据
              const formData1 = new FormData()
              this.fileList1.forEach((o) => {
                formData1.append('files', o.raw)
              })
              // 添加introduce_images数据
              const formData2 = new FormData()
              this.fileList2.forEach((o) => {
                formData2.append('files', o.raw)
              })
              // 并行上传图片
              axios
                .all([
                  uploadProductImageDefault(formData1, 'product', id, 'images'),
                  uploadProductImageDefault(
                    formData2,
                    'product',
                    id,
                    'introduce_images'
                  )
                ])
                .then((res) => {
                  if (res[0].code === 20000 && res[1].code === 20000) {
                    Object.keys(this.currentdata).forEach(
                      (key) => (this.currentdata[key] = '')
                    )
                    this.$refs.uploadRef1.clearFiles()
                    this.$refs.uploadRef2.clearFiles()
                    this.$emit('addwindow', 'success')
                  } else {
                    this.$emit('addwindow', res.msg)
                  }
                })
            } else {
              this.$emit('addwindow', res.msg)
            }
          })
        }
      })
    },
    closeAdd() {
      this.$emit('closeadd')
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
    width: 48%;
    display: flex;
    .el-input,
    .el-select {
      width: 180px !important;
    }
  }
}
.editPics {
  width: 87%;
  // margin: 30px 0;
  // background-color: rgb(75, 34, 34);
  display: flex;
  justify-content: space-around;
  .editPic {
    width: 38%;
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
