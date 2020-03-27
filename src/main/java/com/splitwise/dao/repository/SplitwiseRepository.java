package com.splitwise.dao.repository;

import static com.splitwise.constant.SpliwiseConstants.GET_SPLITWISE_OBJECT;
import static com.splitwise.constant.SpliwiseConstants.SPLITWISE_TABLES;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.splitwise.dao.MySQLTemplate;
import com.splitwise.modal.common.Persistable;

@Repository
public class SplitwiseRepository {

	@Autowired
	private MySQLTemplate mySQLTemplate;

	private static final Logger LOG = Logger.getLogger(SplitwiseRepository.class);

	@SuppressWarnings("unchecked")
	public <T extends Persistable> boolean createSplitwise(Class<T> clazz) {

		LOG.info("createSplitwise method called");
		String tableName = SPLITWISE_TABLES.get(clazz);
		T t = (T) GET_SPLITWISE_OBJECT.get(clazz);
		return mySQLTemplate.createTableWithPKAutoGeneratedIfNotExist(t, tableName, clazz);
	}
}