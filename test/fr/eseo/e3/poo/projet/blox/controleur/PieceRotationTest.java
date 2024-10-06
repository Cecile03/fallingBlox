package fr.eseo.e3.poo.projet.blox.controleur;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;


public class PieceRotationTest {
	
	public static void main(String [] args) {
		SwingUtilities.invokeLater(new Runnable () {
		@Override
			public void run() {
				PieceRotationTest test = new PieceRotationTest();
				try {
					test.testRotation();
				} catch (BloxException e) {
				}
			}
		});
	}
	
	
	void testRotation() throws BloxException{
		JFrame j = new JFrame("Rotation");
		VuePuits vp = new VuePuits(new Puits());
		
		UsineDePiece.setMode(0);
		vp.getPuits().setPieceSuivante(UsineDePiece.genererPiece());
		vp.getPuits().setPieceSuivante(UsineDePiece.genererPiece());
		vp.getPuits().getPieceActuelle().setPosition(5, 5);
		
		vp.repaint();
		
		j.getContentPane().add(vp);
		j.pack();
		j.setLocationRelativeTo(null);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
	}
}
