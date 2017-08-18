package dna.audio.synthesis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SynthProcSongGen extends SynthNoteSequence {

	/**song's tendency to change direction*/
	protected float bounciness;
	/**the max change in frequency*/
	protected float maxFStep;
	/**the min change in frequency*/
	protected float minFStep;
	/**the max change in volume*/
	protected float maxVStep;
	/**the min change in volume*/
	protected float minVStep;
	/**The greatest allowed frequency*/
	protected float rangeUpper;
	/**The lowest allowed frequency*/
	protected float rangeLower;
	/**the shortest note length allowed*/
	protected float minLength;
	/**the longest note length allowed*/
	protected float maxLength;
	/**the quietest note allowed*/
	protected float minVolume;
	/**the loudest note allowed*/
	protected float maxVolume;
	/**the song's tendency to keep going. lower makes it have more rests*/
	protected float continuosity;
	/**the maximum length of the song*/
	protected float songLengthMax;
	/**the minimum length of the song*/
	protected float songLengthMin;
	/**the seed for the random generator.
	 * Makes songs replicatable*/
	protected long seed;

	protected Random random = new Random();

	public SynthProcSongGen(long seed, boolean genSong) {
		super();
		random.setSeed(seed);
		this.bounciness = random.nextFloat();
		this.maxFStep = 100;
		this.minFStep = 20;
		this.rangeUpper = (float) NoteHelper.getFrequencyFor(6, 'g');
		this.rangeLower = (float) NoteHelper.getFrequencyFor(4, 'g');
		this.minLength = random.nextFloat();
		this.maxLength = 4;
		this.continuosity = random.nextFloat();
		this.songLengthMax = 4;
		this.songLengthMin = 8;
		this.minVolume = 17000;
		this.maxVolume = 20000;
		this.minVStep = 30;
		this.maxVStep = 60;
		random.setSeed(seed);
		if (genSong) this.notes = genSong();
	}

	public SynthProcSongGen(float bounciness, float maxFStep, float minFStep, float rangeUpper, float rangeLower, float minLength, float maxLength, float continuosity, float songLengthMax, float songLengthMin, float minVolume, float maxVolume, float minVStep, float maxVStep, long seed) {
		this.bounciness = bounciness;
		this.maxFStep = maxFStep;
		this.minFStep = minFStep;
		this.rangeUpper = rangeUpper;
		this.rangeLower = rangeLower;
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.continuosity = continuosity;
		this.songLengthMax = songLengthMax;
		this.songLengthMin = songLengthMin;
		this.minVolume = minVolume;
		this.maxVolume = maxVolume;
		this.minVStep = minVStep;
		this.maxVStep = maxVStep;
		this.seed = seed;
		random.setSeed(seed);
	}

	public void refreshSong() {
		this.notes = genSong();
	}

	protected Note[] genSong() {
		List<Note> notes = new ArrayList<Note>();
		float volDir = (random.nextBoolean() ? 1f : -1f);
		float freqDir = (random.nextBoolean() ? 1f : -1f);
		float totalLength = 0;
		float songLength = (songLengthMax - songLengthMin) * random.nextFloat() + songLengthMin;
		float lastFreq = (rangeUpper - rangeLower) * random.nextFloat() + rangeLower;
		float lastVolume = (maxVolume - minVolume) * random.nextFloat() + minVolume;
		while (totalLength < songLength) {
			float volume = lastVolume + (getVolStep() * volDir);
			float freq = lastFreq + (getNoteStep() * freqDir);
			float len = getNoteLength();
			if (random.nextFloat() > continuosity) volume = 0;
			if (random.nextFloat() < bounciness) volDir *= -1f;
			if (random.nextFloat() < bounciness || freq > rangeUpper || freq < rangeLower) freqDir *= -1f;
			totalLength += len;
			notes.add(new Note(freq, volume, len, 1.3f + len));
		}
		Note[] result = new Note[notes.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = notes.get(i);
		}
		System.out.println("Generated a song with " + notes.size() + " notes and a length of " + totalLength + " seconds");
		return result;
	}

	

	private float getNoteStep() {
		return (maxFStep - minFStep) * random.nextFloat() + minFStep;
	}

	private float getVolStep() {
		return (maxVStep - minVStep) * random.nextFloat() + minVStep;
	}

	private float getNoteLength() {
		return (maxLength - minLength) * random.nextFloat() + minLength;
	}

	public void setBounciness(float bounciness) {
		this.bounciness = bounciness;
	}

	public void setMaxStep(float maxStep) {
		this.maxFStep = maxStep;
	}

	public void setMinStep(float minStep) {
		this.minFStep = minStep;
	}

	public void setRangeUpper(float rangeUpper) {
		this.rangeUpper = rangeUpper;
	}

	public void setRangeLower(float rangeLower) {
		this.rangeLower = rangeLower;
	}

	public void setMinLength(float minLength) {
		this.minLength = minLength;
	}

	public void setMaxLength(float maxLength) {
		this.maxLength = maxLength;
	}

	public void setContinuosity(float continuosity) {
		this.continuosity = continuosity;
	}

	public void setSongLengthMax(float songLengthMax) {
		this.songLengthMax = songLengthMax;
	}

	public void setSongLengthMin(float songLengthMin) {
		this.songLengthMin = songLengthMin;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public void setMaxFStep(float maxFStep) {
		this.maxFStep = maxFStep;
	}

	public void setMinFStep(float minFStep) {
		this.minFStep = minFStep;
	}

	public void setMaxVStep(float maxVStep) {
		this.maxVStep = maxVStep;
	}

	public void setMinVStep(float minVStep) {
		this.minVStep = minVStep;
	}

	public void setMinVolume(float minVolume) {
		this.minVolume = minVolume;
	}

	public void setMaxVolume(float maxVolume) {
		this.maxVolume = maxVolume;
	}

	public void setSongSettings(SongSynthSettings settings) {
		this.bounciness = settings.bounciness;
		this.maxFStep = settings.maxFStep;
		this.minFStep = settings.minFStep;
		this.rangeUpper = settings.rangeUpper;
		this.rangeLower = settings.rangeLower;
		this.minLength = settings.minLength;
		this.maxLength = settings.maxLength;
		this.continuosity = settings.continuosity;
		this.songLengthMax = settings.songLengthMax;
		this.songLengthMin = settings.songLengthMin;
		this.minVolume = settings.minVolume;
		this.maxVolume = settings.maxVolume;
		this.minVStep = settings.minVStep;
		this.maxVStep = settings.maxVStep;
	}

}
