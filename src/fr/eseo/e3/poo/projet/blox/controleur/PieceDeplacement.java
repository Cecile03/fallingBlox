package fr.eseo.e3.poo.projet.blox.controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

public class PieceDeplacement extends MouseAdapter implements MouseMotionListener, KeyListener {

	private Puits puits;
	private VuePuits vuePuits;
	private int colonne;

	/* Constructeur */
	public PieceDeplacement(VuePuits vuePuits) {
		this.vuePuits = vuePuits;
		this.puits = vuePuits.getPuits();
		this.colonne = -1;
	}

	/**
	 * Cette méthode est appelée lorsqu'un événement de mouvement de souris est
	 * détecté dans la vue. Elle récupère le numéro de colonne sous la souris et
	 * vérifie si la pièce actuelle du puits est définie. Si tel est le cas, elle
	 * déplace la pièce vers la nouvelle colonne. Elle gère également les exceptions
	 * qui peuvent être levées lors du déplacement et stocke le numéro de la
	 * nouvelle colonne.
	 * 
	 * @param event
	 */
	@Override
	public void mouseMoved(MouseEvent event) {
		if (this.vuePuits.getPause().getEnCours()) {
			// Vérifier s'il y a une pieceActuelle définie pour le Puits
			if (this.puits.getPieceActuelle() == null) {
				return;
			}

			// Récupérer le numéro de la colonne au-dessous de laquelle se trouve la souris
			int colonneSouris = event.getX() / this.vuePuits.getTaille();

			// Vérifier si la souris a changé de colonne
			if (this.colonne != -1) {

				if (colonneSouris != this.colonne) {
					try {
						// Déplacer la pièce actuelle vers la nouvelle colonne
						this.puits.getPieceActuelle().deplacerDe((colonneSouris > this.colonne ? 1 : -1), 0);
						this.vuePuits.repaint();
						// Stocker le numéro de la nouvelle colonne
						// this.colonne = colonneSouris;

					} catch (BloxException e) {
						// Si le déplacement lève une exception, ne rien faire

					}
				}
			}
			this.colonne = colonneSouris;
		}
	}

	/**
	 * Cette méthode est appelée lorsque la souris entre dans la vue. Elle récupère
	 * le numéro de la colonne sous la souris et le stocke dans la variable colonne.
	 * 
	 * @param event
	 */
	@Override
	public void mouseEntered(MouseEvent event) {
		this.colonne = event.getX() / this.vuePuits.getTaille();
	}

	/**
	 * Cette méthode est appelée lorsqu'un événement de rotation de la molette de la
	 * souris est détecté dans la vue. Elle vérifie si une pièce est actuellement
	 * sélectionnée et si l'événement correspond à une rotation vers le bas de la
	 * molette. Si c'est le cas, elle déplace la pièce d'une case vers le bas et
	 * gère les exceptions qui peuvent être levées lors du déplacement.
	 * 
	 * @param event
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent event) {
		if (this.vuePuits.getPause().getEnCours()) {
			if (this.puits.getPieceActuelle() == null) {
				return;
			}
			if (event.getWheelRotation() > 0) {
				try {
					this.puits.getPieceActuelle().deplacerDe(0, 1);
					this.vuePuits.repaint();
				} catch (BloxException e) {

				}

			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * Cette méthode est appelée lorsqu'une touche du clavier est enfoncée dans la
	 * vue. Elle vérifie si une touche est actuellement sélectionnée et déplace la
	 * pièce en fonction de la touche enfoncée (gauche, droite, bas ou descente
	 * directe) et gère les exceptions qui peuvent être levées lors du déplacement.
	 * 
	 * @param un objet KeyEvent
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (this.vuePuits.getPause().getEnCours()) {
			int keyCode = e.getKeyCode();
			try {
				if (keyCode == KeyEvent.VK_LEFT) {
					this.puits.getPieceActuelle().deplacerDe(-1, 0);
				} else if (keyCode == KeyEvent.VK_RIGHT) {
					this.puits.getPieceActuelle().deplacerDe(1, 0);
				} else if (keyCode == KeyEvent.VK_DOWN) {
					this.puits.getPieceActuelle().deplacerDe(0, 1);
				} else if (keyCode == KeyEvent.VK_SPACE) {
					for (int i = -4; i < this.puits.getProfondeur(); i++) {
						this.puits.getPieceActuelle().deplacerDe(0, 1);
					}
				}
			} catch (BloxException b) {

			}
			this.vuePuits.repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}