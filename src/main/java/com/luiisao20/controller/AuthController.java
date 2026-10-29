package com.luiisao20.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Data
public class AuthController {

  @GetMapping("/")
  public String getLoginPage() {
    return "index";
  }

  @GetMapping("/post-login")
  public String getPostLoginPage(Authentication auth) {
    String role = auth.getAuthorities().stream()
        .map(grant -> grant.getAuthority())
        .findFirst()
        .orElse("");

    return switch (role) {
      case "ROLE_ADMIN" -> "redirect:/admin";
      default -> "home";
    };
  }

}
