<template>
  <div>
    <!--添加-->
    <el-popover
        placement="right"
        width="400"
        trigger="click">
      <span style="font-size: 13px;font-family: 宋体">添加教室信息</span>
      <div style="margin-top: 10px;margin-left: 30px">
      <el-form :inline="true" :model="addFormBuilding" class="demo-form-inline">
        <el-form-item label="所属楼宇">
          <el-select v-model="selectBuilding.id" :placeholder="请选择教学楼" >
            <el-option
                v-for="item in addFormBuilding"
                :key="item.id"
                :label="item.value"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="addFormElect" class="demo-form-inline">
        <el-form-item label="电源情况">
          <el-select v-model="addFormElect.region" placeholder="电源情况">
            <el-option label="有电源" value="1"></el-option>
            <el-option label="无电源" value="0"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="addFormUse" class="demo-form-inline">
        <el-form-item label="使用情况">
          <el-select v-model="addFormUse.region" placeholder="使用情况">
            <el-option label="在使用" value="1"></el-option>
            <el-option label="未使用" value="0"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="addFormOpen" class="demo-form-inline">
        <el-form-item label="开门情况">
          <el-select v-model="addFormOpen.region" placeholder="开门情况">
            <el-option label="已开门" value="1"></el-option>
            <el-option label="未开门" value="0"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="addFormClean" class="demo-form-inline">
        <el-form-item label="打扫情况">
          <el-select v-model="addFormClean.region" placeholder="打扫情况">
            <el-option label="已打扫" value="1"></el-option>
            <el-option label="未打扫" value="0"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
        <el-form :inline="true" model="" class="demo-form-inline">
          <el-form-item label="门牌号">
            <el-input v-model="addClassroomNo" style="width: 100px"></el-input>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="addSure" style="margin-left: 120px">确 定</el-button>
      </div>
      <el-button slot="reference" type="primary" @click="addClassroomInf">添 加</el-button>
    </el-popover>
    <!--表格-->
    <el-table
        :data="tableData"
        style="width: 100%;margin-top:15px">
      <el-table-column
          prop="build"
          label="所属楼宇"
          width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.buildingName }}</span>
        </template>
      </el-table-column>
      <el-table-column
          prop="roomNo"
          label="门牌号"
          width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.classroomRoomNo }}</span>
        </template>
      </el-table-column>
      <el-table-column
          prop="electInf"
          label="电源情况"
          width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.classroomElectInf==1?'有电源':'无电源' }}</span>
        </template>
      </el-table-column>
      <el-table-column
          prop="userInf"
          label="使用情况"
          width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.classroomIsUse==1?'在使用':'未使用' }}</span>
        </template>
      </el-table-column>
      <el-table-column
          prop="open"
          label="开门情况"
          width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.classroomIsOpen==1?'已开门':'未开门' }}</span>
        </template>
      </el-table-column>
      <el-table-column
          prop="clean"
          label="打扫情况"
          width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.classroomIsClean==1?'已打扫':'未打扫' }}</span>
        </template>
      </el-table-column>
      <el-table-column
          prop=""
          label=""
          width="180">
        <template slot-scope="scope">
          <el-button
              size="small"
              @click="handleEdit(scope.$index, scope.row)">编 辑
          </el-button>
          <el-button
              slot="reference"
              size="small"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删 除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--编辑模态窗口-->
    <el-dialog title="编辑教室" :visible.sync="editDialogFormVisible">
      <div style="margin-left:200px">
        <div>
          <span>当前：</span>
          <el-tag>{{ this.editBuildingDialog_building }}</el-tag>
          <el-tag type="success" style="margin-left: 5px">{{ this.editBuildingDialog_classroom }}</el-tag>
        </div>
        <el-form :inline="true" :model="formElect" class="demo-form-inline" style="margin-top: 20px">
          <el-form-item label="电源情况">
            <el-select v-model="formElect.region" placeholder="电源情况" style="width: 120px">
              <el-option label="有电源" value="1"></el-option>
              <el-option label="无电源" value="0"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <el-form :inline="true" :model="formUse" class="demo-form-inline">
          <el-form-item label="使用情况">
            <el-select v-model="formUse.region" placeholder="使用情况" style="width: 120px">
              <el-option label="在使用" value="1"></el-option>
              <el-option label="未使用" value="0"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <el-form :inline="true" :model="formOpen" class="demo-form-inline">
          <el-form-item label="开门情况">
            <el-select v-model="formOpen.region" placeholder="开门情况" style="width: 120px">
              <el-option label="已开门" value="1"></el-option>
              <el-option label="未开门" value="0"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <el-form :inline="true" :model="formClean" class="demo-form-inline">
          <el-form-item label="打扫情况">
            <el-select v-model="formClean.region" placeholder="打扫情况" style="width: 120px">
              <el-option label="已打扫" value="1"></el-option>
              <el-option label="未打扫" value="0"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </div>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancel">取 消</el-button>
          <el-button type="primary" @click="sure">确 定</el-button>
        </div>
    </el-dialog>
    <!--分页-->
    <div class="block">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNo"
          :page-sizes="[5, 10, 20, 30]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "adminClassroomManage",
  data(){
    return{
      tableData: [],
      pageNo:1,
      pageSize: 10,
      total: 0,
      editDialogFormVisible:false,
      editBuildingDialog_building:'',
      editBuildingDialog_classroom:'',
      editClassroomId:'',
      formElect: {
        user: '',
        region: ''
      },
      formUse: {
        user: '',
        region: ''
      },
      formOpen:{
        user: '',
        region: ''
      },
      formClean:{
        user: '',
        region: ''
      },
      //添加中的form
      addFormBuilding:[
        {
          id:'',
          value:''
        }
      ],
      selectBuilding:{
        id: '',
        value:''
      },
      addFormElect:{
        user: '',
        region: ''
      },
      addFormUse:{
        user: '',
        region: ''
      },
      addFormOpen:{
        user: '',
        region: ''
      },
      addFormClean:{
        user: '',
        region: ''
      },
      addClassroomNo:'',
      selectId: ''
      //
    }
  },
  methods: {
    handleSizeChange(val) {
      this.pageSize=val;
      this.getClassroomList();
    },
    handleCurrentChange(val) {
      this.pageNo=val;
      this.getClassroomList();
    },
    //打开页码加载
    getClassroomList(){
      this.$axios.post("/classroom/getClassroomL.do",
          {
            pageNo: (this.pageNo - 1) * this.pageSize,
            pageSize: this.pageSize,
          })
          .then(resp => {
            this.total = resp.data.data.total;
            this.tableData = resp.data.data.list;
          }, err => {
            console.log(err);
          });
    },
    //添加按钮
    addClassroomInf(){
      this.$axios.post("/classroom/getBuildingList.do",
          {
          })
          .then(resp => {
            this.addFormBuilding=resp.data.data;
            console.log(resp);
          }, err => {
            console.log(err);
          });
    },
    //添加确定按钮
    addSure(){
      alert(this.selectBuilding.id);
      this.$axios.post("/classroom/addClassroomInf.do",
          {
            buildingId:this.selectBuilding.id,
            electInf:this.addFormElect.region,
            isUse:this.addFormUse.region,
            isOpen:this.addFormOpen.region,
            isClean:this.addFormClean.region,
            roomNo:this.addClassroomNo
          })
          .then(resp => {
            console.log(resp);
            this.editDialogFormVisible=false;
            this.getClassroomList();
          }, err => {
            console.log(err);
          });
    },


    //编辑教室信息按钮
    handleEdit(index,row){
      console.log(row);
      this.editDialogFormVisible=true;
      this.formElect.region=row.classroomElectInf;
      this.formUse.region=row.classroomIsUse;
      this.formOpen.region=row.classroomIsOpen;
      this.formClean.region=row.classroomIsClean;
      this.editBuildingDialog_building=row.buildingName;
      this.editBuildingDialog_classroom=row.classroomRoomNo;
      this.editClassroomId=row.classroomId;
    },
    //编辑取消按钮
    cancel(){
      this.editDialogFormVisible=false;
    },
    //编辑确定按钮
    sure(){
      this.$confirm('此操作将修改教室信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("/classroom/editClassroomInf.do",
            {
              id:this.editClassroomId,
              electInf:this.formElect.region,
              isUse:this.formUse.region,
              isOpen:this.formOpen.region,
              isClean:this.formClean.region
            })
            .then(resp => {
              console.log(resp);

              this.getClassroomList();
            }, err => {
              console.log(err);
            });
        this.$message({
          type: 'success',
          message: '修改成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消修改'
        });
      });

    },
    //删除教室信息按钮
    handleDelete(index,row){
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("/classroom/deleteClassroom.do",
            {
              id:row.classroomId
            })
            .then(resp => {
              console.log(resp);
              this.getClassroomList();
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
    }
  },
  created() {
    this.getClassroomList();
    //this.loadCollege();

  }
}
</script>

<style scoped>

</style>