/**
 * This class represents a grid that contains the key for encryption and decryption that a playfair cipher uses.
 * 
 * Name: Ishan Arefin
 * ID#: 112937865
 * Recitation: Monday: 11-11:53AM
 */

public class KeyTable {
	private char[][] key; 
	
	/**
	 * This creates a key table object that is a 5x5 grid. It is empty at creation.
	 */
	public KeyTable() {
		key = new char[5][5];
	}
	
	/**
	 * This function creates a new key table given a specific key. It also creates a default key table if no key is entered.
	 * @param keyphrase is a string that makes each key table unique.
	 * @return is of type KeyTable. It returns that KeyTable object that was created from the key.
	 * @throws IllegalArgumentException if keyphrase is null. 
	 */
	public static KeyTable buildFromString(String keyphrase) throws IllegalArgumentException {
		if(keyphrase == null) throw new IllegalArgumentException();
		keyphrase = keyphrase.replaceAll(" ", "");
		keyphrase = keyphrase.toUpperCase();
		keyphrase = keyRevise(keyphrase);
		int l = 0;
		char x = 'A';
		KeyTable a = new KeyTable();
		for(int row = 0; row < 5; row++) {
			for(int col = 0; col < 5; col++) {
				if(l != keyphrase.length() && Character.isLetter(keyphrase.charAt(l))) {
					a.key[row][col] = keyphrase.charAt(l);
					l++;
				}
				else {
					if(keyphrase.indexOf(x) == -1 && x != 'J') {
						a.getKeyTable()[row][col] = x;
						x++;
					}
					else {
						x++;
						col--;
					}
				}
			}
		}
		return a;
	}
	
	/**
	 * Helper function for the buildFromString(String keyphrase) function.
	 * @param keyphrase is the original key that the user wants to use in the key table.
	 * @return is of type string which returns a proper keyphrase with no duplicate characters.
	 */
	public static String keyRevise(String keyphrase) {
		String s = "";
		for(int i = 0; i < keyphrase.length(); i++) {
			if(s.indexOf(keyphrase.charAt(i)) == -1) {
				s += keyphrase.charAt(i);
			}
		}
		return s;
	}
	
	/**
	 * Gets the key table that was generated. 
	 * @return is a 2D char array that is the key table.
	 */
	public char[][] getKeyTable(){
		return key;
	}
	
	/**
	 * This function finds the row in the key table of a specific character.
	 * @param c is the character the user wants to find.
	 * @return is of type int and it returns the row that the character is located on
	 * @throws IllegalArgumentException if c is not a valid letter in the key matrix.
	 */
	public int findRow(char c) throws IllegalArgumentException {
		if((!((c >= 'a' && c <= 'z')||(c >= 'A' && c <= 'Z')))||c == 'j' || c =='J')
			throw new IllegalArgumentException();
		int r = 0;
		for(int row = 0; row < 5; row++) {
			for(int col = 0; col < 5; col++) {
				if(key[row][col] == c) r = row;
			}
		}
		return r;
	}
	
	/**
	 * This function finds the column in the key table of a specific character.
	 * @param c is the character the user wants to find.
	 * @return is of type int and it returns the column that the character is located on.
	 * @throws IllegalArgumentException if c is not a valid letter in the key matrix.
	 */
	public int findCol(char c) throws IllegalArgumentException {
		if((!((c >= 'a' && c <= 'z')||(c >= 'A' && c <= 'Z')))||c == 'j' || c =='J')
			throw new IllegalArgumentException();
		int co = 0;
		for(int row = 0; row < 5; row++) {
			for(int col = 0; col < 5; col++) {
				if(key[row][col] == c) co = col;
			}
		}
		return co;
	}
	
	/**
	 * @return Returns a string representation of the key table.
	 */
	public String toString() {
		String s = "";
		for(int col = 0; col < 5; col++) {
			for(int row = 0; row < 5; row++) {
				s += key[col][row] + " ";
			}
			s += "\n";
		}
		return s;
	}
}
