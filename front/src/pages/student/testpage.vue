<template>
  <el-card>
    <audio
        src="http://data.huiyi8.com/2020//12-14/2f221550.mp3"
        controls="controls"
        hidden
        ref="correctaudio"
      ></audio>
      <audio
        src="http://downsc.chinaz.net/Files/DownLoad/sound1/201512/6731.mp3"
        controls="controls"
        hidden
        ref="unbelievable"
      ></audio>
      <audio
        src="http://data.huiyi8.com/2021//05-05/8348557f.mp3"
        controls="controls"
        hidden
        ref="wrongaudio"
      ></audio>
    <div v-if="waiting == true">
      <el-row>
        <div
          style="display: flex; align-items: center; justify-content: center"
        >
          <img
            src="https://img.zcool.cn/community/010a865cf9cf98a801213ec2a01fca.gif"
          />
        </div>
        <el-col :span="24">
          <h1 style="font-size: 30px; text-align: center; color: black">正在匹配队友</h1>
        </el-col>
      </el-row>
      <div style="display: flex; align-items: center; justify-content: center">
        <el-avatar
          :size="100"
          style="margin: 10px"
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        ></el-avatar>
        <el-avatar
          v-if="num_member >= 2"
          :size="100"
          style="margin: 10px"
          src="https://t12.baidu.com/it/u=83297312,2774143649&fm=58"
        ></el-avatar>
        <el-avatar v-else :size="100" style="margin: 10px">
          等待加入
        </el-avatar>
        <el-avatar
          v-if="num_member >= 3"
          :size="100"
          style="margin: 10px"
          src="https://t10.baidu.com/it/u=1857180495,278749235&fm=58"
        ></el-avatar>
        <el-avatar v-else :size="100" style="margin: 10px">
          等待加入
        </el-avatar>
        <el-avatar
          v-if="num_member >= 4"
          :size="100"
          style="margin: 10px"
          src="https://t11.baidu.com/it/u=2957415832,879336052&fm=58"
        ></el-avatar>
        <el-avatar v-else :size="100" style="margin: 10px">
          等待加入
        </el-avatar>
      </div>
    </div>
    <div v-else>
      <el-row style="height: 100%">
        <el-col :span="16">
          <el-row style="padding: 20px"
            >{{ question.question }}</el-row
          >
          <el-row style="padding: 20px"
            ><el-button @click="sendAnswer(1)" style="margin-right: 20px"
              >A</el-button
            >{{ question.a }}</el-row
          >
          <el-row style="padding: 20px"
            ><el-button @click="sendAnswer(2)" style="margin-right: 20px"
              >B</el-button
            >{{ question.b }}</el-row
          >
          <el-row style="padding: 20px"
            ><el-button @click="sendAnswer(3)" style="margin-right: 20px"
              >C</el-button
            >{{ question.c }}</el-row
          >
          <el-row style="padding: 20px"
            ><el-button @click="sendAnswer(4)" style="margin-right: 20px"
              >D</el-button
            >{{ question.d }}</el-row
          >
          <!-- <el-row style="padding: 20px"
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
          > -->
        </el-col>
        <el-col :span="8">
          <el-card
            Style="height: 100%; background: #4D5CE8; color: white; border-radius: 12px;"
          >
            <h2 style="margin-left: 20px">当前得分</h2>
            <p style="margin-left: 20px">先达到100分者获胜</p>
            <el-row style="padding: 20px">
              <el-avatar
                src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
              >
              </el-avatar>
              <el-progress
                :text-inside="true"
                :stroke-width="30"
                :percentage="scores[0]"
                status="warning"
              ></el-progress>
            </el-row>
            <el-row style="padding: 20px"
              ><el-avatar
                src="https://t12.baidu.com/it/u=83297312,2774143649&fm=58"
              ></el-avatar
              ><el-progress
                :text-inside="true"
                :stroke-width="20"
                :percentage="scores[1]"
                status="warning"
              ></el-progress
            ></el-row>
            <el-row style="padding: 20px"
              ><el-avatar
                src="https://t10.baidu.com/it/u=1857180495,278749235&fm=58"
              ></el-avatar
              ><el-progress
                :text-inside="true"
                :stroke-width="20"
                :percentage="scores[2]"
                status="warning"
              ></el-progress
            ></el-row>
            <el-row style="padding: 20px"
              ><el-avatar
                src="https://t11.baidu.com/it/u=2957415832,879336052&fm=58"
              ></el-avatar
              ><el-progress
                :text-inside="true"
                :stroke-width="20"
                :percentage="scores[3]"
                status="warning"
              ></el-progress
            ></el-row>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </el-card>
</template>

<script>
import store from "../../store/state";

export default {
  name: "testpage",

  data() {
    return {
      waiting: true,
      num_member: 3,
      tmpcnt: 1,
      websocket: WebSocket,
      question: {
        question: "",
        a: "",
        b: "",
        c: "",
        d: ""
      },
      ids: [store.state.id, "", "", ""],
      scores: [0, 0, 0, 0]
    };
  },
  created() {
    // console.log("WEBSOCKET_USERNAME",state.state.id);
    // sessionStorage.setItem("WEBSOCKET_USERNAME",state.state.id);
    // console.log(sessionStorage.getItem("WEBSOCKET_USERNAME"));
  },
  mounted() {
    this.createWebSocket();
    console.log("create");
  },
  methods: {
    /**
     * 向后端发答案
     * @param n 答案，int型 1 2 3 4
     */
    playCorrect() {
      this.$refs.correctaudio.currentTime = 0; //从头开始播放
      this.$refs.correctaudio.play(); //播放
    },

    playWrong() {
      this.$refs.wrongaudio.currentTime = 0; //从头开始播放
      this.$refs.wrongaudio.play(); //播放
    },

    sendAnswer(n) {
      this.websocket.send(n);
    },

    /**
     * 用户点 参加对抗 后创建socket连接
     * 不是匹配好才创建连接，开始匹配就创建连接
     */
    createWebSocket() {
      this.websocket = new WebSocket(
        "ws://localhost:9094/webSocket/" + store.state.id
      );

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
          case "i":
            this.waiting = false;
          case "a":
            // 传来问题
            console.log(JSON.parse(message));
            this.question = JSON.parse(message);
            break;

          case "+":
            // 同一个房间中某个用户回答正确（可能是自己也可能不是自己）
            // message中的内容时用户id（回答正确的人的id）
            if (message == store.state.id) {
              this.scores[0] += 20;
              this.playCorrect();
            } else if (this.ids.indexOf(message) == -1) {
              this.ids[this.tmpcnt] = message;
              this.scores[this.tmpcnt] += 20;
              console.log(message, this.scores[this.tmpcnt]);
              this.tmpcnt++;
            } else {
              this.scores[this.ids.indexOf(message)] += 20;
              console.log(message, this.scores[this.ids.indexOf(message)]);
            }

            break;

          case "-":
            // 当前用户回答错误
            this.playWrong();
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
      this.websocket.onclose = () => {
        var rank = 1;
        for (let i = 1; i < 4; ++i) {
          if (this.scores[i] > this.scores[0]) {
            rank++;
          }
        }
        this.$alert('对抗练习结束，你的小组排名为' + rank, '练习结束', {
          confirmButtonText: '确定',
          callback: action => {
            this.$router.go(-1);
          }
        });
        console.log("连接关闭");
      };
      this.websocket.onerror = () => {
        this.$alert('抱歉，当前无法连接。', '连接错误', {
          confirmButtonText: '确定',
          callback: action => {
            this.$router.go(-1);
          }
        });
        console.log("连接错误");
      };
      // 路由跳转时结束websocket链接
      this.$router.afterEach(() => {
        this.websocket.close();
      });
      // 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常
      window.onbeforeunload = () => {
        this.websocket.close();
      };
    },
  },
};
</script>

<style scoped>
</style>