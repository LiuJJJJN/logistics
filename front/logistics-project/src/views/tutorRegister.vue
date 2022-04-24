<template>
  <el-form :model="submitForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="用户名" prop="username">
      <el-input v-model="submitForm.username"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="submitForm.password" show-password></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="checkPassword">
      <el-input v-model="submitForm.checkPassword" show-password></el-input>
    </el-form-item>
    <el-form-item label="姓名" prop="name">
      <el-input v-model="submitForm.name"></el-input>
    </el-form-item>
    <el-form-item label="性别" prop="sex">
      <el-radio-group v-model="submitForm.sex">
        <el-radio label="男"></el-radio>
        <el-radio label="女"></el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="所属学院" prop="college">
      <el-select v-model="submitForm.college" placeholder="请选择所属学院" >
        <div v-for="item in collegeEnum" :key="item">
          <el-option :label="item" :value="item"></el-option>
        </div>
      </el-select>
    </el-form-item>
    <br>
    <el-form class="sub-res-button">
      <el-button type="primary" @click="toRegister('ruleForm')">立即创建</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form>
  </el-form>
</template>

<script>
import ElementUI from "element-ui";

export default {
  name: "tutorRegister",
  data (){
    return{
      collegeEnum: [],
      submitForm:{
        id:'',
        username:'',
        password:'',
        salt:'',
        name:'',
        sex:'男',
        college:'',
        dormId:'',
        remark:'',
        avatarPath:''
      },
      ruleForm: {
        restaurants: [],
        timeout: null,
        pickerOptions: {
          disabledDate(time) {
            return time.getTime() > Date.now();
          },
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, {
            text: '昨天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }, {
            text: '一周前',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', date);
            }
          }]
        },
        enterDate: '',
        idcard: ''
      },
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 6, max: 18, message: '长度在 6 到 18 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 18, message: '长度在 6 到 18 个字符', trigger: 'blur'}
        ],
        sex: [
          {required: true, message: '请输入性别', trigger: 'blur'}
        ],
        checkPassword: [
          {required: true, message: '请输入密码确认', trigger: 'blur'},
          {min: 6, max: 18, message: '长度在 6 到 18 个字符', trigger: 'blur'}
        ],
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
          {min: 2, max: 30, message: '长度在 2 到 30 个字符', trigger: 'blur'}
        ],
        college: [
          {required: true, message: '请输入学院', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    toRegister(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.submitForm.password !== this.submitForm.checkPassword) {
            ElementUI.Message.error("两次输入的密码不一致!", {duration: 3 * 1000});
            this.submitForm.password = '';
            this.submitForm.checkPassword = '';
            return false;
          }
          this.$axios.post("/user/registerTutor.do", this.submitForm)
              .then((resp) => {
                this.$router.replace("/login");
                this.$message({
                  message: resp.data.message,
                  type: 'success'
                });
              }, (err) => {
                console.log(err);
              })
        } else {
          console.log('error stuRegister submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    loadCollege:function (){
      this.$axios.get("/user/getCollegeList.do").then(resp=>{
        var list = [];
        for(let i=0; i<resp.data.data.length;i++){
          list[i] = resp.data.data[i].value;
        }
        this.collegeEnum = list;
        // console.log(this.collegeEnum);
      }, err=>{
        console.log(err)
      })
    }
  },
  created() {
    this.loadCollege();
  }
}
</script>

<style scoped>
.sub-res-button{
  width: 200px;
  margin: 0 auto;
}
</style>