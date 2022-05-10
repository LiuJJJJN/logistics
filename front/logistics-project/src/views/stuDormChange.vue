<template>
  <div>
    <el-steps :active="status" finish-status="success" simple style="margin-top: 10px">
      <el-step title="申请换寝"></el-step>
      <el-step title="导员审批"></el-step>
      <el-step title="管理员审批"></el-step>
      <el-step title="完成"></el-step>
    </el-steps>
    <el-row type="flex" class="row-bg" style="padding: 150px 0;" justify="center" v-show="status === 0">
      <el-col :span="8">
        <el-result icon="info" title="信息提示" subTitle="尚未拥有寝室, 请等待管理员分配"
                   v-show="JSON.stringify(dorm) == '{}'"></el-result>
        <el-descriptions title="当前寝室" :column="1" border
                         style="width: 400px; margin: 0;"
                         v-if="JSON.stringify(dorm) != '{}'">
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-office-building"></i>
              寝室楼宇
            </template>
            {{ dorm.building.name }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-key"></i>
              寝室门牌号
            </template>
            {{ dorm.doorNo }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-toilet-paper"></i>
              是否独卫
            </template>
            {{ dorm.haveWc }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-rank"></i>
              寝室大小
            </template>
            {{ dorm.size + " 人寝" }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-tickets"></i>
              水费余额
            </template>
            <el-tag size="small">{{ dorm.water }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-tickets"></i>
              电费余额
            </template>
            <el-tag size="small">{{ dorm.power }}</el-tag>
          </el-descriptions-item>
        </el-descriptions>
      </el-col>
      <el-col :span="8" v-if="JSON.stringify(dorm) != '{}'">
        <span style="font-weight: bolder; display: block">选择寝室</span>
        <el-form :model="toForm" ref="changeDormForm" label-width="100px" class="demo-ruleForm" style="margin: 50px 0">
          <el-form-item label="寝室" prop="dorm" :rules="[{ required: true, message: '寝室不能为空'}]">
            <el-cascader
                v-model="toForm.dorm"
                :options="dormOptions"
                :props="{ expandTrigger: 'hover' }"
            ></el-cascader>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('changeDormForm')">提交</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <el-row type="flex" class="row-bg" justify="center" v-show="status === 1 || status === 2">
      <el-col :span="6" style="margin: 150px 0">
        <el-result icon="info" title="提示" subTitle="当前申请正在审批中, 请等待"></el-result>
      </el-col>
    </el-row>
    <el-row type="flex" class="row-bg" justify="center" v-show="status === 3">
      <el-col :span="6" style="margin: 150px 0">
        <el-result icon="success" title="审批通过" subTitle="请尽快完成换寝">
          <template slot="extra">
            <el-button type="primary" size="medium" @click="finishChange">我已完成</el-button>
          </template>
        </el-result>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "changeDorm",
  data() {
    return {
      dorm: {},
      toForm: {},
      dormOptions: [],
      status: 0
    }
  },
  methods: {
    loadDorm() {
      this.$axios.get("/dorm/getDormByUserId.do", {
        params: {
          userId: this.$store.getters.getUser.userId
        }
      }).then(resp => {
        this.dorm = resp.data.data;
      }, err => {
        console.log(err);
      });
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post("/dorm/changeDorm.do", {
            fromDorm: this.dorm.id,
            toDorm: this.toForm.dorm[1]
          }).then(resp => {
            this.loadStatus();
            this.$message({
              message: resp.data.message,
              type: 'success'
            });
          }, err => {
            console.log(err);
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    loadDormOptions() {
      this.$axios.post("/dorm/getDormOptions.do")
          .then(resp => {
            this.dormOptions = resp.data.data;
          }, err => {
            console.log(err);
          });
    },
    loadStatus() {
      this.$axios.post("/dorm/getChangeStatus.do")
          .then(resp => {
            if (resp.data.data === 5) {
              this.status = 0;
            } else if (resp.data.data === 4){
              this.$notify({
                title: '警告',
                message: '你的换寝申请被驳回, 请谨慎申请',
                type: 'warning'
              });
              this.status = 0;
            } else {
              this.status = resp.data.data;
            }
          }, err => {
            console.log(err);
          });
    },
    finishChange() {
      this.$axios.post("/dorm/finishChange.do")
          .then(resp => {
            this.status = 0;
            this.loadDorm();
            this.loadDormOptions();
            this.$message({
              message: resp.data.message,
              type: 'success'
            });
          }, err => {
            console.log(err);
          });
    }
  },
  mounted() {
    this.loadDorm();
    this.loadDormOptions();
    this.loadStatus();
  }
}
</script>

<style scoped>

.row-bg {
  padding: 10px 0;
  margin: 20px 0;
  background-color: #f9fafc;
}
</style>
