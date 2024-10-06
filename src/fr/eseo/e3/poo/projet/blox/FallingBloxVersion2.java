package fr.eseo.e3.poo.projet.blox;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.Tas;
import fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.vue.LecteurMP3;
import fr.eseo.e3.poo.projet.blox.vue.PanneauInformation;
import fr.eseo.e3.poo.projet.blox.vue.PanneauScore;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

public class FallingBloxVersion2 {

	private JFrame frame;
	private LecteurMP3 lecteur;

	/* Construteur */
	public FallingBloxVersion2()
			throws BloxException, LineUnavailableException, UnsupportedAudioFileException, IOException {
		this.frame = new JFrame("Falling Blox");
		this.lecteur = new LecteurMP3("12.wav");
		lecteur.play();
		fallingBlox();

	}

	public FallingBloxVersion2(int startingElements)
			throws BloxException, LineUnavailableException, UnsupportedAudioFileException, IOException {
		this.frame = new JFrame("Falling Blox");
		this.lecteur = new LecteurMP3("12.wav");
		lecteur.play();
		fallingBloxElement(startingElements);
	}

	public FallingBloxVersion2(int startingElements, int numLines)
			throws BloxException, LineUnavailableException, UnsupportedAudioFileException, IOException {
		this.frame = new JFrame("Falling Blox");
		this.lecteur = new LecteurMP3("12.wav");
		lecteur.play();
		fallingBloxElementLigne(startingElements, numLines);
	}

	public JFrame getFrame() {
		return this.frame;
	}

	public LecteurMP3 getLecteurMP3() {
		return this.lecteur;
	}

	/*
	 * Cette méthode initialise une partie de Falling Blox avec un puits, un tas et
	 * un panneau d'informations. Elle crée une fenêtre JFrame, ajoute les
	 * composants à la fenêtre, puis affiche la fenêtre à l'écran.
	 */
	public void fallingBlox() {

		// Ajout des composants à la fenêtre
		Puits puits = new Puits();
		VuePuits vuePuits = new VuePuits(puits);
		UsineDePiece.setMode(0);
		vuePuits.getPuits().setPieceSuivante(UsineDePiece.genererPiece());
		vuePuits.getPuits().setPieceSuivante(UsineDePiece.genererPiece());
		PanneauInformation infoPanel = new PanneauInformation(vuePuits.getPuits(), this);
		PanneauScore infoScore = new PanneauScore(vuePuits.getPuits());

		// Initialisation de la fenêtre

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(vuePuits, BorderLayout.CENTER);
		frame.add(infoPanel, BorderLayout.EAST);
		frame.add(infoScore, BorderLayout.SOUTH);

		// Mise en forme et affichage de la fenêtre
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	/**
	 * Cette méthode est similaire à fallingBlox(), mais elle ajoute également un
	 * tas avec un nombre initial d'éléments spécifié par l'utilisateur.
	 * 
	 * @param nombre initial d'éléments
	 */
	public void fallingBloxElement(int startingElements) throws BloxException {

		// Ajout des composants à la fenêtre
		Puits puits = new Puits();
		VuePuits vuePuits = new VuePuits(puits);
		Tas tas = new Tas(vuePuits.getPuits(), startingElements);
		vuePuits.getPuits().setTas(tas);
		UsineDePiece.setMode(0);
		vuePuits.getPuits().setPieceSuivante(UsineDePiece.genererPiece());
		vuePuits.getPuits().setPieceSuivante(UsineDePiece.genererPiece());
		PanneauInformation infoPanel = new PanneauInformation(vuePuits.getPuits(), this);
		PanneauScore infoScore = new PanneauScore(vuePuits.getPuits());

		// Initialisation de la fenêtre
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(vuePuits, BorderLayout.CENTER);
		frame.add(infoPanel, BorderLayout.EAST);
		frame.add(infoScore, BorderLayout.SOUTH);

		// Mise en forme et affichage de la fenêtre
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	/**
	 * Cette méthode est similaire à fallingBloxElement(), mais elle crée un puits
	 * avec un nombre de lignes spécifié par l'utilisateur.
	 * 
	 * @param nombre initiale d'éléments
	 * @param nombre de ligne
	 */
	public void fallingBloxElementLigne(int startingElements, int numLines) throws BloxException {

		// Ajout des composants à la fenêtre
		Puits puits = new Puits(Puits.LARGEUR_PAR_DEFAUT, Puits.PROFONDEUR_PAR_DEFAUT, startingElements, numLines);
		VuePuits vuePuits = new VuePuits(puits);
		UsineDePiece.setMode(0);
		vuePuits.getPuits().setPieceSuivante(UsineDePiece.genererPiece());
		vuePuits.getPuits().setPieceSuivante(UsineDePiece.genererPiece());
		PanneauInformation infoPanel = new PanneauInformation(vuePuits.getPuits(), this);
		PanneauScore infoScore = new PanneauScore(vuePuits.getPuits());

		// Initialisation de la fenêtre
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(vuePuits, BorderLayout.CENTER);
		frame.add(infoPanel, BorderLayout.EAST);
		frame.add(infoScore, BorderLayout.SOUTH);

		// Mise en forme et affichage de la fenêtre
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	/**
	 * Cette méthode est la méthode principale de l'application Falling Blox. Elle
	 * crée une instance de la classe FallingBloxVersion2 avec différents paramètres
	 * en fonction des arguments passés en ligne de commande. Elle utilise également
	 * la méthode SwingUtilities.invokeLater() pour lancer l'application sur le
	 * thread d'interface utilisateur Swing.
	 * 
	 * @param tableau de chaine de caractère
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					if (args.length == 0) {
						new FallingBloxVersion2();
					} else if (args.length == 1) {
						new FallingBloxVersion2(Integer.parseInt(args[0]));
					} else if (args.length == 2) {
						new FallingBloxVersion2(Integer.parseInt(args[1]));
					}
				} catch (BloxException | LineUnavailableException | UnsupportedAudioFileException | IOException e) {
				}
			}
		});
	}
}
