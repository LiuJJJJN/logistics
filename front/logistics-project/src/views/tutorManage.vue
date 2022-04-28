<template>
  <div>
    <div style="height: 60px; margin-left: -35px">
      <el-input v-model="input.inputUsername" placeholder="请输入用户名" class="inputUsername"></el-input>
      <el-input v-model="input.inputName" placeholder="请输入姓名" class="inputName"></el-input>
      <el-select v-model="searchSelectCollege" placeholder="请选择学院" class="inputUsername">
        <el-option
            v-for="item in collegeOptions"
            :key="item"
            :label="item"
            :value="item">
        </el-option>
      </el-select>
      <el-button type="primary" icon="el-icon-search" @click="searchBtn" class="search">搜索</el-button>
    </div>
    <el-button
        size="mini"
        type="primary"
        icon="el-icon-delete"
        slot="reference"
        @click="delBtn"
        class="functionBtn"></el-button>

    <el-button
        size="mini"
        type="primary"
        @click="resetPwd"
        class="functionBtn"
        style="width: 80px">重置密码
    </el-button>

    <el-table
        :data="tableData"
        stripe
        style="width: 100%"
        @selection-change="delOrResetSelection">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          prop=""
          label=""
          width="10">
      </el-table-column>
      <el-table-column
          prop="username"
          label="用户名"
          width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.username }}</span>
        </template>
      </el-table-column>

      <el-table-column
          prop="name"
          label="姓名"
          width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>

      <el-table-column
          prop="sex"
          label="性别"
          width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.sex }}</span>
        </template>
      </el-table-column>

      <el-table-column
          prop="college"
          label="学院"
          width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.college }}</span>
        </template>
      </el-table-column>

      <el-table-column
          prop="remark"
          label="备注"
          width="240">
        <template slot-scope="scope">
          <span>{{ scope.row.remark }}</span>
        </template>
      </el-table-column>

      <el-table-column
          prop="zip"
          label="操作"
          width="118">
        <template slot-scope="scope">
          <el-button
              size="mini"
              @click="addOrUpdateBtn(scope.$index,scope.row)">添加/修改备注
          </el-button>
        </template>
      </el-table-column>
      <el-table-column
          prop="zip"
          label=""
          width="100">
        <template slot-scope="">

        </template>
      </el-table-column>
      <el-table-column
          prop=""
          label=""
          width="">
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <div class="block my-pagination">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNo"
          background
          :page-sizes="[5, 10, 50, 100]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <!--  添加/修改模态窗口-->
    <!--    <el-button type="text" @click="dialogFormVisible = true">打开嵌套表单的 Dialog</el-button>-->

    <el-dialog title="删除/修改" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="备注：" :label-width="formLabelWidth">
          <el-input v-model="form.remark" autocomplete="off" class="addAndUpCss"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateSureBtn">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "tutorManage",
  data() {
    return {
      //搜索被选中的college
      searchSelectCollege: '',
      //搜索-学院下拉菜单
      collegeOptions: [],
      //多选
      multipleSelection: [],
      idArray: [],
      resetArray: [],
      tableData: [{
        id: '',
        username: '',
        name: '',
        sex: '',
        college: '',
        remark: ''
      }],
      //模态窗口
      dialogFormAddDicValue: false,
      //添加或修改模态窗口
      dialogFormVisible: false,
      form: {
        remark: ''
      },
      add: {
        id: '',
        code: '',
        name: '',
        description: ''
      },
      formLabelWidth: '120px',
      pageNo: 1, //当前页数
      pageSize: 10, //显示条数
      total: 0, //总条数
      input: {
        inputUsername: '',
        inputName: '',
        inputDescription: ''
      }
    }
  },
  methods: {
    //删除的确认弹窗
    open() {

    },
    //加载页面获取导员信息展示到表格中
    getTutorList() {
      this.$axios.post("/admin/manage/getTutorList.do", {
        pageNo: (this.pageNo - 1) * this.pageSize,
        pageSize: this.pageSize
      })
          .then(resp => {
            //总记录数
            this.total = resp.data.data.total;
            //导员信息
            this.tableData = resp.data.data.list;
            console.log(resp);
          }, err => {
            console.log(err);
          });
    },
    //加载页面，加载college下拉列表
    getCollegeList() {
      this.$axios.post("/admin/manage/getCollegeList.do", {})
          .then(resp => {
            console.log(resp);
            var list = [];
            //list[0]='';
            for (let i = 0; i < resp.data.data.length; i++) {
              list[i] = resp.data.data[i].value;
            }
            this.collegeOptions = list;
          }, err => {
            console.log(err);
          });
    },
    //显示几条
    handleSizeChange(val) {
      this.pageSize = val;
      this.getTutorList();
    },
    //当前页数
    handleCurrentChange(val) {
      this.pageNo = val;
      this.getTutorList();
    },
    //添加或修改按钮
    addOrUpdateBtn(index, row) {
      this.dialogFormVisible = true;
      this.form.remark = row.remark;
      this.$axios.post("/admin/manage/addOrUpTutorRemark.do",
          this.form.remark
      )
          .then(resp => {
            console.log(resp);
            this.getTutorList();
          }, err => {
            console.log(err);
          });
    },
    //获取被选择的实例,同时将被选择的实例提取id转为数组
    delOrResetSelection(val) {
      this.multipleSelection = val;
      for (let i = 0; i < this.multipleSelection.length; i++) {
        this.idArray[i] = this.multipleSelection[i].id;
      }
    },
    //重置密码6个0按钮
    resetPwd() {
      if (this.idArray.length != 0) {
        this.$confirm('此操作将重置密码（6个0）, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          //重置
          this.$axios.post("/admin/manage/resetTP.do",
              this.idArray
          )
              .then(resp => {
                console.log(resp);
                this.idArray = this.resetArray;
              }, err => {
                console.log(err);
              });
          this.$message({
            type: 'success',
            message: '重置成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消重置'
          });
        });
      } else {
        this.$message({
          message: '请选择要重置密码的导员',
          type: 'warning'
        });
      }

    },
    //搜索
    searchBtn() {
      this.$axios.post("/admin/manage/getTutorList.do",
          {
            pageNo: (this.pageNo - 1) * this.pageSize,
            pageSize: this.pageSize,
            username: this.input.inputUsername,
            name: this.input.inputName,
            college: this.searchSelectCollege
          })
          .then(resp => {
            this.total = resp.data.data.total;
            this.tableData = resp.data.data.list;
          }, err => {
            console.log(err);
          });
      this.searchSelectCollege = '';
      this.input.inputUsername = '';
      this.input.inputName = '';
    },
    //删除
    delBtn() {

      if (this.idArray.length != 0) {
        this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          //删除
          this.$axios.post("/admin/manage/delTutorL.do",
              this.idArray
          ).then(resp => {
            console.log(resp);
            this.getTutorList();
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
          message: '请选择要删除的导员',
          type: 'warning'
        });
      }


    }
  },
  created() {
    this.getTutorList();
    this.getCollegeList();
    //this.loadCollege();
  }
}
</script>

<style scoped>
.inputUsername {
  width: 200px;
  margin-left: 40px;
}

.inputName {
  width: 200px;
  margin-left: 40px;
  margin-bottom: 30px;
}

.inputDescription {
  width: 200px;
  margin-left: 40px;
  margin-bottom: 30px;
}

.search {
  margin-left: 40px;
}

.addAndUpCss {
  width: 250px;
}

.my-pagination {
  margin-left: 350px;
}

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