<template>
  <div>
    <el-button type="primary" icon="el-icon-plus" style="margin-bottom: 20px; margin-left: 10px; width: 70px"
               @click="addDialogFormVisible = true"></el-button>
    <el-table
        :data="tableData.filter(data => !search || data.doorNo.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%"
        :default-sort = "{prop: 'building', order: 'building'}">
      <el-table-column
          prop="building.name"
          label="楼宇"
          width="140"
          sortable>
      </el-table-column>
      <el-table-column
          prop="doorNo"
          label="门牌号"
          width="120"
          sortable>
      </el-table-column>
      <el-table-column
          prop="size"
          label="寝室大小"
          width="120"
          sortable>
      </el-table-column>
      <el-table-column
          prop="haveWc"
          label="是否独卫"
          width="120"
          sortable>
      </el-table-column>
      <el-table-column
          prop="water"
          label="水费余额"
          width="120"
          sortable>
      </el-table-column>
      <el-table-column
          prop="power"
          label="电费余额"
          width="120"
          sortable>
      </el-table-column>
      <el-table-column
          prop="remark"
          label="备注"
          width="400">
      </el-table-column>
      <el-table-column label="操作">
        <template slot="header" slot-scope="scope">
          <el-input
              v-model="search"
              size="mini"
              placeholder="按照楼宇名关键字搜索"
              @change="handleSearch(scope.$index, scope.row)"/>
        </template>
        <template slot-scope="scope">
          <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--  添加寝室模态窗口  -->
    <el-dialog title="添加寝室" :visible.sync="addDialogFormVisible">
      <el-form :model="addForm" :rules="rules" ref="addRuleForm">
        <el-form-item label="所属楼宇" :label-width="formLabelWidth" prop="building">
          <el-autocomplete
              class="inline-input"
              v-model="addForm.building"
              :fetch-suggestions="querySearch"
              placeholder="请输入内容"
              @select="handleSelect"
          ></el-autocomplete>
        </el-form-item>
        <el-form-item label="门牌号" :label-width="formLabelWidth" prop="doorNo">
          <el-input v-model="addForm.doorNo" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="寝室大小" :label-width="formLabelWidth" prop="size">
          <el-input-number v-model="addForm.size" autocomplete="off" controls-position="right" :min="1" :max="99"></el-input-number>
        </el-form-item>
        <el-form-item label="是否独卫" :label-width="formLabelWidth" prop="haveWc">
          <el-radio-group v-model="addForm.haveWc" size="small">
            <el-radio label="是">是</el-radio>
            <el-radio label="否">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="水费余额" :label-width="formLabelWidth" prop="water">
          <el-input-number v-model="addForm.water" autocomplete="off" controls-position="right" :controls="false"></el-input-number>
        </el-form-item>
        <el-form-item label="电费余额" :label-width="formLabelWidth" prop="power">
          <el-input-number v-model="addForm.power" autocomplete="off" controls-position="right" :controls="false"></el-input-number>
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth" prop="remark">
          <el-input v-model="addForm.remark" autocomplete="off" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('addRuleForm')">确 定</el-button>
      </div>
    </el-dialog>

    <!--  修改寝室模态窗口  -->
    <el-dialog title="添加寝室" :visible.sync="editDialogFormVisible">
      <el-form :model="editForm" :rules="rules" ref="editRuleForm">
        <el-form-item label="所属楼宇" :label-width="formLabelWidth" prop="building">
          <el-autocomplete
              class="inline-input"
              v-model="editForm.building"
              :fetch-suggestions="querySearch"
              placeholder="请输入内容"
              @select="handleSelect"
          ></el-autocomplete>
        </el-form-item>
        <el-form-item label="门牌号" :label-width="formLabelWidth" prop="doorNo">
          <el-input v-model="editForm.doorNo" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="寝室大小" :label-width="formLabelWidth" prop="size">
          <el-input-number v-model="editForm.size" autocomplete="off" controls-position="right" :min="1" :max="99"></el-input-number>
        </el-form-item>
        <el-form-item label="是否独卫" :label-width="formLabelWidth" prop="haveWc">
          <el-radio-group v-model="editForm.haveWc" size="small">
            <el-radio label="是">是</el-radio>
            <el-radio label="否">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="水费余额" :label-width="formLabelWidth" prop="water">
          <el-input-number v-model="editForm.water" autocomplete="off" controls-position="right" :controls="false"></el-input-number>
        </el-form-item>
        <el-form-item label="电费余额" :label-width="formLabelWidth" prop="power">
          <el-input-number v-model="editForm.power" autocomplete="off" controls-position="right" :controls="false"></el-input-number>
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth" prop="remark">
          <el-input v-model="editForm.remark" autocomplete="off" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="editDorm('editRuleForm')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "dormManage",
  data (){
    return {
      tableData: [],
      addDialogFormVisible: false,
      editDialogFormVisible: false,
      formLabelWidth: '120px',
      addForm: {
        building: '',
        buildingId: '',
        doorNo: '',
        size: '',
        haveWc: '',
        water: '',
        power: '',
        remark: ''
      },
      editForm: {},
      restaurants: [],
      search: '',
      rules: {
        building: [
          {required: true, message: '请选择所属楼宇', trigger: 'blur'},
        ],
        doorNo: [
          {required: true, message: '请输入寝室门牌号', trigger: 'blur'},
        ],
        size: [
          {required: true, message: '请输入可住人数', trigger: 'blur'},
        ],
        haveWc: [
          {required: true, message: '请输入是否独立卫生间', trigger: 'blur'},
        ],
        water: [
          {required: true, message: '请输入水费', trigger: 'blur'},
        ],
        power: [
          {required: true, message: '请输入电费', trigger: 'blur'},
        ],
        remark: []
      }
    }
  },
  methods: {
    handleEdit(index, row) {
      this.editDialogFormVisible = true;
      this.editForm = JSON.parse(JSON.stringify(row));
      this.editForm.buildingId = JSON.parse(JSON.stringify(row.building.id));
      this.editForm.building = JSON.parse(JSON.stringify(row.building.name));
    },
    handleDelete(index, row) {
      this.$confirm('此操作将永久删除该寝室, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("/dorm/deleteDorm.do", {
          id: row.id
        }).then(resp=>{
          this.$message({
            type: 'success',
            message: resp.data.message
          });
          this.loadDormList();
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
    querySearch(queryString, cb) {
      var restaurants = this.restaurants;
      var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    loadAll() {
      // 加载楼宇列表值
      this.$axios.post("/building/getDormBuildingValueList.do")
          .then(resp=>{
            // 设置楼宇下拉列表项
            this.restaurants = resp.data.data;
            // 设置楼宇rules验证枚举
            var rulesEnum = [];
            for (let temp in resp.data.data) {
              rulesEnum.push(resp.data.data[temp].value);
            }
            var t = {type:'enum', enum: rulesEnum, message: '楼宇必须为楼宇列表中的值'};
            this.rules.building.push(t);
          }, err=>{
            console.log(err);
          })
    },
    handleSelect(item) {
      //设置对应楼宇id
      this.addForm.buildingId = item.id;
      this.editForm.buildingId = item.id;
    },
    submitForm(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post("/dorm/addDorm.do", this.addForm)
              .then(resp=>{
                this.$message({
                  message: resp.data.message,
                  type: 'success'
                });
                this.addDialogFormVisible = false;
                this.loadDormList();
              }, err=>{
                console.log(err);
              });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    editDorm(formName){
      this.$refs[formName].validate((valid) => {
        console.log(this.editForm)
        if (valid) {
          this.$axios.post("/dorm/editDorm.do", this.editForm)
              .then(resp=>{
                this.$message({
                  message: resp.data.message,
                  type: 'success'
                });
                this.editDialogFormVisible = false;
                this.loadDormList();
              }, err=>{
                console.log(err);
              });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    loadDormList(){
      this.$axios.post("/dorm/getDormList.do")
          .then(resp=>{
            this.tableData = resp.data.data;
          }, err=>{
            console.log(err);
          });
    },
  },
  mounted() {
    this.loadAll();
    this.loadDormList();
  }
}
</script>

<style scoped>

</style>