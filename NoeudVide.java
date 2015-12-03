package ArbreLexicoProjet;

public class NoeudVide extends NoeudAbstrait {
	
	private static NoeudVide instance = new NoeudVide();

	private NoeudVide() {
		super(null);
	}

	public static NoeudVide getInstance() {
		return instance;
	}

	public boolean contient(String s) {
		return false;
	}

	public boolean prefixe(String s) {
		return false;
	}

	public int nbMots() {
		return 0;
	}

	public NoeudAbstrait ajout(String s) {
		NoeudAbstrait n = new Marque(instance);
		for (int i = s.length() - 1; i >= 0; i --)
			n = new Noeud(instance, n, s.charAt(i));
		return n;
	}

	public NoeudAbstrait suppr(String s) {
		throw new ModificationImpossibleException("suppression impossible : mot absent");
	}

	public String toString(String s) {
		return "";
	}

}
