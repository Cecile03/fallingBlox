package fr.eseo.e3.poo.projet.blox.vue;

import java.awt.Color;
import java.awt.Graphics2D;

import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

public class VuePiece {

	public static final double MULTIPLIER_TEINTE = 0.3;

	private int taille;
	private final Piece piece;

	/* Constructeur */
	public VuePiece(Piece piece, int taille) {
		this.piece = piece;
		this.taille = taille;
	}

	/**
	 * Une méthode qui prend en entrée une couleur et retourne une nouvelle couleur
	 * "teintée" en appliquant un coefficient de teinte. Le coefficient de teinte
	 * est défini par la constante statique MULTIPLIER_TEINTE de la classe VuePiece.
	 * 
	 * @param une couleur
	 * @return une couleur
	 */
	public Color teinte(Color couleur) {
		int red = couleur.getRed();
		int green = couleur.getGreen();
		int blue = couleur.getBlue();

		red = (int) Math.min(red + (255 - red) * VuePiece.MULTIPLIER_TEINTE, 255);
		green = (int) Math.min(green + (255 - green) * VuePiece.MULTIPLIER_TEINTE, 255);
		blue = (int) Math.min(blue + (255 - blue) * VuePiece.MULTIPLIER_TEINTE, 255);

		return new Color(red, green, blue);
	}

	/**
	 * Une méthode qui affiche graphiquement la pièce en utilisant un objet
	 * Graphics2D passé en paramètre. Cette méthode boucle sur les éléments de la
	 * pièce, récupère les coordonnées de chaque élément, et dessine un rectangle en
	 * utilisant la couleur de l'élément. Le premier élément est dessiné avec une
	 * teinte, et les autres éléments sont dessinés avec leur couleur d'origine. La
	 * taille du rectangle est déterminée par la taille de pixel passée au
	 * constructeur de l'objet VuePiece.
	 * 
	 * @param un objet Graphics2D
	 */
	public void afficherPiece(Graphics2D g2D) {
		Couleur c = this.piece.getElements().get(0).getCouleur();
		for (int i = 0; i < this.piece.getElements().size(); i++) {
			int x = this.piece.getElements().get(i).getCoordonnees().getAbscisse() * this.taille;
			int y = this.piece.getElements().get(i).getCoordonnees().getOrdonnee() * this.taille;
			if (i == 0) {
				g2D.setColor(this.teinte(c.getCouleurPourAffichage()));
				g2D.fill3DRect(x, y, this.taille, this.taille, true);
			} else {
				g2D.setColor(c.getCouleurPourAffichage());
				g2D.fill3DRect(x, y, this.taille, this.taille, true);
			}
		}
	}
	


}
