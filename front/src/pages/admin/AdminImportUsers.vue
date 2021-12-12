<template>
  <div style="height: 100%">
    <el-card style="height: 100%">
      <div slot="header" class="clearfix">
        <span><b>导入用户列表</b></span>
      </div>
      <el-upload
       style="width: 600px; margin: 50px"
        drag
        action="https://jsonplaceholder.typicode.com/posts/"
        multiple>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">只能上传xlsx文件，且不超过500kb</div>
      </el-upload>
      <el-row style="width: 600px; margin: 50px">
        <el-col :span="3" :offset="8">
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        </el-col>
        <el-col :span="3" :offset="2">
          <router-link to="/admin/user-management">
            <el-button>取消</el-button>
          </router-link>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<style scoped>
.el-card {
  border-radius: 15px;
}
.info {
  text-align: left;
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
p {
  color: rgb(0, 0, 0);
  position: relative;
}
.fade-transform-enter-active {
  transition: all 0.3s;
}
.fade-transform-leave-active {
  transition: all 0.3s;
}
.image {
  margin-top: -0.5cm;
}

.modify {
  margin-top: 1cm;
  margin-left: 5cm;
}

.pic {
  height: 45%;
  width: 45%;
  margin-top: 1cm;
  margin-left: 2.2cm;
}
</style>

<script>
import { PUTInstructor, PUTStudent } from "../../API/http";

export default {
  data() {
    return {
      ruleForm: {
        type: "student",
        id: "",
        email: "",
        name: "",
        classnum: "",
        schoolName: "",
      },
      colleges: [
        {
          value: "软件学院",
          label: "软件学院",
        },
        {
          value: "土木工程学院",
          label: "土木工程学院",
        },
        {
          value: "经济与管理学院",
          label: "经济与管理学院",
        },
        {
          value: "数学科学学院",
          label: "数学科学学院",
        },
        {
          value: "汽车学院",
          label: "汽车学院",
        },
      ],
    };
  },

  methods: {
    columnStyle({ row, column, rowIndex, columnIndex }) {
      row;
      column;
      //console.log(row, column, rowIndex, columnIndex, "row");
      if (columnIndex == 0 && rowIndex < 3) {
        return "background:#FBFBEF; font-weight: 700;";
      } else if (columnIndex == 0) {
        return "background:#EFFBEF; font-weight: 700;";
      }
    },

    updateData() {
      this.tableData[0].content = this.ruleForm.id;
      this.tableData[1].content = this.ruleForm.name;
      this.tableData[2].content = this.ruleForm.email;
      this.tableData[3].content = this.ruleForm.schoolName;
      this.tableData[4].content = this.ruleForm.classnum;
    },
    setToDB() {
      PUTStudent(this.ruleForm)
        .then(() => {
          this.$message("学生信息修改成功");
        })
        .catch((err) => {
          console.log(err);
          this.$message("学生信息修改失败");
        });
    },
    submitForm: function (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.setToDB();
          this.isForm = false;
          setTimeout(() => {
            this.isTable = true;
            this.updateData();
          }, 400);
          this.$alert("编辑成功！", {
            confirmButtonText: "确定",
            callback: (action) => {
              if (action === "confirm") {
                this.$message({
                  type: "success",
                  message: "编辑成功",
                });
              }
            },
          });
        }
      });
    },
  },
};
</script>