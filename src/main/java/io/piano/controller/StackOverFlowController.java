package io.piano.controller;

import io.piano.service.QuestionService;
import io.piano.service.result.QuestionServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by maratgatin on 13/03/2019.
 */
@Controller
public class StackOverFlowController {

    private QuestionService questionService;
    //http://api.stackexchange.com/docs/search#order=desc&sort=activity&intitle=java&filter=default&site=stackoverflow&run=true


    @Autowired
    public StackOverFlowController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public String search(@RequestParam(required = false) String inTitle, @RequestParam(defaultValue = "1") int page, Model model) {
        if (inTitle != null) {
            QuestionServiceResult result = questionService.getQuestionsInTitle(inTitle, page);
            model.addAttribute("questions", result.getQuestions());
            model.addAttribute("hasMore", result.hasMore());
            model.addAttribute("page", page);
            model.addAttribute("inTitle", inTitle);
        }
        return "index";
    }
}
