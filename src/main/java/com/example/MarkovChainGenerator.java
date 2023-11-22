package com.example;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.example.MarkovChainGenerator.SortTTOutput;


public class MarkovChainGenerator<E> extends ProbabilityGenerator<E> {
	// start coding here

	ArrayList<ArrayList<Float>> transitionTable = new ArrayList<>();

	 ProbabilityGenerator<E> probGen = new ProbabilityGenerator<>();

	void train(ArrayList<E> data) {
		probGen.train(data);
		int lastIndex = -1;
		int tokenIndex = 0;
		for (int i = 0; i < data.size(); i++) {
			tokenIndex = alphabet.indexOf(data.get(i));
			if (tokenIndex == -1) {
				tokenIndex = alphabet.size();
				ArrayList<Float> myRow = new ArrayList<>();
				for (int j = 0; j < alphabet.size(); j++) {
					float z = 0;
					myRow.add(z);
				}
				transitionTable.add(myRow);
				for (int j = 0; j < transitionTable.size(); j++) {
					float z = 0;
					transitionTable.get(j).add(z);
				}
				alphabet.add(data.get(i));

			}
			// ArrayList<Float> myRow = new ArrayList<>();
			// for (int j = 0; j < alphabet.size(); j++) {
			// float z = 0;
			// myRow.add(z);
			// }
			// transitionTable.add(myRow);

			// for (int j = 0; j < transitionTable.size(); j++) {
			// float z = 0;
			// transitionTable.get(j).add(z);
			// }
			// alphabet.add(data.get(i));

			if (lastIndex > -1) {
				float x = transitionTable.get(lastIndex).get(tokenIndex);
				x++;
				transitionTable.get(lastIndex).set(tokenIndex, x);

			}
			lastIndex = tokenIndex;
		}
	}
	ArrayList<ArrayList<Float>> probDist() {
		//printProbabilityDistribution(false, alphabet, transitionTable);
	
		//create arraylist of arraylists
		ArrayList<ArrayList<Float>> result = new ArrayList<>();
	
		for (int i = 0; i < transitionTable.size(); i++) {
			int avg = 0;
	
			// Calculate average
			for (int j = 0; j < transitionTable.get(i).size(); j++) {
				avg += transitionTable.get(i).get(j);
			}
	
			//create an ArrayList for the row
			ArrayList<Float> row = new ArrayList<>();
	
			for (int j = 0; j < transitionTable.get(i).size(); j++) {
				if (transitionTable.get(i).get(j) != 0) {
					float newVal = (float) transitionTable.get(i).get(j) / avg;
					row.add(newVal);  //add newVal to the row
				} else {
					row.add(0f);  // Add 0 if the original value was 0
				}
			}
	
			result.add(row);  // Add the row to the result list
		}
	
		//return 2d array list you created
		return result;
	}
	public E generateSymbol(E initToken) {
    int initTokenIndex = alphabet.indexOf(initToken);
    if (initTokenIndex == -1) {
        // Handle the case where initToken is not found in the alphabet.
        return null;
    }
	ArrayList<Float> row = transitionTable.get(initTokenIndex);

    // Use your existing function for generating symbols from a probability distribution.
    // You may need to modify it to accommodate your new needs.
    E generatedSymbol = super.generate();

    return generatedSymbol;
}

public ArrayList<E> generate(E initToken, int numSymbolsToGenerate) {
	ArrayList<E> generated = new ArrayList<>();
	// for 0 to numSymbols to generate{
	// e token - generateSymbol(initToken);
	// set initToken to token
	// add token to generated
	// }
	for (int i = 0; i < numSymbolsToGenerate; i++) {
		E token = generateSymbol(initToken);
		generated.add(token);
		initToken = token;
	}
	return generated;
}

public ArrayList<E> generate(int numSymbolsToGenerate){ 
	E initToken = probGen.generate(); 

		return generate(initToken, numSymbolsToGenerate);
}

	
 







	// nested convenience class to return two arrays from sortTransitionTable()
	// method
	// students do not need to use this class
	protected class SortTTOutput {
		public ArrayList<E> symbolsListSorted;
		ArrayList<ArrayList<Float>> ttSorted;
	}

	// sort the symbols list and the counts list, so that we can easily print the
	// probability distribution for testing
	// symbols -- your alphabet or list of symbols (input)
	// tt -- the unsorted transition table (input)
	// symbolsListSorted -- your SORTED alphabet or list of symbols (output)
	// ttSorted -- the transition table that changes reflecting the symbols sorting
	// to remain accurate (output)
	public SortTTOutput sortTT(ArrayList<E> symbols, ArrayList<ArrayList<Float>> tt) {

		SortTTOutput sortArraysOutput = new SortTTOutput();

		sortArraysOutput.symbolsListSorted = new ArrayList<E>(symbols);
		sortArraysOutput.ttSorted = new ArrayList<ArrayList<Float>>();

		// sort the symbols list
		Collections.sort(sortArraysOutput.symbolsListSorted, new Comparator<E>() {
			@Override
			public int compare(E o1, E o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});

		// use the current sorted list to reference the counts and get the sorted counts
		for (int i = 0; i < sortArraysOutput.symbolsListSorted.size(); i++) {
			int index = symbols.indexOf(sortArraysOutput.symbolsListSorted.get(i));
			sortArraysOutput.ttSorted.add(new ArrayList<Float>());
			for (int j = 0; j < tt.get(index).size(); j++) {
				int index2 = symbols.indexOf(sortArraysOutput.symbolsListSorted.get(j));
				sortArraysOutput.ttSorted.get(i).add(tt.get(index).get(index2));
			}
		}

		return sortArraysOutput;

	}
	// public float Sum(ArrayList<Float> rowList){
	// 	float sum = 0.0f;
	// 	for(int i = 0; i < rowList.size(); i++){
	// 		sum+= rowList.get(i);
	// 	}
	// 	if(sum == 0.0f){
	// 		sum = 1.0f;
	// 	}
	// 	return sum;
	// 	}
		

	

	// this prints the transition table
	// symbols - the alphabet or list of symbols found in the data
	// tt -- the transition table of probabilities (not COUNTS!) for each symbol
	// coming after another
	public void printProbabilityDistribution(boolean round, ArrayList<E> symbols, ArrayList<ArrayList<Float>> tt) {
		//overload 
		// sort the transition table
		SortTTOutput sorted = sortTT(symbols, tt);
		symbols = sorted.symbolsListSorted;
		tt = sorted.ttSorted;

		System.out.println("-----Transition Table -----");

		System.out.println(symbols);

		for (int i = 0; i < tt.size(); i++) {
			System.out.print("[" + symbols.get(i) + "] ");
			for (int j = 0; j < tt.get(i).size(); j++) {
				// float valueOfIndexTT = tt.get(i).get(j);
				// float sum = Sum(tt.get(i));
				// float probability = valueOfIndexTT/sum;
				if (round) {
					DecimalFormat df = new DecimalFormat("#.##");
					System.out.print(df.format((double) tt.get(i).get(j)) + " ");
				} else {
					System.out.print((double) tt.get(i).get(j) + " ");
				}

			}
			System.out.println();

		}
		System.out.println();

		System.out.println("------------");
	}
	public void printProbabilityDistribution(boolean round){
		 printProbabilityDistribution(round, alphabet,probDist());

	}
}
