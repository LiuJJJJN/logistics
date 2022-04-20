<template>
  <div>
    <el-carousel :interval="4000" type="card" height="300px">
      <el-carousel-item v-for="item in 6" :key="item">
        <h3 class="medium">{{ item }}</h3>
      </el-carousel-item>
    </el-carousel>
    <el-col :span="5" class="el-col">
      <el-card shadow="hover" class="el-card">
        我的寝室
      </el-card>
    </el-col>
    <el-col :span="5" class="el-col">
      <el-card shadow="hover" class="el-card">
        教室占座
      </el-card>
    </el-col>
    <el-col :span="5" class="el-col">
      <el-card shadow="hover" class="el-card">
        图书馆占座
      </el-card>
    </el-col>
    <el-col :span="5" class="el-col">
      <el-card shadow="hover" class="el-card">
        我要反馈
      </el-card>
    </el-col>
  </div>

</template>

<script>
export default {
  name: "userIndex",
  data(){
    return{
      item:[]
    }
  },
  created() {
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

.el-col{
  margin: 1.7%;
}

.el-card{
  background: rgba(64, 158, 255, 0.5);
  border-radius: 10px
}

.el-card:hover{
  background: rgba(64, 158, 255, 0.8);
  border-radius: 10px
}
</style>
