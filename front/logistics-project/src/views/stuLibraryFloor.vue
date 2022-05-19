<template>
  <div>
    <div v-if="$route.params.floor == 1" style="margin-top: 150px">
      <el-result icon="info" title="提示" subTitle="旅顺校区图书馆一楼非自习区域">
        <template slot="extra">
          <router-link to="/stu/grabLibrary/floor/2">
            <el-button type="primary" size="medium">前往二楼</el-button>
          </router-link>
        </template>
      </el-result>
    </div>
    <div v-if="$route.params.floor != 1" style="width: 1400px; height: 740px;">
      <div style="height: 35px;">
        <el-row :gutter="20" style="padding-top: 5px">
          <el-col :span="3">
            <el-tag class="el-icon-school" style="font-size: 15px; margin-left: 10px; margin-top: 2px">当前楼层:
              {{ $route.params.floor }}
            </el-tag>
          </el-col>
          <el-col :span="3">
            <el-radio-group v-model="dayRadio" size="medium">
              <el-radio-button :label=0 @click.native="handlerToday">今日</el-radio-button>
              <el-radio-button :label=1 @click.native="handlerTomorrow">明日</el-radio-button>
            </el-radio-group>
          </el-col>
          <el-col :span="3">
            <el-button type="primary" size="medium">一键预约空闲座位</el-button>
          </el-col>
          <el-col :span="3" :offset="11" style="font-size: 16px">
            <span>当前可用: {{ freeTable }} / {{ tableTotal }}</span>
          </el-col>
        </el-row>
      </div>

      <el-divider></el-divider>

      <div
          style="margin-left:70px; width:420px; height:580px; display: inline-block; font-size: 0; vertical-align: top;">
        <div style="width: 420px; height: 290px; background: #DCDFE6; vertical-align: top">
          <div>
            <el-tag effect="dark" style="margin-top: 10px; margin-left: 10px">A 区</el-tag>
          </div>

          <div @click="toGrabTable(item)"
               v-bind:class="item.orderList.length===item.seat?'used-table':item.status==0?'usable-table':'disabled-table'"
               v-for="item in tableListAreaA" :key="item">
            <span class="my-center">{{ item.orderList.length }}/{{ item.seat }}</span>
          </div>

        </div>
        <div style="width: 420px; height: 290px; background: rgb(233, 233, 235); vertical-align: top">
          <div>
            <el-tag effect="dark" style="margin-top: 10px; margin-left: 10px">B 区</el-tag>
          </div>

          <div @click="toGrabTable(item)"
               v-bind:class="item.orderList.length===item.seat?'used-table':item.status==0?'usable-table':'disabled-table'"
               v-for="item in tableListAreaB" :key="item">
            <span class="my-center">{{ item.orderList.length }}/{{ item.seat }}</span>
          </div>

        </div>
      </div>
      <div
          style=" width:420px; height:580px; display: inline-block; font-size: 0; background: rgb(244, 244, 245); vertical-align: top;">
        <div style="width: 420px; height: 200px; background: #DCDFE6; margin-top: 380px;">
          <div>
            <el-tag effect="dark" style="margin-top: 10px; margin-left: 10px">C 区</el-tag>
          </div>

          <div @click="toGrabTable(item)"
               v-bind:class="item.orderList.length===item.seat?'used-table':item.status==0?'usable-table':'disabled-table'"
               v-for="item in tableListAreaC" :key="item">
            <span class="my-center">{{ item.orderList.length }}/{{ item.seat }}</span>
          </div>

        </div>
      </div>
      <div style=" width:420px; height:580px; display: inline-block; font-size: 0;">
        <div style="width: 210px; height: 380px; display: inline-block; background: rgb(233, 233, 235);">
          <div>
            <el-tag effect="dark" style="margin-top: 10px; margin-left: 10px">D 区</el-tag>
          </div>

          <div @click="toGrabTable(item)"
               v-bind:class="item.orderList.length===item.seat?'used-table':item.status==0?'usable-table':'disabled-table'"
               v-for="item in tableListAreaD" :key="item">
            <span class="my-center">{{ item.orderList.length }}/{{ item.seat }}</span>
          </div>

        </div>
        <div
            style="width: 210px; height: 380px; display: inline-block; background: #909399; vertical-align: top; text-align: center">
          <div>
            <el-tag effect="dark" type="warning" style="margin-top: 160px; margin-left: 10px">藏书区</el-tag>
          </div>
        </div>
        <div
            style="width: 420px; height: 200px; display: inline-block; background: #909092; vertical-align: top; text-align: center;">
          <div>
            <el-tag effect="dark" type="warning" style="margin-top: 90px; margin-left: 10px">卫生间、办公区</el-tag>
          </div>
        </div>
      </div>

      <el-divider></el-divider>

      <div style="height: 30px;">
        <div class="mini-disabled-table"></div>
        停用
        <div class="mini-used-table"></div>
        被占用
        <div class="mini-usable-table"></div>
        可用
        <div class="mini-my-table"></div>
        已预约
      </div>
    </div>

    <!-- 确认订单对话框 -->
    <el-dialog title="确认图书馆座位订单" :visible.sync="checkDialogFormVisible" width="30%">
      <el-descriptions class="margin-top" :title="'当前座位: ' + tableForm.name" :column="1" border>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-office-building"></i>
            楼层
          </template>
          {{ tableForm.floor }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-location-outline"></i>
            区域
          </template>
          {{ tableForm.area }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-table-lamp"></i>
            桌子
          </template>
          {{ tableForm.name }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-collection-tag"></i>
            当前状态
          </template>
          <span v-if="tableForm.status == 1">已停用 ! ! !</span>
          <span v-if="tableForm.status == 0">未停用</span>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-office-building"></i>
            当前空余
          </template>
          <span v-if="tableForm.orderList">{{ tableForm.seat - tableForm.orderList.length }}/{{ tableForm.seat }}</span>
          <span v-if="!tableForm.orderList">4/4</span>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-warning-outline"></i>
            注意
          </template>
          当前桌位已为你锁定, 请及时确认订单
        </el-descriptions-item>
      </el-descriptions>
      <div slot="footer" class="dialog-footer">
        <el-button @click="checkDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" v-bind:disabled="tableForm.status==1 || tableForm.orderList.length==tableForm.seat?true:false" @click="grabTable()">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
const libraryName = '旅顺图书馆';
export default {
  name: "stuLibraryFloor",
  data() {
    return {
      dayRadio: 0,
      checkDialogFormVisible: false,
      formLabelWidth: 120,
      tableListAreaA: [],
      tableListAreaB: [],
      tableListAreaC: [],
      tableListAreaD: [],
      tableForm: {
        orderList: []
      },
      libraryId: '',
      floor: '',
      date: '123',
      freeTable: 0,
      tableTotal: 0
    }
  },
  methods: {
    toGrabTable(item) {
      this.checkDialogFormVisible = true;
      this.tableForm = JSON.parse(JSON.stringify(item));
    },
    grabTable() {
      this.checkDialogFormVisible = false;
    },
    handlerToday() {
      var date = new Date();
      var month = (date.getMonth() + 1) < 10 ? '0' + (date.getMonth() + 1) : (date.getMonth() + 1);
      var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
      this.date = date.getFullYear() + '-' + month + '-' + day;
      this.loadTableListInfo();
    },
    handlerTomorrow() {
      var date = new Date();
      var month = (date.getMonth() + 1) < 10 ? '0' + (date.getMonth() + 1) : (date.getMonth() + 1);
      var day = date.getDate() < 10 ? '0' + date.getDate() + 1 : date.getDate() + 1;
      this.date = date.getFullYear() + '-' + month + '-' + day;
      this.loadTableListInfo();
    },
    loadTableListInfo() {
      // 获取楼层、日期
      this.floor = this.$route.params.floor;
      // 获取A、B、C、D、区域的桌位、占用情况、遍历
      this.$axios.post("/library/getTableListByArea.do", {
        library: libraryName,
        floor: this.floor,
        date: this.date,
        area: 'A'
      }).then(resp => {
        this.tableListAreaA = resp.data.data;
      }, err => {
        console.log(err);
      });
      this.$axios.post("/library/getTableListByArea.do", {
        library: libraryName,
        floor: this.floor,
        date: this.date,
        area: 'B'
      }).then(resp => {
        this.tableListAreaB = resp.data.data;
      }, err => {
        console.log(err);
      });
      this.$axios.post("/library/getTableListByArea.do", {
        library: libraryName,
        floor: this.floor,
        date: this.date,
        area: 'C'
      }).then(resp => {
        this.tableListAreaC = resp.data.data;
      }, err => {
        console.log(err);
      });
      this.$axios.post("/library/getTableListByArea.do", {
        library: libraryName,
        floor: this.floor,
        date: this.date,
        area: 'D'
      }).then(resp => {
        this.tableListAreaD = resp.data.data;
      }, err => {
        console.log(err);
      });
      // 获取当前可用/总数
      this.$axios.post("/library/getFreeTableTotal.do", {
        library: libraryName,
        floor: this.floor,
        date: this.date
      }).then(resp => {
        this.freeTable = resp.data.data;
      }, err => {
        console.log(err);
      });
      this.$axios.post("/library/getTableTotal.do", {
        library: libraryName,
        floor: this.floor
      }).then(resp => {
        this.tableTotal = resp.data.data;
      }, err => {
        console.log(err);
      });
    }
  },
  mounted() {
    this.loadTableListInfo();
    this.handlerToday();
  },
  watch: {
    // 当路由中的楼层数发生变化时，刷新各区域桌位列表
    '$route.params.floor'() {
      this.loadTableListInfo();
    }
  }
}
</script>

<style scoped>
.used-table {
  background: #E6A23C;
}

.disabled-table {
  background: #F56C6C;
}

.usable-table {
  background: #67C23A;
}

.my-table {
  background: #409EFF;
}

div[class$="-table"] {
  vertical-align: top;
  margin: 20px;
  margin-left: 30px;
  margin-bottom: 10px;
  width: 50px;
  height: 50px;
  display: inline-block;
  border-radius: 6px;
}

.mini-used-table {
  background: #E6A23C;
}

.mini-disabled-table {
  background: #F56C6C;
}

.mini-usable-table {
  background: #67C23A;
}

.mini-my-table {
  background: #409EFF;
}

div[class^="mini"] {
  vertical-align: top;
  margin-top: 0;
  margin-left: 30px;
  margin-right: 10px;
  width: 25px;
  height: 25px;
  display: inline-block;
  border-radius: 6px;
}

div[class$="-table"]:hover {
  opacity: 0.5;
  cursor: pointer;
}

.my-center {
  line-height: 50px;
  margin-left: 12px;
  font-size: 16px;
}
</style>
