package fr.eseo.e3.poo.projet.blox.controleur;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

public class PauseTest {
	
	public static void main(String [] args) {
		SwingUtilities.invokeLater(new Runnable () {
		@Override
			public void run() {
				PauseTest test = new PauseTest();
				try {
					test.testPause();
				}catch (BloxException e) {
					
				}
			}
		});
	}
	
	void testPause() throws BloxException{
		JFrame j = new JFrame("Deplacement horizontal");
		Puits p = new Puits();
		VuePuits vp = new VuePuits(p);
		UsineDePiece.setMode(0);
		
		vp.getPuits().setPieceSuivante(UsineDePiece.genererPiece());
		vp.getPuits().setPieceSuivante(UsineDePiece.genererPiece());
		vp.getPuits().getPieceActuelle().setPosition(0, 5);
		
		j.getContentPane().add(vp);
		j.pack();
		j.setLocationRelativeTo(null);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vp.repaint();
		j.setVisible(true);
	}
}
