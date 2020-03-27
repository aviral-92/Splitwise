package com.splitwise.service;

import java.util.List;

import com.splitwise.exception.CustomException;
import com.splitwise.modal.Transactions;

public interface TransactionService {

	public boolean createTransaction(Transactions transactions) throws CustomException;

	public List<Transactions> getAllTransactionPerUser(String email);

	public List<Transactions> getAllTransactionPerUserPerMonth(String email, String month);

	public List<Transactions> getAllTransactionPerGroup(Integer group_id);

	public List<Transactions> getAllTransactionPerGroupPerMonth(Integer group_id, String month);

	public boolean updateTransaction(Transactions transaction);

}
