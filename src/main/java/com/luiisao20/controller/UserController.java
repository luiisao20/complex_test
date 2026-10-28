package com.luiisao20.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.luiisao20.entity.User;
import com.luiisao20.services.UserService;

import jakarta.validation.Valid;
import lombok.Data;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@Data
public class UserController {
  private final UserService userService;

  @PostMapping("/create")
  public String registerUser(@Valid @ModelAttribute("user") User user,
      BindingResult result,
      Model model) {
    if (result.hasErrors()) {
      return "register";
    }
    userService.create(user);
    return "redirect:/?success";
  }

  @GetMapping("/edit/{id}")
  public String getEditPage(@PathVariable UUID id, Model model) {
    model.addAttribute("user", userService.getUserById(id));
    return "pages/editUser";
  }

  @PostMapping("/update")
  public String updateUser(@Valid @ModelAttribute User user, BindingResult results, Model model) {
    if (results.hasErrors()) {
      return "pages/editUser";
    }
    userService.updateUser(user.getId(), user);
    return "redirect:/users/admin";

  }

  @GetMapping("/admin")
  public String getAdminPage(Model model) {
    model.addAttribute("users", userService.getUsers());
    return "admin";
  }

  @PostMapping("/delete/{id}")
  public String postMethodName(@PathVariable UUID id) {
    userService.deleteUser(id);
    return "redirect:/users/admin";
  }

}
