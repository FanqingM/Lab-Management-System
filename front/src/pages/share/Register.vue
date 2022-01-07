<template>
  <div class="IdentitySelectionPage">
    <img
      src="../../assets/IdentitySelection.jpg"
      class="IdentitySelectionBackImage"
    />
    <!--"IdentitySelectionContainer"-->
    <el-card class="IdentitySelectionContainer" style="border-radius: 12px">
      <div slot="header" class="clearfix">
        <el-row>
          <div class="mainh">实验教学管理系统</div>
        </el-row>
      </div>
      <el-row>
        <el-col>
          <el-form
            ref="RegisterForm"
            :model="form"
            :rules="rules"
            label-width="70px"
            :hide-required-asterisk="true"
            size="medium"
          >
            <el-form-item label="账号" prop="accountNo">
              <el-input
                v-model.number="form.accountNo"
                placeholder="请输入账号"
                clearable
              ></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="username">
              <el-input
                v-model="form.username"
                placeholder="请输入姓名"
                clearable
              ></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input
                v-model="form.password"
                showPassword
                placeholder="请输入密码"
                clearable
              ></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPassword" clearable>
              <el-input
                v-model="form.checkPassword"
                showPassword
                placeholder="请再次输入密码"
                clearable
              ></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
              <el-row type="flex">
                <el-input
                  v-model="form.email"
                  placeholder="请输入邮箱"
                  clearable
                ></el-input>
                <el-button
                  type="primary"
                  plain
                  @click="sendEmail('RegisterForm')"
                  :disabled="isOK"
                  >{{ timeCnt }}</el-button
                >
              </el-row>
            </el-form-item>
            <el-form-item label="验证码" prop="verifyEmail">
              <el-input
                v-model="form.verifyEmail"
                placeholder="请输入验证码"
                clearable
              ></el-input>
            </el-form-item>

            <el-row type="flex" justify="center" style="margin: 0px 0 10px 0px">
              <el-button
                @click="submitForm('RegisterForm')"
                size="medium"
                round
                style="width: 70%"
                type="primary"
                >{{"注册为" + ((type == 0) ? "学生" : "教师")}}</el-button
              >
            </el-row>
          </el-form>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center">
        <router-link to="/">
          <el-link :underline="false">返回</el-link>
        </router-link>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import md5 from 'js-md5';
import {
  SendEmail,
  VerifyEmail,
  POSTUnactivatedAccount
} from "../../API/http";
import store from "../../store/state";
export default {
  data() {
    return {
      type: this.$route.params.type,
      //发送邮箱避免频繁发送
      timeCnt: "验证",
      isOK: false,
      cnthandler: null,
      form: {
        accountNo: "",
        username: "",
        password: "",
        checkPassword: "",
        email: "",
        verifyEmail: "",
      },
      rules: {
        accountNo: [
          { required: true, message: "请输入账号", trigger: "blur" },
          {
            type: "number",
            message: "账号必须为7位数字",
            trigger: "change",
            min: 1000000,
            max: 9999999,
          },
        ],
        username: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          {
            // pattern: /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/,
            min: 2,
            max: 10,
            message: "长度为2~10个字符",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          {
            pattern: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,10}$/,
            message:
              "必须包含大小写字母和数字的组合，不能使用特殊字符，长度在 8-10 之间",
            trigger: "blur",
          },
        ],
        checkPassword: [
          { required: true, message: "请再次输入密码", trigger: "change" },
          {
            validator: (rule, value, callback) => {
              if (value !== this.form.password) {
                callback(new Error("两次输入密码不一致"));
              } else {
                callback();
              }
            },
            trigger: "change",
          },
        ],
        email: [
          { required: true, message: "请输入邮箱", trigger: "change" },
          { type: "email", message: "请填写正确的邮箱", trigger: "change" },
        ],
        verifyEmail: [
          { required: true, message: "请输入验证码", trigger: "change" },
        ],
      },
    };
  },
  methods: {
    cnt: function () {
      this.cnthandler = setTimeout(() => {
        if (this.timeCnt === 0) {
          clearInterval(this.cnthandler);
          this.timeCnt = "验证";
          this.isOK = false;
          return;
        }
        this.timeCnt--;
        this.cnt();
      }, 1000);
    },
    sendEmail: function (formName) {
      this.$refs[formName].validateField("email", (ErrorMessage) => {
        if (ErrorMessage) {
          // 邮箱无效
          this.$message(ErrorMessage);
        } else {
          this.timeCnt = 30;
          this.isOK = true;
          this.cnt();
          SendEmail({ email: this.form.email })
            .then((data) => {
              if (data == true) {
                this.$message("验证码发送成功");
              } else {
                this.$message.error("验证码发送失败");
              }
            })
            .catch((err) => {
              this.$message.error("验证码发送失败");
              console.log(err);
            });
        }
      });
    },
    submitForm: function (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          VerifyEmail({ 
            email: this.form.email,
            code: this.form.verifyEmail
           })
            .then((data) => {
              if (data == true) {
                console.log("验证码正确");
                POSTUnactivatedAccount({
                  email: this.form.email,
                  name: this.form.username,
                  password: this.form.password,
                  idcard: "https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/baike/pic/item/e1fe9925bc315c6018cf3d0481b1cb1348547786.jpg",
                  id: this.form.accountNo,
                  isTeacher: (this.type == 0) ? false : true,
                  isSeniorTercher: false
                })
                .then((data) => {
                  console.log(data);
                  this.$message("注册成功");
                  this.$router.push("/");
                })
                .catch((err) => {
                  console.log(err);
                  this.$message.error("注册失败");
                })
              } else {
                this.$message.error("验证码错误");
              }
            })
            .catch((err) => {
              this.$message.error("验证码错误");
              console.log(err);
            });
        }
      });
    },
  },
};
</script>

<style scoped>
@import "../../css/IdentitySelection.css";
.IdentitySelectionChooseButton {
  width: auto;
  height: 10%;
}
.el-card {
  border-radius: 15px;
}
.logoImage {
  height: 100%;
  width: 100%;
}
</style>