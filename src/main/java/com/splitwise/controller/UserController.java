package com.splitwise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitwise.exception.CustomException;
import com.splitwise.modal.User;
import com.splitwise.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/addUser", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Object> addUser(@RequestBody User user) {

		boolean added = false;
		try {
			added = userService.addUser(user);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return added ? new ResponseEntity<Object>("Successfully added new User.", HttpStatus.CREATED)
				: new ResponseEntity<Object>("Unable to add new User.", HttpStatus.I_AM_A_TEAPOT);
	}

	@RequestMapping(value = "/user/getUserByEmail", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> getUser(@RequestParam String email) {

		User response = userService.getUser(email);
		return response != null ? new ResponseEntity<Object>(response, HttpStatus.OK)
				: new ResponseEntity<Object>("Unable to find the User", HttpStatus.I_AM_A_TEAPOT);
	}

	@RequestMapping(value = "/user/getAllUsersWithinGroup", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> getAllUsersWithinGroup(@RequestParam String email) {

		List<User> response = null;
		try {
			response = userService.getAllUsersByGroupID(email);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return (response != null && !response.isEmpty()) ? new ResponseEntity<Object>(response, HttpStatus.OK)
				: new ResponseEntity<Object>("Unable to find the User", HttpStatus.I_AM_A_TEAPOT);
	}

	@RequestMapping(value = "/user/updateUser", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> updateUser(@RequestBody User user) {

		boolean response = userService.updateUser(user);
		return response ? new ResponseEntity<Object>("Successfully updated the USER.", HttpStatus.OK)
				: new ResponseEntity<Object>("Unable to update the User", HttpStatus.I_AM_A_TEAPOT);
	}

}