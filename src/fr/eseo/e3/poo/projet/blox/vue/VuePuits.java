package fr.eseo.e3.poo.projet.blox.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import fr.eseo.e3.poo.projet.blox.controleur.ChangementPiece;
import fr.eseo.e3.poo.projet.blox.controleur.Gravite;
import fr.eseo.e3.poo.projet.blox.controleur.Pause;
import fr.eseo.e3.poo.projet.blox.controleur.PieceDeplacement;
import fr.eseo.e3.poo.projet.blox.controleur.PieceRotation;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

public class VuePuits extends JPanel implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	public static final int TAILLE_PAR_DEFAUT = 15;
	private final VueTas vueTas;
	private Puits puits;
	private int taille;
	private VuePiece vuePiece;
	private ChangementPiece changePiece;
	private Pause pause;

	/* Constructeur */
	public VuePuits(Puits puits) {
		this(puits, VuePuits.TAILLE_PAR_DEFAUT);

	}

	public VuePuits(Puits puits, int taille) {
		this.puits = puits;
		this.taille = taille;
		this.setPreferredSize(
				new Dimension(this.taille * this.puits.getLargeur(), this.taille * this.puits.getProfondeur()));
		this.vuePiece = null;
		this.vueTas = new VueTas(this);
		this.changePiece = new ChangementPiece(this);
		this.pause = new Pause(this);

		setBackground(Color.WHITE);

		this.puits.addPropertyChangeListener(this);
		this.changePiece.addPropertyChangeListener(this);

		this.addMouseMotionListener(new PieceDeplacement(this));
		this.addMouseListener(new PieceDeplacement(this));
		this.addMouseWheelListener(new PieceDeplacement(this));
		this.addMouseListener(new PieceRotation(this));
		this.addKeyListener(new PieceDeplacement(this));
		this.addKeyListener(this.changePiece);
		this.addKeyListener(new PieceRotation(this));
		this.addKeyListener(this.pause);
		this.setFocusable(true);

		new Gravite(this);
	}

	public Puits getPuits() {
		return this.puits;
	}

	public int getTaille() {
		return this.taille;
	}

	public VuePiece getVuePiece() {
		return this.vuePiece;
	}

	public VueTas getVueTas() {
		return this.vueTas;
	}

	public Pause getPause() {
		return this.pause;
	}

	public void setPuits(Puits puits) {
		this.puits = puits;
		this.setPreferredSize(
				new Dimension(this.taille * this.puits.getLargeur(), this.taille * this.puits.getProfondeur()));

	}

	public void setTaille(int taille) {
		this.taille = taille;
		this.setPreferredSize(
				new Dimension(this.taille * this.puits.getLargeur(), this.taille * this.puits.getProfondeur()));

	}

	private void setVuePiece(VuePiece vuePiece) {
		this.vuePiece = vuePiece;
	}


	/**
	 * Méthode qui est appelée pour dessiner l'interface utilisateur de la classe.
	 * Cette méthode dessine le puits, les pièces tombées et la pièce actuelle.
	 * 
	 * @param un objet Graphics
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		/* appel vers super pour remplir le fond du JPanel */

		/*
		 * Le paramètre g est copie en utilisant la méthode copie() puis converti en
		 * instance de Graphics2D grâce à un transtypage (cast) explicite.
		 */
		Graphics2D g2D = (Graphics2D) g.create();
		/* Nous utiliserons l'instance de Graphics2D */

		/* Afficher un quadrillage */

		for (int i = 0; i < puits.getLargeur() * taille; i += this.taille) {
			for (int j = 0; j < puits.getProfondeur() * taille; j += this.taille) {
				g2D.setColor(Color.LIGHT_GRAY);
				g2D.drawRect(i, j, this.taille, this.taille);

				g2D.setColor(Color.WHITE);
				g2D.fillRect(i + 1, j + 1, this.taille - 1, this.taille - 1);

			}
		}
		this.vueTas.afficher(g2D);

		if (this.vuePiece != null) {
			this.vuePiece.afficherPiece(g2D);
		}

		/* Puis nous liberons la memoire */
		g2D.dispose();

	}

	/**
	 * Méthode qui est appelée lorsqu'un événement de changement de propriété est
	 * détecté. Elle est utilisée pour mettre à jour l'affichage de la vue en
	 * réponse aux événements de modification de la pièce actuelle et de changement
	 * de la pièce. Elle vérifie si la partie n'est pas finie, puis elle utilise la
	 * méthode getPropertyName de l'objet evt pour déterminer le type de
	 * l'événement. Si c'est un événement de modification de la pièce actuelle, elle
	 * met à jour la vue en créant une nouvelle instance de VuePiece avec la
	 * nouvelle pièce et en appelant la méthode repaint() pour redessiner la vue. Si
	 * c'est un événement de changement de la pièce, elle déplace la pièce actuelle
	 * vers la position de la pièce suivante, définit la nouvelle pièce suivante
	 * comme pièce actuelle et redessine la vue.
	 * 
	 * @param un objet PropertyChangeEvent
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (!this.puits.getTas().getGameOver() && this.pause.getEnCours()) {
			if (evt.getPropertyName().equals(Puits.MODIFICATION_PIECE_ACTUELLE)) {
				Piece piece = (Piece) evt.getNewValue();
				this.setVuePiece(new VuePiece(piece, this.getTaille()));
				this.repaint();
			}
			if (evt.getPropertyName().equals(ChangementPiece.MODIFICATION_CHANGE_PIECE)) {
				this.getPuits().getPieceActuelle().setPosition(
						this.getPuits().getPieceSuivante().getElements().get(0).getCoordonnees().getAbscisse(),
						this.getPuits().getPieceSuivante().getElements().get(0).getCoordonnees().getOrdonnee());
				this.getPuits().setPieceSuivante(this.getPuits().getPieceActuelle());
				this.repaint();
			}
		}
	}
}
