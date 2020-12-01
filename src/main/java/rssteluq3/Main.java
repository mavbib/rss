/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssteluq3;

import javax.swing.SwingUtilities;

/**
 *
 * @author MAV
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//test new ModeleRss();
                                new TableZone("Fil RSS de TELUQ");
			}
		});	
    }
    
}
