package fr.eseo.e3.poo.projet.blox.modele;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ElementTest {
	
	Coordonnees c = new Coordonnees(15, 2);
	Couleur co = Couleur.VIOLET;
	
	Element e1 = new Element(c);
	Element e2 = new Element(c.getAbscisse(), c.getOrdonnee());
	Element e3 = new Element(c, co);
	Element e4 = new Element(c.getAbscisse(), c.getOrdonnee(), co);
	
	@Test
	void testElement() throws BloxException {
		
		assertEquals(c, e1.getCoordonnees(), "Test methode getCoordonnees");
		assertEquals(c, e2.getCoordonnees(), "Test methode getCoordonnees");
		assertEquals(c, e2.getCoordonnees(), "Test methode getCoordonnees");
		assertEquals(c, e2.getCoordonnees(), "Test methode getCoordonnees");
		
		assertEquals(Couleur.ROUGE, e1.getCouleur(), "Test methode getCouleur");
		assertEquals(Couleur.ROUGE, e2.getCouleur(), "Test methode getCouleur");
		assertEquals(Couleur.VIOLET, e3.getCouleur(), "Test methode getCouleur");
		assertEquals(Couleur.VIOLET, e4.getCouleur(), "Test methode getCouleur");
		
		assertEquals("(15, 2) - VIOLET", e3.toString(), "Test methode toString");
		
		Coordonnees c1 = new Coordonnees(4, 6);
		Couleur co1 = Couleur.BLEU;
		
		e1.setCoordonnees(c1);
		e1.setCouleur(co1);
		
		assertEquals(c1, e1.getCoordonnees(), "Test methode setCoordonnees");
		assertEquals(co1, e1.getCouleur(), "Test methode setCouleur");
		
		Element e = new Element(c, co);
		
		assertEquals(true, e3.equals(e), "Test methode equals");
		assertEquals(false, e1.equals(e4), "Test methode equals");
	}
	
	@Test
	void testDeplacerDe() throws BloxException {
		e1.deplacerDe(5, 3);
		
		assertEquals(20, e1.getCoordonnees().getAbscisse(), "L'abscisse devrait etre de 20");
		assertEquals(5, e1.getCoordonnees().getOrdonnee(), "L'ordonnee devrait etre de 5");
		
	}
	
	@Test
	void testDeplacerDeNOkBas() throws BloxException {
		e1.deplacerDe(3, 20);
		
	}
	
	@Test
	void testDeplacerDeNoKDroite() throws BloxException {
		e1.deplacerDe(30, 3);
	}
	
	@Test
	void testDeplacerDeNoKGauche() throws BloxException {
		e1.deplacerDe(-3, 3);
		
	}
}
