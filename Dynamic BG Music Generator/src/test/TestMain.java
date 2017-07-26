package test;

import dna.audio.DynamicMusic;
import dna.audio.MusicSynthEntry;
import dna.audio.synthesis.SynthProcSongGen;

public class TestMain {

	public static void main(String[] args) {
		DynamicMusic dm = new DynamicMusic();
		//		SynthNoteSequence synth = new SynthNoteSequence(NoteHelper.decodeMMLtoNotes("o=6,l=0.5,/=2.5,c+,d-,e,f-,g,a+,b"));
		//		dm.addSong(new MusicSynthEntry(synth, "song1", -1));
		SynthProcSongGen synth = new SynthProcSongGen(947l, false);
		synth.setSongLengthMin(30);
		synth.setSongLengthMax(45);
		synth.setContinuosity(0.65f);
		synth.setMinLength(0.25f);
		synth.setMaxLength(1f);
		synth.setBounciness(0.1f);
		synth.setMaxFStep(300);
		synth.setMinFStep(100);
		synth.refreshSong();
		dm.addSong(new MusicSynthEntry(synth, "procedural", -1));
		dm.start();
	}

}
