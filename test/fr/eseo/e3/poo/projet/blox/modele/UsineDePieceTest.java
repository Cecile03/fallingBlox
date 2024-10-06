package fr.eseo.e3.poo.projet.blox.modele;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.eseo.e3.poo.projet.blox.modele.pieces.IPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.JPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.LPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.OPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.SPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.TPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.ZPiece;

public class UsineDePieceTest {

	@Test
	public void testCyclic() {
		UsineDePiece.setMode(UsineDePiece.CYCLIC);
		Piece p = UsineDePiece.genererPiece();
		Piece p2 = UsineDePiece.genererPiece();
		Piece p3 = UsineDePiece.genererPiece();
		Piece p4 = UsineDePiece.genererPiece();
		Piece p5 = UsineDePiece.genererPiece();
		Piece p6 = UsineDePiece.genererPiece();
		Piece p7 = UsineDePiece.genererPiece();
		
		assertTrue(p instanceof OPiece);
		assertTrue(p2 instanceof IPiece);
		assertTrue(p3 instanceof TPiece);
		assertTrue(p4 instanceof LPiece);
		assertTrue(p5 instanceof JPiece);
		assertTrue(p6 instanceof ZPiece);
		assertTrue(p7 instanceof SPiece);
		
		}
}
