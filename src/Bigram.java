/**
 * This class represents a bigram which is a collection of two characters. 
 * 
 * Name: Ishan Arefin
 * ID#: 112937865
 * Recitation: Monday: 11-11:53AM
 */

public class Bigram {
	private char first;
	private char second;
	
	/**
	 * Instantiates the first and second letters of the bigram to empty characters.
	 */
	public Bigram() {
		first = ' ';
		second = ' ';
	}
	
	/**
	 * This function obtains the first character in the bigram.
	 * @return is of type char and returns the first character in the bigram.
	 */
	public char getFirst() {
		return first;
	}
	
	/**
	 * This function changes the first character in the bigram.
	 * @param first is the letter that the first letter in the bigram should be changed to.
	 */
	public void setFirst(char first) {
		this.first = first;
	}
	
	/**
	 * This function obtains the second character in the bigram.
	 * @return is of type char and returns the second character in the bigram.
	 */
	public char getSecond() {
		return second;
	}
	
	/**
	 * This function changes the first character in the bigram.
	 * @param second is the letter that the second letter in the bigram should be changed too.
	 */
	public void setSecond(char second) {
		this.second = second;
	}
	
	/**
	 * @return a string representation of the bigram object.
	 */
	public String toString() {
		String s = "";
		s += first;
		s += second;
		return s;
	}
}
