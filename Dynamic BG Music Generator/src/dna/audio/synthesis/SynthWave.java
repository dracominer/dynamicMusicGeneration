package dna.audio.synthesis;

public abstract class SynthWave extends SynthGen {
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
		return volume * (Math.pow(Math.E, -time * decay));
	}

}
