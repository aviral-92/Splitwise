package com.splitwise.modal;

import java.util.Map;

public class WhomOwesWho {

	private Integer group_id;
	private User user;
	private Map<String, Double> emailWithAmountMap;

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, Double> getEmailWithAmountMap() {
		return emailWithAmountMap;
	}

	public void setEmailWithAmountMap(Map<String, Double> emailWithAmountMap) {
		this.emailWithAmountMap = emailWithAmountMap;
	}

}
