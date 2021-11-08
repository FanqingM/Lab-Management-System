<template>
  <el-card class="maincard">
    <div slot="header" class="clearfix">
      <span><b>使用场地申请</b></span>
    </div>
    <el-row class="mainrow" type="flex">
      <el-col class="col1" :span="12">
        <el-form
          ref="reportform"
          :rules="rules"
          :model="ruleform"
          label-width="100px"
        >
          <el-form-item label="实验原理" prop="name">
            <el-input
              class="titlename"
              type="text"
              placeholder="请输入内容"
              v-model="ruleform.name"
              maxlength="10"
              clearable
            >
            </el-input>
          </el-form-item>
          <el-form-item label="实验步骤" prop="site">
            <el-cascader
              placeholder="搜索"
              :options="options"
              :show-all-levels="false"
              v-model="ruleform.site"
              @change="getSite"
              clearable
              filterable
            ></el-cascader>
          </el-form-item>
          <el-form-item label="实验现象" prop="date">
            <el-date-picker
              clearable
              v-model="ruleform.date"
              align="right"
              type="date"
              placeholder="选择日期"
              :picker-options="pickerOptions"
              value-format="yyyy-MM-dd"
              disabled
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="思考与讨论" prop="description">
            <el-input
              clearable
              class="input"
              type="textarea"
              :rows="2"
              placeholder="请输入内容"
              v-model="ruleform.description"
              maxlength="200"
              show-word-limit
            >
            </el-input>
          </el-form-item>
          <el-form-item label="总结" prop="special">
            <el-input
              clearable
              class="input"
              type="textarea"
              :rows="2"
              placeholder="请输入内容"
              v-model="ruleform.special"
              maxlength="100"
              show-word-limit
            >
            </el-input>
          </el-form-item>
          <el-form-item align="center">
            <el-button size="medium" type="primary" @click="submitForm('reportform')"
              >提交</el-button
            >
            <el-button size="medium" @click="back"
              >取消</el-button
            >
          </el-form-item>
        </el-form>
      </el-col>

      <el-col class="col2" :span="12">
        <!-- <el-card> -->
        <transition name="fade-transform" mode="in-out">
          <!-- <div v-if="this.groundId != null" style="padding-left:5%;height:500px"> -->
          <div v-if="this.groundId != null">
            <Mycalendar
              class="calendar-card"
              :groundId="groundId"
              @handleSelect="myfun($event)"
            />
          </div>
          <!-- </div> -->
        </transition>
        <!-- </el-card> -->
      </el-col>
    </el-row>
  </el-card>
</template>

<script scoped>
import store from "../../store/state";
import { POSTActivities,
         PUTActivitiesID } from "../../API/http";
export default {
  components: {
    Mycalendar,
  },
  created() {
    this.getAllSite();
  },
  data() {
    return {
        lab: {
            title: ""
        },
      rules: {
        name: [
          { required: true, message: "请输入活动名称", trigger: "blur" },
          {
            min: 1,
            max: 10,
            message: "长度在 1 到 10 个字符",
            trigger: "blur",
          },
        ],
        date: [{ required: true, message: "请选择活动日期", trigger: "blur" }],
        time: [
          { required: true, message: "请选择活动时间", trigger: "blur" },
          {
            required: true,
            trigger: "blur",
            validator: (rule, value, callback) => {
              if (
                (this.ruleform.time[1][0] - this.ruleform.time[0][0]) * 600 +
                  (this.ruleform.time[1][1] - this.ruleform.time[0][1]) * 60 +
                  (this.ruleform.time[1][3] - this.ruleform.time[0][3]) * 10 +
                  (this.ruleform.time[1][4] - this.ruleform.time[0][4]) * 1 <
                30
              ) {
                callback(new Error("时长不超过30分钟"));
              } else if (
                this.ruleform.time[1][0] == "2" &&
                this.ruleform.time[1][1] >= "2"
              ) {
                callback(new Error("场地晚间22点之后关闭申请"));
              } else if (
                this.ruleform.time[0][0] == "0" &&
                this.ruleform.time[0][1] < "8"
              ) {
                callback(new Error("场地8点之前关闭申请"));
              } else {
                callback();
              }
            },
          },
        ],
        special: [
          { required: false, message: "请输入特殊需求", trigger: "blur" },
          {
            max: 100,
            message: "长度在小于100个字符",
            trigger: "blur",
          },
        ],
        description: [
          { required: true, message: "请输入活动描述", trigger: "blur" },
          {
            max: 100,
            message: "长度在小于100个字符",
            trigger: "blur",
          },
        ],
        site: [
          { required: true, message: "请选择一个活动地点", trigger: "blur" },
        ],
        people: [
          { required: true, message: "请选择活动人数", trigger: "blur" },
        ],
      },
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        },
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            },
          },
          {
            text: "昨天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit("pick", date);
            },
          },
          {
            text: "一周前",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            },
          },
        ],
      },
      options: [],
      ruleform: {
        id: store.state.ID,
        site: [],
        date: "",
        time: [],
        name:
          typeof this.$route.query.activityName == undefined
            ? ""
            : this.$route.query.activityName,
        description: "",
        special: "",
        duration: 0,
        people: 1,
        startTime: "",
      },
      OrgID: store.state.ID,
      groundId: null,
    };
  },
  methods: {
    back(){
      this.$router.push("/student/experiment" );
    },

    handleChange(value) {
      console.log(value);
    },

    setToDB(){
      if (this.$route.query.activityID) {
        PUTActivitiesID(this.$route.query.activityID,
        {id:this.$route.query.activityID,
        name:this.ruleform.name,
        accountNumber:this.OrgID,
        activityDate:this.ruleform.startTime,
        startTime:this.ruleform.startTime,
        participantNum:this.ruleform.people,
        description:this.ruleform.description,
        additionalRequest:this.ruleform.special,
        duration:this.ruleform.duration,
        groundId:this.ruleform.site[this.ruleform.site.length-1],}
        )
        .then(()=>{
          this.$alert("您的活动ID为" + this.$route.query.activityID, "活动ID分配", {
            confirmButtonText: "确定",
            callback: (action) => {
              if (action === "confirm") {
                this.$message({
                  type: "success",
                  message: "申请成功",
                });
              }
            },
          });
        this.ruleform.id=this.$route.query.activityID;
      })
      .catch((err) => {
          console.log(err);
          this.$message("活动申请信息传输错误");
        })
      }
      else{
              POSTActivities({
        name: this.ruleform.name,
        accountNumber: this.OrgID,
        activityDate: this.ruleform.startTime,
        startTime: this.ruleform.startTime,
        participantNum: this.ruleform.people,
        description: this.ruleform.description,
        additionalRequest: this.ruleform.special,
        duration: this.ruleform.duration,
        groundId: this.ruleform.site[this.ruleform.site.length - 1],
      })
        .then((data) => {
          this.$alert("您的活动ID为" + data.id, "活动ID分配", {
            confirmButtonText: "确定",
            callback: (action) => {
              if (action === "confirm") {
                this.$message({
                  type: "success",
                  message: "申请成功",
                });
              }
            },
          });
          this.ruleform.id = data.id;
        })
        .catch((err) => {
          console.log(err);
          this.$message("活动申请信息传输错误");
        })

      }
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.setToDB();
        } else {
          console.log("ID", this.$route.query.activityID);
          console.log("name", this.$route.query.activityName);
          console.log("ground", this.$route.query.groundId);
          console.log("error submit!!");
          return false;
        }
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
  box-shadow: 0 0px 0px 0 rgba(0, 0, 0, 0.1)
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