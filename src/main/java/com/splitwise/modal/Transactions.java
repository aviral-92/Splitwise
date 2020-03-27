package com.splitwise.modal;

import java.util.List;

import com.splitwise.modal.common.Persistable;

public class Transactions implements Persistable {

	private Integer transaction_id;
	private Integer group_id;
	private String transaction_name;
	private String email_id;
	private Double transaction_value;
	private String transaction_month;
	private String transaction_date;
	private Boolean equally_split;
	private Boolean isActive;
	private List<String> splitAmongEmailsList;

	public Transactions() {
		super();
	}

	public Transactions(String email_id, String transaction_month) {
		super();
		this.email_id = email_id;
		this.transaction_month = transaction_month;
	}

	public Transactions(Integer group_id, String transaction_month) {
		super();
		this.group_id = group_id;
		this.transaction_month = transaction_month;
	}

	public Transactions(String email_id) {
		super();
		this.email_id = email_id;
	}

	public Transactions(Integer group_id) {
		super();
		this.group_id = group_id;
	}

	@Override
	public String getPrimaryKeyField() {
		return "transaction_id";
	}

	@Override
	public boolean isAutoGeneratedField() {
		return true;
	}

	@Override
	public Integer getGroup_id() {
		return group_id;
	}

	@Override
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public Integer getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(Integer transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getTransaction_name() {
		return transaction_name;
	}

	public void setTransaction_name(String transaction_name) {
		this.transaction_name = transaction_name;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public Double getTransaction_value() {
		return transaction_value;
	}

	public void setTransaction_value(Double transaction_value) {
		this.transaction_value = transaction_value;
	}

	public String getTransaction_month() {
		return transaction_month;
	}

	public void setTransaction_month(String transaction_month) {
		this.transaction_month = transaction_month;
	}

	public String getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getEqually_split() {
		return equally_split;
	}

	public void setEqually_split(Boolean equally_split) {
		this.equally_split = equally_split;
	}

	public List<String> getSplitAmongEmailsList() {
		return splitAmongEmailsList;
	}

	public void setSplitAmongEmailsList(List<String> splitAmongEmailsList) {
		this.splitAmongEmailsList = splitAmongEmailsList;
	}

}