<template>
  <el-card class="maincard">
    <div slot="header" class="clearfix">
      <span><b>实验报告</b></span>
    </div>
    <p>1. 实验目的</p>
    <quill-editor
      class="editor"
      ref="purposeEditor"
      v-model="reportForm.purpose"
      @focus="onEditorFocus($event)"
      :options="editorOption"
    >
    </quill-editor>
    <p>2. 实验原理</p>
    <quill-editor
      class="editor"
      ref="principleEditor"
      v-model="reportForm.principle"
      @focus="onEditorFocus($event)"
      :options="editorOption"
    >
    </quill-editor>
    <p>3. 实验总结</p>
    <quill-editor
      class="editor"
      ref="conslusionEditor"
      v-model="reportForm.progress"
      @focus="onEditorFocus($event)"
      :options="editorOption"
    >
    </quill-editor>
    <el-form style="margin-top: 20px">
      <el-form-item label="分数" prop="grades">
        <el-input v-model.number="reportForm.grades" style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item align="center">
        <el-button
          size="medium"
          type="primary"
          @click="submitForm()"
          >提交
        </el-button>
        <el-button size="medium" @click="back">取消</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script scoped>
import store from "../../store/state";
import { GETLab, GETOneReport, PUTReport } from "../../API/http";

export default {
  created() {
    console.log(this.$route.params);
    GETOneReport({
      studentId: this.$route.params.studentId,
      courseId: this.$route.params.courseId,
      sectionId: this.$route.params.sectionId,
      labId: this.$route.params.labId,
    })
      .then((data) => {
        console.log(data);
        this.reportForm = data;
        console.log("report", this.reportForm);
      })
      .catch((err) => {
        console.log(err);
        this.$message("报告信息获取错误");
      });
  },
  data() {
    return {
      editorOption: {
        modules: {
          toolbar: [],
        },
        placeholder: "",
        readonly: true,
        theme: "snow",
        syntax: true,
      },
      reportForm: {
        purpose: "",
        principle: "",
        progress: "",
        grades: 0,
      },
      labId: this.$route.params.ID,
      lab: {
        title: "",
      },
      options: [],
      OrgID: store.state.ID,
      groundId: null,
    };
  },
  methods: {
    back() {
      this.$router.go(-1);
    },
    handleChange(value) {
      console.log(value);
    },
    setToDB() {
      PUTReport(this.reportForm)
        .then(() => {
          this.$alert("提交成功");
        })
        .catch((err) => {
          console.log(err);
          this.$message("提交失败");
        });
    },
    onEditorFocus(event) {
      event.enable(false);
    },
    submitForm() {
      if (this.reportForm.grades >= 0 && this.reportForm.grades <= 100) {
        this.setToDB();
        this.$router.go(-1);
      } else {
        console.log("ID", this.$route.query.activityID);
        console.log("name", this.$route.query.activityName);
        console.log("ground", this.$route.query.groundId);
        console.log("error submit!!");
        this.$message.error("分数必须在0-100之间!");
        return false;
      }
    },
  },
};
</script>

<style scoped>
.mainrow {
  height: 100%;
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