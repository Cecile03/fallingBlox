package fr.eseo.e3.poo.projet.blox.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

public class Gravite implements ActionListener {

	private Timer timer;
	private final VuePuits vuePuits;
	private final Puits puits;
	private int periodicite;
	private int oldScore = 0;

	/* Constructeur */
	public Gravite(VuePuits vuePuits) {
		this.vuePuits = vuePuits;
		this.puits = vuePuits.getPuits();
		this.periodicite = 5000;
		this.timer = new Timer(this.periodicite, this);
		this.timer.start();
	}

	public int getPeriodicite() {
		return this.periodicite;
	}

	public void setPeriodicite(int periodicite) {
		this.periodicite = periodicite;
	}

	/**
	 * La méthode appelée à chaque fois que le timer de la gravité s'active. Elle
	 * vérifie le score actuel du jeu et ajuste la fréquence de gravité en
	 * conséquence, en accélérant la chute des pièces en fonction du score. Elle
	 * appelle également la méthode gravite() du puits pour faire tomber la pièce
	 * actuelle d'un cran. La vue du puits est ensuite mise à jour avec repaint().
	 * 
	 * @param un objet ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.vuePuits.getPause().getEnCours() && !this.puits.getTas().getGameOver()) {
			if (this.puits.getTas().getScore() >= this.oldScore + 200) {
				this.timer.stop();
				this.setPeriodicite(this.periodicite / 2);
				this.timer = new Timer(this.periodicite, this);
				this.timer.start();
				this.puits.gravite();
				this.oldScore = this.puits.getTas().getScore();
			} else {
				this.puits.gravite();
			}
			this.vuePuits.repaint();
		}
	}

}
