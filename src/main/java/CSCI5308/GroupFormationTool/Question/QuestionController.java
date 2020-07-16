package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.CustomExceptions.QuestionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class QuestionController {
    
    @RequestMapping(value="/instructor/questions")
    public String getAllUserQuestions
            (@RequestParam(name="userId") Long userId,
             Model model){
        IQuestionService questionService = QuestionServiceAbstractFactory.instance().makeService();
        IQuestionPersistence questionPersistence = QuestionPersistenceAbstractFactory.instance().makePersistence();
        List<IQuestion> questions = questionService.getAllUserQuestions(userId, questionPersistence);
        model.addAttribute("questions",questions);
        model.addAttribute("userId",userId);
        return "questions/AllUserQuestions";
    }

    @RequestMapping(value="/instructor/questions/titleSort")
    public String getAllUserQuestionsSortedTitle
            (@RequestParam(name="userId") Long userId,
             Model model){
        IQuestionService questionService = QuestionServiceAbstractFactory.instance().makeService();
        IQuestionPersistence questionPersistence = QuestionPersistenceAbstractFactory.instance().makePersistence();
        List<IQuestion> questions = questionService.getAllUserQuestionsSortedByTitle(userId, questionPersistence);
        model.addAttribute("questions",questions);
        model.addAttribute("userId",userId);
        return "questions/AllUserQuestions";
    }

    @RequestMapping(value="/instructor/questions/dateSort")
    public String getAllUserQuestionsSortedDate
            (@RequestParam(name="userId") Long userId,
             Model model){
        IQuestionService questionService = QuestionServiceAbstractFactory.instance().makeService();
        IQuestionPersistence questionPersistence = QuestionPersistenceAbstractFactory.instance().makePersistence();
        List<IQuestion> questions = questionService.getAllUserQuestionsSortedByDate(userId, questionPersistence);
        model.addAttribute("questions",questions);
        model.addAttribute("userId",userId);
        return "questions/AllUserQuestions";
    }

    @RequestMapping(value="/instructor/deletequestion",method = RequestMethod.POST)
    public String deleteQuestion(@RequestParam(name="questionId") Long questionId,
                                 @RequestParam(name="userId") Long userId,
                                 RedirectAttributes redirectAttributes){
        IQuestionService questionService = QuestionServiceAbstractFactory.instance().makeService();
        IQuestionPersistence questionPersistence = QuestionPersistenceAbstractFactory.instance().makePersistence();
        questionService.deleteQuestion(questionId,questionPersistence);
        redirectAttributes.addAttribute("userId",userId);
        return "redirect:/instructor/questions";
    }

    @GetMapping("/instructor/{id}/question/create")
    public String createQuestion(Model model, @PathVariable("id") Long instructorId){
        IQuestion question = QuestionServiceAbstractFactory.instance().makeQuestion();
        question.setUserId(instructorId);
        model.addAttribute("question",question);
        return "questions/createQuestion";
    }

    @PostMapping(value = "/question/create")
    public String createQuestion(Model model,@ModelAttribute Question question, RedirectAttributes redirectAttributes){
        try {
            IQuestionService questionService = QuestionServiceAbstractFactory.instance().makeService();
            questionService.createQuestion(question, QuestionPersistenceAbstractFactory.instance().makePersistence());
        } catch (QuestionException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        redirectAttributes.addAttribute("userId",question.getUserId());
        return "redirect:/instructor/questions";
    }

    @PostMapping(value = "/question/more-option")
    public String addOptionToQuestion(Model model, @ModelAttribute Question question,
                                      @RequestParam(value = "moreAnswers",  required = false) String moreAnswers,
                                      @RequestParam(value = "next", required = false) String next){
        model.addAttribute("question", question);
        if (next != null){
            return "questions/displayQuestionPrototype";
        }
        question.getQuestionOptions().add(QuestionServiceAbstractFactory.instance().makeQuestionOption());
        return "questions/addOptionToQuestion";
    }

    @PostMapping(value = "/question/prototype/{isMcqPrototype}")
    public String displayQuestionPrototype(Model model, @ModelAttribute Question question,
                                           @PathVariable(value = "isMcqPrototype", required = false) Integer isMcqPrototype){

        if ((question.getQuestionType() == Question.getMultipleChoiceChooseOne()
                || question.getQuestionType() == Question.getMultipleChoiceChooseMany()) && isMcqPrototype != 1){
            question.getQuestionOptions().add(QuestionServiceAbstractFactory.instance().makeQuestionOption());
            model.addAttribute("question", question);
            return "questions/addOptionToQuestion";
        }
        model.addAttribute("question", question);
        return "questions/displayQuestionPrototype";
    }
}
