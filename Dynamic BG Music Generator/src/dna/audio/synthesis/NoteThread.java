package dna.audio.synthesis;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class NoteThread extends Thread {
	// size of array is arbitrary
	byte[] playBuffer = new byte[16384];
	byte[] data;
	SongSynthesizer synth;

	public NoteThread(SongSynthesizer synth, byte[] audio) {
		data = audio;
		this.synth = synth;
	}

	public void run() {
		try {
			InputStream byteStream = new ByteArrayInputStream(data);
			AudioFormat format = synth.getAudioFormat();
			AudioInputStream stream = new AudioInputStream(byteStream, format, (long) (data.length / format.getFrameSize()));
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
			sourceDataLine.open(format);
			sourceDataLine.start();
			int cnt;
			while ((cnt = stream.read(playBuffer, 0, playBuffer.length)) != -1) {
				if (cnt > 0) {
					sourceDataLine.write(playBuffer, 0, cnt);
				}
			}
			sourceDataLine.drain();
			sourceDataLine.stop();
			sourceDataLine.close();
			synth.setPlayingNote(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
