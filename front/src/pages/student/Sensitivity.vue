<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <span><b>敏感性分析实验</b></span>
      </div>
      <el-row >
        <el-col span="6" style="background-color: #42b983">
          <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="步长">
              <el-input-number v-model="form.step" :precision="2" :step="0.2" :max="5"></el-input-number>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col span="14" style="background-color: #cf0c0c">
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
        <el-col span="4">
          <el-button @click="compute()">确定</el-button>
        </el-col>
      </el-row>
      <div
          id="linechart"
          style="width: 1300px; height: 250px"
      ></div>
      <el-divider></el-divider>
      <el-table
          v-loading="loading"
          :header-row-style="{ height: '20px' }"
          :cell-style="{ padding: '5px' }"
          ref="filterTable1"
          :data="tabledata"
          height="465"
          stripe
          highlight-current-row
          @current-change="handleCurrentChange1"
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
import { GETLabs } from "../../API/http";
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
      form: {
        step: 0.6,
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
        }],

    };
  },

  watch: {
    form: {
      handler(newVal) {
        // console.log("form", newVal);
        params = []
        for (var i = 0; i > newVal.range[0]; i -= newVal.step) {
          params.unshift(i);
        }
        for (var i = newVal.step; i < newVal.range[1]; i += newVal.step) {
          params.push(i);
        }
        console.log("params: ", params);
      },
      deep: true,
  　　immediate: true,
    }
  },
  methods: {
    compute(){

    },
    myEcharts() {
      var chartDom = document.getElementById('linechart');
      var myChart = echarts.init(chartDom);
      var option;

      option = {
        title: {
          text: 'Stacked Line'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['Email', 'Union Ads', 'Video Ads', 'Direct', 'Search Engine']
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
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: 'Email',
            type: 'line',
            stack: 'Total',
            data: [120, 132, 101, 134, 90, 230, 210]
          },
          {
            name: 'Union Ads',
            type: 'line',
            stack: 'Total',
            data: [220, 182, 191, 234, 290, 330, 310]
          },
          {
            name: 'Video Ads',
            type: 'line',
            stack: 'Total',
            data: [150, 232, 201, 154, 190, 330, 410]
          },
          {
            name: 'Direct',
            type: 'line',
            stack: 'Total',
            data: [320, 332, 301, 334, 390, 330, 320]
          },
          {
            name: 'Search Engine',
            type: 'line',
            stack: 'Total',
            data: [820, 932, 901, 934, 1290, 1330, 1320]
          }
        ]
      };

      option && myChart.setOption(option);
    }
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