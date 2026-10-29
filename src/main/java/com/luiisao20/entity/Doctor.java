package com.luiisao20.entity;

import java.time.OffsetDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.luiisao20.types.Gender;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Nombres requeridos")
  private String firstName;

  @NotBlank(message = "Apellidos requeridos")
  private String lastName;

  @NotBlank(message = "Título requeridos")
  private String degree;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  @NotBlank(message = "Correo requerido")
  @Email(message = "Correo no válido")
  @Column(unique = true)
  private String email;

  @CreationTimestamp
  @Column(columnDefinition = "timestamptz")
  private OffsetDateTime createdAt;

  @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Appointment> appointments;
}
