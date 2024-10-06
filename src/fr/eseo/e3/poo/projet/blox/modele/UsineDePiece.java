package fr.eseo.e3.poo.projet.blox.modele;

import java.util.Random;

import fr.eseo.e3.poo.projet.blox.modele.pieces.OPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.IPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.JPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.LPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.SPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.TPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.ZPiece;

public class UsineDePiece {

	public static final int ALEATOIRE_PIECE = 0;
	public static final int ALEATOIRE_COMPLET = 1;
	public static final int CYCLIC = 2;

	private static int mode = UsineDePiece.ALEATOIRE_PIECE;
	private static int numeroPiece = 0;

	/* Constructeur */
	private UsineDePiece() {
	}

	public static void setMode(int mode) {
		UsineDePiece.mode = mode;
	}

	/**
	 * Cette fonction génère une pièce en fonction du mode de génération défini par
	 * la variable statique mode. Elle retourne la pièce générée. Si le mode de
	 * génération est ALEATOIRE_PIECE, une pièce est générée aléatoirement. Si le
	 * mode de génération est ALEATOIRE_COMPLET, une pièce aléatoire est générée
	 * avec une couleur aléatoire. Si le mode de génération est CYCLIC, les pièces
	 * sont générées dans l'ordre défini par la variable numeroPiece, en bouclant
	 * sur les types de pièces disponibles.
	 * 
	 * @return une piece
	 */
	public static Piece genererPiece() {
		Piece piece;
		Random ran = new Random();
		int nbPiece = 7;
		int typePiece;
		Couleur typeCouleur = null;
		if (UsineDePiece.mode == UsineDePiece.ALEATOIRE_PIECE) {
			typePiece = ran.nextInt(nbPiece);
		} else if (UsineDePiece.mode == UsineDePiece.ALEATOIRE_COMPLET) {
			typePiece = ran.nextInt(nbPiece);
			typeCouleur = Couleur.values()[ran.nextInt(Couleur.values().length)];
		} else {
			typePiece = numeroPiece;
			numeroPiece = (numeroPiece + 1) % nbPiece;
		}

		switch (typePiece) {

		case 0: // OPiece
			if (typeCouleur == null) {
				typeCouleur = Couleur.ROUGE;
			}
			piece = new OPiece(new Coordonnees(2, 3), typeCouleur);
			break;

		case 1: // IPiece

			if (typeCouleur == null) {
				typeCouleur = Couleur.ORANGE;
			}
			piece = new IPiece(new Coordonnees(2, 3), typeCouleur);
			break;

		case 2: // TPiece
			if (typeCouleur == null) {
				typeCouleur = Couleur.BLEU;
			}
			piece = new TPiece(new Coordonnees(2, 3), typeCouleur);
			break;

		case 3: // LPiece
			if (typeCouleur == null) {
				typeCouleur = Couleur.VERT;
			}
			piece = new LPiece(new Coordonnees(2, 3), typeCouleur);
			break;

		case 4: // JPiece
			if (typeCouleur == null) {
				typeCouleur = Couleur.JAUNE;
			}
			piece = new JPiece(new Coordonnees(2, 3), typeCouleur);
			break;

		case 5: // ZPiece
			if (typeCouleur == null) {
				typeCouleur = Couleur.CYAN;
			}
			piece = new ZPiece(new Coordonnees(2, 3), typeCouleur);
			break;

		case 6: // SPiece
		default:
			if (typeCouleur == null) {
				typeCouleur = Couleur.VIOLET;
			}
			piece = new SPiece(new Coordonnees(2, 3), typeCouleur);
			break;

		}
		return piece;
	}
	
}
