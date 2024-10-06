package fr.eseo.e3.poo.projet.blox.modele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fr.eseo.e3.poo.projet.blox.modele.pieces.IPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.OPiece;

public class PuitsTest {

	Puits p1 = new Puits();
	Puits p2 = new Puits(6, 18);
	Puits p3 = new Puits(10, 20, 4, 6);

	@Test
	void testConstructeur() {

		assertEquals(10, p1.getLargeur(), "La largeur devrait etre de 10");
		assertEquals(20, p1.getProfondeur(), "La profondeur devrait etre de 20");
		assertEquals(6, p2.getLargeur(), "La largeur devrait etre de 6");
		assertEquals(18, p2.getProfondeur(), "La profondeur devrait etre de 18");
		assertEquals(10, p3.getLargeur(), "La largeur devrait etre de 10");
		assertEquals(20, p3.getProfondeur(), "La profondeur devrait etre de 20");

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			new Puits(4, 22);
		});
		assertEquals("Erreur d'arguments.", exception.getMessage(), "Il devrait y avoir un message d'erreur");

	}

	@Test
	void testSet() {

		p1.setLargeur(7);
		p1.setProfondeur(23);

		assertEquals(7, p1.getLargeur(), "La la" + "rgeur devrait etre de 7");
		assertEquals(23, p1.getProfondeur(), "La profondeur devrait etre de 23");

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			p2.setLargeur(3);
		});
		assertEquals("Erreur d'arguments.", exception.getMessage(), "Il devrait y avoir un message d'erreur");

		Throwable exception1 = assertThrows(IllegalArgumentException.class, () -> {
			p2.setProfondeur(10);
		});
		assertEquals("Erreur d'arguments.", exception1.getMessage(), "Il devrait y avoir un message d'erreur");
	}

	@Test
	void testPiece() {
		Coordonnees c1 = new Coordonnees(3, 5);
		Coordonnees c2 = new Coordonnees(7, 8);
		Couleur co = Couleur.BLEU;
		OPiece pi1 = new OPiece(c1, co);
		OPiece pi2 = new OPiece(c2, co);

		p1.setPieceSuivante(pi1);

		assertEquals(pi1, p1.getPieceSuivante(), "La piece suivante devrait être pi1");

		p1.setPieceSuivante(pi2);

		assertEquals(pi1, p1.getPieceActuelle(), "La piece actuelle devrait etre pi1");
		assertEquals(pi2, p1.getPieceSuivante(), "La piece suivante devrait être pi2");

	}

	@Test
	void testToString() {
		Coordonnees c1 = new Coordonnees(7, 8);
		Couleur co = Couleur.ROUGE;
		IPiece pi = new IPiece(c1, co);

		Coordonnees c2 = new Coordonnees(6, 5);
		Couleur co2 = Couleur.CYAN;
		OPiece po = new OPiece(c2, co2);

		Puits pu = new Puits(10, 15);

		assertEquals("Puits : Dimension 10 x 15\nPiece Actuelle : <aucune>\nPiece Suivante : <aucune>\n", pu.toString(),
				"Test toString pas de piece");

		pu.setPieceSuivante(pi);

		assertEquals("Puits : Dimension 10 x 15\nPiece Actuelle : <aucune>\nPiece Suivante : " + pi.toString(),
				pu.toString(), "Test toString piece sui");

		pu.setPieceSuivante(po);

		assertEquals(
				"Puits : Dimension 10 x 15\nPiece Actuelle : " + pi.toString() + "Piece Suivante : " + po.toString(),
				pu.toString(), "Test toString piece act et piece sui");
	}
	
	@Test
	void testTas() {
		p3.setTas(new Tas(p3));
		assertNotNull(p3.getTas(), "Le puits devrait avoir un tas");
	}

}
