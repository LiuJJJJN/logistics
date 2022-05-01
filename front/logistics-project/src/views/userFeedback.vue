<template>
<div class="mainDiv">
  <h2>反馈:
  </h2>
  <el-checkbox-group v-model="checkboxGroup1" style="float: left">
    <el-checkbox-button v-for="city in cities" :label="city" :key="city">{{city}}</el-checkbox-button>
  </el-checkbox-group>
  <el-button :plain="true" @click="table = true" style="float: right">历史记录</el-button>
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
      size="50%">
    <el-button type="primary" plain @click="delHistory" style="float: right" class="delHistory">删除历史记录</el-button>
    <el-table :data="gridData">
      <el-table-column
          type="selection"
          width="50">
      </el-table-column>
      <el-table-column property="date" label="选择" width="150" ></el-table-column>
      <el-table-column property="date" label="反馈时间" width="150"></el-table-column>
      <el-table-column property="date" label="回复时间" width="130"></el-table-column>
      <el-table-column property="name" label="反馈标题" width="130"></el-table-column>
      <el-table-column property="name" label="查看详细" width="130"><el-button :plain="true" @click="detailedInf">
        查看详细</el-button></el-table-column>
    </el-table>
  </el-drawer>
<!--详细信息模态窗口  -->
  <el-dialog title="修改" :visible.sync="detailedInfOpen">
    <el-input
        type="textarea"
        :autosize="{ minRows: 10, maxRows: 90}"
        placeholder="请输入内容(最多100个字)"
        maxlength="100"
        v-model="textarea"
        disabled
        style="display: inline-block; padding-top: 7px; padding-bottom: 20px; line-height: 300px;">
    </el-input>
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
      checkboxGroup1: ['导员'],
      cities: cityOptions,
      table: false,
      dialog: false,
      loading: false,
      detailedInfOpen: false,
      gridData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }],
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
          this.$axios.post("/user/stuAddFb.do", {
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
    //历史记录-详细信息
    detailedInf(){
      this.detailedInfOpen=true;
    }
  }
}
</script>

<style scoped>
.titleCss{
  margin-top: 25px;
}
.mainDiv{
  width: 700px;
  margin: 0 auto;
}
.delHistory{
  margin-right: 30px;
}

</style>