package ArbreLexicoProjet;

public class ArbreLexicographique {
	NoeudAbstrait entree;

	public ArbreLexicographique() {
		entree = NoeudVide.getInstance();
	}

	public boolean contient(String s) {
		return entree.contient(s);
	}

	public boolean prefixe(String s) {
		return entree.prefixe(s);
	}

	public int nbMots() {
		return entree.nbMots();
	}

	public boolean ajout(String s) {
		try {
			entree = entree.ajout(s);
		} catch (ModificationImpossibleException e) {
			return false;
		}
		return true;
	}

	public boolean suppr(String s) {
		try {
			entree = entree.suppr(s);
		} catch (ModificationImpossibleException e) {
			return false;
		}
		return true;
	}

	public String toString() {
		return entree.toString("");
	}

	public boolean estVide() {
		return entree instanceof NoeudVide;
	}

	public static void main(String[] args) {
		ArbreLexicographique a = new ArbreLexicographique();
		try {
			a.charge("coucou");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(0);
//		System.out.println(a.ajout("QUOI"));
//		System.out.println(a.ajout("QUI"));
//		System.out.println(a.ajout("QUE"));
//		System.out.println(a.ajout("QUEBEC"));
//		System.out.println(a.ajout("QUOI"));
		System.out.print(a);		
		System.out.println(a.nbMots());
		a.sauve("coucou");

	}

}
