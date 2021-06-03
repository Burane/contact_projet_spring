package com.burane.contact.model;

public enum ERole {
	User(Constants.User),
	Admin(Constants.Admin);

	ERole(String role) {
	}

	public static class Constants {
		public static final String User = "User";
		public static final String Admin = "Admin";
	}
}