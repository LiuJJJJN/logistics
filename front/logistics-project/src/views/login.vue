<template>
  <div>
    <el-form ref="form" :model="form" :rules="rules" label-width="80px" class="login-ruleForm">
      <el-form-item label="账号" prop="username">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="form.password" type="password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">登录</el-button>
        <el-tooltip class="item" effect="dark" content="取消是个无用的按钮" placement="bottom-start">
          <el-button>取消</el-button>
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
        password: ''
      },
      rules:{
        username:[
          {required: true, message: "账号必填", trigger: 'blur'},
          {min: 3, max: 30, message: "账号长度不能小于6位或超过30位"}
        ],
        password:[
          {required: true, message: "密码必填", trigger: 'blur'},
          {min: 3, max: 30, message: "密码长度不能小于6位或超过30位"}
        ]
      }
    }
  },
  methods: {
    onSubmit() {
      this.$axios.post("/login.do", this.form)
          .then(resp => {
            // alert(resp.data.message);
            // console.log(resp.data);
            this.$store.commit("SET_TOKEN", resp.data.data.token); //向全局存储中中存值
            this.$store.commit("SET_USERINFO", resp.data.data.user); //向全局存储中中存值
            this.$message({
              message: resp.data.message,
              type: 'success'
            });
            this.$router.replace("/index"); //使用路由跳转
          }, err => {
            // alert(err);
            console.log(err)
          })
    }
  }
}
</script>

<style scoped>
.login-ruleForm{
  padding-top: 200px;
  width: 300px;
  margin: 0 auto;
  text-align: center;
}

</style>
