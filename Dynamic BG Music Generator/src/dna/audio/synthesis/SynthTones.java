package dna.audio.synthesis;

public class SynthTones extends SynthGen {

	protected double frequency = 950.0;
	protected double volume = 16000.0;

	@Override
	protected short gen(SongSynthesizer synth, double time) {
		return (short) (genSinVal(time, frequency) * volume);
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

}
