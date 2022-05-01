<template>
  <div>
    <!--历史记录抽屉窗口-->
    <el-button :plain="true" @click="table = true">历史记录</el-button>
    <el-drawer
        title="历史记录"
        :visible.sync="table"
        direction="rtl"
        size="38%">
      <el-table :data="gridData">
        <el-table-column property="date" label="反馈时间" width="150"></el-table-column>
        <el-table-column property="date" label="回复时间" width="130"></el-table-column>
        <el-table-column property="name" label="反馈标题" width="130"></el-table-column>
        <el-table-column property="name" label="查看详细" width="130"><el-button :plain="true" @click="one">
          查看详细</el-button></el-table-column>
      </el-table>
    </el-drawer>

    <el-table
        :data="tableData"
        style="width: 100%"><el-table-column
        prop="time"
        label="反馈(吐槽)时间"
        width="200">
    </el-table-column>

      <el-table-column
          prop="title"
          label="反馈(吐槽)标题"
          width="230">
      </el-table-column>
      <el-table-column
          prop="feedback"
          label="反馈(吐槽)内容"
          width="360">
      </el-table-column>
      <el-table-column
          prop="status"
          label="状态"
          width="260">
      </el-table-column>
    </el-table>

<!--  进度条  -->
<!--    <div>
      <el-steps :space="200" :active="active" finish-status="success">
        <el-step title="上传反馈"></el-step>
        <el-step title="等待批阅中"></el-step>
        <el-step title="已答复"></el-step>
      </el-steps>
    </div>-->
  </div>

</template>

<script>
export default {
  name: "userMyFeedback",
  data(){
    return{
      active: 2,
      tableData: [{
        time: '2016-05-02',
        title: '王小虎',
        feedback: '上海市普陀区金沙江路 1518 弄',
        status:'未回复'
      }],
      table: false,
      dialog: false,
      loading: false,
      gridData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }],
    }
  },
  methods: {

  }
}
</script>

<style scoped>

</style>