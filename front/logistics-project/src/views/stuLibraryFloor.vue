<template>
<div>
  <div v-if="$route.params.floor == 1" style="margin-top: 150px">
    <el-result icon="info" title="提示" subTitle="旅顺校区图书馆一楼非自习区域">
      <template slot="extra">
        <router-link to="/stu/grabLibrary/floor/2"><el-button type="primary" size="medium">前往二楼</el-button></router-link>
      </template>
    </el-result>
  </div>
  <div v-if="$route.params.floor != 1" style="width: 1400px; height: 740px;">
    <div style="height: 35px;">
      <el-row :gutter="20" style="padding-top: 5px">
        <el-col :span="3">
          <el-tag class="el-icon-school" style="font-size: 15px; margin-left: 10px; margin-top: 2px">当前楼层: {{$route.params.floor}}</el-tag>
        </el-col>
        <el-col :span="3">
          <el-radio-group v-model="radio2" size="medium">
            <el-radio-button label="今日" ></el-radio-button>
            <el-radio-button label="明日"></el-radio-button>
          </el-radio-group>
        </el-col>
        <el-col :span="3">
            <el-button type="primary" size="medium">一键预约空闲座位</el-button>
        </el-col>
        <el-col :span="3" :offset="11" style="font-size: 16px">
            <span>当前状态: 0 / 36</span>
        </el-col>
      </el-row>
    </div>

    <el-divider></el-divider>

    <div style="margin-left:70px; width:420px; height:580px; display: inline-block; font-size: 0; vertical-align: top;">
      <div style="width: 420px; height: 290px; background: #DCDFE6; vertical-align: top">
        <div><el-tag effect="dark" style="margin-top: 10px; margin-left: 10px">A 区</el-tag></div>
        <div @click="toGrabTable(item)" class="usable-table" v-for="item in tableList" :key="item"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
      </div>
      <div style="width: 420px; height: 290px; background: rgb(233, 233, 235); vertical-align: top">
        <div><el-tag effect="dark" style="margin-top: 10px; margin-left: 10px">B 区</el-tag></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
      </div>
    </div>
    <div style=" width:420px; height:580px; display: inline-block; font-size: 0; background: rgb(244, 244, 245); vertical-align: top;">
      <div style="width: 420px; height: 200px; background: #DCDFE6; margin-top: 380px;">
        <div><el-tag effect="dark" style="margin-top: 10px; margin-left: 10px">C 区</el-tag></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
      </div>
    </div>
    <div style=" width:420px; height:580px; display: inline-block; font-size: 0;">
      <div style="width: 210px; height: 380px; display: inline-block; background: rgb(233, 233, 235);">
        <div><el-tag effect="dark" style="margin-top: 10px; margin-left: 10px">D 区</el-tag></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
        <div class="usable-table"></div>
      </div>
      <div style="width: 210px; height: 380px; display: inline-block; background: #909399; vertical-align: top; text-align: center">
        <div><el-tag effect="dark" type="warning" style="margin-top: 160px; margin-left: 10px">藏书区</el-tag></div>
      </div>
      <div style="width: 420px; height: 200px; display: inline-block; background: #909092; vertical-align: top; text-align: center;">
        <div><el-tag effect="dark" type="warning" style="margin-top: 90px; margin-left: 10px">卫生间、办公区</el-tag></div>
      </div>
    </div>

    <el-divider></el-divider>

    <div style="height: 30px;">
      <div class="mini-used-table"></div>被占用、<div class="mini-disabled-table"></div>停用、<div class="mini-usable-table"></div>可用、<div class="mini-my-table"></div>已预约
    </div>
  </div>

  <!-- 确认订单对话框 -->
  <el-dialog title="确认图书馆座位信息" :visible.sync="checkDialogFormVisible" width="30%">
    <el-form :model="tableForm" ref="editRuleForm">
      <el-form-item label="图书馆名" :label-width="formLabelWidth" prop="name">
<!--        {{ tableForm.library }}-->
        <div>{{ tableForm.name }}</div>
      </el-form-item>
      <el-form-item label="楼层" :label-width="formLabelWidth" prop="name">
        <div>{{ tableForm.floor }}</div>
      </el-form-item>
      <el-form-item label="区域" :label-width="formLabelWidth" prop="name">
        {{ tableForm.area }}
      </el-form-item>
      <el-form-item label="桌子" :label-width="formLabelWidth" prop="name">
        {{ tableForm.table }}
      </el-form-item>
      <el-form-item label="开始占用时间" :label-width="formLabelWidth" prop="openTime">
        {{ tableForm.startTime }}
      </el-form-item>
      <el-form-item label="结束占用时间" :label-width="formLabelWidth" prop="closeTime">
        {{ tableForm.endTime }}
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="checkDialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="grabTable()">确 定</el-button>
    </div>
  </el-dialog>
</div>
</template>

<script>
export default {
  name: "stuLibraryFloor",
  data() {
    return{
      radio2: '今日',
      checkDialogFormVisible: false,
      formLabelWidth: 120,
      tableList: [
        {
          name: 123,
          age: 123
        },
        {
          name: 234,
          age: 234
        }
      ],
      tableForm: {}
    }
  },
  methods:{
    toGrabTable(item){
      this.checkDialogFormVisible = true;
      this.tableForm = JSON.parse(JSON.stringify(item));
    },
    grabTable(){
      this.checkDialogFormVisible = false;
    }
  }
}
</script>

<style scoped>
.used-table{
  background: #E6A23C;
}
.disabled-table{
  background: #F56C6C;
}
.usable-table{
  background: #67C23A;
}
.my-table{
  background: #409EFF;
}
div[class$="-table"]{
  vertical-align: top;
  margin: 20px;
  margin-left: 30px;
  margin-bottom: 10px;
  font-size: 18px;
  width: 50px;
  height: 50px;
  display: inline-block;
  border-radius: 6px;
}
.mini-used-table{
  background: #E6A23C;
}
.mini-disabled-table{
  background: #F56C6C;
}
.mini-usable-table{
  background: #67C23A;
}
.mini-my-table{
  background: #409EFF;
}
div[class^="mini"]{
  vertical-align: top;
  margin-top: 0;
  margin-left: 30px;
  margin-right: 10px;
  font-size: 18px;
  width: 25px;
  height: 25px;
  display: inline-block;
  border-radius: 6px;
}
div[class$="-table"]:hover {
  opacity: 0.5;
  cursor: pointer;
}
</style>
