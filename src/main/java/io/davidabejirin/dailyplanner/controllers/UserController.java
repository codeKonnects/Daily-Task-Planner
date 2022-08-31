package io.davidabejirin.dailyplanner.controllers;

import io.davidabejirin.dailyplanner.dto.UserDto;
import io.davidabejirin.dailyplanner.entity.User;
import io.davidabejirin.dailyplanner.exception.SignUpAndLoginException;
import io.davidabejirin.dailyplanner.service.serviceImpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
@Slf4j
@Controller
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping({"/login", "/"} )
    public ModelAndView getLoginForm(){
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new User());
        return mav;
    }
    @GetMapping("/signup")
    public String  signup() {
        return "register";
    }
    @PostMapping("/signup")
    public ModelAndView createAccount(@ModelAttribute UserDto user) throws SignUpAndLoginException {
        User signedUpUser = userService.createUser(user);

        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", signedUpUser);

        User oauthUser = userService.login(user.getEmail(), user.getPassword());

        if(Objects.nonNull(oauthUser)) {
            return mav;
        } else {
            return new ModelAndView("register");
        }
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String password, @RequestParam String email, @ModelAttribute("user") User user) throws SignUpAndLoginException {
        User oauthUser = userService.login(user.getEmail(), user.getPassword());

        if (oauthUser != null) {
            if (oauthUser.getEmail().equals(email) && oauthUser.getPassword().equals(password)) {
                ModelAndView mav = new ModelAndView("index");
                mav.addObject("user", oauthUser);
                mav.addObject("email", user.getEmail());
                return mav;
            }

            ModelAndView mav1 = new ModelAndView("index");
            mav1.addObject("user", oauthUser);
            mav1.addObject("email", user.getEmail());
            return mav1;
        } else {
            return new ModelAndView("register");
        }
    }


}

