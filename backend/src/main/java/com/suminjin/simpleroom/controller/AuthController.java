package com.suminjin.simpleroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suminjin.simpleroom.model.User;
import com.suminjin.simpleroom.service.AuthService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/auth")
@Log4j2
public class AuthController {
	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public String loginProcess(@RequestBody User user) {
		return authService.loginProcess(user);
	}

	@PostMapping("/register")
	public void registerProcess(@RequestBody User user) {
		try {
			authService.registerProcess(user);
		} catch (Exception e) {
			log.error(e);
		}
	}

}
