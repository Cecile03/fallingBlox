package fr.eseo.e3.poo.projet.blox.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

public class PanneauScore extends JPanel implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private Puits puits;
	private VuePiece vuePiece;

	/* Constructeur */
	public PanneauScore(Puits puits) {
		this.puits = puits;
		this.setPreferredSize(new Dimension(50, 50));
		puits.addPropertyChangeListener(this);
	}

	public Puits getPuits() {
		return this.puits;
	}

	public VuePiece getVuePiece() {
		return this.vuePiece;
	}

	/**
	 * Méthode qui dessine le score actuel sur le panneau en utilisant les
	 * graphiques 2D. Elle récupère le score actuel du Tas associé à l'objet Puits
	 * et l'affiche en tant que texte sur le panneau.
	 * 
	 * @param un objet Graphics
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g.create();

		g2D.setBackground(Color.LIGHT_GRAY);
		g2D.setFont(new Font("Britannic Bold", Font.ROMAN_BASELINE, 15));
		g2D.drawString("SCORE : " + this.puits.getTas().getScore(), 10, 30);
		g2D.dispose();

	}

	/**
	 * Méthode qui est appelée lorsque des propriétés de l'objet Puits sont
	 * modifiées. Si la propriété modifiée est MODIFICATION_PIECE_SUIVANTE, la
	 * méthode récupère la nouvelle pièce et crée un nouvel objet VuePiece avec
	 * cette pièce. Elle appelle ensuite la méthode repaint() pour redessiner le
	 * panneau.
	 * 
	 * @param un objet PropertyChangeEvent
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Puits.MODIFICATION_PIECE_SUIVANTE)) {
			Piece piece = (Piece) evt.getNewValue();
			this.vuePiece = new VuePiece(piece, 10);
			this.repaint();
		}
	}

}
