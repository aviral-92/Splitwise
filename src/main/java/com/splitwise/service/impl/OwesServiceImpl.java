package com.splitwise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.splitwise.dao.OwesDao;
import com.splitwise.modal.Owes;
import com.splitwise.service.OwesService;

@Service
public class OwesServiceImpl implements OwesService {

	@Autowired
	private OwesDao owesDao;

	@Override
	public boolean addOwes(Owes owes) {
		return owesDao.addOwes(owes);
	}

	@Override
	public boolean addMultipleOwes(List<Owes> owes) {
		return owesDao.addMultipleOwes(owes);
	}

	@Override
	public List<Owes> getAllOwesByGoupId(Integer group_d) {
		return owesDao.getAllOwesByGoupId(group_d);
	}

	@Override
	public Owes getOwesByOwesTo(String owesByEmail, String owesToEmail) {
		return owesDao.getOwesByOwesTo(owesByEmail, owesToEmail);
	}

}
