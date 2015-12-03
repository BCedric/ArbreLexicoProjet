package ArbreLexicoProjet;

public class Noeud extends NoeudAbstrait {

	private char valeur;
	private NoeudAbstrait fils;

	public Noeud(NoeudAbstrait frere, NoeudAbstrait fils, char valeur) {
		super(frere);
		if (fils == null || fils instanceof NoeudVide)
			throw new ArbreLexicographiqueException();
		this.fils = fils;
		this.valeur = valeur;
	}

	public boolean contient(String s) {
		if (s.isEmpty())
			return false;
		char c = s.charAt(0);
		if (c == valeur)
			return fils.contient(s.substring(1));
		if (c < valeur)
			return false;
		return frere.contient(s);
	}

	public boolean prefixe(String s) {
		if (s.isEmpty())
			return true;
		char c = s.charAt(0);
		if (c == valeur)
			return fils.prefixe(s.substring(1));
		if (c < valeur)
			return false;
		return frere.prefixe(s);
	}

	public int nbMots() {
		return fils.nbMots() + frere.nbMots();
	}

	public NoeudAbstrait ajout(String s) {
		if (s.isEmpty())
			return new Marque(this);
		char c = s.charAt(0);
		if (c == valeur) {
			fils = fils.ajout(s.substring(1));
			return this;
		}
		if (c < valeur) {
			NoeudAbstrait n = NoeudVide.getInstance().ajout(s);
			n.frere = this;
			return n;
		}
		frere = frere.ajout(s);
		return this;
	}

	public NoeudAbstrait suppr(String s) {
		if (s.isEmpty())
			throw new ModificationImpossibleException("suppression impossible : mot absent");
		char c = s.charAt(0);
		if (c == valeur) {
			fils = fils.suppr(s.substring(1));
			if (fils instanceof NoeudVide)
				return frere;
			return this;
		}
		if (c < valeur) 
			throw new ModificationImpossibleException("suppression impossible : mot absent");
		frere = frere.suppr(s);
		return this;
	}

	public String toString(String prefixe) {
		return fils.toString(prefixe + valeur) + frere.toString(prefixe);
	}

}
