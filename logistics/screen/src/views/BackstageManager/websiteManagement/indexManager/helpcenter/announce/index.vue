<template>
  <div class="anform">
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="date" label="日期" width="180" />
      <el-table-column prop="title" label="标题" width="180" />
      <el-table-column prop="content" label="内容" width="905" />
      <el-table-column label="操作" width="190">
        <template slot-scope="scope">
          <!-- <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)"
          >Edit</el-button> -->
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index)"
          >Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button
      icon="el-icon-plus"
      size="mini"
      type="success"
      @click="isShow = !isShow"
    >增加</el-button>
    <el-button
      type="danger"
      icon="el-icon-delete"
      size="mini"
      @click="delectAll"
    >批量删除</el-button>
    <div v-show="isShow" class="addMain">
      <div class="row">
        <div class="text">标题：</div>
        <div class="iptBox">
          <input v-model="addObj.title" type="text">
        </div>
      </div>
      <div class="row">
        <div class="text">内容：</div>
        <div class="iptBox">
          <textarea v-model="addObj.content" />
        </div>
      </div>
      <div class="row">
        <div class="iptBox">
          <button @click="onSubmit">添加内容</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [
        {
          id: '100001',
          date: '2021-08-04',
          title: '2021-2022圣诞元旦假期安排',
          content: '暂时没有什么安排，写这些就是为了让文本区看起来没那么空'
        },
        {
          id: '100002',
          date: '2021-09-14',
          title: '航空燃油附加费调整',
          content:
            '尊敬的客户：11月即将进入一年中最忙碌的冬季航季，航空仓位异常紧张，航空公司对我司的航空价格已经上调。因此，我司不得已跟进更新航空燃油附加费，上调20p/kg。此调整针对以下服务并将于1...'
        },
        {
          id: '100003',
          date: '2021-09-16',
          title: '邮寄白金1/2段奶粉服务更新',
          content:
            '尊敬的用户， 根据口岸最新规定，爱他美白金版奶粉1段、2段将全部按照完税价200元申报。这也就意味着邮寄白金奶粉1段、2段会多出80多元的税金。上述新政会陆续对各个口岸执行。'
        },
        {
          id: '100004',
          date: '2021-9-18',
          title: '调价通知',
          content:
            '尊敬的各位用户，由于目前航空仓位紧张导致了货运价格上涨，决定暂时性对以下路线价格进行上调：1, 四罐奶粉线路2, 六罐奶粉线路3， 四罐奶粉-EMS全程追踪路线4， 333/333plu...'
        },
        {
          id: '100005',
          date: '2021-10-01',
          title: '疫情影响目前部分地区包裹派送通知',
          content:
            '尊敬的各位用户：接国内邮政通知，受疫情影响以下地区的包裹可能暂缓或延缓派送，待恢复后第一时间寄出。新疆-全省内蒙- 二连浩特 阿拉善盟 满洲里市河北-廊坊市大厂县贵州省-仁怀市黑龙江省-黑河市...'
        },
        {
          id: '100006',
          date: '2021-11-26',
          title: '广州/北京庆祝活动期间包裹派送通知',
          content:
            '尊敬的各位用户：接国内邮政通知，在建党百年庆祝活动期间，即日起至7月15日，对寄往7个庆祝活动举办地（北京、上海、浙江嘉兴、江西吉安、贵州遵义、陕西延安、河北石家庄）的快递包裹，英国可正常下单，...'
        }
      ],
      isShow: false,
      addObj: {
        date: new Date().toLocaleDateString(),
        title: ' ',
        content: ''
      }
    }
  },
  methods: {
    delectAll() {},
    handleDelete(index) {
      this.tableData.splice(index, 1)
    },
    onSubmit() {
      if (this.addObj.title === '' || this.addObj.content === '') {
        alert('请按要求填写后再提交')
        return
      }
      this.tableData.push(this.addObj)
      this.addObj = ''
      this.isShow = false
    }
  }
}
</script>

<style scoped>
.el-table {
  overflow: hidden;
}
::-webkit-scrollbar {
  display: none;
}
.abform {
  position: relative;
}
.pic {
  width: 320px;
  display: flex;
  justify-content: space-between;
}
img {
  width: 150px;
  height: 150px;
}
.addMain {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  height: 30%;
  padding: 0 80px;
  font-size: 18px;
  color: #333;
}
.row {
  width: 50%;
  display: flex;
  align-items: center;
  margin-bottom: 1rem;
}
.text {
  width: 20%;
  text-align: right;
  padding-right: 10px;
}
.iptBox {
  width: 100%;
}
.iptBox button {
  width: 100%;
  height: 38px;
  background: #41b883;
  color: #fff;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  transition: 0.3s;
  align-items: center;
}
input,
textarea {
  width: 100%;
  padding: 10px;
  height: 38px;
  border: 1px solid #ccc;
  border-radius: 3px;
  transition: 0.3s;
  font-size: 16px;
  color: #666;
}
textarea:focus {
  border-color: #41b883;
  box-shadow: 0 0 10px rgba(65, 184, 131, 0.3);
}
input:focus {
  border-color: #41b883;
  box-shadow: 0 0 10px rgba(65, 184, 131, 0.3);
}
textarea {
  height: 150px;
}
.el-button {
  margin-top: 10px;
}
</style>
