package br.com.slotshop.storeclient.controller;

import br.com.slotshop.server.model.User;
import br.com.slotshop.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired private UserService userService;

    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/newUser")
    public @ResponseBody Object getRegister(@Valid @RequestBody User user) {
        if(userService.findByEmail(user.getEmail()) == null) {
            user.setRole("CLIENT");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userService.save(user);
        }
        throw new Error("E-mail informado já esta sendo utilizado!");
    }

}
