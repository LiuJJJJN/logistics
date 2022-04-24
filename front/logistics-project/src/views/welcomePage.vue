<template>
  <div>
    <el-carousel :interval="4000" type="card" height="300px">
      <el-carousel-item v-for="item in 6" :key="item">
        <h3 class="medium">{{ item }}</h3>
      </el-carousel-item>
    </el-carousel>
    <el-col :span="5" class="el-col">
      <el-card shadow="hover" class="el-card" v-for="item in itemList" :key="item" style="cursor: pointer" @click.native="toPath(item.path)">
        {{ item.name }}
      </el-card>
    </el-col>
  </div>
</template>

<script>
export default {
  name: "welcomePage",
  data(){
    return{
      item:[],
      itemList:[]
    }
  },
  methods:{
    getMenuList: function () {
      //获取功能列表
      this.$axios.post("/permission/getPermissionList.do", {
        userId: this.$store.getters.getUser.userId
      })
          .then(resp => {
            //加载功能按钮列表
            var itemList = [];
            for (let i = 0; i < resp.data.data.length; i++) {
              for (let j = 0; j <resp.data.data[i].fatherMenu.length; j++) {
                for (let k = 0; k < resp.data.data[i].fatherMenu[j].subMenu.length; k++) {
                  itemList.push(resp.data.data[i].fatherMenu[j].subMenu[k]);
                }
              }
            }
            this.itemList = itemList;
            // console.log(this.itemList);
            this.$router.replace("/welcomePage");
          }, err => {
            console.log(err)
          })
    },
    toPath:function (path){
      this.$router.replace(path);
    }
  },
  created() {
    this.getMenuList();
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
  margin-top: 30px;
  width: 99%;
}

.el-card{
  margin-right: 1%;
  margin-left: 2%;
  margin-bottom: 2%;
  background: rgba(64, 158, 255, 0.2);
  border-radius: 10px;
  height: 150px;
  width: 21.6%;
  text-align: center;
  line-height: 100px;
  display: inline-block;
}

.el-card:hover{
  background: rgba(64, 158, 255, 15);
  border-radius: 10px
}
</style>
