  /*
 * c2017-2023 Courtney Brown (NOTE: you'll have to change the name and give me a bit of credit!)
 * 
 * Class: ProbabliityGenerator
 * 
 */


package com.example;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProbabilityGenerator <E>
{
ArrayList<E> alphabet = new ArrayList<E>();
ArrayList<Float> tokenCounts = new ArrayList<Float>();
double tokenCount = 0; 

void train(ArrayList<E> data)
{
	tokenCount +=data.size();
	// for i = 0 to size of newtokens - 1
	for (int i = 0; i< data.size();i++)
	{

		//index = find index of newtokens[i] in alphabet
			int index = alphabet.indexOf(data.get(i));
			//if (index is NOT found)
			if (index == -1) {

				index = alphabet.size();
                alphabet.add(data.get(i));
				tokenCounts.add(0.0f);
			
			} 
			float val = tokenCounts.get(index)+1.0f;
				tokenCounts.set(index,val);


					//index = size of alphabet

					// add newtokens[i] to alphabet container/array

					//add a 0 to your alphabet counts array  




		   

			// alphabet_counts[index]++ //note – syntax will look different – eg. if using ArrayList
	}
}

public E generate() {
	// for loop that says for X times that i want the loop to generate
	ArrayList<Float> prob = new ArrayList<Float>();
	ArrayList<Float> probSum = new ArrayList<Float>();

	for (int i = 0; i < tokenCounts.size(); i++) {
		float val = tokenCounts.get(i) / (float) tokenCount;
		prob.add(val);

	}
	float sum = 0;

	for (int i = 0; i < tokenCounts.size(); i++ ){
		float proba = prob.get(i);
		 sum += proba;
		 probSum.add(sum);
		
	}
	int index = 0;
	float r =(float) Math.random();
	for (int i = 0; i < tokenCounts.size(); i++){
		float proba = probSum.get(i);
		if (r < proba){
			index = i;
			break;
		}
	}
		// System.out.println(r);	
	
	// for (int i = 0; i < tokenCounts.size(); i++){

	// }
	// System.out.println(prob);
	return alphabet.get(index);

}



public ArrayList<E>generate(int count){
	ArrayList<E> generatesToken = new ArrayList<E>();
	for (int i = 0; i < count; i++){
		E token = generate(); 
		generatesToken.add(token);
		
	}
	return generatesToken;
	

}

    
	//nested convenience class to return two arrays from sortArrays() method
	//students do not need to use this class
	protected class SortArraysOutput
	{
		public ArrayList<E> symbolsListSorted;
		public ArrayList<Float> symbolsCountSorted;
	}

	//sort the symbols list and the counts list, so that we can easily print the probability distribution for testing
	//symbols -- your alphabet or list of symbols (input)
	//counts -- the number of times each symbol occurs (input)
	//symbolsListSorted -- your SORTED alphabet or list of symbols (output)
	//symbolsCountSorted -- list of the number of times each symbol occurs inorder of symbolsListSorted  (output)
	public SortArraysOutput sortArrays(ArrayList<E> symbols, ArrayList<Float> counts)	{

		SortArraysOutput sortArraysOutput = new SortArraysOutput(); 
		
		sortArraysOutput.symbolsListSorted = new ArrayList<E>(symbols);
		sortArraysOutput.symbolsCountSorted = new ArrayList<Float>();
	
		//sort the symbols list
		Collections.sort(sortArraysOutput.symbolsListSorted, new Comparator<E>() {
			@Override
			public int compare(E o1, E o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});

		//use the current sorted list to reference the counts and get the sorted counts
		for(int i=0; i<sortArraysOutput.symbolsListSorted.size(); i++)
		{
			int index = symbols.indexOf(sortArraysOutput.symbolsListSorted.get(i));
			sortArraysOutput.symbolsCountSorted.add(counts.get(index));
		}

		return sortArraysOutput;

	}
	
	//Students should USE this method in your unit tests to print the probability distribution
	//HINT: you can overload this function so that it uses your class variables instead of taking in parameters
	//boolean is FALSE to test train() method & TRUE to test generate() method
	//symbols -- your alphabet or list of symbols (input)
	//counts -- the number of times each symbol occurs (input)
	//sumSymbols -- the count of how many tokens we have encountered (input)
	public void printProbabilityDistribution(boolean round, ArrayList<E> symbols, ArrayList<Float> counts, double sumSymbols)
	{
		//sort the arrays so that elements appear in the same order every time and it is easy to test.
		SortArraysOutput sortResult = sortArrays(symbols, counts);
		ArrayList<E> symbolsListSorted = sortResult.symbolsListSorted;
		ArrayList<Float> symbolsCountSorted = sortResult.symbolsCountSorted;

		System.out.println("-----Probability Distribution-----");
		
		for (int i = 0; i < symbols.size(); i++)
		{
			if (round){
				DecimalFormat df = new DecimalFormat("#.##");
				System.out.println("Data: " + symbolsListSorted.get(i) + " | Probability: " + df.format((double)symbolsCountSorted.get(i) / sumSymbols));
			}
			else
			{
				System.out.println("Data: " + symbolsListSorted.get(i) + " | Probability: " + (double)symbolsCountSorted.get(i) / sumSymbols);
			}
		}
		
		System.out.println("------------");
	}
	public void printProbabilityDistribution(boolean round)
{
	printProbabilityDistribution(round,alphabet, tokenCounts, tokenCount);
}

}

