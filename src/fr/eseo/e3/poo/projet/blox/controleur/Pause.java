package fr.eseo.e3.poo.projet.blox.controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

public class Pause implements KeyListener {

	private VuePuits vuePuits;
	private boolean enCours;

	/* Constructeur */
	public Pause(VuePuits vuePuits) {
		this.vuePuits = vuePuits;
		this.enCours = true;

	}

	public boolean getEnCours() {
		return this.enCours;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Une méthode de l'interface KeyListener, qui est appelée lorsque l'utilisateur
	 * appuie sur une touche. Dans cette méthode, on vérifie si la touche appuyée
	 * est la touche "P", et si c'est le cas, on passe la variable enCours à false.
	 * Si la touche appuyée est la touche "O", et que la variable enCours est false,
	 * on la passe à true. Enfin, on appelle la méthode repaint() de l'instance de
	 * VuePuits pour redessiner le puits.
	 * 
	 * @param e
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_P) {
			if (enCours) {
				this.enCours = false;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_O) {
			if (!enCours) {
				this.enCours = true;
			}
		}
		this.vuePuits.repaint();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
