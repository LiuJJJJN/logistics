<template>
  <div>
    <div style="width: 500px; margin: 0 auto; margin-top: 200px; padding-left: 20px">
      <h2 style="width: 217px; margin: 0 auto; margin-bottom: 30px">后勤管理系统 - 登录</h2>
    </div>
    <el-form ref="form" :model="form" :rules="rules" label-width="80px" class="login-ruleForm">
      <el-form-item label="角色" prop="ident">
        <el-radio-group v-model="form.ident" size="small">
          <el-radio-button label="学生"></el-radio-button>
          <el-radio-button label="导员"></el-radio-button>
          <el-radio-button label="管理员"></el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="账号" prop="username">
        <el-input v-model="form.username" placeholder="请输入账号" clearable></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input @keyup.enter.native="onSubmit('form')" v-model="form.password" placeholder="请输入密码"
                  show-password></el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="code">
        <el-row :span="24">
          <el-col :span="13">
            <el-input @keyup.enter.native="onSubmit('form')" v-model="form.code" auto-complete="off"
                      placeholder="请输入验证码" size=""></el-input>
          </el-col>
          <el-col :span="11">
            <div class="login-code" width="100%" @click="refreshCode">
              <!--验证码组件-->
              <CAPTCHA :identifyCode="identifyCode" style="line-height: 10px"></CAPTCHA>
            </div>
          </el-col>
        </el-row>
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
import CAPTCHA from "@/views/CAPTCHA";

export default {
  // eslint-disable-next-line vue/no-unused-components
  components: {CAPTCHA},	//写在export default{}中与data同级表示使用组件
  // eslint-disable-next-line vue/multi-word-component-names
  name: "login",
  data() {
    return {
      form: {
        username: '',
        password: '',
        code: '',
        ident: '学生',
        rememberMe: false
      },
      formLogin: {
        username: "",
        password: "",
        code: ""
      },
      identifyCodes: '234678abcefghknpquvwxyz',//随机串内容
      identifyCode: '',
      rules: {
        ident: [
          {required: true},
        ],
        username: [
          {required: true, message: "账号必填", trigger: 'blur'},
          {min: 6, max: 18, message: "账号长度不能小于 6 位或超过 18 位"}
        ],
        password: [
          {required: true, message: "密码必填", trigger: 'blur'},
          {min: 6, max: 18, message: "密码长度不能小于 6 位或超过 18 位"}
        ],
        code: [{required: true, message: "请输入验证码", trigger: "blur"}]
      }
    }
  },
  methods: {
    onSubmit(formName) {
      if (this.form.code && this.form.code.toLowerCase() !== this.identifyCode.toLowerCase()) {
        this.$message.error('请填写正确验证码');
        this.form.code = '';
        this.refreshCode();
        return
      }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.form.username = this.form.username.trim();
          this.$axios.post("/user/login.do", this.form)
              .then(resp => {
                // console.log(resp.data);
                this.$store.commit("SET_TOKEN", resp.data.data.token); //向全局存储中中存值
                this.$store.commit("SET_USERINFO", resp.data.data.user); //向全局存储中中存值
                this.$store.commit("SET_REMEMBER_ME", resp.data.data.rememberMe); //向全局存储中中存值
                this.$store.commit("SET_TIME_STAMP", resp.data.data.timestamp); //向全局存储中中存值
                this.$store.commit("SET_SESSION_ID", resp.data.data.sessionId); //向全局存储中中存值
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
      this.$router.replace("/register/stuRegister");
    },
    checkLogin: function () {
      if (this.$store.getters.getTimeStamp > 0) { //页面打开时判断是否需要登录
        this.$router.replace("/index");
        this.$notify({
          title: '成功',
          message: '通过免登录验证, 进入主页',
          type: 'success'
        });
      }
    },
    refreshCode() {
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[this.randomNum(0, this.identifyCodes.length)]
      }
    },
    randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min)
    }
  },
  created() {
    this.checkLogin();
    this.identifyCode = ''
    this.makeCode(this.identifyCodes, 4)

    // this.$notify({
    //   title: '提示',
    //   message: '项目目前实现 登录注册、账户角色管理、个人信息修改、数据字典 等功能.<br/><br/>' +
    //       '管理员账号:admin123, 密码:123123<br/><br/>' +
    //       '项目 gitee 地址:<br/> <a href="https://gitee.com/LiuJaNing/logistics/tree/dev/">https://gitee.com/...</a>',
    //   duration: 0,
    //   dangerouslyUseHTMLString: true,
    //   offset: 200
    // });
  }

}
</script>

<style scoped>

.login-ruleForm {
  padding-top: 20px;
  width: 300px;
  margin: 0 auto;
  text-align: center;
}

.rememberMe {
  margin-left: -120px;
}

</style>
