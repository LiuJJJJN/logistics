<template>
<div>
  <el-result icon="info" title="信息提示" subTitle="尚未拥有寝室, 请等待管理员分配" v-show="JSON.stringify(dorm) == '{}'"></el-result>
  <el-descriptions class="margin-top" title="寝室信息" :column="2" border style="width: 1200px; margin: 0 auto;" v-if="JSON.stringify(dorm) != '{}'">
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
      {{ dorm.size+" 人寝" }}
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
  <el-descriptions class="margin-top" title="我的室友" :column="1" border style="width: 1200px; margin: 0 auto; margin-top: 20px" v-if="JSON.stringify(dorm) != '{}'">
    <el-descriptions-item>
      <el-timeline style="margin-top: 20px">
        <el-timeline-item
            v-for="(activity, index) in dormFriends"
            :key="index">
          <el-descriptions class="margin-top" :title="activity.name" :column="3" :size="size" border>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-user"></i>
                姓名
              </template>
              {{activity.name}}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-mobile-phone"></i>
                性别
              </template>
              {{ activity.sex }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-location-outline"></i>
                学院
              </template>
              {{ activity.college }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-tickets"></i>
                班级
              </template>
              {{ activity.stuClass }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-tickets"></i>
                学号
              </template>
              {{ activity.sno }}
            </el-descriptions-item>
          </el-descriptions>
        </el-timeline-item>
      </el-timeline>
    </el-descriptions-item>
  </el-descriptions>
</div>
</template>

<script>
export default {
  name: "stuDorm",
  data() {
    return{
      dorm:{},
      dormFriends: [],
      activities: [{
        content: '活动按期开始'
      }, {
        content: '通过审核'
      }, {
        content: '创建成功'
      }]
    }
  },
  methods:{
    loadDorm(){
      this.$axios.get("/dorm/getDormByUserId.do", {
        params:{
          userId: this.$store.getters.getUser.userId
        }
      }).then(resp=>{
        this.dorm = resp.data.data;
      }, err=>{
        console.log(err);
      });
    },
    loadDormFriend(){
      this.$axios.get("/dorm/getDormFriendByUserId.do", {
        params:{
          userId: this.$store.getters.getUser.userId
        }
      }).then(resp=>{
          this.dormFriends = resp.data.data;
        }, err=>{
          console.log(err);
        });
    }
  },
  mounted() {
    this.loadDorm();
    this.loadDormFriend();
  }
}
</script>

<style scoped>

</style>