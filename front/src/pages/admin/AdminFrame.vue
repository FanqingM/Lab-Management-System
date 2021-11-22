<template>
  <el-container style="height: 100%">
    <el-header class="header" style="height: 60px">
      <el-row class="header-row">
        <el-col :span="1" class="header-row-col1">
          <el-row class="headarrow" type="flex" justify="left" align="middle">
            <el-button class="fold-button" @click="test" type="text">
              <i v-if="isCollapse" class="el-icon-s-unfold"></i
              ><i v-else class="el-icon-s-fold"></i
            ></el-button>
          </el-row>
        </el-col>
        <el-col :span="20" class="header-row-col1">
          <div style="font-size:30px">实验教学管理系统</div>
        </el-col>
        <el-col :span="3" class="header-row-col3">
          <el-row class="headarrow" type="flex" justify="end" align="middle">
            <el-button type="text" @click="handleClick">
              <el-avatar
                src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
              ></el-avatar>
            </el-button>
            <el-dropdown trigger="click" @command="handleCommand">
              <span class="el-dropdown-link" trigger="click">
                {{ this.accountData.name }}<i class="el-icon-arrow-down el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="accountInfo"
                  >账号信息</el-dropdown-item
                >
                <el-dropdown-item command="modifyPassword"
                  >修改密码</el-dropdown-item
                >
                <el-dropdown-item command="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-row>
        </el-col>
      </el-row>
    </el-header>

    <el-container style="height: calc(100% - 60px)">
      <el-aside style="width: auto">
        <el-menu
          class="el-menu-vertical-demo"
          default-active="/admin/home"
          router
          active-text-color="#409EFF"
          :collapse="isCollapse"
          style="height: 100%"
        >
          <el-menu-item index="/admin/home">
            <i class="el-icon-house"></i>
            <span style="font-size: 14px">主页</span>
          </el-menu-item>
          <el-menu-item index="/admin/verification">
            <i class="el-icon-finished"></i>
            <span style="font-size: 14px">审核注册</span>
          </el-menu-item>
          <el-menu-item index="/admin/user-management">
            <i class="el-icon-edit"></i>
            <span style="font-size: 14px">用户管理</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-main
        style="height: 100%; overflow: auto; background: rgb(237, 241, 245)"
      >
        <keep-alive>
          <transition name="fade-transform" mode="out-in">
            <router-view style="height: 100%"></router-view>
          </transition>
        </keep-alive>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import store from "../../store/state"
import { GETAdminID } from "../../API/http";
import * as echarts from "echarts";
//import router from "../../router/index"
export default {
  data() {
    return {
      timeUpdatedStatus: {
        second: false,
        minute: false,
        hour: false,
      },
      accountData: {
        id: "",
        email: "",
        name: "",
        classnum: "",
        schoolName: ""
      },
      nowWeek: "",
      nowDate: "",
      nowTime: "",
      weeks: [
        "星期日",
        "星期一",
        "星期二",
        "星期三",
        "星期四",
        "星期五",
        "星期六",
      ],
      clock: null,
      option: null,
      isCollapse: true,
      value: "",
      breadList: [],
    };
  },
  mounted() {
    GETAdminID(store.state.id)
    .then((data) => {
      this.accountData = data;
      console.log(this.accountData);
    })
    .catch((err) => {
      console.log(err);
      this.$message("账户信息获取错误");
    });
    this.breadList = this.$route.matched;
    this.drawClock();
    console.log("option", this.option);
    const that = this;
    setInterval(function () {
      var date = new Date();
      var second = date.getSeconds();
      var minute = date.getMinutes() + second / 60;
      var hour = (date.getHours() % 12) + minute / 60;
      //console.log(that.option);
      that.updateSeries(second, that.option.series[2], "second");
      that.updateSeries(minute, that.option.series[1], "minute");
      that.updateSeries(hour, that.option.series[0], "hour");
      that.option.animationDurationUpdate = 300;
      that.reNewTime();
      //console.log("Week", that.nowTime);
      that.clock.setOption(that.option, true);
      date = null;
    }, 1000);
  },
  methods: {
    test() {
      this.isCollapse = !this.isCollapse;
    },

    handleClick() {
      this.$router.push("/SysAdminFrame/AccountModify");
    },
    /**
     * @description 获取路由数组
     * @params val 路由参数
     */

    handleCommand(command) {
      switch (command) {
        case "accountInfo":
          this.$router.push({ path: "/SysAdminFrame/AccountModify" });
          break;
        case "modifyPassword":
          this.$router.push({ path: "/SysAdminFrame/ModifyPassword" });
          break;
        default:
          this.confirmLogout();
      }
    },

    confirmLogout() {
      this.$confirm("确认退出登录?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        localStorage.removeItem("uuid");
        localStorage.removeItem("uutype");
        localStorage.removeItem("uutoken");
        store.state.ID = null;
        // const routeHistory = history.length - 1;
        // router.go(-routeHistory);
        // router.replace("/");
        this.$router.push({path: "/"});
      });
    },

    getBreadList(val) {
      // 过滤路由matched对象
      console.log("val.matched", val.matched);
      if (val.matched) {
        let matched = val.matched.filter(
          (item) => item.meta && item.meta.title
        );
        console.log("matched", matched);
        // 拿到过滤好的路由v-for遍历出来
        this.breadList = matched;
      }
      this.breadList = val.matched;
      console.log("this.breadList", this.breadList);
    },
    updateSeries(time, series, type) {
      var isCritical =
        Math.floor(time) === 0 || (type === "second" && time === 1);
      if (isCritical && this.timeUpdatedStatus[type] === true) {
        this.timeUpdatedStatus[type] = false;
        series.data[0].value = 0;
        series.clockwise = true;
        this.option.animationDurationUpdate = 0;
        //this.myChart.setOption(this.option, true);
      }
      series.data[0].value = time;
      series.clockwise = true;
      if (time === 0) {
        this.timeUpdatedStatus[type] = true;
        series.clockwise = false;
      }
    },
    reNewTime() {
      let myDate = new Date();
      let wk = myDate.getDay();
      let yy = String(myDate.getFullYear());
      let mm = myDate.getMonth() + 1;
      let dd = String(
        myDate.getDate() < 10 ? "0" + myDate.getDate() : myDate.getDate()
      );
      let hou = String(
        myDate.getHours() < 10 ? "0" + myDate.getHours() : myDate.getHours()
      );
      let min = String(
        myDate.getMinutes() < 10
          ? "0" + myDate.getMinutes()
          : myDate.getMinutes()
      );
      let sec = String(
        myDate.getSeconds() < 10
          ? "0" + myDate.getSeconds()
          : myDate.getSeconds()
      );
      let week = this.weeks[wk];
      this.nowDate = yy + "-" + mm + "-" + dd;
      this.nowTime = hou + ":" + min + ":" + sec;
      this.nowWeek = week;
    },
  },
  watch: {
    // 监听路由
    $route(val) {
      // 调用获取路由数组方法
      this.getBreadList(val);
      console.log("调用获取路由数组方法", this.getBreadList(val));
    },
  },
};
</script>


<style scoped>
html,
body,
#app,
.header {
  background-color: white;
  padding-left: 16px;
  border-bottom: 0.5px solid rgba(85, 79, 78, 0.178);
}
.el-main {
  overflow: auto;
  /* background-color: wheat; */
  background-color: rgb(0, 128, 255);
}
.el-card {
  border-radius: 10px;
}
.header-row {
  height: 100%;
}
.header-row-col1 {
  height: 100%;
}
.header-row-col2 {
  height: 100%;
  /* background-color: azure; */
}
.header-row-col3 {
  height: 100%;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
.fold-button {
  font-size: 30px;

  /* border-style: hidden;
  border-color: white;
  background-color: white; */
}
.headarrow {
  font-size: 30px;
  height: 100%;
}
/* .el-icon-s-unfold {
  height: 40px;
  width: 40px;
} */
</style>