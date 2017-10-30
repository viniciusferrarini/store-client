package br.com.slotshop.storeclient.controller;

import br.com.slotshop.server.enumeration.YesNo;
import br.com.slotshop.server.model.ForgotPassword;
import br.com.slotshop.server.model.User;
import br.com.slotshop.server.service.ForgotPasswordService;
import br.com.slotshop.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("forgotPassword")
public class ForgotPasswordController {

    @Autowired private ForgotPasswordService forgotPasswordService;

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired private UserService userService;

    @GetMapping
    public ModelAndView get(@RequestParam("token") String token){
        ModelAndView modelAndView = new ModelAndView("/forgotPassword");
        ForgotPassword forgotPassword = forgotPasswordService.findByTokenIsValid(token);
        if (forgotPassword != null){
            modelAndView.addObject("forgotPassword", token);
            return modelAndView;
        }
        return modelAndView.addObject("forgotPassword", "false");
    }

    @GetMapping("/changePassword")
    private @ResponseBody ResponseEntity changePassword(@RequestParam("password") String password, @RequestParam("token") String token){
        ForgotPassword forgotPassword = forgotPasswordService.findByTokenIsValid(token);
        if (forgotPassword != null){
            forgotPassword.setIsValid(YesNo.N);
            forgotPasswordService.save(forgotPassword);
            User user = forgotPassword.getUser();
            user.setPassword(passwordEncoder.encode(password));
            userService.save(user);
            return ResponseEntity.ok("Senha Alterada com Sucesso!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao alterar senha!");
    }
}
