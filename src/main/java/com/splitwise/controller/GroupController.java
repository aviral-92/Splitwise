package com.splitwise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitwise.modal.Group;
import com.splitwise.service.GroupService;

@RestController
public class GroupController {

	@Autowired
	private GroupService groupService;

	@RequestMapping(value = "/group/addGroup", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Object> addGroup(@RequestBody Group group) {

		int value = groupService.addGroup(group);
		return value > 0 ? new ResponseEntity<Object>("Successfully created new Group.", HttpStatus.CREATED)
				: new ResponseEntity<Object>("Unable to create new Group.", HttpStatus.I_AM_A_TEAPOT);
	}

	@RequestMapping(value = "/group/getGroup", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> getGroup(Integer group_id) {

		Group group = groupService.getGroup(group_id);
		return group != null ? new ResponseEntity<Object>(group, HttpStatus.OK)
				: new ResponseEntity<Object>("Unable to find the Group.", HttpStatus.I_AM_A_TEAPOT);
	}

	@RequestMapping(value = "/group/getAllGroups", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> getAllGroups() {

		List<Group> groups = groupService.getAllGroups();
		return (groups != null && !groups.isEmpty()) ? new ResponseEntity<Object>(groups, HttpStatus.OK)
				: new ResponseEntity<Object>("Unable to find the Group.", HttpStatus.I_AM_A_TEAPOT);
	}

	@RequestMapping(value = "/group/updateGroup", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> updateGroup(@RequestBody Group group) {

		boolean update = groupService.updateGroup(group);
		return update ? new ResponseEntity<Object>("Successfully updated the Group", HttpStatus.OK)
				: new ResponseEntity<Object>("Unable to update the Group.", HttpStatus.I_AM_A_TEAPOT);
	}

}
