package com.salah.projectmanager.web;

import com.salah.projectmanager.domain.User;
import com.salah.projectmanager.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by bnadem on 5/28/17.
 */
@Controller
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private GuestService guestService;

    @RequestMapping(value = "signin", method = RequestMethod.GET)
    public String signinGet(Model model) {
        model.addAttribute("title", "Sign in");
        return "signin";
    }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signupGet(Model model) {
        model.addAttribute("title", "Sign up");
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signupPost(Model model, @ModelAttribute @Valid User user, Errors errors) {
        if(errors.hasErrors()){
            model.addAttribute("title", "Sign up");
            model.addAttribute("error", "error");
            return "signup";
        }
        if(guestService.signIn(user.getUsername()) != null) {
            model.addAttribute("double", "Username already exist");
            return "signup";
        }
        user.setAvatar("img_avatar2.png");
        user.setCryptPassword(passwordEncoder.encode(user.getPassword()));
        guestService.signUp(user);
        return "redirect:/signup?success";
    }
}
