/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssteluq3;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author MAV
 */
public class Ecriture {
    ModeleTable modelet = new ModeleTable();
    //afin de separer les cellules du JTable par des tab dans le fichier texte
    private static Vector separeTab(String string) {
        StringTokenizer tokenizer = new StringTokenizer(string, "\t", true);
        Vector row = new Vector();
        //Object[] array = row.toArray(new String[row.size()]); TEST convertir Vector to Array
        String elem = null;
        String last = null;
        while (tokenizer.hasMoreTokens()) {
            last = elem;
            elem = tokenizer.nextToken();
            if (!elem.equals("\t")) row.add(elem);
            else if (last.equals("\t")) row.add("");
            // reperer l'etat
            
        }
        if (elem.equals("\t")) row.add(""); // a la limite de mes competences en Java ca fonctionne mais pas certain de comprenre???

        return (row);
    }
     private static void ecrire(BufferedWriter out, Collection strings) throws IOException {
        Iterator it = strings.iterator();

        while (it.hasNext()) {
            String string = (String) it.next();
            if (string != null) out.write(string);
            if (it.hasNext()) out.write('\t');
        }
        out.newLine();
    }
//n'est pas utiliser, mais eventuellement ce serait bien de travailler les fils d'exporter les changements et de recuperer le fichier.
    public void chargeFichier(File file) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));

            // lire le nom des colonnes
            Vector premier = separeTab(in.readLine());
            ModeleRSS.colonnesNoms = premier;

            // chaque rangee une ligne
            String line;
            ModeleRSS.rangeDonne = new Vector<>();
            while ((line = in.readLine()) != null) {
                ModeleRSS.rangeDonne.add(separeTab(line));
            }

            in.close();

            // envoi message toute la table a changer
            modelet.fireTableStructureChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveToFile(File file) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));

            // ecrire le nom des collones
            ecrire(out, ModeleRSS.colonnesNoms);

            // ecrire toutes les donnes (les rangees)
            for (int i=0; i<ModeleRSS.rangeDonne.size(); i++) {
                ecrire(out, (Vector) ModeleRSS.rangeDonne.get(i));
            }

            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
