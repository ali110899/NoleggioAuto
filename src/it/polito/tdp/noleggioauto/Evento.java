package it.polito.tdp.noleggioauto;

import java.util.Objects;

public class Evento implements Comparable<Evento>{
	
	public enum EventType {
		
		Nuovo_cliente,
		Restituzione_Auto
	}

	private int time; //minuti dell'evento(dall'inizio giornata lavorativa)
	private EventType type;
	
	public Evento(int time, EventType type) {
		super();
		this.time = time;
		this.type = type;
	}

	public int getTime() {
		return time;
	}

	public EventType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(time, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return time == other.time && type == other.type;
	}

	@Override
	public String toString() {
		return "Evento [time=" + time + ", type=" + type + "]";
	}
	
	@Override
	public int compareTo(Evento o) {
		// Potevamo fare anche una Collections.sort(e,new Comparator());
		return this.time-o.getTime();
	}
	
	
	
}
