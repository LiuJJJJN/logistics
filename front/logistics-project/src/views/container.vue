<template>
  <el-container>
    <el-header>
      <el-row type="flex" class="row-bg" justify="space-between">
        <el-col :span="2"><div class="grid-content bg-purple"><span style="font-size: 25px;font-weight: bolder">学生界面</span></div></el-col>
        <el-col :span="6"><div class="grid-content bg-purple">
          <el-link :underline="false" style="font-size: 15px">我的订单<i class="el-icon-view el-icon--right"></i> </el-link>
          <el-avatar :size="size" :src="circleUrl" style="padding-top: 0px;"></el-avatar>
        </div></el-col>
      </el-row>
    </el-header>
    <el-container>

      <el-aside width="200px">
        <el-menu :default-openeds="[]"> <!--哪个功能不折叠-->
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-menu"></i>学生功能</template>
            <el-menu-item-group>
              <template slot="title">寝室信息</template>
              <el-menu-item index="1-1">我的寝室</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="我要自习">
              <el-menu-item index="1-2">教室占座</el-menu-item>
              <el-menu-item index="1-3">图书馆占座</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="我要反馈">
              <el-menu-item index="1-4">我要反馈</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>导员功能</template>
            <el-menu-item-group>
              <template slot="title">我的班级</template>
              <el-menu-item index="2-1">管理班级</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="我的学生">
              <el-menu-item index="2-2">管理学生</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="学生反馈">
              <el-menu-item index="2-3">学生反馈</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="处理申请">
              <el-menu-item index="2-4">处理申请</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-menu"></i>管理员功能</template>
            <el-menu-item index="2-1">用户管理</el-menu-item>
            <el-menu-item index="2-2">寝室管理</el-menu-item>
            <el-menu-item index="2-2">教室管理</el-menu-item>
            <el-menu-item index="2-2">图书馆管理</el-menu-item>
            <el-menu-item index="2-2">权限管理</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-main>Main</el-main>
        <el-footer>Footer</el-footer>
      </el-container>

    </el-container>
  </el-container>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "container",
  data(){
    return{
      size:"large",
      circleUrl:"img"

    }
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