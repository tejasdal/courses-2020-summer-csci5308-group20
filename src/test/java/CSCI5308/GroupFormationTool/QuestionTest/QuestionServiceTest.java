package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.Question.QuestionPersistence;
import CSCI5308.GroupFormationTool.Question.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class QuestionServiceTest {

    @InjectMocks
    QuestionService questionService;

    @Mock
    QuestionPersistence questionPersistence;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllUserQuestions(){
        List<Question> questionList = new ArrayList<Question>();
        Date date = new Date(20200614L);
        Question q1 = new Question(1L,"TestTitle1","Testing",2L,1,date,null);
        Question q2 = new Question(2L,"TestTitle2","Testing",2L,1,date,null);
        questionList.add(q1);
        questionList.add(q2);

        when(questionPersistence.getAllQuestionsForUser(2L)).thenReturn(questionList);
        List<Question> returnedList = questionService.getAllQuestionOfInstructor(2L,questionPersistence);
        assertEquals(2,returnedList.size());


    }

}
