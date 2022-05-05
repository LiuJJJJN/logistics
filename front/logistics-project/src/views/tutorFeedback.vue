<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="1">
        <div class="grid-content bg-purple">
          <el-badge :value="this.unNum" :max="10" class="item" v-show="this.unNum==0?false:true">
            <el-button size="large" @click="unReply">未回复</el-button>
          </el-badge>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content bg-purple">
          <h1 style="font-size: 20px; margin-left: -50px">历史回复:</h1>
        </div>
      </el-col>
    </el-row>
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
          width="500">
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
          <!--            <el-button
                          size="mini"
                          type="danger"
                          @click="handleDelete(scope.$index, scope.row)"
                          slot="reference">删 除</el-button>-->
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
        <el-form-item :label="this.whoHistoryInf+':'">
          <el-input
              type="textarea"
              :rows="4"
              v-model="this.textarea3"
              :disabled="true"
              class="feedbackInfCss2"></el-input>
        </el-form-item>
        <!--我的答复 -->
        <el-form-item label="我的回复:">
          <el-input
              type="textarea"
              :rows="4"
              v-model="this.replyList[0].reply"
              :disabled="true"
              class="feedbackInfCss2"></el-input>
        </el-form-item>
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
      tableData: [{
        name: '',
        id: '',
        titleFeedback: '',
        time: '',
        replyTime: '',
      }],
      replyList: [{
        time: '',
        reply: ''
      }],
      dialogVisible: false
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
        console.log(resp.data);
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
        console.log(resp.data);
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
    }
  },
  created() {
    this.unReplyNumShow();
    this.replyHistoryList();
  }
}
</script>

<style scoped>
.item {
  margin-top: 10px;
  margin-left: 1200px;
}

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
  width: 500px;
}

.my-pagination {
  margin: 20px;
}
</style>
