package com.luiisao20.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luiisao20.entity.Appointment;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
  List<Appointment> findByUserEmail(String email);

  List<Appointment> findByDoctorId(Long id);

  List<Appointment> findByUserEmailOrderByCreatedAtDesc(String email);
}
