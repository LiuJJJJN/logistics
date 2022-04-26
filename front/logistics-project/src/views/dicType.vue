<template>
  <dev>
    <el-input v-model="input.inputCode" placeholder="请输入code" class="inputCode"></el-input>
    <el-input v-model="input.inputName" placeholder="请输入名称" class="inputName"></el-input>
    <el-button type="primary" icon="el-icon-search" @click="searchBtn" class="search">搜索</el-button>
    <br>
    <el-button
        size="mini"
        @click="open" >删 除</el-button>
    <el-button
        size="mini"
        @click="addDicTypeBtn" >添 加</el-button>

    <el-table
        :data="tableData"
        stripe
        style="width: 100%"
        @selection-change="handleSelectionChange">
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
        prop="code"
        label="code"
        width="230">
        <template slot-scope="scope">
          <span>{{ scope.row.code }}</span>
        </template>
      </el-table-column>

      <el-table-column
          prop="name"
          label="名称"
          width="260">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>

      <el-table-column
          prop="description"
          label="描述"
          width="250">
        <template slot-scope="scope">
          <span>{{ scope.row.description }}</span>
        </template>
      </el-table-column>

      <el-table-column
          prop="zip"
          label="操作"
          width="85">
        <template slot-scope="scope">
          <el-button
              size="mini"
              @click="updateBtn(scope.$index,scope.row)" >修 改</el-button>
        </template>
      </el-table-column>
      <el-table-column
          prop="zip"
          label=""
          width="100">
        <template slot-scope="">

        </template>
        <!--      <el-button type="text" @click="open">点击打开 Message Box</el-button>-->
      </el-table-column>
      <el-table-column
          prop=""
          label=""
          width="">
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

    <!--  修改模态窗口-->
<!--    <el-button type="text" @click="dialogFormVisible = true">打开嵌套表单的 Dialog</el-button>-->

    <el-dialog title="修改" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="代码" :label-width="formLabelWidth">
          <el-input v-model="form.code" autocomplete="off" class="aaa"></el-input>
        </el-form-item>
        <el-form-item label="名称" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off" class="aaa"></el-input>
        </el-form-item>
        <el-form-item label="描述" :label-width="formLabelWidth">
          <el-input v-model="form.description" autocomplete="off" class="aaa"></el-input>
        </el-form-item>
<!--        <el-form-item label="活动区域" :label-width="formLabelWidth">
          <el-select v-model="form.region" placeholder="请选择活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateSureBtn">确 定</el-button>
      </div>
    </el-dialog>


    <!--添加模态窗口-->
    <el-dialog title="修改" :visible.sync="dialogFormAddDicValue">
      <el-form :model="add">
        <el-form-item label="代码" :label-width="formLabelWidth">
          <el-input v-model="add.code" autocomplete="off" class="aaa"></el-input>
        </el-form-item>
        <el-form-item label="名称" :label-width="formLabelWidth">
          <el-input v-model="add.name" autocomplete="off" class="aaa"></el-input>
        </el-form-item>
        <el-form-item label="描述" :label-width="formLabelWidth">
          <el-input v-model="add.description" autocomplete="off" class="aaa"></el-input>
        </el-form-item>
        <!--        <el-form-item label="活动区域" :label-width="formLabelWidth">
                  <el-select v-model="form.region" placeholder="请选择活动区域">
                    <el-option label="区域一" value="shanghai"></el-option>
                    <el-option label="区域二" value="beijing"></el-option>
                  </el-select>
                </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormAddDicValue = false">取 消</el-button>
        <el-button type="primary" @click="addSureBtn">确 定</el-button>
      </div>
    </el-dialog>
  </dev>
</template>

<script>
export default {
  name: "dicType",
  data() {
    return {
      /*searchDicTypeList: [{
        code:'',
        name: '',
        description: '',

      }],*/
      tableData: [{
        id:'',
        code:'',
        name: '',
        description: '',
      }],
      //模态窗口
      //dialogTableVisible: false,
      dialogFormAddDicValue: false,
      dialogFormVisible: false,
      form: {
        id:'',
        code:'',
        name: '',
        description:'',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      add: {
        id:'',
        code:'',
        name:'',
        description:''
      },
      formLabelWidth: '120px',
      pageNo: 1, //当前页数
      pageSize: 10, //显示条数
      total: 0, //总条数
      input:{
        inputCode:'',
        inputName:''
      }
    }
  },
  methods:{
    //删除的确认弹窗
    open() {
      this.$confirm('此操作将永久删除该数据字典类型, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
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
    },
    //搜索
    searchBtn(){
      //alert(this.input.inputCode+","+this.input.inputName+","+this.pageNo+","+this.pageSize);
      this.$axios.post("/admin/getDicTByCN.do",
          {
            pageNo:(this.pageNo-1)*this.pageSize,
            pageSize:this.pageSize,
            name:this.input.inputName,
            code:this.input.inputCode
          })
          .then(resp=>{
            this.total=resp.data.data.total;
            this.tableData = resp.data.data.list;
          },err=>{
            console.log(err);
          });
    },
    //加载表格数据
    getDicTypeList(){
      this.$axios.post("/admin/getDicTByCN.do",
          {
            pageNo:(this.pageNo-1)*this.pageSize,
            pageSize:this.pageSize
          })
          .then(resp=>{
            console.log(resp.data);
            this.total=resp.data.data.total;
            this.tableData = resp.data.data.list;

          },err=>{
            console.log(err);
          });
    },
    //显示几条
    handleSizeChange(val) {
      this.pageSize = val;
      //alert("size"+this.pageSize);
      this.searchBtn();
      // console.log(`每页 ${val} 条`);
    },
    //当前页数
    handleCurrentChange(val) {
      this.pageNo = val;

      alert("no"+this.pageNo);
      this.searchBtn();
      // console.log(`当前页: ${val}`);
    },
    //模态窗口
    updateBtn(index,row){
      this.dialogFormVisible = true;
      this.form.id=row.id;
      this.form.code=row.code;
      this.form.name=row.name;
      this.form.description=row.description;
    },
    //修改按钮
    updateSureBtn(){
      alert(this.form.id);
      this.$axios.post("/admin/updateDicTL.do",
          {
                  code:this.form.code,
                  name:this.form.name,
                  description:this.form.description,
                  id:this.form.id
          })
          .then(resp=>{
            console.log(resp.data);
            this.tableData=resp.data.data;

          },err=>{
            console.log(err);
          });
      this.dialogFormVisible = false
    },
    //添加按钮
    addDicTypeBtn(){
      this.dialogFormAddDicValue = true;
    },
    //添加确定按钮
    addSureBtn(){
      alert(this.add.code);
      this.$axios.post("/admin/setDicTL.do",
          {
            code:this.add.code,
            name:this.add.name,
            description:this.add.description,
          })
          .then(resp=>{
            console.log(resp.data);
            this.tableData=resp.data.data;

          },err=>{
            console.log(err);
          });
      this.dialogFormAddDicValue = false;
    }
  },
  created() {
    this.getDicTypeList();
    //this.loadCollege();
  }
}
</script>

<style scoped>
.inputCode{
  width:200px;
  margin-left: 40px;
}
.inputName{
  width:200px;
  margin-left: 40px;
  margin-bottom: 30px;
}
.search{
  margin-left: 40px;
}
.aaa{
  width: 120px;
}
.my-pagination{
  margin-left: 350px;
}
</style>