<template>
  <div>
    <el-button type="primary"
               icon="el-icon-plus"
               style="margin-bottom: 20px; margin-left: 10px; width: 70px"
               @click="addDialogFormVisible = true"></el-button>
    <el-table
        :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%">
      <el-table-column
          type="index"
          width="50">
      </el-table-column>
      <el-table-column
          label="图书馆名"
          prop="name">
      </el-table-column>
      <el-table-column
          label="楼宇名"
          prop="building.name">
      </el-table-column>
      <el-table-column
          label="层数"
          width="100"
          prop="building.floor">
      </el-table-column>
      <el-table-column
          label="开馆时间"
          prop="openTime">
      </el-table-column>
      <el-table-column
          label="闭馆时间"
          prop="closeTime">
      </el-table-column>
      <el-table-column
          label="是否开放"
          prop="isOpen">
      </el-table-column>
      <el-table-column
          align="right">
        <template slot="header" slot-scope="scope">
          <el-input
              v-model="search"
              size="mini"
              placeholder="按照图书馆名关键字搜索"
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

    <!-- 添加图书馆模态窗口 -->
    <el-dialog title="添加图书馆" :visible.sync="addDialogFormVisible" width="30%">
      <el-form :model="addForm" :rules="rules" ref="addRuleForm">
        <el-form-item label="图书馆名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="addForm.name" autocomplete="off" style="width: 220px"></el-input>
        </el-form-item>
        <el-form-item label="开馆时间" :label-width="formLabelWidth" prop="openTime">
          <el-time-picker
              v-model="addForm.openTime"
              value-format="HH:mm:ss"
              :picker-options="{
                selectableRange: '00:00:00 - 23:59:59'
              }"
              placeholder="任意时间点">
          </el-time-picker>
        </el-form-item>
        <el-form-item label="闭馆时间" :label-width="formLabelWidth" prop="closeTime">
          <el-time-picker
              v-model="addForm.closeTime"
              value-format="HH:mm:ss"
              :picker-options="{
                selectableRange: '00:00:00 - 23:59:59'
              }"
              placeholder="任意时间点">
          </el-time-picker>
        </el-form-item>
        <el-form-item label="是否开放" :label-width="formLabelWidth" prop="isOpen">
          <el-radio v-model="addForm.isOpen" label="是">是</el-radio>
          <el-radio v-model="addForm.isOpen" label="否">否</el-radio>
        </el-form-item>
        <el-form-item label="关联楼宇" :label-width="formLabelWidth" prop="buildingId">
          <el-select v-model="addForm.buildingId" placeholder="请选择楼宇">
            <el-option :label="item.value" :value="item.id" v-for="item in libraryBuildingList"
                       :key="item.name"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDialog('addRuleForm')">取 消</el-button>
        <el-button type="primary" @click="addLibrary('addRuleForm')">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 编辑图书馆对话框 -->
    <el-dialog title="图书馆信息修改" :visible.sync="editDialogFormVisible" width="40%">
      <el-form :model="editForm" :rules="rules" ref="editRuleForm">
        <el-form-item label="图书馆名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="editForm.name" autocomplete="off" style="width: 220px"></el-input>
        </el-form-item>
        <el-form-item label="开馆时间" :label-width="formLabelWidth" prop="openTime">
          <el-time-picker
              v-model="editForm.openTime"
              value-format="HH:mm:ss"
              :picker-options="{
                selectableRange: '00:00:00 - 23:59:59'
              }"
              placeholder="任意时间点">
          </el-time-picker>
        </el-form-item>
        <el-form-item label="闭馆时间" :label-width="formLabelWidth" prop="closeTime">
          <el-time-picker
              v-model="editForm.closeTime"
              value-format="HH:mm:ss"
              :picker-options="{
                selectableRange: '00:00:00 - 23:59:59'
              }"
              placeholder="任意时间点">
          </el-time-picker>
        </el-form-item>
        <el-form-item label="是否开放" :label-width="formLabelWidth" prop="isOpen">
          <el-radio v-model="editForm.isOpen" label="是">是</el-radio>
          <el-radio v-model="editForm.isOpen" label="否">否</el-radio>
        </el-form-item>
        <el-form-item label="关联楼宇" :label-width="formLabelWidth" prop="buildingId">
          <el-select v-model="editForm.buildingId" placeholder="请选择楼宇">
            <el-option :label="item.value" :value="item.id" v-for="item in libraryBuildingList"
                       :key="item.name"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDialog('editRuleForm')">取 消</el-button>
        <el-button type="primary" @click="editLibrary('editRuleForm')">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "adminLibraryManage",
  data() {
    return {
      libraryBuildingList: [],
      tableData: [],
      search: '',
      addDialogFormVisible: false,
      editDialogFormVisible: false,
      addForm: {
        name: '',
        openTime: '',
        closeTime: '',
        isOpen: '是',
        buildingId: ''
      },
      editForm: {},
      formLabelWidth: '120px',
      rules: {
        name: [
          {required: true, message: '请输入图书馆名', trigger: 'blur'},
        ],
        openTime: [
          {required: true, message: '请输入开馆时间', trigger: 'blur'},
        ],
        closeTime: [
          {required: true, message: '请输入闭馆时间', trigger: 'blur'},
        ],
        isOpen: [
          {required: true, message: '请选择是否开放', trigger: 'blur'},
        ],
        buildingId: [
          {required: true, message: '请输入楼宇类型', trigger: 'blur'},
        ],
      },
    }
  },
  methods: {
    handleEdit(index, row) {
      this.editDialogFormVisible = true;
      this.editForm = JSON.parse(JSON.stringify(row));
    },
    handleDelete(index, row) {
      this.$confirm('此操作将永久删除该图书馆, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("/library/deleteLibrary.do", {
          id: row.id
        }).then(resp => {
          this.$message({
            type: 'success',
            message: resp.data.message
          });
          this.loadLibraryList();
        }, err => {
          console.log(err);
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    editLibrary(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post("/library/editLibrary.do", this.editForm)
              .then(resp => {
                this.$message({
                  message: resp.data.message,
                  type: 'success'
                });
                this.loadLibraryList();
              }, err => {
                console.log(err);
              });
          this.editDialogFormVisible = false;
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    addLibrary(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post("/library/addLibrary.do", this.addForm)
              .then(resp => {
                this.$message({
                  message: resp.data.message,
                  type: 'success'
                });
                this.loadLibraryList();
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
    loadLibraryBuildingList() {
      this.$axios.post("/building/getBuildingValueListByType.do", {
        type: "图书馆"
      }).then(resp => {
        this.libraryBuildingList = resp.data.data;
        // console.log(resp.data.data);
      }, err => {
        console.log(err);
      });
    },
    closeDialog(formName) {
      this.editDialogFormVisible = false;
      this.addDialogFormVisible = false;
      this.$refs[formName].resetFields();
    },
    loadLibraryList() {
      this.$axios.post("/library/getLibraryList.do")
          .then(resp => {
            this.tableData = resp.data.data;
          }, err => {
            console.log(err);
          })
    }
  },
  created() {
    this.loadLibraryBuildingList();
    this.loadLibraryList();
  }
}
</script>

<style scoped>

</style>
