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

class ZPieceTest {

	@Test
	void testZPiece() {

		Coordonnees c = new Coordonnees(6, 5);
		Couleur co = Couleur.CYAN;

		ZPiece zp = new ZPiece(c, co);

		List<Element> ae = new ArrayList<Element>();
		ae.add(new Element(6, 5, Couleur.CYAN));
		ae.add(new Element(6, 4, Couleur.CYAN));
		ae.add(new Element(5, 4, Couleur.CYAN));
		ae.add(new Element(7, 5, Couleur.CYAN));

		assertEquals(ae, zp.getElements(), "Test methode getElements");
		assertEquals(Couleur.CYAN, zp.getElements().get(0).getCouleur(), "Test couleur");
		assertEquals(c, zp.getElements().get(0).getCoordonnees(), "Test coordonnees");

		assertEquals("ZPiece :\n	(6, 5) - CYAN\n	(6, 4) - CYAN\n	(5, 4) - CYAN\n	(7, 5) - CYAN\n", zp.toString(),
				"Test methode toString");

	}

	@Test
	void testSetPosition() {
		Coordonnees c = new Coordonnees(6, 5);
		Couleur co = Couleur.CYAN;

		ZPiece zp = new ZPiece(c, co);

		zp.setPosition(15, 2);

		assertEquals(new Coordonnees(15, 2), zp.getElements().get(0).getCoordonnees(), "Test methode setPosition");
	}

	@Test
	void testPuit() {
		Coordonnees c = new Coordonnees(6, 5);
		Couleur co = Couleur.CYAN;

		ZPiece zp = new ZPiece(c, co);
		Puits pu = new Puits();

		zp.setPuits(pu);

		assertEquals(pu, zp.getPuits(), "La piece devrait etre sur le puits pu");
	}

	@Test
	void testDeplacerDeNOk() {
		Coordonnees c = new Coordonnees(6, 5);
		Couleur co = Couleur.CYAN;

		ZPiece zp = new ZPiece(c, co);

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			zp.deplacerDe(2, -3);
		});
		assertEquals("Erreur d'arguments.", exception.getMessage(), "Il devrait y avoir un message d'erreur");

	}

	@Test
	void testDeplacerDeOk() {
		Coordonnees c = new Coordonnees(6, 5);
		Couleur co = Couleur.CYAN;

		ZPiece zp = new ZPiece(c, co);

		try {
			zp.deplacerDe(1, 1);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BloxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(7, zp.getElements().get(0).getCoordonnees().getAbscisse(),
				"L'abscisse du premier elements devrait etre 7");
		assertEquals(6, zp.getElements().get(0).getCoordonnees().getOrdonnee(),
				"L'ordonnee du premier elements devrait etre 6");

		assertEquals(7, zp.getElements().get(1).getCoordonnees().getAbscisse(),
				"L'abscisse du 2e elements devrait etre 7");
		assertEquals(5, zp.getElements().get(1).getCoordonnees().getOrdonnee(),
				"L'ordonnee du 2e elements devrait etre 5");

		assertEquals(6, zp.getElements().get(2).getCoordonnees().getAbscisse(),
				"L'abscisse du 3e elements devrait etre 6");
		assertEquals(5, zp.getElements().get(2).getCoordonnees().getOrdonnee(),
				"L'ordonnee du 3e elements devrait etre 5");

		assertEquals(8, zp.getElements().get(3).getCoordonnees().getAbscisse(),
				"L'abscisse du 4e elements devrait etre 8");
		assertEquals(6, zp.getElements().get(3).getCoordonnees().getOrdonnee(),
				"L'ordonnee du 4e elements devrait etre 6");
	}

	@Test
	void testTournerSensHoraire() {
		Coordonnees c = new Coordonnees(6, 5);
		Couleur co = Couleur.CYAN;

		ZPiece zp = new ZPiece(c, co);

		try {
			zp.deplacerDe(1, 1);
			zp.tourner(true);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BloxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(7, zp.getElements().get(0).getCoordonnees().getAbscisse(),
				"L'abscisse du premier elements devrait etre 7");
		assertEquals(6, zp.getElements().get(0).getCoordonnees().getOrdonnee(),
				"L'ordonnee du premier elements devrait etre 6");

		assertEquals(8, zp.getElements().get(1).getCoordonnees().getAbscisse(),
				"L'abscisse du 2e elements devrait etre 8");
		assertEquals(6, zp.getElements().get(1).getCoordonnees().getOrdonnee(),
				"L'ordonnee du 2e elements devrait etre 6");

		assertEquals(8, zp.getElements().get(2).getCoordonnees().getAbscisse(),
				"L'abscisse du 3e elements devrait etre 8");
		assertEquals(5, zp.getElements().get(2).getCoordonnees().getOrdonnee(),
				"L'ordonnee du 3e elements devrait etre 5");

		assertEquals(7, zp.getElements().get(3).getCoordonnees().getAbscisse(),
				"L'abscisse du 4e elements devrait etre 7");
		assertEquals(7, zp.getElements().get(3).getCoordonnees().getOrdonnee(),
				"L'ordonnee du 4e elements devrait etre 7");
	}

	@Test
	void testTournerSensAntiHoraire() {
		Coordonnees c = new Coordonnees(6, 5);
		Couleur co = Couleur.CYAN;

		ZPiece zp = new ZPiece(c, co);

		try {
			zp.deplacerDe(1, 1);
			zp.tourner(false);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BloxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(7, zp.getElements().get(0).getCoordonnees().getAbscisse(),
				"L'abscisse du premier elements devrait etre 7");
		assertEquals(6, zp.getElements().get(0).getCoordonnees().getOrdonnee(),
				"L'ordonnee du premier elements devrait etre 6");

		assertEquals(6, zp.getElements().get(1).getCoordonnees().getAbscisse(),
				"L'abscisse du 2e elements devrait etre 6");
		assertEquals(6, zp.getElements().get(1).getCoordonnees().getOrdonnee(),
				"L'ordonnee du 2e elements devrait etre 6");

		assertEquals(6, zp.getElements().get(2).getCoordonnees().getAbscisse(),
				"L'abscisse du 3e elements devrait etre 6");
		assertEquals(7, zp.getElements().get(2).getCoordonnees().getOrdonnee(),
				"L'ordonnee du 3e elements devrait etre 7");

		assertEquals(7, zp.getElements().get(3).getCoordonnees().getAbscisse(),
				"L'abscisse du 4e elements devrait etre 7");
		assertEquals(5, zp.getElements().get(3).getCoordonnees().getOrdonnee(),
				"L'ordonnee du 4e elements devrait etre 5");
	}

	@Test
	void testDeplacerNOkBas() {
		Coordonnees c = new Coordonnees(6, 20);
		Couleur co = Couleur.CYAN;

		ZPiece zp = new ZPiece(c, co);
		Puits puits = new Puits();
		puits.setPieceSuivante(zp);

		try {
			zp.deplacerDe(0, 1);
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

		ZPiece zp = new ZPiece(c, co);
		Puits puits = new Puits();
		puits.setPieceSuivante(zp);

		try {
			zp.deplacerDe(1, 1);
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

		ZPiece zp = new ZPiece(c, co);
		Puits puits = new Puits();
		puits.setPieceSuivante(zp);

		try {
			zp.deplacerDe(-1, 1);
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

		ZPiece zp = new ZPiece(c, co);
		Puits puits = new Puits(15, 20, 6, 4);
		puits.setPieceSuivante(zp);

		for (int i = 0; i < puits.getLargeur(); i++) {
			for (int j = 0; j < puits.getProfondeur(); j++) {
				try {
					zp.deplacerDe(1, 1);
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

		ZPiece zp = new ZPiece(c, co);
		Puits puits = new Puits(15, 20, 6, 4);
		puits.setPieceSuivante(zp);

		for (int i = 0; i < puits.getLargeur(); i++) {
			for (int j = 0; j < puits.getProfondeur(); j++) {
				try {
					zp.tourner(true);
				} catch (BloxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
