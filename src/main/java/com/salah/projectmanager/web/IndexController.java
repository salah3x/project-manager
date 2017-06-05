package com.salah.projectmanager.web;

import com.salah.projectmanager.domain.Message;
import com.salah.projectmanager.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

/**
 * Created by bnadem on 5/21/17.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private GuestService guestService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, Principal principal) {
        model.addAttribute("title", "Project Manager");
        return "index";
    }

    @RequestMapping(value = "search", method = {RequestMethod.GET, RequestMethod.POST})
    public String search(@RequestParam String p, Model model) {
        model.addAttribute("title", "Search");
        model.addAttribute("keyword", "You searched for: " + p);
        model.addAttribute("projects", guestService.search(p));
        return "search";
    }

    @RequestMapping(value = "wiki", method = RequestMethod.GET)
    public String wiki(Model model) {
        model.addAttribute("title", "Wiki");
        return "wiki";
    }

    @RequestMapping(value = "message", method = RequestMethod.GET)
    public String message(Model model, @RequestParam String email, @RequestParam String msg) {
        Message m= new Message();
        m.setMessage(msg);
        m.setSender(email);
        System.out.println(m);
        guestService.sendMessage(m);
        return "redirect:/?success";
    }

}
