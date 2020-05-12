package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by kanet on 15.01.2020.
 */
@Controller
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @RequestMapping(value="/admin/user", method = RequestMethod.GET)
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        List<User> userList = userRepository.findAll();
        modelAndView.addObject("allUsers", userList);
        modelAndView.setViewName("admin/users");
        return modelAndView;
    }

    @RequestMapping(value="/admin/user/update", method = RequestMethod.GET)
    public ModelAndView update(@RequestParam int id){
        ModelAndView modelAndView = new ModelAndView();
        User user = userRepository.findById(id);

        modelAndView.addObject("user", user);
        modelAndView.setViewName("admin/userUpdate");
        return modelAndView;
    }

    @RequestMapping(value="/admin/user/update", method = RequestMethod.POST)
    public ModelAndView updateUser(@RequestParam int id, @Valid User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();

        User updatedUser = userRepository.findById(id);
        updatedUser.setName(user.getName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());

        userRepository.save(updatedUser);
        modelAndView.addObject("user", user);
        modelAndView.addObject("message", "Foydalanuvchi o'zgartirildi!");
        modelAndView.setViewName("admin/userUpdate");

        return modelAndView;
    }
}
