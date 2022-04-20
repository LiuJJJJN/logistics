<template>
<div class="mainDiv">
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
      style="display: inline-block; line-height: 500px;">
  </el-input>
  <div>
    <el-button type="primary" plain>上传反馈</el-button>
    <el-button type="info" plain>取消</el-button>
  </div>
</div>
</template>

<script>
export default {
  name: "userFeedback",
  data(){
    return{
      text:'',
      textarea:'',
      fileList: [{name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}, {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}]
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
  width: 900px;
  margin: 0 auto;
}
</style>