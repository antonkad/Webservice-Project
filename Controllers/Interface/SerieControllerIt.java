package com.projetWebservice.Controllers.Interface;

import java.util.List;
import java.util.Optional;

import com.projetWebservice.Models.Serie;
import com.projetWebservice.Models.UpdateStatus;

public interface SerieControllerIt {

	/**
	* Get a Serie from its id
	*
	* @param id the id of the wanter Serie
	* @return a Serie with the given id if there is one
	*/
	List<Serie> getSerie(String id);
	/**
	* Add a Serie with the given ISBN
	*
	* @param isbn the ISBN
	* @return the id of the added Serie if the isbn exists
	*/
	String addSerie(Serie serie);
	/**
	* Borrow a Serie from the library
	*
	* @param id the id of the borrowed Serie
	* @param UpdateStatus (Status and userBorrowing)
	*/
	void borrowSerie(UpdateStatus updateStatus);
	/**
	* Return a Serie back to the library
	*/
	void returnSerie(UpdateStatus updateStatus);
	/**
	* Get all Series of the library
	*
	* @return the Series
	*/
	List<Serie> getSeries();
	/**
	* Return all Series with an author, a title or an ISBN matching the search term
	*
	* @param searchTerm the searched term
	* @return the Series matching the search term
	*/
	List<Serie> searchSeries(String searchTerm);
}
