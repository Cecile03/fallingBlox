package fr.eseo.e3.poo.projet.blox.controleur;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.vue.PanneauInformation;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

public class ChangementPieceTest {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ChangementPieceTest test = new ChangementPieceTest();
				test.testChangementPieceTest();

			}
		});
	}

	public void testChangementPieceTest() {
		JFrame j = new JFrame("Changement Piece");
		Puits p = new Puits();
		VuePuits vp = new VuePuits(p);
		UsineDePiece.setMode(0);

		vp.getPuits().setPieceSuivante(UsineDePiece.genererPiece());
		vp.getPuits().setPieceSuivante(UsineDePiece.genererPiece());
		vp.getPuits().getPieceActuelle().setPosition(0, 5);
		
		PanneauInformation infoPanel = new PanneauInformation(vp.getPuits());
		
		j.add(infoPanel, BorderLayout.EAST);
		j.getContentPane().add(vp);
		j.pack();
		j.setLocationRelativeTo(null);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vp.repaint();
		j.setVisible(true);
	}

}
