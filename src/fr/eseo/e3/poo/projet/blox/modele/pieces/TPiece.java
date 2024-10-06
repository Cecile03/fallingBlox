package fr.eseo.e3.poo.projet.blox.modele.pieces;

import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;

public class TPiece extends Piece{

	/* Constructeur */
	public TPiece(Coordonnees c, Couleur co) {
		super(c, co);
		setElements(c, co);
	}

	/**
	 * Methode protégé qui remplit la liste elements de la pièce en forme de T avec
	 * quatre éléments
	 * 
	 * @param une coordonnees
	 * @param une couleur
	 */
	@Override
	protected void setElements(Coordonnees c, Couleur co) {
		this.getElements().clear();
		Coordonnees droite = new Coordonnees(c.getAbscisse() + 1, c.getOrdonnee());
		Coordonnees gauche = new Coordonnees(c.getAbscisse() - 1, c.getOrdonnee());
		Coordonnees bas = new Coordonnees(c.getAbscisse(), c.getOrdonnee() + 1);
		this.getElements().add(0, new Element(c, co));
		this.getElements().add(1, new Element(droite, co));
		this.getElements().add(2, new Element(gauche, co));
		this.getElements().add(3, new Element(bas, co));
	}
	
}
