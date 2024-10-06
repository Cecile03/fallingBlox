package fr.eseo.e3.poo.projet.blox.modele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.jupiter.api.Test;

import fr.eseo.e3.poo.projet.blox.modele.pieces.IPiece;

public class TasTest {
	
	Puits p1 = new Puits();
	
	@Test
	void testConstructeurPuits() {
		Tas tas = new Tas(p1);
		
		assertEquals(20, tas.getElements().length, "La longueur des elements du tas devrait être de 0");
		assertEquals(p1, tas.getPuits(), "Le tas devrait avoir le puits p1");
		assertNotNull(tas.getElements(), "Il devrait y avoir des elements");
	}
	
	@Test
	void testConstructeurPuitsInt() {
		Tas tas = new Tas(p1, 4);
		
		assertEquals(p1, tas.getPuits(), "Le tas devrait avoir le puits p1");
		assertEquals(20, tas.getElements().length, "La longueur des elements du tas devrait etre de 10");
		assertNotNull(tas.getElements(), "Il devrait y avoir des elements");
	}
	
	@Test
	void testConstructeurPuitsIntInt() {
		Tas tas = new Tas(p1, 4, 5);
		
		assertEquals(p1, tas.getPuits(), "Le tas devrait avoir le puits p1");
		assertEquals(20, tas.getElements().length, "La longueur des elements du tas devrait etre de 10");
		assertNotNull(tas.getElements(), "Il devrait y avoir des elements");
	}
	
	@Test
	void testConstructeurPuitsIntIntRan() {
		Tas tas = new Tas(p1, 4, 5, new Random());
		
		assertEquals(p1, tas.getPuits(), "Le tas devrait avoir le puits p1");
		assertEquals(20, tas.getElements().length, "La longueur des elements du tas devrait etre de 10");
		assertNotNull(tas.getElements(), "Il devrait y avoir des elements");
		
	}
	
	@Test
	void testConstructeurNOk() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			new Puits(4, 22, 80, 50);
		});
		assertEquals("Erreur d'arguments.", exception.getMessage(), "Il devrait y avoir un message d'erreur");
	}
	
	@Test
	void testNombreElement() {
		Puits p2 = new Puits(10, 20, 5, 6);
		assertEquals(5, p2.getTas().getNombreElement(), "Il devrait y avoir 5 elements dans le tas");
	}
	
	@Test
	void testAjouteElement() {
		IPiece ip = new IPiece(new Coordonnees(5, 6), Couleur.ORANGE);
		Tas tas = new Tas(p1);
		
		tas.ajouterElements(ip);
		
		assertNotNull(tas.getElements(), "Il devrait y avoir des elements");
		
	}
	
	
}
