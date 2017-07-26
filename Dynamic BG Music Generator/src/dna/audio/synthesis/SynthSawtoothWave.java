package dna.audio.synthesis;

public class SynthSawtoothWave extends SynthWave {

	double period = 8.0;
	double off = 0.0;

	@Override
	protected short gen(SongSynthesizer synth, double time) {
		double s = 0;
		int cnt = (int) period;
		for (double i = 1; i < cnt + 1; i += 1.0) {
			double div = 1;
			div = 1.0 / i;
			double sine = div * Math.sin(time * frequency * i);
			if (i % 2 == 0) {
				s += sine;
			} else {
				s -= sine;
			}
		}
		return (short) (s * getDecayedVolume(time));
	}

	public void setPeriod(double period) {
		this.period = period;
	}

	public void setOff(double off) {
		this.off = off;
	}

}
