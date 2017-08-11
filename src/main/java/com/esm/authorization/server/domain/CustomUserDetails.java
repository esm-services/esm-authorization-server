package com.esm.authorization.server.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	private String password;

	private String username;

	private boolean enabled;

	private static final long serialVersionUID = 1L;

	private Collection<? extends GrantedAuthority> authorities;

	public CustomUserDetails(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.enabled = user.isEnabled();
		this.authorities = translate(user.getRoles());
	}

	private Collection<? extends GrantedAuthority> translate(Set<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			String name = role.getRole().toUpperCase();
			if (!name.startsWith("ROLE_")) {
				name = "ROLE_" + name;
			}
			authorities.add(new SimpleGrantedAuthority(name));
		}
		return authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
