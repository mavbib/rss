/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssteluq3;

import java.io.*;
import java.util.*;

import javax.swing.table.*;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author MAV
 */
public class ModeleTable extends AbstractTableModel {
    
    public ModeleTable(){
         super();
         }
    public String getColumnName(int col) {
        return (String) ModeleRSS.colonnesNoms.get(col);
    }
    
    public int getColumnCount() {
        return (ModeleRSS.colonnesNoms.size());
    }
    public int getRowCount() {
        return (ModeleRSS.rangeDonne.size());
    }
        public Object getValueAt(int rangC, int col) {
        Vector rangListe = ModeleRSS.rangeDonne.get(rangC);
        Object result = null;
        if (col < rangListe.size()) {
            result = rangListe.get(col);
            //test System.out.println(result);
        }
        return (result);
    }
        public boolean isCellEditable(int rangC, int col) {
        return true;
    }
            public void setValueAt(Object value, int rangC, int col) {
        Vector rangListe = ModeleRSS.rangeDonne.get(rangC);

       
        if (col >= rangListe.size()) {
            while (col >= rangListe.size()) rangListe.add(null);
        }

        rangListe.set(col, value);

        // envoyer modification
        fireTableCellUpdated(rangC, col);
    }
       // public void addColumn(String ) {
       // ModeleRss.colonnesNoms.add(nom);
       // fireTableStructureChanged();

    //}
        public int ajoutRangee() {
        // Une rangee vide
            Vector rang = new Vector();
            return ajoutRangee(rang);
    }
        public int ajoutRangee(Vector rang) {
            ModeleRSS.rangeDonne.add(rang);
            //test System.out.println(ModeleRss.rangeDonne.lastElement()); 
            fireTableRowsInserted(ModeleRSS.rangeDonne.size() - 1, ModeleRSS.rangeDonne.size() - 1);
            return (ModeleRSS.rangeDonne.size() - 1);
    }

            public void supprimeRangee(int row) {
        if (row == -1) return;

        ModeleRSS.rangeDonne.remove(row);
        fireTableRowsDeleted(row, row);
    }

}

