<template>
  <div>
    <!-- <el-col :span="12"> -->
    <el-row class="upper-row">
      <el-card class="maincard">
        <div slot="header" class="clearfix">
          <span class="maintitle">实验详情 </span>
        </div>
        <el-col :span="12">
          <p class=""><b>课程代号：</b>{{ lanInstanceInfo.courseId }}</p>
          <p class=""><b>班级代号：</b>{{ lanInstanceInfo.sectionId }}</p>
          <p class=""><b>实验代号：</b>{{ lanInstanceInfo.labId }}</p>
          <p class=""><b>实验名称：</b>{{ lanInstanceInfo.name }}</p>
        </el-col>
        <el-col :span="12">

          <div class="detailinfo">

            <p class=""><b>起始时间：</b>{{ lanInstanceInfo.startTime }}</p>
            <p class=""><b>截止时间：</b>{{ lanInstanceInfo.endTime }}</p>
            <p class=""><b>提交人数：</b>{{ lanInstanceInfo.submitNum }}</p>

          </div>
        </el-col>
      </el-card>
    </el-row>
    <el-row class="lower-row">
      <el-card class="maincard">
        <div slot="header" class="clearfix">
          <span class="maintitle">已交报告 </span>
        </div>
        <el-table
            v-loading="loading"
            :header-row-style="{ height: '20px' }"
            :cell-style="{ padding: '5px' }"
            ref="filterTable1"
            :data="reportInfo"
            height="465"
            stripe
            highlight-current-row
            @current-change="handleCurrentChange1"
            style="width: 100%"
            :default-sort="{ prop: 'date', order: 'descending' }"
        >
          <el-table-column prop="studentId" sortable label="学号"></el-table-column>
          <el-table-column prop="name" sortable label="姓名">
          </el-table-column>
          <el-table-column
              prop="graded"
              label="是否已评分"
              :filters="[{ text: '否', value: false }, { text: '是', value: true }]"
              :filter-method="filterTag"
              filter-placement="bottom-end">
            <template slot-scope="scope">
              <el-tag
                  :type="scope.row.graded === true ? 'primary' : 'success'"
                  disable-transitions>{{scope.row.graded?"是":"否"}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="grades" sortable label="分数">
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <router-link
                  :to="{
                name: 'TeacherGradingReport',
                params: { studentId:scope.row.studentId,courseId: scope.row.courseId ,sectionId: scope.row.sectionId,labId:scope.row.labId},
              }"
              >
                <el-button @click="handleClick(scope.row)" type="text"
                >{{scope.row.graded?"查看报告":"评分"}}
                </el-button
                >
              </router-link>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </el-row>
  </div>
</template>


<style scoped>
html,
body,
#app,
.el-container {
  /*设置内部填充为0，几个布局元素之间没有间距*/
  padding: 0px;
  /*外部间距也是如此设置*/
  margin: 0px;
  /*统一设置高度为100%*/
  height: 100%;
}

.upper-row {
  height: 45%;
  padding-bottom: 15px;
}

.lower-row {
  height: 55%;
}

.maintitle {
  text-align: left;
  font-size: 20px;
  font-weight: 600;
}

.maincard {
  border-radius: 15px;
  height: 100%;
}

.detailinfo {
  padding-left: 15px;
}

.el-card {
  border-radius: 15px;
}
</style>


<script>
import {
  GETActivitiesID,
  GETLabInstanceInfo,
  GETLabInstanceOfSection,
  GETReportOfLabInstace,
  GETSectionInfo
} from "../../API/http";
// import { GETOrganizationsID } from "../../API/http";
import {GETCreditRecordsID} from "../../API/http";
import {POSTCreditRecords} from "../../API/http";

// import store from "../../state/state.js"
export default {
  name: "creditscoring",
  data() {
    return {
      url: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
      reportInfo: null,
      lanInstanceInfo: null,
      ruleForm: {
        reason: "",
      },
      rules: {
        reason: [
          {required: true, message: "请输入评分理由", trigger: "blur"},
        ],
      },
      activityName: null,
      date: null,
      startTime: null,
      description: null,
      additionalRequest: null,
      participantNum: null,
      duration: null,
      groundID: null,
      groupID: null,
      isReviewed: null,
      creditDate: null,
      creditTime: null,
      score: null,
      groupName: null,
      groundName: null,
      hasCredit: null,
    };
  },
  mounted() {
    const that = this;
    console.log(that.$route.params.courseId, that.$route.params.sectionId);
    GETLabInstanceInfo({
      "courseId": that.$route.params.courseId,
      "sectionId": that.$route.params.sectionId,
      "labId": that.$route.params.labId
    }).then((data) => {
      that.lanInstanceInfo = data;
      console.log("labinstacedata", that.lanInstanceInfo);
    }).catch((err) => {
      console.log(err);
      this.$message("实验实例数据请求错误");
    });
    GETReportOfLabInstace({
      "courseId": that.$route.params.courseId,
      "sectionId": that.$route.params.sectionId,
      "labId": that.$route.params.labId
    }).then((data) => {
      that.reportInfo = data;
      console.log("reportInfo", that.reportInfo);
    }).catch((err) => {
      console.log(err);
      this.$message("实验报告数据请求错误");
    });
  },
  methods: {
    dealWithActivitiy(data) {
      this.groundName = data.groundName;
      this.activityName = data.name;
      this.accountNumber = data.accountNumber;
      this.groupName = data.organizationName;
      this.date = data.activityDate.substr(0, data.activityDate.search("T"));
      this.startTime = data.activityDate.slice(
          data.activityDate.search("T") + 1
      );
      this.participantNum = data.participantNum;
      this.description = data.description;
      this.additionalRequest = data.additionalRequest;
      this.duration = data.duration;
      this.hasCredit = data.hasCredit;
      // this.state = data.activityState; 是否评分还没有
    },
    cancle() {
      this.$router.push({
        path: "/GroundsAdmin/ScoringActivityList",
      });
    },
    publish() {
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          POSTCreditRecords({
            reason: this.ruleForm.reason,
            score: this.score,
            id: this.$route.params.ID,
          })
              .then((data) => {
                GETCreditRecordsID(this.$route.params.ID).then((data) => {
                  this.score = data.relativeScore;
                  this.creditDate = data.creditDate.substr(
                      0,
                      data.creditDate.search("T")
                  );
                  this.creditTime = data.creditDate.slice(
                      data.creditDate.search("T") + 1
                  );
                  this.ruleForm.reason = data.reason;
                }).catch((err) => {
                  console.log(err);
                  this.$message("评分时间请求错误");
                });
                this.hasCredit = 1;
                console.log(data);
                this.$message({message: "评分成功", type: "success"});
                //this.$router.push({ path: "/GroundsAdmin/Main" });
              })
              .catch((err) => {
                err;
                this.$message({message: "评分失败", type: "error"});
              });
        } else {
          this.$message({message: "请输入符合规范的数据", type: "warning"});
        }
      });
    },
    form() {
    },
    created() {
      console.log(String(this.activityID));
      this.activityID = Number(this.$route.params.activityID); //具体信息
    },
  },
};
</script>