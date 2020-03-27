package com.splitwise.dao;

import java.util.List;

import com.splitwise.modal.Owes;

public interface OwesDao {

	public boolean addOwes(Owes owes);

	public boolean addMultipleOwes(List<Owes> owes);

	public List<Owes> getAllOwesByGoupId(Integer group_d);

	public Owes getOwesByOwesTo(String owesByEmail, String owesToEmail);
}
