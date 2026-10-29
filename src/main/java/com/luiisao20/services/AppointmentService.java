package com.luiisao20.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.luiisao20.entity.Appointment;
import com.luiisao20.repository.AppointmentRepository;

import lombok.Data;

@Service
@Data
public class AppointmentService {
  private final AppointmentRepository appointmentRepository;

  public Appointment create(Appointment appointment) {
    return appointmentRepository.save(appointment);
  }

  public List<Appointment> getByUser(String email) {
    return appointmentRepository.findByUserEmailOrderByCreatedAtDesc(email);
  }

  public List<Appointment> getByDoctor(Long id) {
    return appointmentRepository.findByDoctorId(id);
  }

  public Appointment update(Long id, Appointment appointment) {
    Appointment oldAppointment = appointmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Appointment not found"));
    
    oldAppointment.setBuilding(appointment.getBuilding());
    oldAppointment.setRoom(appointment.getRoom());
    oldAppointment.setDate(appointment.getDate());
    oldAppointment.setStatus(appointment.getStatus());

    return appointmentRepository.save(oldAppointment);
  }

  public void delete(Long id) {
    Appointment appointment = appointmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Appointment not found"));
    appointmentRepository.delete(appointment);
  }

}
