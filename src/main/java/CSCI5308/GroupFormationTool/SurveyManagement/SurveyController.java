package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.Question.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
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

        ISurveyService surveyService = SurveyServiceAbstractFactory.instance().makeService();
        Map<String, Object> result = surveyService.getAllSurveyQuestions(courseId, SurveyPersistenceAbstractFactory.instance().makePersistence());

        if (result != null && result.isEmpty() == false) {
            model.addAttribute("questions", result.get("questions"));
            model.addAttribute("surveyId", result.get("surveyId"));
            model.addAttribute("status", result.get("status"));
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

        ISurveyService surveyService = SurveyServiceAbstractFactory.instance().makeService();
        Map<String, Object> result = surveyService.addQuestionPage(courseId, surveyId, SurveyPersistenceAbstractFactory.instance().makePersistence());
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

        ISurveyService surveyService = SurveyServiceAbstractFactory.instance().makeService();
        surveyService.addQuestionToSurvey(surveyId, questionId, SurveyPersistenceAbstractFactory.instance().makePersistence());

        Map<String, Object> result = surveyService.addQuestionPage(courseId, surveyId, SurveyPersistenceAbstractFactory.instance().makePersistence());

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
        ISurveyService surveyService = SurveyServiceAbstractFactory.instance().makeService();

        surveyService.deleteQuestionFromSurvey(surveyId, questionId, SurveyPersistenceAbstractFactory.instance().makePersistence());
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

        ISurveyService surveyService = SurveyServiceAbstractFactory.instance().makeService();
        if (surveyService.publishSurvey(surveyId, SurveyPersistenceAbstractFactory.instance().makePersistence())) {
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

        ISurveyService surveyService = SurveyServiceAbstractFactory.instance().makeService();
        if (surveyService.unpublishSurvey(surveyId, SurveyPersistenceAbstractFactory.instance().makePersistence())) {
            redirectAttributes.addFlashAttribute("unpublishSuccess", true);
        }
        return new ModelAndView("redirect:/instructor/survey/", model);
    }

    @GetMapping(value = "/student/survey/questions")
    public String displaySurveyQuestionToStudent
            (@RequestParam(name = "courseId") Long courseId,
             Model model) {
        ISurveyService surveyService = SurveyServiceAbstractFactory.instance().makeService();
        Map<String, Object> result = surveyService.displaySurveyQuestionsToStudents(courseId,
                SurveyPersistenceAbstractFactory.instance().makePersistence());
        model.addAttribute("courseId", courseId);
        if (result.containsKey("isSurveyPublished")) {
            model.addAttribute("isSurveyPublished", result.get("isSurveyPublished"));
        }
        if (result.containsKey("survey") && result.containsKey("surveyId")) {
            model.addAttribute("survey", result.get("survey"));
            model.addAttribute("surveyId", result.get("surveyId"));
        }
        return "survey/displaySurveyToStudent";
    }

    @RequestMapping(value = "student/survey/submit")
    public String submitSurvey
            (@ModelAttribute Survey survey,
             @RequestParam(name = "surveyId") Long surveyId,
             @RequestParam(name = "bannerId") String bannerId) {
        ISurveyService surveyService = SurveyServiceAbstractFactory.instance().makeService();
        surveyService.submitAnswers(bannerId, surveyId, survey, SurveyPersistenceAbstractFactory.instance().makePersistence());
        return "redirect:/";
    }

    @GetMapping("instructor/survey/creategroup")
    public String createGroup(@RequestParam(name = "courseId") Long courseId,
                              @RequestParam(name = "surveyId") Long surveyId,
                              Model model) {
        ISurveyService surveyService = SurveyServiceAbstractFactory.instance().makeService();
        Map<String, Object> result = surveyService.getAllSurveyQuestions(courseId, SurveyPersistenceAbstractFactory.instance().makePersistence());
        IQuestionCriteriaList questionCriteriaList = SurveyServiceAbstractFactory.instance().makeQuestionCriteriaListUsingList((List<Question>) result.get("questions"));
        model.addAttribute("questions", questionCriteriaList);
        model.addAttribute("surveyId", surveyId);
        return "survey/creategroups";
    }

    @PostMapping(value = "/survey/generategroups")
    public String generateGroups
            (@ModelAttribute QuestionCriteriaList questionCriteriaList,
             @RequestParam(name = "surveyId", required = false) Long surveyId,
             @RequestParam(name = "bannerId", required = false) String bannerId) {
        questionCriteriaList.list.forEach(x -> {
            System.out.println(x.getCriteriaType() + "  " + x.getCriteriaValue());
        });
        System.out.println(questionCriteriaList.getSurveyId() + " surveyid ");
        System.out.println(questionCriteriaList.getMembersPerGroup() + " memebrs per group  ");
        return "index";
    }
}

