package com.splitwise.service;

import java.util.List;

import com.splitwise.modal.Group;

public interface GroupService {

	public Integer addGroup(Group group);

	public Group getGroup(Integer group_id);

	public boolean updateGroup(Group group);

	public boolean deleteGroup(Integer group_id);

	public List<Group> getAllGroups();

}
