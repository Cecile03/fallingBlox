package fr.eseo.e3.poo.projet.blox.controleur;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

public class GraviteTest {
	
	public static void main(String [] args) {
		SwingUtilities.invokeLater(new Runnable () {
		@Override
			public void run() {
				GraviteTest test = new GraviteTest();
				try {
					test.testGravite();
				}catch (BloxException e) {
					
				}
			}
		});
	}
	
	void testGravite() throws BloxException{
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

	@Test 
	void testGetPeriodicite() {
		Puits puits = new Puits();
		VuePuits vuePuits = new VuePuits(puits);
		Gravite gravite = new Gravite(vuePuits);
		
		assertEquals(5000, gravite.getPeriodicite(), "La periodicite devrait etre 5000");
		
	}
	
	@Test 
	void testSetPeriodicite() {
		Puits puits = new Puits();
		VuePuits vuePuits = new VuePuits(puits);
		Gravite gravite = new Gravite(vuePuits);
		
		gravite.setPeriodicite(2000);
		
		assertEquals(2000, gravite.getPeriodicite(), "La periodicite devrait etre 2000");
	}
}
