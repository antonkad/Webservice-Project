package com.projetWebservice.Models;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Serie{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
 	private long id;
    private String name;
 	private String isbn = UUID.randomUUID().toString();
 	private boolean borrowed = false;
 	private String userBorrowing = "";
 	private String description = "";

    public Serie() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

	public boolean isStatus() {
		return borrowed;
	}

	public void setStatus(boolean status) {
		this.borrowed = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Serie [id=" + id + ", name=" + name + ", isbn=" + isbn + ", status=" + borrowed + ", userBorrowing="
				+ userBorrowing + "]";
	}


}
