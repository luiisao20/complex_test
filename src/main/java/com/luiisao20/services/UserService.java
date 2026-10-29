package com.luiisao20.services;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.luiisao20.entity.User;
import com.luiisao20.repository.UserRepository;
import com.luiisao20.types.Role;

import lombok.Data;

@Service
@Data
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public User registerUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole(Role.ROLE_USER);
    return userRepository.save(user);
  }

  public User getUserById(UUID id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  public void deleteUser(UUID id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));

    userRepository.delete(user);
  }

  public User updateUser(UUID id, User user) {
    User oldUser = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));

    oldUser.setFirstName(user.getFirstName());
    oldUser.setLastName(user.getLastName());
    oldUser.setEmail(user.getEmail());
    oldUser.setRole(user.getRole());
    oldUser.setGender(user.getGender());

    return userRepository.save(oldUser);
  }

  @Override
  public UserDetails loadUserByUsername(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));

    return org.springframework.security.core.userdetails.User.builder()
        .username(user.getEmail())
        .password(user.getPassword())
        .authorities(user.getRole().name())
        .build();
  }

}
