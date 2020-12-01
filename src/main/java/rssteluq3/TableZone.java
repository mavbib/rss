/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssteluq3;

import java.util.*;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
//import java.awt.Insets; pas oublier si utilise gridbaglayout
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author MAV
 */
public class TableZone extends JFrame{
    //private JTable table;
    //private ModeleRss mod;
    
    JButton boutonRangee;
    JButton boutonSup;
    JButton boutonSauve;
    JComponent content;
    JTable table;
    private Ecriture ecriture;
    private ModeleTable modelet;
    private ModeleRSS modeler;
    
    
    public TableZone(String titre){
        super(titre);
        content = (JComponent)getContentPane();
	content.setLayout(new BorderLayout(6,6));
        
        modeler = new ModeleRSS();
        table = new JTable(modeler.rangeDonne, modeler.colonnesNoms);
        modelet = new ModeleTable();
        ecriture = new Ecriture();
        
        Dimension dim = getPreferredSize();
        dim.width = 700;
        dim.height = 600;
        setPreferredSize(dim);
        
        JScrollPane scrollpane = new JScrollPane(table);
	scrollpane.setPreferredSize(dim);
	content.add(scrollpane, BorderLayout.CENTER);		
		
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        content.add(panel, BorderLayout.WEST);
        
        /*
        *admettre que ce fichier chevauche la vue et le control.
        *cette ligne : table = new JTable(modeler.rangeDonne, modeler.colonnesNoms);
        *on devrait pouvoir passer ces donnees a un fichier controle et les recuperer dans la vue???
        *penser interface....
        */
        
        
        boutonRangee = new JButton("Ajouter une rangee");
        panel.add(boutonRangee);
        boutonRangee.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int i = modelet.ajoutRangee();
                        table.clearSelection();
                        table.addRowSelectionInterval(i, i);
                        table.setModel(modelet);
                        
                        
                        
                        System.out.println(i);//test
                                        
                                            
                    
                
                    }
                }
        );
        boutonSup = new JButton("Supprimer une rangee");
        panel.add(boutonSup);
        boutonSup.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        int rangSel = table.getSelectedRow();
                        if (rangSel!=-1) modelet.supprimeRangee(rangSel);
                        table.setModel(modelet);
                    }
                }
        );    
        boutonSauve = new JButton("Exporter toutes les rangees");
        panel.add(boutonSauve);
        boutonSauve.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        JFileChooser choix = new JFileChooser(".");
                        int status = choix.showSaveDialog(TableZone.this);
                        if (status == JFileChooser.APPROVE_OPTION) {
						ecriture.saveToFile(choix.getSelectedFile());
					}
                    }
                }
        );
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }


}
