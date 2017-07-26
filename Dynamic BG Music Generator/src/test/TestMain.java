package test;

import java.util.Random;

import dna.audio.DynamicMusic;
import dna.audio.MusicSynthEntry;
import dna.audio.synthesis.NoteHelper;
import dna.audio.synthesis.SynthProcSongGen;

public class TestMain {

	public static void main(String[] args) {
		DynamicMusic dm = new DynamicMusic();
		SynthProcSongGen synth = new SynthProcSongGen(new Random().nextLong(), false);
		synth.setSongLengthMin(1500);
		synth.setSongLengthMax(4500);
		synth.setContinuosity(0.75f);
		synth.setMinLength(0.25f);
		synth.setMaxLength(2f);
		synth.setBounciness(0.1f);
		synth.setMaxFStep(150);
		synth.setMinFStep(20);
		synth.setRangeUpper((float) NoteHelper.getFrequencyFor(6, 'a'));
		synth.setRangeLower((float) NoteHelper.getFrequencyFor(2, 'c'));
		synth.refreshSong();
		dm.addSong(new MusicSynthEntry(synth, "procedural", -1));
		dm.start();
	}

}
