package it.prova.societaimpiegatoprogetto.Service;

import java.util.List;

import it.prova.societaimpiegatoprogetto.model.Progetto;


public interface ProgettoService {

	public List<Progetto> listAllProgetti();

	public Progetto caricaSingoloProgetto(Long id);

	public void aggiorna(Progetto progettoInstance);

	public void inserisciNuovo(Progetto progettoInstance);

	public void rimuovi(Progetto progettoInstance);
	
	public List<Progetto> caricaTuttiIdSocieta(Long id);
	
	List<Progetto> almenoUnImpiegatoRal(int ral);
	
}
