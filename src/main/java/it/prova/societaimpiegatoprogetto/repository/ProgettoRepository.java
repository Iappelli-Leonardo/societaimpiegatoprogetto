package it.prova.societaimpiegatoprogetto.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.societaimpiegatoprogetto.model.Progetto;

public interface ProgettoRepository extends CrudRepository<Progetto, Long>,QueryByExampleExecutor <Progetto>{

//	@EntityGraph(attributePaths = { "impiegati" , ""})
//	List<Progetto> findAllByClienteAndAbitanti(Societa societaInput);
	
	@Query("select distinct p from Societa s join s.impiegati i join i.progetti p where s.id = ?1")
	List<Progetto> caricaTuttiIdSocieta(Long id);

	List<Progetto> findAllDistinctByImpiegati_RalIs(int ral);
	
}
