package com.projetWebservice.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetWebservice.Controllers.Interface.FilmControllerIt;
import com.projetWebservice.Dao.FilmDao;
import com.projetWebservice.Models.Film;
import com.projetWebservice.Models.UpdateStatus;
import com.projetWebservice.ProjetWebServiceApplication;

@RestController
@RequestMapping(value="/rest/film")
public class FilmController implements FilmControllerIt {
	
	private FilmDao filmDao;

	public FilmController(FilmDao filmDao) {
		this.filmDao = filmDao;
	}
	
	@Override
	@RequestMapping(value="/{isbn}",method = RequestMethod.GET)
	public List<Film> getfilm(@PathVariable String isbn) {//<----J'avoue que renvoyer une list à 
		return filmDao.findByIsbn(isbn);					// partir d'un UUID c'est pas propre
	}

	@Override
	@RequestMapping(value="/add",method = RequestMethod.PUT)
	public String addfilm(@RequestBody Film film) {
		filmDao.save(film);
		return film.getIsbn();
	}
	
	@Override
	@RequestMapping(value="/borrow",method = RequestMethod.POST)
	public void borrowfilm(@RequestBody UpdateStatus updateStatus) {
		
		Film film = filmDao.findByIsbn(updateStatus.getIsbn()).get(0);//<----ça aussi désolé.
		film.setStatus(true);
		film.setUserBorrowing(updateStatus.getUserBorrowing());
		filmDao.saveAndFlush(film);				
		
		//filmDao.updateBorrowedFilm(updateStatus.getIsbn(), updateStatus.getUserBorrowing());
	}

	@Override
	@RequestMapping(value="/return",method = RequestMethod.POST)
	public void returnfilm(@RequestBody UpdateStatus updateStatus) {
		
		Film film = filmDao.findByIsbn(updateStatus.getIsbn()).get(0);
		film.setStatus(false);
		film.setUserBorrowing("");
		filmDao.saveAndFlush(film);
		
		//filmDao.updateReturnedFilm(updateStatus.getIsbn(), updateStatus.getUserBorrowing());
	} 

	@Override
	@RequestMapping(value="/",method = RequestMethod.GET)
	public List<Film> getfilms() {
		return filmDao.findAll();
	}

	@Override
	@RequestMapping(value="/search",method = RequestMethod.GET)
	public List<Film> searchfilms(@RequestParam(value="searchTerm") String searchTerm) {
		List<Film> tmp = filmDao.findByName(searchTerm);
		filmDao.findByDescriptionContaining(searchTerm).forEach(film->tmp.add(film));
		return tmp;
	}	
}
