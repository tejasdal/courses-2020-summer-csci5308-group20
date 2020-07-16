package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Question.Answers;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class SurveyController {

    private Logger log = LoggerFactory.getLogger(SurveyController.class);

    @RequestMapping(value = "/instructor/survey")
    public String surveyManagementPage
            (@RequestParam(name = "userId") Long userId,
             @RequestParam(name = "courseId") Long courseId,
             Model model) {
        log.info("Processing a request to load a survey management page to instructor with ID: {} for a course with ID: {}",
                userId, courseId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("userId", userId);

        ISurveyService surveyService = ServiceAbstractFactory.instance().makeService();
        Map<String, Object> result = surveyService.getAllSurveyQuestions(courseId, PersistenceAbstractFactory.instance().makePersistence());

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
        log.info("Processing a request to load a page to add question to a survey with ID: {} by instructor with ID: {} " +
                "for a course with ID: {}",surveyId, userId, courseId);
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("userId", userId);

        ISurveyService surveyService = ServiceAbstractFactory.instance().makeService();
        Map<String, Object> result = surveyService.addQuestionPage(courseId, surveyId, PersistenceAbstractFactory.instance().makePersistence());
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
        log.info("Processing a request to add question to a survey with ID: {} by instructor with ID: {} for a course with" +
                " ID: {}",surveyId, userId, courseId);
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("userId", userId);

        ISurveyService surveyService = ServiceAbstractFactory.instance().makeService();
        surveyService.addQuestionToSurvey(surveyId, questionId, PersistenceAbstractFactory.instance().makePersistence());

        Map<String, Object> result = surveyService.addQuestionPage(courseId, surveyId, PersistenceAbstractFactory.instance().makePersistence());

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
        log.info("Processing a request to delete question with ID: {} of a survey with ID: {} by instructor with ID: {} " +
                "for a course with ID: {}", questionId, surveyId, userId, courseId);
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("userId", userId);
        ISurveyService surveyService = ServiceAbstractFactory.instance().makeService();

        surveyService.deleteQuestionFromSurvey(surveyId, questionId, PersistenceAbstractFactory.instance().makePersistence());
        return new ModelAndView("redirect:/instructor/survey/addquestions", model);
    }

    @GetMapping(value = "/instructor/survey/publish")
    public ModelAndView publishSurvey
            (@RequestParam(name = "courseId") Long courseId,
             @RequestParam(name = "surveyId") Long surveyId,
             @RequestParam(name = "userId") Long userId,
             ModelMap model,
             RedirectAttributes redirectAttributes) {
        log.info("Processing a request to publish a survey with ID: {} by instructor with ID: {} for a course with" +
                " ID: {}",surveyId, userId, courseId);
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("userId", userId);

        ISurveyService surveyService = ServiceAbstractFactory.instance().makeService();
        if (surveyService.publishSurvey(surveyId, PersistenceAbstractFactory.instance().makePersistence())) {
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
        log.info("Processing a request to unpublish a survey with ID: {} by instructor with ID: {} for a course with" +
                " ID: {}",surveyId, userId, courseId);
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("userId", userId);

        ISurveyService surveyService = ServiceAbstractFactory.instance().makeService();
        if (surveyService.unpublishSurvey(surveyId, PersistenceAbstractFactory.instance().makePersistence())) {
            redirectAttributes.addFlashAttribute("unpublishSuccess", true);
        }
        return new ModelAndView("redirect:/instructor/survey/", model);
    }

    @GetMapping(value = "/student/survey/questions")
    public String displaySurveyQuestionToStudent
            (@RequestParam(name = "courseId") Long courseId,
             Model model) {
        log.info("Processing a request to display all survey questions to student for a course with ID: {}", courseId);
        ISurveyService surveyService = ServiceAbstractFactory.instance().makeService();
        Map<String, Object> result = surveyService.displaySurveyQuestionsToStudents(courseId,
                PersistenceAbstractFactory.instance().makePersistence());
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
        log.info("Processing a request to submit response of a survey with ID: {} by a student with banner ID: {}", surveyId, bannerId);
        ISurveyService surveyService = ServiceAbstractFactory.instance().makeService();
        surveyService.submitAnswers(bannerId, surveyId, survey, PersistenceAbstractFactory.instance().makePersistence());
        return "redirect:/";
    }
}

