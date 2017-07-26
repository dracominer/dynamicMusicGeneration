package dna.audio;

import dna.audio.synthesis.SongSynthesizer;

public class MusicSynthEntry {

	public SongSynthesizer synth;
	public String name;
	public double weight;

	public MusicSynthEntry(SongSynthesizer synth, String name, double weight) {
		this.synth = synth;
		this.name = name;
		this.weight = weight;
	}

	public SongSynthesizer getSynth() {
		return synth;
	}

	public void setSynth(SongSynthesizer synth) {
		this.synth = synth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
