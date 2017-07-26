package dna.audio.synthesis;

import java.util.ArrayList;
import java.util.List;

/**
 * this is a static helper class that can provide the frequencies of all notes easily played upon a piano
 * for use with the synthesizers.
 * */
public class NoteHelper {

	private static final double defaultFrequency = 950.0;

	public static final int Flat = -1;
	public static final int Natural = 0;
	public static final int Sharp = 1;

	public static Note[] decodeMMLtoNotes(String dmlLine) {
		List<Note> notes = new ArrayList<Note>();
		// start decoding
		double length = 1.0;
		double volume = 5000.0;
		double decay = 1.0;
		int octave = 4;
		String[] pairs = dmlLine.split(",");
		for (String pair : pairs) {
			if (!pair.contains("=")) {
				int type = Natural;
				double vol = volume;
				double len = length;
				if (pair.toLowerCase().charAt(0) == 'r') vol = 0;
				String data = pair.substring(1).toLowerCase();
				if (data.length() > 0) {
					char[] chars = new char[data.length()];
					data.getChars(0, data.length(), chars, 0);
					for (char c : chars) {
						if (c == '+') type = Sharp;
						if (c == '-') type = Flat;
					}
					try {
						len = Integer.parseInt(data);
					} catch (Exception e) {
						try {
							len = Double.parseDouble(data);
						} catch (Exception e2) {}
					}
				}
				notes.add(new Note(getFrequencyFor(octave, pair.toLowerCase().charAt(0), type), vol, len, decay));
			} else {
				String[] parts = pair.split("=", 2);
				if (parts.length != 2) {
					System.err.println("\"" + pair + "\" is an invalid pair. Length:" + parts.length);
					continue;
				}
				String key = parts[0].toLowerCase();
				String value = parts[1];
				if (key.contains("o")) {
					octave = Integer.parseInt(value);
				}
				if (key.contains("v")) {
					volume = Double.parseDouble(value);
				}
				if (key.contains("t") || key.contains("l")) {
					length = Double.parseDouble(value);
				}
				if (key.contains("/") || key.contains("d")) {
					decay = Double.parseDouble(value);
				}
			}
		}
		// translate into array
		Note[] result = new Note[notes.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = notes.get(i);
		}
		return result;
	}

	/**
	 * uses a big lookup table to find the frequency for each note.
	 * Only can provide common frequencies of piano notes. Interpolation and
	 * other maths can be used to create different sounds but this is a good base.
	 * 
	 * defaults to natural notes
	 * */
	public static double getFrequencyFor(int octave, char note) {
		return getFrequencyFor(octave, note, Natural);
	}

	/**
	 * uses a big lookup table to find the frequency for each note.
	 * Only can provide common frequencies of piano notes. Interpolation and
	 * other maths can be used to create different sounds but this is a good base.
	 * */
	public static double getFrequencyFor(int octave, char note, int type) {
		if (octave == 0) return octave0(note, type);
		if (octave == 1) return octave1(note, type);
		if (octave == 2) return octave2(note, type);
		if (octave == 3) return octave3(note, type);
		if (octave == 4) return octave4(note, type);
		if (octave == 5) return octave5(note, type);
		if (octave == 6) return octave6(note, type);
		if (octave == 7) return octave7(note, type);
		if (octave == 8) return octave8(note, type);
		return defaultFrequency;
	}

	private static double octave0(char note, int type) {
		if (note == 'a') {
			if (type == Flat) return 25.9565;
			if (type == Natural) return 27.5;
			if (type == Sharp) return 29.1352;
		}
		if (note == 'b') {
			if (type == Flat) return 29.1352;
			if (type == Natural) return 30.8677;
			if (type == Sharp) return 32.7032;
		}
		return defaultFrequency;
	}

	private static double octave1(char note, int type) {
		if (note == 'c') {
			if (type == Flat) return 30.8677;
			if (type == Natural) return 32.7032;
			if (type == Sharp) return 34.6478;
		}
		if (note == 'd') {
			if (type == Flat) return 34.6478;
			if (type == Natural) return 36.7081;
			if (type == Sharp) return 38.8909;
		}
		if (note == 'e') {
			if (type == Flat) return 38.8909;
			if (type == Natural) return 41.2034;
			if (type == Sharp) return 43.6535;
		}
		if (note == 'f') {
			if (type == Flat) return 41.2034;
			if (type == Natural) return 43.6535;
			if (type == Sharp) return 46.2493;
		}
		if (note == 'g') {
			if (type == Flat) return 46.2493;
			if (type == Natural) return 48.9994;
			if (type == Sharp) return 51.9131;
		}
		if (note == 'a') {
			if (type == Flat) return 51.9131;
			if (type == Natural) return 55.0000;
			if (type == Sharp) return 58.2705;
		}
		if (note == 'b') {
			if (type == Flat) return 58.2705;
			if (type == Natural) return 61.7354;
			if (type == Sharp) return 65.4064;
		}
		return defaultFrequency;
	}

	private static double octave2(char note, int type) {
		if (note == 'c') {
			if (type == Flat) return 61.7354;
			if (type == Natural) return 65.4064;
			if (type == Sharp) return 69.2957;
		}
		if (note == 'd') {
			if (type == Flat) return 69.2957;
			if (type == Natural) return 73.4162;
			if (type == Sharp) return 77.7817;
		}
		if (note == 'e') {
			if (type == Flat) return 77.7817;
			if (type == Natural) return 82.4069;
			if (type == Sharp) return 87.3071;
		}
		if (note == 'f') {
			if (type == Flat) return 82.4069;
			if (type == Natural) return 87.3071;
			if (type == Sharp) return 92.4986;
		}
		if (note == 'g') {
			if (type == Flat) return 92.4986;
			if (type == Natural) return 97.9989;
			if (type == Sharp) return 103.826;
		}
		if (note == 'a') {
			if (type == Flat) return 103.826;
			if (type == Natural) return 110.000;
			if (type == Sharp) return 116.541;
		}
		if (note == 'b') {
			if (type == Flat) return 116.541;
			if (type == Natural) return 123.471;
			if (type == Sharp) return 130.813;
		}
		return defaultFrequency;
	}

	private static double octave3(char note, int type) {
		if (note == 'c') {
			if (type == Flat) return 123.471;
			if (type == Natural) return 130.813;
			if (type == Sharp) return 138.591;
		}
		if (note == 'd') {
			if (type == Flat) return 138.591;
			if (type == Natural) return 146.832;
			if (type == Sharp) return 155.563;
		}
		if (note == 'e') {
			if (type == Flat) return 155.563;
			if (type == Natural) return 164.814;
			if (type == Sharp) return 174.614;
		}
		if (note == 'f') {
			if (type == Flat) return 164.814;
			if (type == Natural) return 174.614;
			if (type == Sharp) return 184.997;
		}
		if (note == 'g') {
			if (type == Flat) return 184.997;
			if (type == Natural) return 195.998;
			if (type == Sharp) return 207.652;
		}
		if (note == 'a') {
			if (type == Flat) return 207.652;
			if (type == Natural) return 220.000;
			if (type == Sharp) return 233.082;
		}
		if (note == 'b') {
			if (type == Flat) return 233.082;
			if (type == Natural) return 246.942;
			if (type == Sharp) return 261.626;
		}
		return defaultFrequency;
	}

	private static double octave4(char note, int type) {
		if (note == 'c') {
			if (type == Flat) return 246.942;
			if (type == Natural) return 261.626;
			if (type == Sharp) return 277.183;
		}
		if (note == 'd') {
			if (type == Flat) return 277.183;
			if (type == Natural) return 293.665;
			if (type == Sharp) return 311.127;
		}
		if (note == 'e') {
			if (type == Flat) return 311.127;
			if (type == Natural) return 329.628;
			if (type == Sharp) return 349.228;
		}
		if (note == 'f') {
			if (type == Flat) return 329.628;
			if (type == Natural) return 349.228;
			if (type == Sharp) return 369.994;
		}
		if (note == 'g') {
			if (type == Flat) return 369.994;
			if (type == Natural) return 391.995;
			if (type == Sharp) return 415.305;
		}
		if (note == 'a') {
			if (type == Flat) return 415.305;
			if (type == Natural) return 440.000;
			if (type == Sharp) return 466.164;
		}
		if (note == 'b') {
			if (type == Flat) return 466.164;
			if (type == Natural) return 493.883;
			if (type == Sharp) return 523.251;
		}
		return defaultFrequency;
	}

	private static double octave5(char note, int type) {
		if (note == 'c') {
			if (type == Flat) return 493.883;
			if (type == Natural) return 523.251;
			if (type == Sharp) return 554.365;
		}
		if (note == 'd') {
			if (type == Flat) return 554.365;
			if (type == Natural) return 587.330;
			if (type == Sharp) return 622.254;
		}
		if (note == 'e') {
			if (type == Flat) return 622.254;
			if (type == Natural) return 659.255;
			if (type == Sharp) return 698.456;
		}
		if (note == 'f') {
			if (type == Flat) return 659.255;
			if (type == Natural) return 698.456;
			if (type == Sharp) return 739.989;
		}
		if (note == 'g') {
			if (type == Flat) return 739.989;
			if (type == Natural) return 783.991;
			if (type == Sharp) return 830.609;
		}
		if (note == 'a') {
			if (type == Flat) return 830.609;
			if (type == Natural) return 880.000;
			if (type == Sharp) return 932.328;
		}
		if (note == 'b') {
			if (type == Flat) return 932.328;
			if (type == Natural) return 987.767;
			if (type == Sharp) return 1046.50;
		}
		return defaultFrequency;
	}

	private static double octave6(char note, int type) {
		if (note == 'c') {
			if (type == Flat) return 987.767;
			if (type == Natural) return 1046.50;
			if (type == Sharp) return 1108.73;
		}
		if (note == 'd') {
			if (type == Flat) return 1108.73;
			if (type == Natural) return 1174.66;
			if (type == Sharp) return 1244.51;
		}
		if (note == 'e') {
			if (type == Flat) return 1244.51;
			if (type == Natural) return 1318.510;
			if (type == Sharp) return 1396.910;
		}
		if (note == 'f') {
			if (type == Flat) return 1318.510;
			if (type == Natural) return 1396.910;
			if (type == Sharp) return 1479.980;
		}
		if (note == 'g') {
			if (type == Flat) return 1479.980;
			if (type == Natural) return 1567.980;
			if (type == Sharp) return 1661.220;
		}
		if (note == 'a') {
			if (type == Flat) return 1661.220;
			if (type == Natural) return 1760.00;
			if (type == Sharp) return 1864.66;
		}
		if (note == 'b') {
			if (type == Flat) return 1864.66;
			if (type == Natural) return 1975.53;
			if (type == Sharp) return 2093.00;
		}
		return defaultFrequency;
	}

	private static double octave7(char note, int type) {
		if (note == 'c') {
			if (type == Flat) return 1975.53;
			if (type == Natural) return 2093.00;
			if (type == Sharp) return 2217.46;
		}
		if (note == 'd') {
			if (type == Flat) return 2217.46;
			if (type == Natural) return 2349.32;
			if (type == Sharp) return 2489.02;
		}
		if (note == 'e') {
			if (type == Flat) return 2489.02;
			if (type == Natural) return 2637.02;
			if (type == Sharp) return 2793.83;
		}
		if (note == 'f') {
			if (type == Flat) return 2637.02;
			if (type == Natural) return 2793.83;
			if (type == Sharp) return 2959.96;
		}
		if (note == 'g') {
			if (type == Flat) return 2959.96;
			if (type == Natural) return 3135.96;
			if (type == Sharp) return 3322.44;
		}
		if (note == 'a') {
			if (type == Flat) return 3322.44;
			if (type == Natural) return 3520.00;
			if (type == Sharp) return 3729.31;
		}
		if (note == 'b') {
			if (type == Flat) return 3729.31;
			if (type == Natural) return 3951.07;
			if (type == Sharp) return 4186.01;
		}
		return defaultFrequency;
	}

	private static double octave8(char note, int type) {
		if (note == 'c') {
			if (type == Flat) return 3951.07;
			if (type == Natural) return 4186.01;
			if (type == Sharp) return 4434.92;
		}
		return defaultFrequency;
	}

}
