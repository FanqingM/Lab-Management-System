<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <span><b>我的实验</b></span>
      </div>
      <el-tabs v-model="activeName">
        <el-tab-pane label="未结束" name="first">
          <el-table
            v-loading="loading"
            :header-row-style="{ height: '20px' }"
            :cell-style="{ padding: '5px' }"
            ref="filterTable1"
            :data="unfinished"
            stripe
            highlight-current-row
            @current-change="handleCurrentChange1"
            style="width: 100%"
            :default-sort="{ prop: 'date', order: 'descending' }"
          >
            <el-table-column prop="labName" label="实验名称"> </el-table-column>
            <el-table-column prop="courseName" label="课程">
            </el-table-column>
            <el-table-column prop="endTime" sortable label="截止时间">
            </el-table-column>
            <el-table-column prop="status" label="状态">
              <template slot-scope="scope">
                <el-tag
                  :type="scope.row.grades == null ? 'primary' : 'success'"
                  disable-transitions
                >
                  {{ scope.row.grades == null ? "未提交" : "已提交"}}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <router-link
                  :to="{
                    name: 'StudentReport',
                    params: {
                      labId: scope.row.labId,
                      courseId: scope.row.courseId,
                      sectionId: scope.row.sectionId,
                      labName: scope.row.labName 
                    },
                  }"
                >
                  <el-button type="text"
                    >{{ scope.row.grades == null ? "完成报告" : "重新编辑"}}</el-button
                  >
                </router-link>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="已结束" name="second">
          <el-table
            v-loading="loading"
            :header-row-style="{ height: '20px' }"
            :cell-style="{ padding: '5px' }"
            :data="finished"
            height="465"
            stripe
            highlight-current-row
            @current-change="handleCurrentChange1"
            style="width: 100%"
            :default-sort="{ prop: 'date', order: 'descending' }"
          >
            <el-table-column prop="labName" label="实验名称"> </el-table-column>
            <el-table-column prop="courseName" label="课程">
            </el-table-column>
            <el-table-column prop="status" label="状态">
              <template slot-scope="scope">
                <el-tag
                  :type="scope.row.grades == null ? 'danger' : (scope.row.grades != 0 ? 'success' : 'primary')"
                  disable-transitions
                >
                  {{ scope.row.grades == null ? "缺交" : (scope.row.grades != 0 ? "已批改" : "未批改")}}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <router-link
                  :to="{
                    path: '/student/finished-report',
                    query: {
                      courseId: scope.row.courseId,
                      sectionId: scope.row.sectionId,
                      labId: scope.row.labId,
                    },
                  }"
                  v-if="scope.row.grades != null"
                >
                <el-button type="text">查看详情</el-button>
                </router-link>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script>
import store from "../../store/state";
import { GETLabs } from "../../API/http";
export default {
  created() {
    GETLabs({
      studentId: store.state.id,
    })
      .then((data) => {
        let nowDate = new Date()
        let date = {
          year: nowDate.getFullYear(),
          month: nowDate.getMonth() + 1,
          date: nowDate.getDate()
        }
        var currentTime = date.year + '-' + (date.month < 10 ? ('0'+String(date.month)) : String(date.month)) + '-' + (date.date < 10 ? ('0'+String(date.date)) : String(date.date))
        console.log(currentTime);
        console.log("data", data);
        for (var i = 0; i < data.length; ++i) {
          if (data[i].endTime < currentTime) {
            this.finished.push(data[i]);
          } else {
            this.unfinished.push(data[i]);
          }
        }
        console.log(this.finished);
        console.log(this.unfinished);
      })
      .catch((err) => {
        console.log(err);
        this.$message("实验信息获取错误");
      });
    this.loading = false;
  },
  data() {
    return {
      unfinished: [],
      finished: [],
      ruleForm: {
        score: null,
        textarea: "",
      },
      loading: true,
      colors: ["#99A9BF", "#F7BA2A", "#FF9900"],
      activeName: "first",
      feedbackVisible: false,
    };
  },
  methods: {
    filterTag(value, row) {
      return row.tag === value;
    },
  },
};
</script>

<style>
.el-dialog {
  border-radius: 12px;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}
.clearfix {
  font-size: 18px;
}
</style>
<style scoped>
body {
  margin: 0;
}
.page {
  height: 100%;
  width: 100%;
  margin: 0;
  display: flex;
  background-color: rgb(236, 233, 233);
  background: rgba(240, 235, 235, 0.5);
  justify-content: center;
  align-items: center;
}
.el-dialog__header {
  border-bottom: 1px solid #ebebeb;
}
.background {
  margin: 0;
  height: 98%;
  width: 99%;
  background-color: rgb(255, 255, 255);
  background: rgba(255, 255, 255, 0.5);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  position: relative;
}
.el-card {
  border-radius: 15px;
  height: 100%;
}
.modify {
  margin-left: 0.3cm;
}
.demo-table-expand {
  font-size: 0;
}
.el-card {
  border-radius: 15px;
}
.demo-table-expand label {
  width: 90px;
  font-weight: 700;
}
.dialog {
  backdrop-filter: blur(10px);
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}

::v-deep .el-dialog__body {
  padding: 0px;
  display: flex;
  justify-content: center;
}
#pdfDom {
  /* 要想pdf周边留白，要在这里设置 */
  padding: 20px;
  width: 750px;
}
.proBox {
  /* background: url("../../assets/zs.jpg") no-repeat; */
  background-size: cover;
  width: 750px;
  height: 525px;
  padding: 90px 94px;
  box-sizing: border-box;
  margin: 0 auto;
  position: relative;
  color: #000;
  font-family: SimSun;
}
.tit {
  color: #cf0c0c;
  font-size: 36px;
  font-weight: 700;
  position: relative;
  top: -6px;
  left: 8px;
  letter-spacing: 20px;
  font-family: STHeiti;
  margin: 20px 0;
}
.proid {
  text-align: right;
  margin: 0;
  font-weight: 500;
  /* margin-right: 5px; */
}
.con {
  font-size: 20px;
  font-weight: 700;
  text-align: left;
  margin: 10px 0;
  line-height: 32px;
  text-indent: 2em;
}
.con-name {
  font-family: 华文行楷, STXingkai;
  border-bottom: 2px solid #000;
}
.con-unit {
  font-size: 18px;
  font-weight: 700;
  position: absolute;
  right: 100px;
  bottom: 100px;
  text-align: center;
  letter-spacing: 3px;
}
.con-unit p {
  margin: 5px 0;
}
.con-footer {
  font-size: 18px;
  font-weight: 700;
  position: absolute;
  bottom: 45px;
  left: 0;
  right: 0;
  text-align: center;
}
.chapter {
  border-radius: 50%;
  position: absolute;
  bottom: 75px;
  right: 134px;
}
</style>