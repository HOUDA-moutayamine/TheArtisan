package com.project.artisan.service;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.project.artisan.model.User;
import com.project.artisan.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
	public List<User> findAll();
	public User findByEmail(String email);
	public User findById(Long id);
	public User getCurrentlyLoggedInUser(Authentication authentication);
}
