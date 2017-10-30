package br.com.slotshop.storeclient.controller;

import br.com.slotshop.server.model.User;
import br.com.slotshop.server.service.ForgotPasswordService;
import br.com.slotshop.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired private UserService userService;

    @Autowired private ForgotPasswordService forgotPasswordService;

    @GetMapping
    public ModelAndView get(){
        return new ModelAndView("/login");
    }

    @GetMapping("/forgotPassword")
    public @ResponseBody Boolean forgotPassword(@RequestParam("email") String email){
        User user = userService.findByEmail(email);
        if(user != null) {
            return forgotPasswordService.createTokenForUser(user);
        }
        return false;
    }
}
