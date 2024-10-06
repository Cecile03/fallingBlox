package fr.eseo.e3.poo.projet.blox.modele;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

public class CouleurTest {

	@Test
	void test() {
		Couleur c1 = Couleur.ORANGE;
		
		assertEquals(Color.ORANGE, c1.getCouleurPourAffichage(), "La couleur devrait etre orange");
	}
}
