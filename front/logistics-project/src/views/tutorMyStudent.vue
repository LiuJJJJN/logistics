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
        <el-select v-model="searchForm.college" placeholder="请选择所属学院">
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
            unlink-panels
            range-separator="至"
            start-placeholder="入学时间开始日期"
            end-placeholder="入学时间结束日期"
            :picker-options="pickerOptions">
        </el-date-picker>
      </el-col>
      <el-col :span="2">
        <el-button icon="el-icon-search" circle @click="getStudentList"></el-button>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="1.5"><div class="grid-content bg-purple">
        <el-button
            size="mini"
            type="danger"
            icon="el-icon-delete"
            slot="reference"
            @click="delBtn"
            class="functionBtn"></el-button>
      </div></el-col>
      <el-col :span="2"><div class="grid-content bg-purple">
        <!--学生下载信息-->
        <el-form :action="actionURL" method="post" type="primary">
          <el-input type="submit" value="导出" style="width: 80px;"/>
        </el-form>
      </div></el-col>
      <!--学生上传信息-->
      <el-col :span="2"><div class="grid-content bg-purple">
        <el-button @click="openUpload" type="primary" plain>导入我的学生</el-button>
      </div></el-col>
    </el-row>

    <!--上传模态窗口-->
    <el-dialog title="上传文件" :visible.sync="dialogFormVisible">
      <el-form action='http://localhost:8080/logisticsProject/permission/downloadM.do' method="post" type="primary">
        <el-input type="submit" value="下载模板" style="width: 80px;"/>
      </el-form>
      <el-upload
          class="upload-demo"
          drag
          :action="uploadURL" style="margin-left: 200px"
          multiple>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip"><font style="color:red;">只能上传Excel文件，并且所有单元格格式设置为文本格式！</font></div>
        <div class="el-upload__tip" slot="tip"><font style="color:red">注：上传后的学生，账号用户名默认为学号，密码为6个0</font></div>
      </el-upload>
    </el-dialog>

    <!--表格-->
    <el-table
        :data="tableData"
        style="width: 100%"
        @selection-change="delOrResetSelection">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          label="用户名"
          width="120">
        <template slot-scope="scope">
          <span>{{ scope.row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="姓名"
          width="80">
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
          width="120">
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
          width="190">
        <template slot-scope="scope">
          <span>{{ scope.row.remark }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
              size="mini"
              @click="showRemarkDialog(scope.$index, scope.row)">修改备注
          </el-button>
          <el-button
              size="mini"
              @click="resetPwd(scope.$index, scope.row)">重置密码
          </el-button>
          <el-button
              size="mini"
              @click="abandonStu(scope.$index, scope.row)">抛弃学生
          </el-button>
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
  name: "tutorMyStudent",
  data() {
    return {
      dialogFormVisible: false,
      actionURL: 'http://localhost:8080/logisticsProject/permission/downloadStu.do?id=' + this.$store.getters.getUser.userId,
      uploadURL: 'http://localhost:8080/logisticsProject/permission/uploadMyStu.do?id=' + this.$store.getters.getUser.userId,
      collegeEnum: [],
      searchForm: {
        name: '',
        sno: '',
        college: '',
        stuClass: '',
        date: []
      },
      submitForm: {
        id: 'loading',
        remark: 'loading',

      },
      tableData: [],
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
      multipleSelection: [],

      idArray: [],
      isTutor: this.$store.getters.getUser.primaryRole == '导员',
      downloadUrl: '',
    }
  },
  methods: {
    //上传模态窗口打开
    openUpload(){
      this.dialogFormVisible=true;
    },
    showRemarkDialog(index, row) {
      this.dialogRemarkFormVisible = true
      this.submitForm.id = row.id;
      this.submitForm.name = row.name;
      this.submitForm.remark = row.remark;
      this.submitForm.sex = row.sex;
      this.submitForm.username = row.username;
    },
    getStudentList() {
      //复用了权限管理中的接口，获取学生总数
      this.$axios.post("/permission/getStudentListByTutorTotal.do",
          {
            name: this.searchForm.name,
            sno: this.searchForm.sno,
            college: this.searchForm.college,
            stuClass: this.searchForm.stuClass,
            date: this.searchForm.date
          })
          .then(resp => {
            this.total = resp.data.data;
            // console.log(resp.data.data);
          }, err => {
            console.log(err);
          });
      this.$axios.post("/admin/manage/getStudentListByTutor.do",
          {
            name: this.searchForm.name,
            sno: this.searchForm.sno,
            college: this.searchForm.college,
            stuClass: this.searchForm.stuClass,
            date: this.searchForm.date,
            pageNo: (this.pageNo - 1) * this.pageSize,
            pageSize: this.pageSize
          })
          .then(resp => {
            this.tableData = resp.data.data;
            // console.log(resp.data.data);
          }, err => {
            console.log(err)
          })
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getStudentList();
      // console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.pageNo = val;
      this.getStudentList();
      // console.log(`当前页: ${val}`);
    },
    loadCollege: function () {
      this.$axios.get("/dic/getCollegeList.do").then(resp => {
        var list = [];
        list[0] = '';
        for (let i = 0; i < resp.data.data.length; i++) {
          list[i + 1] = resp.data.data[i].value;
        }
        this.collegeEnum = list;
        // console.log(this.collegeEnum);
      }, err => {
        console.log(err)
      })
    },
    editStudentRemark() {
      this.dialogRemarkFormVisible = false;
      // console.log(this.submitForm);
      this.$axios.post("/admin/manage/editStudentRemark.do", this.submitForm)
          .then(resp => {
            this.getStudentList();
            this.$message({
              type: 'success',
              message: resp.data.message
            });
            console.log(resp.data)
          }, err => {
            console.log(err)
          })
    },
    delBtn() {
      if (this.idArray.length !== 0) {
        this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          //删除
          this.$axios.post("/admin/manage/delStudentL.do",
              this.idArray
          ).then(resp => {
            console.log(resp);
            this.getStudentList();
          }, err => {
            console.log(err);
          });
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      } else {
        this.$message({
          message: '请选择要删除的学生',
          type: 'warning'
        });
      }
    },
    resetPwd(index, row) {
      console.log(row.id)
      this.$confirm('此操作将修改学生密码为 \'000000\' 且不可逆, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        //删除
        this.$axios.post("/admin/manage/resetStudentPassword.do", {
          id: row.id
        }).then(resp => {
          this.$message({
            message: resp.data.message,
            type: 'success'
          });
        }, err => {
          console.log(err);
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消修改'
        });
      });
    },
    delOrResetSelection(val) {
      this.multipleSelection = val;
      for (let i = 0; i < this.multipleSelection.length; i++) {
        this.idArray[i] = this.multipleSelection[i].id;
      }
    },
    abandonStu(index, row) {
      this.$axios.get("/tutor/abandonStu.do", {
        params: {
          stuId: row.id
        }
      }).then(resp => {
        this.$message({
          message: resp.data.message,
          type: 'success'
        });
        this.getStudentList();
      }, err => {
        console.log(err);
      });
    }
  },
  created() {
    this.getStudentList();
    this.loadCollege();
  }
}
</script>

<style scoped>
.my-pagination {
  margin: 20px;
}

.functionBtn {
  display: inline-block;
  height: 35px;
  width: 60px;
  margin-left: 5px;
  margin-bottom: 13px;
  margin-right: 10px;
}
</style>
