package dna.audio.synthesis;

public class SynthTriangleWave extends SynthWave {

	@Override
	protected short gen(SongSynthesizer synth, double time) {
		double result = (2.0 / Math.PI) * Math.asin(genSinVal(time, frequency));
		return (short) (result * getDecayedVolume(time));
	}

}
