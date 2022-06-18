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
          align="right"
          width="300">
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
              @click="handleTable(scope.$index, scope.row)">桌位管理
          </el-button>
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

    <!--图书馆座位管理抽屉-->
    <el-drawer
        title="管理图书馆桌位"
        :visible.sync="drawer"
        size="50%">
      <div style="margin: 20px">
        <el-row :gutter="20">
          <el-col :span="6">
            <el-input
                placeholder="请输入楼层"
                v-model="searchForm.floor"
                clearable>
            </el-input>
          </el-col>
          <el-col :span="6">
            <el-input
                placeholder="请输入区域"
                v-model="searchForm.area"
                clearable>
            </el-input>
          </el-col>
          <el-col :span="6">
            <el-input
                placeholder="请输入状态"
                v-model="searchForm.status"
                clearable>
            </el-input>
          </el-col>
          <el-col :span="6">
            <el-button icon="el-icon-search" circle @click="loadTableListByLibraryId()"></el-button>
          </el-col>
        </el-row>
        <div style="margin-top: 20px">
          <el-button type="primary" icon="el-icon-plus" style="margin-bottom: 20px; margin-left: 10px; width: 70px"
                     @click="addTable()"></el-button>
        </div>
        <el-table
            :data="tableTableData"
            style="width: 100%">
          <el-table-column
              label="所属楼层"
              width="100">
            <template slot-scope="scope">
              <span style="margin-left: 10px">{{ scope.row.floor }}</span>
            </template>
          </el-table-column>
          <el-table-column
              label="所属区域"
              width="100">
            <template slot-scope="scope">
              <span style="margin-left: 10px">{{ scope.row.area }}</span>
            </template>
          </el-table-column>
          <el-table-column
              label="桌子编号"
              width="150">
            <template slot-scope="scope">
              <span style="margin-left: 10px">{{ scope.row.name }}</span>
            </template>
          </el-table-column>
          <el-table-column
              label="座位数量"
              width="100">
            <template slot-scope="scope">
              <span style="margin-left: 10px">{{ scope.row.seat }}</span>
            </template>
          </el-table-column>
          <el-table-column
              label="状态"
              width="100">
            <template slot-scope="scope">
              <span style="margin-left: 10px" v-if="scope.row.status == 0">可用</span>
              <span style="margin-left: 10px" v-if="scope.row.status == 1">不可用</span>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button
                  size="mini"
                  @click="tableHandleEdit(scope.$index, scope.row)">编辑
              </el-button>
              <el-button
                  size="mini"
                  type="danger"
                  @click="tableHandleDelete(scope.$index, scope.row)">删除
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

        <!-- 添加图书馆座位对话框 -->
        <el-dialog title="添加图书馆桌位" :visible.sync="addTableDialogFormVisible" :append-to-body="true" width="40%">
          <el-form :model="addTableForm" :rules="addTableRules" ref="addTableRuleForm">
            <el-form-item label="所属图书馆" :label-width="formLabelWidth" prop="libraryId">
              <el-input v-model="addTableForm.libraryName" autocomplete="off" style="width: 220px" disabled></el-input>
            </el-form-item>
            <el-form-item label="所属楼层" :label-width="formLabelWidth" prop="floor">
              <el-input-number v-model="addTableForm.floor" :min="1" :max="maxFloor" label="请输入楼层" autocomplete="off" style="width: 220px"></el-input-number>
            </el-form-item>
            <el-form-item label="所属区域" :label-width="formLabelWidth" prop="area">
              <el-input v-model="addTableForm.area" autocomplete="off" style="width: 220px"></el-input>
            </el-form-item>
            <el-form-item label="桌子编号" :label-width="formLabelWidth" prop="name">
              <el-input v-model="addTableForm.name" autocomplete="off" style="width: 220px"></el-input>
            </el-form-item>
            <el-form-item label="是否可用" :label-width="formLabelWidth" prop="status">
              <el-radio v-model="addTableForm.status" label="0">是</el-radio>
              <el-radio v-model="addTableForm.status" label="1">否</el-radio>
            </el-form-item>
            <el-form-item label="座位数量" :label-width="formLabelWidth" prop="seat">
              <el-input-number v-model="addTableForm.seat" :min="0" :max="10" label="请输入座位数量" autocomplete="off" style="width: 220px"></el-input-number>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="closeDialog('addTableRuleForm')">取 消</el-button>
            <el-button type="primary" @click="addLibraryTable('addTableRuleForm')">确 定</el-button>
          </div>
        </el-dialog>

        <!-- 修改图书馆座位对话框 -->
        <el-dialog title="修改图书馆桌位" :visible.sync="editTableDialogFormVisible" :append-to-body="true" width="40%">
          <el-form :model="editTableForm" :rules="addTableRules" ref="editTableRuleForm">
            <el-form-item label="所属楼层" :label-width="formLabelWidth" prop="floor">
              <el-input-number v-model="editTableForm.floor" :min="1" :max="maxFloor" label="请输入楼层" autocomplete="off" style="width: 220px"></el-input-number>
            </el-form-item>
            <el-form-item label="所属区域" :label-width="formLabelWidth" prop="area">
              <el-input v-model="editTableForm.area" autocomplete="off" style="width: 220px"></el-input>
            </el-form-item>
            <el-form-item label="桌子编号" :label-width="formLabelWidth" prop="name">
              <el-input v-model="editTableForm.name" autocomplete="off" style="width: 220px"></el-input>
            </el-form-item>
            <el-form-item label="是否可用" :label-width="formLabelWidth" prop="status">
              <el-radio v-model="editTableForm.status" label="0">是</el-radio>
              <el-radio v-model="editTableForm.status" label="1">否</el-radio>
            </el-form-item>
            <el-form-item label="座位数量" :label-width="formLabelWidth" prop="seat">
              <el-input-number v-model="editTableForm.seat" :min="0" :max="10" label="请输入座位数量" autocomplete="off" style="width: 220px"></el-input-number>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="closeDialog('editTableRuleForm')">取 消</el-button>
            <el-button type="primary" @click="editLibraryTable('editTableRuleForm')">确 定</el-button>
          </div>
        </el-dialog>

      </div>
    </el-drawer>
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
      addTableDialogFormVisible: false,
      editTableDialogFormVisible: false,
      drawer: false,
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
      addTableRules: {
        libraryId: [
          {required: true, message: '图书馆必选', trigger: 'blur'},
        ],
        floor: [
          {required: true, message: '请输入所属楼层', trigger: 'blur'},
        ],
        area: [
          {required: true, message: '请输入所属区域', trigger: 'blur'},
        ],
        status: [
          {required: true, message: '请选择是否开放', trigger: 'blur'},
        ],
        name: [
          {required: true, message: '请输入座位编号', trigger: 'blur'},
        ],
        seat: [
          {required: true, message: '请输入座位数量', trigger: 'blur'},
        ],
      },
      nowLibraryId: '',
      tableTableData: [],
      searchForm: {
        floor: '',
        area: '',
        status: ''
      },
      pageNo: 1, //分页数据: 当前页数
      pageSize: 10, //分页数据: 显示条数
      total: 0, //分页数据: 总条数
      addTableForm: {
        libraryId: '',
        libraryName: '',
        status: '0',
        seat: 4,
        floor: '1',
        area: ''
      },
      maxFloor: '',
      editTableForm: {}
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
      this.addTableDialogFormVisible = false;
      this.editTableDialogFormVisible = false;
      this.$refs[formName].resetFields();
    },
    loadLibraryList() {
      this.$axios.post("/library/getLibraryList.do")
          .then(resp => {
            this.tableData = resp.data.data;
          }, err => {
            console.log(err);
          })
    },
    handleTable(index, row) {
      this.tableTableData = [];
      this.drawer = true;
      this.nowLibraryId = row.id;
      this.addTableForm.libraryName = row.name;
      this.addTableForm.libraryId = row.id;
      this.maxFloor = row.building.floor;
      this.pageNo = 1;
      this.loadTableListByLibraryId();
    },
    tableHandleEdit(index, row) {
      this.editTableDialogFormVisible = true;
      this.editTableForm = JSON.parse(JSON.stringify(row));
    },
    tableHandleDelete(index, row) {
      this.$confirm('此操作将永久删除该桌位, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("/library/deleteTable.do", {
          id: row.id
        }).then(resp => {
          this.$message({
            type: 'success',
            message: resp.data.message
          });
          this.loadTableListByLibraryId();
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
    handleSizeChange(val) {
      this.pageSize = val;
      this.loadTableListByLibraryId();
    },
    handleCurrentChange(val) {
      this.pageNo = val;
      this.loadTableListByLibraryId();
    },
    loadTableListByLibraryId() {
      this.$axios.post("/library/getTableListByLibraryId.do", {
        libraryId: this.nowLibraryId,
        floor: this.searchForm.floor,
        area: this.searchForm.area,
        status: this.searchForm.status,
        pageNo: (this.pageNo - 1) * this.pageSize,
        pageSize: this.pageSize
      }).then(resp => {
        this.tableTableData = resp.data.data;
      }, err => {
        console.log(err);
      });
      this.$axios.post("/library/getTableTotalByLibraryId.do", {
        libraryId: this.nowLibraryId,
        floor: this.searchForm.floor,
        area: this.searchForm.area,
        status: this.searchForm.status
      }).then(resp => {
        this.total = resp.data.data;
      }, err => {
        console.log(err);
      });
    },
    addTable() {
      this.addTableDialogFormVisible = true;
    },
    addLibraryTable(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post("/library/addLibraryTable.do", this.addTableForm)
              .then(resp => {
                this.$message({
                  message: resp.data.message,
                  type: 'success'
                });
                this.loadTableListByLibraryId();
              }, err => {
                console.log(err);
              })
          this.addTableDialogFormVisible = false;
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    editLibraryTable(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post("/library/editLibraryTable.do", this.editTableForm)
              .then(resp => {
                this.$message({
                  message: resp.data.message,
                  type: 'success'
                });
                this.loadTableListByLibraryId();
              }, err => {
                console.log(err);
              })
          this.editTableDialogFormVisible = false;
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  },
  created() {
    this.loadLibraryBuildingList();
    this.loadLibraryList();
  }
}
</script>

<style scoped>
.my-pagination {
  margin: 20px;
}
</style>
