package com.splitwise.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.splitwise.dao.UserDao;
import com.splitwise.exception.CustomException;
import com.splitwise.modal.Group;
import com.splitwise.modal.User;
import com.splitwise.modal.validate.ObjectValidator;
import com.splitwise.service.GroupService;
import com.splitwise.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userRepo;
	@Autowired
	private GroupService groupService;
	@Autowired
	private ObjectValidator objectValidator;

	@Override
	public boolean addUser(User user) throws CustomException {

		objectValidator.validatePkObject(user, User.class);
		Group group;
		if (user.getGroup_id() != null) {
			group = groupService.getGroup(user.getGroup_id());
			try {
				if (group != null) {
					group.setMembers_count(group.getMembers_count() + 1);
					boolean updated = groupService.updateGroup(group);
					if (updated) {
						return userRepo.addUser(user);
					} else {
						throw new CustomException("Unable to update members count.");
					}
				} else {
					throw new CustomException("Group ID " + user.getGroup_id()
							+ " does not exist. Please specify correct group ID and then try again.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		group = new Group();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-YYYY");
		group.setCreated_date(sdf.format(new Date()));
		group.setIsActive(true);
		group.setMembers_count(1);
		Integer groupID = groupService.addGroup(group);
		if (groupID != null) {
			user.setGroup_id(groupID);
			user.setTotal_owes(0.0);
			return userRepo.addUser(user);
		} else {
			return false;
		}
	}

	@Override
	public User getUser(String email) {
		return userRepo.getUser(email);
	}

	@Override
	public List<User> getAllUsersByGroupID(String email) throws CustomException {

		User user = getUser(email);
		if (user == null) {
			throw new CustomException("User profile does not exists for email: " + email);
		}
		return userRepo.getAllUsersByGroupId(user);
	}

	@Override
	public boolean updateUser(User user) {
		return userRepo.updateUser(user);
	}

	@Override
	public boolean deleteUser(String email) {

		User user = new User();
		user.setEmail_id(email);
		user.setActive(false);
		return userRepo.updateUser(user);
	}

}
