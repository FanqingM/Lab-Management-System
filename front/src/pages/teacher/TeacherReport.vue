<template>
  <el-card class="maincard">
    <div slot="header" class="clearfix">
      <span><b>实验报告</b></span>
    </div>
    <el-row class="mainrow" type="flex">
      <el-col class="col1" :span="12">
        <el-form
            ref="reportform"
            :rules="rules"
            :model="ruleform"
            label-width="100px"
        >
          <el-form-item label="实验目的" prop="purpose">
            <el-input
                clearable
                class="input"
                type="textarea"
                :rows="2"
                placeholder="请输入内容"
                v-model="ruleform.purpose"
                maxlength="400"
                show-word-limit
            >
            </el-input>
          </el-form-item>
          <el-form-item label="实验原理" prop="principle">
            <el-input
                clearable
                class="input"
                type="textarea"
                :rows="2"
                placeholder="请输入内容"
                v-model="ruleform.principle"
                maxlength="400"
                show-word-limit
            >
            </el-input>
          </el-form-item>
          <el-form-item label="实验步骤" prop="progress">
            <el-input
                clearable
                class="input"
                type="textarea"
                :rows="2"
                placeholder="请输入内容"
                v-model="ruleform.progress"
                maxlength="400"
                show-word-limit
            >
            </el-input>
          </el-form-item>
          <el-form-item label="分数" prop="grades">
            <el-input v-model.number="ruleform.grades"></el-input>
          </el-form-item>
          <el-form-item align="center">
            <el-button
                size="medium"
                type="primary"
                @click="submitForm('reportform')"
            >提交
            </el-button
            >
            <el-button size="medium" @click="back">取消</el-button>
          </el-form-item>

        </el-form>
      </el-col>

      <el-col class="col2" :span="12">
        <!--        &lt;!&ndash; <el-card> &ndash;&gt;-->
        <!--        <transition name="fade-transform" mode="in-out">-->
        <!--          &lt;!&ndash; <div v-if="this.groundId != null" style="padding-left:5%;height:500px"> &ndash;&gt;-->
        <!--          <div v-if="this.groundId != null">-->
        <!--            <Mycalendar-->
        <!--              class="calendar-card"-->
        <!--              :groundId="groundId"-->
        <!--              @handleSelect="myfun($event)"-->
        <!--            />-->
        <!--          </div>-->
        <!--          &lt;!&ndash; </div> &ndash;&gt;-->
        <!--        </transition>-->
        <!--        &lt;!&ndash; </el-card> &ndash;&gt;-->
      </el-col>
    </el-row>
  </el-card>
</template>

<script scoped>
import store from "../../store/state";
import {GETLab, GETOneReport, PUTReport} from "../../API/http";

export default {
  created() {
    console.log(this.$route.params);
    GETOneReport({
      "studentId": this.$route.params.studentId,
      "courseId": this.$route.params.courseId,
      "sectionId": this.$route.params.sectionId,
      "labId": this.$route.params.labId
    }).then((data) => {
      console.log(data);
      this.reportform = data;
      this.ruleform.progress = this.reportform.progress;
      this.ruleform.principle = this.reportform.principle;
      this.ruleform.purpose = this.reportform.purpose;
      this.ruleform.grades = this.reportform.grades;
      console.log("report", this.reportform);
    }).catch((err) => {
      console.log(err);
      this.$message("报告信息获取错误");
    });
  },
  data() {
    return {
      reportform:null,
      labId: this.$route.params.ID,
      lab: {
        title: "",
      },
      rules: {
        purpose: [
          {required: true, message: "请输入实验目的", trigger: "blur"},
          {
            max: 400,
            message: "长度必须小于400个字符",
            trigger: "blur",
          },
        ],
        progress: [
          {required: true, message: "请输入实验步骤", trigger: "blur"},
          {
            max: 400,
            message: "长度必须小于400个字符",
            trigger: "blur",
          },
        ],
        principle: [
          {required: true, message: "请输入实验原理", trigger: "blur"},
          {
            max: 400,
            message: "长度必须小于400个字符",
            trigger: "blur",
          },
        ],
      },
      options: [],
      ruleform: {
        purpose: "",
        principle: "",
        progress: "",
        grades:null
      },
      OrgID: store.state.ID,
      groundId: null,
    };
  }
  ,
  methods: {
    back() {
      this.$router.push("/student/experiment");
    }
    ,

    handleChange(value) {
      console.log(value);
    }
    ,

    setToDB() {
      this.reportform.progress = this.ruleform.progress;
      this.reportform.principle = this.ruleform.principle;
      this.reportform.purpose = this.ruleform.purpose;
      this.reportform.grades = this.ruleform.grades;
      PUTReport(this.reportform)
          .then(() => {
            this.$alert("提交成功");
          })
          .catch((err) => {
            console.log(err);
            this.$message("提交失败");
          });
    }
    ,
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.setToDB();
        } else {
          console.log("ID", this.$route.query.activityID);
          console.log("name", this.$route.query.activityName);
          console.log("ground", this.$route.query.groundId);
          console.log("error submit!!");
          return false;
        }
      });
    }
    ,
  }
  ,
}
;
</script>

<style scoped>
.mainrow {
  height: 100%;
}

.calendar-card {
  height: 510px;
  overflow: auto;
  border: 0;
  border-radius: 0px;
  box-shadow: 0 0px 0px 0 rgba(0, 0, 0, 0.1);
}

.col1,
.col2 {
  height: 100%;
  padding: 0px;
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

.fade-transform-enter-active {
  transition: all 0.3s;
}

.fade-transform-leave-active {
  transition: all 0.3s;
}

.maincard {
  border-radius: 15px;
  height: 100%;
  overflow: auto;
}

/* #content {
  position: absolute;
  left: 350px;
  top: 90px;
} */
.titlename {
  width: 100%;
}

.el-card {
  border-radius: 15px;
}
</style>