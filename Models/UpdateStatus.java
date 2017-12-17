package com.projetWebservice.Models;

public class UpdateStatus{
    private String isbn;
    private String userBorrowing;
	
    public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getUserBorrowing() {
		return userBorrowing;
	}
	public void setUserBorrowing(String userBorrowing) {
		this.userBorrowing = userBorrowing;
	}
}
