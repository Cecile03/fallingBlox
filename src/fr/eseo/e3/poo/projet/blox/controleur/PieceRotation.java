package fr.eseo.e3.poo.projet.blox.controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

public class PieceRotation extends MouseAdapter implements MouseListener, KeyListener {

	private Puits puits;
	private VuePuits vuePuits;

	/* Constructeur */
	public PieceRotation(VuePuits vuePuits) {
		this.vuePuits = vuePuits;
		this.puits = this.vuePuits.getPuits();
	}

	/**
	 * Cette méthode est appelée lorsque l'utilisateur clique sur le Puits avec la
	 * souris. Elle vérifie quel bouton a été cliqué (gauche ou droit) et fait
	 * tourner la pièce actuelle dans la direction correspondante. Enfin, elle
	 * redessine la VuePuits pour afficher la pièce dans sa nouvelle position.
	 * 
	 * @param event
	 */
	@Override
	public void mouseClicked(MouseEvent event) {
		if (this.vuePuits.getPause().getEnCours()) {
			if (SwingUtilities.isLeftMouseButton(event)) {
				try {
					this.puits.getPieceActuelle().tourner(false);
				} catch (BloxException e) {
				}
			}
			if (SwingUtilities.isRightMouseButton(event)) {
				try {
					this.puits.getPieceActuelle().tourner(true);
				} catch (BloxException e) {
				}
			}
			this.vuePuits.repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Cette méthode est appelée lorsque l'utilisateur appuie sur une touche du
	 * clavier. Elle vérifie quelle touche a été appuyée (S ou Q) et fait tourner la
	 * pièce actuelle dans la direction correspondante. Enfin, elle redessine la
	 * VuePuits pour afficher la pièce dans sa nouvelle position.
	 * 
	 * @param un objet KeyEvent
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (this.vuePuits.getPause().getEnCours()) {
			if (e.getKeyCode() == KeyEvent.VK_S) {
				try {
					this.puits.getPieceActuelle().tourner(true);
				} catch (BloxException b) {
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				try {
					this.puits.getPieceActuelle().tourner(false);
				} catch (BloxException b) {
				}
			}
			this.vuePuits.repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
