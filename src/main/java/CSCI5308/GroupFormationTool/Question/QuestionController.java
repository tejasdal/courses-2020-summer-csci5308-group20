package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class QuestionController {

    private Logger log = LoggerFactory.getLogger(QuestionController.class);

    @RequestMapping(value="/instructor/questions")
    public String getAllUserQuestions
            (@RequestParam(name="userId") Long userId,
             Model model){
        log.info("Processing a request to load all questions for instructor with userID: {}", userId);
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
        log.info("Processing a request to load all questions sorted by title for instructor with userID: {}", userId);
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
        log.info("Processing a request to load all questions sorted by created date for instructor with userID: {}", userId);
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
        log.info("Processing a request to delete a question with ID: {} for instructor with userID: {}", questionId, userId);
        IQuestionService questionService = QuestionServiceAbstractFactory.instance().makeService();
        IQuestionPersistence questionPersistence = QuestionPersistenceAbstractFactory.instance().makePersistence();
        questionService.deleteQuestion(questionId,questionPersistence);
        redirectAttributes.addAttribute("userId",userId);
        return "redirect:/instructor/questions";
    }

    @GetMapping("/instructor/{id}/question/create")
    public String createQuestion(Model model, @PathVariable("id") Long instructorId){
        log.info("Processing a request to load a page to create a question for instructor with userID: {}", instructorId);
        IQuestion question = QuestionServiceAbstractFactory.instance().makeQuestion();
        question.setUserId(instructorId);
        model.addAttribute("question",question);
        return "questions/createQuestion";
    }

    @PostMapping(value = "/question/create")
    public String createQuestion(Model model,@ModelAttribute Question question, RedirectAttributes redirectAttributes){
        log.info("Processing a request to create a question for instructor.");
        try {
            IQuestionService questionService = QuestionServiceAbstractFactory.instance().makeService();
            questionService.createQuestion(question, QuestionPersistenceAbstractFactory.instance().makePersistence());
        } catch (Exception e) {
            log.warn("Error while processing a request to create a new question, error: {}", e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
        }
        redirectAttributes.addAttribute("userId",question.getUserId());
        return "redirect:/instructor/questions";
    }

    @PostMapping(value = "/question/more-option")
    public String addOptionToQuestion(Model model, @ModelAttribute Question question,
                                      @RequestParam(value = "moreAnswers",  required = false) String moreAnswers,
                                      @RequestParam(value = "next", required = false) String next){
        log.info("Processing a request to add more option to a question.");
        model.addAttribute("question", question);
        if (next != null){
            return "questions/displayQuestionPrototype";
        }
        question.getQuestionOptions().add(new QuestionOption());
        return "questions/addOptionToQuestion";
    }

    @PostMapping(value = "/question/prototype/{isMcqPrototype}")
    public String displayQuestionPrototype(Model model, @ModelAttribute Question question,
                                           @PathVariable(value = "isMcqPrototype", required = false) Integer isMcqPrototype){
        log.info("Processing a request to load a page to display prototype of a question.");
        if ((question.getQuestionType() == Question.getMultipleChoiceChooseOne()
                || question.getQuestionType() == Question.getMultipleChoiceChooseMany()) && isMcqPrototype != 1){
            question.getQuestionOptions().add(new QuestionOption());
            model.addAttribute("question", question);
            return "questions/addOptionToQuestion";
        }
        model.addAttribute("question", question);
        return "questions/displayQuestionPrototype";
    }
}
