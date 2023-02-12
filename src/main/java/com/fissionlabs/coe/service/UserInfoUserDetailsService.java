package com.fissionlabs.coe.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fissionlabs.coe.entity.UserInfo;
import com.fissionlabs.coe.entity.UserInfoUserDetails;
import com.fissionlabs.coe.repository.UserInfoRepository;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {

	 @Autowired
	private UserInfoRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo= repository.findByName(username);
		
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
		
	}

}
