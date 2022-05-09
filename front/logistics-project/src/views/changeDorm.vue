<template>
<div>
  <el-steps :active="1" finish-status="success" simple style="margin-top: 20px">
    <el-step title="申请换寝" ></el-step>
    <el-step title="导员审批" ></el-step>
    <el-step title="管理员审批" ></el-step>
    <el-step title="完成" ></el-step>
  </el-steps>
</div>
</template>

<script>
export default {
  name: "changeDorm",
  data () {
    return {

    }
  }
}
</script>

<style scoped>

</style>
