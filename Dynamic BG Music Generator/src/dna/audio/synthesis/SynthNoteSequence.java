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
		/* for some reason this only works when there is some kind of static external call.
		 I think it means that the threads run too fast and get ahead of themselves. However,
		 this method allows for me to switch between threads in realtime so I can't complain.
		*/
		System.out.hashCode();
		if (!playingNote) {
			playNextNoteSafe();
		}
	}

	public void playNextNote() throws LineUnavailableException {
		((SynthNote) gen).setNote(getCurrentNote());
		int len = (int) (getCurrentNote().getLength() * sampleRate);
		bytes = new byte[len];
		gen.generateAudio(bytes, this);
		NoteThread thread = new NoteThread(this, bytes);
		setPlayingNote(true);
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
		//		System.out.println("Playing note? " + this.playingNote);
		if (!playingNote) {
			index++;
		}
	}

	protected Note getCurrentNote() {
		if (index >= notes.length) return new Note(NoteHelper.getFrequencyFor(4, 'c'), 20000, 0.5, 2.0);
		return notes[index];
	}

}
