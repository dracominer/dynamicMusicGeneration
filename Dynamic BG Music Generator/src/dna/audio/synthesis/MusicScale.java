package dna.audio.synthesis;

import java.util.ArrayList;
import java.util.List;

public class MusicScale {

	public static MusicScale Pentatonic = new MusicScale("Penatonic", 4, 'c', 'd', 'e', 'f', 'g', 'c');

	private NoteData[] acceptedNotes;
	private String name;

	public MusicScale(String name, NoteData... notes) {
		this.acceptedNotes = notes;
		this.name = name;
	}

	public MusicScale(String name, int startOctave, char... notes) {
		this.name = name;
		List<NoteData> data = new ArrayList<NoteData>();
		int octave = startOctave;
		int dataIndex = 0;
		for (int i = 0; i < notes.length; i++) {
			char note = Character.toLowerCase(notes[i]);
			if (i != 0 && note == 'c') octave++;
			if (Character.isLetter(note)) {
				data.add(new NoteData(octave, note));
				dataIndex++;
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
