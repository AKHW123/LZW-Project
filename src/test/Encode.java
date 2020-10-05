/*
 * Praise be to Ms. Kaufman and Computer Science A teachers.
 * They spoke the truth when they spoke of handwritten code and BlueJ.
 */

//package test;

//imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Encode {

	/**
	 * Compresses a file using the LZW algorithm
	 * 
	 * @param fileName       Path to file with uncompressed message.
	 * @param outputFileName Path to file where compressed message should be saved
	 * @throws IOException
	 */
	public static void encode(String fileName, String outputFileName) throws IOException {
		// initialize the dictionary
		HashMap<String, Integer> dictionary = new HashMap<String, Integer>();
		// initialize variables to hold the previous, current, and combined values
		String previous = "";
		String current = "";
		String combined = previous + current;
		// this value is the size of our initial dictionary.
		int value = 255;
		// initialize the variable that will hold the output, not including the
		// dictionary
		String output = "";
		// building our ASCII dictionary
		for (int i = 0; i < 256; i++) {
			dictionary.put((char) (i) + "", i);
		}
		// initialize a BufferedReader and a PrintWriter to read in and write to the
		// files
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
		// writing to the output file is done within this method so that at least the
		// dictionary entries can be written dynamically and without storing them in one
		// big String
		PrintWriter printWriter = new PrintWriter(outputFileName);
		// an int to hold the value of each character being read in
		int characterValue;
		// start reading the file
		while ((characterValue = bufferedReader.read()) != -1) {
			// set the current variable to the character currently being read in
			current = String.valueOf((char) characterValue);
			// set the combined variable to previous + current
			combined = previous + current;
			// if combined is either of length 1 or already in the dictionary, set previous
			// to previous + current
			if (combined.length() == 1 || dictionary.containsKey(combined)) {
				previous = previous + current;
			}
			// otherwise:
			else {
				// append the encoded value of previous to the output String
				output += ((int) dictionary.get(previous)) + " ";
				// increment the index
				value = value + 1;
				// put the new combination into the dictionary
				dictionary.put(combined, value);
				// write the new dictionary entry and its length to the output file
				printWriter.print("" + combined.length() + ":" + combined);
				// set previous to current
				previous = current;
			}

		}
		// print the x that separates dictionary entries and encoded values
		printWriter.print("x");
		// print the output String to the file
		printWriter.print(output);
		// print the final code to the file
		printWriter.print(dictionary.get(previous) + " ");
		// close the BufferedReader and the PrintWriter
		printWriter.close();
		bufferedReader.close();
	}
}

// 					  .
//					 / V\
//	  			   / `  /
//   			  <<   |
//   			  /    |
//  			/      |
//			  /        |
//			/    \   \ /
//		   (      )  | |
// ________|   _/_   | |
//<__________\______)\__)