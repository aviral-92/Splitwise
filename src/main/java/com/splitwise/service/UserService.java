package com.splitwise.service;

import java.util.List;

import com.splitwise.exception.CustomException;
import com.splitwise.modal.User;

public interface UserService {

	public boolean addUser(User user) throws CustomException;

	public User getUser(String email);

	public boolean updateUser(User user);

	public boolean deleteUser(String email);

	List<User> getAllUsersByGroupID(String email) throws CustomException;
}
