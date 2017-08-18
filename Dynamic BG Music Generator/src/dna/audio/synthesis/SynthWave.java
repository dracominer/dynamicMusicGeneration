package dna.audio.synthesis;

public abstract class SynthWave extends SynthGen {

	public static final float DecayVolOffset = 0.001f;
	protected double volume = 5000;
	protected double decay = 5;
	protected double frequency = 970.0;

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getDecay() {
		return decay;
	}

	public void setDecay(double decay) {
		this.decay = decay;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	public double getDecayedVolume(double time) {
		return volume * (Math.pow(2, -time * decay) - DecayVolOffset);
	}

	public static float getDecayLevelForNoteLength(float length) {
		double a = Math.log10(DecayVolOffset);
		double b = (Math.log10(2) * -length);
		if (b == 0) b = 0.00001;
		double r = a / b;
		return (float) r;
	}

}
