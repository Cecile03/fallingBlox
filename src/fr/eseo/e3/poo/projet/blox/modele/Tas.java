package fr.eseo.e3.poo.projet.blox.modele;

import java.util.Random;

import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

public class Tas {

	private Puits puits;
	private Element[][] elements;
	private int score;
	private boolean gameOver;

	/* Constructeur */
	public Tas(Puits puits) {
		this.puits = puits;
		this.score = 0;
		this.elements = new Element[this.puits.getProfondeur()][this.puits.getLargeur()];
		this.gameOver = false;
		this.construireTas(0, 0, new Random());
	}

	public Tas(Puits puits, int nbElements) {
		this(puits);
		this.construireTas(nbElements, (nbElements / this.puits.getLargeur()) + 1, new Random());
	}

	public Tas(Puits puits, int nbElements, int nbLignes) {
		this(puits);
		this.construireTas(nbElements, nbLignes, new Random());
	}

	public Tas(Puits puits, int nbElements, int nbLignes, Random rand) {
		this(puits);
		this.construireTas(nbElements, nbLignes, rand);
	}

	public Puits getPuits() {
		return this.puits;
	}

	public Element[][] getElements() {
		return this.elements;
	}

	public int getScore() {
		return this.score;
	}

	public boolean getGameOver() {
		return this.gameOver;
	}

	/**
	 * Méthode permettant d'obtenir le nombre d'éléments présents dans le tas
	 * 
	 * @return le nombre d'élément dans le tas
	 */
	public int getNombreElement() {
		int compteur = 0;
		for (int i = 0; i < this.getElements().length; i++) {
			for (int j = 0; j < this.getElements()[i].length; j++) {
				if (this.getElements()[i][j] != null) {
					compteur++;
				}
			}
		}
		return compteur;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * Méthode privée qui construit le tas avec un nombre d'éléments et de lignes
	 * spécifiés et un objet Random. Cette méthode place aléatoirement les éléments
	 * dans la matrice d'éléments du tas.
	 * 
	 * @param le nombre d'élément
	 * @param le nombre de ligne
	 * @param un random
	 */
	private void construireTas(int nbElements, int nbLignes, Random rand) {
		if (nbElements != 0 && nbElements >= this.puits.getLargeur() * nbLignes) {
			throw new IllegalArgumentException("Erreur d'arguments.");
		}
		if (nbLignes != 0 && nbLignes >= this.puits.getProfondeur()) {
			throw new IllegalArgumentException("Erreur d'arguments.");
		}
		int nbElementPlace = 0;
		while (nbElementPlace <= nbElements - 1) {
			int ordon = this.puits.getProfondeur() - (rand.nextInt(nbLignes) + 1);
			int absci = rand.nextInt(this.puits.getLargeur());
			if (this.elements[ordon][absci] == null) {
				int indiceCouleur = rand.nextInt(Couleur.values().length);
				Element element = new Element(new Coordonnees(absci, ordon), Couleur.values()[indiceCouleur]);
				this.elements[ordon][absci] = element;

				nbElementPlace++;
			}
		}
	}

	/**
	 * Ajoute les éléments d'une pièce dans la matrice d'éléments du tas.
	 * 
	 * @param une piece
	 * @throws BloxException
	 */
	public void ajouterElements(Piece piece) {
		try {
			for (int i = 0; i < piece.getElements().size(); i++) {
				this.elements[piece.getElements().get(i).getCoordonnees().getOrdonnee()][piece.getElements().get(i)
						.getCoordonnees().getAbscisse()] = piece.getElements().get(i);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			this.gameOver = true;
		}
	}

	/**
	 * Retire les lignes complètes de la matrice d'éléments du tas. Pour chaque
	 * ligne complète, cette méthode supprime tous les éléments de cette ligne,
	 * ajoute le score correspondant au score total du tas et descend les éléments
	 * de toutes les lignes au-dessus de la ligne supprimée en appelant la méthode
	 * descendreLigne.
	 */
	public void retirerLigne() {
		int compteur = 0;
		int nbrLigneSup = 0;
		for (int i = 0; i < this.getElements().length; i++) {
			for (int j = 0; j < this.getElements()[i].length; j++) {
				if (this.getElements()[i][j] != null) {
					compteur++;
				}
			}
			if (compteur == this.getPuits().getLargeur()) {
				for (int k = 0; k < this.getPuits().getLargeur(); k++) {
					this.getElements()[i][k] = null;
				}
				nbrLigneSup++;
				this.score += 100 * nbrLigneSup;
				this.descendreLigne(i);
			}
			compteur = 0;
		}
	}

	/**
	 * Méthode permettant de descendre les éléments de toutes les lignes au-dessus
	 * de la ligne spécifiée en paramètre.
	 * 
	 * @param la ligne à descendre
	 */
	public void descendreLigne(int ligneADescendre) {
		for (int i = ligneADescendre - 1; i >= 0; i--) {
			for (int j = 0; j < this.getElements()[i].length; j++) {
				if (this.getElements()[i][j] != null) {
					Element element = this.getElements()[i][j];
					element.getCoordonnees().setOrdonnee(element.getCoordonnees().getOrdonnee() + 1);
					this.getElements()[i][j] = null;
					this.getElements()[element.getCoordonnees().getOrdonnee()][element.getCoordonnees()
							.getAbscisse()] = element;
				}
			}
		}
	}

	/**
	 * Méthode permettant de savoir si la partie est terminée (c'est-à-dire si la
	 * première ligne de la matrice d'éléments est remplie)
	 * 
	 */
	public void finDePartie() {
		for (int i = 0; i < this.getElements()[0].length; i++) {
			if (this.getElements()[0][i] != null) {
				this.gameOver = true;
			}
		}
	}

}
