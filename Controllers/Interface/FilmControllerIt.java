package com.projetWebservice.Controllers.Interface;

import java.util.List;
import java.util.Optional;

import com.projetWebservice.Models.Film;
import com.projetWebservice.Models.UpdateStatus;

public interface FilmControllerIt {
	
	/**
	* Get a film from its id
	*
	* @param id the id of the wanter film
	* @return a film with the given id if there is one
	*/
	List<Film> getfilm(String id);
	/**
	* Add a film with the generated ISBN
	*
	* @param Film film
	* @return the id of the added film if the isbn exists
	*/
	String addfilm(Film film);
	/**
	* Borrow a film from the library
	*
	* @param id the id of the borrowed film
	* @param UpdateStatus (Status and userBorrowing)
	*/
	void borrowfilm(UpdateStatus updateStatus);
	/**
	* Return a film back to the library
	*/
	void returnfilm(UpdateStatus updateStatus);
	/**
	* Get all films of the library
	*
	* @return the films
	*/
	List<Film> getfilms();
	/**
	* Return all films with an author, a title or an ISBN matching the search term
	*
	* @param searchTerm the searched term
	* @return the films matching the search term
	*/
	List<Film> searchfilms(String searchTerm);
}