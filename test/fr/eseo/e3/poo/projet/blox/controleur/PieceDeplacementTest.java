package fr.eseo.e3.poo.projet.blox.controleur;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

public class PieceDeplacementTest {

	public static void main(String [] args) {
		SwingUtilities.invokeLater(new Runnable () {
		@Override
			public void run() {
				PieceDeplacementTest test = new PieceDeplacementTest();
				try {
					test.testDeplacementVertical();
					test.testDeplacementHorizontal();
				}catch (BloxException e) {
					
				}
			}
		});
	}
	
	private void testDeplacementHorizontal() throws BloxException{
		JFrame j = new JFrame("Deplacement horizontal");
		Puits p = new Puits();
		VuePuits vp = new VuePuits(p);
		UsineDePiece.setMode(0);
		Piece piece1 = UsineDePiece.genererPiece();
		Piece piece2 = UsineDePiece.genererPiece();
		
		
		vp.getPuits().setPieceSuivante(piece1);
		vp.getPuits().setPieceSuivante(piece2);
		
		j.getContentPane().add(vp);
		j.pack();
		j.setLocationRelativeTo(null);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
		
	}
	
	private void testDeplacementVertical() throws BloxException{
		JFrame j = new JFrame("Deplacement horizontal");
		Puits p = new Puits();
		VuePuits vp = new VuePuits(p);
		UsineDePiece.setMode(0);
		
		Piece piece1 = UsineDePiece.genererPiece();
		Piece piece2 = UsineDePiece.genererPiece();
		
		
		vp.getPuits().setPieceSuivante(piece1);
		vp.getPuits().setPieceSuivante(piece2);
		//vp.getPuits().getPieceActuelle().setPosition(5, 5);
		
		j.getContentPane().add(vp);
		j.pack();
		j.setLocationRelativeTo(null);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vp.repaint();
		j.setVisible(true);
		
	}
}
