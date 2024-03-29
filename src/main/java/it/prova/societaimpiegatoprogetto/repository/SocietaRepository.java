package it.prova.societaimpiegatoprogetto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.societaimpiegatoprogetto.model.Societa;


public interface SocietaRepository extends CrudRepository<Societa, Long>,QueryByExampleExecutor <Societa>{


	@Query("select distinct s from Societa s join s.impiegati i join i.progetti p where p.durata > 6")
	List<Societa> societaConProgettiPiuDiUnAnno();
	
}
