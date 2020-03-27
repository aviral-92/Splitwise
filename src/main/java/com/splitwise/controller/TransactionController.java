package com.splitwise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitwise.exception.CustomException;
import com.splitwise.modal.Transactions;
import com.splitwise.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "/transaction/createTransaction", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> createTransaction(@RequestBody Transactions transactions) {

		try {
			boolean created = transactionService.createTransaction(transactions);
			return created ? new ResponseEntity<Object>("Transaction Successfully added", HttpStatus.CREATED)
					: new ResponseEntity<Object>("Unable to processed transaction.", HttpStatus.I_AM_A_TEAPOT);
		} catch (CustomException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * public List<Transactions> getAllTransactionPerUser(String email);
	 * 
	 * public List<Transactions> getAllTransactionPerUserPerMonth(String email,
	 * String month);
	 * 
	 * public List<Transactions> getAllTransactionPerGroup(Integer group_id);
	 * 
	 * public List<Transactions> getAllTransactionPerGroupPerMonth(Integer group_id,
	 * String month);
	 * 
	 * public boolean updateTransaction(Transactions transaction);
	 */
}
