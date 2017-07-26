package test;

import dna.audio.DynamicMusic;
import dna.audio.MusicSynthEntry;
import dna.audio.synthesis.NoteHelper;
import dna.audio.synthesis.SynthNoteSequence;

public class TestMain {

	public static void main(String[] args) {
		DynamicMusic dm = new DynamicMusic();
		SynthNoteSequence synth = new SynthNoteSequence(NoteHelper.decodeMMLtoNotes("o=6,l=0.125,/=2.5,c+,d-,e,f-,g,a+,b"));
		dm.addSong(new MusicSynthEntry(synth, "song1", -1));
		//		SynthProcSongGen synth = new SynthProcSongGen(947l, false);
		//		synth.setSongLengthMin(500);
		//		synth.setSongLengthMax(600);
		//		synth.setContinuosity(0.99f);
		//		synth.setMinLength(1);
		//		synth.setMaxLength(3);
		//		synth.setBounciness(0.1f);
		//		synth.refreshSong();
		dm.addSong(new MusicSynthEntry(synth, "procedural", -1));
		dm.start();
	}

}
