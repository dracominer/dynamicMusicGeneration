package dna.audio.synthesis;

import javax.sound.sampled.LineUnavailableException;

public class SynthNoteSequence extends SongSynthesizer {

	protected Note[] notes = new Note[0];
	protected int index = 0;

	public SynthNoteSequence(Note[] notes) {
		this.notes = notes;
		setSynth(new SynthNote());
	}

	protected SynthNoteSequence() {
		setSynth(new SynthNote());
	}

	public void printNotes() {
		System.out.println("-----Song Notes-------");
		for (Note n : notes) {
			System.out.println(n);
		}
		System.out.println("----------------------");
	}

	public void update() {
		if (!playingNote) {
			System.out.println("Playing next note!");
			playNextNoteSafe();
		}
	}

	public void playNextNote() throws LineUnavailableException {
		System.out.println("setting note: " + getCurrentNote());
		((SynthNote) gen).setNote(getCurrentNote());
		System.out.println("setting byte buffer length");
		int len = (int) (getCurrentNote().getLength() * sampleRate);
		bytes = new byte[len];
		System.out.println("Generating note");
		gen.generateAudio(bytes, this);
		System.out.println("creating new note thread");
		NoteThread thread = new NoteThread(this, bytes);
		playingNote = true;
		System.out.println("Playing note");
		thread.start();
	}

	public boolean hasNextNote() {
		return (index < notes.length);
	}

	public boolean isFinished() {
		return !hasNextNote();
	}

	public void setPlayingNote(boolean playingNote) {
		this.playingNote = playingNote;
		if (!playingNote) {
			System.out.println("Last note has ended!");
			index++;
		}
	}

	protected Note getCurrentNote() {
		return notes[index];
	}

}
