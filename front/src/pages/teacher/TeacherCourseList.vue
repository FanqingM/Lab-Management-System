<template>
  <div>
    <div slot="header" class="clearfix">
      <span><b>我的课程</b></span>
    </div>
    <el-main>
      <div class="card_container">
        <el-row :gutter="25" type="flex">
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
              @click.native="viewCourse(item.courseId, item.sectionId)"
            >
              <!-- @click="todoctor(item.id, item.dname)" -->
              <div class="card_text">
                <img :src="getImg(item.courseId)" width="100%" />
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
  methods: {
    viewCourse(courseId, sectionId) {
      console.log(courseId);
      this.$router.push({
              name: 'TeacherSectionInfo',
              params: {
                courseId: courseId,
                sectionId: sectionId,
              }});
    },
    getImg(courseId) {
      switch (courseId) {
        case "420244":
          return "http://nos.netease.com/edu-image/bf3299ba3762452e96b1fc871185d9a7.jpg";
        case "420343":
          return "https://edu-image.nosdn.127.net/6AE1A85C007E8E946EEC979BC9AB04A1.jpg?imageView&thumbnail=510y288&quality=100";
        case "420679":
          return "https://edu-image.nosdn.127.net/0BA6974706F28E52F053981570841423.jpg?imageView&thumbnail=510y288&quality=100";
        case "420680":
          return "http://edu-image.nosdn.127.net/580BC7EE28CC1036DBB263985C57848E.jpg?imageView&thumbnail=426y240&quality=100";
        case "420681":
          return "http://img-ph-mirror.nosdn.127.net/Ove82eB-nUtuC-8mfX7EMQ==/6630543298653738028.jpg";
        case "420682":
          return "http://edu-image.nosdn.127.net/A7C6B5B16043CC2C4D46ED5FA94AEEE2.jpg?imageView&thumbnail=426y240&quality=100";
        default:
          return "http://img-ph-mirror.nosdn.127.net/tYhzuDVilzlDOo2bEyH_Qg==/6608226511143817333.jpg";
      }
    },
  },
};
</script>

<style scoped>
.ecard {
  border-radius: 7px;
  background: #ffffff;
  text-align: center;
  min-height: 100%;
  height: 100%;
  margin: 20px;
}
.ecard >>> .el-card__body {
  height: 100%;
}
.card_text {
  margin-top: 5%;
  margin-left: 5%;
  margin-right: 5%;
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
  height: 300px;
  padding: 0.5%;
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
  border-radius: 10px;
  overflow: auto;
}
</style>
