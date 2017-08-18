package dna.audio.synthesis;

public class NoteData {
	private double frequency;

	public NoteData(double frequency) {
		this.frequency = frequency;
	}

	public NoteData(int octave, char note, int type) {
		this.frequency = NoteHelper.getFrequencyFor(octave, note, type);
	}

	public NoteData(int octave, char note) {
		this(octave, note, NoteHelper.Natural);
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

}
