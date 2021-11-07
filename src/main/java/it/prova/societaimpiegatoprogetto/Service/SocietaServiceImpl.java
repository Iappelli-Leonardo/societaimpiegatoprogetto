package it.prova.societaimpiegatoprogetto.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.societaimpiegatoprogetto.model.Societa;
import it.prova.societaimpiegatoprogetto.repository.SocietaRepository;

@Service
public class SocietaServiceImpl implements SocietaService{
	
	@Autowired
	SocietaRepository societaRepository;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional(readOnly = true)
	public List<Societa> listAllSocieta() {
		return (List<Societa>) societaRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Societa caricaSingoloSocieta(Long id) {
		return societaRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Societa societaInstance) {
		societaRepository.save(societaInstance);
	}

	@Transactional
	public void inserisciNuovo(Societa societaInstance) {
		societaRepository.save(societaInstance);
		
	}

	@Transactional
	public void rimuovi(Societa societaInstance) {
		societaRepository.delete(societaInstance);
		
	}

	@Transactional(readOnly = true)
	public List<Societa> findByExample(Societa example) {
		String query = "select s from Societa s where s.id = s.id ";

		if (StringUtils.isNotEmpty(example.getRagioneSociale()))
			query += " and s.ragioneSociale like '%" + example.getRagioneSociale() + "%' ";
		if ( !(example.getDataFondazione() == null))
			query += " and s.dataFondazione like '%" + example.getDataFondazione() + "%' ";
	
		return entityManager.createQuery(query, Societa.class).getResultList();
	}

	@Transactional
	public List<Societa> societaProgettiPiuDiUnAnno() {
		return societaRepository.societaConProgettiPiuDiUnAnno();		
	}

}
