package fr.eseo.e3.poo.projet.blox.modele.pieces;

import java.util.List;
import java.util.ArrayList;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Puits;

public abstract class Piece {

	private List<Element> elements;
	private Puits puits;

	/* Constructeur */
	public Piece(Coordonnees c, Couleur co) {
		this.elements = new ArrayList<>();
		this.setElements(c, co);
	}

	public List<Element> getElements() {
		return this.elements;
	}

	public Puits getPuits() {
		return this.puits;
	}

	public void setPuits(Puits p) {
		this.puits = p;
	}

	/**
	 * Déplace la pièce à la coordonnée donnée.
	 * 
	 * @param une abscisse
	 * @param une ordonnée
	 */
	public void setPosition(int abs, int ord) {
		this.setElements(new Coordonnees(abs, ord), this.getElements().get(0).getCouleur());
	}

	/**
	 * Méthode abstraite qui sera implémentée dans les classes filles et qui
	 * permettra de définir les éléments de la pièce.
	 * 
	 * @param une coordonnée
	 * @param une couleur
	 */
	protected abstract void setElements(Coordonnees c, Couleur co);

	/**
	 * Déplace la pièce de deltaX et deltaY dans le puits. Une exception
	 * BloxException est levée si la pièce sort du puits ou entre en collision avec
	 * une autre pièce.
	 * 
	 * @param deltaX
	 * @param deltaY
	 * @throws BloxException
	 * @throws IllegalArgumentException
	 */
	public void deplacerDe(int deltaX, int deltaY) throws BloxException, IllegalArgumentException {
		if (deltaY < 0 || deltaX > 1 || deltaX < -1 || deltaY > 1) {
			throw new IllegalArgumentException("Erreur d'arguments.");
		}

		if (getPuits() != null) {

			if (this.sortiePuits(deltaX)) {
				throw new BloxException("Il y a sortie de puits", BloxException.BLOX_SORTIE_PUITS);
			}
			if (this.collision(deltaX, deltaY)) {
				throw new BloxException("il y a collision", BloxException.BLOX_COLLISION);
			}
		}
		for (int i = 0; i < this.getElements().size(); i++) {
			this.elements.get(i).deplacerDe(deltaX, deltaY);
		}

	}

	/**
	 * Tourne la pièce dans le sens horaire ou antihoraire. Une exception
	 * BloxException est levée si la pièce sort du puits ou entre en collision avec
	 * une autre pièce.
	 * 
	 * @param sensHoraire
	 * @throws BloxException
	 */
	public void tourner(boolean sensHoraire) throws BloxException {
		int[] newX = new int[getElements().size()];
		int[] newY = new int[getElements().size()];
		for (int i = 0; i < this.getElements().size(); i++) {

			int abs0 = this.getElements().get(0).getCoordonnees().getAbscisse();
			int ord0 = this.getElements().get(0).getCoordonnees().getOrdonnee();
			int x = this.getElements().get(i).getCoordonnees().getAbscisse();
			int y = this.getElements().get(i).getCoordonnees().getOrdonnee();
			int tx = x - abs0;
			int ty = y - ord0;

			if (sensHoraire) {
				if ((tx > 0 && ty > 0) || (tx < 0 && ty < 0)) {
					tx = -ty;
					x += (-ty + tx);
				} else if ((tx < 0 && ty > 0) || (tx > 0 && ty < 0)) {
					ty = tx;
					y += (ty + tx);
				} else if (tx == 0 && ty != 0) {
					tx = ty;
					x -= tx;
					y -= ty;
				} else if (ty == 0 && tx != 0) {
					ty = -tx;
					x -= tx;
					y -= ty;
				}

			} else {

				if ((tx > 0 && ty > 0) || (tx < 0 && ty < 0)) {
					ty = -tx;
					y += (-tx + ty);
				} else if ((tx < 0 && ty > 0) || (tx > 0 && ty < 0)) {
					tx = ty;
					x += (tx + ty);
				} else if (tx == 0 && ty != 0) {
					tx = -ty;
					x -= tx;
					y -= ty;

				} else if (ty == 0 && tx != 0) {
					ty = tx;
					x -= tx;
					y -= ty;

				}

			}

			if (this.getPuits() != null) {
				if (x >= this.getPuits().getLargeur() || x < 0) {
					throw new BloxException("La piece sort du puits", BloxException.BLOX_SORTIE_PUITS);
				}

				if (y >= this.getPuits().getProfondeur()) {
					throw new BloxException("La piece sort en bas", BloxException.BLOX_COLLISION);
				}

				if (this.getPuits().getTas().getElements()[y][x] != null) {
					throw new BloxException("La piece se superpose au tas ", BloxException.BLOX_COLLISION);
				}

			}
			newX[i] = x;
			newY[i] = y;
		}

		for (int i = 0; i < newX.length; i++) {
			this.getElements().get(i).setCoordonnees(new Coordonnees(newX[i], newY[i]));
		}
	}

	/**
	 * Vérifie s'il y a collision entre les éléments de la pièce et les éléments du
	 * puits à la position deltaX et deltaY.
	 * 
	 * @param deltaX
	 * @param deltaY
	 * @return un boolean
	 */
	private boolean collision(int deltaX, int deltaY) {
		for (int i = 0; i < this.getElements().size(); i++) {
			int x = this.getElements().get(i).getCoordonnees().getAbscisse() + deltaX;
			int y = this.getElements().get(i).getCoordonnees().getOrdonnee() + deltaY;
			if (y >= 0) {
				if (y >= this.getPuits().getProfondeur()) {
					return true;
				}
				if (this.getPuits().getTas() != null) {
					if (this.getPuits().getTas().getElements()[y][x] != null) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Vérifie si la pièce est sortie du puits en se déplaçant sur l'axe horizontal.
	 * 
	 * @param deltaX
	 * @return un boolean
	 */
	private boolean sortiePuits(int deltaX) {
		for (int i = 0; i < this.getElements().size(); i++) {
			int x = this.getElements().get(i).getCoordonnees().getAbscisse() + deltaX;
			if (x < 0 || x >= this.puits.getLargeur()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Méthode permettant d'obtenir une chaîne de caractères représentant la pièce.
	 * 
	 * @return La chaîne de caractères représentant la pièce.
	 */
	public String toString() {
		String chaine = this.getClass().getSimpleName() + " :\n";
		for (int i = 0; i < this.elements.size(); i++) {
			chaine += "	" + this.elements.get(i).toString() + "\n";
		}
		return chaine;
	}

}
