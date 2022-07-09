package com.suminjin.simpleroom.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.suminjin.simpleroom.model.Role;
import com.suminjin.simpleroom.model.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
	private final User user;

	private final static String ROLE_PREFIX = "ROLE_";

	@Override
	public String getUsername() {
		return user.getUserID();
	}

	@Override
	public String getPassword() {
		return user.getUserPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Role role = user.getRole();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(ROLE_PREFIX + role);
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

}
