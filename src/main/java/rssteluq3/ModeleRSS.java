/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssteluq3;
import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 *
 * @author MAV
 */
public class ModeleRSS{
	 public static Vector<Vector> rangeDonne = new Vector<Vector>();
    public static Vector<String> rangeUn = new Vector<String>();
    public static Vector<String> colonnesNoms = new Vector<String>();


    /**
     *Les titres des colonnes sont codes "en dure" ("hard coding into the program")
     * Ce n'est pas ideal. Peut passer pour un prototype. 
     * amelioration devait aller chercher titres des colonnes de la meme facon que donnees
     */
    public ModeleRSS() {
        colonnesNoms.addElement("Titre");
        colonnesNoms.addElement("Link");
        colonnesNoms.addElement("GUID");
        colonnesNoms.addElement("Date de publication");
        colonnesNoms.addElement("Description");
        /*reperage des elements sur la page RSS tous FAUX deviennent VRAI si present
         *et si VRAI les donnees sont ajouter a la JTable
         */

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler priseenmain;
            priseenmain = new DefaultHandler() {

                boolean itemRss = false;
                boolean titreRss = false;
                boolean link = false;
                boolean guid = false;
                boolean datePub = false;
                boolean desc = false;
                private int i;

                public void startElement(String uri, String localName, String
                        qName, Attributes attributes) throws SAXException {
                    //test System.out.println("premier element :" + qName);

                    if (qName.equalsIgnoreCase("item")) {
                        rangeUn = new Vector<String>();
                        itemRss = true;
                    }
                    if (qName.equalsIgnoreCase("TITLE")) {
                        titreRss = true;
                    }
                    if (qName.equalsIgnoreCase("LINK")) {
                        link = true;
                    }
                    if (qName.equalsIgnoreCase("GUID")) {
                        guid = true;
                    }
                    if (qName.equalsIgnoreCase("PUBDATE")) {
                        datePub = true;
                    }
                    if (qName.equalsIgnoreCase("DESCRIPTION")) {
                        desc = true;
                    }
                }

                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {
                    //test System.out.println("Element final :" + qName);
                    if ("item".equals(qName)) {
                        rangeDonne.addElement(rangeUn);
                    }
                    ;
                }

                @Override
                public void characters(char ch[], int start, int length) {
                    if (titreRss) {
                        String s = new String(ch, start, length);
                        rangeUn.addElement(s);
                        System.out.println("titre : " + new String(ch, start, length));
                        titreRss = false;
                    }
                    if (link) {
                        rangeUn.addElement(new String(ch, start, length));
                        System.out.println("Link : " + new String(ch, start, length));
                        link = false;
                    }
                    if (guid) {
                        rangeUn.addElement(new String(ch, start, length));
                        System.out.println("GUID : " + new String(ch, start, length));
                        guid = false;
                    }
                    if (datePub) {
                        rangeUn.addElement(new String(ch, start, length));
                        System.out.println("Pubdate : " + new String(ch, start, length));
                        datePub = false;
                    }
                    if (desc) {
                        rangeUn.addElement(new String(ch, start, length));
                        System.out.println("Description : " + new String(ch, start, length));
                        desc = false;
                    }
                   //test  System.out.println("longueur" + rangeUn.size());//test
                   //test System.out.println("nb de rangees " + rangeDonne.size());//test
                }
            };
            saxParser.parse("https://www.teluq.ca/site/infos/rss/communiques.php", priseenmain);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void SetColonnesNoms(Vector<String> colonnesNoms) {
        this.colonnesNoms = colonnesNoms;
    }


}