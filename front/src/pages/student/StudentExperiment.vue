<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <span><b>我的实验</b></span>
      </div>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="未完成" name="first">
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
            <el-table-column prop="labName" label="实验名称">
            </el-table-column>
            <el-table-column prop="date" sortable label="实验时间">
            </el-table-column>
            <el-table-column prop="due" sortable label="截止时间">
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <router-link
                  :to="{
                    name: 'StudentReport',
                    params: { labId: scope.row.labId },
                  }"
                >
                  <el-button @click="handleClick(scope.row)" type="text"
                    >完成报告</el-button
                  >
                </router-link>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="已完成" name="second">
          <el-table
            :header-row-style="{ height: '20px' }"
            :cell-style="{ padding: '5px' }"
            v-loading="loading"
            ref="filterTable2"
            :data="finished"
            height="465"
            stripe
            highlight-current-row
            @current-change="handleCurrentChange2"
            style="width: 100%"
            :default-sort="{ prop: 'date', order: 'descending' }"
          >
            <el-table-column prop="labName" label="实验名称">
            </el-table-column>
            <el-table-column prop="date" sortable label="实验时间">
            </el-table-column>
            <el-table-column prop="due" sortable label="截止时间">
            </el-table-column>
            <el-table-column
              prop="status"
              label="状态"
            >
              <!-- <template slot-scope="scope1">
                <el-tag
                  :type="tagType[scope1.row.activityState]"
                  disable-transitions
                >
                  未批改
                </el-tag>
              </template> -->
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <router-link
                  :to="{
                    name: 'StudentFinishedReport',
                    params: {
                      studentId: store.state.id,
                      courseId: scope.row.courseId,
                      sectionId: scope.row.sectionId,
                      labId: scope.row.labId
                    },
                  }"
                >
                  <el-button @click="handleClick(scope.row)" type="text"
                    >查看</el-button
                  >
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
import {
  GETLabs,
} from "../../API/http";
export default {
  //  components: {
  //     FeedbackDialog,
  //  },
  created() {
    GETLabs({
      studentId: store.state.id
      })
      .then((data) => {
        console.log("data", data);
        for (var i = 0; i < data.length; ++i) {
          if (data[i].grades != null) {
            this.finished.push({
              labId: data[i].labId,
              labName: data[i].labName,
              grades: data[i].grades,
              date: "2021/11/1",
              due: "2021/11/8",
              status: (data[i].grades === 0) ? "未评分" : "已评分"
            });
          }
          else {
            this.unfinished.push({
              labId: data[i].labId,
              labName: data[i].labName,
              grades: data[i].grades,
              date: "2021/11/1",
              due: "2021/11/10",
            });
          }
        }
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
      loading:true,
      feedbackRow: {
        ID: "",
        additionalRequest: "",
        date: "",
        description: "",
        groundname: "",
        groupname: "",
        name: "",
        participantNum: 0,
        tag: "",
        time: "",
      },
      colors: ["#99A9BF", "#F7BA2A", "#FF9900"],
      activeName: "first",

      //以下是调用api后新增的内容
      axiosdata: "",
      orgId: store.state.ID,
      feedbackVisible: false,
    };
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
    filterTag(value, row) {
      return row.tag === value;
    },
    formatter(row) {
      return row.groundname;
    },
    handleDelete(index, row, type) {
      console.log(index, row);

      this.$confirm("此操作将永久删除该活动信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          const tempList =
            type == 1 ? this.tableData.待举办 : this.tableData.审核中;

          for (var i = 0; i < tempList.length; i++) {
            if (tempList[i].ID == row.ID) {
              tempList.splice(i, 1);
              this.deleteAppointment(row.ID);
              break;
            }
          }
          this.$message({
            type: "success",
            message: "删除成功!",
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    handleChange(index, row, type) {
      index;
      type;
      this.$router.push({
        name: "ApplySiteWindow",
        query: {
          activityID: row.ID,
        },
      });
    },
    handleFeedback(row) {
      console.log(row);
      this.feedbackVisible = true;
      this.feedbackRow = row;
    },
    handleRenew(index, row) {
      console.log(index, row);
    },
    handleCurrentChange1(val) {
      this.currentRow = val;
      //this.$router.push('/Recorddescription')
    },
    handleCurrentChange2(val) {
      this.currentRow = val;
      this.$router.push({
        name: "PendingWindow",
        params: {
          activityID: val.ID,
        },
      });
    },
    handleCurrentChange3(val) {
      this.currentRow = val;
      this.$router.push({
        name: "RecordWindow",
        params: {
          activityID: val.ID,
        },
      });
    },
    handleCurrentChange4(val) {
      this.currentRow = val;
      this.$router.push({
        name: "RejectedWindow",
        params: {
          activityID: val.ID,
        },
      });
    },
    dealWithActivities(data) {
      console.log("run dealwithActivities", data);

      for (var key in data) {
        // console.log("key",key);
        for (var i = 0; i < data[key].length; i++) {
          // console.log(data[key][i]);
          var temp = {
            date: "2016-05-03",
            name: "活动2",
            groundname: "a楼",
            ID: "11117",
            participantNum: 40,
            additionalRequest: "无",
            description: "听数据库开会",
            tag: "室外",
            activityState: "审核中",
          };
          temp.ID = data[key][i].id;
          temp.date = data[key][i].activityDate.split("T")[0];
          temp.time = data[key][i].activityDate.split("T")[1];
          temp.name = data[key][i].name;
          temp.description = data[key][i].description;
          temp.participantNum = data[key][i].participantNum;
          temp.groupname = data[key][i].organizationName;
          temp.groundname = data[key][i].groundName;
          temp.additionalRequest = data[key][i].additionalRequest;
          temp.activityState = data[key][i].activityState;
          temp.tag = data[key][i].isGroundIndoor ? '室内' : '室外';
          this.tableData[key].push(temp);
        }
      }

      for (let j = 0; j < this.tableData["待反馈"].length; j++) {
        this.tableData["已完成"].push(this.tableData["待反馈"][j]);
      }
      for (let j = 0; j < this.tableData["已反馈"].length; j++) {
        this.tableData["已完成"].push(this.tableData["已反馈"][j]);
      }
      console.log(this.tableData);
    },
  },
  computed: {
    formatTime() {
      var Y, m, d, H, i, s, sresult;

      const date = new Date();

      (Y = date.getFullYear()),
        (m = date.getMonth() + 1),
        (d = date.getDate()),
        (H = date.getHours()),
        (i = date.getMinutes()),
        (s = date.getSeconds());
      if (m < 10) {
        m = "0" + m;
      }
      if (d < 10) {
        d = "0" + d;
      }
      if (H < 10) {
        H = "0" + H;
      }
      if (i < 10) {
        i = "0" + i;
      }
      if (s < 10) {
        s = "0" + s;
      }
      sresult = String(Y) + "-" + m + "-" + d + "T" + H + ":" + i + ":" + s;

      return sresult;
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