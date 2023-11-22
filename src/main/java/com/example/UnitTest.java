package com.example;

import java.util.ArrayList;

public class UnitTest {

    MidiFileToNotes midiNotes;

    UnitTest(MidiFileToNotes midiNotes){
        this.midiNotes = midiNotes;
    }


    void test(){
        ProbabilityGenerator<Integer> pitchGen = new ProbabilityGenerator<Integer>();
        ProbabilityGenerator<Double> rhythmGen = new ProbabilityGenerator<Double>();

        pitchGen.train(midiNotes.getPitchArray());
        rhythmGen.train(midiNotes.getRhythmArray());

        pitchGen.printProbabilityDistribution(false);
        rhythmGen.printProbabilityDistribution(false);

        ProbabilityGenerator<Integer> pitchGen2 = new ProbabilityGenerator<Integer>();
        ProbabilityGenerator<Double> rhythmGen2 = new ProbabilityGenerator<Double>();

        for(int i = 0 ; i<10000; i++){
            ArrayList<Integer> pitches = pitchGen.generate(20);
             ArrayList<Double> rhythms = rhythmGen.generate(20);

             pitchGen2.train(pitches);
             rhythmGen2.train(rhythms);
        }
        pitchGen2.printProbabilityDistribution(true);
        rhythmGen2.printProbabilityDistribution(true);

    


    }
    void test2(){
        MarkovChainGenerator<Integer> pitchGen = new MarkovChainGenerator<Integer>();
        MarkovChainGenerator<Double> rhythmGen = new MarkovChainGenerator<Double>();

        pitchGen.train(midiNotes.getPitchArray());
        rhythmGen.train(midiNotes.getRhythmArray());

        pitchGen.printProbabilityDistribution(false);
        rhythmGen.printProbabilityDistribution(false);

        MarkovChainGenerator<Integer> pitchGen2 = new MarkovChainGenerator<Integer>();
        MarkovChainGenerator<Double> rhythmGen2 = new MarkovChainGenerator<Double>();

    }

       void test3(){
         MarkovChainGenerator<Integer> pitchGen = new MarkovChainGenerator<Integer>();
        MarkovChainGenerator<Double> rhythmGen = new MarkovChainGenerator<Double>();

        pitchGen.train(midiNotes.getPitchArray());
        rhythmGen.train(midiNotes.getRhythmArray());

        pitchGen.printProbabilityDistribution(false);
        rhythmGen.printProbabilityDistribution(false);

       MarkovChainGenerator<Integer> pitchGen2 = new MarkovChainGenerator<Integer>();
       MarkovChainGenerator<Double> rhythmGen2 = new MarkovChainGenerator<Double>();

        for(int i = 0 ; i<10000; i++){
            ArrayList<Integer> pitches = pitchGen.generate(20);
             ArrayList<Double> rhythms = rhythmGen.generate(20);

             pitchGen2.train(pitches);
             rhythmGen2.train(rhythms);
        }
        pitchGen2.printProbabilityDistribution(true);
        rhythmGen2.printProbabilityDistribution(true);

    

    }

}
