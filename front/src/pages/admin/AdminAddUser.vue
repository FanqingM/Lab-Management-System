<template>
  <div style="height: 100%">
    <el-card style="height: 100%">
      <div slot="header" class="clearfix">
        <span><b>添加用户</b></span>
      </div>
      <el-form
        ref="ruleForm"
        :rules="rules"
        :model="ruleForm"
        label-width="100px"
        style="width: 500px"
        :hide-required-asterisk="true"
      >
        <el-form-item label="类型：">
          <el-select v-model="ruleForm.type" placeholder="选择要创建的账户类型">
            <el-option label="学生" value="student"></el-option>
            <el-option label="教师" value="instructor"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="账号：" prop="id">
          <el-input v-model="ruleForm.id"></el-input>
        </el-form-item>
        <el-form-item label="姓名：" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="学院：" prop="schoolName">
          <el-select v-model="ruleForm.schoolName" placeholder="请选择学院">
            <el-option label="软件学院" value="软件学院"></el-option>
            <el-option label="土木工程学院" value="土木工程学院"></el-option>
            <el-option
              label="经济与管理学院"
              value="经济与管理学院"
            ></el-option>
            <el-option label="数学科学学院" value="数学科学学院"></el-option>
            <el-option label="汽车学院" value="汽车学院"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="邮箱：" prop="email">
          <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>
        <el-form-item
          v-if="ruleForm.type == 'student'"
          label="班级："
          prop="classnum"
        >
          <el-input v-model="ruleForm.classnum"></el-input>
        </el-form-item>
      </el-form>
      <el-row style="width: 500px">
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

      rules: {
        id: [
          { required: true, message: "请输入账号", trigger: "blur" },],
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }],
        classnum: [{ required: true, message: "请输入班级", trigger: "blur" }],
        email: [
          { required: true, message: "请输入邮箱", trigger: "change" },
          { type: "email", message: "请输入正确的邮箱", trigger: "change" },
        ],
        schoolName: [{ required: true, message: "请选择学院", trigger: "change" },]
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

    submitForm: function (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          PUTStudent(this.ruleForm)
        .then(() => {
          this.$message("添加成功");
          this.$router.go(-1);
        })
        .catch((err) => {
          console.log(err);
          this.$message("添加失败");
        });
        }
      });
    },
  },
};
</script>