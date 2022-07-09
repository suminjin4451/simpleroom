package com.suminjin.simpleroom.service;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import com.suminjin.simpleroom.model.Role;
import com.suminjin.simpleroom.model.User;
import com.suminjin.simpleroom.repository.UserRepository;
import com.suminjin.simpleroom.security.JWTProvider;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JWTProvider jwtProvider;

	@ExceptionHandler(AccountNotFoundException.class)
	public String controllerExceptionHandler(Exception e) {
		log.error(e.getMessage());
		return "/error/404";
	}

	// Validate User and Return JWT
	public String loginProcess(User user) {
		User findUser = userRepository.findByUserID(user.getUserID()).orElse(null);

		if ((findUser != null) && (findUser.getUserPassword().equals(user.getUserPassword()))) {
			return jwtProvider.generateJwt(findUser);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not Correct ID");
		}
	}

	// Register User
	public void registerProcess(User user) {
		user.setRole(Role.USER);

		User findUser = userRepository.findByUserID(user.getUserID()).orElse(null);

		if (findUser == null) {
			userRepository.save(user);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Duplicated ID");
		}

	}

}
