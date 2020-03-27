package com.splitwise.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.splitwise.dao.TransactionDao;
import com.splitwise.exception.CustomException;
import com.splitwise.modal.Group;
import com.splitwise.modal.Owes;
import com.splitwise.modal.Transactions;
import com.splitwise.modal.User;
import com.splitwise.service.GroupService;
import com.splitwise.service.OwesService;
import com.splitwise.service.TransactionService;
import com.splitwise.service.UserService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private UserService userService;
	@Autowired
	private TransactionDao transactionDao;
	@Autowired
	private GroupService groupService;
	@Autowired
	private OwesService owesService;

	@Override
	public boolean createTransaction(Transactions transactions) throws CustomException {

		if (transactions != null && !StringUtils.isEmpty(transactions.getEmail_id())) {
			User user = userService.getUser(transactions.getEmail_id());
			if (user != null) {
				transactions.setGroup_id(user.getGroup_id());
				boolean added = transactionDao.createTransaction(transactions);
				if (added) {
					Group group = groupService.getGroup(user.getGroup_id());
					int noOfUsersToSplit = 0;
					if (transactions.getEqually_split()) {
						noOfUsersToSplit = group != null && group.getMembers_count() != null ? group.getMembers_count()
								: 0;
					} else {
						/* Adding 1 so to count share who is paying as well. */
						noOfUsersToSplit = transactions.getSplitAmongEmailsList() != null
								&& !transactions.getSplitAmongEmailsList().isEmpty()
										? transactions.getSplitAmongEmailsList().size() + 1
										: 0;
					}
					if (noOfUsersToSplit > 0) {
						List<Owes> owesList = new ArrayList<Owes>();
						double splitUpAmount = transactions.getTransaction_value() / noOfUsersToSplit;
						for (int i = 0; i < noOfUsersToSplit; i++) {
							Owes owes = owesService.getOwesByOwesTo(user.getEmail_id(),
									transactions.getSplitAmongEmailsList().get(i));
							if (owes == null) {
								owes = new Owes(user.getGroup_id(), transactions.getTransaction_id(),
										user.getEmail_id(), transactions.getSplitAmongEmailsList().get(i),
										splitUpAmount);
							} else {
								Owes reverseOwes = owesService.getOwesByOwesTo(
										transactions.getSplitAmongEmailsList().get(i), user.getEmail_id());
								if (reverseOwes == null) {
									owes.setAmount(owes.getAmount() + splitUpAmount);
								} else {
									Double amount = owes.getAmount() - reverseOwes.getAmount();
									if (amount == 0.0) {
										owes.setAmount(0.0);
										reverseOwes.setAmount(0.0);
									} else if (amount > 0.0) {
										owes.setAmount(amount);
										reverseOwes.setAmount(0.0);
									} else {
										owes.setAmount(0.0);
										reverseOwes.setAmount(Math.abs(amount));
									}
									owesList.add(reverseOwes);
								}
							}
							owesList.add(owes);
						}
						return owesService.addMultipleOwes(owesList);
					} else {
						throw new CustomException(
								"Please split equally or provide user's email list in 'SplitAmongEmailsList' field.");
					}
				}
				return added;
			} else {
				throw new CustomException("Unable to find the specified USER.");
			}
		} else {
			throw new CustomException("Please specify User's EMail ID.");
		}
	}

	@Override
	public List<Transactions> getAllTransactionPerUser(String email) {
		return transactionDao.getAllTransactionPerUser(email);
	}

	@Override
	public List<Transactions> getAllTransactionPerUserPerMonth(String email, String month) {
		return transactionDao.getAllTransactionPerUserPerMonth(email, month);
	}

	@Override
	public List<Transactions> getAllTransactionPerGroup(Integer group_id) {
		return transactionDao.getAllTransactionPerGroup(group_id);
	}

	@Override
	public List<Transactions> getAllTransactionPerGroupPerMonth(Integer group_id, String month) {
		return transactionDao.getAllTransactionPerGroupPerMonth(group_id, month);
	}

	@Override
	public boolean updateTransaction(Transactions transaction) {
		// TODO Auto-generated method stub
		return false;
	}

}
