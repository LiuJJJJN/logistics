<template>
  <div>
    <el-table
        :data="tableData"
        style="width: 100%">
      <el-table-column
          type="index"
          width="50">
      </el-table-column>
      <el-table-column
          label="姓名"
          width="100">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>用户名: {{ scope.row.student.username }}</p>
            <p>学号: {{ scope.row.student.sno }}</p>
            <p>入学日期: {{ scope.row.student.enterDate }}</p>
            <p>学制: {{ scope.row.student.schoolSys }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.student.name }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
          label="性别"
          width="70">
        <template slot-scope="scope">
          <span>{{ scope.row.student.sex }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="导员"
          width="100">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>性别: {{ scope.row.student.tutor.sex }}</p>
            <p>学院: {{ scope.row.student.tutor.college }}</p>
            <p>备注: {{ scope.row.student.tutor.remark }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.student.tutor.name }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
          label="学院"
          width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.student.college }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="班级"
          width="70">
        <template slot-scope="scope">
          <span>{{ scope.row.student.stuClass }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="备注"
          width="170">
        <template slot-scope="scope">
          <span>{{ scope.row.student.remark }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="原寝室"
          width="180">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>寝室大小: {{ scope.row.student.dorm.size }}</p>
            <p>是否独卫: {{ scope.row.student.dorm.haveWc }}</p>
            <p>水费余额: {{ scope.row.student.dorm.water }}</p>
            <p>电费余额: {{ scope.row.student.dorm.power }}</p>
            <p>备注: {{ scope.row.student.dorm.remark }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{
                  scope.row.student.dorm.building.name + " / " + scope.row.student.dorm.doorNo
                }}
              </el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
          label="目标寝室"
          width="180">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>寝室大小: {{ scope.row.toDorm.size }}</p>
            <p>是否独卫: {{ scope.row.toDorm.haveWc }}</p>
            <p>水费余额: {{ scope.row.toDorm.water }}</p>
            <p>电费余额: {{ scope.row.toDorm.power }}</p>
            <p>备注: {{ scope.row.toDorm.remark }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.toDorm.building.name + " / " + scope.row.toDorm.doorNo }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
          label="时间"
          width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.time }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核">
        <template slot-scope="scope">
          <span v-if="scope.row.status == 3">已通过</span>
          <span v-if="scope.row.status == 4">被拒绝</span>
          <span v-if="scope.row.status == 5">已完成</span>
          <span v-if="$store.getters.getUser.primaryRole == '管理员' && scope.row.status == 1">等待导员审核</span>
          <span v-if="$store.getters.getUser.primaryRole == '导员' && scope.row.status == 2">等待管理员审核</span>
          <div v-if="$store.getters.getUser.primaryRole == '导员' && scope.row.status == 1">
            <el-button
                size="mini"
                type="success"
                class="el-icon-check"
                @click="handlePass(scope.$index, scope.row)"></el-button>
            <el-button
                size="mini"
                type="danger"
                class="el-icon-close"
                @click="handleReject(scope.$index, scope.row)"></el-button>
          </div>
          <div v-if="$store.getters.getUser.primaryRole == '管理员' && scope.row.status == 2">
            <el-button
                size="mini"
                type="success"
                class="el-icon-check"
                @click="handlePass(scope.$index, scope.row)"></el-button>
            <el-button
                size="mini"
                type="danger"
                class="el-icon-close"
                @click="handleReject(scope.$index, scope.row)"></el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "tutorAndAdminDormChangeApply",
  data() {
    return {
      tableData: []
    }
  },
  methods: {
    handlePass(index, row) {
      var status = 2;
      if (this.$store.getters.getUser.primaryRole === "管理员") {
        status = 3;
      }
      this.$axios.get("/dorm/setDormChangeApplyStatus.do", {
        params: {
          id: row.id,
          status: status
        }
      }).then(resp => {
        this.loadApplyList();
        this.$message({
          message: resp.data.message,
          type: 'success'
        });
      }, err => {
        console.log(err);
      });
    },
    handleReject(index, row) {
      this.$axios.get("/dorm/setDormChangeApplyStatus.do", {
        params: {
          id: row.id,
          status: 4
        }
      }).then(resp => {
        this.loadApplyList();
        this.$message({
          message: resp.data.message,
          type: 'success'
        });
      }, err => {
        console.log(err);
      });
    },
    loadApplyList() {
      var userId = this.$store.getters.getUser.userId;
      if (this.$store.getters.getUser.primaryRole !== "导员") {
        userId = "";
      }
      this.$axios.post("/dorm/getDormChangeApplyList.do", {
        userId: userId
      }).then(resp => {
        this.tableData = resp.data.data;
      }, err => {
        console.log(err);
      });
    }
  },
  mounted() {
    this.loadApplyList();
  }
}
</script>

<style scoped>

</style>
