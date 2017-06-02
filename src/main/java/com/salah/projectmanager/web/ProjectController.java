package com.salah.projectmanager.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by bnadem on 5/20/17.
 */
@Controller
@RequestMapping(value = "projects")
public class ProjectController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("title", "Projects");
        return "projects/index";
    }
}
