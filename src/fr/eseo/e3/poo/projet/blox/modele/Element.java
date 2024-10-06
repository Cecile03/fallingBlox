package fr.eseo.e3.poo.projet.blox.modele;

import java.util.Objects;

public class Element {

	private Couleur couleur;
	private Coordonnees coordonnees;

	/* Constructeur */
	public Element(Coordonnees c) {
		this.coordonnees = c;
		this.couleur = Couleur.ROUGE;
	}

	public Element(int abs, int ord) {
		this.coordonnees = new Coordonnees(abs, ord);
		this.couleur = Couleur.ROUGE;
	}

	public Element(Coordonnees c, Couleur couleur) {
		this.coordonnees = c;
		this.couleur = couleur;
	}

	public Element(int abs, int ord, Couleur cou) {
		this.coordonnees = new Coordonnees(abs, ord);
		this.couleur = cou;
	}

	public Coordonnees getCoordonnees() {
		return this.coordonnees;
	}

	public Couleur getCouleur() {
		return this.couleur;
	}

	public void setCoordonnees(Coordonnees c) {
		this.coordonnees = c;
	}

	public void setCouleur(Couleur co) {
		this.couleur = co;
	}

	/**
	 * Déplace l'élément de deltaX et deltaY.
	 * 
	 * @param deltaX
	 * @param deltaY
	 */
	public void deplacerDe(int deltaX, int deltaY) {
		this.coordonnees.setAbscisse(this.coordonnees.getAbscisse() + deltaX);
		this.coordonnees.setOrdonnee(this.coordonnees.getOrdonnee() + deltaY);
	}

	/**
	 * Méthode permettant d'obtenir une chaîne de caractères représentant l'élément.
	 * 
	 * @return une chaine de caractère représentant un élément
	 */
	public String toString() {
		return this.coordonnees.toString() + " - " + this.couleur;
	}

	@Override
	public int hashCode() {
		return Objects.hash(coordonnees, couleur);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Element other = (Element) obj;
		return Objects.equals(coordonnees, other.coordonnees) && couleur == other.couleur;
	}

}
