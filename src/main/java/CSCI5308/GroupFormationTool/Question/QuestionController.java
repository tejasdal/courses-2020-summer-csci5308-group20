package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class QuestionController {


    @RequestMapping(value="/instructor/questions")
    public String getAllUserQuestions(@RequestParam(name="userId") Long userId, Model model){
        IQuestionService questionService = SystemConfig.instance().getQuestionService();
        IQuestionPersistence questionPersistence = SystemConfig.instance().getQuestionPersistence();
        List<Question> questions = questionService.getAllQuestionOfInstructor(userId,questionPersistence);
        model.addAttribute("questions",questions);
        return "questions/AllUserQuestions";
    }
}
