
<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <span><b>我的课程</b></span>
      </div>
      <el-main>
        <div class="select_container">
          <el-row :gutter="25">
            <el-col
              :span="5"
              v-for="(item, index) in r_fdnamelist"
              :key="index"
              class="ecol"
            >
              <el-checkbox @change="selectfd(item)" class="elcheckbox" border>{{
                item.name
              }}</el-checkbox>
            </el-col>
          </el-row>
        </div>

        <div class="card_container">
          <el-row gutter="25">
            <el-col
              :span="5"
              v-for="item in r_dnamelist"
              :key="item.dname"
              class="ecol"
            >
              <el-card
                :body-style="{ padding: '2px' }"
                shadow="hover"
                class="ecard"
                @click="todoctor(item.id, item.dname)"
              >
                <div class="card_text">
                  <font size="4" color="black"> {{ item.dname }}</font>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </el-main>
    </el-card>
  </div>
</template>

<script>
import store from "../../store/state";
import { GETCoursesByStudentID } from "../../API/http";
export default {
  data() {
    return {
      semester: 1,
      year: "2021-2022",
      courses: [],
    };
  },
  mounted() {
    GETCoursesByStudentID({
      studentId: store.state.id,
      semeter: this.semester,
      year: this.year,
    })
      .then((data) => {
        console.log("data", data);
        this.courses = data;
      })
      .catch((err) => {
        console.log(err);
        this.$message("课程信息获取错误");
      });
    this.loading = false;
  },
  methods: {
    selectfd(pra) {
      if (target.checked) {
        this.s_fdnamelist.push(pra);
      } else {
        var j;
        for (var i = 0; i < this.s_fdnamelist.length; i++) {
          if (this.s_fdnamelist[i].name == pra.name) j = i;
        }
        this.s_fdnamelist.splice(j, 1);
      }
      this.r_dnamelist.splice(0);

      if (this.s_fdnamelist.length > 0) {
        for (i = 0; i < this.dnamelist.length; i++)
          for (j = 0; j < this.s_fdnamelist.length; j++)
            if (this.dnamelist[i].fname == this.s_fdnamelist[j].name) {
              this.r_dnamelist.push(this.dnamelist[i]);
              continue;
            }
        // console.log('1');
        // console.log(this.s_fdnamelist);
        // console.log(this.dnamelist);
        // console.log(this.fdnamelist);
        // console.log(this.r_dnamelist);
        // console.log(this.r_fdnamelist);
      } else {
        this.r_dnamelist.splice(0);
        this.r_dnamelist = this.getobj.map((item) => {
          return Object.assign(
            {},
            { dname: item.departmentName, fname: item.fatherDepartmentId }
          );
        });
        // console.log('2');
        // console.log(this.s_fdnamelist);
        // console.log(this.dnamelist);
        // console.log(this.fdnamelist);
        // console.log(this.r_dnamelist);
        // console.log(this.r_fdnamelist);
      }

      // console.log(pra.name);
      // console.log(event.target.checked);
    },
    todoctor(id, dname) {
      localStorage.setItem("SelectDepartmentId", id);
      localStorage.setItem("SelectDepartmentName", dname);
      console.log(id);
      console.log(dname);
      this.$router.push("/doctor");
    },
    initdata() {
      fetch(this.url + this.urlid + "/all")
        // 第一个 then 接受到的是请求头的相关信息
        .then((res) => {
          return res.json();
        })
        .then((res) => {
          this.getobj = res;
          this.dnamelist.splice(0);
          this.fdnamelist.splice(0);

          this.dnamelist = this.getobj.map((item) => {
            return Object.assign(
              {},
              {
                dname: item.departmentName,
                fname: item.fatherDepartmentName,
                id: item.departmentId,
              }
            );
          });
          this.r_dnamelist = this.getobj.map((item) => {
            return Object.assign(
              {},
              {
                dname: item.departmentName,
                fname: item.fatherDepartmentName,
                id: item.departmentId,
              }
            );
          });

          this.tempObj = this.getobj.map((item) => {
            return Object.assign({}, { name: item.fatherDepartmentName });
          });
          for (var i = 0; i < this.tempObj.length; i++) {
            for (var j = i + 1; j < this.tempObj.length; j++) {
              if (this.tempObj[i].name === this.tempObj[j].name) {
                this.tempObj.splice(j, 1);
                j--;
              }
            }
          }
          this.fdnamelist = this.tempObj.map((item) => {
            return Object.assign({}, { name: item.name });
          });
          this.r_fdnamelist = this.tempObj.map((item) => {
            return Object.assign({}, { name: item.name });
          });

          // console.log(this.dnamelist);
          // console.log(this.fdnamelist);
        })
        // 请求错误时执行
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style scoped>
.ecard {
  border-radius: 7px;
  background: #d7f0fa;
  height: 80px;
  text-align: center;
}
.card_text {
  margin-top: 10%;
  margin-left: 10%;
  margin-right: 10%;
}
.select_container {
  width: 80%;
  position: relative;
  left: 16%;
  top: 0%;
}
.elcheckbox {
  background: white;
}
.elcheckbox:hover {
  background: #a2d8ee;
}
.card_container {
  width: 80%;
  position: relative;
  left: 16%;
  top: 0%;
  text-align: center;
}
.ecol {
  padding: 0.6%;
}
.etag1 {
  position: relative;
  left: 3%;
  top: 50px;
}
.el-row {
  margin-bottom: 0px;
}
.el-col {
  border-radius: 4px;
}
.el-card {
  height: 100%;
  border-radius: 15px;
}
</style>
