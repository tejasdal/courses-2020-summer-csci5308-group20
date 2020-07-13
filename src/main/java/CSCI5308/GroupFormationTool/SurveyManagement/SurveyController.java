package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Question.Answers;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.Map;

@Controller
public class SurveyController {

    @RequestMapping(value = "/instructor/survey")
    public String surveyManagementPage
            (@RequestParam(name = "userId") Long userId,
             @RequestParam(name = "courseId") Long courseId,
             Model model) {
        model.addAttribute("courseId", courseId);
        model.addAttribute("userId", userId);

        ISurveyService surveyService = SystemConfig.instance().getSurveyService();
        Map<String, Object> result = surveyService.getAllSurveyQuestions(courseId);

        if (result != null && result.isEmpty() == false) {
            model.addAttribute("questions", result.get("questions"));
            model.addAttribute("surveyId", result.get("surveyId"));
        }
        return "survey/surveyquestions";
    }

    @GetMapping(value = "/instructor/survey/addquestions")
    public String addQuestionsToSurvey
            (@RequestParam(name = "courseId") Long courseId,
             @RequestParam(name = "surveyId") Long surveyId,
             @RequestParam(name = "userId") Long userId,
             Model model) {
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("userId", userId);

        ISurveyService surveyService = SystemConfig.instance().getSurveyService();
        Map<String, Object> result = surveyService.addQuestionPage(courseId, surveyId);
        if (result != null && result.isEmpty() == false) {
            model.addAttribute("available", result.get("availableQuestions"));
            model.addAttribute("added", result.get("addedQuestion"));
        }

        return "survey/addquestions";
    }

    @PostMapping(value = "/instructor/survey/addquestions")
    public String addQuestions
            (@RequestParam(name = "courseId") Long courseId,
             @RequestParam(name = "surveyId") Long surveyId,
             @RequestParam(name = "userId") Long userId,
             @RequestParam(name = "questionId") Long questionId,
             Model model) {
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("userId", userId);

        ISurveyService surveyService = SystemConfig.instance().getSurveyService();
        surveyService.addQuestionToSurvey(surveyId, questionId);

        Map<String, Object> result = surveyService.addQuestionPage(courseId, surveyId);

        model.addAttribute("available", result.get("availableQuestions"));
        model.addAttribute("added", result.get("addedQuestion"));
        return "survey/addquestions";
    }

    @PostMapping(value = "/instructor/survey/deletequestion")
    public ModelAndView deleteQuestions
            (@RequestParam(name = "courseId") Long courseId,
             @RequestParam(name = "surveyId") Long surveyId,
             @RequestParam(name = "userId") Long userId,
             @RequestParam(name = "questionId") Long questionId,
             ModelMap model) {
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("userId", userId);
        ISurveyService surveyService = SystemConfig.instance().getSurveyService();

        surveyService.deleteQuestionFromSurvey(surveyId, questionId);
        return new ModelAndView("redirect:/instructor/survey/addquestions", model);
    }

    @GetMapping(value = "/instructor/survey/publish")
    public ModelAndView publishSurvey
            (@RequestParam(name = "courseId") Long courseId,
             @RequestParam(name = "surveyId") Long surveyId,
             @RequestParam(name = "userId") Long userId,
             ModelMap model,
             RedirectAttributes redirectAttributes) {
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("userId", userId);

        ISurveyService surveyService = SystemConfig.instance().getSurveyService();
        if (surveyService.publishSurvey(surveyId)) {
            redirectAttributes.addFlashAttribute("publishSuccess", true);
        }
        return new ModelAndView("redirect:/instructor/survey/", model);
    }

    @GetMapping(value = "/instructor/survey/unpublish")
    public ModelAndView unpublishSurvey
            (@RequestParam(name = "courseId") Long courseId,
             @RequestParam(name = "surveyId") Long surveyId,
             @RequestParam(name = "userId") Long userId,
             ModelMap model,
             RedirectAttributes redirectAttributes) {

        model.addAttribute("surveyId", surveyId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("userId", userId);

        ISurveyService surveyService = SystemConfig.instance().getSurveyService();
        if (surveyService.unpublishSurvey(surveyId)) {
            redirectAttributes.addFlashAttribute("unpublishSuccess", true);
        }
        return new ModelAndView("redirect:/instructor/survey/", model);
    }

    @GetMapping(value = "/student/survey/questions")
    public String displaySurveyQuestionToStudent
            (@RequestParam(name = "courseId") Long courseId,
             Model model) {
        ISurveyService surveyService = SystemConfig.instance().getSurveyService();
        Map<String, Object> result = surveyService.displaySurveyQuestionsToStudents(courseId,
                SystemConfig.instance().getSurveyPersistence());
        model.addAttribute("courseId", courseId);
        if (result.containsKey("isSurveyPublished")) {
            model.addAttribute("isSurveyPublished", result.get("isSurveyPublished"));
        }
        if (result.containsKey("survey") && result.containsKey("surveyId")) {

//            for (Question question: (List<Question>) result.get("questions")) {
//            }
            model.addAttribute("survey", (Survey) result.get("survey"));
            model.addAttribute("surveyId", result.get("surveyId"));
        }
        return "survey/displaySurveyToStudent";
    }

    @RequestMapping(value = "student/survey/submit")
    public String submitSurvey
            (@ModelAttribute Survey survey,
             @RequestParam(name = "surveyId") Long surveyId,
             @RequestParam(name = "bannerId") String bannerId) {
        ISurveyService surveyService = SystemConfig.instance().getSurveyService();
        surveyService.submitAnswers(bannerId,surveyId,survey,SystemConfig.instance().getSurveyPersistence());
        return "redirect:/";
    }
}

