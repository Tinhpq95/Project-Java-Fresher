package com.finalproject.service;

import com.finalproject.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
