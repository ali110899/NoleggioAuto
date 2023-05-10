package it.polito.tdp.noleggioauto;

public class TestSimulatore {

	public static void main(String[] args) {

		Simulatore sim = new Simulatore(10, 10, 60, 3) ;
		sim.initialize();
		sim.run();
		
		System.out.println("Clienti totali: "+sim.getTOT_CLIENTI()) ;
		System.out.println("Clienti insoddisfatti: "+sim.getTOT_INSODDISFATTI()) ;
	
	}

}
