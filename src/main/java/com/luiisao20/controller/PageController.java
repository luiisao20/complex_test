package com.luiisao20.controller;

import com.luiisao20.entity.Doctor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

  @GetMapping("/admin")
  public String getAdminPage(Model model) {
    model.addAttribute("doctor", new Doctor());
    return "admin";
  }

  @GetMapping("/home")
  public String getHomePage() {
    return "home";
  }

}
