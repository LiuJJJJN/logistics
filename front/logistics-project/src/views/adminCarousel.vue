<template>
<div>
  <h3>走马灯图片预览</h3>
  <el-carousel :interval="4000" type="card" height="300px">
    <el-carousel-item v-for="item in fileList" :key="item">
      <img :src="item.url" alt="" />
    </el-carousel-item>
  </el-carousel>
  <h3 style="display: inline-block;">当前走马灯图片</h3>
  <div class="el-icon-info" style="display: inline-block; margin-left: 20px"> 此处不做格式限制, 但推荐上传png/jpg文件, 且不超过500KB</div>
  <el-upload
      action="http://47.111.84.87:8080/logisticsProject/addCarousel.do"
      list-type="picture-card"
      :on-preview="handlePictureCardPreview"
      :on-remove="handleRemove"
      :on-success="handleSuccess"
      :file-list="fileList"
      limit="10"
      :on-exceed="handleExceed">
    <i class="el-icon-plus"></i>
  </el-upload>
  <el-dialog :visible.sync="dialogVisible">
    <img width="100%" :src="dialogImageUrl" alt="">
  </el-dialog>
</div>
</template>

<script>
export default {
  name: "adminCarousel",
  data() {
    return {
      dialogImageUrl: '',
      dialogVisible: false,
      fileList: []
    };
  },
  methods: {
    handleRemove(file, fileList) {
      this.fileList = fileList;
      this.$axios.get("/removeCarousel.do", {
        params:{
          name:file.name
        }
      }).then(resp=>{
        this.$message({
          message: resp.data.message,
          type: 'success'
        });
      }, err=>{
        console.log(err);
      })
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleSuccess(file, fileList){
      this.fileList.push(fileList);
    },
    loadFileList(){
      this.$axios.post("/getFileList.do")
          .then(resp=>{
            this.fileList = resp.data.data;
            this.$message({
              message: resp.data.message,
              type: 'success'
            });
          }, err=>{
            console.log(err);
          })
    },
    handleExceed() {
      this.$message({
        message: "最多上传10张走马灯图片",
        type: 'warning'
      });
    }
  },
  created() {
    this.loadFileList();
  }
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>