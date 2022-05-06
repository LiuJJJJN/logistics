<template>
  <div>
    <el-button type="primary" icon="el-icon-plus" style="margin-bottom: 20px; margin-left: 10px; width: 70px"
               @click="addDialogFormVisible = true"></el-button>
    <el-table
        :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%">
      <el-table-column
          label="序号"
          prop="orderNo">
      </el-table-column>
      <el-table-column
          label="楼宇名"
          prop="name">
      </el-table-column>
      <el-table-column
          label="层数"
          prop="floor">
      </el-table-column>
      <el-table-column
          label="每层房间数"
          prop="rooms">
      </el-table-column>
      <el-table-column
          label="类型"
          prop="type">
      </el-table-column>
      <el-table-column
          align="right">
        <template slot="header" slot-scope="scope">
          <el-input
              v-model="search"
              size="mini"
              placeholder="按照楼宇名关键字搜索"
              @change="handleSearch(scope.$index, scope.row)"/>
        </template>
        <template slot-scope="scope">
          <el-button
              size="small"
              @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button
              slot="reference"
              size="small"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加楼宇模态窗口 -->
    <el-dialog title="添加楼宇" :visible.sync="addDialogFormVisible">
      <el-form :model="addForm" :rules="rules" ref="addRuleForm">
        <el-form-item label="序号" :label-width="formLabelWidth" prop="orderNo">
          <el-input-number :controls="false" style="width: 320px" min="1" max="999" controls-position="right"
                           v-model="addForm.orderNo" autocomplete="off"></el-input-number>
        </el-form-item>
        <el-form-item label="楼宇名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="addForm.name" autocomplete="off" style="width: 320px"></el-input>
        </el-form-item>
        <el-form-item label="层数" :label-width="formLabelWidth" prop="floor">
          <el-input-number :controls="false" style="width: 320px" min="1" max="999" controls-position="right"
                           v-model="addForm.floor" autocomplete="off"></el-input-number>
        </el-form-item>
        <el-form-item label="每层房间数" :label-width="formLabelWidth" prop="rooms">
          <el-input-number :controls="false" style="width: 320px" min="1" max="999" controls-position="right"
                           v-model="addForm.rooms" autocomplete="off"></el-input-number>
        </el-form-item>
        <el-form-item label="楼宇类型" :label-width="formLabelWidth" prop="type">
          <el-select v-model="addForm.type" placeholder="请选择楼宇类型">
            <el-option :label="item.value" :value="item.value" v-for="item in buildTypeList"
                       :key="item.name"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeBuildingDialog('addRuleForm')">取 消</el-button>
        <el-button type="primary" @click="addBuilding('addRuleForm')">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 编辑楼宇模态窗口 -->
    <el-dialog title="楼宇信息修改" :visible.sync="editDialogFormVisible">
      <el-form :model="editForm" :rules="rules" ref="editRuleForm">
        <el-form-item label="序号" :label-width="formLabelWidth" prop="orderNo">
          <el-input-number :controls="false" style="width: 320px" min="1" max="999" controls-position="right"
                           v-model="editForm.orderNo" autocomplete="off"></el-input-number>
        </el-form-item>
        <el-form-item label="楼宇名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="editForm.name" autocomplete="off" style="width: 320px"></el-input>
        </el-form-item>
        <el-form-item label="层数" :label-width="formLabelWidth" prop="floor">
          <el-input-number :controls="false" style="width: 320px" min="1" max="999" controls-position="right"
                           v-model="editForm.floor" autocomplete="off"></el-input-number>
        </el-form-item>
        <el-form-item label="每层房间数" :label-width="formLabelWidth" prop="rooms">
          <el-input-number :controls="false" style="width: 320px" min="1" max="999" controls-position="right"
                           v-model="editForm.rooms" autocomplete="off"></el-input-number>
        </el-form-item>
        <el-form-item label="楼宇类型" :label-width="formLabelWidth" prop="type">
          <el-select v-model="editForm.type" placeholder="请选择楼宇类型">
            <el-option :label="item.value" :value="item.value" v-for="item in buildTypeList"
                       :key="item.name"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeBuildingDialog('editRuleForm')">取 消</el-button>
        <el-button type="primary" @click="editBuilding('editRuleForm')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "buildingManage",
  data() {
    return {
      buildTypeList: [],
      tableData: [],
      search: '',
      addDialogFormVisible: false,
      editDialogFormVisible: false,
      addForm: {
        orderNo: '',
        name: '',
        floor: '',
        rooms: '',
        type: ''
      },
      editForm: {},
      formLabelWidth: '120px',
      rules: {
        orderNo: [
          {required: true, message: '请输入排序依据', trigger: 'blur'},
        ],
        name: [
          {required: true, message: '请输入楼宇名', trigger: 'blur'},
        ],
        floor: [
          {required: true, message: '请输入楼层数', trigger: 'blur'},
        ],
        rooms: [
          {required: true, message: '请输入每层房间数', trigger: 'blur'},
        ],
        type: [
          {required: true, message: '请输入楼宇类型', trigger: 'blur'},
        ],
      }
    }
  },
  methods: {
    handleEdit(index, row) {
      this.editDialogFormVisible = true;
      this.editForm = row;
    },
    handleDelete(index, row) {
      this.$confirm('此操作将永久删除该楼宇, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("/building/deleteBuilding.do", {
          id: row.id
        }).then(resp=>{
          this.$message({
              type: 'success',
              message: resp.data.message
            });
          this.loadBuildingList();
          }, err=>{
          console.log(err);
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    editBuilding(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post("/building/editBuilding.do", this.editForm)
              .then(resp=>{
                this.$message({
                  message: resp.data.message,
                  type: 'success'
                });
                this.loadBuildingList();
              }, err=>{
                console.log(err);
              });
          this.editDialogFormVisible = false;
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    addBuilding(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post("/building/addBuilding.do", this.addForm)
              .then(resp => {
                this.$message({
                  message: resp.data.message,
                  type: 'success'
                });
                this.loadBuildingList();
              }, err => {
                console.log(err);
              })
          this.addDialogFormVisible = false;
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    loadBuildingTypeList() {
      this.$axios.post("/dic/getBuildingTypeList.do")
          .then(resp => {
            this.buildTypeList = resp.data.data;
            // console.log(resp.data.data);
          }, err => {
            console.log(err);
          });
    },
    closeBuildingDialog(formName) {
      this.editDialogFormVisible = false;
      this.addDialogFormVisible = false;
      this.$refs[formName].resetFields();
    },
    loadBuildingList(){
      this.$axios.post("/building/getBuildingList.do")
          .then(resp=>{
            this.tableData = resp.data.data;
          }, err=>{
            console.log(err);
          })
    }
  },
  created() {
    this.loadBuildingTypeList();
    this.loadBuildingList();
  }
}
</script>

<style scoped>

</style>