package fr.eseo.e3.poo.projet.blox.modele;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CoordonneesTest {

	Coordonnees c = new Coordonnees(15, 2);
	
	@Test
	void testConstructeur() {
		
		assertEquals(15, c.getAbscisse(), "Test de la methode getAbscisse");
		assertEquals(2, c.getOrdonnee(), "Test de la methode getOrdonnees");
		assertEquals("(15, 2)", c.toString(), "Test de la methode toString");
	}
	
	@Test 
	void testSet() {
		c.setAbscisse(3);
		c.setOrdonnee(6);
		
		assertEquals(3, c.getAbscisse(), "Test de la methode setAbscisse");
		assertEquals(6, c.getOrdonnee(), "Test de la methode setOrdonnee");
	}
	
	@Test
	void testEquals() {
		Coordonnees c1 = new Coordonnees(15, 2);
		Coordonnees c2 = new Coordonnees(15, 4);
		Coordonnees c3 = new Coordonnees(4, 2);
		Coordonnees c4 = new Coordonnees(1, 3);
		
		assertEquals(true, c.equals(c1), "Test de la methode equals : les deux objets sont identiques");
		assertEquals(false, c.equals(c2), "Test de la methode equals : abs identique mais pas ord");
		assertEquals(false, c.equals(c3), "Test de la methode equals : ord identique mais pas abs");
		assertEquals(false, c.equals(c4), "Test de la methode equals : Les deux objets sont diff");
	}

}
