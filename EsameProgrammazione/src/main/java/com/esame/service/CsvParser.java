package com.esame.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.esame.model.Record;

/** Rappresenta la classe statica che effettua il parsing del file csv
 * e restituisce i record salvati.
 * @author Marco Sebastianelli
 * @author Cristian Vitali
*/

public class CsvParser {

	String csvFile;
	static BufferedReader br = null;                                     
	static String line = "";
	static int row = 1;
	static ArrayList<Record> records = new ArrayList<>();
		

	/**
	 * Effettua il parsing del file CSV, salvando ogni riga come oggetto Record.
	 * @param csvFile il nome (compreso percorso cartella), del file in cui effettuare le operazioni.
	 * @return L' intero ArrayList di Record.
	*/
	
	public static ArrayList<Record> csvParsing(String csvFile){
		
		try {
			
			br = new BufferedReader(new FileReader(csvFile));
			br.readLine(); // leggi la prima riga e ignorala
			
			while ((line = br.readLine()) != null) {	
				
				try {
			
			        // usa virgola e spazio come separatore
			        String[] recordCorrente = line.split(";");	
			 			        
			        // crea un oggetto di tipo Record
			        // elimina eventuali spazi vuoti all'inizio delle Stringhe
			        Record oggettoRecord = new Record(
			        							recordCorrente[0].replaceAll("^\\s+",""), 
			        							recordCorrente[1].replaceAll("^\\s+",""),
			        							recordCorrente[2].replaceAll("^\\s+",""),
												Integer.parseInt(recordCorrente[3]),
												Integer.parseInt(recordCorrente[4]),
												Integer.parseInt(recordCorrente[5]),
												Integer.parseInt(recordCorrente[6]),
												Integer.parseInt(recordCorrente[7]),
												Integer.parseInt(recordCorrente[8])						
					);
			        
			        records.add(oggettoRecord);
			        
				} catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("Row #"+row+"  "+e.toString()
									  +" parametri inseriti. Aspettati 9");
				} catch(NumberFormatException e) {
					System.out.println("Row #"+row+"  "+e.toString());
				} 
				row++;
			}
	     
			try {
				br.close();
	        } catch (IOException e) {
	        	System.out.println(e.getClass().getCanonicalName()
	    	        	+"Errore in in com.example.demo.service.ParseCsv.java: Chiusura File");
	    	        }
	       
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getClass().getCanonicalName()
		    +"Errore in com.example.demo.service.ParseCsv.java: File non trovato");
		} catch (IOException e) {
			System.out.println(e.getClass().getCanonicalName()
			+"Errore in com.example.demo.service.ParseCsv.java: Operazione di I/O interrotte");
		}
		
		return records;
	}
}
