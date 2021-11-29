<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <span><b>敏感性分析实验</b></span>
      </div>
      <el-row>
        <el-col :span=6>
          <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="步长(%)">
              <el-input-number v-model="form.step" :precision="2" :step="0.2" :max="5" :min="0.1"></el-input-number>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span=14>
          <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="变化范围">
              <el-slider
                  v-model="form.range"
                  range
                  :max="25"
                  :min="-25">
              </el-slider>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span=4 align='middle'>
          <el-button @click="compute()" type="primary">确定</el-button>
        </el-col>
      </el-row>
      <div
          id="linechart"
          style="width: 100%; height: 250px"
      ></div>
      <el-divider></el-divider>
      <el-table
          v-loading="loading"
          :header-row-style="{ height: '20px' }"
          :cell-style="{ padding: '5px' }"
          ref="filterTable1"
          :data="tabledata"
          height=""
          stripe
          highlight-current-row
          style="width: 100%"
          :default-sort="{ prop: 'date', order: 'descending' }"
      >
        <el-table-column prop="changeRate" label="变化率(%)">
        </el-table-column>
        <el-table-column label="不确定因素">
          <el-table-column prop="income" sortable label="营业收入">
          </el-table-column>
          <el-table-column prop="investment" sortable label="建设投资">
          </el-table-column>
          <el-table-column prop="operatingCost" sortable label="运维成本">
          </el-table-column>
          <el-table-column prop="staffCost" sortable label="人员成本">
          </el-table-column>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
import store from "../../store/state";
import {GETLabs} from "../../API/http";
import * as echarts from 'echarts';

export default {
  //  components: {
  //     FeedbackDialog,
  //  },
  mounted() {
    this.myEcharts();
  },
  data() {
    return {
      loading: '',
      form: {
        step: 2,
        range: [-15, 15],
      },

      tabledata: [
        {
          changeRate: -15,
          income: -29.77,
          investment: 24.98,
          operatingCost: 24.40,
          staffCost: 49.42
        },
        {
          changeRate: 0,
          income: 19.85,
          investment: 19.85,
          operatingCost: 19.85,
          staffCost: 19.85
        },
        {
          changeRate: 15,
          income: 57.11,
          investment: 15.70,
          operatingCost: 15.23,
          staffCost: -13.57
        }
      ],

    };
  },

  watch: {
    form: {
      handler(newVal) {
        if (newVal.range[0] > 0) {
          this.form.range[0] = 0;
        }
        if (newVal.range[1] < 0) {
          this.form.range[1] = 0;
        }
      },
      deep: true,
      immediate: true,
    }
  },
  methods: {
    compute() {
        var params = [];
        for (var i = 0; i > this.form.range[0]; i -= this.form.step) {
          params.unshift(i);
        }
        for (var i = this.form.step; i < this.form.range[1]; i += this.form.step) {
          params.push(i);
        }
        console.log("params: ", params);
        this.myEcharts();
    },
    myEcharts() {
      var chartDom = document.getElementById('linechart');
      var myChart = echarts.init(chartDom);
      var option;

      option = {
        title: {
          text: '敏感性分析图'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['营业收入', '建设投资', '运维成本', '人员成本']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '营业收入',
            type: 'line',
            stack: 'Total',
            data: []
          },
          {
            name: '建设投资',
            type: 'line',
            stack: 'Total',
            data: []
          },
          {
            name: '运维成本',
            type: 'line',
            stack: 'Total',
            data: []
          },
          {
            name: '人员成本',
            type: 'line',
            stack: 'Total',
            data: []
          }
        ]
      };

      for (var item in this.tabledata) {
        // console.log(this.tabledata[item]);
        option.xAxis.data.push(this.tabledata[item].changeRate + '%');
        option.series[0].data.push(this.tabledata[item].income);
        option.series[1].data.push(this.tabledata[item].investment);
        option.series[2].data.push(this.tabledata[item].operatingCost);
        option.series[3].data.push(this.tabledata[item].staffCost);
      }

      console.log("option", option);

      option && myChart.setOption(option);
    }
  },
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
};
</script>

<style scpoed>
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

html,
body {
  padding: 0px;
  margin: 0px;
  height: 100%;
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