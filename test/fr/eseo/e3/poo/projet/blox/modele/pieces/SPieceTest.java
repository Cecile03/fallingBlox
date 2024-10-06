package fr.eseo.e3.poo.projet.blox.modele.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Puits;

public class SPieceTest {

	@Test
	void testSPiece() {

		Coordonnees c = new Coordonnees(6, 5);
		Couleur co = Couleur.CYAN;

		SPiece sp = new SPiece(c, co);

		List<Element> ae = new ArrayList<Element>();
		ae.add(new Element(6, 5, Couleur.CYAN));
		ae.add(new Element(6, 4, Couleur.CYAN));
		ae.add(new Element(7, 4, Couleur.CYAN));
		ae.add(new Element(5, 5, Couleur.CYAN));

		assertEquals(ae, sp.getElements(), "Test methode getElements");
		assertEquals(Couleur.CYAN, sp.getElements().get(0).getCouleur(), "Test couleur");
		assertEquals(c, sp.getElements().get(0).getCoordonnees(), "Test coordonnees");

		assertEquals("SPiece :\n	(6, 5) - CYAN\n	(6, 4) - CYAN\n	(7, 4) - CYAN\n	(5, 5) - CYAN\n", sp.toString(),
				"Test methode toString");

	}

	@Test
	void testSetPosition() {
		Coordonnees c = new Coordonnees(6, 5);
		Couleur co = Couleur.CYAN;

		SPiece sp = new SPiece(c, co);

		sp.setPosition(15, 2);

		assertEquals(new Coordonnees(15, 2), sp.getElements().get(0).getCoordonnees(), "Test methode setPosition");
	}

	@Test
	void testPuit() {
		Coordonnees c = new Coordonnees(6, 5);
		Couleur co = Couleur.CYAN;

		SPiece sp = new SPiece(c, co);
		Puits pu = new Puits();

		sp.setPuits(pu);

		assertEquals(pu, sp.getPuits(), "La piece devrait etre sur le puits pu");
	}

	@Test
	void testDeplacerDeNOk() {
		Coordonnees c = new Coordonnees(6, 5);
		Couleur co = Couleur.CYAN;

		SPiece sp = new SPiece(c, co);

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			sp.deplacerDe(2, -3);
		});
		assertEquals("Erreur d'arguments.", exception.getMessage(), "Il devrait y avoir un message d'erreur");

	}

	@Test
	void testDeplacerDeOk() throws BloxException {
		Coordonnees c = new Coordonnees(6, 5);
		Couleur co = Couleur.CYAN;

		SPiece sp = new SPiece(c, co);

		sp.deplacerDe(1, 1);

		assertEquals(7, sp.getElements().get(0).getCoordonnees().getAbscisse(),
				"L'abscisse du premier elements devrait etre 7");
		assertEquals(6, sp.getElements().get(0).getCoordonnees().getOrdonnee(),
				"L'ordonnee du premier elements devrait etre 6");

		assertEquals(7, sp.getElements().get(1).getCoordonnees().getAbscisse(),
				"L'abscisse du 2e elements devrait etre 7");
		assertEquals(5, sp.getElements().get(1).getCoordonnees().getOrdonnee(),
				"L'ordonnee du 2e elements devrait etre 5");

		assertEquals(8, sp.getElements().get(2).getCoordonnees().getAbscisse(),
				"L'abscisse du 3e elements devrait etre 8");
		assertEquals(5, sp.getElements().get(2).getCoordonnees().getOrdonnee(),
				"L'ordonnee du 3e elements devrait etre 5");

		assertEquals(6, sp.getElements().get(3).getCoordonnees().getAbscisse(),
				"L'abscisse du 4e elements devrait etre 6");
		assertEquals(6, sp.getElements().get(3).getCoordonnees().getOrdonnee(),
				"L'ordonnee du 4e elements devrait etre 6");
	}

	@Test
	void testTourner() throws BloxException {
		Coordonnees c = new Coordonnees(6, 5);
		Couleur co = Couleur.CYAN;

		SPiece sp = new SPiece(c, co);

		sp.deplacerDe(1, 1);
		sp.tourner(false);

		assertEquals(7, sp.getElements().get(0).getCoordonnees().getAbscisse(),
				"L'abscisse du premier elements devrait etre 7");
		assertEquals(6, sp.getElements().get(0).getCoordonnees().getOrdonnee(),
				"L'ordonnee du premier elements devrait etre 6");

		assertEquals(6, sp.getElements().get(1).getCoordonnees().getAbscisse(),
				"L'abscisse du 2e elements devrait etre 6");
		assertEquals(6, sp.getElements().get(1).getCoordonnees().getOrdonnee(),
				"L'ordonnee du 2e elements devrait etre 6");

		assertEquals(6, sp.getElements().get(2).getCoordonnees().getAbscisse(),
				"L'abscisse du 3e elements devrait etre 6");
		assertEquals(5, sp.getElements().get(2).getCoordonnees().getOrdonnee(),
				"L'ordonnee du 3e elements devrait etre 5");

		assertEquals(7, sp.getElements().get(3).getCoordonnees().getAbscisse(),
				"L'abscisse du 4e elements devrait etre 7");
		assertEquals(7, sp.getElements().get(3).getCoordonnees().getOrdonnee(),
				"L'ordonnee du 4e elements devrait etre 7");
	}

	@Test
	void testDeplacerNOkBas() {
		Coordonnees c = new Coordonnees(6, 20);
		Couleur co = Couleur.CYAN;

		SPiece sp = new SPiece(c, co);
		Puits puits = new Puits();
		puits.setPieceSuivante(sp);
		try {
			sp.deplacerDe(0, 1);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BloxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void testDeplacerNOkdroite() {
		Coordonnees c = new Coordonnees(15, 5);
		Couleur co = Couleur.CYAN;

		SPiece sp = new SPiece(c, co);
		Puits puits = new Puits();
		puits.setPieceSuivante(sp);

		try {
			sp.deplacerDe(1, 1);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BloxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void testDeplacerNOkGauche() {
		Coordonnees c = new Coordonnees(0, 5);
		Couleur co = Couleur.CYAN;

		SPiece sp = new SPiece(c, co);
		Puits puits = new Puits();
		puits.setPieceSuivante(sp);

		try {
			sp.deplacerDe(-1, 1);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BloxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void testDeplacerNOkcollision() {
		Coordonnees c = new Coordonnees(6, 15);
		Couleur co = Couleur.CYAN;

		SPiece sp = new SPiece(c, co);
		Puits puits = new Puits(15, 20, 6, 4);
		puits.setPieceSuivante(sp);

		for (int i = 0; i < puits.getLargeur(); i++) {
			for (int j = 0; j < puits.getProfondeur(); j++) {
				try {
					sp.deplacerDe(1, 1);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BloxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Test
	void testTournerNOkCollision() {
		Coordonnees c = new Coordonnees(7, 15);
		Couleur co = Couleur.CYAN;

		SPiece sp = new SPiece(c, co);
		Puits puits = new Puits(15, 20, 6, 4);
		puits.setPieceSuivante(sp);

		for (int i = 0; i < puits.getLargeur(); i++) {
			for (int j = 0; j < puits.getProfondeur(); j++) {
				try {
					sp.tourner(true);
				} catch (BloxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
