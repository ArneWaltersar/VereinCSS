package verein;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

public class Func {
	
	public static File erstellenDatei(String a) {
		File file = new File(a);
		try {
			
			
			if(!file.exists()) {				
				file.createNewFile();				
			}			
			
		} catch (IOException e) {
			System.out.println("Fehler beim erstellen");
			e.printStackTrace();
		}
		return file;
	}
	
	public static boolean löschen(File file) {
		boolean io = false;
		if(file.exists()) {				
			file.delete();				
			io = true;
		}
		return io;
	}

	public static File erstellenVerzeichnis(String v) {		
		File unterV = new File(v);
		if(!unterV.exists()) {			
			unterV.mkdir();
		}
		return unterV;
	}

	public static File schreibe(String v, File f) {	
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(f));
			
			bw.write(v);
			
			bw.flush();
		} catch (IOException e) {
			System.out.println("Fehler beim schreiben");
			e.printStackTrace();
			
		}
		finally {
			try {
				bw.flush();
				bw.close();
			} catch (IOException e) {
			}
		}
		
		return f;
	}
	
	public static File lesen(File f) {
		return Func.lesen(f, 0);
	}

	public static File lesen(File f, int show) {	
		BufferedReader br = null;
		String lies = null;
		int count = 1;
		try {
			br = new BufferedReader(new FileReader(f));
			do {
				
				lies = br.readLine();
				
				if(lies != null) {
					if (show == 0 || count % show == 0)
					System.out.println(lies);
				}
				
				count++;
			} while (lies != null || lies != "\n\r" || lies != "\r" || lies != "\n");
				
		} catch (IOException e) {
			System.out.println("Fehler beim schreiben");
			e.printStackTrace();
			
		}
		finally {
			try {
				br.close();
			} catch (IOException e) {
			}
		}
		
		return f;
	}


	public static String auslesen(File f) {	
		BufferedReader br = null;
		String lies = null;
		StringBuffer inhalt = new StringBuffer();
		try {
			br = new BufferedReader(new FileReader(f));
			while ((lies = br.readLine()) !=null) {
				//System.out.println(lies);
				inhalt.append(lies);
				inhalt.append("\n");
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Fehler beim schreiben");
			e.printStackTrace();
			
		}
		finally {
			try {
				br.close();
			} catch (IOException e) {
			}
		}
		
		return inhalt.toString();
	}
	

	public static ArrayList<String> auslesenZeilen(File f) {	
		ArrayList<String> zeilen = new ArrayList<String>();
		BufferedReader br = null;
		String lies = null;
		try {
			br = new BufferedReader(new FileReader(f));
			while ((lies = br.readLine()) !=null) {
				//System.out.println(lies);
				zeilen.add(lies);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Fehler beim schreiben");
			e.printStackTrace();
			
		}
		finally {
			try {
				br.close();
			} catch (IOException e) {
			}
		}
		
		return zeilen;
	}
	


	public static HashMap<String, File> verzeichnisDurchsuchen(String v) {		
		
		HashMap<String, File> map = new HashMap<String, File>();
		
		Func.verzeichnisDurchsuchenRek(map, v);		
		
		for (String key : map.keySet()) {
			System.out.println(map.get(key).getName());
		}
		return map;
	}
	
	private static boolean verzeichnisDurchsuchenRek(HashMap<String, File> map, String v) {		
		File fileStart = new File(v);
		
		boolean endeErreicht = false;
		System.out.println(fileStart == null ? "Ja": "Nein "+fileStart.getName());
		for (String name : fileStart.list()) {
			File file = new File(v);
			if (file.isDirectory()) {
				map.put("Ordner", file);
				verzeichnisDurchsuchenRek(map, name);
			} else {
				map.put("Datei", file);
			}
			
		}	
		
		return endeErreicht;
	}
	
}
