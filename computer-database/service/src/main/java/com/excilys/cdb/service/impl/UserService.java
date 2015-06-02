package com.excilys.cdb.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.dao.impl.UserDao;
import com.excilys.cdb.model.Authority;
import com.excilys.cdb.model.User;
import com.excilys.cdb.service.IUserService;

@Service("serviceUser")
@Transactional
public class UserService implements IUserService, UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = true)
	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userDao.findByUserName(username);
		List<GrantedAuthority> authorities = buildUserAuthority(user
				.getUserRole());
		return buildUserForAuthentication(user, authorities);
	}

	private org.springframework.security.core.userdetails.User buildUserForAuthentication(
			User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), user.isEnabled(), true,
				true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<Authority> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (Authority userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getAuthority()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(
				setAuths);

		return Result;
	}
}
