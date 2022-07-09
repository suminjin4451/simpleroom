package com.suminjin.simpleroom.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.suminjin.simpleroom.model.User;
import com.suminjin.simpleroom.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userID) throws UsernameNotFoundException {
		User user = userRepository.findByUserID(userID).orElse(null);
		return new UserDetailsImpl(user);
	}

}
