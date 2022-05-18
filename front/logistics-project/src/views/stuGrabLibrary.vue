<template>
<div>
  <el-tabs type="border-card">
    <el-tab-pane label="旅顺校区图书馆">
      <el-container style="height: 780px;">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
          <el-menu
              :default-active="$route.path"
              class="el-menu-vertical-demo"
              :router="true">
            <el-menu-item index="/stu/grabLibrary/floor/5">
              <i class="el-icon-menu"></i>
              <span slot="title">五层</span>
            </el-menu-item>
            <el-menu-item index="/stu/grabLibrary/floor/4">
              <i class="el-icon-menu"></i>
              <span slot="title">四层</span>
            </el-menu-item>
            <el-menu-item index="/stu/grabLibrary/floor/3">
              <i class="el-icon-menu"></i>
              <span slot="title">三层</span>
            </el-menu-item>
            <el-menu-item index="/stu/grabLibrary/floor/2">
              <i class="el-icon-menu"></i>
              <span slot="title">二层</span>
            </el-menu-item>
            <el-menu-item index="/stu/grabLibrary/floor/1">
              <i class="el-icon-menu"></i>
              <span slot="title">一层</span>
            </el-menu-item>
          </el-menu>
        </el-aside>

        <el-container>
          <el-main>
            <router-view></router-view>
          </el-main>
        </el-container>
      </el-container>
    </el-tab-pane>
    <el-tab-pane label="本部校区图书馆">coding</el-tab-pane>
  </el-tabs>
</div>
</template>

<script>
export default {
  name: "stuGrabLibrary"
}
</script>

<style scoped>

</style>