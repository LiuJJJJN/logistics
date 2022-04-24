<template>
<div>
  <el-table
      :data="tableData"
      style="width: 100%">
    <el-table-column
        label="用户名"
        width="180">
      <template slot-scope="scope">
        <span>{{ scope.row.username }}</span>
      </template>
    </el-table-column>
    <el-table-column
        label="姓名"
        width="150">
      <template slot-scope="scope">
        <span>{{ scope.row.name }}</span>
      </template>
    </el-table-column>
    <el-table-column
        label="性别"
        width="100">
      <template slot-scope="scope">
        <span>{{ scope.row.sex }}</span>
      </template>
    </el-table-column>
    <el-table-column
        label="备注"
        width="300">
      <template slot-scope="scope">
        <span>{{ scope.row.remark }}</span>
      </template>
    </el-table-column>
    <el-table-column
        label="角色"
        width="200">
      <template slot-scope="scope">
        <el-popover trigger="hover" placement="top" v-for="perm in scope.row.perms" :key="perm" style="display: inline-block; margin-right: 10px">
          <p>角色名: {{ perm }}</p>
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ perm }}</el-tag>
          </div>
        </el-popover>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
            size="mini"
            @click="showDialog(scope.$index, scope.row)" >修改权限</el-button>
      </template>
    </el-table-column>
  </el-table>

  <!--  选择权限模态窗口-->
  <el-dialog title="修改角色权限" :visible.sync="dialogFormVisible">
    <el-form :model="form">
      <el-form-item label="用户名" :label-width="formLabelWidth">
        <span>{{ submitForm.username }}</span>
      </el-form-item>
      <el-form-item label="姓名" :label-width="formLabelWidth">
        <span>{{ submitForm.name }}</span>
      </el-form-item>
      <el-form-item label="备注" :label-width="formLabelWidth">
        <span>{{ submitForm.remark }}</span>
      </el-form-item>
      <el-form-item label="权限" :label-width="formLabelWidth">
        <el-checkbox-group v-model="submitForm.perms" size="small">
          <el-checkbox-button v-for="perm in perms" :label="perm" :key="perm">{{perm}}</el-checkbox-button>
        </el-checkbox-group>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
    </div>
  </el-dialog>

</div>
</template>

<script>
const permOptions = ['学生', '导员', '管理员'];
export default {
  name: "adminStudentRole",
  data(){
    return{
      submitForm:{
        id:'',
        username:'mike',
        name:'mi',
        remark:'',
        perms: ['学生'],
      },
      tableData: [{
        id:'',
        username:'stu1',
        name: '王小虎',
        perms: ['学生', '导员'],
        sex: '男',
        remark: '这人贼帅',
      },
      {
        id:'',
        username:'stu2',
        name: '王小虎2',
        perms:['学生', '导员'],
        sex: '男',
        remark: '这人贼帅',
      }],
      perms: permOptions,
      dialogTableVisible: false,
      dialogFormVisible: false,
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      formLabelWidth: '120px'
    }
  },
  methods:{
    showDialog (index, row) {
      this.dialogFormVisible = true
      this.submitForm.id = row.id;
      this.submitForm.name = row.name;
      this.submitForm.perms = row.perms;
      this.submitForm.remark = row.remark;
      this.submitForm.sex = row.sex;
      this.submitForm.username = row.username;
      console.log(index, row);
    },
    getUserRoleList(){
      this.$axios.post("http://localhost:8080/logisticsProject/permission/getUserRoleList.do")
          .then(resp=>{
            this.tableData = resp.data.data;
            console.log(resp.data.data);
          },err=>{
            console.log(err)
          })
    }
  },
  created() {
    this.getUserRoleList();
  }
}
</script>

<style scoped>

</style>
