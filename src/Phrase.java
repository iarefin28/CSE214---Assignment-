import java.util.LinkedList;
import java.util.Queue;
/**
 * This class represents a grid that contains the key for encryption and decryption that a playfair cipher uses.
 * 
 * Name: Ishan Arefin
 * ID#: 112937865
 * Recitation: Monday: 11-11:53AM
 */
public class Phrase extends LinkedList<Bigram> {
	/**
	 * Adds a bigram to the queue.
	 * @param b is a bigram that should be added to the queue.
	 */
	public void enqueue(Bigram b) {
		add(b);
	}
	
	/**
	 * Removes the first bigram from the queue.
	 * @return is of type bigram and is the bigram that is removed.
	 */
	public Bigram dequeue() {
		return (Bigram) removeFirst();
	}
	
	/**
	 * @return the bigram at the top of the queue without removing it.
	 */
	public Bigram peek() {
		return peek();
	}
	
	/**
	 * This function builds a phrase object from an input string.
	 * @param s The string that the phrase should be based upon
	 * @return A phrase object which is a queue that holds bigrams of the inputted string.
	 */
	public static Phrase buildPhraseFromStringforEnc(String s) {
		Phrase a = new Phrase();
		for(int i = 0; i < s.length(); i++) {
			Bigram n = new Bigram();
			n.setFirst(s.charAt(i));
			if(i == s.length()-1) {
				n.setFirst(s.charAt(i));
				n.setSecond('X');
			}
			else if(s.charAt(i) != s.charAt(i+1)) {
				n.setSecond(s.charAt(i+1));
				i++;
			}
			else {
				n.setSecond('X');
			}
			a.enqueue(n);
		}
		return a;
	}
	
	/**
	 * This function encrypts the phrase.
	 * @param key is the key table that should be used for encryption
	 * @param cur is the phrase that is to be encrypted.
	 * @return is another phrase object that contains bigrams of the encrypted text.
	 * @throws IllegalArgumentException if key is null.
	 */
	public Phrase encrypt(KeyTable key, Phrase cur) throws IllegalArgumentException {
		if(key == null) throw new IllegalArgumentException();
		Phrase rev = new Phrase();
		while(!cur.isEmpty()) {
			Bigram b = cur.dequeue();
			if(key.findCol(b.getFirst()) == key.findCol(b.getSecond())) {         //if both characters are in the same column
				Bigram n = new Bigram();
				if(key.findRow(b.getFirst()) == 4) {
					n.setFirst(key.getKeyTable()[0][key.findCol(b.getFirst())]);
					n.setSecond(key.getKeyTable()[key.findRow(b.getSecond())+1][key.findCol(b.getSecond())]);
				}
				else if(key.findRow(b.getSecond()) == 4) {
					n.setFirst(key.getKeyTable()[key.findRow(b.getFirst())+1][key.findCol(b.getFirst())]);
					n.setSecond(key.getKeyTable()[0][key.findCol(b.getSecond())]);
				}
				else {
					n.setFirst(key.getKeyTable()[key.findRow(b.getFirst())+1][key.findCol(b.getFirst())]);
					n.setSecond(key.getKeyTable()[key.findRow(b.getSecond())+1][key.findCol(b.getSecond())]);
				}
				rev.enqueue(n);
			}
			else if(key.findRow(b.getFirst()) == key.findRow(b.getSecond())) {         //if both characters are in the same row
				Bigram n = new Bigram();
				if(key.findCol(b.getFirst()) == 4) {
					n.setFirst(key.getKeyTable()[key.findRow(b.getFirst())][0]);
					n.setSecond(key.getKeyTable()[key.findRow(b.getSecond())][key.findCol(b.getSecond())+1]);
				}
				else if(key.findCol(b.getSecond()) == 4) {
					n.setFirst(key.getKeyTable()[key.findRow(b.getFirst())][key.findCol(b.getFirst())+1]);
					n.setSecond(key.getKeyTable()[key.findRow(b.getSecond())][0]);
				}
				else {
					n.setFirst(key.getKeyTable()[key.findRow(b.getFirst())][key.findCol(b.getFirst())+1]);
					n.setSecond(key.getKeyTable()[key.findRow(b.getSecond())][key.findCol(b.getSecond())+1]);
				}
				rev.enqueue(n);
			}
			else {
				Bigram n = new Bigram();
				n.setFirst(key.getKeyTable()[key.findRow(b.getFirst())][key.findCol(b.getSecond())]);
				n.setSecond(key.getKeyTable()[key.findRow(b.getSecond())][key.findCol(b.getFirst())]);
				rev.enqueue(n);
			}
		}
		return rev;
	}
	
	/**
	 * This function decrypts the phrase.
	 * @param key is the key table that should be used for decryption.
	 * @param cur is the phrase that is to be decrypted.
	 * @return is another phrase object that contains bigrams of the decrypted text.
	 * @throws IllegalArgumentException if key is null.
	 */
	public Phrase decrypt(KeyTable key, Phrase cur) throws IllegalArgumentException {
		if(key == null) throw new IllegalArgumentException();
		Phrase rev = new Phrase();
		while(!cur.isEmpty()) {
			Bigram b = cur.dequeue();
			if(key.findCol(b.getFirst()) == key.findCol(b.getSecond())) {         //if both characters are in the same column
				Bigram n = new Bigram();
				if(key.findRow(b.getFirst()) == 0) {
					n.setFirst(key.getKeyTable()[4][key.findCol(b.getFirst())]);
					n.setSecond(key.getKeyTable()[key.findRow(b.getSecond())-1][key.findCol(b.getSecond())]);
				}
				else if(key.findRow(b.getSecond()) == 0) {
					n.setFirst(key.getKeyTable()[key.findRow(b.getFirst())-1][key.findCol(b.getFirst())]);
					n.setSecond(key.getKeyTable()[4][key.findCol(b.getSecond())]);
				}
				else {
					n.setFirst(key.getKeyTable()[key.findRow(b.getFirst())-1][key.findCol(b.getFirst())]);
					n.setSecond(key.getKeyTable()[key.findRow(b.getSecond())-1][key.findCol(b.getSecond())]);
				}
				rev.enqueue(n);
			}
			else if(key.findRow(b.getFirst()) == key.findRow(b.getSecond())) {         //if both characters are in the same row
				Bigram n = new Bigram();
				if(key.findCol(b.getFirst()) == 0) {
					n.setFirst(key.getKeyTable()[key.findRow(b.getFirst())][4]);
					n.setSecond(key.getKeyTable()[key.findRow(b.getSecond())][key.findCol(b.getSecond())-1]);
				}
				else if(key.findCol(b.getSecond()) == 0) {
					n.setFirst(key.getKeyTable()[key.findRow(b.getFirst())][key.findCol(b.getFirst())-1]);
					n.setSecond(key.getKeyTable()[key.findRow(b.getSecond())][4]);
				}
				else {
					n.setFirst(key.getKeyTable()[key.findRow(b.getFirst())][key.findCol(b.getFirst())-1]);
					n.setSecond(key.getKeyTable()[key.findRow(b.getSecond())][key.findCol(b.getSecond())-1]);
				}
				rev.enqueue(n);
			}
			else {
				Bigram n = new Bigram();
				n.setFirst(key.getKeyTable()[key.findRow(b.getFirst())][key.findCol(b.getSecond())]);
				n.setSecond(key.getKeyTable()[key.findRow(b.getSecond())][key.findCol(b.getFirst())]);
				rev.enqueue(n);
			}
		}
		return rev;
	}
	
	/**
	 * @param cur is the phrase that is to be converted to a string.
	 * @return is a string representation of the phrase object.
	 */
	public String toString(Phrase cur) {
		String s = "";
		while(!cur.isEmpty()) {
			Bigram a = cur.dequeue();
			s += a.toString();
		}
		return s;
	}
	
}
