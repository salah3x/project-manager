package com.salah.projectmanager.web;

import com.salah.projectmanager.domain.ManagerRequest;
import com.salah.projectmanager.service.AdminService;
import com.salah.projectmanager.service.CollaboratorService;
import com.salah.projectmanager.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by bnadem on 6/12/17.
 */
@Controller
public class ProfileController {

    @Autowired
    private CollaboratorService collaboratorService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private GuestService guestService;

    @RequestMapping(value = "/profile")
    public String show(Model model, Principal principal) {
        model.addAttribute("title", "Profile | "+principal.getName());
        model.addAttribute("user", guestService.signIn(principal.getName()));
        model.addAttribute("requests", adminService.getRequests());
        model.addAttribute("messages", adminService.getMessages());
        model.addAttribute("req", new ManagerRequest());
        return "profile/index";
    }

    @RequestMapping(value = "/profile/{username}")
    public String show(Model model,@PathVariable String username, Principal principal) {
        if (username.equals(principal.getName())) {
            return "redirect:/profile";
        }
        model.addAttribute("title", "Profile | "+principal.getName());
        model.addAttribute("user", guestService.signIn(username));
        model.addAttribute("visitor", true);
        return "profile/index";
    }

    @RequestMapping(value = "/managerReq/{id}/accept")
    public String accept(@PathVariable String id){
        adminService.acceptRequest(Integer.parseInt(id));
        return "redirect:/profile";
    }

    @RequestMapping(value = "/managerReq/send", method = RequestMethod.POST)
    public String send(@ModelAttribute @Valid ManagerRequest request, Principal principal, Errors errors){
        if (errors.hasErrors()) {
            return "redirect:/profile?reqError";
        }
        collaboratorService.sendManagerRequest(request, principal.getName());
        return "redirect:/profile?reqSuccess";
    }
}
