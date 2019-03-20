package verein;
import java.io.Serializable;

public class Verein implements Serializable, Comparable<Verein> {

	private static final long serialVersionUID = -8116612173162975392L;
	
	private String name;
	private int spiele;
	private int punkte;
	private int tore;
	private int gegentore;
	

	private int platz;
	private int siege;
	private int unentschieden;
	private int niederlagen;
	private int tordiff;
	
	public Verein(String name) {
		super();
		this.name = name;
		this.spiele = 0;
		this.punkte = 0;
		this.tore = 0;
		this.gegentore = 0;
		

		this.platz = 0;
		this.siege = 0;
		this.unentschieden = 0;
		this.niederlagen = 0;
		this.tordiff = 0;
	}

	public static void reset(Verein v) {
		v.setPunkte(0);
		v.setSpiele(0);
		v.setTore(0);
		v.setGegentore(0);

		v.setPlatz(0);
		v.setSiege(0);
		v.setUnentschieden(0);
		v.setNiederlagen(0);
		v.setTordiff(0);
	}
	
	public void tordiff() {
		this.setTordiff(this.getTore()-this.getGegentore());
	}
	
	public int getPlatz() {
		return platz;
	}

	public void setPlatz(int platz) {
		this.platz = platz;
	}

	public int getSiege() {
		return siege;
	}

	public void setSiege(int siege) {
		this.siege = siege;
	}

	public int getUnentschieden() {
		return unentschieden;
	}

	public void setUnentschieden(int unentschieden) {
		this.unentschieden = unentschieden;
	}

	public int getNiederlagen() {
		return niederlagen;
	}

	public void setNiederlagen(int niederlagen) {
		this.niederlagen = niederlagen;
	}

	public int getTordiff() {
		return tordiff;
	}

	private void setTordiff(int tordiff) {
		this.tordiff = tordiff;
	}

	public String getName() {
		return name;
	}

	public int getSpiele() {
		return spiele;
	}

	public void setSpiele(int spiele) {
		this.spiele = spiele;
	}

	public int getPunkte() {
		return punkte;
	}

	public void setPunkte(int punkte) {
		this.punkte = punkte;
	}

	public int getTore() {
		return tore;
	}

	public void setTore(int tore) {
		this.tore = tore;
	}

	public int getGegentore() {
		return gegentore;
	}

	public void setGegentore(int gegentore) {
		this.gegentore = gegentore;
	}
		
	public String toString() {	
		return this.getName() + " Punkte: "+ this.getPunkte() +" Spiele: "+ this.getSpiele() +" Tore: "+ this.getTore() +" Gegentore: "+ this.getGegentore();
	}

	@Override
	public int compareTo(Verein arg0) {
		int punkte = Integer.valueOf(arg0.getPunkte()).compareTo(this.getPunkte());
		int tore = Integer.valueOf(arg0.getTore()).compareTo(this.getTore());
		int ggntore = Integer.valueOf(this.getGegentore()).compareTo(arg0.getGegentore());
		
		return punkte != 0?punkte:tore!=0?tore:ggntore!=0?ggntore:0;
	}
	
	@Override
    public boolean equals(Object o) {
		// If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Complex or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof Verein)) { 
            return false; 
        } 
          
        // typecast o to Complex so that we can compare data members  
        Verein c = (Verein) o; 
          
        // Compare the data members and return accordingly  
        return this.getName().toLowerCase().equals(c.getName().toLowerCase()); 
	}	
	
}
