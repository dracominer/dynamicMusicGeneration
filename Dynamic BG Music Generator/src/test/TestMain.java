package test;

import dna.audio.DynamicMusic;
import dna.audio.MusicSynthEntry;
import dna.audio.synthesis.MusicScale;
import dna.audio.synthesis.SynthSongScaledGen;

public class TestMain {

	public static void main(String[] args) {
		DynamicMusic dm = new DynamicMusic();
		SynthSongScaledGen synth = new SynthSongScaledGen(MusicScale.Chromatic, 0.01f, 0.8f, 30000f, 0.9f, 1.3f, 25f);
		synth.setSeed(947l);
		synth.refreshSong();
		dm.addSong(new MusicSynthEntry(synth, "procedural", -1));
		dm.start();
	}

}
