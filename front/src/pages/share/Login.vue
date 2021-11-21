<template>
  <div class="IdentitySelectionPage">
    <img
      src="../../assets/IdentitySelection.jpg"
      class="IdentitySelectionBackImage"
    />
    <!--"IdentitySelectionContainer"-->
    <el-card
      id="logincard"
      class="IdentitySelectionContainer"
      style="border-radius: 12px"
    >
      <el-row>
        <h1>实验教学管理系统</h1>
      </el-row>

      <el-row style="margin: 20px 0 10px 0px">
        <el-col>
          <el-form
            ref="LoginForm"
            :model="form"
            :rules="rules"
            label-width="40px"
            :hide-required-asterisk="true"
            size="medium"
          >
            <el-form-item label="账号" prop="accountNumber">
              <el-input
                v-model.number="form.accountNumber"
                placeholder="请输入账号"
                clearable
                autofocus
                autocomplete
              ></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input
                v-model="form.password"
                showPassword
                placeholder="请输入密码"
                clearable
                autofocus
                autocomplete
              ></el-input>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <el-row style="margin: 20px 0 10px 0px">
        <el-col>
          <el-radio-group v-model="identity" style="margin: 0px 0 15px 0px">
            <el-row :gutter="35" style="margin: 0px 0 5px 0px">
              <el-col :span="8">
                <el-radio :label="0">学生</el-radio>
              </el-col>
              <el-col :span="8">
                <el-radio :label="2">教师</el-radio>
              </el-col>
              <el-col :span="8">
                <el-radio :label="1">管理员</el-radio>
              </el-col>
            </el-row>
          </el-radio-group>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center" style="margin: 0px 0 10px 0px">
        <el-button
          @click="submitForm('LoginForm')"
          size="medium"
          round
          style="width: 70%"
          type="primary"
          >登录</el-button
        >
      </el-row>

      <el-row>
        <el-col :span="8" :offset="6">
          <router-link :to="'/Register/' + identity">
            <el-link :underline="false">注册</el-link>
          </router-link>
        </el-col>
        <el-col :span="8">
          <router-link :to="'/ForgetPassword/' + identity">
            <el-link :underline="false">忘记密码</el-link>
          </router-link>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import { Login } from "../../API/http";
import store from "../../store/state";
import md5 from "js-md5";
export default {
  data() {
    return {
      form: {
        accountNumber: "",
        password: "",
      },
      rules: {
        accountNumber: [
          {
            required: true,
            message: "请输入账号",
            trigger: "blur",
          },
          {
            type: "number",
            message: "账号必须为7位数字",
            trigger: "blur",
            min: 1000000,
            max: 9999999,
          },
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur",
          },
          {
            // pattern: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,10}$/,
            // message:
            //   "必须包含大小写字母和数字的组合，可以使用特殊字符，长度在8-10之间",
            trigger: "blur",
          },
        ],
      },
      identity: 0,
    };
  },
  methods: {
    submitForm: function (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
            Login({   
              id: this.form.accountNumber,
              password: this.form.password,
              authority: this.identity,
              //secretPassword: md5(this.form.password, "hhh"),
            })
            .then((data) => {
              console.log(data);
              if (data.status === 200) {
                localStorage.setItem("uutype", this.identity);
                localStorage.setItem("uuid", this.form.accountNumber);
                localStorage.setItem("uutoken", data.data.token);
                store.state.id = this.form.accountNumber;
                store.state.membertype = this.identity;
                if (this.identity === 0) {
                  this.$router.push("/student/home");
                } else if (this.identity === 2) {
                  this.$router.push("/teacher/home");
                } else {
                  this.$router.push("/admin/home");
                }
              }             
              this.$message(data.message);
            })
            .catch((err) => {
              console.log(err);
              this.$message.error("连接错误");
              // if (err.response.status == 404) {
              //   this.$message.error("账户不存在");
              // } else if (err.response.status == 401) {
              //   this.$message.error("该账户还未被审核，请稍后再试");
              // } else {
              //   this.$message.error("账户或密码错误");
              //   console.log(typeof err.response.status);
              // }
            });
        }
      });
    },
  },
};
</script>

<style scoped>
@import "../../css/IdentitySelection.css";
@import "../../css/Login.css";
.el-card {
  border-radius: 15px;
}

h1 {
  color: rgb(0, 81, 255);
}

h4 {
  color: white;
}

.blur {
  filter: blur(10px);
}
</style>