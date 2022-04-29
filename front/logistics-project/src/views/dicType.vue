<template>
  <div>
    <div style="height: 60px; margin-left: -35px">
      <el-input v-model="input.inputCode" placeholder="请输入code" class="inputCode"></el-input>
      <el-input v-model="input.inputName" placeholder="请输入名称" class="inputName"></el-input>
      <el-input v-model="input.inputDescription" placeholder="请输入描述" class="inputDescription"></el-input>
      <el-button type="primary" icon="el-icon-search" @click="searchBtn" class="search">搜索</el-button>
    </div>
    <!--  删除按钮  -->
    <el-popconfirm
        title="确定删除吗？">
      <el-button
          size="mini"
          type="danger"
          icon="el-icon-delete"
          slot="reference"
          @click="delBtn"
          class="functionBtn"></el-button>
    </el-popconfirm>
    <!--  添加按钮  -->
    <el-button
        size="mini"
        type="primary"
        icon="el-icon-plus"
        @click="addDicTypeBtn"
        class="functionBtn"></el-button>

    <el-table
        :data="tableData"
        stripe
        style="width: 100%"
        @selection-change="selection">
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

<!-- 分页 -->
    <div class="block my-pagination">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNo"
          :page-sizes="[5, 10, 50, 100]"
          :page-size="pageSize"
          background
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <!--  修改模态窗口-->
<!--    <el-button type="text" @click="dialogFormVisible = true">打开嵌套表单的 Dialog</el-button>-->

    <el-dialog title="修 改" :visible.sync="dialogFormVisible">
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
    <el-dialog title="添 加" :visible.sync="dialogFormAddDicValue">
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
  </div>
</template>

<script>
export default {
  name: "dicType",
  data() {
    return {
      //多选
      multipleSelection: [],
      idArray:[],
      tableData: [{
        id:'',
        code:'',
        name: '',
        description: '',
      }],
      //模态窗口
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
        inputName:'',
        inputDescription:''
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
      this.$axios.post("/admin/getDicTByCN.do",
          {
            pageNo:(this.pageNo-1)*this.pageSize,
            pageSize:this.pageSize,
            name:this.input.inputName,
            code:this.input.inputCode,
            description:this.input.inputDescription
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
      //pageNo第几页
      //pageSize 多少条一页
      this.$axios.post("/admin/getDicTByCN.do",
          {
            pageNo:(this.pageNo-1)*this.pageSize,
            pageSize:this.pageSize
          })
          .then(resp=>{
            console.log(resp.data);
            //alert(this.pageSize);
            this.total=resp.data.data.total;
            this.tableData = resp.data.data.list;
          },err=>{
            console.log(err);
          });

    },
    //显示几条
    handleSizeChange(val) {
      this.pageSize = val;
      this.getDicTypeList();
    },
    //当前页数
    handleCurrentChange(val) {
      this.pageNo = val;
      this.getDicTypeList();
    },
    //修改-模态窗口
    updateBtn(index,row){
      this.dialogFormVisible = true;
      this.form.id=row.id;
      this.form.code=row.code;
      this.form.name=row.name;
      this.form.description=row.description;
    },
    //修改按钮
    updateSureBtn(){
      if(this.form.code==''){
        this.$notify({
          title: '偏移',
          message: '代码不能为空',
          offset: 100
        });
        return false;
      }else if(this.form.name==''){
        this.$notify({
          title: '偏移',
          message: '名称不能为空',
          offset: 100
        });
        return false;
      }else if(this.form.description==''){
        this.$notify({
          title: '偏移',
          message: '描述不能为空',
          offset: 100
        });
        return false;
      }else{
        this.$axios.post("/admin/updateDicTL.do",
            {
              code:this.form.code,
              name:this.form.name,
              description:this.form.description,
              id:this.form.id
            })
            .then(resp=>{
              console.log(resp.data);
              //this.tableData=resp.data.data;
              this.getDicTypeList();
            },err=>{
              console.log(err);
            });
        this.dialogFormVisible = false
      }


    },
    //添加按钮
    addDicTypeBtn(){
      this.dialogFormAddDicValue = true;
    },
    //添加确定按钮
    addSureBtn(){
      if(this.add.code==''){
        this.$notify({
          title: '偏移',
          message: '代码不能为空',
          offset: 100
        });
        return false;
      }else if(this.add.name==''){
        this.$notify({
          title: '偏移',
          message: '名称不能为空',
          offset: 100
        });
        return false;
      }else if(this.add.description==''){
        this.$notify({
          title: '偏移',
          message: '描述不能为空',
          offset: 100
        });
        return false;
      }else{
        this.$axios.post("/admin/setDicTL.do",
            {
              code:this.add.code,
              name:this.add.name,
              description:this.add.description,
            })
            .then(resp=>{
              console.log(resp.data);
              this.tableData=resp.data.data;
              this.add.code='';
              this.add.name='';
              this.add.description='';
              this.getDicTypeList();
            },err=>{
              console.log(err);
            });
        this.dialogFormAddDicValue = false;
      }

    },
    //删除
    selection(val){
      this.multipleSelection = val;
      console.log(val);
      /*alert(val[0].code+","+val[0].name+","+val[0].description+","+val[0].id);*/
    },
    delBtn(){
      for(let i=0;i<this.multipleSelection.length;i++){
        this.idArray[i]=this.multipleSelection[i].id;
      }

      this.$axios.post("/admin/delDicTL.do",
          this.idArray
          )
          .then(resp=>{
            console.log(resp.data);
            console.log(resp.data);
            this.tableData=resp.data.data;
            this.getDicTypeList();
          },err=>{
            console.log(err);
          });
    },
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
.inputDescription{
  width:200px;
  margin-left: 40px;
  margin-bottom: 30px;
}
.search{
  margin-left: 40px;
}
.aaa{
  width: 220px;
}
.my-pagination{
  margin: 20px;
}
.functionBtn{
  display: inline-block;
  height: 35px;
  width: 60px;
  margin-left: 5px;
  margin-bottom: 13px;
  margin-right: 10px;
}

</style>