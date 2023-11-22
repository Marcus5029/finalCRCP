/*
 * c2017-2023 Courtney Brown 
 * Class: Project 2 Template
 * Description: This is a template for the project 2 code, which is an implementation of a Markov chain of order 1
 */

package com.example;

//importing the JMusic stuff
import jm.music.data.*;
import jm.util.*;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.ArrayList;

// import com.example.processing.Square;

import processing.core.*;

//make sure this class name matches your file name, if not fix.
public class App extends PApplet {

	static MelodyPlayer player; // play a midi sequence
	static MidiFileToNotes midiNotes; // read a midi file
	static int noteCount = 0;

	// make cross-platform
	static FileSystem sys = FileSystems.getDefault();

	// the getSeperator() creates the appropriate back or forward slash based on the
	// OS in which it is running -- OS X & Windows use same code :)
	static String filePath = "mid" + sys.getSeparator() + "MaryHadALittleLamb.mid"; // path to the midi file -- you can
																					// change this to your file
	// location/name

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int whichTest = Integer.parseInt(args[0]);

		if (args.length >= 1 && whichTest == 2){
			midiSetup(filePath); 
			UnitTest test  = new UnitTest(midiNotes);
			test.test3();
		}
		else 
		PApplet.main("com.example.App");

		// run the unit tests
		
		// setup the melody player
		// uncomment below when you are ready to test or present sound output
		// make sure that it is commented out for your final submit to github (eg. when
		// pushing)
		// setup();
		// playMelody();
		// UnitTest unitTest = new UnitTest(midiNotes);
		// unitTest.test2();
		// uncomment to debug your midi file
		// this code MUST be commited when submitting unit tests or any code to github
		// playMidiFileDebugTest(filePath);
	}

	// doing all the setup stuff
	public void setup() {

		// playMidiFile(filePath); //use to debug -- this will play the ENTIRE file --
		// use ONLY to check if you have a valid path & file & it plays
		// it will NOT let you know whether you have opened file to get the data in the
		// form you need for the assignment

		midiSetup(filePath);
		ProbabilityGenerator<Integer> pitchGen = new ProbabilityGenerator<Integer>();
		ProbabilityGenerator<Double> rhythmGen = new ProbabilityGenerator<Double>();

		pitchGen.train(midiNotes.getPitchArray());
		rhythmGen.train(midiNotes.getRhythmArray());

		ArrayList<Integer> pitches = pitchGen.generate(200);
		ArrayList<Double> rhythms = rhythmGen.generate(200);
		player.setMelody(pitches);
		player.setRhythm(rhythms);
		background(255, 255, 255);

	}

	public void settings() {
		size(800, 800);
	}

	public void draw() {
		// background(255, 255, 255);

		int note = playMelody();
		// println("Hello");
		if (note != -1) {
			fill((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
			rect((int) (Math.random() * 800), (int) (Math.random() * 800), (int) (Math.random() * 255),
					(int) (Math.random() * 255));
		}
	}

	void fadeOldestSquares() {
		// Implement logic to start fading the oldest squares
		// ...
		// for (int i = 0; i < squares.size(); i++) {
		// Square square = squares.get(i);
		// // Increase the fading rate for the oldest squares
		// square.alpha -= 5;
	}

	class Square {
		PApplet parent; // Add a reference to the PApplet
		float x, y;
		int c; // Use int for color
		float alpha = 255;

		Square(PApplet parent, float x, float y, int c) {
			this.parent = parent;
			this.x = x;
			this.y = y;
			this.c = c;
		}

		void update() {
			alpha -= 2;
			if (alpha < 0)
				alpha = 0;
		}

		void display() {
			parent.fill(c, alpha);
			parent.rect(x, y, 50, 50); // Adjust size as needed
		}

		boolean isFaded() {
			return alpha <= 0;
		}
	}
	// float x, y;
	// int c; //use int for color
	// float alpha = 255;

	// Square(float x, float y, int c) {
	// this.x = x;
	// this.y = y;
	// this.c = c;
	// }

	// void update() {
	// // Fade logic
	// alpha -= 2;
	// if (alpha < 0) alpha = 0;
	// }

	// void display() {
	// fill(c, alpha);
	// rect(x, y, 50, 50); // Adjust size as needed
	// }

	// boolean isFaded() {
	// return alpha <= 0;
	// }
	// }

	// Add your MIDI processing functions and logic
	// ...

	// plays the midi file using the player -- so sends the midi to an external
	// synth such as Kontakt or a DAW like Ableton or Logic
	public Integer playMelody() {
		ArrayList<Square> squares = new ArrayList<Square>();

		assert (player != null); // this will throw an error if player is null -- eg. if you haven't called
									// setup() first

		if (!player.atEndOfMelody()) {
			int note = player.play(); // play each note in the sequence -- the player will determine whether is time
							// for a note onset

			// Square square = new Square(, (int)(Math.random() * 800), (int)(Math.random()
			// * 800), (int)(Math.random() * 255));
			// square.update();
			// square.display();
			// if (square.isFaded()) {
			// squares.remove(i);
			// }

			// Remove older squares after every 50 notes
			if (noteCount % 50 == 0) {
				// fadeOldestSquares();
			}
			return note;

		}
		return -1;

	}

	// opens the midi file, extracts a voice, then initializes a melody player with
	// that midi voice (e.g. the melody)
	// filePath -- the name of the midi file to play
	static void midiSetup(String filePath) {

		// Change the bus to the relevant port -- if you have named it something
		// different OR you are using Windows
		player = new MelodyPlayer(100, "Microsoft GS Wavetable Synth"); // sets up the player with your bus.

		midiNotes = new MidiFileToNotes(filePath); // creates a new MidiFileToNotes -- reminder -- ALL objects in Java
													// must
													// be created with "new". Note how every object is a pointer or
													// reference. Every. single. one.

		// // which line to read in --> this object only reads one line (or ie, voice or
		// ie, one instrument)'s worth of data from the file
		midiNotes.setWhichLine(0); // this assumes the melody is midi channel 0 -- this is usually but not ALWAYS
									// the case, so you can try other channels as well, if 0 is not working out for
									// you.

		noteCount = midiNotes.getPitchArray().size(); // get the number of notes in the midi file

		assert (noteCount > 0); // make sure it got some notes (throw an error to alert you, the coder, if not)

		// sets the player to the melody to play the voice grabbed from the midi file
		// above
		player.setMelody(midiNotes.getPitchArray());
		player.setRhythm(midiNotes.getRhythmArray());
	}

	static void resetMelody() {
		player.reset();

	}

	// this function is not currently called. you may call this from setup() if you
	// want to test
	// this just plays the midi file -- all of it via your software synth. You will
	// not use this function in upcoming projects
	// but it could be a good debug tool.
	// filename -- the name of the midi file to play
	static void playMidiFileDebugTest(String filename) {
		Score theScore = new Score("Temporary score");
		Read.midi(theScore, filename);
		Play.midi(theScore);
	}

}
