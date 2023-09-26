package com.jmurienega.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.jmurienega.demo.domain.entity.User;
import com.jmurienega.demo.domain.repository.UserRepository;
import com.jmurienega.demo.exceptionhandling.CustomUserException;
import com.jmurienega.demo.model.UserLoginResponse;
import com.jmurienega.demo.model.UserSignUpRequest;
import com.jmurienega.demo.model.UserSignUpResponse;
import com.jmurienega.demo.security.JwtUtils;

@Service
@Validated
public class UserService {
	@Autowired	
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtUtils jwtUtils;
	
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

	public Optional<User> getUserById(Long accountId) {
		return userRepository.findById(accountId);
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public UserSignUpResponse createUser(@Valid UserSignUpRequest newUser) throws CustomUserException {
		User user = this.modelMapper.map(newUser, User.class);
		user.setPassword(encoder.encode(newUser.getPassword()));
		user.setCreated(LocalDateTime.now());
		user.setLastLogin(LocalDateTime.now());
		user.setActive(true);
		this.userRepository.save(user);
		UserSignUpResponse userCreated = this.modelMapper.map(user, UserSignUpResponse.class);
		if (userCreated == null)
			throw new CustomUserException(2,"Error al crear el usuario");
		userCreated.setToken(jwtUtils.generateToken(newUser.getEmail()));
		return userCreated;
	}

	public UserLoginResponse login(String token) throws CustomUserException {
		String userName = jwtUtils.getUserEmailFromJWT(token);
		User user = this.userRepository.findByEmail(userName);
		
		if (user == null) {
			
			throw new CustomUserException(1,"El usuario no existe");
		}
		
		user.setLastLogin(LocalDateTime.now());
		this.userRepository.save(user);
		UserLoginResponse response = this.modelMapper.map(user, UserLoginResponse.class);
		response.setToken(jwtUtils.generateToken(user.getEmail()));
		return  response;
	
	}
}
