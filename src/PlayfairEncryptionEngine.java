import java.util.Scanner;
import java.util.Queue;

/**
 * This class holds a menu driver for the program and makes sure all classes function together.
 * 
 * Name: Ishan Arefin
 * ID#: 112937865
 * Recitation: Monday: 11-11:53AM
 */
public class PlayfairEncryptionEngine {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		boolean quit = false; 
		System.out.print("Enter key phrase: ");
		String key = s.nextLine();
		KeyTable b = KeyTable.buildFromString(key);
		System.out.println("Generation success!\n");
		
		while(!quit) {
			System.out.println("Menu:\n(CK) - Change key\n"
					+ "(PK) - Print key\n(EN) - Encrypt\n(DE) - Decrypt\n(Q) - Quit");
			System.out.println("Please select an option: ");
			String input = s.nextLine();
			input = input.toUpperCase();
		
			if(input.equals("PK")) {
				System.out.println(b.toString());
			}
			
			if(input.equals("CK")) {
				System.out.print("Enter key phrase: ");
				key = s.nextLine();
				b = KeyTable.buildFromString(key);
				System.out.println("Generation success!\n");
			}
		
			if(input.equals("EN")) {
				System.out.println("Please enter a phrase to encrypt: ");
				String p = s.nextLine();
				p = p.replaceAll(" ", "");
				p = p.toUpperCase();
				String feed = "";
				for(int i = 0; i < p.length(); i++) {
					if(p.charAt(i) >= 'A' && p.charAt(i) <= 'Z') feed += p.charAt(i);
				}
				Phrase a = Phrase.buildPhraseFromStringforEnc(feed);
				System.out.println(a.toString(a.encrypt(b, a)));
			}
			
			if(input.equals("DE")) {
				System.out.println("Please enter a phrase to decrypt: ");
				String p = s.nextLine();
				p = p.replaceAll(" ", "");
				p = p.toUpperCase();
				String feed = "";
				for(int i = 0; i < p.length(); i++) {
					if(p.charAt(i) >= 'A' && p.charAt(i) <= 'Z') feed += p.charAt(i);
				}
				Phrase a = Phrase.buildPhraseFromStringforEnc(feed);
				System.out.println(a.toString(a.decrypt(b, a)));
			}
			
			if(input.equals("Q")) {
				quit = true;
				System.out.println("Program terminating....");
			}
		}
		
	}
}
