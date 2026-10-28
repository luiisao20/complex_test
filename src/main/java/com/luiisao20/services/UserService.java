package com.luiisao20.services;

import java.util.List;
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

  public User create(User user) {
    user.setRole(Role.ROLE_USER);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public User getUserById(UUID id) {
    return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
  }

  public User updateUser(UUID id, User user) {
    User oldUser = getUserById(id);

    oldUser.setEmail(user.getEmail());
    oldUser.setFirstName(user.getFirstName());
    oldUser.setLastName(user.getLastName());
    oldUser.setRole(user.getRole());

    return userRepository.save(oldUser);
  }

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public void deleteUser(UUID id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));

    userRepository.delete(user);
  }

  @Override
  public UserDetails loadUserByUsername(String email) {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

    return org.springframework.security.core.userdetails.User.builder()
        .password(user.getPassword())
        .username(user.getEmail())
        .authorities(user.getRole().name())
        .build();
  }
}
