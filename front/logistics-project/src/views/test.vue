<template>
  <h1>test1</h1>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "test",
  data() {
    return {
      test: ''
    }
  },
  methods:{
    testMethods(){
      // this.$axios.post("http://localhost:8080/logisticsProject/test2.do")
      //     .then(resp=>{
      //       alert(resp);
      //     }, err=>{
      //       alert(err);
      // })
    }
  },
  created() {
    this.testMethods()
  }
}
</script>

<style scoped>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
