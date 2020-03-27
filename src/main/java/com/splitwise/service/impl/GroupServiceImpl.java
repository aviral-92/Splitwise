package com.splitwise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.splitwise.dao.GroupDao;
import com.splitwise.modal.Group;
import com.splitwise.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDao groupDao;

	@Override
	public Integer addGroup(Group group) {
		return groupDao.addGroup(group);
	}

	@Override
	public Group getGroup(Integer group_id) {
		return groupDao.getGroup(group_id);
	}

	@Override
	public List<Group> getAllGroups() {
		return groupDao.getAllGroups();
	}

	@Override
	public boolean updateGroup(Group group) {
		return groupDao.updateGroup(group);
	}

	@Override
	public boolean deleteGroup(Integer group_id) {
		return groupDao.deleteGroup(group_id);
	}

}
