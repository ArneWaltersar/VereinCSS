package verein;

import java.util.ArrayList;
import java.util.Collections;


public class Func_Controller {
	
	public final String vereinError = "Ungueltige Eingabe!";

	//fuer debug false
	private boolean live = true;

	private String dateip = "Paarungen2.txt";
	private String dateiv = "Vereine2.txt";
	
	private ArrayList<Spielpaarung> ap;
	private	ArrayList<Verein> av;
	
	public Func_Controller () {
		//Func_Controller.init();
		//this.live = false;
		
		ap = FuncObj.einlesenSpielpaarungen(dateip);
		av = FuncObj.einlesenVerein(dateiv);

		ap = FuncObj.normalise(ap, av);
	}
	
	private static void init() {
		
		FuncObj.dateiloeschen("Paarungen2.txt");
		FuncObj.dateiloeschen("Vereine2.txt");
		
		Verein v1 = new Verein ("HSV");
		Verein v2 = new Verein ("Bayern");
		Verein v3 = new Verein ("Schalke");
		Verein v4 = new Verein ("BVB");
		
		Spielpaarung  paar1 = new Spielpaarung(v1, v2);
		Spielpaarung  paar2 = new Spielpaarung(v2, v1);
		
		ArrayList<Spielpaarung> ap = new ArrayList<Spielpaarung>();
		ap.add(paar1);
		ap.add(paar2);
		
		for ( Spielpaarung p : ap) {
			FuncObj.speichernSpielpaarungen("Paarungen2.txt", p);
		}
		ArrayList<Verein> av = new ArrayList<Verein>();
		av.add(v1);
		av.add(v2);
		av.add(v3);
		av.add(v4);
		av.add(new Verein("trolo"));
	
	
		for ( Verein v : av) {
			FuncObj.speichernVereine("Vereine2.txt", v);
		}
		
	}

	public boolean addVerein(String text) {
		boolean change = false;
		if(!text.equals("") && !text.equals(this.vereinError)) {
    		Verein neuerVerein = new Verein(text);
    		
    		//gibts den schon
    		if(!this.av.contains(neuerVerein)) {
    			this.av.add(neuerVerein);
    			change = true;
    		}
    	}
		/*
		 //beim Beenden Speichern
		if(change && this.live) {
			//in datei schreiben
			FuncObj.dateiloeschen("Vereine2.txt");
			for ( Verein v : av) {
				FuncObj.speichernVereine("Vereine2.txt", v);
			}
		}
		*/
		return change;
	}

	public String getVereinsliste() {
		StringBuilder res = new StringBuilder();
		Collections.sort(this.av);
		this.av.forEach( v -> {res.append(v.toString()); res.append("\n");});
		
		return res.toString();
	}

	public String getSpielliste() {
		StringBuilder res = new StringBuilder();
		this.ap.forEach( v -> {res.append(v.toString()); res.append("\n");});
		
		return res.toString();
	}

	public boolean paarungenGenerieren() {
		boolean res = false;
		
		ap = FuncObj.shuffle(av);
		
		return res;		
	}
	
	public boolean ergebnisseGenerieren() {
		boolean res = false;
		
		ap.forEach(Spielpaarung::play);
		
		return res;		
	}

	public ArrayList<Spielpaarung> getAp() {
		return ap;
	}

	public ArrayList<Verein> getAv() {
		Collections.sort(this.av);
		return av;
	}

	public void close() {
		// Speichern		
		if(this.live) {
			//in datei schreiben
			FuncObj.dateiloeschen("Vereine2.txt");
			for ( Verein v : av) {
				FuncObj.speichernVereine("Vereine2.txt", v);
			}
			
			FuncObj.dateiloeschen("Paarungen2.txt");
			for ( Spielpaarung p : ap) {
				FuncObj.speichernSpielpaarungen("Paarungen2.txt", p);
			}
			System.out.println("Saved");
		} else {
			System.out.println("Not Saved");
		}
	}
	
}
