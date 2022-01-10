<template>
  <div class="main-row">
    <div class="main-col">
      <div class="upper-row">
        <el-card class="upper-card">
          <el-row>
            <el-col :span="9">
              <div>
                <br />
                <el-avatar
                  :size="130"
                  src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
                ></el-avatar>
              </div>
            </el-col>
            <el-col :span="15">
              <div class="name">{{ accountData.name }}</div>
              <div class="other-info">
                <br />ID：{{ accountData.id }} <br />{{
                  accountData.schoolName
                }}
              </div>
              <div class="date">
                {{ semesterInfo.fromYear }}-{{ semesterInfo.toYear }}年度第{{
                  semesterInfo.semester
                }}学期第{{ semesterInfo.week }}周
              </div>
            </el-col>
          </el-row>
        </el-card>
      </div>
      <div style="height: 2%"></div>
      <div class="lower-row">
        <el-card class="lower-card">
          <div slot="header">
            <span><b>待办</b></span>
            <router-link to="/student/experiment">
              <el-button style="float: right; padding: 3px 0" type="text"
                >查看更多</el-button
              >
            </router-link>
          </div>
          <el-table
            :header-row-style="{ height: '15px' }"
            :cell-style="{ padding: '5px' }"
            :data="unfinished"
            stripe
            highlight-current-row
            style="width: 100%"
            :default-sort="{ prop: 'date', order: 'descending' }"
          >
            <el-table-column prop="labName" label="实验名称"> </el-table-column>
            <el-table-column prop="courseName" label="课程"> </el-table-column>
            <el-table-column prop="endTime" sortable label="截止时间">
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <router-link
                  :to="{
                    name: 'StudentReport',
                    params: {
                      labId: scope.row.labId,
                      labName: scope.row.labName,
                    },
                  }"
                >
                  <el-button type="text">完成报告</el-button>
                </router-link>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </div>
    </div>
    <div style="width: 1%; height: 100%; display: inline-block"></div>
    <div style="width: 49%; height: 100%; display: inline-block">
      <el-card class="right-card">
        <full-calendar
          :events="monthData"
          first-day="1"
          locale="zh-cn"
          @eventClick="eventClick"
        ></full-calendar>
      </el-card>
    </div>
  </div>
</template>

<script>
import { FullCalendar } from "vue-fullcalendar";
import { GETStudentsID, GETLabs } from "../../API/http";
import store from "../../store/state";
require("echarts/theme/macarons"); //引入主题

export default {
  created() {
    console.log("ID: ", store.state.id);
    GETStudentsID(store.state.id)
      .then((data) => {
        this.accountData = data;
        console.log(this.accountData);
      })
      .catch((err) => {
        console.log(err);
        this.$message("学生信息获取错误");
      });
    GETLabs({
      studentId: store.state.id,
    })
      .then((data) => {
        let nowDate = new Date();
        let date = {
          year: nowDate.getFullYear(),
          month: nowDate.getMonth() + 1,
          date: nowDate.getDate(),
        };
        var currentTime =
          date.year +
          "-" +
          (date.month < 10 ? "0" + String(date.month) : String(date.month)) +
          "-" +
          (date.date < 10 ? "0" + String(date.date) : String(date.date));
        console.log(currentTime);
        console.log("data", data);
        for (var i = 0; i < data.length; ++i) {
          if (data[i].endTime >= currentTime) {
            this.monthData.push({
              title: data[i].courseName + data[i].labName,
              start: data[i].endTime,
              end: data[i].endTime,
              labId: data[i].labId,
              labName: data[i].labName,
            });
            if (data[i].grades == null) {
              this.unfinished.push(data[i]);
            }
          }
        }
      })
      .catch((err) => {
        console.log(err);
        this.$message("实验信息获取错误");
      });
    GETLabs({
      studentId: store.state.id,
    })
      .then((data) => {
        console.log("data", data);
        this.grade = [];
        for (var i = 0; i < data.length; ++i) {
          if (data[i].grades != null && data[i].grades !== 0) {
            this.grade.push(data[i]);
          }
        }
      })
      .catch((err) => {
        console.log(err);
        this.$message("成绩获取错误");
      });
  },
  data() {
    return {
      chartPie: null,
      systemData: null,
      monthData: [],
      circleUrl:
        "https://car3.autoimg.cn/cardfs/product/g2/M04/EE/1D/1400x0_1_q95_autohomecar__ChsEkF242U6ADiMLAAaEhG82eyQ499.jpg",
      accountData: {
        id: "",
        email: "",
        name: "",
        classnum: "",
        schoolName: "",
      },
      //第一块卡片信息
      orgnizationInfo: {},
      semesterInfo: {
        //get semester from backend

        fromYear: "2021",
        toYear: "2022",
        semester: "2",
        week: "18",
      },
      //第二块卡片信息
      OrgID: store.state.ID,
      //弹出式公告
      dialogTitle: "",
      dialogContent: "",
      dialogVisible: false,
      activityVisible: false,
      activitySelected: {
        id: "",
        name: "",
        description: "",
        organizationName: "",
        startTime: "",
        groundName: "",
        participantNum: "",
      },
      groundAnnouncement: [],
      systemAnnouncement: [],

      //第三块卡片信息
      unfinished: [],
      //第四片卡片信息
      grade: [],
    };
  },
  methods: {
    eventClick(event, jsEvent, pos) {
      console.log("eventClick", event, jsEvent, pos);
      this.$router.push({
        name: "StudentReport",
        params: { labId: event.labId, labName: event.labName },
      });
    },
  },
  components: {
    "full-calendar": require("vue-fullcalendar"),
  },
};
</script>

<style scoped>
.main-row {

  height: 100%;
  width: 100%;
}
.main-col {
  height: 100%;
  width: 50%;
  display: inline-block;
}
.upper-row {
  height: 40%;
}
.upper-card {
  border-radius: 10px;
  min-height: 100%;
}
.lower-row {
  height: 58%;
}
.lower-card {
  min-height: 100%;
  overflow: auto;
  border-radius: 10px;
}
.right-card {
  height: 100%;
  border-radius: 10px;
  overflow: auto;
}
.name {
  font-size: 30px;
  font-weight: 550;
  line-height: 20px;
}
.other-info {
  font-size: 15px;
  line-height: 25px;
  font-weight: 500;
}
.date {
  font-size: 20px;
  line-height: 40px;
  font-weight: 700;
}
</style>