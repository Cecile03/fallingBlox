package fr.eseo.e3.poo.projet.blox.controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

public class ChangementPiece implements KeyListener {

	public static final String MODIFICATION_CHANGE_PIECE = "changePiece";

	private Puits puits;
	private VuePuits vuePuits;
	private PropertyChangeSupport pcs;

	/* Constructeur */
	public ChangementPiece(VuePuits vuePuits) {
		this.vuePuits = vuePuits;
		this.puits = vuePuits.getPuits();
		this.pcs = new PropertyChangeSupport(this);
	}

	public Puits getPuits() {
		return this.puits;
	}

	public VuePuits getVuePuits() {
		return this.vuePuits;
	}

	/**
	 * Cette fonction permet d'ajouter un PropertyChangeListener à la liste des
	 * écouteurs de changement de propriété (pcs).
	 * 
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Cette fonction permet de supprimer un PropertyChangeListener de la liste des
	 * écouteurs de changement de propriété (pcs).
	 * 
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Cette fonction est appelée lorsqu'une touche du clavier est enfoncée. Si la
	 * pièce actuelle du puits est null, la fonction se termine. Sinon, si la touche
	 * "C" est enfoncée, la fonction invoque firePropertyChange sur l'objet pcs
	 * Cette méthode permet de signaler à tous les écouteurs enregistrés sur l'objet
	 * pcs qu'un changement de pièce est survenu.
	 * 
	 * @param un objet KeyEvent
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (this.getPuits().getPieceActuelle() == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_C) {
			pcs.firePropertyChange(ChangementPiece.MODIFICATION_CHANGE_PIECE, this.getPuits().getPieceSuivante(),
					this.getPuits().getPieceActuelle());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
