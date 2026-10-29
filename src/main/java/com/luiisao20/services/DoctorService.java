package com.luiisao20.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.luiisao20.entity.Doctor;
import com.luiisao20.repository.DoctorRepository;

import lombok.Data;

@Service
@Data
public class DoctorService {
  private final DoctorRepository doctorRepository;

  public Doctor registerDoctor(Doctor doctor) {
    return doctorRepository.save(doctor);
  }

  public Doctor getDoctorById(Long id) {
    return doctorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Doctor not found"));
  }

  public void deleteUser(Long id) {
    Doctor doctor = doctorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Doctor not found"));

    doctorRepository.delete(doctor);
  }

  public Doctor updateDoctor(Long id, Doctor doctor) {
    Doctor oldDoctor = doctorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Doctor not found"));

    oldDoctor.setFirstName(doctor.getFirstName());
    oldDoctor.setLastName(doctor.getLastName());
    oldDoctor.setEmail(doctor.getEmail());
    oldDoctor.setGender(doctor.getGender());
    oldDoctor.setDegree(doctor.getDegree());

    return doctorRepository.save(oldDoctor);
  }

  public List<Doctor> getDoctors() {
    return doctorRepository.findAll();
  }
}
