package dna.audio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import dna.audio.synthesis.SongSynthesizer;

/**
 * this class handles creation of background music that changes dynamically based upon 
 * definable variables. This is good for transitions between different regions and even heightening intensities
 * for jump scares or psyching up the player for a boss battle.
 * This is intended for use in games but there is no doubt applications for this software elsewhere.
 * */
public class DynamicMusic implements Runnable {
	Thread thread;

	protected List<MusicSynthEntry> entries = new ArrayList<MusicSynthEntry>();
	SongSynthesizer synth;

	public DynamicMusic() {
		thread = new Thread(this, "Songs Thread");
	}

	/**
	 * the music must be stopped before running this method
	 * */
	public void addSong(MusicSynthEntry entry) {
		entries.add(entry);
	}

	/**
	 * the music must be stopped before running this method
	 * */
	public void removeSong(MusicSynthEntry entry) {
		entries.remove(entry);
	}

	public void start() {
		thread.start();
	}

	public void stop() {
		// does this stop the thread?
		thread.interrupt();
	}

	@Override
	public void run() {
		System.out.println("started");
		if (synth == null) synth = getSong();
		while (!synth.isFinished()) {
			synth.update();
			if (synth.isFinished()) System.out.println("Song is finished");
		}
		System.out.println("stopped");
	}

	private SongSynthesizer getSong() {
		Random rand = new Random();
		Collections.shuffle(entries);
		double c = rand.nextDouble();
		SongSynthesizer result = entries.get(0).getSynth();
		for (MusicSynthEntry e : entries) {
			if (e.weight < c) {
				System.out.println("Using song gen: " + e.getSynth());
				return e.getSynth();
			}
		}
		System.out.println("Using song gen: " + result);
		return result;
	}

}
