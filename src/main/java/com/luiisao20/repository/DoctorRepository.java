package com.luiisao20.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luiisao20.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
