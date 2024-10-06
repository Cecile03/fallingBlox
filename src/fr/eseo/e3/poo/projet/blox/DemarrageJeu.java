package fr.eseo.e3.poo.projet.blox;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;

public class DemarrageJeu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	/* Constructeur */
	public DemarrageJeu() {
		// créer la fenêtre
		setTitle("Tetris Cécile TESSIER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);

		// ajouter l'image de fond
		ImageIcon imageIcon = new ImageIcon("tetris.jpeg");
		Image image = imageIcon.getImage();
		int newWidth = 800;
		int newHeight = 600;

		// redimensionner l'image
		Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImage);
		this.getContentPane().add(new JLabel(resizedIcon));

		// créer le bouton "Jouer"
		JButton boutonJouer = new JButton("Jouer");
		boutonJouer.setFont(new Font("Arial", Font.BOLD, 24));
		boutonJouer.addActionListener(this);

		// ajouter le bouton "Jouer" à la fenêtre
		this.getContentPane().add(boutonJouer, BorderLayout.SOUTH);

		// rendre la fenêtre visible
		setVisible(true);
	}

	/**
	 * Cette méthode est appelée lorsque l'utilisateur clique sur le bouton "Jouer".
	 * Elle crée une nouvelle instance de la classe FallingBloxVersion2. Si une
	 * erreur se produit lors de la création de l'instance, elle affiche la pile
	 * d'erreurs. Elle cache ensuite la fenêtre de démarrage
	 * 
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			new FallingBloxVersion2();
		} catch (BloxException | LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setVisible(false); // cacher la fenêtre de démarrage
	}

	/**
	 * Cette méthode est le point d'entrée du programme. Elle crée une nouvelle
	 * instance de la classe DemarrageJeu, qui est la fenêtre de démarrage du jeu
	 * Tetris. Lorsque cette instance est créée, le constructeur est exécuté et la
	 * fenêtre de démarrage est affichée.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new DemarrageJeu();
	}

}
