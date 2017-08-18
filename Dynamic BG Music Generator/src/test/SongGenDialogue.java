package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import dna.audio.DynamicMusic;
import dna.audio.MusicSynthEntry;
import dna.audio.synthesis.MusicScale;
import dna.audio.synthesis.SynthSongScaledGen;

@SuppressWarnings("serial")
public class SongGenDialogue extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textSongLen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SongGenDialogue frame = new SongGenDialogue();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SongGenDialogue() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Failed to make window look localized.");
			e.printStackTrace();
		}
		setTitle("Song Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 311, 121);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JButton btnPlay = new JButton("PLAY");
		contentPane.add(btnPlay, BorderLayout.SOUTH);
		btnPlay.setActionCommand("play");
		btnPlay.addActionListener(this);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblSongLength = new JLabel("Song Length (Seconds)");
		lblSongLength.setBounds(10, 8, 169, 14);
		panel.add(lblSongLength);

		textSongLen = new JTextField();
		textSongLen.setText("10");
		textSongLen.setBounds(189, 5, 86, 20);
		panel.add(textSongLen);
		textSongLen.setColumns(10);
	}

	public JTextField getTextField() {
		return textSongLen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("play")) {
			// generate and play the song
			String text = textSongLen.getText();
			boolean decimal = false;
			int i = 0;
			double d = 0;
			try {
				d = Double.parseDouble(text);
				decimal = true;
			} catch (Exception e1) {
				i = Integer.parseInt(text);
			}
			if (!decimal) d = i;
			DynamicMusic dm = new DynamicMusic();
			SynthSongScaledGen synth = new SynthSongScaledGen(MusicScale.Pentatonic, 0.25f, 0.9999f, 30000f, 0.2f, 1.0f, (float) d);
			synth.refreshSong();
			dm.addSong(new MusicSynthEntry(synth, "procedural", -1));
			dm.start();
		}
	}
}
