package com.luiisao20.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luiisao20.entity.Doctor;
import com.luiisao20.services.DoctorService;

import jakarta.validation.Valid;
import lombok.Data;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/doctors")
@Data
public class DoctorController {
  private final DoctorService doctorService;

  @GetMapping("/list")
  public String getListPage(Model model) {
    model.addAttribute("doctors", doctorService.getDoctors());
    return "doctors-list";
  }

  @PostMapping("/save")
  public String saveDoctor(@Valid @ModelAttribute Doctor doctor, BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "admin";
    }
    doctorService.registerDoctor(doctor);

    return "redirect:/doctors/list";
  }

  @PostMapping("/delete/{id}")
  public String deleteDoctor(@PathVariable Long id) {
    doctorService.deleteUser(id);
    return "redirect:/doctors/list";
  }

  @GetMapping("/edit/{id}")
  public String getEditPage(@PathVariable Long id, Model model) {
    model.addAttribute("doctor", doctorService.getDoctorById(id));
    model.addAttribute("edit", true);
    return "admin";
  }

  @PostMapping("/update")
  public String updateDoctor(@Valid @ModelAttribute Doctor doctor, BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "admin";
    }
    doctorService.updateDoctor(doctor.getId(), doctor);

    return "redirect:/doctors/list";
  }

}
