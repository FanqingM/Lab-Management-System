<template>
  <div style="height: 100%">
    <el-card style="height: 100%">
      <div slot="header" class="clearfix">
        <span><b>用户信息</b></span>
      </div>
      <el-row>
        <el-col :span="12">
          <div>
            <h3>用户头像</h3>
          </div>
          <div class="image">
            <img
              src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
              class="pic"
            />
          </div>
          <!-- <div class="modify">
            <el-button type="primary">更改照片</el-button>
          </div> -->
        </el-col>
        <el-col :span="8">
          <div>
            <h3>用户信息</h3>
          </div>
          <div id="content">
            <transition name="fade-transform" mode="out-in">
              <div v-if="isForm == true">
                <el-form
                  ref="ruleForm"
                  :rules="rules"
                  :model="ruleForm"
                  label-width="100px"
                  :hide-required-asterisk="true"
                >
                  <el-form-item label="学号：" prop="id">
                    <el-input
                      v-model="ruleForm.id"
                      :readonly="true"
                    ></el-input>
                  </el-form-item>
                  <el-form-item label="姓名：" prop="name">
                    <el-input
                      v-model="ruleForm.name"
                      :readonly="true"
                    ></el-input>
                  </el-form-item>
                  <el-form-item label="邮箱：" prop="email">
                    <el-input
                      v-model="ruleForm.email"
                      :readonly="true"
                    ></el-input>
                  </el-form-item>
                  <el-form-item label="学院：" prop="schoolName">
                    <!-- <el-input
                      v-model="ruleForm.schoolName"
                      :readonly="false"
                    ></el-input> -->
                    <el-select v-model="ruleForm.schoolName" placeholder="请选择学院">
                      <el-option label="软件学院" value="软件学院"></el-option>
                      <el-option label="土木工程学院" value="土木工程学院"></el-option>
                      <el-option label="经济与管理学院" value="经济与管理学院"></el-option>
                      <el-option label="数学科学学院" value="数学科学学院"></el-option>
                      <el-option label="汽车学院" value="汽车学院"></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="班级：" prop="classnum">
                    <el-input
                      v-model="ruleForm.classnum"
                      :readonly="false"
                    ></el-input>
                  </el-form-item>
                </el-form>
              </div>
            </transition>

            <transition name="fade-transform" mode="out-in">
              <div v-if="isTable == true">
                <el-table
                  :show-header="false"
                  :data="tableData"
                  :cell-style="columnStyle"
                  border
                  style="width: 80%; margin-top: 20px"
                >
                  <el-table-column width="180" prop="title" label="标题">
                  </el-table-column>
                  <el-table-column prop="content" label="内容">
                  </el-table-column>
                </el-table>
              </div>
            </transition>
            <div v-if="isTable == true" class="modify">
              <el-button type="primary" @click="edit">编辑个人信息</el-button>
            </div>
            <div v-if="isForm == true" class="modify">
              <el-button type="primary" @click="submitForm('ruleForm')"
                >提交</el-button
              >
              <el-button @click="returnback">取消</el-button>
            </div>
          </div>
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
import { GETStudentsID, PUTStudent } from "../../API/http";

export default {
  mounted() {
    console.log("获取学生数据", this.$route.query.id);
    GETStudentsID(this.$route.query.id)
      .then((data) => {
        this.ruleForm = data;
        this.updateData();
      })
      .catch((err) => {
        console.log(err);
        this.$message("信息获取错误");
      });
  },

  data() {
    return {
      ruleForm: {
        id: "string",
        email: "string",
        name: "string",
        classnum: "string",
        schoolName: "string"
      },

      rules: {
        classnum: [{ required: true, message: "请输入班级", trigger: "blur" }],
      },
      tableData: [
        {
          title: "学号",
          content: "",
        },
        {
          title: "姓名",
          content: "",
        },
        {
          title: "邮箱",
          content: "",
        },
        {
          title: "学院",
          content: "",
        },
        {
          title: "班级",
          content: "",
        },
      ],
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
      isForm: false,
      isTable: true,
    };
  },

  methods: {
    columnStyle({ row, column, rowIndex, columnIndex }) {
      row;
      column;
      //console.log(row, column, rowIndex, columnIndex, "row");
      if (columnIndex == 0 && (rowIndex < 3)) {
        return "background:#FBFBEF; font-weight: 700;";
      } else if (columnIndex == 0) {
        return "background:#EFFBEF; font-weight: 700;";
      }
    },

    returnData() {
      this.ruleForm.id = this.tableData[0].content;
      this.ruleForm.name = this.tableData[1].content;
      this.ruleForm.email = this.tableData[2].content;
      this.ruleForm.schoolName = this.tableData[3].content;
      this.ruleForm.classnum = this.tableData[4].content;
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
    edit() {
      this.isTable = false;
      setTimeout(() => {
        this.isForm = true;
      }, 400);
    },
    returnback() {
      this.isForm = false;
      setTimeout(() => {
        this.isTable = true;
        this.returnData();
      }, 400);
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