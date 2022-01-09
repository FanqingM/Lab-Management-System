<template>
  <div>
    <el-row :gutter="25">
      <el-col :span="6">
        <img :src="getImg(sectionInfo.courseId)" style="width: 100%" />
        <p class=""><b>课程名称：</b>{{ sectionInfo.courseName }}</p>
        <p class=""><b>课程代号：</b>{{ sectionInfo.courseId }}</p>
        <p class=""><b>班级代号：</b>{{ sectionInfo.sectionId }}</p>
        <p class="">
          <b>课程学期：</b
          >{{ sectionInfo.year + (sectionInfo.semeter ? "秋" : "春") }}
        </p>
        <p class=""><b>课程学分：</b>{{ sectionInfo.credits }}</p>
      </el-col>
      <el-col :span="18">
        <el-tabs v-model="activeName">
          <el-tab-pane label="实验" name="first">
            <el-table
              :header-row-style="{ height: '20px' }"
              :cell-style="{ padding: '5px' }"
              :data="lanInstanceInfo"
              stripe
              highlight-current-row
              style="width: 100%"
              :default-sort="{ prop: 'labId', order: 'descending' }"
            >
              <el-table-column prop="labId" label="实验ID"></el-table-column>
              <el-table-column prop="name" label="实验名称"> </el-table-column>
              <el-table-column prop="endTime" sortable label="截止时间">
              </el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <router-link
                    :to="{
                      name: 'TeacherLabInstanceInfo',
                      params: {
                        courseId: scope.row.courseId,
                        sectionId: scope.row.sectionId,
                        labId: scope.row.labId,
                      },
                    }"
                  >
                    <el-button @click="handleClick(scope.row)" type="text"
                      >查看详情
                    </el-button>
                  </router-link>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="文件" name="second">
            <el-upload
              class="upload-demo"
              action="http://139.196.114.7:9094/teacher/addFile"
              :data="{courseId: sectionInfo.courseId}"
              name="file"
              :on-success="onSuccess"
              :on-error="onError"
              multiple
              :file-list="uploadFiles"
            >
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
            <el-table
              :header-row-style="{ height: '20px' }"
              :data="files"
              stripe
              highlight-current-row
              style="width: 100%"
              :default-sort="{ prop: 'date', order: 'descending' }"
            >
              <el-table-column label="文件名">
                <template slot-scope="scope">
                  <el-link
                    :href="
                      'http://139.196.114.7:9094/student/downloadFile?filename=' +
                      scope.row.fileName
                    "
                    target="_blank"
                    >{{ scope.row.fileName }}</el-link
                  >
                </template>
              </el-table-column>
              <el-table-column prop="time" label="上传时间"> </el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button type="text" icon="el-icon-delete" style="color: red" @click="deleteFile(scope.$index)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
        </el-tabs>
      </el-col>
    </el-row>
  </div>
</template>


<style scoped>
html,
body,
#app,
.el-container {
  /*设置内部填充为0，几个布局元素之间没有间距*/
  padding: 0px;
  /*外部间距也是如此设置*/
  margin: 0px;
  /*统一设置高度为100%*/
  height: 100%;
}

.upper-row {
  height: 45%;
  padding-bottom: 15px;
}

.lower-row {
  height: 55%;
}

.maintitle {
  text-align: left;
  font-size: 20px;
  font-weight: 600;
}

.maincard {
  border-radius: 15px;
  height: 100%;
}

.detailinfo {
  padding-left: 15px;
}

.el-card {
  border-radius: 15px;
}
</style>


<script>
import {
  GETFiles,
  GETLabInstanceOfSection,
  GETSectionInfo,
} from "../../API/http";

// import store from "../../state/state.js"
export default {
  name: "creditscoring",
  data() {
    return {
      sectionInfo: {
        courseId: "",
        courseName: "",
        sectionId: "",
      },
      lanInstanceInfo: [],
      files: [],
      uploadFiles: [],
      score: null,
      activeName: "first",
    };
  },
  mounted() {
    const that = this;
    console.log(that.$route.params.courseId, that.$route.params.sectionId);
    GETSectionInfo({
      courseId: that.$route.params.courseId,
      section_Id: that.$route.params.sectionId,
    })
      .then((data) => {
        that.sectionInfo = data;
        console.log("sectionInfo", that.sectionInfo);
      })
      .catch((err) => {
        console.log(err);
        this.$message("评分数据请求错误");
      });
    GETLabInstanceOfSection({
      courseId: that.$route.params.courseId,
      sectionId: that.$route.params.sectionId,
    }).then((data) => {
      for (let i = 0; i < data.length; ++i) {
        data[i].endTime = data[i].endTime.slice(0, 10);
      }
      that.lanInstanceInfo = data;
      console.log("lanInstanceInfo", that.lanInstanceInfo);
    });
    GETFiles(that.$route.params.courseId)
      .then((data) => {
        for (let i = 0; i < data.length; ++i) {
          data[i].time = data[i].time.slice(0, 10);
        }
        this.files = data;
      })
      .catch((err) => {
        console.log(err);
        this.$message("文件获取失败");
      });
  },
  methods: {
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
    onSuccess(response, file, fileList) {
      this.$message("文件上传成功");
      GETFiles(this.$route.params.courseId)
      .then((data) => {
        for (let i = 0; i < data.length; ++i) {
          data[i].time = data[i].time.slice(0, 10);
        }
        this.files = data;
        this.uploadFiles.shift();
      })
      .catch((err) => {
        console.log(err);
      });
    },
    onError(err, file, fileList) {
      this.$message.error("选择的文件过大");
      console.log(err);
    },
    deleteFile(index) {
      console.log(index);
      this.$confirm("确认删除" + this.files[index].fileName + "?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.files.splice(index);
          this.$message({
            type: "success",
            message: "已删除!",
          });
        })
        .catch(() => {});
    },
    form() {},
  },
};
</script>