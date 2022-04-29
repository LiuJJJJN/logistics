<template>
  <div>
    <el-row :gutter="20" style="margin-bottom: 20px">
      <el-col :span="3">
        <el-input
            placeholder="请输入姓名"
            v-model="searchForm.name"
            clearable>
        </el-input>
      </el-col>
      <el-col :span="3">
        <el-input
            placeholder="请输入学号"
            v-model="searchForm.sno"
            clearable>
        </el-input>
      </el-col>
      <el-col :span="3">
        <el-select v-model="searchForm.college" placeholder="请选择所属学院" >
          <div v-for="item in collegeEnum" :key="item">
            <el-option :label="item" :value="item"></el-option>
          </div>
        </el-select>
      </el-col>
      <el-col :span="3">
        <el-input
            placeholder="请输入班级"
            v-model="searchForm.stuClass"
            clearable>
        </el-input>
      </el-col>
      <el-col :span="7">
        <el-date-picker
            v-model="searchForm.date"
            type="daterange"
            align="right"
            unlink-panels
            range-separator="至"
            start-placeholder="入学时间开始日期"
            end-placeholder="入学时间结束日期"
            :picker-options="pickerOptions">
        </el-date-picker>
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
          width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="姓名"
          width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="性别"
          width="50">
        <template slot-scope="scope">
          <span>{{ scope.row.sex }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="学号"
          width="120">
        <template slot-scope="scope">
          <span>{{ scope.row.sno }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="注册时间"
          width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.enterDate }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="学院"
          width="130">
        <template slot-scope="scope">
          <span>{{ scope.row.college }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="班级"
          width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.stuClass }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="学制"
          width="50">
        <template slot-scope="scope">
          <span>{{ scope.row.schoolSys }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="备注"
          width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.remark }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
              size="mini"
              @click="showDialog(scope.$index, scope.row)" >修改备注</el-button>
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


    <!--  修改备注模态窗口-->
    <el-dialog title="修改备注信息" :visible.sync="dialogRemarkFormVisible">
      <el-form :model="form">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <span>{{ submitForm.username }}</span>
        </el-form-item>
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <span>{{ submitForm.name }}</span>
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth">
          <el-input
              type="textarea"
              placeholder="请输入内容"
              v-model="submitForm.remark"
              maxlength="255"
              show-word-limit
              style="width: 500px"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogRemarkFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="editStudentRemark">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "stuManage",
  data(){
    return{
      collegeEnum:[],
      searchForm:{
        name:'',
        sno:'',
        college:'',
        stuClass:'',
        date:[]
      },
      submitForm:{
        id:'loading',
        remark:'loading',
      },
      tableData: [{
        id:'',
        username:'loading',
        name: 'loading',
        sex: 'loading',
        sno:'loading',
        enterDate: '',
        college:'',
        stuClass:'',
        schoolSys:'',
        remark: 'loading',
      }],
      dialogRemarkFormVisible: false,
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
      this.dialogRemarkFormVisible = true
      this.submitForm.id = row.id;
      this.submitForm.name = row.name;
      this.submitForm.remark = row.remark;
      this.submitForm.sex = row.sex;
      this.submitForm.username = row.username;
    },
    getUserRoleList(){
      //复用了权限管理中的接口，获取学生总数
      this.$axios.post("/permission/getStudentListTotal.do",
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
      this.$axios.post("/admin/manage/getStudentList.do",
          {
            name:this.searchForm.name,
            sno:this.searchForm.sno,
            college:this.searchForm.college,
            stuClass:this.searchForm.stuClass,
            date:this.searchForm.date,
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
      this.$axios.get("/getCollegeList.do").then(resp=>{
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
    editStudentRemark(){
      this.dialogRemarkFormVisible = false;
      // console.log(this.submitForm);
      this.$axios.post("/admin/manage/editStudentRemark.do", this.submitForm)
          .then(resp=>{
            this.$router.go(0);
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
