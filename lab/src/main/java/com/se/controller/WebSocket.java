//package com.se.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.se.entity.Question;
//import com.se.mapper.QuestionMapper;
//import com.se.service.ExerciseService;
//import com.se.service.ExerciseServiceImpl;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.PostConstruct;
//import javax.websocket.*;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@ServerEndpoint("/webSocket/{userId}")
//@Slf4j
//@Component
//public class WebSocket {
//
//    @Autowired
//    ExerciseService exerciseService;
//
//    private static WebSocket webSocket;
//
//    @PostConstruct
//    public void init(){
//        webSocket = this;
//        webSocket.exerciseService = this.exerciseService;
//    }
//
//    private static int onlineCount = 0;
//    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();
//    private Session session;
//    private String userId;
//
//    @OnOpen
//    public void onOpen(@PathParam("userId") String userId, Session session) {
//        this.userId = userId;
//        this.session = session;
//        WebSocket.onlineCount++;
//        clients.put(userId, this);
//
////        Question q = new Question();
////        q.setQuestionId("123");
////        q.setQuestion("what's wrong");
////        q.setA("achoice");
////        q.setB("bchoice");
////        q.setC("cchoice");
////        q.setD("dchoice");
////        q.setAnswer(1);
////
////        Question q1 = new Question();
////        q1.setQuestionId("123");
////        q1.setQuestion("what's wrong");
////        q1.setA("achoice");
////        q1.setB("bchoice");
////        q1.setC("cchoice");
////        q1.setD("dchoice");
////        q1.setAnswer(1);
////
////        List<Question> list = new ArrayList<>();
////        list.add(q1);
////        list.add(q);
//
////        if (exerciseService.addWaitUsers(userId,this) == 1){ //已经成团
//////            System.out.println(exerciseService.findNQuestions(3));
////        }
////        System.out.println(JSON.toJSONString(list));
////        sendMessage("q"+JSON.toJSONString(q));
//
//
//        webSocket.exerciseService.addWaitUsers(userId,this);
//        System.out.println(onlineCount);
//    }
//
//    @OnClose
//    public void onClose() {
//        clients.remove(userId);
//        WebSocket.onlineCount--;
////        exerciseService.removeGroupIf1(userId);
//    }
//
//    @OnMessage
//    public void onMessage(String message) {
//
//        webSocket.exerciseService.checkAnswer(this.userId,Integer.valueOf(message));
//    }
//
//    @OnError
//    public void onError(Session session, Throwable throwable) {
//        log.error("WebSocket发生错误：" + throwable.getMessage());
//    }
//
//    public void sendMessage(String message) {
//        // 向所有连接websocket的客户端发送消息
//        // 可以修改为对某个客户端发消息
//        this.session.getAsyncRemote().sendText(message);
//    }
//
//    public void sendQuestion(Question q,Boolean addGrade){
//        if (addGrade){
//            sendMessage("a"+JSON.toJSONString(q));
//        }else{
//            sendMessage("i"+JSON.toJSONString(q));
//        }
//    }
//
//    public void sendRight(String userId){
//        sendMessage("+"+userId);
//    }
//
//    public void sendWrong(){
//        sendMessage("- ");
//    }
//
//    public void sendEnd(Map<String, Integer> grades){
//        sendMessage("e"+JSON.toJSONString(grades));
//    }
//}
//
