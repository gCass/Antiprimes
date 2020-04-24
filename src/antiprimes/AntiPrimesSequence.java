package antiprimes;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import thread.AntiprimesProcessor;


/**
 * Represent the sequence of antiprimes found so far.
 */
public class AntiPrimesSequence extends Observable /* implements Observer */{

    /**
     * The numbers in the sequence.
     */
    private List<Number> antiPrimes = new ArrayList<>();
    private Thread thread;
    private boolean firstTime;
    private AntiprimesProcessor antiprimesProcessor = new AntiprimesProcessor(this);
    private boolean usedFromUi;
    /**
     * Create a new sequence containing only the first antiprime (the number '1').
     */
    public AntiPrimesSequence() {
        this.reset();
        //usedFromUi = true;
        firstTime = true;
        antiprimesProcessor = new AntiprimesProcessor(this);
        thread = new Thread(antiprimesProcessor);
    }

    /**
     * Clear the sequence so that it contains only the first antiprime (the number '1').
     */
    synchronized public void reset() {
     
    	antiPrimes.clear();
        antiPrimes.add(new Number(1, 1));
        antiprimesProcessor.setNumberToSurpass(new Number(1, 1));
        this.setChanged();
        this.notifyObservers();
    }

     /**
     * Return the last antiprime found.
     */
    public Number getLast() {
        int n = antiPrimes.size();
        return antiPrimes.get(n - 1);
    }

    /**
     * Return the last k antiprimes found.
     */
    synchronized public List<Number> getLastK(int k) {    	
    	int n = antiPrimes.size();
        if (k > n)
            k = n;
                
        return antiPrimes.subList(n - k, n);
    }
    
      
    //Versione dove il thread accede alla sequenza come se fosse un monitor
    synchronized public void computeNext() {
    	antiprimesProcessor.setNumberToSurpass(this.getLast());
    	if(firstTime) {
    		thread.start();//Lo faccio partire se era la prima volta
    		usedFromUi = false;
    		firstTime = false;
    	}else {
    		notify(); //Risveglio il thread se stava dormendo alle volte successive   	
    	} 	
   	}
    
    synchronized public void addElementToSequence(Number n){
    	
    	
    	if(!antiPrimes.contains(n)) {
    		System.out.println("La squenza non avevo il numero, lo aggiungo");
			antiPrimes.add(n);
    	}
		this.setChanged(); //Avviso l'interfaccia che si è modificato
		this.notifyObservers();
    	notify(); //Risveglio eventuali thread dormienti
    	
    	try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

    
    synchronized void checkIfFree(){
    	
    }
	
    
    
}
