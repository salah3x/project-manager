package com.salah.projectmanager.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by bnadem on 5/28/17.
 */
@Controller
@RequestMapping(value = "/")
public class UserController {

    @RequestMapping(value = "signin", method = RequestMethod.GET)
    public String signinGet(Model model) {
        model.addAttribute("title", "Sign in");
        return "signin";
    }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signupGet(Model model) {
        model.addAttribute("title", "Sign up");
        return "signup";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signupPost(Model model) {
        model.addAttribute("title", "Sign up");

        return "redirect:/";
    }
}
