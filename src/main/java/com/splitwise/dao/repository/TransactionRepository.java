package com.splitwise.dao.repository;

import static com.splitwise.constant.SpliwiseConstants.TRANSACTION_TABLE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.splitwise.dao.MySQLTemplate;
import com.splitwise.dao.TransactionDao;
import com.splitwise.modal.Transactions;

@Repository
public class TransactionRepository implements TransactionDao {

	@Autowired
	private MySQLTemplate mySQLTemplate;

	@Override
	public boolean createTransaction(Transactions transactions) {
		return mySQLTemplate.insert(transactions, TRANSACTION_TABLE, Transactions.class);
	}

	@Override
	public List<Transactions> getTransactionsByEmail(String email) {
		Transactions transactions = new Transactions(email);
		return mySQLTemplate.getAllRecordsByID(transactions, TRANSACTION_TABLE, Transactions.class);
	}

	@Override
	public List<Transactions> getAllTransactionPerUser(String email) {
		Transactions transactions = new Transactions(email);
		return mySQLTemplate.getAllRecordsByID(transactions, TRANSACTION_TABLE, Transactions.class);
	}

	@Override
	public List<Transactions> getAllTransactionPerUserPerMonth(String email, String month) {
		Transactions transactions = new Transactions(email, month);
		return mySQLTemplate.getAllRecordsByID(transactions, TRANSACTION_TABLE, Transactions.class);
	}

	@Override
	public List<Transactions> getAllTransactionPerGroup(Integer group_id) {
		Transactions transactions = new Transactions(group_id);
		return mySQLTemplate.getAllRecordsByID(transactions, TRANSACTION_TABLE, Transactions.class);
	}

	@Override
	public List<Transactions> getAllTransactionPerGroupPerMonth(Integer group_id, String month) {
		Transactions transactions = new Transactions(group_id, month);
		return mySQLTemplate.getAllRecordsByID(transactions, TRANSACTION_TABLE, Transactions.class);
	}

	@Override
	public boolean updateTransaction(Transactions transaction) {
		return mySQLTemplate.updateRecord(transaction, TRANSACTION_TABLE, Transactions.class);
	}

}
