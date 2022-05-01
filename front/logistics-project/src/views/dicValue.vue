<template>
  <div>
    <div style="height: 60px; margin-left: -35px">
      <el-input v-model="input.inputValue" placeholder="请输入value" class="inputName"></el-input>
      <el-input v-model="input.inputOrderNo" placeholder="请输入orderNo" class="inputName"></el-input>
      <el-input v-model="input.inputCode" placeholder="请输入code" class="inputCode"></el-input>
      <el-button type="primary" icon="el-icon-search" @click="searchBtn" class="search">搜索</el-button>
    </div>
    <el-popconfirm
        title="确定删除吗？">
      <el-button
          size="mini"
          type="primary"
          icon="el-icon-delete"
          slot="reference"
          @click="delBtn"
          class="functionBtn"></el-button>
    </el-popconfirm>
    <el-button
        size="mini"
        type="primary"
        icon="el-icon-plus"
        @click="addDicValueBtn"
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
          prop="value"
          label="value"
          width="230">
        <template slot-scope="scope">
          <span>{{ scope.row.value }}</span>
        </template>
      </el-table-column>

      <el-table-column
          prop="orderNo"
          label="order_no"
          width="260">
        <template slot-scope="scope">
          <span>{{ scope.row.orderNo }}</span>
        </template>
      </el-table-column>

      <el-table-column
          prop="typeCode"
          label="type_code"
          width="250">
        <template slot-scope="scope">
          <span>{{ scope.row.typeCode }}</span>
        </template>
      </el-table-column>

      <el-table-column
          prop="zip"
          label="操作"
          width="85">
        <template slot-scope="scope">
          <el-button
              size="mini"
              @click="updateBtn(scope.$index,scope.row)">修 改
          </el-button>
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
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          background>
      </el-pagination>
    </div>
    <!--  修改模态窗口-->
    <el-dialog title="修改" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="value" :label-width="formLabelWidth">
          <el-input v-model="form.value" autocomplete="off" class="aaa"></el-input>
        </el-form-item>
        <el-form-item label="order_no" :label-width="formLabelWidth">
          <el-input v-model="form.orderNo" autocomplete="off" class="aaa"></el-input>
        </el-form-item>
        <el-form-item label="code" :label-width="formLabelWidth">
          <el-select v-model="selectValue" placeholder="请选择code">
            <el-option
                v-for="item in options"
                :key="item"
                :label="item"
                :value="item">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateSureBtn">确 定</el-button>
      </div>
    </el-dialog>


    <!--添加模态窗口-->
    <el-dialog title="添 加" :visible.sync="dialogFormAddDicValue">
      <el-form :model="add">
        <el-form-item label="value" :label-width="formLabelWidth">
          <el-input v-model="add.value" autocomplete="off" class="aaa"></el-input>
        </el-form-item>
        <el-form-item label="orderNo" :label-width="formLabelWidth">
          <el-input v-model="add.orderNo" autocomplete="off" class="aaa"></el-input>
        </el-form-item>
        <!--        <el-form-item label="描述" :label-width="formLabelWidth">
                  <el-input v-model="add.description" autocomplete="off" class="aaa"></el-input>
                </el-form-item>-->
        <el-select v-model="selectValue" placeholder="请选择type_code">
          <el-option
              v-for="item in options"
              :key="item"
              :label="item"
              :value="item">
          </el-option>
        </el-select>
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
  name: "dicValue",
  data() {
    return {
      //多选
      multipleSelection: [],
      idArray: [],
      tableData: [{
        id: '',
        value: '',
        orderNo: '',
        typeCode: '',
      }],
      //模态窗口
      dialogFormAddDicValue: false,
      dialogFormVisible: false,
      form: {
        id: '',
        value: '',
        orderNo: '',
        typeCode: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      add: {
        id: '',
        value: '',
        typeCode: '',
        orderNo: ''
      },
      formLabelWidth: '120px',
      pageNo: 1, //当前页数
      pageSize: 10, //显示条数
      total: 0, //总条数
      input: {
        inputCode: '',
        inputValue: '',
        inputOrderNo: ''
      },
      //修改窗口-下拉菜单
      options: [],
      selectValue: ''
    }
  },
  methods: {
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
    searchBtn() {
      this.$axios.post("/admin/getDicVByCV.do",
          {
            pageNo: (this.pageNo - 1) * this.pageSize,
            pageSize: this.pageSize,
            value: this.input.inputValue,
            typeCode: this.input.inputCode,
            orderNo: this.input.inputOrderNo
          })
          .then(resp => {
            console.log(resp);
            this.total = resp.data.data.total;
            this.tableData = resp.data.data.list;
          }, err => {
            console.log(err);
          });
    },
    //加载表格数据
    getDicValueList() {
      //pageNo第几页
      //pageSize 多少条一页
      this.$axios.post("/admin/getDicVByCV.do",
          {
            pageNo: (this.pageNo - 1) * this.pageSize,
            pageSize: this.pageSize
          })
          .then(resp => {
            console.log(resp.data);
            //alert(this.pageSize);
            this.total = resp.data.data.total;
            this.tableData = resp.data.data.list;
          }, err => {
            console.log(err);
          });

    },
    //显示几条
    handleSizeChange(val) {
      this.pageSize = val;
      this.getDicValueList();
    },
    //当前页数
    handleCurrentChange(val) {
      this.pageNo = val;
      this.getDicValueList();
    },
    //修改触发模态窗口
    updateBtn(index, row) {
      this.dialogFormVisible = true;
      this.form.id = row.id;
      this.form.value = row.value;
      this.form.orderNo = row.orderNo;
      this.selectValue = row.typeCode;
      //this.form.typeCode=row.typeCode;
      //下拉菜单加载
      this.$axios.post("/admin/getDicTL.do",
          {})
          .then(resp => {
            console.log(resp.data);
            //this.tableData=resp.data.data;
            var list = [];
            for (let i = 0; i < resp.data.data.length; i++) {
              list[i] = resp.data.data[i].code;
              //alert(list[i])
            }
            this.options = list;

            this.getDicValueList();
          }, err => {
            console.log(err);
          });
    },
    //修改-确认按钮
    updateSureBtn() {
      if (this.form.value == '') {
        this.$notify({
          title: '偏移',
          message: 'value不能为空',
          offset: 100
        });
        return false;
      } else if (this.form.orderNo == '') {
        this.$notify({
          title: '偏移',
          message: 'orderNo不能为空',
          offset: 100
        });
        return false;
      } else {
        this.$axios.post("/admin/updateDicV.do",
            {
              value: this.form.value,
              orderNo: this.form.orderNo,
              typeCode: this.selectValue,
              id: this.form.id
            })
            .then(resp => {
              this.getDicValueList()
              console.log(resp.data);
              //this.tableData=resp.data.data;
            }, err => {
              console.log(err);
            });
        this.dialogFormVisible = false
      }


    },
    //添加按钮
    addDicValueBtn() {
      this.dialogFormAddDicValue = true;
      this.$axios.post("/admin/getDicTL.do",
          {})
          .then(resp => {
            console.log(resp.data);
            //this.tableData=resp.data.data;
            var list = [];
            for (let i = 0; i < resp.data.data.length; i++) {
              list[i] = resp.data.data[i].code;
              //alert(list[i])
            }
            this.options = list;
            this.getDicValueList();
          }, err => {
            console.log(err);
          });
    },
    //添加确定按钮
    addSureBtn() {
      if (this.add.value == '') {
        this.$notify({
          title: '偏移',
          message: 'value不能为空',
          offset: 100
        });
        return false;
      } else if (this.add.orderNo == '') {
        this.$notify({
          title: '偏移',
          message: 'orderNo不能为空',
          offset: 100
        });
        return false;
      } else if (this.selectValue == '') {
        this.$notify({
          title: '偏移',
          message: '请选择学院',
          offset: 100
        });
        return false;
      } else {
        this.$axios.post("/admin/setDicV.do",
            {
              value: this.add.value,
              orderNo: this.add.orderNo,
              typeCode: this.selectValue,
            })
            .then(resp => {
              this.add.value = '',
                  this.add.orderNo = '',
                  this.selectValue = ''
              console.log(resp.data);
              //this.tableData=resp.data.data;
              this.getDicValueList();
            }, err => {
              console.log(err);
            });
        this.dialogFormAddDicValue = false;
      }

    },
    //删除
    selection(val) {
      this.multipleSelection = val;
      console.log(val);
    },
    delBtn() {
      for (let i = 0; i < this.multipleSelection.length; i++) {
        this.idArray[i] = this.multipleSelection[i].id;
      }
      console.log(this.idArray);
      if (this.idArray.length != 0) {
        this.$axios.post("/admin/delDicV.do",
            //array:this.dialogFormAddDicValue
            this.idArray
        ).then(resp => {
          console.log(resp.data);
          //this.tableData=resp.data.data;
          this.getDicValueList();
        }, err => {
          console.log(err);
        });
      }else{
        this.$message({
          message: '请选择要删除的数据字典值',
          type: 'warning'
        });
      }

    }
  },
  created() {
    this.getDicValueList();
    //this.loadCollege();
  }
}
</script>

<style scoped>
.inputCode {
  width: 200px;
  margin-left: 40px;
}

.inputName {
  width: 200px;
  margin-left: 40px;
  margin-bottom: 30px;
}

.search {
  margin-left: 40px;
}

.aaa {
  width: 220px;
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