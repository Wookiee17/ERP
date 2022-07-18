package com.cloven.dev.erpdev.core.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cloven.dev.erodev.model.entity.User;
import com.cloven.dev.erpdev.core.utils.BeanUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserSession implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 246057179390227694L;
	private String userName;
	private String name;
	private List<GrantedAuthority> authorities;


	public static UserSession create(User user) {

		ApplicationContext context = BeanUtil.getAppContext();
		List<GrantedAuthority> authorities = user.getAuthorities();

		return new UserSession(user.getUsername(), user.getUsername(),authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
