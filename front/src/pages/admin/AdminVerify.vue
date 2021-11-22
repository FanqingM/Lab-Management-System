<template>
  <div class="maindiv">
    <el-card class="mycard">
      <div slot="header" class="clearfix">
        <span><b>审核注册</b></span>
      </div>
      <el-row>
        <el-col :span="10" :offset="2">
          <div class="info">
            <p><b>姓名：</b>{{accountDetail.name}}</p>
            <p><b>学工号：</b>{{accountDetail.id}}</p>
            <p><b>邮箱：</b>{{accountDetail.email}}</p>
            <p><b>注册身份：</b>{{accountDetail.isTeacher == true ? "教师" : "学生"}}</p>
            <el-image
              :src="accountDetail.idcard"></el-image>
          </div></el-col
      ></el-row>

      <el-row>
        <el-col :span="10" :offset="2"
          ><div class="status">
            <h3>审核意见</h3>
            <el-col :span="2" :offset="2">
              <el-radio v-model="radio" label="1">通过</el-radio>
            </el-col>
            <el-col :span="2" :offset="2">
              <el-radio v-model="radio" label="0">不通过</el-radio>
            </el-col>
          </div></el-col
        >
      </el-row>

      <el-row>
        <el-col :span="5" :offset="15">
          <div class="submit">
            <router-link
              to="/admin/verification"
              tag="el-button"
              type="primary"
              >返回</router-link
            >
            <el-button type="primary" @click="submit">提交</el-button>
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<style scoped>
html,
body,
.mycard {
  height: 100%;
  border-radius: 12px;
  overflow: auto;
}
.info {
  text-align: left;
}
.el-card {
  border-radius: 15px;
}
/* .submit {
  margin: 1cm 1cm 1cm 17cm;
   float: right;
} */

p {
  color: rgb(0, 0, 0);
  position: relative;
}
</style>

<script>
import { GETUnactivatedAccountID, ActivateAccount, DELETEUnactivatedAccount } from "../../API/http";

export default {
  mounted() {
    // console.log(this.accountId);
    GETUnactivatedAccountID(this.accountId)
      .then((data) => {
        this.accountDetail = data;
        console.log(data);
      })
      .catch((err) => {
        console.log(err);
        this.$message("数据请求错误");
      });
  },

  data() {
    return {
      accountId: this.$route.params.ID,
      accountDetail: {
        email: "",
        name: "",
        password: "",
        idcard: "",
        id: "",
        isTeacher: true,
        isSeniorTercher: true
      },
      radio: null,
      textarea: "",
      ruleForm: {
        name: "",
        content: "",
      },
    };
  },

  methods: {
    submit() {
      console.log(this.radio);
      if (this.radio == null) {
        this.$message("请选择审核意见！");
      } else if (this.radio == 1) {
        ActivateAccount(this.accountId)
          .then((data) => {
            this.accountDetail = data;
            this.$message("已通过");
            console.log(data);
            this.$router.push({path: "/admin/verification"});
          })
          .catch((err) => {
            console.log(err);
            this.$message("提交失败");
          });
      } else {
        DELETEUnactivatedAccount(this.accountId)
          .then((data) => {
            this.accountDetail = data;
            this.$message("已拒绝");
            console.log(data);
            this.$router.push({path: "/admin/verification"});
          })
          .catch((err) => {
            console.log(err);
            this.$message("提交失败");
          });
      }
    },
  },
};
</script>