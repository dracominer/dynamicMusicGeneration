package dna.audio.synthesis;

import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

/**
 * a class that generates a single note or more.
 * */
public abstract class SynthGen {

	protected ByteBuffer byteBuffer;
	protected ShortBuffer shortBuffer;
	protected int byteLength;

	public void generateAudio(byte[] dataBuffer, SongSynthesizer synth) {
		byteBuffer = ByteBuffer.wrap(dataBuffer);
		shortBuffer = byteBuffer.asShortBuffer();
		byteLength = dataBuffer.length;
		float sampleRate = synth.sampleRate;
		int bytesPerSamp = synth.sampleSize / 8;
		int sampleLength = byteLength / bytesPerSamp;
		for (int cnt = 0; cnt < sampleLength; cnt++) {
			double time = cnt / sampleRate;
			shortBuffer.put(gen(synth, time));
		}
	}

	protected abstract short gen(SongSynthesizer synth, double time);

	protected double genSinVal(double time, double frequency) {
		double sinValue = (Math.sin(2 * Math.PI * frequency * time) + Math.sin(2 * Math.PI * (frequency / 1.8) * time) + Math.sin(2 * Math.PI * (frequency / 1.5) * time)) / 3.0;
		return sinValue;
	}

}
