package com.exercise.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TournamentPageController {

    @GetMapping(value = { ""})
    public ModelAndView preLoginPages() throws Exception {
        System.out.println("yessss");
        ModelAndView model = new ModelAndView("index.html");
        return model;
    }
}
