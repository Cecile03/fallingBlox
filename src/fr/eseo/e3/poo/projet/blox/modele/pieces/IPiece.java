package fr.eseo.e3.poo.projet.blox.modele.pieces;

import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;

public class IPiece extends Piece {

	/* Constructeur */
	public IPiece(Coordonnees c, Couleur co) {
		super(c, co);
		setElements(c, co);
	}

	/**
	 * Methode protégé qui remplit la liste elements de la pièce en forme de I avec
	 * quatre éléments
	 * 
	 * @param une coordonnees
	 * @param une couleur
	 */
	protected void setElements(Coordonnees c, Couleur co) {
		this.getElements().clear();
		Coordonnees haut = new Coordonnees(c.getAbscisse(), c.getOrdonnee() - 1);
		Coordonnees haut2 = new Coordonnees(c.getAbscisse(), c.getOrdonnee() - 2);
		Coordonnees bas = new Coordonnees(c.getAbscisse(), c.getOrdonnee() + 1);
		this.getElements().add(0, new Element(c, co));
		this.getElements().add(1, new Element(haut, co));
		this.getElements().add(2, new Element(haut2, co));
		this.getElements().add(3, new Element(bas, co));

	}

}
