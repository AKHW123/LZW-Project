package test;

//imports
import java.util.*;
import java.io.*;

public class Encode
{
	public static void main(String[] args) throws IOException
	{
		//takes in lzw-text0.txt, outputs to lzw-text0_encoded.txt (can be changed, of course)
		encode("lzw-text0.txt", "lzw-text0_encoded.txt");
	}
	//takes in a filename and an outputFileName
	public static void encode(String filename, String outputFileName) throws IOException
	{
		//initialize the dictionary
		HashMap<String, Integer> dictionary = new HashMap<String, Integer>();
		//initialize variables to hold the previous, current, and combined values
		String previous = "";
		String current = "";
		String combined = previous+current;
		//this value is the size of our initial dictionary.
		int value = 255;
		//initialize the variable that will hold the output, not including the dictionary
		String output = "";
		//building our ASCII dictionary
		for(int i = 0; i < 256; i++)
		{
			dictionary.put((char)(i)+"", i);
		}
		//initialize a BufferedReader and a PrintWriter to read in and write to the files
		BufferedReader br = new BufferedReader(new FileReader(filename));
		//writing to the output file is done within this method so that at least the dictionary entries can be written dynamically and without storing them in one big String
		PrintWriter pw = new PrintWriter (outputFileName);
		//an int to hold the value of each character being read in
		int a;
		//start reading the file
		while((a = br.read()) != -1)
		{
			//set the current variable to the character currently being read in
			current = String.valueOf((char)a);
			//set the combined variable to previous + current
			combined = previous+current;
			//if combined is either of length 1 or already in the dictionary, set previous to previous + current
			if(combined.length() == 1 || dictionary.containsKey(combined))
			{
				previous = previous + current;
			}
			//otherwise:
			else
			{
				//append the encoded value of previous to the output String
				output += ((int)dictionary.get(previous))+" ";
				//increment the index
				value = value + 1;
				//put the new combination into the dictionary
				dictionary.put(combined,value);
				//write the new dictionary entry and its length to the output file
				pw.print("" + combined.length() + ":" + combined);
				//set previous to current
				previous = current;
			}
			
		}
		//print the x that separates dictionary entries and encoded values
		pw.print("x");
		//print the output String to the file
		pw.print(output);
		//print the final code to the file
		pw.print(dictionary.get(previous)+ " ");
		//close the BufferedReader and the PrintWriter
		pw.close();
		br.close();
	}
}