<template>
<div class="mainDiv">
  <h2>反馈:
    <el-checkbox-group v-model="checkboxGroup1" style="float: right">
      <el-checkbox-button v-for="city in cities" :label="city" :key="city">{{city}}</el-checkbox-button>
    </el-checkbox-group>
  </h2>
  <el-input
      type="text"
      placeholder="请输入标题"
      v-model="text"
      maxlength="20"
      show-word-limit
  />
  <el-input
      type="textarea"
      :autosize="{ minRows: 10, maxRows: 90}"
      placeholder="请输入内容"
      v-model="textarea"
      style="display: inline-block; padding-top: 20px; padding-bottom: 20px; line-height: 500px;">
  </el-input>
  <div>
    <el-button type="primary" plain>上传反馈</el-button>
  </div>
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
      cities: cityOptions
    }
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    }
  }
}
</script>

<style scoped>
.mainDiv{
  width: 700px;
  margin: 0 auto;
}

</style>