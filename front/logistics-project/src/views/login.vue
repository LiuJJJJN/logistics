<template>
  <div>
    <el-form ref="form" :model="form" :rules="rules" label-width="80px" class="login-ruleForm">
      <h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Login</h1>
      <el-form-item label="角色" prop="ident">
        <el-radio-group v-model="form.ident" size="small">
          <el-radio-button label="学生"></el-radio-button>
          <el-radio-button label="导员"></el-radio-button>
          <el-radio-button label="管理员"></el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="账号" prop="username">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="form.password" show-password></el-input>
      </el-form-item>
      <el-form-item label="" prop="rememberMe" class="rememberMe">
        <el-checkbox v-model="form.rememberMe">七天免登录</el-checkbox>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')">登录</el-button>
        <el-tooltip class="item" effect="dark" content="还没有账号?立马去注册!" placement="bottom">
          <el-button @click="toRegister">注册</el-button>
        </el-tooltip>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "login",
  data() {
    return {
      form: {
        username: '',
        password: '',
        ident: '学生',
        rememberMe: false
      },
      rules: {
        username: [
          {required: true, message: "账号必填", trigger: 'blur'},
          {min: 3, max: 18, message: "账号长度不能小于 3 位或超过 18 位"}
        ],
        password: [
          {required: true, message: "密码必填", trigger: 'blur'},
          {min: 3, max: 18, message: "密码长度不能小于 3 位或超过 18 位"}
        ]
      }
    }
  },
  methods: {
    onSubmit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post("/user/login.do", this.form)
              .then(resp => {
                // alert(resp.data.message);
                console.log(resp.data);
                this.$store.commit("SET_TOKEN", resp.data.data.token); //向全局存储中中存值
                this.$store.commit("SET_USERINFO", resp.data.data.user); //向全局存储中中存值
                this.$store.commit("SET_REMEMBER_ME", resp.data.data.rememberMe); //向全局存储中中存值
                this.$store.commit("SET_TIME_STAMP", resp.data.data.timestamp); //向全局存储中中存值
                this.$message({
                  message: resp.data.message,
                  type: 'success'
                });
                this.$router.replace("/index"); //使用路由跳转
                return true;
              }, err => {
                console.log(err);
                return false;
              });
        } else {
          console.log('error login submit!!');
          return false;
        }
      });
    },
    toRegister: function () {
      this.$router.replace("/register");
    }
  },
  created() {
    if (this.$store.getters.getTimeStamp > 0) { //页面打开时判断是否需要登录
      this.$router.replace("/index");
      this.$notify({
        title: '成功',
        message: '通过免登录验证, 进入主页',
        type: 'success'
      });
    }
  }
}
</script>

<style scoped>

.login-ruleForm {
  padding-top: 200px;
  width: 300px;
  margin: 0 auto;
  text-align: center;
}

.rememberMe {
  margin-left: -120px;
}

</style>
