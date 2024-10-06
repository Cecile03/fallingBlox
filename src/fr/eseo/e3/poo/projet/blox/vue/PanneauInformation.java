package fr.eseo.e3.poo.projet.blox.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import fr.eseo.e3.poo.projet.blox.FallingBloxVersion2;
import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

public class PanneauInformation extends JPanel implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	public static final int TAILLE_PAR_DEFAUT = 70;
	private Puits puits;
	private int taille;
	private VuePiece vuePiece;
	private FallingBloxVersion2 jeu;

	/* Constructeur */
	public PanneauInformation(Puits puits) {
		this.puits = puits;
		this.taille = PanneauInformation.TAILLE_PAR_DEFAUT;
		if (this.puits.getPieceSuivante() != null) {
			this.vuePiece = new VuePiece(this.puits.getPieceSuivante(), 10);
		}
		this.setPreferredSize(new Dimension(this.taille, this.taille));
		this.puits.addPropertyChangeListener(this);
	}
	
	public PanneauInformation(Puits puits, FallingBloxVersion2 jeu) {
		this(puits);
		this.jeu = jeu;

	}

	/**
	 * Affiche le prochain morceau de la pièce en appelant la méthode afficherPiece
	 * de VuePiece. Vérifie si la partie est terminée en appelant la méthode
	 * finDePartie de Tas. Si c'est le cas, crée une instance de PanneauGameOver et
	 * lance le jeu en appelant la méthode main.
	 * 
	 * @param un objet Graphics
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g.create();

		this.setBackground(Color.LIGHT_GRAY);
		this.vuePiece.afficherPiece(g2D);
		if (this.puits.getTas().getGameOver()) {
			PanneauGameOver gameOver;
			try {
				gameOver = new PanneauGameOver("Game Over", this.puits, this.jeu);
				gameOver.main(null);
			} catch (BloxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		g2D.dispose();
	}

	/**
	 * Méthode de l'interface PropertyChangeListener qui est appelée lorsqu'une
	 * propriété de l'objet Puits change. Si la propriété modifiée est
	 * MODIFICATION_PIECE_SUIVANTE, récupère le nouveau morceau de la pièce et crée
	 * une nouvelle instance de VuePiece pour l'afficher.
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
