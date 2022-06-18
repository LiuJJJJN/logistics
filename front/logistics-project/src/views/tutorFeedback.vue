<template>
  <div>
    <h1 style="font-size: 20px; width: 200px; float: left;">历史回复:</h1>
    <div style="float: right; width: 100px; margin-top: 20px">
      <el-badge :value="this.unNum" :max="100" class="item1" v-show="this.unNum==0?false:true">
        <el-button size="large" @click="unReply">未回复</el-button>
      </el-badge>
    </div>

    <el-table
        :data="tableData"
        style="width: 100%">
      <el-table-column
          label="反馈日期"
          width="240">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.time }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="学生姓名"
          width="150">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="反馈标题"
          width="350">
        <template slot-scope="scope">
          <i class="el-icon-s-order"></i>
          <span style="margin-left: 10px">{{ scope.row.titleFeedback }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="答复日期"
          width="240">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.replyTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
              size="mini"
              @click="historyFeedbackInf(scope.$index,scope.row)">详细内容
          </el-button>
          <div style="display:inline-block; padding-left: 10px"></div>
        </template>
      </el-table-column>
    </el-table>
    <div class="pageCss">
      <div class="block my-pagination">
        <el-pagination
            @size-change="historyHandleSizeChange"
            @current-change="historyHandleCurrentChange"
            :current-page="HistoryPageNo"
            :page-sizes="[5, 10, 50, 100]"
            :page-size="historyPageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="historyTotal"
            background>
        </el-pagination>
      </div>
    </div>

    <!--抽屉窗口-->
    <el-drawer
        title="未回复"
        :visible.sync="table"
        direction="rtl"
        size="40%">
      <el-table :data="gridData">
        <el-table-column property="date" label="反馈日期" width="170">
          <template slot-scope="scope">
            <span>{{ scope.row.time }}</span>
          </template>
        </el-table-column>
        <el-table-column property="name" label="学生姓名" width="90">
          <template slot-scope="scope">
            <span>{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column property="address" label="反馈标题">
          <template slot-scope="scope">
            <span>{{ scope.row.titleFeedback }}</span>
          </template>
        </el-table-column>
        <el-table-column property="address" label="">
          <template slot-scope="scope">
            <el-button size="small" @click="feedbackInf(scope.$index,scope.row)">反馈内容</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--反馈内容-->
      <el-dialog title="反馈内容" :visible.sync="detailedInfOpen" :append-to-body="true">
        <el-form label-width="150px">
          <!--学生的内容-->
          <el-form-item :label="this.who+':'">
            <el-input
                type="textarea"
                :rows="4"
                v-model="this.textarea"
                :disabled="true"
                class="feedbackInfCss2"></el-input>
          </el-form-item>
          <!--答复 -->
          <el-form-item label="回复:">
            <el-input
                type="textarea"
                :rows="4"
                v-model="textarea2"
                placeholder="输入您的答复"
                class="feedbackInfCss2"></el-input>
          </el-form-item>
        </el-form>
        <el-button type="primary" round class="replyButton" @click="replyButton">答 复</el-button>
      </el-dialog>

      <!-- 分页 -->
      <div class="pageCss">
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
      </div>
    </el-drawer>

    <!--历史详细内容模态窗口-->
    <el-dialog title="详细内容" :visible.sync="detailedhistoryInfOpen">
      <el-form label-width="150px">
        <!--学生的内容-->
        <el-card class="box-card">
          <div class="text item">
            <span style="font-weight: bolder">{{ this.whoHistoryInf }}：&nbsp;&nbsp;</span>
            <span>{{ this.textarea3 }}</span>
          </div>
          <div v-for="o in this.replyList" :key="o" class="text item">
            <font style="font-weight: bolder">我：</font><font style="font-weight: lighter;font-family: 幼圆">{{
              o.reply
            }}</font>
          </div>
        </el-card>
        <!--再次答复 -->
        <div style="margin-top: 30px"><font style="margin-left:150px;font-size: 15px">再次回复:</font></div>
        <el-form-item label="" style="margin-top: 10px">
          <el-input
              type="textarea"
              :rows="4"
              v-model="replyAgainInf"
              class="feedbackInfCss2"></el-input>
        </el-form-item>
        <el-button type="primary" round style="margin-left:550px" @click="replyAgainInfBtn">回 复</el-button>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "feedback",
  data() {
    return {
      unNum: 0,
      table: false,
      pageNo: 1, //当前页数
      pageSize: 10, //显示条数
      total: 0, //总条数
      historyPageNo: 1, //当前页数
      historyPageSize: 10, //显示条数
      historyTotal: 0, //总条数
      who: '',
      idFlag: '',
      textarea: '',
      textarea2: '',
      detailedInfOpen: false,
      detailedhistoryInfOpen: false,
      whoHistoryInf: '',
      textarea3: '',
      textarea4: '',
      gridData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市'
      }],
      showUnReply: false,
      tableData: [],
      replyList: [{
        time: '',
        reply: ''
      }],
      dialogVisible: false,
      getId: '',
      replyAgainInf: '',
    }
  },
  methods: {
    //上来就加载为回复的记录数量
    unReplyNumShow() {
      this.$axios.post("/reply/getUnNum.do", {
            id: this.$store.getters.getUser.userId,//user表中的id
          }
      ).then(resp => {
        this.unNum = resp.data.data.num;
        // console.log(resp.data);
      }, err => {
        console.log(err);
      });
    },
    //未回复按钮，显示表格
    unReply() {
      this.table = true;
      this.unReplyList();
    },
    //加载未回复的反馈
    unReplyList() {
      this.$axios.post("/reply/getReplyL.do", {
            id: this.$store.getters.getUser.userId,//user表中的id
            tutorId: (this.pageNo - 1) * this.pageSize,//避免后端建立vo类之间赋值给user的tutorId
            adminId: this.pageSize
          }
      ).then(resp => {
        this.total = resp.data.data.total;
        this.gridData = resp.data.data.list;
        this.unNum = resp.data.data.total;
        console.log(resp.data);
      }, err => {
        console.log(err);
      });
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.unReplyList();
    },
    //当前页数
    handleCurrentChange(val) {
      this.pageNo = val;
      this.unReplyList();
    },
    //未反馈的详细内容
    feedbackInf(index, row) {
      this.detailedInfOpen = true;
      this.who = row.name;
      this.idFlag = row.id;
      this.$axios.post("/reply/getFeedbackBI.do", {
            id: row.id
          }
      ).then(resp => {
        this.textarea = resp.data.data.feedback;
        console.log(resp.data);
      }, err => {
        console.log(err);
      });
    },
    //答复确认按钮
    replyButton() {
      if (this.textarea2 == '') {
        this.$message({
          message: '警告，回复内容不能为空',
          type: 'warning'
        });
      } else {
        this.$confirm('是否确认回复?', '', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post("/reply/tutorReply.do", {
                id: this.$store.getters.getUser.userId,//user表中的id
                tutorId: this.idFlag,//当前学生反馈的id
                reply: this.textarea2
              }
          ).then(resp => {
            this.detailedInfOpen = false;
            this.textarea2 = '';
            this.unReplyList();
            this.replyHistoryList();
            console.log(resp.data);
          }, err => {
            console.log(err);
          });
          this.$message({
            type: 'success',
            message: '回复成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消回复'
          });
        });
      }
    },
    //回复过的历史反馈列表
    replyHistoryList() {
      this.$axios.post("/reply/getRL.do", {
            id: this.$store.getters.getUser.userId,//user表中的id
            tutorId: (this.historyPageNo - 1) * this.historyPageSize,//避免后端建立vo类之间赋值给user的tutorId
            adminId: this.historyPageSize
          }
      ).then(resp => {
        this.historyTotal = resp.data.data.total;
        this.tableData = resp.data.data.list;
        // console.log(resp.data);
      }, err => {
        console.log(err);
      });
    },
    historyHandleSizeChange(val) {
      this.historyPageSize = val;
      this.replyHistoryList();
    },
    historyHandleCurrentChange(val) {
      this.historyPageNo = val;
      this.replyHistoryList();
    },
    //详细内容
    historyFeedbackInf(index, row) {
      this.detailedhistoryInfOpen = true;
      this.getId = row.id;
      this.$axios.post("/reply/getHistoryIBI.do", {
            id: row.id,//feed_id
          }
      ).then(resp => {
        this.textarea3 = resp.data.data.feedback;
        this.whoHistoryInf = row.name;
        console.log(resp.data);
      }, err => {
        console.log(err);
      });
      this.$axios.post("/reply/getReplyIBI.do", {
            id: row.id,//feed_id
          }
      ).then(resp => {
        this.replyList = resp.data.data;
        this.whoHistoryInf = row.name;
        console.log(resp.data);
      }, err => {
        console.log(err);
      });
    },
    //再次回复按钮
    replyAgainInfBtn() {
      if (this.replyAgainInf == '') {
        this.$message({
          message: '警告，回复内容不能为空',
          type: 'warning'
        });
      } else {
        this.$confirm('是否确认再次回复?', '', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post("/reply/replyAgain.do", {
                tutorId: this.$store.getters.getUser.userId,//userId
                feedbackId: this.getId,//反馈的id
                reply: this.replyAgainInf//再次回复的内容
              }
          ).then(resp => {
            this.replyAgainInf = '';
            this.replyHistoryList();
            console.log(resp.data);
          }, err => {
            console.log(err);
          });
          this.$message({
            type: 'success',
            message: '再次回复成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消回复'
          });
        });
      }

    }
  },
  created() {
    this.unReplyNumShow();
    this.replyHistoryList();
  }
}
</script>

<style scoped>

.feedbackInfCss {
  width: 400px;
}

.spanCss {
  color: #409EFF;
  margin-left: 100px;
}

.spanCss2 {
  margin-left: 100px;
}

.replyCss {
  margin-left: 30px;
  width: 400px;
}

.replyButton {
  margin-top: 10px;
  margin-left: 550px;
}

.feedbackInfCss2 {
  width: 480px;
}

.my-pagination {
  margin: 20px;
}

.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  margin-left: 150px;
  width: 480px;
}
</style>
