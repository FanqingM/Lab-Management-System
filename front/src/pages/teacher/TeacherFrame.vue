<template>
  <el-container height="100%">
    <el-header class="header" style="height: 60px">
      <el-row class="header-row">
        <el-col :span="1" class="header-row-col1">
          <el-row class="headarrow" type="flex" justify="left" align="middle">
              <router-link
                  :to="{path: '/teacher/courses',}"
                >
              <i class="el-icon-s-home"></i
            ></router-link>
          </el-row>
        </el-col>
        <el-col :span="20" class="header-row-col1">
          <div style="font-size: 30px">实验教学管理系统</div>
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
                {{ this.name }}<i class="el-icon-arrow-down el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="accountInfo"
                  >账号信息
                </el-dropdown-item>
                <el-dropdown-item command="modifyPassword"
                  >修改密码
                </el-dropdown-item>
                <el-dropdown-item command="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-row>
        </el-col>
      </el-row>
    </el-header>

    <el-container>
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
import { GETOrganizationsID } from "../../API/http";
import store from "../../store/state";
//import router from "../../router/index"
export default {
  created() {
    this.id = "123456";
    this.name = "测试账号";
    // GETOrganizationsID(this.id)
    //   .then((data) => {
    //     this.name = data.name;
    //   })
    //   .catch((err) => {
    //     console.log(err);
    //     this.$message("组织信息获取错误");
    //   });
  },
  data() {
    return {
      clock: null,
      option: null,
      isCollapse: true,
      value: "",
      name: "",
      id: store.state.ID,
    };
  },
  methods: {
    test() {
      this.isCollapse = !this.isCollapse;
    },
    handleClick() {
      this.$router.push("/student/account");
    },
    handleCommand(command) {
      switch (command) {
        case "accountInfo":
          this.$router.push({
            path: "/student/account",
          });
          break;
        case "modifyPassword":
          this.$message("功能不可用");
          // this.$router.push({
          //   path: "/student/modify-password",
          // });
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
        this.$router.push({ path: "/" });
      });
    },
    clickAccountInfo() {
      this.$router.push({
        path: "/GroundsAdmin/AccountModify",
      });
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
  border-radius: 15px;
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

.logoImage {
  height: 100%;
  padding-left: 20px;
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
.el-card {
  border-radius: 15px;
}
</style>