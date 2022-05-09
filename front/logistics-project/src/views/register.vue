<template>
  <div>
    <el-page-header @back="goBack" content="注册页面" class="page-header"></el-page-header>
    <el-divider></el-divider>
    <div class="reg-main">
      <el-radio-group v-model="ident">
        <router-link :to="'/register/stuRegister'" style="color: black">
          <el-radio-button label="注册学生"></el-radio-button>
        </router-link>
        <div style="padding: 10px; display: inline-block"></div>
        <router-link :to="'/register/tutorRegister'">
          <el-radio-button label="注册导员"></el-radio-button>
        </router-link>
      </el-radio-group>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "register",
  data() {
    return {
      ident: '',
    };
  },
  methods: {
    goBack: function () {
      this.$router.replace('/login');
    },
  }
}
</script>

<style scoped>
.demo-ruleForm {
  padding-top: 30px;
  width: 500px;
  margin: 0 auto;
}

.page-header {
  height: 70px;
  line-height: 70px;
  margin-left: 30px;
  margin-bottom: -20px;
}

.reg-main {
  width: 500px;
  margin: 0 auto;
}

</style>
