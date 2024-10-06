package fr.eseo.e3.poo.projet.blox.modele.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Puits;

public class OPieceTest {
	
	@Test
	void testOPiece() {
		
		Coordonnees c = new Coordonnees(6, 5);
		Couleur co = Couleur.CYAN;
		
		OPiece op = new OPiece(c, co);
		
		List<Element> ae = new ArrayList<Element>();
		ae.add(new Element(6, 5, Couleur.CYAN));
		ae.add(new Element(6, 4, Couleur.CYAN));
		ae.add(new Element(7, 4, Couleur.CYAN));
		ae.add(new Element(7, 5, Couleur.CYAN));
		
		assertEquals(ae, op.getElements(), "Test methode getElements");
		assertEquals(Couleur.CYAN, op.getElements().get(0).getCouleur(), "Test couleur");
		assertEquals(c, op.getElements().get(0).getCoordonnees(), "Test coordonnees");
		
		assertEquals("OPiece :\n	(6, 5) - CYAN\n	(6, 4) - CYAN\n	(7, 4) - CYAN\n	(7, 5) - CYAN\n", 
				op.toString(), "Test methode toString");
		
	}
	
	@Test
	void testSetPosition() {
		Coordonnees c = new Coordonnees(6, 5);
		Couleur co = Couleur.CYAN;
		
		OPiece op = new OPiece(c, co);
		
		op.setPosition(15, 2);
		
		assertEquals(new Coordonnees(15, 2), op.getElements().get(0).getCoordonnees(), "Test methode setPosition");
	}
	
	@Test
	void testPuit() {
		Coordonnees c = new Coordonnees(6, 5);
		Couleur co = Couleur.CYAN;
		
		OPiece op = new OPiece(c, co);
		Puits pu = new Puits();
		
		op.setPuits(pu);
		
		assertEquals(pu, op.getPuits(), "La piece devrait etre sur le puits pu");
	}
	
	@Test
	void testDeplacerDeNOk() {
		Coordonnees c = new Coordonnees(6, 5);
		Couleur co = Couleur.CYAN;
		
		OPiece op = new OPiece(c, co);
		
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			op.deplacerDe(2, -3);
		}); 
		assertEquals("Erreur d'arguments.", exception.getMessage(), "Il devrait y avoir un message d'erreur");
		
	}
	
	@Test
	void testDeplacerDeOk() throws BloxException {
		Coordonnees c = new Coordonnees(6, 5);
		Couleur co = Couleur.CYAN;
		
		OPiece op = new OPiece(c, co);
		
		op.deplacerDe(1, 1);
		
		assertEquals(7, op.getElements().get(0).getCoordonnees().getAbscisse(), "L'abscisse du premier elements devrait etre 7");
		assertEquals(6, op.getElements().get(0).getCoordonnees().getOrdonnee(), "L'ordonnee du premier elements devrait etre 6");
		
		assertEquals(7, op.getElements().get(1).getCoordonnees().getAbscisse(), "L'abscisse du 2e elements devrait etre 7");
		assertEquals(5, op.getElements().get(1).getCoordonnees().getOrdonnee(), "L'ordonnee du 2e elements devrait etre 5");
		
		assertEquals(8, op.getElements().get(2).getCoordonnees().getAbscisse(), "L'abscisse du 3e elements devrait etre 8");
		assertEquals(5, op.getElements().get(2).getCoordonnees().getOrdonnee(), "L'ordonnee du 3e elements devrait etre 5");
		
		assertEquals(8, op.getElements().get(3).getCoordonnees().getAbscisse(), "L'abscisse du 4e elements devrait etre 8");
		assertEquals(6, op.getElements().get(3).getCoordonnees().getOrdonnee(), "L'ordonnee du 4e elements devrait etre 6");
	}
	
	@Test
	void testTourner() throws BloxException {
		Coordonnees c = new Coordonnees(6, 5);
		Couleur co = Couleur.CYAN;
		
		OPiece op = new OPiece(c, co);
		
		op.deplacerDe(1, 1);
		op.tourner(false);
		
		assertEquals(7, op.getElements().get(0).getCoordonnees().getAbscisse(), "L'abscisse du premier elements devrait etre 7");
		assertEquals(6, op.getElements().get(0).getCoordonnees().getOrdonnee(), "L'ordonnee du premier elements devrait etre 6");
		
		assertEquals(7, op.getElements().get(1).getCoordonnees().getAbscisse(), "L'abscisse du 2e elements devrait etre 7");
		assertEquals(5, op.getElements().get(1).getCoordonnees().getOrdonnee(), "L'ordonnee du 2e elements devrait etre 5");
		
		assertEquals(8, op.getElements().get(2).getCoordonnees().getAbscisse(), "L'abscisse du 3e elements devrait etre 8");
		assertEquals(5, op.getElements().get(2).getCoordonnees().getOrdonnee(), "L'ordonnee du 3e elements devrait etre 5");
		
		assertEquals(8, op.getElements().get(3).getCoordonnees().getAbscisse(), "L'abscisse du 4e elements devrait etre 8");
		assertEquals(6, op.getElements().get(3).getCoordonnees().getOrdonnee(), "L'ordonnee du 4e elements devrait etre 6");
	}
	
	@Test
	void testDeplacerNOkBas() {
		Coordonnees c = new Coordonnees(6, 20);
		Couleur co = Couleur.CYAN;
		
		OPiece op = new OPiece(c, co);
		Puits puits = new Puits();
		puits.setPieceSuivante(op);
		try {
			op.deplacerDe(0, 1);
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
		
		OPiece op = new OPiece(c, co);
		Puits puits = new Puits();
		puits.setPieceSuivante(op);
		
		try {
			op.deplacerDe(1, 1);
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
		
		OPiece op = new OPiece(c, co);
		Puits puits = new Puits();
		puits.setPieceSuivante(op);
		
		try {
			op.deplacerDe(-1, 1);
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
		
		OPiece op = new OPiece(c, co);
		Puits puits = new Puits(15, 20, 6, 4);
		puits.setPieceSuivante(op);
		
		for(int i = 0; i < puits.getLargeur(); i++) {
			for(int j = 0; j < puits.getProfondeur(); j++) {
				try {
					op.deplacerDe(1, 1);
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
		
		OPiece op = new OPiece(c, co);
		Puits puits = new Puits(15, 20, 6, 4);
		puits.setPieceSuivante(op);
		
		for(int i = 0; i < puits.getLargeur(); i++) {
			for(int j = 0; j < puits.getProfondeur(); j++) {
				op.tourner(true);
			}
		}
		
	}
}
