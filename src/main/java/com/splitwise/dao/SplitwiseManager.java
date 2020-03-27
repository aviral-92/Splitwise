package com.splitwise.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.splitwise.dao.repository.SplitwiseRepository;
import com.splitwise.modal.Country;
import com.splitwise.modal.Group;
import com.splitwise.modal.Owes;
import com.splitwise.modal.Transactions;
import com.splitwise.modal.User;

@Component
public class SplitwiseManager {

	private static final Logger LOG = Logger.getLogger(SplitwiseManager.class);

	@Autowired
	private SplitwiseRepository splitwiseRepository;

	public void createUserTable() {

		LOG.info("Creating USER table");
		boolean isCreated = splitwiseRepository.createSplitwise(User.class);
		if (isCreated) {
			LOG.info("USER table created.");
		} else {
			LOG.info("USER table might already exist.");
		}
	}
	
	public void createGroupTable() {

		LOG.info("Creating GROUP table");
		boolean isCreated = splitwiseRepository.createSplitwise(Group.class);
		if (isCreated) {
			LOG.info("GROUP table created.");
		} else {
			LOG.info("GROUP table might already exist.");
		}
	}
	
	public void createTransactionalTable() {

		LOG.info("Creating TRANSACTIONS table");
		boolean isCreated = splitwiseRepository.createSplitwise(Transactions.class);
		if (isCreated) {
			LOG.info("TRANSACTIONS table created.");
		} else {
			LOG.info("TRANSACTIONS table might already exist.");
		}
	}
	
	public void createCountryTable() {

		LOG.info("Creating COUNTRY table");
		boolean isCreated = splitwiseRepository.createSplitwise(Country.class);
		if (isCreated) {
			LOG.info("COUNTRY table created.");
		} else {
			LOG.info("COUNTRY table might already exist.");
		}
	}

	public void createOwes() {

		LOG.info("Creating Owes table");
		boolean isCreated = splitwiseRepository.createSplitwise(Owes.class);
		if (isCreated) {
			LOG.info("Owes table created.");
		} else {
			LOG.info("Owes table might already exist.");
		}
	}
}
