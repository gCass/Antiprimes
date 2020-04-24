package thread;

import java.util.Observable;

import antiprimes.AntiPrimes;
import antiprimes.AntiPrimesSequence;
import antiprimes.Number;

public class AntiprimesProcessor extends Observable implements Runnable {
	
	private AntiPrimesSequence antiPrimesSequence;
	private Number n;
	private Number newAntiPrimes;
	
	public AntiprimesProcessor() {
		// TODO Auto-generated constructor stub
	}
	
	public AntiprimesProcessor(AntiPrimesSequence antiPrimesSequence) {
		// TODO Auto-generated constructor stub
		this.antiPrimesSequence = antiPrimesSequence;
	}
	
	
	synchronized public void setNumberToSurpass(Number n){
		this.n = n;
	}

	public Number getAntiprimesCalculated() {
		return this.newAntiPrimes;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Inizio run");
		while(true) {
			System.out.println("Ciclo infinito");
			newAntiPrimes = AntiPrimes.nextAntiPrimeAfter(n); //Calcolo il numero
			System.out.println("Numero "+ newAntiPrimes.getValue());		
			antiPrimesSequence.addElementToSequence(newAntiPrimes); //Eentro nel metedo syncro
		}
	}
	
}
