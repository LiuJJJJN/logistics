<template>
  <el-table
      :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%">
    <el-table-column
        label="序号"
        prop="no">
    </el-table-column>
    <el-table-column
        label="班级名"
        prop="name">
    </el-table-column>
    <el-table-column
        align="right">
      <!-- eslint-disable-next-line -->
      <template slot="header" slot-scope="scope">
        <el-input
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"/>
      </template>
      <template slot-scope="scope">
        <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        &nbsp;
        <el-popover
            placement="top"
            width="160"
            v-model="visible">
          <p>确定删除吗？</p>
          <div style="text-align: right; margin: 0">
            <el-button size="mini" type="text" @click="visible = false">取消</el-button>
            <el-button type="primary" size="mini" @click="visible = false">确定</el-button>
          </div>
          <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              slot="reference">删除</el-button>
        </el-popover>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "tutorClass",
  data() {
    return {
      tableData: [{
        id: 'id1',
        no: '1',
        name: '王小虎'
      }, {
        id: 'id2',
        no: '2',
        name: '王小虎'
      }, {
        id: 'id3',
        no: '3',
        name: '王小虎'
      }, {
        id: 'id4',
        no: '4',
        name: '王小虎'
      }],
      search: ''
    }
  },
  methods: {
    handleEdit(index, row) {
      this.$prompt('请输入班级名', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        this.$message({
          type: 'success',
          message: '新的班级名: ' + value
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    }
  },
}
</script>

<style scoped>

</style>
