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

        </el-card>
      </el-col>
    </el-row>
    <el-row class="lower-row">
      <el-col :span="12" class="lower-row-col1">
        <el-card class="lower-card">
          <div slot="header" class="clearfix">
            <span><b>待审核注册</b></span>
            <router-link to="/admin/verification">
              <el-button style="float: right; padding: 3px 0" type="text"
                >查看更多</el-button
              >
            </router-link>
          </div>
<el-table 
          :data="unactivatedAccounts"
          stripe
          style="width: 100%"
          :header-row-style="{ height: '20px' }"
          :cell-style="{ padding: '5px' }"
        >
          <el-table-column prop="id" label="负责人">
          </el-table-column>

          <el-table-column prop="name" label="姓名">
          </el-table-column>

          <el-table-column label="类型">
            <template slot-scope="scope">{{(scope.row.isTeacher == 0) ? "学生" : "教师"}}</template>
          </el-table-column>
          
          <el-table-column label="操作">
            <template slot-scope="scope">
              <router-link
                :to="{
                  path: '/admin/verification-detail',
                  query: {
                    ID: scope.row.id,
                  },
                }"
              >
                <el-button type="text">审核</el-button>
              </router-link>
              
            </template>
          </el-table-column>
        </el-table>
        </el-card>
      </el-col>
      <el-col :span="12" class="lower-row-col2">
        <el-card class="lower-card">
          <div slot="header" class="clearfix">
            <span><b>账户信息</b></span>
            <router-link to="/admin/user-management">
              <el-button style="float: right; padding: 3px 0" type="text"
                >查看更多</el-button
              >
            </router-link>
          </div>
          <el-table
            :data="userList"
            stripe
            style="width: 100%"
            :header-row-style="{ height: '20px' }"
            :cell-style="{ padding: '5px' }"
          >
            <el-table-column label="学工号" prop="id"> </el-table-column>
            <el-table-column label="姓名" prop="name"> </el-table-column>
            <el-table-column label="学院" prop="schoolName"> </el-table-column>
            <el-table-column
              label="类型"
              prop="type"
              :filters="[
                { text: '学生', value: 'student' },
                { text: '教师', value: 'instructor' },
              ]"
              :filter-method="filterTag"
              filter-placement="bottom-end"
            >
              <template slot-scope="scope">
                <el-tag
                  :type="scope.row.type == 'student' ? 'primary' : 'success'"
                  disable-transitions
                >
                  {{ scope.row.type == "student" ? "学生" : "教师" }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <div v-if="scope.row.type == 'student'">
                  <router-link
                    :to="{
                      path: '/admin/modify-student',
                      query: {
                        id: scope.row.id,
                      },
                    }"
                  >
                  <el-button type="text">编辑信息</el-button>
                  </router-link>
                </div>
                <div v-else>
                  <router-link
                    :to="{
                      path: '/admin/modify-instructor',
                      query: {
                        id: scope.row.id,
                      },
                    }"
                  >
                  <el-button type="text">编辑信息</el-button>
                  </router-link>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
    <!-- <el-dialog :visible.sync="dialogVisible" width="50%" class="dialog">
      <span slot="title">
        <h3>{{ dialogTitle }}</h3>
      </span>
      <div class="content">
        <span>{{ dialogContent }}</span>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false"
          >确定</el-button
        >
      </span>
    </el-dialog> -->
  </div>
</template>


<script>
import store from "../../store/state";
import {
  GETAdminID,
  GETUnactivatedAccounts,
  GETStudents,
  GETInstructors,
  GETSystemAdministratorsID,

  // GETStudentsID,
  // GETActivities,
  // GETOccupyTimes
} from "../../API/http";
export default {
  created() {
    GETAdminID(store.state.id)
      .then((data) => {
        this.accountData = data;
        console.log(this.accountData);
      })
      .catch((err) => {
        console.log(err);
        this.$message("学生信息获取错误");
      });
    //获取场地公告
    GETUnactivatedAccounts()
      .then((data) => {
        this.unactivatedAccounts = data;
      })
      .catch((err) => {
        console.log(err);
        this.$message("数据请求错误");
      });

    //获取学生信息
    GETStudents()
      .then((data) => {
        this.userInfo = data;
        console.log(">>>>>>", data);
      })
      .catch((err) => {
        console.log(err);
        this.$message("学生信息请求错误");
      });

    //获取管理员信息
    GETSystemAdministratorsID(store.state.ID)
      .then((data) => {
        this.sysInfo = data;
        this.sysInfo.image = "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png";
        console.log(">>>>>>", data);
      })
      .catch((err) => {
        console.log(err);
        this.$message("管理员信息请求错误");
      });
  },
  mounted() {
    this.userList = [];
      GETStudents()
        .then((data) => {
          console.log(data);
          for (var i = 0; i < data.length; i++) {
            var item = data[i];
            item["type"] = "student";
            this.userList.push(item);
          }
        })
        .catch((err) => {
          console.log(err);
          this.$message("数据请求错误");
        });
      GETInstructors()
        .then((data) => {
          for (var i = 0; i < data.length; i++) {
            var item = data[i];
            item["type"] = "instructor";
            this.userList.push(item);
          }
        })
        .catch((err) => {
          console.log(err);
          this.$message("数据请求错误");
        });
  },
  data() {
    return {
      accountData: null,
      dialogTitle: "",
      dialogContent: "",
      dialogVisible: false,
      unactivatedAccounts: [],
      groundAnnouncement: [],
      systemAnnouncement: [],

      axiosdata: null,

      //第一块卡片信息
      // SysID=store.stats.ID,
      sysInfo: {},
      semesterInfo: {
        fromYear: "2020",
        toYear: "2021",
        semester: "2",
        week: "14",
      },
      //第三块卡片信息
      groupInfo: [],
      //第四片卡片信息
      userInfo: [
        {
          userid: "123",
          account: "你",
        },
        {
          userid: "456",
          account: "我",
        },
        {
          userid: "789",
          account: "他",
        },
        {
          userid: "321",
          account: "她",
        },
        {
          userid: "654",
          account: "它",
        },
        {
          userid: "987",
          account: "嗨",
        },
      ],
    };
  },
  methods: {
    dealWithAnnouncements(data) {
      console.log("run dealwith");
      for (var i = 0; i < data.length; i++) {
        var temp = {
          accountNumber: "123123",
          title: "关于饮水机的公告##C楼饮水机坏了，望周知",
          systemAnnouncementDate: "2020-05-17T00:00:00",
          content: "C楼饮水机坏了，望周知",
        };
        temp.accountNumber = data[i].accountNumber;
        temp.systemAnnouncementDate = data[i].systemAnnouncementDate.replace(
          "T",
          " "
        );
        temp.title = data[i].content.substr(0, data[i].content.search("##"));
        temp.content = data[i].content.slice(data[i].content.search("##") + 2);
        this.systemAnnouncement.push(temp);
      }
      console.log(this.systemAnnouncement);
    },
  },
};
</script>

<style scoped>
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
.upper-card{
  height: 100%;
  border-radius: 10px;
}
.lower-card {
  height: 100%;
  border-radius: 10px;
}
/* .el-dialog {
  border-radius: 12px;
} */
/* .dialog {
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
.lower-row-col1{
  height: 100%;
  padding: 5px;
}
.lower-row-col2{
  height: 100%;
  padding: 5px;
}
.upper-row-col1{
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