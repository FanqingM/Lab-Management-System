<template>
  <div>
    <!-- <el-col :span="12"> -->
    <el-row class="upper-row">
      <el-card class="maincard">
        <div slot="header" class="clearfix">
          <span class="maintitle">课程详情 </span>
        </div>
         <el-col :span="12">
           <el-image
               style="width: 500px; height: 250px"
               :src="url"
               fit="contain"></el-image>

         </el-col>
        <el-col :span="12">

          <div class="detailinfo">
            <p class=""><b>课程名称：</b>{{ sectionInfo.courseName }}</p>
            <p class=""><b>课程代号：</b>{{ sectionInfo.courseId }}</p>
            <p class=""><b>班级代号：</b>{{ sectionInfo.sectionId }}</p>
            <p class=""><b>课程学年：</b>{{ sectionInfo.year }}</p>
            <p class=""><b>课程学期：</b>{{ sectionInfo.semeter }}</p>
            <p class=""><b>课程学分：</b>{{ sectionInfo.credits }}</p>
          </div>
        </el-col>
      </el-card>
    </el-row>
    <el-row class="lower-row">
      <el-card class="maincard">
        <div slot="header" class="clearfix">
          <span class="maintitle">课程实验 </span>
        </div>
        <el-table
            v-loading="loading"
            :header-row-style="{ height: '20px' }"
            :cell-style="{ padding: '5px' }"
            ref="filterTable1"
            :data="lanInstanceInfo"
            height="465"
            stripe
            highlight-current-row
            @current-change="handleCurrentChange1"
            style="width: 100%"
            :default-sort="{ prop: 'labId', order: 'descending' }"
        >
          <el-table-column prop="labId" sortable label="实验ID"></el-table-column>
          <el-table-column prop="name" sortable label="实验名称">
          </el-table-column>
          <el-table-column prop="endTime" sortable label="截止时间">
          </el-table-column>
          <el-table-column prop="submitNum" sortable label="提交人数">
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <router-link
                  :to="{
                name: 'TeacherLabInstanceInfo',
                params: { courseId: scope.row.courseId ,sectionId: scope.row.sectionId,labId: scope.row.labId},
              }"
              >
                <el-button @click="handleClick(scope.row)" type="text"
                >查看详情
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


<style scpoed>
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
import {GETActivitiesID, GETLabInstanceOfSection, GETSectionInfo} from "../../API/http";
// import { GETOrganizationsID } from "../../API/http";
import {GETCreditRecordsID} from "../../API/http";
import {POSTCreditRecords} from "../../API/http";

// import store from "../../state/state.js"
export default {
  name: "creditscoring",
  data() {
    return {
      url: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
      sectionInfo:null,
      lanInstanceInfo:null,
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
    console.log(that.$route.params.courseId,that.$route.params.sectionId);
    GETSectionInfo({"courseId":that.$route.params.courseId,"section_Id":that.$route.params.sectionId}).then((data)=>{
      that.sectionInfo = data;
      console.log("sectionInfo",that.sectionInfo);
    }).catch((err) => {
      console.log(err);
      this.$message("评分数据请求错误");
    });
    GETLabInstanceOfSection({"courseId":that.$route.params.courseId,"sectionId":that.$route.params.sectionId}).then((data=>{
      that.lanInstanceInfo = data;
      console.log("lanInstanceInfo",that.lanInstanceInfo);

    }))
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