package dna.audio.synthesis;

public class Note {

	private double frequency;
	private double volume;
	private double length;
	private double decay;

	public Note(double frequency, double volume, double length, double decay) {
		this.frequency = frequency;
		this.volume = volume;
		this.length = length;
		this.decay = decay;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getDecay() {
		return decay;
	}

	public void setDecay(double decay) {
		this.decay = decay;
	}

	public String toString() {
		return "Note[freq=" + getFrequency() + ",len=" + getLength() + ",vol=" + getVolume() + ",decay=" + getDecay() + "]";
	}

}
