<template>
  <el-card>
    <div v-if="waiting == true">
      <el-row>
      <div style="display: flex; align-items: center; justify-content: center">
        <img src="https://img.zcool.cn/community/010a865cf9cf98a801213ec2a01fca.gif"/>
      </div>  
        <el-col :span="24">
          <h1 style="font-size: 30px; text-align: center">正在匹配队友</h1>
        </el-col>
      </el-row>
      <div style="display: flex; align-items: center; justify-content: center">
        <el-avatar
        :size="100" style="margin: 10px"
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        ></el-avatar>
        <el-avatar :size="100" style="margin: 10px"
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        ></el-avatar>
        <el-avatar :size="100" style="margin: 10px"> 等待加入 </el-avatar>
        <el-avatar :size="100" style="margin: 10px"> 等待加入 </el-avatar>
      </div>
    </div>
    <div v-else>
      <el-row style="height: 100%">
        <el-col :span="16">
          <el-row style="padding: 20px"
            >下列关于凸函数的定义中，正确的是</el-row
          >
          <el-row style="padding: 20px"
            ><el-button @click="sendAnswer(1)" style="margin-right: 20px"
              >A</el-button
            >对任意的x,y∈dom f, 0≤θ≤1, f(θx+(1-θ)y)≤θf(x)+(1-θ)f(y)</el-row
          >
          <el-row style="padding: 20px"
            ><el-button @click="sendAnswer(2)" style="margin-right: 20px"
              >B</el-button
            >对任意的x,y∈dom f, 0≤θ≤1, f(θx+(1-θ)y)≥θf(x)+(1-θ)f(y)</el-row
          >
          <el-row style="padding: 20px"
            ><el-button @click="sendAnswer(3)" style="margin-right: 20px"
              >C</el-button
            >对任意的x,y∈dom f, θ∈R, f(θx+(1-θ)y)≤θf(x)+(1-θ)f(y)</el-row
          >
          <el-row style="padding: 20px"
            ><el-button @click="sendAnswer(4)" style="margin-right: 20px"
              >D</el-button
            >对任意的x,y∈dom f, θ∈R, f(θx+(1-θ)y)≤θf(x)+(1-θ)f(y)</el-row
          >
        </el-col>
        <el-col :span="8">
          <h2 style="padding: 20px">当前得分</h2>
          <el-row style="padding: 20px">
            <el-avatar
              src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
            >
            </el-avatar>
            <el-progress
              :text-inside="true"
              :stroke-width="30"
              :percentage="70"
            ></el-progress>
          </el-row>
          <el-row style="padding: 20px"
            ><el-avatar
              src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
            ></el-avatar
            ><el-progress
              :text-inside="true"
              :stroke-width="20"
              :percentage="100"
              status="success"
            ></el-progress
          ></el-row>
          <el-row style="padding: 20px"
            ><el-avatar
              src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
            ></el-avatar
            ><el-progress
              :text-inside="true"
              :stroke-width="20"
              :percentage="80"
              status="warning"
            ></el-progress
          ></el-row>
          <el-row style="padding: 20px"
            ><el-avatar
              src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
            ></el-avatar
            ><el-progress
              :text-inside="true"
              :stroke-width="20"
              :percentage="50"
              status="exception"
            ></el-progress
          ></el-row>
        </el-col>
      </el-row>
    </div>
  </el-card>
</template>

<script>
import state from "../../store/state";

export default {
  name: "testpage",

  data() {
    return {
      a: "",
      waiting: true,
      websocket: WebSocket,
      questions: [],
    };
  },
  created() {
    // console.log("WEBSOCKET_USERNAME",state.state.id);
    // sessionStorage.setItem("WEBSOCKET_USERNAME",state.state.id);
    // console.log(sessionStorage.getItem("WEBSOCKET_USERNAME"));
  },
  mounted() {
    createWebSocket();
  },
  methods: {
    /**
     * 向后端发答案
     * @param n 答案，int型 1 2 3 4
     */
    sendAnswer(n) {
      this.websocket.send(n);
    },

    /**
     * 用户点 参加对抗 后创建socket连接
     * 不是匹配好才创建连接，开始匹配就创建连接
     */
    createWebSocket() {
      this.websocket = new WebSocket(
        "ws://localhost:9094/webSocket/" + state.state.id + this.a
      ); //a是测试用的，为了一个浏览器模拟多个用户
      this.a = this.a + "1";

      // 连接成功时
      this.websocket.onopen = () => {
        // websocket.send('hello')
        console.log("建立连接");
      };
      this.websocket.onmessage = (event) => {
        // 后端发送的消息在event.data中
        console.log(event.data);
        var messageType = event.data[0];
        var message = event.data.slice(1);
        switch (messageType) {
          case "a":
          case "i":
            // 传来问题
            console.log(JSON.parse(message));
            break;

          case "+":
            // 同一个房间中某个用户回答正确（可能是自己也可能不是自己）
            // message中的内容时用户id（回答正确的人的id）
            break;

          case "-":
            // 当前用户回答错误
            break;

          case "e":
            // 有一个用户答对了五道题，比赛结束
            // message是每个人的分数（答对的题目数）就按答对的题目数量给分吧，对几题给几分

            console.log(JSON.parse(message));
            break;
        }
        // if (event.data[0] == "a") {
        //
        //   console.log(JSON.parse(event.data.slice(1)))
        // }
      };
      this.websocket.onclose = function () {
        console.log("关闭了");
      };
      this.websocket.onerror = function () {
        console.log("连接错误");
      };
      // 路由跳转时结束websocket链接
      this.$router.afterEach(function () {
        this.websocket.close();
      });
      // 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常
      window.onbeforeunload = function () {
        this.websocket.close();
      };
    },
  },
};
</script>

<style scoped>
</style>