package fr.eseo.e3.poo.projet.blox.vue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.eseo.e3.poo.projet.blox.modele.Puits;

public class VuePuitsTest {
	
	Puits pu = new Puits();
	VuePuits vpu1 = new VuePuits(pu);
	VuePuits vpu2 = new VuePuits(pu, 10);
	
	@Test
	public void testConstructeur() {
		
		assertEquals(pu, vpu1.getPuits(), "Le puits devrait etre pu");
		assertEquals(5, vpu1.getTaille(), "La valeur par defaut de la taille");
		assertEquals(pu, vpu2.getPuits(), "Le puits devrait etre puits");
		assertEquals(10, vpu2.getTaille(), "La valeur de la taille devrait etre 10");
	}
	
	@Test
	public void testSet() {
		Puits pu1 = new Puits();
		
		vpu1.setPuits(pu1);
		vpu1.setTaille(3);
		
		assertEquals(pu1, vpu1.getPuits(), "Le puits devrait etre pu1");
		assertEquals(3, vpu1.getTaille(), "La valeur par defaut de la taille");
	}

}
