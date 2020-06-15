package com.mg.gateWay.model;


/**DTO mapping class for user registration mapping fields*/
public class UserDTO {
	
	private Integer id;
	
	private String emailId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private int contactNumber;
	
	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public int getContactNumber() {
		return contactNumber;
	}



	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}
	
}
