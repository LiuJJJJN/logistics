<template>
  <el-container class="index">
    <el-header>
      <el-row type="flex" class="row-bg" justify="space-between">
        <el-col :span="4" @click.native="toIndex" style="cursor: pointer">
          <div class="grid-content bg-purple">
            <span style="font-size: 25px;font-weight: bolder">校园智慧后勤管理系统</span>
          </div>
        </el-col>
        <el-col :span="2">
          <div class="grid-content bg-purple">
            <el-dropdown style="top: 10px; left: 20px;">
              <span class="el-dropdown-link">
                <el-avatar :size="size" :src="'http://47.111.84.87/images/'+avatarPath" style="top: 10px; left: 20px; float: left;">暂无</el-avatar>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item disabled>{{ role }} : {{ name }}</el-dropdown-item>
                <router-link :to="'/userInfo'"><el-dropdown-item>个人信息</el-dropdown-item></router-link>
                <el-dropdown-item @click.native="exitLogin">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </el-col>
      </el-row>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu :default-openeds="items" :default-active="$route.path"> <!--哪个功能不折叠-->
          <div v-for="(item, index) in itemList" :key="index">
            <el-submenu :index="index">
              <template slot="title"><i class="el-icon-menu"></i>{{ item.role }}</template>
              <div v-for="(x, y) in item.fatherMenu" :key="x">
                <el-menu-item-group :title="item.fatherMenu[y].name">
                  <div v-for="a in x.subMenu" :key="a">
                    <router-link :to="a.path">
                      <el-menu-item :index="a.path" :class="$route.path==a.path?'is-active':''">{{ a.name }}</el-menu-item>
                    </router-link>
                  </div>
                </el-menu-item-group>
              </div>
            </el-submenu>
          </div>
        </el-menu>
      </el-aside>

      <el-container>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "container",
  data() {
    return {
      role: this.$store.getters.getUser.primaryRole,
      name: this.$store.getters.getUser.name,
      size: "large",
      avatarPath: this.$store.getters.getUser.avatarPath,
      items: [],
      itemList: []
    }
  },
  methods: {
    exitLogin: function () {
      this.$store.commit("REMOVE_INFO");
      this.$notify.info({
        title: '注意',
        message: '您已退出登录'
      });
      this.$router.replace("/login");
    },
    getMenuList: function () {
      //获取功能列表
      this.$axios.post("/permission/getPermissionList.do", {
        userId: this.$store.getters.getUser.userId
      })
          .then(resp => {
            //渲染功能列表
            this.itemList = resp.data.data;
            console.log(this.itemList);
          }, err => {
            console.log(err)
          })
    },
    toIndex:function (){
      this.$router.replace("/index");
    }
  },
  created() {
    this.getMenuList();

    //展开所有功能列表
    for (let i = 0; i < 3; i++) {
      this.items.push(i);
    }

  }
}
</script>

<style scoped>
.index {
  padding: 0px;
  margin: 0px;
}

.el-header {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
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

.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}

.el-icon-arrow-down {
  font-size: 12px;
}

.demonstration {
  display: block;
  color: #8492a6;
  font-size: 14px;
  margin-bottom: 20px;
}

a {
  text-decoration: none;
}

.grid-content:hover{
  color: #606266;
}

</style>

<!--

this.$axios.post("")
    .then(resp=>{
      console.log(resp);
    }, err=>{
      console.log(err);
    });

this.$message({
  message: resp.data.message,
  type: 'success'
});


-->