package com.suminjin.simpleroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suminjin.simpleroom.model.User;
import com.suminjin.simpleroom.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User findUser(User user) {
		return userRepository.findByUserID(user.getUserID()).orElse(null);
	}

}
