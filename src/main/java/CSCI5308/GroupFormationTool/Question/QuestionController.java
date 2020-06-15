package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class QuestionController {


    @RequestMapping(value="/instructor/questions")
    public String getAllUserQuestions
            (@RequestParam(name="userId") Long userId,
             @RequestParam(name="sortBy",defaultValue="date",required=false) String sortBy,
             Model model){
        IQuestionService questionService = SystemConfig.instance().getQuestionService();
        IQuestionPersistence questionPersistence = SystemConfig.instance().getQuestionPersistence();
        List<Question> questions = questionService.getAllQuestionOfInstructor(userId,sortBy,questionPersistence);
        model.addAttribute("questions",questions);
        model.addAttribute("userId",userId);
        return "questions/AllUserQuestions";
    }

    @RequestMapping(value="/instructor/deletequestion")
    public String deleteQuestion(@RequestParam(name="questionId") Long questionId,
                                 @RequestParam(name="userId") Long userId,
                                 RedirectAttributes redirectAttributes){
        IQuestionService questionService = SystemConfig.instance().getQuestionService();
        IQuestionPersistence questionPersistence = SystemConfig.instance().getQuestionPersistence();
        questionService.deleteQuestion(questionId,questionPersistence);
        //ModelAndView mv = new ModelAndView("redirect:/questions/AllUserQuestions");
        redirectAttributes.addAttribute("userId",userId);
        return "redirect:/instructor/questions";
    }

    @GetMapping("/instructor/{id}/question/create")
    public String createQuestion(Model model, @PathVariable("id") Long instructorId){
        Question question = new Question();
        question.setUserId(instructorId);
        model.addAttribute("question",question);
        return "questions/createQuestion";
    }

    @PostMapping(value = "/question/create")
    public String createQuestion(Model model,@ModelAttribute Question question, RedirectAttributes redirectAttributes){
        try {
            IQuestionService questionService = SystemConfig.instance().getQuestionService();
            questionService.createQuestion(question, SystemConfig.instance().getQuestionPersistence());
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
        question.getQuestionOptions().add(new QuestionOption());
        return "questions/addOptionToQuestion";
    }

    /**
     * If <code>questionType</code> is MCQ and path variable <code>isMcdPrototype</code> is 1,
     * then display prototype for MCQ question, else redirect it to <code>/question/option</code>.
     *
     * If <code>questionType</code> is either Numeric or FreeText, then display prototype for the
     * question.
     *
     * @param model
     * @param question
     * @return
     */
    @PostMapping(value = "/question/prototype/{isMcqPrototype}")
    public String displayQuestionPrototype(Model model, @ModelAttribute Question question,
                                           @PathVariable(value = "isMcqPrototype", required = false) Integer isMcqPrototype){

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
