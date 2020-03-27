package com.splitwise.dao;

import java.util.List;

import com.splitwise.modal.Transactions;

public interface TransactionDao {

	public boolean createTransaction(Transactions transactions);

	public List<Transactions> getTransactionsByEmail(String email);

	public List<Transactions> getAllTransactionPerUser(String email);

	public List<Transactions> getAllTransactionPerUserPerMonth(String email, String month);

	public List<Transactions> getAllTransactionPerGroup(Integer group_id);

	public List<Transactions> getAllTransactionPerGroupPerMonth(Integer group_id, String month);

	public boolean updateTransaction(Transactions transaction);

}
