<template>
  <el-card class="maincard">
    <div slot="header" class="clearfix">
      <span><b>实验报告</b></span>
    </div>
    <div style="margin: 40px">
      <p>1. 实验目的</p>
      <quill-editor
        class="editor"
        ref="purposeEditor"
        v-model="purpose"
        :options="editorOption"
      >
      </quill-editor>
      <p>2. 实验原理</p>
      <quill-editor
        class="editor"
        ref="principleEditor"
        v-model="principle"
        :options="editorOption"
      >
      </quill-editor>
      <p>3. 实验总结</p>
      <quill-editor
        class="editor"
        ref="conslusionEditor"
        v-model="conclusion"
        :options="editorOption"
      >
      </quill-editor>
      <div align="center" style="padding: 20px">
        <el-button size="medium" type="primary" @click="submitForm">提交</el-button>
        <el-button size="medium" @click="back">取消</el-button>
      </div>
    </div>
  </el-card>
</template>

<script scoped>
import store from "../../store/state";
import { GETLab, PUTReport } from "../../API/http";
export default {
  created() {
    GETLab({
      studentId: store.state.id,
    })
      .then((data) => {
        console.log("data", data);
        for (var i = 0; i < data.length; ++i) {
          if (data[i].grades != null) {
            this.finished.push({
              labId: data[i].labId,
              labName: data[i].labName,
              grades: data[i].grades,
              date: "2021/11/1",
              due: "2021/11/8",
              status: data[i].grades === 0 ? "未评分" : "已评分",
            });
          } else {
            this.unfinished.push({
              labId: data[i].labId,
              labName: data[i].labName,
              grades: data[i].grades,
              date: "2021/11/1",
              due: "2021/11/10",
            });
          }
        }
      })
      .catch((err) => {
        console.log(err);
        this.$message("学生信息获取错误");
      });
  },
  data() {
    return {
      editorOption: {
        modules: {
          toolbar: [
            ["bold", "italic", "underline", "strike"], // 加粗 斜体 下划线 删除线
            ["blockquote", "code-block"], // 引用  代码块
            [{ header: [1, 2, 3, 4, 5, 6, false] }], // 标题
            [{ list: "ordered" }, { list: "bullet" }], // 有序、无序列表
            [{ script: "sub" }, { script: "super" }], // 上标/下标
            [{ indent: "-1" }, { indent: "+1" }], // 缩进
            // [{'direction': 'rtl'}],                         // 文本方向
            [{ size: ["small", false, "large", "huge"] }], // 字体大小
            [{ color: [] }, { background: [] }], // 字体颜色、字体背景颜色
            [{ font: [] }], // 字体种类
            [{ align: [] }], // 对齐方式
            ["clean"], // 清除文本格式
            ["image"], // 链接、图片、视频
          ], //工具菜单栏配置
        },
        placeholder: "请在这里填写内容", //提示
        readyOnly: false, //是否只读
        theme: "snow", //主题 snow/bubble
        syntax: true, //语法检测
      },
      labId: this.$route.params.ID,
      lab: {
        title: "",
      },
      options: [],
      purpose: null,
      principle: null,
      conclusion: null,
      OrgID: store.state.ID,
      groundId: null,
    };
  },
  methods: {
    back() {
      this.$router.push("/student/experiment");
    },

    handleChange(value) {
      console.log(value);
    },
    submitForm() {
      PUTReport({
        studentId: store.state.id,
        courseId: "420244",
        sectionId: "01",
        labId: String(this.labId),
        url: "www.google.com",
        grades: 0,
        purpose: this.purpose,
        progress: this.progress,
        principle: this.conclusion,
      })
        .then(() => {
          this.$alert("提交成功");
        })
        .catch((err) => {
          console.log(err);
          this.$message("提交失败");
        });
    },
  },
};
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

<style>
.ql-snow .ql-picker.ql-size .ql-picker-label::before,
.ql-snow .ql-picker.ql-size .ql-picker-item::before {
  content: '常规';
}
.ql-snow .ql-picker.ql-size .ql-picker-label[data-value=small]::before,
.ql-snow .ql-picker.ql-size .ql-picker-item[data-value=small]::before {
  content: '小';
}
.ql-snow .ql-picker.ql-size .ql-picker-label[data-value=large]::before,
.ql-snow .ql-picker.ql-size .ql-picker-item[data-value=large]::before {
  content: '中';
}
.ql-snow .ql-picker.ql-size .ql-picker-label[data-value=huge]::before,
.ql-snow .ql-picker.ql-size .ql-picker-item[data-value=huge]::before {
  content: '大';
}

.ql-snow .ql-picker.ql-header .ql-picker-label::before,
.ql-snow .ql-picker.ql-header .ql-picker-item::before {
  content: '文本';
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="1"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="1"]::before {
  content: "标题1";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="2"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="2"]::before {
  content: "标题2";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="3"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="3"]::before {
  content: "标题3";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="4"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="4"]::before {
  content: "标题4";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="5"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="5"]::before {
  content: "标题5";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="6"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="6"]::before {
  content: "标题6";
}
</style>