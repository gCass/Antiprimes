package antiprimes;
import ui.MainWindow;

import javax.swing.*;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;


/**
 * Class that setup the program.
 */
public class AntiPrimesApplication{
    private AntiPrimesSequence sequence;

    /**
     * Launch the program.
     */
    public static void main(String[] args) {
        AntiPrimesSequence sequence = new AntiPrimesSequence();
        	
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainWindow window = new MainWindow(sequence);
                
                window.setVisible(true);
            }
        });
    }
    
    

}
