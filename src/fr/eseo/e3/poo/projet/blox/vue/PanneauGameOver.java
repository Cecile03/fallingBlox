package fr.eseo.e3.poo.projet.blox.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.eseo.e3.poo.projet.blox.FallingBloxVersion2;
import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;

public class PanneauGameOver extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private String message;
	private Puits puits;
	private JButton rejouerButton;
	private FallingBloxVersion2 jeu;
	private JFrame frame;

	/* Constructeur */
	public PanneauGameOver(String message, Puits puits, FallingBloxVersion2 jeu) throws BloxException {
		this.message = message;
		this.puits = puits;
		this.rejouerButton = new JButton("Rejouer");
		this.rejouerButton.addActionListener(this);
		this.frame = new JFrame(this.message);
		this.jeu = jeu;
	}
	
	public String getMessage() {
		return this.message;
	}

	public Puits getPuits() {
		return this.puits;
	}

	/**
	 * Méthode qui dessine le composant. Cette méthode est appelée automatiquement
	 * par Swing chaque fois que le composant doit être redessiné. Elle prend en
	 * argument un objet g de type Graphics qui permet de dessiner.
	 * 
	 * @param un objet Graphics
	 */
	@Override
	protected void paintComponent(Graphics g) {
		// Appeler la méthode parente pour dessiner le composant
		super.paintComponent(g);

		// Créer un objet Graphics2D à partir de l'objet Graphics passé en argument
		Graphics2D g2d = (Graphics2D) g.create();

		// Activer l'anti-aliasing pour améliorer la qualité de l'affichage
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Dessiner un rectangle noir en arrière-plan pour le message "Game Over"
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		// Dessiner le message "Game Over" en blanc et en gras
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Arial", Font.BOLD, 50));
		FontMetrics fm = g2d.getFontMetrics();
		int x = (getWidth() - fm.stringWidth(message)) / 2;
		int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
		g2d.drawString(message, x, y);

		g2d.setFont(new Font("Arial", Font.BOLD, 25));
		g2d.drawString("Votre score final est : " + this.getPuits().getTas().getScore(), 20, getHeight() - 30);

		// Libérer les ressources utilisées par l'objet Graphics2D
		g2d.dispose();
	}
	
	
	/**
	 * Cette méthode permet de réinitialiser le jeu
	 * 
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.jeu.getLecteurMP3().stop();
		frame.dispose();
		this.jeu.getFrame().dispose();
		try {
			this.jeu = new FallingBloxVersion2();
		} catch (BloxException | LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Cette méthode est appelée par le programme principal pour créer la fenêtre
	 * d'affichage. Elle crée une nouvelle instance de JFrame, définit sa taille et
	 * sa position, crée une nouvelle instance de PanneauGameOver, ajoute cette
	 * instance à la fenêtre et affiche la fenêtre.
	 * 
	 * @param args
	 * @throws BloxException 
	 */
	public void main(String[] args) throws BloxException {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);

		JPanel panel = new PanneauGameOver(this.message, this.puits, this.jeu);

		frame.add(panel);
		frame.add(rejouerButton, BorderLayout.SOUTH);
		frame.setVisible(true);

	}

	

}
