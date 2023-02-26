package com.masai.dto;

public class EngineerImpl implements Engineer {

	private int engId;
	private String engName;
	private String engUserName;
	private String engPass;
	private String engCategory;

	@Override
	public int getEngId() {
		return engId;
	}

	@Override
	public void setEngId(int engId) {
		this.engId = engId;
	}

	@Override
	public String getEngName() {
		return engName;
	}

	@Override
	public void setEngName(String engName) {
		this.engName = engName;
	}

	@Override
	public String getEngUserName() {
		return engUserName;
	}

	@Override
	public void setEngUserName(String engUserName) {
		this.engUserName = engUserName;
	}

	@Override
	public String getEngPass() {
		return engPass;
	}

	@Override
	public void setEngPass(String engPass) {
		this.engPass = engPass;
	}

	@Override
	public String getEngCategory() {
		return engCategory;
	}

	@Override
	public void setEngCategory(String engCategory) {
		this.engCategory = engCategory;
	}

	public EngineerImpl() {
	}

	public EngineerImpl(String engUserName, String engPass) {
		this.engUserName = engUserName;
		this.engPass = engPass;
	}

	public EngineerImpl(String engName, String engUserName, String engPass, String engCategory) {
		this.engName = engName;
		this.engUserName = engUserName;
		this.engPass = engPass;
		this.engCategory = engCategory;
	}

	public EngineerImpl(int engId, String engName, String engUserName, String engPass, String engCategory) {
		this.engId = engId;
		this.engName = engName;
		this.engUserName = engUserName;
		this.engPass = engPass;
		this.engCategory = engCategory;
	}

	@Override
	public String toString() {
		return "Engineer [engId=" + engId + ", engName=" + engName + ", engUserName=" + engUserName + ", engPass="
				+ engPass + ", engCategory=" + engCategory + "]";
	}

}
