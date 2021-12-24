package com.se.mapper;

import com.se.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {

    @Select("SELECT * FROM QUESTION as t1 WHERE t1.QUESTION_ID>=(RAND()*(SELECT MAX(QUESTION_ID) FROM QUESTION))LIMIT #{n}")
//    @Select("SELECT * FROM QUESTION")
    List<Question> findNQuestions(int n);

    @Select("SELECT * FROM QUESTION as t1 WHERE t1.QUESTION_ID>=(RAND()*(SELECT MAX(QUESTION_ID) FROM QUESTION))LIMIT 1")
//    @Select("SELECT * FROM QUESTION")
    Question findOne();
}
