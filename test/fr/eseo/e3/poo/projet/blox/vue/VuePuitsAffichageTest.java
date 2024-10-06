package fr.eseo.e3.poo.projet.blox.vue;

import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;

public class VuePuitsAffichageTest {
	
	
	public static void main(String [] args) {
		SwingUtilities.invokeLater(new Runnable () {
		@Override
			public void run() {
				VuePuitsAffichageTest test = new VuePuitsAffichageTest();
				test.testConstructeurPuits();
				test.testConstructeurPuitsTaille();
			}
		});
	}
	
	private void testConstructeurPuits() {
		JFrame j = new JFrame("Puits");
		VuePuits vp = new VuePuits(new Puits());
		
		UsineDePiece.setMode(0);
		vp.getPuits().setPieceSuivante(UsineDePiece.genererPiece());
		PropertyChangeEvent evt = new PropertyChangeEvent(vp, vp.getPuits().getPieceSuivante().getClass().getSimpleName(), 
				null, vp.getPuits().getPieceSuivante());
		vp.propertyChange(evt);
		
		j.getContentPane().add(vp);
		j.pack();
		j.setLocationRelativeTo(null);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
	}
	
	private void testConstructeurPuitsTaille() {
	    JFrame frame = new JFrame("Puits et taille");
	    VuePuits vp = new VuePuits(new Puits(10, 20, 5, 6), 30); // une largeur de 200 est passée en paramètre
	    
	    UsineDePiece.setMode(0);
	    vp.getPuits().setPieceSuivante(UsineDePiece.genererPiece());
		PropertyChangeEvent evt = new PropertyChangeEvent(vp, vp.getPuits().getPieceSuivante().getClass().getSimpleName(), 
				null, vp.getPuits().getPieceSuivante());
		
		
		vp.propertyChange(evt);
		
	    frame.getContentPane().add(vp);
	    frame.pack(); // ajuster la taille de la fenêtre selon les préférences de la vue
	    frame.setLocationRelativeTo(null); // positionner la fenêtre au centre de l'écran

	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // arrêter l'application quand la fenêtre est fermée

	    frame.setVisible(true); // afficher la fenêtre
	}

}
