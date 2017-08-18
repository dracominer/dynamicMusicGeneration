package dna.audio.synthesis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SynthSongScaledGen extends SynthNoteSequence {

	protected MusicScale scale = MusicScale.Pentatonic;

	protected float bounciness;
	protected float continuosity;
	protected float volume;
	protected float minNoteLen;
	protected float maxNoteLen;
	protected float songLength;

	protected long seed;

	protected Random rand = new Random();

	public SynthSongScaledGen(MusicScale scale, float bounciness, float continuosity, float volume, float minNoteLen, float maxNoteLen, float songLength) {
		super();
		this.scale = scale;
		this.bounciness = bounciness;
		this.continuosity = continuosity;
		this.volume = volume;
		this.minNoteLen = minNoteLen;
		this.maxNoteLen = maxNoteLen;
		this.songLength = songLength;
	}

	public void refreshSong() {
		this.notes = genSong();
	}

	protected Note[] genSong() {
		List<Note> notes = new ArrayList<Note>();
		int noteDir = (rand.nextBoolean() ? 1 : -1);
		float totalLength = 0;
		int lastNote = rand.nextInt(scale.getAcceptedNotes().length);
		while (totalLength < songLength) {
			int n = lastNote + (getNoteStep() * noteDir);
			lastNote = n;
			float freq = (float) scale.getNote(n).getFrequency();
			float len = getNoteLength();
			float vol = volume;
			if (rand.nextFloat() > continuosity) vol = 0;
			if (rand.nextFloat() < bounciness) noteDir *= -1f;
			totalLength += len;
			notes.add(new Note(freq, vol, len, SynthWave.getDecayLevelForNoteLength(len)));
		}
		Note[] result = new Note[notes.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = notes.get(i);
		}
		System.out.println("Generated a song with " + notes.size() + " notes and a length of " + totalLength + " seconds");
		return result;
	}

	private int getNoteStep() {
		return rand.nextInt(3);
	}

	private float getNoteLength() {
		return (maxNoteLen - minNoteLen) * rand.nextFloat() + minNoteLen;
	}

	public void setSeed(long l) {
		this.seed = l;
		rand.setSeed(seed);
	}

}
