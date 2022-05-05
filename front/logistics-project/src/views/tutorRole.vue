<template>
  <div>
    <el-row :gutter="20" style="margin-bottom: 20px">
      <el-col :span="4">
        <el-input
            placeholder="请输入用户名"
            v-model="searchForm.username"
            clearable>
        </el-input>
      </el-col>
      <el-col :span="4">
        <el-input
            placeholder="请输入姓名"
            v-model="searchForm.name"
            clearable>
        </el-input>
      </el-col>
      <el-col :span="5">
        <el-select v-model="searchForm.college" placeholder="请选择所属学院" >
          <div v-for="item in collegeEnum" :key="item">
            <el-option :label="item" :value="item"></el-option>
          </div>
        </el-select>
      </el-col>
      <el-col :span="2">
        <el-button icon="el-icon-search" circle @click="getUserRoleList"></el-button>
      </el-col>
    </el-row>
    <el-table
        :data="tableData"
        style="width: 100%">
      <el-table-column
          type="index"
          width="70">
      </el-table-column>
      <el-table-column
          label="用户名"
          width="150">
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
          label="学院"
          width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.college }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="备注"
          width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.remark }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="角色"
          width="220">
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

    <div class="my-pagination">
      <el-pagination
          background
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNo"
          :page-sizes="[5, 10, 50, 100]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>


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
            <el-checkbox-button disabled>学生</el-checkbox-button>
            <el-checkbox-button disabled>导员</el-checkbox-button>
            <el-checkbox-button v-for="perm in perms" :label="perm" :key="perm">{{perm}}</el-checkbox-button>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="changeStudentRole">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
const permOptions = ['管理员'];
export default {
  name: "tutorRole",
  data(){
    return{
      collegeEnum:[],
      searchForm:{
        username:'',
        name:'',
        college:''
      },
      submitForm:{
        id:'loading',
        username:'loading',
        name:'loading',
        remark:'loading',
        perms: ['导员'],
      },
      tableData: [{
        id:'',
        username:'loading',
        name: 'loading',
        perms: [],
        sex: 'loading',
        college:'',
        remark: 'loading',
      }],
      perms: permOptions,
      dialogFormVisible: false,
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一年',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30 * 12);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
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
      formLabelWidth: '120px',
      pageNo: 1, //当前页数
      pageSize: 10, //显示条数
      total: 0, //总条数
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
    },
    getUserRoleList(){
      this.$axios.post("/permission/getTutorRoleListTotal.do",
          {
            name:this.searchForm.name,
            sno:this.searchForm.sno,
            college:this.searchForm.college,
            stuClass:this.searchForm.stuClass,
            date:this.searchForm.date
          })
          .then(resp=>{
            this.total = resp.data.data;
            // console.log(resp.data.data);
          },err=>{
            console.log(err);
          });
      this.$axios.post("/permission/getTutorRoleList.do",
          {
            username:this.searchForm.username,
            name:this.searchForm.name,
            sno:this.searchForm.sno,
            college:this.searchForm.college,
            pageNo:(this.pageNo-1)*this.pageSize,
            pageSize:this.pageSize
          })
          .then(resp=>{
            this.tableData = resp.data.data;
            // console.log(resp.data.data);
          },err=>{
            console.log(err)
          })
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getUserRoleList();
      // console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.pageNo = val;
      this.getUserRoleList();
      // console.log(`当前页: ${val}`);
    },
    loadCollege:function (){
      this.$axios.get("/dic/getCollegeList.do").then(resp=>{
        var list = [];
        list[0] = '';
        for(let i=0; i<resp.data.data.length;i++){
          list[i+1] = resp.data.data[i].value;
        }
        this.collegeEnum = list;
        // console.log(this.collegeEnum);
      }, err=>{
        console.log(err)
      })
    },
    changeStudentRole(){
      this.dialogFormVisible = false;
      // console.log(this.submitForm);
      this.$axios.post("/permission/changeUserRoleList.do", this.submitForm)
          .then(resp=>{
            this.getUserRoleList();
            console.log(resp.data)
          }, err=>{
            console.log(err)
          })
    }
  },
  created() {
    this.getUserRoleList();
    this.loadCollege();
  }
}
</script>

<style scoped>
.my-pagination{
  margin: 20px;
}
</style>
