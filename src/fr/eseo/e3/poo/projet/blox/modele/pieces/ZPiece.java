package fr.eseo.e3.poo.projet.blox.modele.pieces;

import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;

public class ZPiece extends Piece{

	/* Constructeur */
	public ZPiece(Coordonnees c, Couleur co) {
		super(c, co);
		setElements(c, co);
	}

	/**
	 * Methode protégé qui remplit la liste elements de la pièce en forme de Z avec
	 * quatre éléments
	 * 
	 * @param une coordonnees
	 * @param une couleur
	 */
	@Override
	protected void setElements(Coordonnees c, Couleur co) {
		this.getElements().clear();
		
		Coordonnees haut = new Coordonnees(c.getAbscisse(), c.getOrdonnee() - 1);
		Coordonnees hautGauche = new Coordonnees(c.getAbscisse() - 1, c.getOrdonnee() - 1);
		Coordonnees droite = new Coordonnees(c.getAbscisse() + 1, c.getOrdonnee());

		this.getElements().add(0, new Element(c, co));
		this.getElements().add(1, new Element(haut, co));
		this.getElements().add(2, new Element(hautGauche, co));
		this.getElements().add(3, new Element(droite, co));
		
	}
	
	
}
