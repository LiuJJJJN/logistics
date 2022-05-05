<template>
<div class="mainDiv">
  <h2>反馈:
  </h2>
  <el-checkbox-group v-model="checkboxGroup1" style="float: left">
    <el-checkbox-button v-for="city in cities" :label="city" :key="city">{{city}}</el-checkbox-button>
  </el-checkbox-group>
  <el-button :plain="true" @click="historyButton" style="float: right">历史记录</el-button>
  <el-input
      type="text"
      class="titleCss"
      placeholder="请输入标题"
      v-model="text"
      maxlength="20"
      show-word-limit
  />
  <el-input
      type="textarea"
      :autosize="{ minRows: 10, maxRows: 90}"
      placeholder="请输入内容(最多100个字)"
      maxlength="100"
      v-model="textarea"
      style="display: inline-block; padding-top: 7px; padding-bottom: 20px; line-height: 500px;">
  </el-input>
  <div>
    <el-button type="primary" plain @click="upload" style="float: right">一键吐槽(反馈)</el-button>
  </div>

<!-- 上传反馈确认窗口 -->
  <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="45%"
      :before-close="handleClose">
    <span>这是一段信息</span>
    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
  </el-dialog>

  <!--历史记录抽屉窗口-->
  <el-drawer
      title="历史记录"
      :visible.sync="table"
      direction="rtl"
      size="40%">
    <el-button type="danger" @click="delHistory" style="margin-left: 550px" icon="el-icon-delete" circle></el-button>
    <el-table :data="gridData" @selection-change="selection">
      <el-table-column
          type="selection"
          width="50">
      </el-table-column>
      <el-table-column property="date" label="反馈时间" width="220">
        <template slot-scope="scope">
        <span>{{ scope.row.time }}</span>
      </template></el-table-column>
      <el-table-column property="name" label="反馈标题" width="190">
        <template slot-scope="scope">
          <span>{{ scope.row.titleFeedback }}</span>
        </template>
      </el-table-column>
      <el-table-column property="name" label="查看详细" width="130">
        <template slot-scope="scope">
        <el-button :plain="true" @click="detailedInf(scope.$index,scope.row)">
        查看详细</el-button></template></el-table-column>
    </el-table>
    <br>
    <!-- 分页 -->
    <div class="pageCss">
      <div class="block my-pagination">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNo"
            :page-sizes="[5, 10, 50, 100]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            background>
        </el-pagination>
      </div>
    </div>
  </el-drawer>

<!--详细信息模态窗口  -->
  <el-dialog title="反馈详细" :visible.sync="detailedInfOpen" >
    <!--  进度条  -->
        <div class="stepStyle">
          <el-steps :space="200" :active="active" finish-status="success" align-center>
            <el-step title="上传反馈"></el-step>
            <el-step title="等待答复中"></el-step>
            <el-step title="已答复"></el-step>
          </el-steps>
        </div>
    <!-- 反馈内容 -->
    <br>
    <el-card class="box-card">
      <font> 我的反馈：</font>
      <div  class="text item">
        <el-input
            type="textarea"
            :rows="4"
            placeholder="请输入内容"
            v-model="textarea2"
            :disabled="active==3?true:false">
        </el-input>
      </div>
      <el-button type="primary" plain @click="updateFeedback()" style="float: right; margin-bottom: 20px" v-show="active==3?false:true">修 改</el-button>
    </el-card>
    <br>
    <el-card class="box-card" v-show="active==3?true:false">
      <font> 导员的回复：</font>
      <div v-for="o in replyList" :key="o" class="text item">
        {{o.time+' 回复了你： ' + o.reply }}
      </div>
    </el-card>
    <br>
  </el-dialog>

</div>

</template>

<script>
const cityOptions = ['导员', '管理员'];
export default {
  name: "userFeedback",
  data(){
    return{
      text:'',
      textarea:'',
      textarea2:'',
      middleTextarea:'',
      updateId:'',
      readonly:true,//只读变量，控制详细信息的文本和按钮是否开启
      checkboxGroup1: ['导员'],
      cities: cityOptions,
      active: '',
      table: false,
      dialog: false,
      loading: false,
      detailedInfOpen: false,
      pageNo: 1, //当前页数
      pageSize: 10, //显示条数
      total: 0, //总条数
      gridData: [{
        id: '',
        time: '',
        titleFeedback: '',
        address: ''
      }],
      multipleSelection: [],
      idArray: [],
      replyList: [{
        id : '',
        reply : '',
        tutorId : '',
        feedbackId : '',
        time : '',
      }]
    }
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },

    //一键吐槽(反馈)按钮
    upload(){
      if(this.text==''){
        this.$message({
          message: '警告，标题不能为空',
          type: 'warning'
        });
      }else if(this.textarea==''){
        this.$message({
          message: '警告，反馈内容不能为空',
          type: 'warning'
        });
      }else{
        this.$confirm('是否确认吐槽(反馈)', '友情提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post("/feedback/stuAddFb.do", {
            stuId:this.$store.getters.getUser.userId,//当前用户的userId：this.$store.getters.getUser.userId
            titleFeedback:this.text,
            feedback:this.textarea
          }).then(resp=>{

            console.log(resp.data)
          }, err=>{
            console.log(err)
          })
          this.text='';
          this.textarea='';
          this.$message({
            type: 'success',
            message: '吐槽成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消吐槽'
          });
        });
      }
    },
    //历史记录按钮
    historyButton(){
      this.table=true;
      this.historyList()
    },
    //加载历史记录列表
    historyList(){
      this.$axios.post("/feedback/stuFdList.do", { //加载历史列表信息
        id:this.$store.getters.getUser.userId,//当前用户的userId：this.$store.getters.getUser.userId
        tutorId: (this.pageNo - 1) * this.pageSize,//避免后端建立vo类之间赋值给user的tutorId
        adminId: this.pageSize
      }).then(resp=>{
        this.total=resp.data.data.total;
        this.gridData = resp.data.data.list;
        console.log(resp.data)
      }, err=>{
        console.log(err)
      })
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.historyList();
    },
    //当前页数
    handleCurrentChange(val) {
      this.pageNo = val;
      this.historyList();
    },
    //删除数组
    selection(val) {
      this.multipleSelection = val;
      console.log(val);
    },
    //删除历史记录
    delHistory(){
      for(let i=0;i<this.multipleSelection.length;i++){
        this.idArray[i]=this.multipleSelection[i].id;
      }
      console.log(this.multipleSelection)
      if(this.multipleSelection.length!=0){
        this.$confirm('此操作将永久删除历史记录无法恢复, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          //删除
          this.$axios.post("/feedback/delStuFL.do",
              //array:this.dialogFormAddDicValue
              this.idArray
          ).then(resp => {
            console.log(resp.data);
            //this.tableData=resp.data.data;
            this.historyList();
          }, err => {
            console.log(err);
          });
          this.$message({
            type: 'success',
            message: '删除历史记录成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除历史记录'
          });
        });

      }else{
        this.$message({
          message: '请选择要删除的数据历史记录',
          type: 'warning'
        });
      }
    },
    //历史记录-详细信息
    detailedInf(index, row){
      this.detailedInfOpen=true;
      this.$axios.post("/feedback/getDI.do",{
        id:row.id
      }
      ).then(resp => {
        this.active=resp.data.data.feedback.status;//渲染状态(进度条)
        this.textarea2=resp.data.data.feedback.feedback;//渲染反馈内容
        this.updateId=resp.data.data.feedback.id;
        this.middleTextarea=this.textarea2;
        this.replyList=resp.data.data.replyList;
        console.log(resp.data);
        this.historyList();
      }, err => {
        console.log(err);
      });
    },
    //修改反馈
    updateFeedback(){
      if(this.middleTextarea==this.textarea2){
        this.$message({
          message: '您还未修改',
          type: 'warning'
        });
      }
      else{
        this.$confirm('此操作将修改反馈内容?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post("/user/updD.do",{
                feedback:this.textarea2,
                id:this.updateId
              }
          ).then(resp => {
            this.historyList();
            this.detailedInfOpen=false;
            console.log(resp.data);
            this.historyList();
          }, err => {
            console.log(err);
          });
          this.$message({
            type: 'success',
            message: '修改反馈成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消修改'
          });
        });
      }

    }
  }
}
</script>

<style scoped>
.titleCss{
  margin-top: 25px;
}
.stepStyle{
  margin-left: 140px;
  width: 460px;
}
.textareaInf{
  width: 500px;
}
.mainDiv{
  width: 700px;
  margin: 0 auto;
}
.pageCss{
  margin-left: 80px;
}
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 480px;
  margin-left: 130px;
}

</style>