import java.util.Scanner;
import java.util.Random;

public class Main {
	static String[] strAlphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	static String[] refAlphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	static String strMessage;
	static int key;
	
	public static void main(String[] args) {
		//Welcome
		System.out.println("~~~~~~ Welcome To Caesar Cipher Simulator ~~~~~~");
		System.out.println("               By: Muhammad Alvi\n");
		
		//Encrypt or Decrypt:
		System.out.println("    --- What would you like to do today? ---\n      Encrypt or Decrypt (Type 'E' or 'D')\n");
		Scanner scannerType = new Scanner(System.in);
		System.out.print("Response: ");
		String type = scannerType.nextLine();
		type = scannerType.nextLine();
		
		//Corrects the user if they input any letter other than E or D
		if (type.charAt(0) == 69 || type.charAt(0) == 101) {
			System.out.println("\n- ENCRYPTION SELECTED -\n");
		} else if (type.charAt(0) == 68|| type.charAt(0) == 100) {
			System.out.println("\n- DECRYPTION SELECTED -\n");
		} else {
			System.out.println("\nPlease Type either 'E' or 'D'.\n");
			System.out.print("Response: ");
			type = scannerType.nextLine();
			if (type.charAt(0) == 69 || type.charAt(0) == 101) {
				System.out.println("\n- ENCRYPTION SELECTED -\n");
			} else if (type.charAt(0) == 68|| type.charAt(0) == 100) {
				System.out.println("\n- DECRYPTION SELECTED -\n");
			} else {
				System.out.println("\nOkay, lets try this again. Please respond either with the letter 'E' or the letter 'D'.\n");
				System.out.print("Response: ");
				type = scannerType.nextLine();
				if (type.charAt(0) == 69 || type.charAt(0) == 101) {
					System.out.println("\n- ENCRYPTION SELECTED -\n");
				} else if (type.charAt(0) == 68|| type.charAt(0) == 100) {
					System.out.println("\n- DECRYPTION SELECTED -\n");
				} else {
					System.out.println("\nOur systems have noticed that you are incapable of working this program. Goodbye.\n");
					System.exit(0);
				}
			}
		}
	
		//Ask for the Message:
		System.out.println("What is the message you would like to Encrypt/Decrypt? (Letters only)\n");
		Scanner scannerMessage = new Scanner(System.in);
		System.out.print("Response: ");
		String strMessage = scannerMessage.nextLine().toUpperCase().replaceAll("\\s","");
		System.out.println("\n- MESSAGE: " + strMessage + " -");
		
		//Ask for the Key:
		System.out.println("\nWhat is the alphabet shift? (Integer only)\n");
		Scanner scannerKey = new Scanner(System.in);
		System.out.print("Response: ");
		int key = scannerKey.nextInt();
		System.out.println("\n- KEY: " + key + " -\n\n");
		
		//If user inputs a key above 26 or below 0, it will correct itself
		if (key > 26) {
			key = key % 26;
		} else if (key < 0) {
			key = ((key+26) % 26);
		} else if (key == 0) {
			key = 26 - key;
		}
		
		long startTime = System.nanoTime(); //Starts a timer
		//Calls the appropriate method based off the user's choice
		if (type.charAt(0) == 69|| type.charAt(0) == 101) {
			Shift(key);
			encrypt(strMessage, key);
		} else if (type.charAt(0) == 68|| type.charAt(0) == 100) {
			Shift(key);
			decrypt(strMessage, key);
		}
		long endTime = System.nanoTime(); //Ends timer and displays elapsed time
		System.out.println("Completed in " + (endTime - startTime) + " ns"); 
		
	}
	
	
	private static String Shift(int key){
		//When the method is called, the alphabet begins to shift
		System.out.print("Shifted Alphabet: ");
		
		for (int i = 0; i < strAlphabet.length; i++) {
			
			//When the alphabet exceeds the number of letters, it will restart from A, B, etc.
			if (strAlphabet[i] == strAlphabet[strAlphabet.length - key]) {
				for (int j = i; j < strAlphabet.length; j++) {
					strAlphabet[i] = (refAlphabet[key + (i - 26)]);
					System.out.print(strAlphabet[i++]);
				}
			} else {
			strAlphabet[i] = (strAlphabet[i + key]);
			System.out.print(strAlphabet[i]);
			}
		}
		return null;
	}
	
	private static String encrypt(String strMessage, int key){
		//Shifts the message by a certain number (key)
		String newMessage = "";
		
		for (int i = 0; i < strMessage.length(); i++) {
			char letter = strMessage.charAt(i);
			if (letter > 90 - key) { //To make sure that "letter" doesn't start using symbols
				letter = (char) (letter - 26 + key);
				newMessage = newMessage + letter;
			} else {
			letter = (char) (letter + key);
			newMessage = newMessage + letter;
			}

		}
		chunkIt(newMessage);
		return null;
		
	}
	private static String decrypt(String strMessage, int key){
		//"Unshifts" the message by a certain number (key)
		String newMessage = "";
		key = 26 - key;
		for (int i = 0; i < strMessage.length(); i++) {
			char letter = strMessage.charAt(i);
			if (letter > 90 - key) { //To make sure that "letter" doesn't start using symbols
				letter = (char) (letter - 26 + key);
				newMessage = newMessage + letter;
			} else {
			letter = (char) (letter + key);
			newMessage = newMessage + letter;
			}
		}
		System.out.println("\nDecrypted Message: " + newMessage);
		return null;
		
	}

	private static String chunkIt(String newMessage){
		//Chunk the string in RANDOM groups. Example: ABCD --> A BC D
		String finalMessage = newMessage;
		Random randj = new Random();
		int j = randj.nextInt(newMessage.length()-1) + 1;
		Random randn = new Random();
		for (int i = j; i <= newMessage.length(); i++) {
			int n = randn.nextInt(newMessage.length() - 1) + 1;
			//Reduces the number of spaces that are right next to each other
			if (finalMessage.charAt(n) == 32) {
			} else {
				finalMessage = finalMessage.substring(0, n) + " " + finalMessage.substring(n, finalMessage.length());
			}
		}
		System.out.println("\nEncrypted Message: " + finalMessage);
		return null;
		
	}
	
}