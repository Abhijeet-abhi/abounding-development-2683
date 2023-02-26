package com.masai.dto;

public class HodImpl  implements Hod {

	private String username;
	private String password;
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	public HodImpl() {

	}

	public HodImpl(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Hod [username=" + username + ", password=" + password + "]";
	}

}
