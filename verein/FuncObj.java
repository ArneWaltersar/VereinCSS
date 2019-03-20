package verein;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FuncObj {
	
	public static boolean debug = true;  
		
	
	public static ArrayList<Spielpaarung> shuffle(ArrayList<Verein> vereine) {
		ArrayList<Spielpaarung> liste = new ArrayList<Spielpaarung>();
		Collections.shuffle(vereine);
		vereine.forEach(Verein::reset);
		
		for (Verein v : vereine) {
			liste.addAll(FuncObj.paarung(vereine, v));
		}
		
		return liste;
	}
	
	public static ArrayList<Spielpaarung> paarung(ArrayList<Verein> vereine, Verein v) {
		ArrayList<Spielpaarung> liste = new ArrayList<Spielpaarung>();
		
		for (Verein gegner : vereine) {
			if (!v.equals(gegner)) {
				liste.add(new Spielpaarung(v, gegner));
			}			
		}
		
		return liste;		
	}
		
	
	public static boolean dateiloeschen(String filename) {
		String pfad = "C:\\WS\\filesys\\verein\\";
		File f1 = new File(pfad+filename);				
		
		return f1.delete();
	}
	
	public static File speichernVereine (String filename, Verein obj) {
		String type = "Verein";
		String pfad = "C:\\WS\\filesys\\verein\\";
		ArrayList<Verein> av = new ArrayList<Verein>();
		boolean lesenSkip = false;
		File f1 = new File(pfad+filename);
		
		if(!f1.exists()) {				
			try {
				f1.createNewFile();
				lesenSkip = true;
			} catch (IOException e) {
				if (debug)
					System.out.println(type+"-file kann nicht erstellt werden!");
			}				
		}			
		
		if(!lesenSkip) {
			av = FuncObj.einlesenVerein(filename);
		}
		av.add(obj);
				
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f1)));
			for ( Verein v : av) {
				oos.writeObject(v);
				oos.flush();
			}
			
			oos.close();
		} catch (IOException e) {
			if (debug)
				System.out.println("FuncObj: Fehler beim "+type+" speichern");
		} finally { 
			try {		
				oos.close();
			} catch (IOException e) {
			}
			
		}
		return f1;
	}
		
	public static ArrayList<Verein> einlesenVerein (String filename) {
		ArrayList<Verein> liste = new ArrayList<Verein>();
		String type = "Verein";
		String pfad = "C:\\WS\\filesys\\verein\\";
		File f1 = Func.erstellenDatei(pfad+filename);
		
		Verein v = null;
		int count = 0;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f1)));
			
			while(true) {                
				v = (Verein) ois.readObject();
	            
	            if (v!=null) {
	            	liste.add(v);	
	            }				
				count++;
	        }

		} catch (Exception e) {
			if (debug)
				System.out.println("FuncObj: Fehler beim einlesen von "+ type +" count: "+count);
		} finally { 
			try {	
				if (ois != null) {
					ois.close();					
				}
			} catch (IOException e) {
			}
			
		}
		return liste;
	}

	public static File speichernSpielpaarungen (String filename, Spielpaarung obj) {
		String type = "Spielpaarungen";
		String pfad = "C:\\WS\\filesys\\verein\\";
		ArrayList<Spielpaarung> av = new ArrayList<Spielpaarung>();
		boolean lesenSkip = false;
		File f1 = new File(pfad+filename);
		
		if(!f1.exists()) {				
			try {
				f1.createNewFile();
				lesenSkip = true;
			} catch (IOException e) {
				if (debug)
					System.out.println(type+"-file kann nicht erstellt werden!");
			}				
		}			
		
		if(!lesenSkip) {
			av = FuncObj.einlesenSpielpaarungen(filename);
		}
		av.add(obj);
				
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f1)));
			for ( Spielpaarung v : av) {
				oos.writeObject(v);
				oos.flush();
			}
			
			oos.close();
		} catch (IOException e) {
			if (debug)
				System.out.println("FuncObj: Fehler beim "+type+" speichern");
		} finally { 
			try {		
				oos.close();
			} catch (IOException e) {
			}
			
		}
		return f1;
	}
		
	public static ArrayList<Spielpaarung> einlesenSpielpaarungen (String filename) {
		ArrayList<Spielpaarung> liste = new ArrayList<Spielpaarung>();
		String type = "Spielpaarung";
		String pfad = "C:\\WS\\filesys\\verein\\";
		File f1 = Func.erstellenDatei(pfad+filename);
		
		Spielpaarung v = null;
		int count = 0;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f1)));
			
			while(true) {                
				v = (Spielpaarung) ois.readObject();
	            
	            if (v!=null) {
	            	liste.add(v);	
	            }				
				count++;
	        }

		} catch (Exception e) {
			if (debug)
				System.out.println("FuncObj: Fehler beim einlesen von "+ type +" count: "+count);
		} finally { 
			try {	
				if (ois != null) {
					ois.close();					
				}
			} catch (IOException e) {
			}
			
		}
		return liste;
	}
	
	public static ArrayList<Spielpaarung> normalise(ArrayList<Spielpaarung> ap, ArrayList<Verein> av) {
		ap.forEach(p -> {
			av.forEach(v -> {
				if(v.equals(p.getLinks())) {
					p.setLinks(v);
				} 
				if(v.equals(p.getRechts())) {
					p.setRechts(v);
				}
			});
		});
		return ap;
	}

	// Klasse Vereine Compareable implementiert
	public static void sortiereVereine(ArrayList<Verein> liste) {
		Collections.sort(liste, new Comparator<Verein>() {
			public int compare(Verein v1, Verein v2) {
				return Integer.valueOf(v2.getPunkte()).compareTo(v1.getPunkte());
			}
		});
	}

	
}
