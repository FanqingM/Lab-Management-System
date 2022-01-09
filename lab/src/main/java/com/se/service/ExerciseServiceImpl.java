package com.se.service;

import com.se.controller.WebSocket;
import com.se.entity.Question;
import com.se.mapper.QuestionMapper;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    @Autowired
    private QuestionMapper questionMapper;

    static private int MEMBERNUM = 4;

    static private List<Group> groups = new ArrayList<>();

    static private List<String> waitUsers = new ArrayList<>();

    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();

    static int tag = 0;

    @Override
    public List<Question> findNQuestions(int n) {
        List<Question> questionList;
        while (true) {
            questionList = questionMapper.findNQuestions(n);
            if (questionList.size() >= n) {
                return questionList;
            }
        }
    }

    @Override
    public Integer addWaitUsers(String userId, WebSocket socket) {

        if (waitUsers.size() == 0) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        //do Something
                        if (groups.size() == 0) {
                            tag = -1;
                            addWaitUsers("robot1", null);
                            addWaitUsers("robot2", null);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 15000);
        }

        waitUsers.add(userId);
        if (userId.indexOf("robot") != 0) {
            clients.put(userId, socket);
        }
        if (waitUsers.size() >= MEMBERNUM) {
            Group newGroup = new Group();
            newGroup.setMembers(waitUsers);
//            List<Question> questionList = findNQuestions(3);
//            for (Question question:questionList){
//                newGroup.addAnswer(question.getQuestionId(),question.getAnswer());
//            }
            groups.add(newGroup);
            for (String user : waitUsers) {
                sendQuestion(newGroup, user, false);
            }

            //发头像
            waitUsers = new ArrayList<>();
            return 1;
        } else {

            //加头像之类的
            return 2;
        }
    }


    void autoAnswer() {
        System.out.println("run autoanswer");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("timerrun");
                    try {
                        //do Something
                        if (tag == 0) {
                            checkAnswer("robot1", 0);
                            tag = 1;
                        } else {
                            checkAnswer("robot2", 0);
                            tag = 0;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }, 6000, 6000);
    }

    /**
     * 弃用
     */
    @Override
    public void addGroup() {
//        List<String> newGroup = new ArrayList<>();
//        for (int i=0;i<MEMBERNUM;i++){
//            newGroup.add(waitUsers.remove(0));
//        }
//        groups.add(newGroup);

        //分配题目
    }

    /**
     * 只剩一个人时删除组
     *
     * @param userId
     */
    @Override
    public void removeGroupIf1(String userId) {
        for (Group group : groups) {
            if (group.haveMember(userId)) {
                if (group.size() <= 1) {
                    groups.remove(group);
                }
                return;
            }
        }
    }

    @Override
    public Group getGroup(String userId) {
        for (Group group : groups) {
            if (group.getMembers().contains(userId)) {
                return group;
            }
        }
        return null;
    }

    public void sendQuestion(Group group, String userId, Boolean addGrade) {
        //保存问题
        if (userId.indexOf("robot") != 0) {
            Question q = findOneQuestion(group, userId);
            group.setAnswer(userId, q.getAnswer());
            group.answered(userId, q.getQuestionId());

            WebSocket socket = clients.get(userId);
            socket.sendQuestion(q, addGrade);
        }
    }

    public Question findOneQuestion(Group group, String userId) {
        Question q;
        while (true) {
            q = questionMapper.findOne();
            if (q != null && !group.haveAnswered(userId, q.getQuestionId())) {
//                answeredQuestions.add(q.getQuestionId());
                return q;
            }
        }
    }

    @Override
    public void checkAnswer(String userId, Integer ans) {
        if (tag == -1) {
            tag = 0;
            autoAnswer();
        }
        Group group = getGroup(userId);
        if (group.getAnswer(userId) == ans) {
            if (group.lastTimeWrong(userId)) {
                sendQuestion(group, userId, true);
                return;
            }
            if (group.addGrade(userId)) {
                refresh(group, userId);
                end(group, userId);
            } else {
                sendQuestion(group, userId, true);
                refresh(group, userId);
            }
        } else { //答错了
            sendWrong(userId);
            group.wrong(userId);
        }

    }

    public void end(Group group, String winner) {
        for (String userId : group.getMembers()) {
            sendEnd(group, userId);
            clients.remove(userId);

            // 数据库加分

        }
        groups.remove(group);
    }

    public void refresh(Group group, String userId) {
        for (String user : group.getMembers()) {
            sendRight(user, userId);
        }
    }

    public void sendRight(String to, String rightUser) {
        if (to.indexOf("robot") != 0) {
            clients.get(to).sendRight(rightUser);
        }
    }

    public void sendWrong(String userId) {
        if (userId.indexOf("robot") != 0) {
            clients.get(userId).sendWrong();
        }
    }

    public void sendEnd(Group group, String to) {
        if (to.indexOf("robot") != 0) {
            clients.get(to).sendEnd(group.getGrades());
        }
    }

}

@Data
class Group {
    private static Integer MAXGRADE = 5;

    private List<String> members;

    private Map<String, Integer> answer = new HashMap<>();

    private Map<String, Integer> grades = new HashMap<>();

    private Map<String, List<String>> answeredQuestions = new HashMap<>();

    private Map<String, Boolean> answerWrong = new HashMap<>();

    public Boolean haveMember(String userId) {
        return members.contains(userId);
    }

//    public void addAnswer(String questionId,Integer ans){
//        answer.put(questionId,ans);
//    }

    public Integer size() {
        return members.size();
    }

    public void setMembers(List<String> members) {
        this.members = members;
        for (String user : members) {
            grades.put(user, 0);
            answerWrong.put(user, false);
            answeredQuestions.put(user, new ArrayList<>());
            if (user.indexOf("robot") == 0) {
                setAnswer(user, 0);
            }
        }
    }

    public void setAnswer(String userId, Integer ans) {
        answer.put(userId, ans);
    }

    public Integer getAnswer(String userId) {
        return answer.get(userId);
    }


    /**
     * @param userId
     * @return 是否到达五分
     */
    public Boolean addGrade(String userId) {
        answerWrong.put(userId, false);
        Integer curGrade = grades.get(userId);
        grades.put(userId, curGrade += 1);
        return curGrade >= MAXGRADE;
    }

    public Boolean haveAnswered(String userId, String quesId) {
        return answeredQuestions.get(userId).contains(quesId);
    }

    public void answered(String userId, String quesId) {
        List<String> answered = answeredQuestions.get(userId);
        answered.add(quesId);
        //是否已经改了
        answeredQuestions.put(userId, answered);
    }

    public void wrong(String userId) {
        answerWrong.put(userId, true);
    }

    public Boolean lastTimeWrong(String userId) {
        if (userId.indexOf("robot") == 0) {
            return false;
        }
        Boolean tmp = answerWrong.get(userId);
        answerWrong.put(userId, false);
        return tmp;
    }
}

