package com.projetWebservice.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetWebservice.Controllers.Interface.SerieControllerIt;
import com.projetWebservice.Dao.SerieDao;
import com.projetWebservice.Models.Serie;
import com.projetWebservice.Models.UpdateStatus;
import com.projetWebservice.ProjetWebServiceApplication;

@RestController
@RequestMapping(value="/rest/serie")
public class SerieController implements SerieControllerIt {

	private SerieDao serieDao;

	public SerieController(SerieDao serieDao) {
		this.serieDao = serieDao;
	}

	@Override
	@RequestMapping(value="/{isbn}",method = RequestMethod.GET)
	public List<Serie> getSerie(@PathVariable String isbn) {//<----J'avoue que renvoyer une list à
		return serieDao.findByIsbn(isbn);					// partir d'un UUID c'est pas propre
	}

	@Override
	@RequestMapping(value="/add",method = RequestMethod.PUT)
	public String addSerie(@RequestBody Serie serie) {
		serieDao.save(serie);
		return serie.getIsbn();
	}

	@Override
	@RequestMapping(value="/borrow",method = RequestMethod.POST)
	public void borrowSerie(@RequestBody UpdateStatus updateStatus) {

		Serie serie = serieDao.findByIsbn(updateStatus.getIsbn()).get(0);//<----ça aussi désolé.
		serie.setStatus(true);
		serie.setUserBorrowing(updateStatus.getUserBorrowing());
		serieDao.saveAndFlush(serie);

		//serieDao.updateBorrowedSerie(updateStatus.getIsbn(), updateStatus.getUserBorrowing());
	}

	@Override
	@RequestMapping(value="/return",method = RequestMethod.POST)
	public void returnSerie(@RequestBody UpdateStatus updateStatus) {

		Serie serie = serieDao.findByIsbn(updateStatus.getIsbn()).get(0);
		serie.setStatus(false);
		serie.setUserBorrowing("");
		serieDao.saveAndFlush(serie);

		//serieDao.updateReturnedSerie(updateStatus.getIsbn(), updateStatus.getUserBorrowing());
	}

	@Override
	@RequestMapping(value="/",method = RequestMethod.GET)
	public List<Serie> getSeries() {
		return serieDao.findAll();
	}

	@Override
	@RequestMapping(value="/search",method = RequestMethod.GET)
	public List<Serie> searchSeries(@RequestParam(value="searchTerm") String searchTerm) {
		List<Serie> tmp = serieDao.findByName(searchTerm);
		serieDao.findByDescriptionContaining(searchTerm).forEach(serie->tmp.add(serie));
		return tmp;
	}
}
