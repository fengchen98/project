<template>
  <div class="material">
    <div class="material-head">
      <el-select v-model="value" placeholder="请选择" style="height: 40%">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        />
      </el-select>
      <el-input v-model="input1" placeholder="请输入内容" class="fuzzyinput" />
      <el-button
        icon="el-icon-plus"
        size="mini"
        type="success"
        style="width: 8%; height: 4vh; font-size: 14px"
        @click="
          addDialogVisible = true
          tag = 1
        "
      >新增内容</el-button>
      <el-button
        type="danger"
        icon="el-icon-delete"
        size="mini"
        style="width: 8%; height: 4vh; font-size: 14px"
      >批量删除</el-button>
    </div>
    <div class="material-content">
      <!-- 气泡保护类 -->
      <div class="bubble">
        <el-table :data="flidatas" stripe style="width: 100%" class="addPic">
          <el-table-column type="selection" width="80" align="center" />
          <el-table-column
            type="index"
            width="120"
            label="序号"
            align="center"
          />
          <el-table-column
            prop="date"
            label="上传日期"
            width="120"
            align="center"
          />
          <el-table-column
            prop="name"
            label="材料名称"
            width="280"
            align="center"
          />
          <el-table-column prop="url" label="图片" align="center" width="180">
            <template slot-scope="scope">
              <img :src="scope.row.url" alt style="width: 50%; height: 80%">
            </template>
          </el-table-column>
          <el-table-column
            prop="price"
            label="价格"
            width="130"
            align="center"
          />
          <el-table-column prop="size" label="尺寸" width="220" align="center">
            <template slot-scope="scope">
              <span v-if="scope.row.type === 'nowater'">{{
                `${scope.row.length}×${scope.row.width}`
              }}</span>
              <span v-else>{{
                `${scope.row.length}×${scope.row.width}×${scope.row.height}`
              }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <div class="operatebtn">
                <el-button
                  size="mini"
                  @click="
                    showEditDialog(scope.row)
                    tag = 1
                  "
                >查看详情</el-button>
                <el-button
                  size="mini"
                  class="editbtn"
                  @click="
                    editDialogVisible = true
                    editDialog(scope.row)
                    tag = 1
                  "
                >编辑</el-button>
                <el-button
                  size="mini"
                  type="danger"
                  @click="
                    handleDelete(scope.$index, scope.row)
                    tag = 1
                  "
                >删除</el-button>
              </div>
            </template>
          </el-table-column>
          <el-table-column
            prop="mess"
            label="备注"
            width="270"
            align="center"
          />
        </el-table>
      </div>
      <!-- 防水保护封装类 -->
      <!-- <div v-show="value === 'nowater'" class="nowater">
        <el-table :data="tableData2" stripe style="width: 100%" class="addPic">
          <el-table-column type="selection" width="80" align="center" />
          <el-table-column
            type="index"
            width="80"
            label="序号"
            align="center"
          />
          <el-table-column
            prop="date"
            label="上传日期"
            width="100"
            align="center"
          />
          <el-table-column
            prop="name"
            label="材料名称"
            width="220"
            align="center"
          />
          <el-table-column prop="url" label="图片" align="center" width="150">
            <template slot-scope="scope">
              <img :src="scope.row.url" alt style="width: 100%; height: 100%">
            </template>
          </el-table-column>
          <el-table-column
            prop="price"
            label="价格"
            width="130"
            align="center"
          />
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="
                  showEditDialog(scope.row.id)
                  tag = 1
                "
              >查看详情</el-button>
              <el-button
                size="mini"
                class="editbtn"
                @click="
                  showEditDialog(scope.row.id)
                  tag = 1
                "
              >编辑</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="
                  handleDelete(scope.$index, scope.row)
                  tag = 1
                "
              >删除</el-button>
            </template>
          </el-table-column>
          <el-table-column
            prop="mess"
            label="备注"
            width="150"
            align="center"
          />
        </el-table>
      </div> -->
      <!-- 包装纸箱类 -->
      <!-- <div v-show="value === 'paperbox'" class="paperbox">
        <el-table :data="tableData3" stripe style="width: 100%" class="addPic">
          <el-table-column type="selection" width="80" align="center" />
          <el-table-column
            type="index"
            width="80"
            label="序号"
            align="center"
          />
          <el-table-column
            prop="date"
            label="上传日期"
            width="100"
            align="center"
          />
          <el-table-column
            prop="name"
            label="材料名称"
            width="200"
            align="center"
          />
          <el-table-column prop="url" label="图片" align="center" width="150">
            <template slot-scope="scope">
              <img :src="scope.row.url" alt style="width: 100%; height: 100%">
            </template>
          </el-table-column>
          <el-table-column
            prop="price"
            label="价格"
            width="100"
            align="center"
          />
          <el-table-column
            prop="size"
            label="尺寸"
            width="150"
            align="center"
          />
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button
                size="mini"
                class="editbtn"
                @click="
                  showEditDialog(scope.row.id)
                  tag = 1
                "
              >编辑</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="
                  handleDelete(scope.$index, scope.row)
                  tag = 1
                "
              >删除</el-button>
            </template>
          </el-table-column>
          <el-table-column
            prop="mess"
            label="备注"
            width="200"
            align="center"
          />
        </el-table>
      </div> -->
    </div>
    <!-- 添加内容 -->
    <el-dialog
      title="添加内容"
      :visible.sync="addDialogVisible"
      width="27%"
      @close="addDialogClosed"
    >
      <el-form
        ref="addFormRef"
        label-width="80px"
        :model="addForm"
        :rules="addFormrules"
      >
        <el-form-item label="材料名称" prop="name">
          <el-input v-model="addForm.name" />
        </el-form-item>
        <el-form-item label="上传图片" prop="img">
          <el-upload
            class="upload-demo"
            drag
            action="https://jsonplaceholder.typicode.com/posts/"
            multiple
            width="80%"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :file-list="fileList"
            list-type="picture"
            :limit="1"
            :class="{ disabled: uploadDisbled }"
          >
            <i class="el-icon-upload" />
            <div class="el-upload__text" width="80%">
              将文件拖到此处，或<em>点击上传</em>
            </div>
            <div slot="tip" class="el-upload__tip">
              只能上传jpg/png文件，且不超过500kb
            </div>
            <!-- <el-button size="small" type="primary">点击上传</el-button> -->
          </el-upload>
          <el-upload
            class="upload-demo"
            drag
            action="https://jsonplaceholder.typicode.com/posts/"
            multiple
            width="80%"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :file-list="fileList"
            list-type="picture"
          >
            <i class="el-icon-upload" />
            <div class="el-upload__text" width="80%">
              将文件拖到此处，或<em>点击上传</em>
            </div>
            <div slot="tip" class="el-upload__tip">
              只能上传jpg/png文件，且不超过500kb
            </div>
            <!-- <el-button size="small" type="primary">点击上传</el-button> -->
          </el-upload>
          <!-- <el-input v-model="addForm.name" /> -->
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="addForm.price" />
        </el-form-item>
        <el-form-item label="尺寸-长" prop="size">
          <el-input v-model="addForm.length" />
        </el-form-item>
        <el-form-item label="尺寸-宽" prop="size">
          <el-input v-model="addForm.width" />
        </el-form-item>
        <el-form-item label="尺寸-高">
          <el-input v-model="addForm.height" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="addForm.mess" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addContent">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 修改信息 -->
    <el-dialog
      title="编辑信息"
      :visible.sync="editDialogVisible"
      width="27%"
      @close="editDialogclosed"
    >
      <el-form
        ref="editFormRef"
        label-width="80px"
        :model="editForm"
        :rules="editFormrules"
      >
        <el-form-item label="材料名称" prop="name">
          <el-input v-model="editForm.name" />
        </el-form-item>
        <el-form-item label="上传图片" prop="img">
          <!-- <el-upload
            class="avatar-uploader"
            action="https://jsonplaceholder.typicode.com/posts/"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            width="200px"
          >
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon" />
          </el-upload> -->
          <el-upload
            class="upload-demo"
            drag
            action="https://jsonplaceholder.typicode.com/posts/"
            multiple
            width="80%"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :file-list="fileList"
            list-type="picture"
            :limit="1"
            :class="{ disabled: uploadDisbled }"
          >
            <i class="el-icon-upload" />
            <div class="el-upload__text" width="80%">
              将文件拖到此处，或<em>点击上传</em>
            </div>
            <div slot="tip" class="el-upload__tip">
              只能上传jpg/png文件，且不超过500kb
            </div>
            <!-- <el-button size="small" type="primary">点击上传</el-button> -->
          </el-upload>
          <el-upload
            class="upload-demo"
            drag
            action="https://jsonplaceholder.typicode.com/posts/"
            multiple
            width="80%"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :file-list="fileList"
            list-type="picture"
          >
            <i class="el-icon-upload" />
            <div class="el-upload__text" width="80%">
              将文件拖到此处，或<em>点击上传</em>
            </div>
            <div slot="tip" class="el-upload__tip">
              只能上传jpg/png文件，且不超过500kb
            </div>
            <!-- <el-button size="small" type="primary">点击上传</el-button> -->
          </el-upload>
          <!-- <el-input v-model="addForm.name" /> -->
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="editForm.price" />
        </el-form-item>
        <el-form-item label="尺寸-长">
          <el-input v-model="editForm.length" />
        </el-form-item>
        <el-form-item label="尺寸-宽">
          <el-input v-model="editForm.width" />
        </el-form-item>
        <el-form-item label="尺寸-高">
          <el-input v-model="editForm.height" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="editForm.mess" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="editDialogVisible = false"
        >确 定</el-button>
      </span>
    </el-dialog>
    <!-- 查看详情 -->
    <el-dialog title="查看详情" :visible.sync="showDialogVisible" width="20%">
      <el-form
        ref="showFormRef"
        :model="showForm"
        label-width="20%"
        disabled="true"
        class="editForm"
        label-position="cencter"
      >
        <el-form-item label="材料名称">
          <el-input v-model="showForm.name" />
        </el-form-item>
        <el-form-item label="材料图片">
          <img :src="showForm.url" style="width: 25%; height: 45%">
          <!-- <el-input v-model="showForm.url" /> -->
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="showForm.price" />
        </el-form-item>
        <el-form-item label="尺寸-长">
          <el-input v-model="showForm.length" />
        </el-form-item>
        <el-form-item label="尺寸-宽">
          <el-input v-model="showForm.width" />
        </el-form-item>
        <el-form-item label="尺寸-高">
          <el-input v-model="showForm.height" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="showForm.mess" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="showDialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="showDialogVisible = false"
        >确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData1: [
        {
          date: '2016-05-02',
          name: '可封口小气泡袋 BM1',
          price: '£ 2.99',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fss.mmzzx.com%2FupLoad%2Fproduct%2Fmonth_2011%2F202011181037255522.jpg&refer=http%3A%2F%2Fss.mmzzx.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690139&t=2005bb705a75660179a0144cf21893f2',
          length: '100mm',
          width: '165mm',
          height: '30mm',
          mess: 'BM1',
          type: 'bubble'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fss.mmzzx.com%2FupLoad%2Fproduct%2Fmonth_2011%2F202011181037255522.jpg&refer=http%3A%2F%2Fss.mmzzx.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690139&t=2005bb705a75660179a0144cf21893f2',
          name: '可封口小气泡袋 BM2',
          price: '£ 3.59',
          length: '130mm',
          width: '265mm',
          height: '30mm',
          mess: 'BM2',
          type: 'bubble'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fss.mmzzx.com%2FupLoad%2Fproduct%2Fmonth_2011%2F202011181037255522.jpg&refer=http%3A%2F%2Fss.mmzzx.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690139&t=2005bb705a75660179a0144cf21893f2',
          name: '可封口小气泡袋 BM3',
          price: '£ 3.69',
          length: '180mm',
          width: '280mm',
          height: '30mm',
          mess: 'BM3',
          type: 'bubble'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fss.mmzzx.com%2FupLoad%2Fproduct%2Fmonth_2011%2F202011181037255522.jpg&refer=http%3A%2F%2Fss.mmzzx.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690139&t=2005bb705a75660179a0144cf21893f2',
          name: '可封口小气泡袋 BM4',
          price: '£ 3.99',
          length: '230mm',
          width: '280mm',
          height: '30mm',
          mess: 'BM4',
          type: 'bubble'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fss.mmzzx.com%2FupLoad%2Fproduct%2Fmonth_2011%2F202011181037255522.jpg&refer=http%3A%2F%2Fss.mmzzx.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690139&t=2005bb705a75660179a0144cf21893f2',
          name: '可封口小气泡袋 BM5',
          price: '£ 4.29',
          length: '280mm',
          width: '355mm',
          height: '30mm',
          mess: 'BM5',
          type: 'bubble'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fss.mmzzx.com%2FupLoad%2Fproduct%2Fmonth_2011%2F202011181037255522.jpg&refer=http%3A%2F%2Fss.mmzzx.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690139&t=2005bb705a75660179a0144cf21893f2',
          name: '可封口小气泡袋 BM6',
          price: '£ 4.69',
          length: '300mm',
          width: '430mm',
          height: '30mm',
          mess: 'BM6',
          type: 'bubble'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fss.mmzzx.com%2FupLoad%2Fproduct%2Fmonth_2011%2F202011181037255522.jpg&refer=http%3A%2F%2Fss.mmzzx.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690139&t=2005bb705a75660179a0144cf21893f2',
          name: '可封口小气泡袋 BM7',
          price: '£ 5.99',
          length: '380mm',
          width: '430mm',
          height: '30mm',
          mess: 'BM7',
          type: 'bubble'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.616pic.com%2Fys_bnew_img%2F00%2F47%2F96%2FAysGzvzboq.jpg&refer=http%3A%2F%2Fpic.616pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690182&t=d84828eed99e5b5ca02cabad1f2af0dc',
          name: '48mmx66m 透明胶带',
          price: '£ 5.99',
          length: '48mm',
          width: '66m',
          mess: '已到货 欢迎订购',
          type: 'nowater'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.616pic.com%2Fys_bnew_img%2F00%2F47%2F96%2FAysGzvzboq.jpg&refer=http%3A%2F%2Fpic.616pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690182&t=d84828eed99e5b5ca02cabad1f2af0dc',
          name: '48mmx91m Fragile胶带',
          price: '£ 5.79',
          length: '48mm',
          width: '91m',
          mess: '易碎品警示胶带',
          type: 'nowater'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.616pic.com%2Fys_bnew_img%2F00%2F47%2F96%2FAysGzvzboq.jpg&refer=http%3A%2F%2Fpic.616pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690182&t=d84828eed99e5b5ca02cabad1f2af0dc',
          name: '48mmx150m 透明胶带',
          price: '£ 9.99',
          length: '48mm',
          width: '150m',
          mess: '已到货 欢迎订购',
          type: 'nowater'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.616pic.com%2Fys_bnew_img%2F00%2F47%2F96%2FAysGzvzboq.jpg&refer=http%3A%2F%2Fpic.616pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690182&t=d84828eed99e5b5ca02cabad1f2af0dc',
          name: '48mmx91m 透明胶带',
          length: '48mm',
          width: '91m',
          price: '£ 6.99',
          mess: '已到货 欢迎订购',
          type: 'nowater'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.616pic.com%2Fys_bnew_img%2F00%2F47%2F96%2FAysGzvzboq.jpg&refer=http%3A%2F%2Fpic.616pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690182&t=d84828eed99e5b5ca02cabad1f2af0dc',
          name: '25mmx66m 透明胶带',
          price: '£ 3.49',
          length: '25mm',
          width: '66m',
          mess: '已到货 欢迎订购',
          type: 'nowater'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.newwaybuy.co.nz%2Fimages%2F201609%2Fgoods_img%2F828_P_1474311833978.jpg&refer=http%3A%2F%2Fwww.newwaybuy.co.nz&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690213&t=cc57fc1334f601944615cbd8ae0795aa',
          name: '奶粉专用气柱袋（10柱）',
          price: '£ 16.69',
          length: '-',
          width: '-',
          mess: '10柱双联排气柱',
          type: 'nowater'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsem.g3img.com%2Fsite%2F50020930%2Fimage%2Fc2_20190110123943_82784.jpg%3FimageView2%2F2%2Fw%2F350&refer=http%3A%2F%2Fsem.g3img.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690242&t=c955800585ae7ad1c4db2e3aa0fe17eb',
          name: '鞋箱（单双鞋）',
          price: '£ 16.69',
          length: '20',
          width: '20',
          height: '20',
          mess: '10柱双联排气柱',
          type: 'paperbox'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsem.g3img.com%2Fsite%2F50020930%2Fimage%2Fc2_20190110123943_82784.jpg%3FimageView2%2F2%2Fw%2F350&refer=http%3A%2F%2Fsem.g3img.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690242&t=c955800585ae7ad1c4db2e3aa0fe17eb',
          name: '1公斤杂货小纸箱',
          price: '£ 16.69',
          length: '20',
          width: '20',
          height: '20',
          mess: '暂时缺货 2021年9月3号到货',
          type: 'paperbox'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsem.g3img.com%2Fsite%2F50020930%2Fimage%2Fc2_20190110123943_82784.jpg%3FimageView2%2F2%2Fw%2F350&refer=http%3A%2F%2Fsem.g3img.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690242&t=c955800585ae7ad1c4db2e3aa0fe17eb',
          name: 'SD4 - 4罐奶粉箱（加强版 带印刷）',
          price: '£ 16.69',
          length: '20',
          width: '20',
          height: '20',
          mess: '10柱双联排气柱',
          type: 'paperbox'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsem.g3img.com%2Fsite%2F50020930%2Fimage%2Fc2_20190110123943_82784.jpg%3FimageView2%2F2%2Fw%2F350&refer=http%3A%2F%2Fsem.g3img.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690242&t=c955800585ae7ad1c4db2e3aa0fe17eb',
          name: 'SD1 - 6罐奶粉箱（加强版 带印刷）',
          price: '£ 16.69',
          length: '20',
          width: '20',
          height: '20',
          mess: '10柱双联排气柱',
          type: 'paperbox'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsem.g3img.com%2Fsite%2F50020930%2Fimage%2Fc2_20190110123943_82784.jpg%3FimageView2%2F2%2Fw%2F350&refer=http%3A%2F%2Fsem.g3img.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690242&t=c955800585ae7ad1c4db2e3aa0fe17eb',
          name: 'SD10 - 2罐奶粉箱 (经济版 带印刷)',
          price: '£ 16.69',
          length: '20',
          width: '20',
          height: '20',
          mess: '目前缺货 请勿下单！！！！',
          type: 'paperbox'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsem.g3img.com%2Fsite%2F50020930%2Fimage%2Fc2_20190110123943_82784.jpg%3FimageView2%2F2%2Fw%2F350&refer=http%3A%2F%2Fsem.g3img.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690242&t=c955800585ae7ad1c4db2e3aa0fe17eb',
          name: 'SD10 - 2罐奶粉箱 (加强版 带印刷)',
          price: '£ 16.69',
          length: '20',
          width: '20',
          height: '20',
          mess: '已到货 欢迎下单',
          type: 'paperbox'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsem.g3img.com%2Fsite%2F50020930%2Fimage%2Fc2_20190110123943_82784.jpg%3FimageView2%2F2%2Fw%2F350&refer=http%3A%2F%2Fsem.g3img.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690242&t=c955800585ae7ad1c4db2e3aa0fe17eb',
          name: 'LOGO - 40*50*60加强版行李纸箱',
          price: '£ 16.69',
          length: '20',
          width: '20',
          height: '20',
          mess: '适用归国留学行李，搬家',
          type: 'paperbox'
        },
        {
          date: '2016-05-02',
          url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsem.g3img.com%2Fsite%2F50020930%2Fimage%2Fc2_20190110123943_82784.jpg%3FimageView2%2F2%2Fw%2F350&refer=http%3A%2F%2Fsem.g3img.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654690242&t=c955800585ae7ad1c4db2e3aa0fe17eb',
          name: 'SD20 - 40*50*60大纸箱',
          price: '£ 16.69',
          length: '20',
          width: '20',
          height: '20',
          mess: '缺货中 28.01.2021到货',
          type: 'paperbox'
        }
      ],
      addDialogVisible: false,
      editDialogVisible: false,
      showDialogVisible: false,
      addForm: {
        date: new Date().toLocaleDateString(),
        name: '',
        img: '',
        prize: '',
        length: '',
        width: '',
        height: '',
        mess: ''
      },
      joinForm: {
        date: new Date().toLocaleDateString(),
        name: '',
        img: ''
      },
      editForm: {
        date: '',
        name: '',
        price: '',
        url: '',
        length: '',
        width: '',
        height: '',
        mess: '',
        type: ''
      },
      showForm: {
        date: '',
        name: '',
        price: '',
        url: '',
        length: '',
        width: '',
        height: '',
        mess: '',
        type: ''
      },
      addFormrules: {
        name: [{ required: true, message: '请输入名称', trigger: 'blur' }],
        img: [{ required: true, message: '请添加图片', trigger: 'blur' }],
        price: [{ required: true, message: '请输入价格', trigger: 'blur' }],
        size: [{ required: true, message: '请输入尺寸', trigger: 'blur' }]
      },
      editFormrules: {
        name: [{ required: true, message: '请输入名称', trigger: 'blur' }],
        img: [{ required: true, message: '请输入路径', trigger: 'blur' }]
      },
      options: [
        {
          value: 'bubble',
          label: '气泡保护类'
        },
        {
          value: 'nowater',
          label: '防水保护封装类'
        },
        {
          value: 'paperbox',
          label: '包装纸箱类'
        }
      ],
      value: 'bubble',
      input1: '',
      select: '',
      needchange: 0,
      imageUrl: '',
      fileList: []
    }
  },
  computed: {
    flidatas() {
      let arr = this.tableData1.filter((p) => {
        return p.type === this.value
      })
      if (this.input1) {
        console.log(this.input1)
        arr = this.tableData1.filter((p) => {
          return p.name.indexOf(this.input1) !== -1 && p.type === this.value
        })
      }
      return arr
    }
  },
  methods: {
    async handleDelete(index) {
      const res = await this.$confirm(
        '此操作将永久删除该内容, 是否继续?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch((err) => err)
      if (res !== 'confirm') return this.$message.info('已取消删除')
      console.log(this.tag)
      if (this.tag === 1) this.tableData1.splice(index, 1)
      this.tableData2.splice(index, 1)
    },
    showEditDialog(row) {
      this.showDialogVisible = true
      this.showForm = row
      console.log(this.showForm)
    },
    editDialog(row) {
      this.editForm = row
    },
    addDialogClosed() {
      this.$refs.addFormRef.resetFields()
    },
    editDialogclosed() {
      this.$refs.editFormRef.resetFields()
    },
    addContent() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return
        if (this.tag === 1) this.tableData1.push(this.addForm)
        this.tableData2.push(this.joinForm)
        this.addDialogVisible = false
      })
    },
    // fuzzyserch() {
    //   this.needchange = 1
    //   console.log(this.input1)
    //   const arr = arr.filter((p) => {
    //     return p.name.indexOf(this.input1) !== -1
    //   })
    // },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePreview(file) {
      console.log(file)
    }
  }
}
</script>
<style lang="scss" scoped>
.material {
  padding: 20px;
}
.material-head {
  width: 100%;
  height: 10vh;
  display: flex;
}
.el-select {
  margin-bottom: 40px;
}
.addPic {
  margin-bottom: 15px;
}
.el-tabs__item {
  width: 200px;
}
.avatar-uploader .el-upload {
  width: 200px;
  height: 200px;
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
.editbtn {
  background-color: #34c447;
  color: white;
}

.fuzzyinput .input-with-select .el-input-group__prepend {
  background-color: #fff;
}
.el-form {
  color: black;
}
.operatebtn {
  display: flex;
}
.fuzzyinput {
  width: 30%;
  height: 40%;
  margin: 0 20px;
}
.el-input {
  height: 4vh;
}
.el-form {
  margin: 0 auto;
}
.el-dialog__body {
  padding: 30px 30px;
}
.editForm .el-form-item__label {
  text-align: center;
  padding: 0;
}
</style>
