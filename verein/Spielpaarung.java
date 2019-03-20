package verein;
import java.io.Serializable;

public class Spielpaarung implements Serializable {

	private static final long serialVersionUID = -3668967549720183383L;
	
	private Verein links;
	private Verein rechts;
	
	private Integer ergebnisLinks = null;
	private Integer ergebnisRechts = null;
	
	private int finish = 0;
	private int sortierung;	

	public Spielpaarung(Verein links, Verein rechts) {
		super();
		this.links = links;
		this.rechts = rechts;
	}
	
	public static void play(Spielpaarung p) {
		if(p.finish == 0) {
			p.ausspielen();
		}
	}
	
	public void ausspielen() { 
		int toreLinks = (int) (Math.random() * 10);
		int toreRechts =  (int) (Math.random() * 10);
		this.setAusspielen(toreLinks, toreRechts);
	}
	
	public void ausspielen(int toreVereinLinks, int toreVereinRechts) { 
		this.setAusspielen(toreVereinLinks, toreVereinRechts);
	}
	
	private void setAusspielen(int toreLinks, int toreRechts) {	
		this.setErgebnisLinks(toreLinks);
		this.setErgebnisRechts(toreRechts);
		
		// Punkte
		if (toreLinks > toreRechts) {
			// Verein links gewinnt
			this.links.setPunkte(this.links.getPunkte()+3);
			this.links.setSpiele(this.links.getSpiele()+1);
			
			this.rechts.setSpiele(this.rechts.getSpiele()+1);

			this.links.setSiege(this.links.getSiege()+1);
			this.rechts.setNiederlagen(this.rechts.getNiederlagen()+1);
		} else if (toreLinks < toreRechts) {
			//Verein rechts gewinnt
			this.rechts.setPunkte(this.rechts.getPunkte()+3);
			this.rechts.setSpiele(this.rechts.getSpiele()+1);
			
			this.links.setSpiele(this.links.getSpiele()+1);

			this.rechts.setSiege(this.rechts.getSiege()+1);
			this.links.setNiederlagen(this.links.getNiederlagen()+1);
		} else {
			//unentschieden
			this.rechts.setPunkte(this.rechts.getPunkte()+1);
			this.rechts.setSpiele(this.rechts.getSpiele()+1);
			
			this.links.setPunkte(this.links.getPunkte()+1);
			this.links.setSpiele(this.links.getSpiele()+1);			

			this.links.setUnentschieden(this.links.getUnentschieden()+1);
			this.rechts.setUnentschieden(this.rechts.getUnentschieden()+1);
		}
		this.links.setTore(this.links.getTore()+toreLinks);
		this.links.setGegentore(this.links.getGegentore()+toreRechts);		

		this.rechts.setTore(this.rechts.getTore()+toreRechts);
		this.rechts.setGegentore(this.rechts.getGegentore()+toreLinks);
		
		//ToreDiff
		this.links.tordiff();
		this.rechts.tordiff();		
		
		this.setFinish(1);
	}

	public int getFinish() {
		return finish;
	}

	private void setFinish(int finish) {
		this.finish = finish;
	}

	public Verein getLinks() {
		return links;
	}
	
	public void setLinks(Verein v) {
		this.links = v;
	}

	public Verein getRechts() {
		return rechts;
	}
	
	public void setRechts(Verein v) {
		this.rechts = v;
	}

	public int getSortierung() {
		return sortierung;
	}
	

	public Integer getErgebnisLinks() {
		return ergebnisLinks;
	}

	public void setErgebnisLinks(int ergebnisLinks) {
		this.ergebnisLinks = ergebnisLinks;
	}

	public Integer getErgebnisRechts() {
		return ergebnisRechts;
	}

	public void setErgebnisRechts(int ergebnisRechts) {
		this.ergebnisRechts = ergebnisRechts;
	}
	
	public String toString() {
		if (this.getErgebnisLinks() != null) {
			return this.getLinks().getName() + " "+ this.getErgebnisLinks() + ":"+ this.getErgebnisRechts() + " "+ this.getRechts().getName();
		} else {
			return this.getLinks().getName() + " --:-- "+ this.getRechts().getName();
		}		
	}
	
}
