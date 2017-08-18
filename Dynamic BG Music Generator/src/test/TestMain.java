package test;

import dna.audio.DynamicMusic;
import dna.audio.MusicSynthEntry;
import dna.audio.synthesis.MusicScale;
import dna.audio.synthesis.SynthSongScaledGen;

public class TestMain {

	public static void main(String[] args) {
		DynamicMusic dm = new DynamicMusic();
		SynthSongScaledGen synth = new SynthSongScaledGen(MusicScale.Pentatonic, 0.25f, 0.9999f, 30000f, 0.2f, 1.0f, 2000f);
		synth.setSeed(947l);
		synth.refreshSong();
		dm.addSong(new MusicSynthEntry(synth, "procedural", -1));
		dm.start();
	}

}
