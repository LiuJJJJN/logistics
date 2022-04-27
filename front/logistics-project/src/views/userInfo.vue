<template>
<div style="width: 1200px; margin: 0 auto">
  <h3 style="display: inline-block">用户详细信息</h3>
  <el-button type="primary" icon="el-icon-edit" style="display: inline-block; float: right; margin-right: 10px; margin-top: 10px" @click="changeEdit" circle></el-button>
  <el-button style="float:right; margin-right: 10px; width: 80px; margin-top: 10px" v-show="showEditBtn" type="success" icon="el-icon-check" round></el-button>
  <el-descriptions class="margin-top" :column="2" :size="size" border>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-picture-outline-round"></i>
        头像
      </template>
      <el-upload
          action="#"
          list-type="picture-card"
          :auto-upload="false"
          v-show="showEditBtn"
          limit="1"
          :on-exceed="onExceedOne">
        <i slot="default" class="el-icon-plus"></i>
        <div slot="file" slot-scope="{file}">
          <img
              class="el-upload-list__item-thumbnail"
              :src="file.url" alt=""
          >
          <span class="el-upload-list__item-actions">
        <span
            class="el-upload-list__item-preview"
            @click="handlePictureCardPreview(file)"
        >
          <i class="el-icon-zoom-in"></i>
        </span>
        <span
            v-if="!disabled"
            class="el-upload-list__item-delete"
            @click="handleRemove(file)"
        >
          <i class="el-icon-delete"></i>
        </span>
      </span>
        </div>
      </el-upload>
      <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImageUrl" alt="">
      </el-dialog>
      <el-avatar size="large" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" style="display: inline-block" v-show="!showEditBtn"></el-avatar>
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
          style="width: 200px">
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
          placeholder="请输入学号"
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
          type="date"
          placeholder="选择日期"
          v-show="showEditBtn">
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
        {{ userInfo.dorm.doorNo }}
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
  {{ userInfo }}
  <br>
  <br>
  <br>
  {{submitInfo}}
</div>
</template>

<script>
export default {
  name: "userInfo",
  data(){
    return{
      userInfo:'',
      submitInfo:'',
      size: 'medium',
      imageUrl: '',
      showEditBtn: true,
      collegeEnum:[],
      dialogImageUrl: '',
      dialogVisible: false,
      disabled: false
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
      this.userInfo = this.$store.getters.getUser;
    },
    changeEdit(){
      this.showEditBtn = !this.showEditBtn;
      if (this.showEditBtn) {
        this.submitInfo = JSON.parse(JSON.stringify(this.userInfo));
      }
    },
    loadCollege:function (){
      this.$axios.get("/getCollegeList.do").then(resp=>{
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
    handleRemove(file) {
      console.log(file);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    onExceedOne(){
      this.$message({
        message: '警告，最多上传一个头像',
        type: 'warning'
      });

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
