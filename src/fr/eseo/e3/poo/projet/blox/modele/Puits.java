package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

public class Puits {

	public static final int LARGEUR_PAR_DEFAUT = 10;
	public static final int PROFONDEUR_PAR_DEFAUT = 20;

	public static final String MODIFICATION_PIECE_ACTUELLE = "pieceActuelle";
	public static final String MODIFICATION_PIECE_SUIVANTE = "pieceSuivante";

	private Piece pieceActuelle;
	private Piece pieceSuivante;

	private int profondeur;
	private int largeur;
	private Tas tas;

	private PropertyChangeSupport pcs;

	/* Constructeur */
	public Puits(int largeur, int profondeur) {
		if (profondeur < 15 || profondeur > 25 || largeur < 5 || largeur > 15) {
			throw new IllegalArgumentException("Erreur d'arguments.");
		}
		this.profondeur = profondeur;
		this.largeur = largeur;
		this.pcs = new PropertyChangeSupport(this);
		this.tas = new Tas(this);
	}

	public Puits() {
		this(LARGEUR_PAR_DEFAUT, PROFONDEUR_PAR_DEFAUT);
	}

	public Puits(int largeur, int profondeur, int nbElement, int nbLigne) {
		this(largeur, profondeur);
		this.tas = new Tas(this, nbElement, nbLigne, new Random());
	}

	public int getProfondeur() {
		return this.profondeur;
	}

	public int getLargeur() {
		return this.largeur;
	}

	public Piece getPieceActuelle() {
		return this.pieceActuelle;
	}

	public Piece getPieceSuivante() {
		return this.pieceSuivante;
	}

	public Tas getTas() {
		return this.tas;
	}

	public void setTas(Tas tas) {
		this.tas = tas;
	}

	public void setProfondeur(int pro) {
		if (pro < 15 || pro > 25) {
			throw new IllegalArgumentException("Erreur d'arguments.");
		}
		this.profondeur = pro;
	}

	public void setLargeur(int lar) {
		if (lar < 5 || lar > 15) {
			throw new IllegalArgumentException("Erreur d'arguments.");
		}
		this.largeur = lar;
	}

	/**
	 * Modifie la pièce suivante en la remplaçant par la pièce passée en paramètre.
	 * Si une pièce suivante existe déjà, elle devient la pièce actuelle et la
	 * nouvelle pièce devient la pièce suivante. Cette méthode utilise également
	 * l'objet PropertyChangeSupport pour notifier les auditeurs d'un changement de
	 * propriété.
	 * 
	 * @param une piece
	 */
	public void setPieceSuivante(Piece piece) {
		if (this.pieceSuivante != null) {
			Piece p = this.pieceActuelle;
			this.pieceActuelle = this.pieceSuivante;
			this.pieceActuelle.setPosition(this.largeur / 2, -4);
			this.pcs.firePropertyChange(Puits.MODIFICATION_PIECE_ACTUELLE, p, this.pieceActuelle);

		}
		Piece p = this.pieceSuivante;
		this.pieceSuivante = piece;
		this.pieceSuivante.setPuits(this);
		this.pcs.firePropertyChange(Puits.MODIFICATION_PIECE_SUIVANTE, p, this.pieceSuivante);

	}

	/**
	 * Cette méthode est appelée lorsqu'une pièce ne peut plus descendre car elle
	 * est en collision avec une autre pièce. La pièce actuelle est ajoutée au tas
	 * de pièces et une nouvelle pièce suivante est générée. La méthode
	 * retirerLigne() est également appelée pour supprimer les lignes complètes du
	 * tas.
	 */
	private void gererCollision() {
		this.tas.ajouterElements(pieceActuelle);
		this.setPieceSuivante(UsineDePiece.genererPiece());
		this.tas.retirerLigne();
		this.tas.finDePartie();
	}

	/**
	 * Déplace la pièce actuelle d'une ligne vers le bas. Si la pièce ne peut plus
	 * descendre, la méthode gererCollision() est appelée.
	 */
	public void gravite() {
		if (!this.getTas().getGameOver()) {
			try {
				pieceActuelle.deplacerDe(0, 1);
			} catch (BloxException e) {
				gererCollision();
				return;
			}
		}
	}

	/*
	 * Ajoute un auditeur pour les événements de changement de propriété.
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/*
	 * Supprime un auditeur pour les événements de changement de propriété.
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	/**
	 * Méthode permettant d'obtenir une chaîne de caractères décrivant l'état actuel
	 * du plateau, de la pièce actuelle et de la pièce suivante.
	 * 
	 * @return une chaîne de caractère du puits
	 */
	public String toString() {
		String chaine = "";
		chaine += "Puits : Dimension " + this.largeur + " x " + this.profondeur + "\n";
		if (this.pieceSuivante == null) {
			chaine += "Piece Actuelle : <aucune>\n";
			chaine += "Piece Suivante : <aucune>\n";
		} else if (this.pieceActuelle == null) {
			chaine += "Piece Actuelle : <aucune>\n";
			chaine += "Piece Suivante : " + this.pieceSuivante.toString();
		} else {
			chaine += "Piece Actuelle : " + this.pieceActuelle.toString();
			chaine += "Piece Suivante : " + this.pieceSuivante.toString();
		}
		return chaine;
	}

}
