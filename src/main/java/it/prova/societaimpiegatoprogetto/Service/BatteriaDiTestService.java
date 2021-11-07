package it.prova.societaimpiegatoprogetto.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.societaimpiegatoprogetto.model.Impiegato;
import it.prova.societaimpiegatoprogetto.model.Progetto;
import it.prova.societaimpiegatoprogetto.model.Societa;

@Service
public class BatteriaDiTestService {

	@Autowired
	SocietaService societaService;
	@Autowired
	ImpiegatoService impiegatoService;
	@Autowired
	ProgettoService progettoService;
	
	public void testInserisciNuovaSocieta() {
		Societa nuovaSocieta = new Societa("srl", new Date());
		if (nuovaSocieta.getId() != null)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: transient object con id valorizzato");
		
		societaService.inserisciNuovo(nuovaSocieta);
		
		if (nuovaSocieta.getId() == null || nuovaSocieta.getId() < 1)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: inserimento fallito");
	
		societaService.rimuovi(nuovaSocieta);
		
		System.out.println("################ INSERISCI SOCIETA OK #################");
	}
	
	public void testFindByExampleSocieta() {
		Societa societaExample = new Societa("snc",new Date());
		if (societaExample.getId() != null)
			throw new RuntimeException("testFindByExampleSocieta...failed: transient object con id valorizzato");
		societaService.findByExample(societaExample);

		System.out.println("################ FINDBYEXAMPLE OK #################");
	}
	
	public void testRimozioneSocieta() {
		Societa societaDaRimuovere = new Societa("spa",new Date());
		
		if (societaDaRimuovere.getId() != null)
			throw new RuntimeException("testRimozioneSocieta...failed: transient object con id valorizzato");
		
		societaService.inserisciNuovo(societaDaRimuovere);
		
		if (societaDaRimuovere.getId() == null || societaDaRimuovere.getId() < 1)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: inserimento fallito");
	
		societaService.rimuovi(societaDaRimuovere);
		
		if(societaService.listAllSocieta().isEmpty()) {
			System.out.println("################ RIMOZIONE SOCIETA OK #################");
		}
	}
	
	public void testInserimentoImpiegato() {
		
		Societa nuovaSocieta = new Societa("srcc",new Date());
		
		Impiegato nuovoImpiegato = new Impiegato("Leonardo","Iappelli",new Date("2010/02/10"),30000,nuovaSocieta);
		
		if (nuovaSocieta.getId() != null)
			throw new RuntimeException("testInserimentoImpiegato...failed: transient object con id valorizzato");
		
		if (nuovoImpiegato.getId() != null)
			throw new RuntimeException("testInserimentoImpiegato...failed: transient object con id valorizzato");
		
		nuovoImpiegato.setSocieta(nuovaSocieta);
		
		if(!(nuovoImpiegato.getSocieta()== null))		
		System.out.println("################ INSERIMENTO IMPIEGATO OK #################");
	}

	public void testInserimentoProgetto() {
		
		Progetto nuovoProgetto = new Progetto("MicroServizi","Arnaldo Franchetti",4);
		
		if (nuovoProgetto.getId() != null)
			throw new RuntimeException("testInserimentoProgetto...failed: transient object con id valorizzato");
		
		progettoService.inserisciNuovo(nuovoProgetto);
		
		if(!progettoService.listAllProgetti().isEmpty())
		System.out.println("################ INSERIMENTO PROGETTO OK #################");
		
		progettoService.rimuovi(nuovoProgetto);
	}

	public void testCollegamentoIpegatoProgetti() {
		
		Societa societa = new Societa("solving", new Date());
		societaService.inserisciNuovo(societa);

		if (societa.getId() == null)
			throw new RuntimeException("testCollegamentoIpegatoProgetti...failed: transient object con id valorizzato");

		Progetto progetto1 = new Progetto("sirsi", "socua3", 4);
		
		progettoService.inserisciNuovo(progetto1);

		Progetto progetto2 = new Progetto("sirod", "eors", 4);
		
		progettoService.inserisciNuovo(progetto2);

		Impiegato impiegato = new Impiegato("leo", "ernesti", new Date("2020/12/12"), 5000, societa);

	
		impiegatoService.inserisciNuovo(impiegato);
		impiegato.addToProgetti(progetto1);
		impiegato.addToProgetti(progetto2);
		impiegatoService.aggiorna(impiegato);

		System.out.println("################ COLLEGAMENTO IMPIEGATOPROGETTI OK #################");
	}
	
	public void testCollegamentoProgettoImpiegati() {
		
		Societa societa = new Societa("Tesla", new Date("1950/02/26"));
		societaService.inserisciNuovo(societa);

		if (societa.getId() == null)
			throw new RuntimeException("testCollegamentoProgettoImpiegati...failed: transient object con id valorizzato");

		Progetto progetto = new Progetto("App2", "Societa appaltatrice", 4);
		progettoService.inserisciNuovo(progetto);

		Progetto progetto2 = new Progetto("App3", "Societa appaltatrice", 4);
		progettoService.inserisciNuovo(progetto);

		Impiegato impiegato = new Impiegato("leo", "ernesti", new Date("2010/02/10"), 5000, societa);

		impiegatoService.inserisciNuovo(impiegato);
		impiegato.addToProgetti(progetto);
		impiegato.addToProgetti(progetto2);
		impiegatoService.aggiorna(impiegato);

		System.out.println("################ COLLEGAMENTO PROGETTOIMPIEGATI OK #################");
	}
	
	public void testQueryListaClientiDeiProgettiDiUnaSocieta() {

		Societa societa = new Societa("Blue Origin", new Date("1950/02/26"));
		societaService.inserisciNuovo(societa);

		if (societa.getId() == null)
			throw new RuntimeException("testListaClientiDeiProgettiDiUnaSocieta...failed: transient object con id valorizzato");

		Progetto progetto = new Progetto("url", "Dao", 3);
		Progetto progetto2 = new Progetto("Ppsh", "microservizi", 3);
		progettoService.inserisciNuovo(progetto);
		progettoService.inserisciNuovo(progetto2);

		Impiegato impiegato = new Impiegato("Pippo", "Franco", new Date("1990/11/24"), 50000, societa);
		impiegatoService.inserisciNuovo(impiegato);

		impiegato.addToProgetti(progetto);
		impiegato.addToProgetti(progetto2);

		impiegatoService.aggiorna(impiegato);

		List<Progetto> progetti = progettoService.caricaTuttiIdSocieta(societa.getId());


		progetti.forEach(p -> System.out.println(p.getCliente()));
		
		System.out.println("################ QUERYLISTACLIENTEID OK #################");
	}
	
	public void testQueryNomiSocietaConProgettoDurataPiuDiUnAnno() {
		Societa societa = new Societa("Francy", new Date("1999/09/11"));
		societaService.inserisciNuovo(societa);

		Societa societa2 = new Societa("Raland", new Date("1976/08/12"));
		societaService.inserisciNuovo(societa2);

		Progetto progetto = new Progetto("innfarross", "Giancarli", 10);
		Progetto progetto2 = new Progetto("Eliminazione", "Ross", 13);
		progettoService.inserisciNuovo(progetto);
		progettoService.inserisciNuovo(progetto2);

		Impiegato impiegato = new Impiegato("Orlando", "Porlo", new Date("2002/07/28"), 201999, societa);
		impiegatoService.inserisciNuovo(impiegato);
		Impiegato impiegato2 = new Impiegato("Giancarlo", "Falsetti", new Date("2020/03/30"), 2000, societa2);
		impiegatoService.inserisciNuovo(impiegato2);

		impiegato.addToProgetti(progetto);
		impiegato.addToProgetti(progetto2);

		impiegatoService.aggiorna(impiegato);
		impiegatoService.aggiorna(impiegato2);

		List<Societa> societa1 = societaService.societaProgettiPiuDiUnAnno();

		societa1.forEach(s -> System.out.println(s));
		
		System.out.println("################ QUERYLISTNOMISOCIETADURATAUNANNO OK #################");
	}
	
	public void testQueryProgettiConImpiegatoRal() {

		Societa societa = new Societa("Socc2", new Date("1950/02/26"));
		societaService.inserisciNuovo(societa);

		Progetto progetto1 = new Progetto("Paraceta", "Perla", 10);
		Progetto progetto2 = new Progetto("flusso", "Colvins", 13);
		progettoService.inserisciNuovo(progetto1);
		progettoService.inserisciNuovo(progetto2);

		Impiegato impiegato1 = new Impiegato("Lorenzo", "Corneli", new Date("1999/10/06"), 29173, societa);
		impiegatoService.inserisciNuovo(impiegato1);
		Impiegato impiegato2 = new Impiegato("Vincenzo", "Ramus", new Date("2021/12/09"), 7009, societa);
		impiegatoService.inserisciNuovo(impiegato2);

		impiegato1.addToProgetti(progetto1);
		impiegato2.addToProgetti(progetto2);

		impiegatoService.aggiorna(impiegato1);
		impiegatoService.aggiorna(impiegato2);

		List<Progetto> progetti = progettoService.almenoUnImpiegatoRal(30000);


		System.out.println("################ QUERYLISTNOMISOCIETADURATAUNANNO OK #################");
	}
	
	public void testQueryImpiegatoAnzianoSocietaFondatePrimaCheLavoraAProgettoPiuDi() {

		Societa societa = new Societa("Usp", new Date("1990/12/02"));
		societaService.inserisciNuovo(societa);

		Progetto progetto = new Progetto("Infrastruttura esterna", "Giess", 10);
		progettoService.inserisciNuovo(progetto);

		Impiegato impiegato1 = new Impiegato("Francesco", "Grigi", new Date("2001/01/04"), 2993, societa);
		impiegatoService.inserisciNuovo(impiegato1);
		Impiegato impiegato2 = new Impiegato("Simone", "Brandi", new Date("2013/08/23"), 90000, societa);
		impiegatoService.inserisciNuovo(impiegato2);

		impiegato1.addToProgetti(progetto);
		impiegato2.addToProgetti(progetto);

		impiegatoService.aggiorna(impiegato1);
		impiegatoService.aggiorna(impiegato2);

		impiegatoService.impiegatoPiuAnzianoSocietaFondatePrimaCheLavoraAProgettoPiuDi();
		System.out.println("################ QUERYIMPIEGATOANZIANOSOCIETFONDATEPRIMACHRLAVOROPROGETTO OK #################");
	}
	
}
