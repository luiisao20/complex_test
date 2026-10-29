package com.luiisao20.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.luiisao20.entity.User;
import com.luiisao20.services.UserService;

import jakarta.validation.Valid;
import lombok.Data;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Data
public class UserController {
  private final UserService userService;

  @GetMapping("/register")
  public String getRegisterPage(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping("/register-user")
  public String registerUser(
      @Valid @ModelAttribute User user,
      BindingResult results,
      Model model) {
    if (results.hasErrors()) {
      return "register";
    }

    userService.registerUser(user);
    return "redirect:/?success";
  }

}
