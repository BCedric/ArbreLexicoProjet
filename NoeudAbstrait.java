package ArbreLexicoProjet;

public abstract class NoeudAbstrait {
	protected NoeudAbstrait frere;

	public NoeudAbstrait(NoeudAbstrait frere) {
		if (frere == null && !(this instanceof NoeudVide))
			throw new ArbreLexicographiqueException("Utiliser une instance de NoeudVide à la place de null");
		this.frere = frere;
	}

	public abstract boolean contient(String s);

	public abstract boolean prefixe(String s);

	public abstract int nbMots(); // nombre d'éléments

	public abstract NoeudAbstrait ajout(String s);

	public abstract NoeudAbstrait suppr(String s);

	public abstract String toString(String s); // éléments séparés par \n

	public static void main(String[] args) {
//		NoeudAbstrait n = new NoeudVide();
//		n=n.ajout("FOR");
//		n=n.ajout("FOX");
//		n=n.ajout("FOG");
//		n=n.ajout("AXE");
//		//n=n.ajout("FOG");
//		System.out.println(n.nbMots());
//		System.out.println(n.toString(""));
//
	}

}
