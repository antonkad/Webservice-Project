package com.projetWebservice.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.projetWebservice.Models.Serie;

@Transactional
public interface SerieDao extends JpaRepository<Serie,Long>{

	List<Serie> findByIsbn(String isbn);
	List<Serie> findByName(String name);
	List<Serie> findByDescriptionContaining(String searchTerm);


	//Tout ça c'est joli mais ça fait rien
	@Query("SELECT f FROM Serie f where f.name = ?1 AND f.description = ?1")
    public List<Serie> findByTitleAndDescription(String searchTerm);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE Serie f set f.borrowed=false, f.userBorrowing='' where f.isbn = :isbn AND f.userBorrowing = :userBorrowing")
    public void updateReturnedSerie(@Param("isbn") String isbn, @Param("userBorrowing") String userBorrowing);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE Serie f SET f.borrowed=true, f.userBorrowing= :userBorrowing where f.isbn = :isbn AND f.userBorrowing = :userBorrowing")
    public int updateBorrowedSerie(@Param("isbn") String isbn, @Param("userBorrowing") String userBorrowing);

}
