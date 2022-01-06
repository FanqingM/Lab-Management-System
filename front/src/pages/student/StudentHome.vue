<template>
  <div>
    <el-row class="upper-row">
      <el-col :span="10" class="upper-row-col1"
        ><!--左上角的两块-->
        <el-card class="upper-card">
          <el-row>
            <el-col :span="9">
              <div>
                <br />
                <el-avatar :size="130" :src="circleUrl"></el-avatar>
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
      </el-col>
      <el-col :span="14" class="upper-row-col2">
        <el-card class="upper-card">
          <div
            id="chartPie"
            class="pie-wrap"
            style="width: 600px; height: 250px"
          ></div>
          <!-- <div slot="header" class="clearfix">
            <span><b>公告</b></span>
            <router-link to="/student/announcement">
              <el-button style="float: right; padding: 3px 0" type="text"
                >查看更多</el-button
              >
            </router-link>
          </div>
          <el-table
            :data="systemAnnouncement"
            stripe
            style="width: 100%"
            height="146"
            @row-click="onRowClick"
            :show-header="false"
          >
            <el-table-column prop="title" width="auto"> </el-table-column>
            <el-table-column prop="systemAnnouncementDate" width="auto">
              <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span style="margin-left: 10px">{{
                  scope.row.systemAnnouncementDate
                }}</span>
              </template>
            </el-table-column>
          </el-table> -->
        </el-card>
      </el-col>
    </el-row>
    <el-row class="lower-row">
      <el-col :span="12" class="lower-row-col1">
        <el-card class="lower-card">
          <div slot="header" class="clearfix">
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
            @current-change="handleCurrentChange1"
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
                    name: 'StudentFinishedReport',
                    params: { ID: scope.row.labId },
                  }"
                >
                  <el-button @click="handleClick(scope.row)" type="text"
                    >完成报告</el-button
                  >
                </router-link>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12" class="lower-row-col2">
        <el-card class="lower-card">
          <div slot="header" class="clearfix">
            <span><b>成绩</b></span>
            <router-link to="/student/score">
              <el-button style="float: right; padding: 3px 0" type="text"
                >查看更多</el-button
              >
            </router-link>
          </div>
          <el-table
            v-loading="loading"
            :header-row-style="{ height: '15px' }"
            :cell-style="{ padding: '5px' }"
            ref="filterTable1"
            :data="grade"
            stripe
            highlight-current-row
            @current-change="handleCurrentChange1"
            style="width: 100%"
            :default-sort="{ prop: 'date', order: 'descending' }"
          >
            <el-table-column prop="labName" label="实验名称"> </el-table-column>
            <el-table-column prop="courseName" label="课程"> </el-table-column>
            <el-table-column prop="grades" sortable label="评分">
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <router-link
                  :to="{
                    name: 'StudentReport',
                    params: { ID: scope.row.experimentID },
                  }"
                >
                  <el-button @click="handleClick(scope.row)" type="text"
                    >查看详情</el-button
                  >
                </router-link>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { GETStudentsID, GETLabs } from "../../API/http";
import store from "../../store/state";
import * as echarts from "echarts";
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
        console.log("data", data);
        this.unfinished = [];
        for (var i = 0; i < data.length; ++i) {
          if (data[i].grades == null) {
            this.unfinished.push(data[i]);
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
  mounted() {
    this.$nextTick(() => {
      this.drawPieChart();
    });
  },
  data() {
    return {
      chartPie: null,
      systemData: null,
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
    drawPieChart() {
      let mytextStyle = {
        color: "#333",
        fontSize: 18,
      };
      let mylabel = {
        show: true,
        position: "right",
        offset: [30, 40],
        formatter: "{b} : {c} ({d}%)",
        textStyle: mytextStyle,
      };
      this.chartPie = echarts.init(
        document.getElementById("chartPie"),
        "macarons"
      );
      this.chartPie.setOption({
        title: {
          text: "实验成绩分布",
          subtext: "",
          x: "center",
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)",
        },
        legend: {
          data: [
            "计算机网络实验",
            "软件工程经济学",
            "物理实验",
            "编译原理实验",
            "计算机组成原理实验",
          ],
          left: "center",
          top: "bottom",
          orient: "horizontal",
        },
        series: [
          {
            name: "访问来源",
            type: "pie",
            radius: ["50%", "70%"],
            center: ["50%", "50%"],
            data: [
              { value: 335, name: "计算机网络实验" },
              { value: 310, name: "软件工程经济学" },
              { value: 234, name: "物理实验" },
              { value: 135, name: "编译原理实验" },
              { value: 1548, name: "计算机组成原理实验" },
            ],
            animationEasing: "cubicInOut",
            animationDuration: 2600,
            label: {
              emphasis: mylabel,
            },
          },
        ],
      });
    },
  },
};
</script>

<style scpoed>
html,
body {
  padding: 0px;
  margin: 0px;
  height: 100%;
}
.tag {
  margin-top: 4px;
  margin-bottom: 1px;
  margin-right: 5px;
}
.upper-card {
  height: 100%;
  border-radius: 10px;
}
.lower-card {
  height: 100%;
  border-radius: 10px;
  overflow: auto;
}
/* .el-dialog {
  border-radius: 12px;
}
.dialog {
  backdrop-filter: blur(10px);
} */
.content {
  height: 320px;
}
.upper-row {
  height: 50%;
}
.lower-row {
  height: 50%;
}
.lower-row-col1 {
  height: 100%;
  padding: 5px;
}
.lower-row-col2 {
  height: 100%;
  padding: 5px;
}
.upper-row-col1 {
  height: 100%;
  padding: 5px;
}
.upper-row-col2 {
  height: 100%;
  padding: 5px;
}
.el-card {
  border-radius: 10px;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}
.el-dialog__header {
  border-bottom: 1px solid #ebebeb;
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