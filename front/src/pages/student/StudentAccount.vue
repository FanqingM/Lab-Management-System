<template>
  <div class="page">
    <el-card>
      <div>
        <h2>用户信息</h2>
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
          <div class="modify">
            <el-button type="primary">更改照片</el-button>
          </div>
        </el-col>
        <el-col :span="8">
          <div>
            <h3>用户信息</h3>
              <el-table
                :show-header="false"
                :data="tableData"
                :cell-style="columnStyle"
                border
                style="width: 80%; margin-top: 20px"
              >
                <el-table-column prop="title" label="标题">
                </el-table-column>
                <el-table-column prop="content" label="内容"> </el-table-column>
              </el-table>
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<style scoped>
.info {
  text-align: left;
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
  height: 57%;
  width: 57%;
  margin-top: 1cm;
  margin-left: 2.2cm;
}
.el-card {
  border-radius: 15px;
}
</style>

<script>
import { GETStudentsID } from "../../API/http";
import store from "../../store/state";

export default {
  created() {
    GETStudentsID(store.state.id)
      .then((data) => {
        this.accountData = data;
        console.log(this.accountData);
        this.updateData();
      })
      .catch((err) => {
        console.log(err);
        this.$message("学生信息获取错误");
      });
  },
  data() {
    return {
      submitState: true,
      radio: "1",
      textarea: "",
      accountData: {
        id: "",
        email: "",
        name: "",
        classnum: "",
        schoolName: ""
      },
      tableData: [
        {
          title: "账号",
          content: "",
        },
        {
          title: "邮箱",
          content: "",
        },
        {
          title: "姓名",
          content: "",
        },
        {
          title: "班级",
          content: "",
        },
        {
          title: "学院",
          content: "",
        },
      ],
    };
  },
  methods: {
    columnStyle({ row, column, rowIndex, columnIndex }) {
      row;
      column;
      //console.log(row, column, rowIndex, columnIndex, "row");
      if (columnIndex == 0 && rowIndex < 5) {
        //第三第四列的背景色就改变了2和3都是列数的下标
        return "background:#FBFBEF; font-weight: 700;";
      } else if (columnIndex == 0) {
        return "background:#EFFBEF; font-weight: 700;";
      }
    },
    updateData() {
      this.tableData[0].content = this.accountData.id;
      this.tableData[1].content = this.accountData.email;
      this.tableData[2].content = this.accountData.name;
      this.tableData[3].content = this.accountData.classnum;
      this.tableData[4].content = this.accountData.schoolName;
    },
    // setToDB() {
    //   PUTStudentsID(this.ruleForm.account, {
    //     accountNumber: this.ruleForm.account,
    //     name: this.ruleForm.name,
    //     gender: this.numData.gender,
    //     grade: this.numData.grade,
    //     academy: this.numData.college,
    //     eMailAddress: this.ruleForm.email,
    //     major: this.ruleForm.specialty,
    //     nation: this.numData.country,
    //   }).catch((err) => {
    //     console.log(err);
    //     this.submitState = false;
    //     this.$message("用户信息获取失败");
    //   });
    // },
    edit() {
      setTimeout(() => {
        this.isForm = true;
      }, 400);
    },
    submitForm: function (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.StrToNum();
          // this.setToDB();
          if (this.submitState == true) {
            this.isForm = false;
            setTimeout(() => {
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
          } else {
            this.$message({
              type: "error",
              message: "编辑失败",
            });
          }
          this.submitState = false;
        }
      });
    },
  },
};
</script>