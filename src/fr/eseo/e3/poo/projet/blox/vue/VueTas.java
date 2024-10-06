package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Tas;
import java.awt.Color;
import java.awt.Graphics2D;

public class VueTas {

	public static final double MULTIPLIER_NUANCE = 0.4;

	private final VuePuits vuePuits;
	private final Tas tas;

	/* Constructeur */
	public VueTas(VuePuits vuePuits) {
		this.vuePuits = vuePuits;
		this.tas = vuePuits.getPuits().getTas();
	}

	public VuePuits getVuePuits() {
		return this.vuePuits;
	}

	public Tas getTas() {
		return this.tas;
	}

	/**
	 * Méthode qui prend en paramètre une couleur et renvoie une nuance de cette
	 * couleur (plus foncée) en appliquant un coefficient de réduction de
	 * luminosité. Cette nuance sera utilisée pour dessiner les blocs du tas.
	 * 
	 * @param une couleur
	 * @return une couleur avec la nuance
	 */
	public Color nuance(Color couleur) {
		int red = couleur.getRed();
		int green = couleur.getGreen();
		int blue = couleur.getBlue();

		red = (int) Math.min(red * (1 - VueTas.MULTIPLIER_NUANCE), 255);
		blue = (int) Math.min(blue * (1 - VueTas.MULTIPLIER_NUANCE), 255);
		green = (int) Math.min(green * (1 - VueTas.MULTIPLIER_NUANCE), 255);

		return new Color(red, green, blue);
	}

	/**
	 * Méthode qui prend en paramètre un contexte graphique 2D et dessine les blocs
	 * du tas en utilisant les couleurs et les positions définies par le tas associé
	 * à cette vue. Pour chaque bloc, la couleur est nuancée avec la fonction nuance
	 * et le bloc est dessiné en utilisant la méthode fill3DRect de l'objet g2d
	 * 
	 * @param un objet Graphics2D
	 */
	public void afficher(Graphics2D g2d) {
		for (int i = 0; i < this.tas.getElements().length; i++) {
			for (int j = 0; j < this.tas.getElements()[i].length; j++) {
				if (this.tas.getElements()[i][j] != null) {
					Couleur couleur = this.tas.getElements()[i][j].getCouleur();
					g2d.setColor(this.nuance(couleur.getCouleurPourAffichage()));
					int x = this.tas.getElements()[i][j].getCoordonnees().getAbscisse() * this.vuePuits.getTaille();
					int y = this.tas.getElements()[i][j].getCoordonnees().getOrdonnee() * this.vuePuits.getTaille();
					g2d.fill3DRect(x, y, this.vuePuits.getTaille(), this.vuePuits.getTaille(), true);
				}
			}
		}

	}

}
