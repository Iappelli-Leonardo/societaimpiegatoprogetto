package it.prova.societaimpiegatoprogetto.Service;

import java.util.List;

import it.prova.societaimpiegatoprogetto.model.Impiegato;


public interface ImpiegatoService {

	public List<Impiegato> listAllImpiegati();

	public Impiegato caricaSingoloImpiegato(Long id);

	public void aggiorna(Impiegato impiegatoInstance);

	public void inserisciNuovo(Impiegato impiegatoInstance);

	public void rimuovi(Impiegato impiegatoInstance);
	
	public Impiegato caricaImpiegatoEagerProgetti(Long id);
	
	public List<Impiegato> impiegatoPiuAnzianoSocietaFondatePrimaCheLavoraAProgettoPiuDi();
	
}
