package com.project.artisan.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.artisan.model.Role;
import com.project.artisan.model.User;
import com.project.artisan.repository.UserRepository;
import com.project.artisan.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	

	@Override
	public User save(UserRegistrationDto registrationDto) {
		
		User user = new User(registrationDto.getName(), registrationDto.getEmail(), registrationDto.getTelephone(),
				passwordEncoder.encode(registrationDto.getPassword()), registrationDto.getGender(), Arrays.asList(new Role ("ROLE_USER")));
		
		List<User> users=userRepository.findAll();
		int c=0;
		for(var i=0;i<users.size();i++) {
			if(users.get(i).getName()=="admin") {
				c++;
			}
			
		}
		if(c==0) {
			User admin = new User("admin", "admin.admin@gmail.com", "028747892086",
					passwordEncoder.encode("admin1234"), "Femme", Arrays.asList(new Role ("ROLE_ADMIN")));
			userRepository.save(admin);
		}
		return userRepository.save(user);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
	public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

	@Override
	public User getCurrentlyLoggedInUser(Authentication authentication) {
		if(authentication ==null) return null;
		User user=null;
		Object principal =authentication.getPrincipal();
		
		if (principal instanceof User) {
			String email =((User)principal).getEmail();
			user=findByEmail(email);
		}
		return user;
	}
}
