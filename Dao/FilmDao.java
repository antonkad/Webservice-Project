package com.projetWebservice.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.projetWebservice.Models.Film;

@Transactional
public interface FilmDao extends JpaRepository<Film,Long>{
	
	List<Film> findByIsbn(String isbn);	
	List<Film> findByName(String name);
	List<Film> findByDescriptionContaining(String searchTerm);

	
	//Tout ça c'est joli mais ça fait rien	
	@Query("SELECT f FROM Film f where f.name = ?1 AND f.description = ?1")
    public List<Film> findByTitleAndDescription(String searchTerm);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE Film f set f.borrowed=false, f.userBorrowing='' where f.isbn = :isbn AND f.userBorrowing = :userBorrowing")
    public void updateReturnedFilm(@Param("isbn") String isbn, @Param("userBorrowing") String userBorrowing);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Film f SET f.borrowed=true, f.userBorrowing= :userBorrowing where f.isbn = :isbn AND f.userBorrowing = :userBorrowing")
    public int updateBorrowedFilm(@Param("isbn") String isbn, @Param("userBorrowing") String userBorrowing);

}
