package ArbreLexicoProjet;

public class Marque extends NoeudAbstrait {

	public Marque(NoeudAbstrait frere) {
		super(frere);
	}

	public boolean contient(String s) {
		if (s.isEmpty())
			return true;
		return frere.contient(s);
	}

	public boolean prefixe(String s) {
		if (s.isEmpty())
			return true;
		return frere.prefixe(s);
	}

	public int nbMots() {
		return 1 + frere.nbMots();
	}

	public NoeudAbstrait ajout(String s) {
		if (s.isEmpty())
			throw new ModificationImpossibleException("ajout impossible : mot déjà présent");
		frere = frere.ajout(s);
		return this;
	}

	public NoeudAbstrait suppr(String s) {
		if (s.isEmpty())
			return frere;
		frere = frere.suppr(s);
		return this;
	}

	public String toString(String s) {
		return s + "\n" + frere.toString(s);
	}

}
