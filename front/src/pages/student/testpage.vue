<template>
  <el-card>
    <el-button @click="createWebSocket()">建立连接</el-button>
    <el-button @click="sendAnswer(1)">send1</el-button>
    <el-button @click="sendAnswer(2)">send2</el-button>
    <el-button @click="sendAnswer(3)">send3</el-button>
    <el-button @click="sendAnswer(4)">send4</el-button>
  </el-card>
</template>

<script>
import state from "../../store/state";

export default {
  name: "testpage",

  data() {
    return {
      a: '',
      websocket: WebSocket,
      questions: []
    };
  },
  created() {
    // console.log("WEBSOCKET_USERNAME",state.state.id);
    // sessionStorage.setItem("WEBSOCKET_USERNAME",state.state.id);
    // console.log(sessionStorage.getItem("WEBSOCKET_USERNAME"));
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
      this.websocket = new WebSocket('ws://localhost:9094/webSocket/' + state.state.id + this.a) //a是测试用的，为了一个浏览器模拟多个用户
      this.a = this.a + '1'

      // 连接成功时
      this.websocket.onopen = () => {
        // websocket.send('hello')
        console.log("建立连接")
      }
      this.websocket.onmessage = event => {
        // 后端发送的消息在event.data中
        console.log(event.data)
        var messageType = event.data[0];
        var message = event.data.slice(1);
        switch (messageType) {
          case 'a':
          case 'i':
            // 传来问题
            console.log(JSON.parse(message))
            break

          case '+':
            // 同一个房间中某个用户回答正确（可能是自己也可能不是自己）
            // message中的内容时用户id（回答正确的人的id）
            break

          case '-':
            // 当前用户回答错误
            break

          case 'e':
            // 有一个用户答对了五道题，比赛结束
            // message是每个人的分数（答对的题目数）就按答对的题目数量给分吧，对几题给几分

            console.log(JSON.parse(message))
            break

        }
        // if (event.data[0] == "a") {
        //
        //   console.log(JSON.parse(event.data.slice(1)))
        // }
      }
      this.websocket.onclose = function () {
        console.log('关闭了')
      }
      this.websocket.onerror = function () {
        console.log('连接错误')
      }
      // 路由跳转时结束websocket链接
      this.$router.afterEach(function () {
        this.websocket.close()
      })
      // 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常
      window.onbeforeunload = function () {
        this.websocket.close()
      }
    },
  }
}
</script>

<style scoped>

</style>