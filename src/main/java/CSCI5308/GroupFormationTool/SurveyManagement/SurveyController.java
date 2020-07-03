package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class SurveyController {

    @RequestMapping(value = "/instructor/survey")
    public String surveyManagementPage
            (@RequestParam(name = "userId") Long userId, @RequestParam(name = "courseId") Long courseId,
             Model model) {
        model.addAttribute("courseId", courseId);
        model.addAttribute("userId", userId);

        ISurveyService surveyService = SystemConfig.instance().getSurveyService();
        Map<String, Object> result = surveyService.getAllSurveyQuestions(courseId);

        if (result != null) {
            model.addAttribute("questions", result.get("questions"));
            model.addAttribute("surveyId", result.get("surveyId"));
        }
        return "survey/surveyquestions";
    }

    @GetMapping(value = "/instructor/survey/addquestions")
    public String addQuestionsToSurvey
            (@RequestParam(name = "courseId") Long courseId,
             @RequestParam(name = "surveyId") Long surveyId,
             Model model) {
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("courseId", courseId);

        ISurveyService surveyService = SystemConfig.instance().getSurveyService();
        Map<String, Object> result = surveyService.addQuestionPage(courseId, surveyId);

        model.addAttribute("available", result.get("availableQuestions"));
        model.addAttribute("added", result.get("addedQuestion"));

        return "survey/addquestions";
    }

    @PostMapping(value = "/instructor/survey/addquestions")
    public String addQuestions
            (@RequestParam(name = "courseId") Long courseId,
             @RequestParam(name = "surveyId") Long surveyId,
             @RequestParam(name = "questionId") Long questionId,
             Model model) {
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("courseId", courseId);

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
             @RequestParam(name = "questionId") Long questionId,
             ModelMap model) {
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("courseId", courseId);
        ISurveyService surveyService = SystemConfig.instance().getSurveyService();

        if (surveyService.deleteQuestionFromSurvey(surveyId, questionId)) {
            return new ModelAndView("redirect:/instructor/survey/addquestions", model);
        }
        return null;
    }

    @RequestMapping(value = "/instructor/survey/publish")
    public String publishSurvey
            (@RequestParam(name = "userId") Long userId,
             Model model) {
        ////////////////////////////////////
        model.addAttribute("published", true);
        return "survey/surveyquestions";
    }
}
