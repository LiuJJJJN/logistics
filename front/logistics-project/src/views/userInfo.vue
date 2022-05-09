<template>
<div style="width: 1200px; margin: 0 auto">
  <h3 style="display: inline-block">用户详细信息</h3>
  <el-button type="primary" icon="el-icon-edit" style="display: inline-block; float: right; margin-right: 10px; margin-top: 10px" @click="changeEdit" circle></el-button>
  <el-popover
      placement="top"
      width="160"
      v-model="visible">
    <p>确定提交修改吗？</p>
    <div style="text-align: right; margin: 0">
      <el-button size="mini" type="text" @click="visible = false">取消</el-button>
      <el-button type="primary" size="mini" @click="toSubmitChange">确定</el-button>
    </div>
    <el-button slot="reference" style="float:right; margin-right: 10px; width: 80px; margin-top: 10px" v-show="showEditBtn" type="success" icon="el-icon-check" round></el-button>
  </el-popover>
  <el-descriptions class="margin-top" :column="2" :size="size" border>
    <el-descriptions-item span="2">
      <template slot="label">
        <i class="el-icon-picture-outline-round"></i>
        头像
      </template>
      <el-avatar :src="'http://47.113.216.124/images/'+avatarPath" style="display: inline-block; width: 100px; height: 100px" v-show="!showEditBtn && userInfo.college"></el-avatar>
      <el-upload
          class="upload-demo"
          ref="upload"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :auto-upload="false"
          :headers="{authToken: this.$store.getters.getSessionId}"
          action="http://47.113.216.124:8080/logisticsProject/user/uploadAvatar.do"
          multiple
          :limit="1"
          :on-exceed="handleExceed"
          v-show="showEditBtn && userInfo.college"
          :before-upload="beforeAvatarUpload"
          style="height: 105px;">
        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
        <div slot="tip" class="el-upload__tip" style="display: inline-block; margin-left: 10px">只能上传jpg文件，且不超过10MB</div>
      </el-upload>
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        用户名
      </template>
      <el-input
        placeholder="请输入用户名"
        v-model="submitInfo.username"
        clearable
        v-show="showEditBtn"
        style="width: 200px">
      </el-input>
      <span class="contentText" v-show="!showEditBtn">
        {{ userInfo.username }}
      </span>
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-more"></i>
        密码
      </template>
      <el-input
          placeholder="请输入新密码"
          v-model="submitInfo.password"
          v-show="showEditBtn"
          style="width: 200px"
          show-password>
      </el-input>
      <span class="contentText" v-show="!showEditBtn">
        •••••••••••
      </span>
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        真实姓名
      </template>
      <el-input
          placeholder="请输入姓名"
          v-model="submitInfo.name"
          clearable
          v-show="showEditBtn"
          style="width: 200px">
      </el-input>
      <span class="contentText" v-show="!showEditBtn">
        {{ userInfo.name }}
      </span>
    </el-descriptions-item>
    <el-descriptions-item v-if="userInfo.sex != null">
      <template slot="label">
        <i class="el-icon-male" v-if="userInfo.sex == '男'"></i>
        <i class="el-icon-female" v-if="userInfo.sex == '女'"></i>
        性别
      </template>
      <div v-show="showEditBtn">
        <el-radio v-model="submitInfo.sex" label="男">男</el-radio>
        <el-radio v-model="submitInfo.sex" label="女">女</el-radio>
      </div>
      <span class="contentText" v-show="!showEditBtn">
        {{ userInfo.sex }}
      </span>
    </el-descriptions-item>
    <el-descriptions-item v-if="userInfo.sno != null">
      <template slot="label">
        <i class="el-icon-notebook-2"></i>
        学号
      </template>
      <el-input
          placeholder="请输入学号"
          v-model="submitInfo.sno"
          clearable
          v-show="showEditBtn"
          style="width: 200px"
          type="number">
      </el-input>
      <span class="contentText" v-show="!showEditBtn">
        {{ userInfo.sno }}
      </span>
    </el-descriptions-item>
    <el-descriptions-item v-if="userInfo.college != null">
      <template slot="label">
        <i class="el-icon-reading"></i>
        学院
      </template>
      <el-select v-model="submitInfo.college" placeholder="请选择" v-show="showEditBtn">
        <div v-for="item in collegeEnum" :key="item">
          <el-option :label="item" :value="item"></el-option>
        </div>
      </el-select>
      <span class="contentText" v-show="!showEditBtn">
        {{ userInfo.college }}
      </span>
    </el-descriptions-item>
    <el-descriptions-item v-if="userInfo.stuClass != null">
      <template slot="label">
        <i class="el-icon-collection-tag"></i>
        班级
      </template>
      <el-input
          placeholder="请输入班级"
          v-model="submitInfo.stuClass"
          clearable
          v-show="showEditBtn"
          style="width: 200px">
      </el-input>
      <span class="contentText" v-show="!showEditBtn">
        {{ userInfo.stuClass }}
      </span>
    </el-descriptions-item>
    <el-descriptions-item v-if="userInfo.enterDate != null">
      <template slot="label">
        <i class="el-icon-time"></i>
        入校时间
      </template>
      <el-date-picker
          v-model="submitInfo.enterDate"
          format="yyyy-MM-dd"
          value-format="yyyy-MM-dd"
          placeholder="选择日期"
          v-show="showEditBtn"
          >
      </el-date-picker>
      <span class="contentText" v-show="!showEditBtn">
        {{ userInfo.enterDate }}
      </span>
    </el-descriptions-item>
    <el-descriptions-item v-if="userInfo.dorm != null">
      <template slot="label">
        <i class="el-icon-office-building"></i>
        寝室
      </template>
      <span class="contentText">
        {{ userInfo.dorm.building.name+" / "+userInfo.dorm.doorNo }}
      </span>
    </el-descriptions-item>
    <el-descriptions-item v-if="userInfo.tutor != null">
      <template slot="label">
        <i class="el-icon-user-solid"></i>
        导员
      </template>
      <span class="contentText">
        {{ userInfo.tutor.name }}
      </span>
    </el-descriptions-item>
  </el-descriptions>
</div>
</template>

<script>
export default {
  name: "userInfo",
  data(){
    return{
      userInfo:{},
      submitInfo:{},
      size: 'medium',
      imageUrl: '',
      showEditBtn: false,
      collegeEnum:[],
      dialogImageUrl: '',
      dialogVisible: false,
      disabled: false,
      visible: false,
      avatarPath: this.$store.getters.getUser.avatarPath,
    }
  },
  methods:{
    loadUserInfo(){
      this.$axios.get("/user/getUserInfo.do")
          .then(resp=>{
            this.userInfo = resp.data.data;
            this.submitInfo = JSON.parse(JSON.stringify(resp.data.data));
            console.log(resp);
          }, err=>{
            console.log(err);
          });
    },
    changeEdit(){
      this.showEditBtn = !this.showEditBtn;
      if (this.showEditBtn) {
        this.submitInfo = JSON.parse(JSON.stringify(this.userInfo));
        this.submitInfo.password = '';
      }
    },
    loadCollege:function (){
      this.$axios.get("/dic/getCollegeList.do").then(resp=>{
        var list = [];
        for(let i=0; i<resp.data.data.length;i++){
          list[i] = resp.data.data[i].value;
        }
        this.collegeEnum = list;
        // console.log(this.collegeEnum);
      }, err=>{
        console.log(err)
      })
    },
    toSubmitChange(){
      this.visible = false;
      this.submitAvatar();
      if (this.submitInfo.username.trim() == '' || !this.submitInfo.username){
        this.$message({
          message: '警告，用户名不能为空',
          type: 'warning'
        });
        this.submitInfo.username = '';
        return;
      }
      if (this.submitInfo.username.length < 6 || this.submitInfo.username.length > 18){
        this.$message({
          message: '警告，用户名长度应在 6 - 18 字符',
          type: 'warning'
        });
        this.submitInfo.username = '';
        return;
      }
      if (this.submitInfo.password.trim() == '' || !this.submitInfo.password){
        this.$message({
          message: '警告，新密码不能为空',
          type: 'warning'
        });
        this.submitInfo.password = '';
        return;
      }
      if (this.submitInfo.password.length < 6 || this.submitInfo.password.length > 18){
        this.$message({
          message: '警告，新密码长度应在 6 - 18 字符',
          type: 'warning'
        });
        this.submitInfo.password = '';
        return;
      }
      if (this.submitInfo.sno && this.submitInfo.sno.length > 10){
        this.$message({
          message: '警告，学号长度应在 10 字符以内',
          type: 'warning'
        });
        this.submitInfo.password = '';
        return;
      }
      var url = "/user/editStudentInfo.do";
      if (this.$store.getters.getUser.primaryRole == '导员') {
        url = "/user/editTutorInfo.do";
      }
      if (this.$store.getters.getUser.primaryRole == '管理员') {
        url = "/user/editAdminInfo.do";
      }
      this.$axios.post(url,this.submitInfo)
          .then(resp=>{
            this.$message({
              message: resp.data.message,
              type: 'success'
            });
            this.$store.commit("REMOVE_INFO");
            this.$router.replace("/login");
          }, err=>{
            console.log(err);
          });
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(){
      this.$message({
        message: '最多只能上传一个头像',
        type: 'warning'
      });
    },
    submitAvatar(){
      this.$refs.upload.submit();
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 10;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 10MB!');
      }
      return isJPG && isLt2M;
    }
  },
  created() {
    this.loadUserInfo();
    this.loadCollege();
  }
}
</script>

<style scoped>
.contentText{
  line-height: 40px;
}
>>>th{
  width: 120px;
}

</style>
