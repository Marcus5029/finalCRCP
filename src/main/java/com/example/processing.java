package com.example;

// import java.util.ArrayList;

// import processing.core.PApplet;

// import processing.sound;

// public class processing {

//     PApplet myMain;
//     processing(PApplet mainProgram){
//         myMain = mainProgram;
//     }
  
// MidiFile midi;
// int noteCount = 0;
// ArrayList<Square> squares = new ArrayList<Square>();

// void setup() {
//   size(800, 800);
//   // Initialize MIDI processing here
// }

// void draw() {
//   background(255);
  
//   // Update and draw squares
//   for (int i = squares.size() - 1; i >= 0; i--) {
//     Square square = squares.get(i);
//     square.update();
//     square.display();
//     if (square.isFaded()) {
//       squares.remove(i);
//     }
//   }

//   // Process MIDI and add squares based on notes
//   // ...

//   // Remove older squares after every 50 notes
//   if (noteCount % 50 == 0) {
//     fadeOldestSquares();
//   }
// }

// void fadeOldestSquares() {
//   // Implement logic to start fading the oldest squares
//   // ...
// }

// class Square {
//   float x, y;
//   color c;
//   float alpha = 255;

//   Square(float x, float y, color c) {
//     this.x = x;
//     this.y = y;
//     this.c = c;
//   }

//   void update() {
//     // Fade logic
//     alpha -= 2;
//     if (alpha < 0) alpha = 0;
//   }

//   void display() {
//     fill(c, alpha);
//     rect(x, y, 50, 50); // Adjust size as needed
//   }

//   boolean isFaded() {
//     return alpha <= 0;
//   }
// }

// // Add your MIDI processing functions and logic
// // ...

// }
