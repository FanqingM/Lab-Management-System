<template>
  <div>
    <el-card class="mycard">
      <div slot="header" class="clearfix">
        <el-row type="flex" align="middle">
          <el-col :span="18">
            <span><b>用户管理</b></span>
          </el-col>
          <el-col :span="2" class="buttoncol">
            <el-button size="medium" @click="addUser()" type="primary" plain
              >添加用户</el-button
            >
          </el-col>
          <el-col :span="4">
            <el-input
              clearable
              v-model="toMatch"
              placeholder="输入学工号以搜索"
              @input="search"
            ></el-input>
          </el-col>
        </el-row>
      </div>

      <el-table
        :data="matchList"
        max-height="500"
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
                size="small"
                type="primary"
                tag="el-button"
                >编辑信息</router-link
              >
              <el-button
                size="small"
                type="danger"
                @click="deleteUser(scope.$index, scope.row)"
                >删除</el-button
              >
            </div>
            <div v-else>
              <router-link
                :to="{
                  path: '/admin/modify-instructor',
                  query: {
                    id: scope.row.id,
                  },
                }"
                size="small"
                type="primary"
                tag="el-button"
                >编辑信息</router-link
              >
              <el-button
                size="small"
                type="danger"
                @click="deleteUser(scope.$index, scope.row)"
                >删除</el-button
              >
            </div>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<style scoped>
html,
body,
.mycard {
  height: 100%;
  border-radius: 12px;
}
.buttoncol {
  float: right;
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

.maintitle {
  font-size: 22px;
  text-align: left;
  font-weight: bold;
  padding: 5px;
  margin-bottom: 30px;
}
.el-card {
  border-radius: 15px;
}
</style>

<script>
import {
  GETInstructors,
  GETStudents,
  DELETEStudent,
  DELETEInstructor,
} from "../../API/http";

export default {
  mounted() {
    this.fetchData();
  },

  methods: {
    filterTag(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    },

    fetchData() {
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
      this.matchList = this.userList;
    },
    addUser() {
      this.$router.push({ path: "AddUserTest" });
    },

    deleteStudent(id, index) {
      DELETEStudent(id)
        .then(() => {
          console.log("删除学生成功");
          this.userList.splice(index, 1);
          this.$message({
            type: "success",
            message: "删除成功",
          });
        })
        .catch((err) => {
          console.log(err);
          this.$message("删除失败");
        });
    },
    deleteInstructor(id, index) {
      DELETEInstructor(id)
        .then(() => {
          console.log("删除教师成功");
          this.userList.splice(index, 1);
          this.$message({
            type: "success",
            message: "删除成功",
          });
        })
        .catch((err) => {
          console.log(err);
          this.$message("删除失败");
        });
    },

    deleteUser(index, row) {
      const that = this;
      console.log(index, row);
      this.$confirm("此操作将永久删除该用户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          that.deleted = false;
          for (var i = 0; i < that.userList.length; i++) {
            if (that.userList[i].id == row.id) {
              if (row.type == "student") {
                that.deleteStudent(row.id, i);
              } else {
                that.deleteInstructor(row.id, i);
              }
              break;
            }
          }
        })
        .catch(() => {
          that.$message({
            type: "info",
            message: "已取消",
          });
        });
    },

    search: function () {
      if (this.toMatch == "") {
        this.matchList = this.userList;
      } else {
        this.matchList = [];
        for (var i = 0; i < this.userList.length; i++) {
          if (this.userList[i].id.search(this.toMatch) != -1) {
            this.matchList.push(this.userList[i]);
          }
        }
      }
    },
  },

  data() {
    return {
      userList: [],
      toMatch: "",
      toMatchStatus: "",
      matchList: [],
    };
  },
};
</script>