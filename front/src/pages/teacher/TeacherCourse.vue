
<template>
  <div>
    <div slot="header" class="clearfix">
      <span><b>我的课程</b></span>
    </div>
    <el-main>
      <div class="card_container">
        <el-row :gutter="25">
          <el-col
            :span="6"
            v-for="item in courses"
            :key="item.courseId"
            class="ecol"
          >
            <el-card
              :body-style="{ padding: '2px' }"
              shadow="hover"
              class="ecard"
            >
              <!-- @click="todoctor(item.id, item.dname)" -->
              <div class="card_text">
                <img
                  src="https://edu-image.nosdn.127.net/6AE1A85C007E8E946EEC979BC9AB04A1.jpg?imageView&thumbnail=510y288&quality=100"
                  width="100%"
                />
                <p class="font-size:5">
                  <b> {{ item.courseName }} </b>
                </p>
                <p class="font-size:4">{{ item.courseId }}</p>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </el-main>
  </div>
</template>

<script>
import store from "../../store/state";
import { GETCoursesByTeacherID } from "../../API/http";
export default {
  data() {
    return {
      semester: 1,
      year: "2021-2022",
      courses: [],
    };
  },
  mounted() {
    // for (let i = 0; i < 80; ++i) {
    //   this.courses.push({
    //     courseId: String(420343 + i),
    //     courseName: "编译原理",
    //     sectionId: "01",
    //     semeter: true,
    //     year: "2021-2022",
    //   });
    // }
    GETCoursesByTeacherID({
      teacherId: store.state.id,
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
  methods: {},
};
</script>

<style scoped>
.ecard {
  border-radius: 7px;
  background: #ffffff;

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
  background: #ffffff;
  box-shadow: 0 25px 50px rgba(0, 0, 0, 0.5);
}
.card_container {
  width: 100%;
  position: relative;
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
  overflow: auto;
}
</style>
