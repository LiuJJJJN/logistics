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
      <el-autocomplete
          v-model="submitForm.college"
          :fetch-suggestions="querySearchAsync"
          placeholder="请输入内容"
          @select="handleSelect"
      ></el-autocomplete>
    </el-form-item>
    <el-form-item label="所属班级" prop="stuClass">
      <el-input v-model="submitForm.stuClass"></el-input>
    </el-form-item>
    <el-form-item label="学号" prop="sno">
      <el-input v-model="submitForm.sno" ></el-input>
    </el-form-item>
    <el-form-item label="学制" prop="schoolSys">
      <el-input-number v-model="submitForm.schoolSys" controls-position="right" :min="4"
                       :max="8"></el-input-number>
    </el-form-item>
    <el-form-item label="入学日期" prop="enterDate">
      <div class="block">
        <el-date-picker
            v-model="submitForm.enterDate"
            align="right"
            type="date"
            placeholder="选择日期"
            :picker-options="ruleForm.pickerOptions">
        </el-date-picker>
      </div>
    </el-form-item>
    <el-form class="sub-res-button">
      <br>
      <el-button type="primary" @click="toRegister('ruleForm')">立即创建</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form>
  </el-form>
</template>

<script>
import ElementUI from "element-ui";

export default {
  name: "stuRegister",
  data (){
    return{
      restaurants: [],
      submitForm: {
        username: '',
        password: '',
        checkPassword: '',
        name: '',
        sex: '男',
        college: '',
        stuClass: '',
        sno: '',
        schoolSys: '',
        enterDate: '',
      },
      ruleForm: {
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
        }
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
          {required: true, message: '请输入学院', trigger: 'blur'},
          {type:'enum', enum: ['23', '34'], message: '学院必须为学院列表中的值'}
        ],
        stuClass: [
          {required: true, message: '请输入班级', trigger: 'blur'}
        ],
        sno: [
          {required: true, message: '请输入学号', trigger: 'blur'},
          {pattern: /^-?\d*$/, message: '请输入全数字学号', trigger: 'blur'}
        ],
        schoolSys: [
          {required: true, message: '请输入学制', trigger: 'blur'}
        ],
        enterDate: [
          {required: true, message: '请输入入学日期', trigger: 'blur'}
        ]
      }
    }
  },
  methods:{
    toRegister(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.submitForm.password !== this.submitForm.checkPassword) {
            ElementUI.Message.error("两次输入的密码不一致!", {duration:3*1000});
            this.submitForm.password = '';
            this.submitForm.checkPassword = '';
            return false;
          }
          this.$axios.post("/user/registerStudent.do",this.submitForm)
              .then((resp)=>{
                console.log(resp);
              }, (err)=>{
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
    loadAll() {
      this.$axios.get("/user/getCollegeList.do").then(resp=>{
        console.log(resp.data.data);
        return [
          {'value':'23623123'}
        ]
      }, err=>{
        console.log(err)
      })
    },
    querySearchAsync(queryString, cb) {
      var restaurants = this.restaurants;
      var results = queryString ? restaurants.filter(this.createStateFilter(queryString)) : restaurants;

      clearTimeout(this.timeout);
      this.timeout = setTimeout(() => {
        cb(results);
      }, 300);
    },
    createStateFilter(queryString) {
      return (college) => {
        return (college.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    handleSelect(item) {
      console.log(item);
    }
  },
  mounted() {
    this.restaurants = this.loadAll();
  }
}
</script>

<style scoped>
.sub-res-button{
  width: 200px;
  margin: 0 auto;
}
</style>
