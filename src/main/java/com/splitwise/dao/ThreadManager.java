package com.splitwise.dao;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.splitwise.exception.CustomException;

@Component
public class ThreadManager {

	private static final Logger LOG = Logger.getLogger(ThreadManager.class);

	private ExecutorService executorService;
	private SplitwiseManager splitwiseManager;

	@Autowired
	public ThreadManager(ExecutorService executorService, SplitwiseManager splitwiseManager) {
		this.executorService = executorService;
		this.splitwiseManager = splitwiseManager;
		createTables();
	}

	public void createTables() {

		LOG.info("Started creating tables: createTables()");
		LOG.info(":::::::::Started tables creation:::::::::::::");
		Future<Boolean> iUserCreated = executorService.submit(getTablesCreatedTask("CREATE_USER"));
		Future<Boolean> isGroupCreated = executorService.submit(getTablesCreatedTask("CREATE_GROUP"));
		Future<Boolean> isTransactionsCreated = executorService.submit(getTablesCreatedTask("CREATE_TRANSACTIONAL"));
		Future<Boolean> isCountryCreated = executorService.submit(getTablesCreatedTask("CREATE_COUNTRY"));
		Future<Boolean> isOwesCreated = executorService.submit(getTablesCreatedTask("CREATE_OWES"));

		try {
			if (iUserCreated.get() && isGroupCreated.get() && isTransactionsCreated.get() && isCountryCreated.get()
					&& isOwesCreated.get()) {
				LOG.info(":::::::::Ending tables creation:::::::::::::");
			} else {
				LOG.info("::::::::::Unable to create tables:::::::::::");
			}
		} catch (InterruptedException | ExecutionException e) {
			LOG.info("Error creating table: " + e.getMessage());
		}
	}

	private CreateMedicalTables getTablesCreatedTask(String tablesCreatedTask) {
		return new CreateMedicalTables(splitwiseManager, tablesCreatedTask);
	}

	private class CreateMedicalTables implements Callable<Boolean> {

		private static final String TABLE_CREATED_NAME_STR = "Tables creation started: [%s]";
		private SplitwiseManager splitwiseManager;
		private String tablesCreatedTask;

		public CreateMedicalTables(SplitwiseManager splitwiseManager, String tablesCreatedTask) {

			this.splitwiseManager = splitwiseManager;
			this.tablesCreatedTask = tablesCreatedTask;
		}

		@Override
		public Boolean call() throws Exception {

			Thread.currentThread().setName(String.format(TABLE_CREATED_NAME_STR, tablesCreatedTask));
			LOG.info(":::::::::: " + Thread.currentThread().getName() + " :::::::::::");
			switch (tablesCreatedTask) {
			case "CREATE_USER":
				splitwiseManager.createUserTable();
				return true;
			case "CREATE_GROUP":
				splitwiseManager.createGroupTable();
				return true;
			case "CREATE_TRANSACTIONAL":
				splitwiseManager.createTransactionalTable();
				return true;
			case "CREATE_COUNTRY":
				splitwiseManager.createCountryTable();
				return true;
			case "CREATE_OWES":
				splitwiseManager.createOwes();
				return true;
			default:
				throw new CustomException("default: no case found.");
			}
		}
	}
}
