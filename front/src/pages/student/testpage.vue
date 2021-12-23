<template>
<el-button @click="createWebSocket()">建立连接</el-button>
</template>

<script>
import state from "../../store/state";
export default {
  name: "testpage",

  data() {
    return {

    };
  },
  created() {
    // console.log("WEBSOCKET_USERNAME",state.state.id);
    // sessionStorage.setItem("WEBSOCKET_USERNAME",state.state.id);
    // console.log(sessionStorage.getItem("WEBSOCKET_USERNAME"));
  },
  methods:{
    // 与websocket服务器创建连接
    createWebSocket() {
      // 注意这里的端口号是后端服务的端口号，后面的是后端正常请求的路径，ziyuan是我的项目名，最后面的是我放在cookie中的当前登陆用户
      // let websocket = new WebSocket('ws://127.0.0.1:9094/ziyuan/webSocket/' + this.$cookie.get('nameOrEmail'))
      let websocket = new WebSocket('ws://localhost:9094/webSocket/' +'123456')

      // 连接成功时
      websocket.onopen = () => {
        websocket.send('hello')
        console.log("建立连接")
      }
      websocket.onmessage = event => {
        // 后端发送的消息在event.data中
        console.log(event.data)
      }
      websocket.onclose = function () {
        console.log('关闭了')
      }
      websocket.onerror = function (){
        console.log('连接错误')
      }
      // 路由跳转时结束websocket链接
      this.$router.afterEach(function () {
        websocket.close()
      })
      // 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常
      window.onbeforeunload = function () {
        websocket.close()
      }
    },
    connect(){
      var websocket;
      if ('WebSocket' in window) {
        console.log("WebSocket-->");
        //new WebSocket()在IDEA打开的任何项目中都可以 直接调用
        websocket = new WebSocket("ws://localhost:9094/my_SXD_Socket");
      } else if ('MozWebSocket' in window) {
        console.log("MozWebSocket-->");
        websocket = new MozWebSocket("ws://my_SXD_Socket");
      } else {
        console.log("SockJS-->");
        websocket = new SockJS("http://127.0.0.1:9094/sockjs/my_SXD_Socket");
      }

      websocket.onopen = function (evnt) {
        console.log("链接服务器成功!", evnt.data);
      };
      websocket.onmessage = function (evnt) {
        console.log('收到消息:', evnt.data);
        var json = JSON.parse(evnt.data);
        if (json.hasOwnProperty('users')) {
          var users = json.users;
          for (var i = 0; i < users.length; i++) {
            $("#inputGroupSelect01").append('<option value="' + users[i] + '">' + users[i] + '</option>');
          }
        } else {
          //打印消息
          toast(json.msg, 'info')
        }
      };
      websocket.onerror = function (evnt) {
        console.log("onerror");
      };
      websocket.onclose = function (evnt) {
        console.log("与服务器断开了链接!")
      }

    }
  }

}
</script>

<style scoped>

</style>