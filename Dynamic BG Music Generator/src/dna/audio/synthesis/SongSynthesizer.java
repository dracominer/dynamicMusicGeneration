package dna.audio.synthesis;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineUnavailableException;

/**
 * this class is designed to procedurally generate a single type of song. 
 * The DynamicMusic class can be fed multiple 'DNASyth's
 * in order to dynamically switch between song types based upon given input
 * */
public class SongSynthesizer {

	//Settings

	/**
	 * can be: 8000, 11025, 16000, 22050, or 44100
	 * higher sample rate, the bigger the drag on the computer.
	 * Lower is better for speed
	 * Higher is better for quality
	 **/
	protected float sampleRate = 16000.0f;
	/**
	 * the sample size in bits. can be 8 or 16
	 * changes how diverse the levels of tones can be.
	 * Smaller range with 8
	 * larger range with 16
	 * */
	protected int sampleSize = 16;
	/**
	 * can be 1 or 2
	 * 1 = mono
	 * 2 = stereo
	 * */
	protected int channels = 1;
	/**
	 * can be true or false
	 * bunch of extra work to make unsigned work. Just leave this to be true unless you
	 * want to redesign a big chunk of code.
	 * */
	protected boolean signed = true;
	/**
	 * can be true or false
	 * Java defaults to making bigEndian data for audio so it's easier to use bigEndian here.
	 * Just leave this to be true unless you want to redesign a big chunk of code.
	 **/
	protected boolean bigEndian = true;

	protected byte[] bytes = new byte[(int) (sampleRate * 4f)];

	protected SynthGen gen = new SynthTones();

	protected boolean playingNote = false;

	public void update() {}

	public void playNextNoteSafe() {
		try {
			playNextNote();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void playNextNote() throws LineUnavailableException {
		gen.generateAudio(bytes, this);
		NoteThread thread = new NoteThread(this, bytes);
		playingNote = true;
		thread.start();
	}

	public AudioFormat getAudioFormat() {
		return new AudioFormat(sampleRate, sampleSize, channels, signed, bigEndian);
	}

	public void setSampleRate(float sampleRate) {
		this.sampleRate = sampleRate;
	}

	public void setSampleSize(int sampleSize) {
		this.sampleSize = sampleSize;
	}

	public void setChannels(int channels) {
		this.channels = channels;
	}

	public void setSigned(boolean signed) {
		this.signed = signed;
	}

	public void setBigEndian(boolean bigEndian) {
		this.bigEndian = bigEndian;
	}

	public void setPlayingNote(boolean playingNote) {
		this.playingNote = playingNote;
	}

	public void setSynth(SynthGen gen) {
		this.gen = gen;
	}

	public boolean isPlayingNote() {
		return playingNote;
	}

	public boolean isFinished() {
		return !playingNote;
	}

}
