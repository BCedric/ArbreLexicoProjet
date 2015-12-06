package ArbreLexicoProjet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public aspect Serialisation {

	declare parents : NoeudAbstrait implements Serializable;
	public void ArbreLexicographique.sauve(String nomFichier){
		
		try {
			FileOutputStream fichier = new FileOutputStream(nomFichier);
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(this.entree);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ArbreLexicographique.charge(String nomFichier) throws IOException, ClassNotFoundException{
		FileInputStream fichier = new FileInputStream(nomFichier); 
	    ObjectInputStream o =new ObjectInputStream(fichier); 
	    this.entree = (Noeud)o.readObject();
	    o.close();
	}

}
