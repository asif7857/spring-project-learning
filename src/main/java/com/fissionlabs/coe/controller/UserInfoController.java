package com.fissionlabs.coe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fissionlabs.coe.entity.UserInfo;
import com.fissionlabs.coe.service.UserInfoService;

@RestController
@RequestMapping("/api/v1")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	@PostMapping("/add")
	public String addNewUString(@RequestBody UserInfo userInfo)
	{
            return userInfoService.addUser(userInfo);		
	}
	
	
	
}
