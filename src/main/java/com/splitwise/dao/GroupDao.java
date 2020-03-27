package com.splitwise.dao;

import java.util.List;

import com.splitwise.modal.Group;

public interface GroupDao {

	public Integer addGroup(Group group);

	public Group getGroup(Integer group_id);

	public List<Group> getAllGroups();

	public boolean updateGroup(Group group);

	public boolean deleteGroup(Integer group_id);

}
