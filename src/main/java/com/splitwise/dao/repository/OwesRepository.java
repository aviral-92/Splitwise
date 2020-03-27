package com.splitwise.dao.repository;

import static com.splitwise.constant.SpliwiseConstants.OWES_TABLE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.splitwise.dao.MySQLTemplate;
import com.splitwise.dao.OwesDao;
import com.splitwise.modal.Owes;

@Repository
public class OwesRepository implements OwesDao {

	@Autowired
	private MySQLTemplate mySQLTemplate;

	@Override
	public boolean addOwes(Owes owes) {
		return mySQLTemplate.insert(owes, OWES_TABLE, Owes.class);
	}

	@Override
	public boolean addMultipleOwes(List<Owes> owes) {
		return mySQLTemplate.insertAll(owes, OWES_TABLE, Owes.class);
	}

	@Override
	public List<Owes> getAllOwesByGoupId(Integer group_d) {
		return mySQLTemplate.getAllRecordsByID(new Owes(group_d), OWES_TABLE, Owes.class);
	}

	@Override
	public Owes getOwesByOwesTo(String owesByEmail, String owesToEmail) {

		Owes owes = new Owes();
		owes.setOwes_by_user(owesByEmail);
		owes.setOwes_to_user(owesToEmail);
		List<Owes> list = mySQLTemplate.getAllRecordsByID(owes, OWES_TABLE, Owes.class);
		return list == null || list.isEmpty() ? null : list.get(0);
	}

}
