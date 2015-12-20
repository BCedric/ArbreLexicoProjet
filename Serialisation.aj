package ArbreLexicoProjet;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public aspect Serialisation {

	declare parents : NoeudAbstrait implements Serializable;
	public void ArbreLexicographique.sauve(String nomFichier){
		
		File f = new File(nomFichier);
		
		try{
			FileWriter fw = new FileWriter(f);
			fw.write(this.toString());
			fw.close();
		} catch (IOException exception) {
		    System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
		}
		
	}
	
	public void ArbreLexicographique.charge(String nomFichier) throws IOException, ClassNotFoundException{
		try{
			BufferedReader buff = new BufferedReader(new FileReader(nomFichier));
			try {
				String line;
				
				while ((line = buff.readLine()) != null) {
					this.ajout(line);
				}
			} finally {
				buff.close();
			}
		} catch (IOException ioe) {	
			System.out.println("Erreur --" + ioe.toString());
		}
	}

}
