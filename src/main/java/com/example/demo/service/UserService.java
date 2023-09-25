package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Phone;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.exceptionhandling.CustomUserException;
import com.example.demo.security.JwtUtils;
import com.test.test.dto.UserLoginResponse;
import com.test.test.dto.UserSignUpRequest;
import com.test.test.dto.UserSignUpResponse;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtUtils jwtUtils;
	


	public Optional<User> getUserById(Long accountId) {
		return userRepository.findById(accountId);
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public UserSignUpResponse createUser(UserSignUpRequest newUser) throws CustomUserException {
		User user = this.modelMapper.map(newUser, User.class);
		//newUser.getPhones().stream().map(dto -> this.modelMapper.map(dto, Phone.class))
        //.forEach(user.getPhones()::add);
		user.setPassword(encoder.encode(newUser.getPassword()));
		user.setCreated(LocalDateTime.now());
		user.setLastLogin(LocalDateTime.now());
		user.setActive(true);
		this.userRepository.save(user);
		UserSignUpResponse userCreated = this.modelMapper.map(user, UserSignUpResponse.class);
		if (userCreated == null)
			throw new CustomUserException(2,"Error al crear el usuario");
		userCreated.setToken(jwtUtils.generateToken(newUser.getName()));
		return userCreated;
	}

	public UserLoginResponse login(String token) throws CustomUserException {
		String userName = jwtUtils.getUserUsernameFromJWT(token);
		User user = this.userRepository.findByName(userName);
		
		if (user == null) {
			
			throw new CustomUserException(1,"El usuario no existe");
		}
		
		user.setLastLogin(LocalDateTime.now());
		this.userRepository.save(user);
		UserLoginResponse response = this.modelMapper.map(user, UserLoginResponse.class);
		response.setToken(jwtUtils.generateToken(userName));
		return  response;
	
	}
}
