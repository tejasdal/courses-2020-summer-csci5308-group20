package org.dal.cs5308.t20.Project.question;

import org.dal.cs5308.t20.Project.Factory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class QuestionController {

    private IQuestionService questionService = Factory.getQuestionService();

    @GetMapping("/question/create")
    public String createQuestion(Model model){
        model.addAttribute("question", new Question());
        return "createQuestion";
    }

    @PostMapping(value = "/question/create")
    public String createQuestion(Model model,@ModelAttribute Question question){
        try {
            this.questionService.createQuestion(question);
        } catch (QuestionException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "displayQuestions";
    }

    @GetMapping(value = "/question/option")
    public String addOptionToQuestion(Model model, @ModelAttribute Question question){
        System.out.println(question.getTitle() + question.getDescription() + question.getQuestionType());
        model.addAttribute("question", question);
        return "addOptionToQuestion";
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

        System.out.println(question.getTitle() + question.getDescription() + question.getQuestionType());
        model.addAttribute("question", question);
        if (question.getQuestionType() == Question.getMultipleChoiceChooseOne()
                || question.getQuestionType() == Question.getMultipleChoiceChooseMany() || isMcqPrototype != 1){
            return "redirect:/question/option";
        }
        return "displayQuestionPrototype";
    }
}
