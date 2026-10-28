package com.luiisao20.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import com.luiisao20.entity.User;

@Controller
@Data
public class AuthController {
  @GetMapping("/")
  public String getLoginPage() {
    return "index";
  }

  @GetMapping("/register")
  public String getRegisterPage(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @GetMapping("/home")
  public String getHomePage(Model model) {
    // // model.addAttribute("user", new User());
    return "home";
  }

  @GetMapping("/post-login")
  public String redirectByRole(Authentication auth) {
    String role = auth.getAuthorities().stream()
        .map(grantedAuthority -> grantedAuthority.getAuthority())
        .findFirst()
        .orElse("");
    return switch (role) {
      case "ROLE_USER" -> "redirect:/home";
      default -> "redirect:/users/admin";
    };
  }

}
