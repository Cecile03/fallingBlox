package fr.eseo.e3.poo.projet.blox.vue;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class LecteurMP3 {

	private String fichier;
	private Clip clip;

	/* Constructeur */
	public LecteurMP3(String fichier) throws LineUnavailableException {
		this.clip = AudioSystem.getClip();
		this.fichier = fichier;
	}

	/**
	 * Cette fonction lit le fichier audio spécifié dans la variable fichier. Elle
	 * récupère un objet AudioInputStream à partir de la bibliothèque AudioSystem de
	 * Java pour pouvoir lire le fichier audio. Elle récupère un objet Clip à partir
	 * de la bibliothèque AudioSystem de Java et l'ouvre avec l'objet
	 * AudioInputStream précédemment créé. Elle démarre la lecture du fichier audio
	 * à l'aide de la méthode start() de l'objet Clip.
	 * 
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(this.fichier).getAbsoluteFile());
		this.clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.start();
	}

	/**
	 * Cette fonction arrête la lecture du fichier audio en cours avec la méthode
	 * stop() de l'objet Clip.
	 */
	public void stop() {
		clip.stop();
	}
}
