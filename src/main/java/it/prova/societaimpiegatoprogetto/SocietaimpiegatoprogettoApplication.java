package it.prova.societaimpiegatoprogetto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.societaimpiegatoprogetto.Service.BatteriaDiTestService;

@SpringBootApplication
public class SocietaimpiegatoprogettoApplication implements CommandLineRunner{

	@Autowired
	private BatteriaDiTestService batteriaDiTestService;
	
	public static void main(String[] args) {
		SpringApplication.run(SocietaimpiegatoprogettoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("################ INIZIO  TEST #################");
		
		batteriaDiTestService.testInserisciNuovaSocieta();
		batteriaDiTestService.testFindByExampleSocieta();
		batteriaDiTestService.testRimozioneSocieta();
		batteriaDiTestService.testInserisciNuovaSocieta();
		batteriaDiTestService.testInserimentoProgetto();
		batteriaDiTestService.testCollegamentoIpegatoProgetti();
		batteriaDiTestService.testCollegamentoProgettoImpiegati();
		batteriaDiTestService.testQueryListaClientiDeiProgettiDiUnaSocieta();
		batteriaDiTestService.testQueryNomiSocietaConProgettoDurataPiuDiUnAnno();
		batteriaDiTestService.testQueryProgettiConImpiegatoRal();
		batteriaDiTestService.testQueryImpiegatoAnzianoSocietaFondatePrimaCheLavoraAProgettoPiuDi();
		
		System.out.println("################ FINE  TEST #################");
	}

}
