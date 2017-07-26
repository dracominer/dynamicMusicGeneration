package dna.audio.synthesis;

public class SynthNote extends SynthTriangleWave {

	protected Note note;

	@Override
	protected short gen(SongSynthesizer synth, double time) {
		if (note == null) return 0;
		if (time > note.getLength()) return 0;
		return super.gen(synth, time);
	}

	public void setNote(Note note) {
		this.note = note;
		setDecay(note.getDecay());
		setFrequency(note.getFrequency());
		setVolume(note.getVolume());
	}

}
