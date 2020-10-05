/*
 * Praise be to Ms. Kaufman and Computer Science A teachers.
 * They spoke the truth when they spoke of handwritten code and BlueJ. 
 */

import java.io.IOException;

public class Tester {
	public static void main(String[] args) throws IOException {
		// an encoder object
		Encode encoder = new Encode();
		// encode the given file
		Encode.encode("lzw-text0.txt", "lzw-text0_encoded.txt");
		// a decoder object
		Decode decoder = new Decode();
		// decode the encoded file
		System.out.println(decoder.decode("lzw-text0_encoded.txt"));
	}
}