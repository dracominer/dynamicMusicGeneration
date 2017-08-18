package dna.audio.synthesis;

public enum SongSynthSettings {
	Creepy(0.1f, 150f, 120f, 30f, 60f, 1760.00f, 65.4064f, 0.25f, 2f, 17000f, 20000f, 0.75f, 15f, 30), // 
	Melodic(0.2f, 50, 5, 0, 2, 1760, 65, 0.5f, 1.5f, 17000, 20000, 0.9f, 5, 15), //
	newMelodic(SongSynthSettings.Melodic, new SynthNote());

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
	/** the designated synthesizer of the music. This may need to change to alter the sound of the music.*/
	protected SynthGen synth = new SynthTones();

	private SongSynthSettings(float bounciness, float maxFStep, float minFStep, float maxVStep, float minVStep, float rangeUpper, float rangeLower, float minLength, float maxLength, float minVolume, float maxVolume, float continuosity, float songLengthMax, float songLengthMin, SynthGen synth) {
		this.bounciness = bounciness;
		this.maxFStep = maxFStep;
		this.minFStep = minFStep;
		this.maxVStep = maxVStep;
		this.minVStep = minVStep;
		this.rangeUpper = rangeUpper;
		this.rangeLower = rangeLower;
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.minVolume = minVolume;
		this.maxVolume = maxVolume;
		this.continuosity = continuosity;
		this.songLengthMax = songLengthMax;
		this.songLengthMin = songLengthMin;
		this.synth = synth;
	}

	private SongSynthSettings(SongSynthSettings sup, SynthGen synth) {
		this.bounciness = sup.bounciness;
		this.maxFStep = sup.maxFStep;
		this.minFStep = sup.minFStep;
		this.maxVStep = sup.maxVStep;
		this.minVStep = sup.minVStep;
		this.rangeUpper = sup.rangeUpper;
		this.rangeLower = sup.rangeLower;
		this.minLength = sup.minLength;
		this.maxLength = sup.maxLength;
		this.minVolume = sup.minVolume;
		this.maxVolume = sup.maxVolume;
		this.continuosity = sup.continuosity;
		this.songLengthMax = sup.songLengthMax;
		this.songLengthMin = sup.songLengthMin;
		this.synth = synth;
	}

	private SongSynthSettings(float bounciness, float maxFStep, float minFStep, float maxVStep, float minVStep, float rangeUpper, float rangeLower, float minLength, float maxLength, float minVolume, float maxVolume, float continuosity, float songLengthMax, float songLengthMin) {
		this.bounciness = bounciness;
		this.maxFStep = maxFStep;
		this.minFStep = minFStep;
		this.maxVStep = maxVStep;
		this.minVStep = minVStep;
		this.rangeUpper = rangeUpper;
		this.rangeLower = rangeLower;
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.minVolume = minVolume;
		this.maxVolume = maxVolume;
		this.continuosity = continuosity;
		this.songLengthMax = songLengthMax;
		this.songLengthMin = songLengthMin;
	}

	public float getBounciness() {
		return bounciness;
	}

	public float getMaxFStep() {
		return maxFStep;
	}

	public float getMinFStep() {
		return minFStep;
	}

	public float getMaxVStep() {
		return maxVStep;
	}

	public float getMinVStep() {
		return minVStep;
	}

	public float getRangeUpper() {
		return rangeUpper;
	}

	public float getRangeLower() {
		return rangeLower;
	}

	public float getMinLength() {
		return minLength;
	}

	public float getMaxLength() {
		return maxLength;
	}

	public float getMinVolume() {
		return minVolume;
	}

	public float getMaxVolume() {
		return maxVolume;
	}

	public float getContinuosity() {
		return continuosity;
	}

	public float getSongLengthMax() {
		return songLengthMax;
	}

	public float getSongLengthMin() {
		return songLengthMin;
	}

}
