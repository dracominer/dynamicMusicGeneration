package dna.audio.synthesis;

import java.util.ArrayList;
import java.util.List;

public class MusicScale {

	public static final MusicScale Pentatonic = new MusicScale("Penatonic", 4, 'c', 'd', 'e', 'f', 'g', 'c');
	public static final MusicScale PhrygianDominant = new MusicScale("PhrygianDominant", 4, 'c', 'd', '-', 'e', 'f', 'g', 'a', '-', 'b', '-', 'c');
	/**The pythagorean tuning of the chromatic scale*/
	public static final MusicScale Chromatic = new MusicScale("PhrygianDominant", 4, 'c', 'd', '-', 'c', '+', 'd', 'e', '-', 'd', '+', 'e', 'f', 'f', '+', 'g', '-', 'g', 'a', '-', 'g', '+', 'a', 'b', '-', 'a', '+', 'b', 'c');

	private NoteData[] acceptedNotes;
	private String name;

	public MusicScale(String name, NoteData... notes) {
		this.acceptedNotes = notes;
		this.name = name;
	}

	public MusicScale(String name, int startOctave, char... notes) {
		this.name = name;
		List<NoteData> data = new ArrayList<NoteData>(notes.length);
		int octave = startOctave;
		int dataIndex = 0;
		for (int i = 0; i < notes.length; i++) {
			char note = Character.toLowerCase(notes[i]);
			if (i != 0 && note == 'c') octave++;
			if (Character.isLetter(note)) {
				if (i != 0) dataIndex++;
				data.add(new NoteData(octave, note));
			} else {
				if (note == '+') data.set(dataIndex, new NoteData(octave, notes[i - 1], NoteHelper.Sharp));
				if (note == '-') data.set(dataIndex, new NoteData(octave, notes[i - 1], NoteHelper.Flat));
			}
		}
		acceptedNotes = new NoteData[data.size()];
		acceptedNotes = data.toArray(acceptedNotes);
	}

	public NoteData[] getAcceptedNotes() {
		return acceptedNotes;
	}

	public NoteData getNote(int index) {
		int i = Math.abs(index) % acceptedNotes.length;
		return acceptedNotes[i];
	}

	public String getName() {
		return name;
	}

}
