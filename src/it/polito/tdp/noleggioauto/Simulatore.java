package it.polito.tdp.noleggioauto;

import java.util.PriorityQueue;

import it.polito.tdp.noleggioauto.Evento.EventType;

public class Simulatore {
	
	/*
	 * Dobbiamo definire 
	 * 1. Modello Mondo : numero macchine che ho in garage
	*/
	private int numAuto;
	
	
	
	 /* 2.Parametri ingresso: numero auto acquistate
	  */
	  private int autoAcquistate;
	  private int numeroMinutiTraDueClienti; //tempo d'arrivo tra due clienti(10minuti)
	  private int tempoNoleggio; //durata noleggio(60minuti)
	  
	  private int multTempoNoleggio; //quanti Noleggi al Max posso avere
	  
	 /* 3.Indiaori uscita
	  */
	  
	  private int totClienti;
	  private int totClientiInsoddisfatti; //num clienti a cui non ho potuto affittare l'auto
	  
	 /* 4.Tipi di eventi(e coda degli eventi)
	  * Arriva cliente--> assegno auto
	  * Arriva cliente--> restituisce auto noleggiata
	 */

	  private PriorityQueue<Evento> queue;

	  //Il mio Comparatore deve avere SOLO i campi di input
	  public Simulatore(int autoAcquistate, int numeroMinutiTraDueClienti, int tempoNoleggio,
			int multTempoNoleggio) {
		this.autoAcquistate = autoAcquistate;
		this.numeroMinutiTraDueClienti = numeroMinutiTraDueClienti;
		this.tempoNoleggio = tempoNoleggio;
		this.multTempoNoleggio = multTempoNoleggio;
		
		this.numAuto = this.autoAcquistate;
		this.totClienti=0; //non ho ancora visto nessun cliente
		this.totClientiInsoddisfatti=0; //non ho ancora clienti insoddisfatti
	
		this.queue = new PriorityQueue<Evento>();
	  }
	  
	  public void run() {
		  
		  //finchè c'è roba in coda..estraggo evento come prossimo evento
		  while(this.queue.isEmpty()==false) {
			  //Estraggo un evento
			  Evento e = this.queue.poll();
			  int time= e.getTime();
			  EventType type = e.getType();
			  
			  System.out.println(e.getType()+" al tempo :"+e.getTime());
			  
			  switch(type) {
			  case Nuovo_cliente: 
				  //ho ancora auto?
				  if(numAuto>0) {
					  //si, incremento numClienti arrivati e in magazzino ho un auto in meno
					  this.totClienti++;
					  this.numAuto--;
					  
					  //quest'auto verrà restituita
					  //simulo una probabile durata di noleggio (da 1min a 60min)
					  int durata = this.tempoNoleggio*(int)(1+Math.random()*this.multTempoNoleggio);
					  //creo evento che si verificherà nel futuro
					  Evento eFuturo = new Evento(time+durata, EventType.Restituzione_Auto);
					  this.queue.add(eFuturo);
					  
				  } else {
					  //no, incremento numClienti arrivati e quello Insoddisfatti
					  this.totClienti++;
					  this.totClientiInsoddisfatti++;
				  }
				  break;
				  
			  case Restituzione_Auto: 
				  
				  //riaggiungo la mia auto al numAuto disponibili
				  this.numAuto++;
				  
				  break;
			  }
		  }
		  
	  }
	  
	  //Simula l'arrivo clienti
	  //popula la coda con l'arrivo previsto di clienti
	  public void inizialize() {
		  
		  //arrivano eventi nella queue: arrivo di tanti clienti
		  //apertura del servizio: esempio 8h
		  //i clienti arrivano ogni 10 minuti: all'h arrivano 6 clienti
		  int T = 10;
		  
		  for(int time=0; time<8*60; time=time+T) {
			  //ogni iterazione metto nella coda l'evento dell'arrivo cliente
			  Evento e = new Evento(time, EventType.Nuovo_cliente);
			  this.queue.add(e);  
		  } 
	  }

	public int getTotClienti() {
		return totClienti;
	}

	public int getTotClientiInsoddisfatti() {
		return totClientiInsoddisfatti;
	}

	  
}
