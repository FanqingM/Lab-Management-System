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
    <p><b>得分：</b>{{ reportForm.grades }}</p>
    <el-button size="medium" type="primary" @click="back">返回</el-button>
  </el-card>
</template>

<script scoped>
import store from "../../store/state";
import {GETOneReport} from "../../API/http";

export default {
  created() {
    var params = this.$route.query;
    params['studentId'] = store.state.id;
    GETOneReport(params).then((data) => {
      console.log("data**", data);
      this.reportForm = data;
      console.log("report", this.reportForm);
    }).catch((err) => {
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
      reportForm:{  
        studentId: "string",
        courseId: "string",
        sectionId: "string",
        labId: "string",
        url: "string",
        grades: 0,
        purpose: "",
        progress: "",
        principle: ""
      },
      labId: this.$route.params.ID,
      lab: {
        title: "",
      },
      options: [],
      groundId: null,
    };
  }
  ,
  methods: {
    back() {
      this.$router.push("/student/experiment");
    }
    ,

onEditorFocus(event) { 
        event.enable(false);
    },
    handleChange(value) {
      console.log(value);
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