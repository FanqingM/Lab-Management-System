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
          <h1 style="font-size: 30px; text-align: center; color: black">
            正在匹配队友
          </h1>
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
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        ></el-avatar>
        <el-avatar v-else :size="100" style="margin: 10px">
          等待加入
        </el-avatar>
        <el-avatar
          v-if="num_member >= 3"
          :size="100"
          style="margin: 10px"
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        ></el-avatar>
        <el-avatar v-else :size="100" style="margin: 10px">
          等待加入
        </el-avatar>
        <el-avatar
          v-if="num_member >= 4"
          :size="100"
          style="margin: 10px"
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        ></el-avatar>
        <el-avatar v-else :size="100" style="margin: 10px">
          等待加入
        </el-avatar>
      </div>
    </div>
    <div v-else>
      <el-row style="height: 100%">
        <el-col :span="16">
          <h2 style="margin-left: 20px">题目</h2>
          <el-row style="padding: 20px">{{ question.question }}</el-row>
          <el-row style="padding: 20px"
            ><el-button
              type="primary"
              round
              @click="sendAnswer(1)"
              style="margin-right: 20px"
              >A</el-button
            >{{ question.a }}</el-row
          >
          <el-row style="padding: 20px"
            ><el-button
              type="primary"
              round
              @click="sendAnswer(2)"
              style="margin-right: 20px"
              >B</el-button
            >{{ question.b }}</el-row
          >
          <el-row style="padding: 20px"
            ><el-button
              type="primary"
              round
              @click="sendAnswer(3)"
              style="margin-right: 20px"
              >C</el-button
            >{{ question.c }}</el-row
          >
          <el-row style="padding: 20px"
            ><el-button
              type="primary"
              round
              @click="sendAnswer(4)"
              style="margin-right: 20px"
              >D</el-button
            >{{ question.d }}</el-row
          >
        </el-col>
        <el-col :span="8">
          <el-card class="scorecard">
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
                :percentage="score0"
                :format="format"
                status="warning"
              ></el-progress>
            </el-row>
            <el-row style="padding: 20px"
              ><el-avatar
                src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
              ></el-avatar
              ><el-progress
                :text-inside="true"
                :stroke-width="20"
                :percentage="score1"
                :format="format"
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
                :percentage="score2"
                :format="format"
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
                :percentage="score3"
                :format="format"
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
      connstate: false,
      waiting: true,
      num_member: 2,
      tmpcnt: 1,
      websocket: WebSocket,
      question: {
        question: "",
        a: "",
        b: "",
        c: "",
        d: "",
      },
      ids: [store.state.id, "", "", ""],
      score0: 0,
      score1: 0,
      score2: 0,
      score3: 0,
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

    format(percentage) {
      return percentage;
    },

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
      console.log("sent:", n);
    },

    /**
     * 用户点 参加对抗 后创建socket连接
     * 不是匹配好才创建连接，开始匹配就创建连接
     */
    createWebSocket() {
      this.websocket = new WebSocket(
        "ws://139.196.114.7:9094//webSocket/" + store.state.id
      );

      // 连接成功时
      this.websocket.onopen = () => {
        // websocket.send('hello')
        this.connstate = true;
        console.log("建立连接");
        this.score0 = 0;
        this.score1 = 0;
        this.score2 = 0;
        this.score3 = 0;
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
              this.score0 += 20;
              this.playCorrect();
              if (this.score0 >= 100) {
                this.websocket.close();
              }
            } else if (this.ids.indexOf(message) == -1) {
              this.ids[this.tmpcnt] = message;
              switch (this.tmpcnt) {
                case 1:
                  this.score1 += 20;
                  break;
                case 2:
                  this.score2 += 20;
                  break;
                default:
                  this.score3 += 20;
              }
              this.tmpcnt++;
            } else {
              switch (this.ids.indexOf(message)) {
                case 1:
                  this.score1 += 20;
                  if (this.score1 >= 100) {
                    this.websocket.close();
                  }
                  break;
                case 2:
                  this.score2 += 20;
                  if (this.score2 >= 100) {
                    this.websocket.close();
                  }
                  break;
                default:
                  this.score3 += 20;
                  if (this.score3 >= 100) {
                    this.websocket.close();
                  }
              }
            }
            break;

          case "-":
            // 当前用户回答错误
            this.playWrong();
            break;

          case "e":
            // 有一个用户答对了五道题，比赛结束
            // message是每个人的分数（答对的题目数）就按答对的题目数量给分吧，对几题给几分
            this.websocket.close();
            console.log(JSON.parse(message));
            break;
        }
        // if (event.data[0] == "a") {
        //
        //   console.log(JSON.parse(event.data.slice(1)))
        // }
      };
      this.websocket.onclose = () => {
        if (this.connstate == true) {
          var rank = 1;
          if (this.score1 > this.score0) {
            rank++;
          }
          if (this.score3 > this.score0) {
            rank++;
          }
          if (this.score3 > this.score0) {
            rank++;
          }
          this.$alert("对抗练习结束，你的小组排名为" + rank, "练习结束", {
            confirmButtonText: "确定",
            callback: (action) => {
              this.$router.push({ path: "/student/test-list" });
            },
          });
        }
        console.log("连接关闭");
      };
      this.websocket.onerror = () => {
        this.connstate = false;
        this.$alert("抱歉，当前无法连接。", "连接错误", {
          confirmButtonText: "确定",
          callback: (action) => {
            this.$router.go(-1);
          },
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
.scorecard {
  height: 100%;
  background: #4d5ce8;
  color: white;
  border-radius: 12px;
}
</style>