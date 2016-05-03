import java.util.ArrayList;
import java.util.Scanner;

public class EncryptionMain {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.println("Welcome to the Encryption Program");
		initiationSequence(input);

	}

	public static void initiationSequence(Scanner input) {
		int answer;
		do {
			System.out.println("Are you generating a new Encrypted message? or decoding an existing message?: ");
			System.out.println("\t\t\t '1' Generating New Message.");
			System.out.println("\t\t\t '2' Decode an existing message");
			System.out.print("Input your response here: ");
			answer = Validator.getInt(input);
			if ((answer < 1) || (answer > 2)) {
				System.out.println("\nInvalid Entry.\n");
			}
		} while ((answer < 1) || (answer > 2));

		if (answer == 1) {
			generateEncryptedMessage();

		} else {
			decodeExistingMessage(input);
		}
	}

	public static void decodeExistingMessage(Scanner input) {
		int answer;
		ArrayList<Long> encodedMessage;
		Decryption key = new Decryption();

		System.out.println(
				"\nYou've chosen to decode an existing message. I will walk you through the process of doing that.\n");
		System.out.print(
				"Please enter the Decoding Integer (Hint: This is the number that is at the top of the Key File: ");
		answer = Validator.getInt(input);
		key.setDecodingInteger(answer);
		System.out.println("\nGreat! Now enter the public key (Hint: This is the second number in the Key File: ");
		answer = Validator.getInt(input);
		key.setPrimeMultiple(answer);

		System.out.println("Copy and paste the encrypted message into the ecryptedtext.txt file.");
		encodedMessage = Encryption.readEncodedMessageFromFile();

		key.decodeMessage(encodedMessage);
	}

	public static void generateEncryptedMessage() {
		ArrayList<Long> encodedMessage;
		System.out.println("");
		Decryption key = new Decryption();
		int primeMultiple = key.getPrimeMultiple();
		int relativePrime = key.getRelativePrime();

		char[] message = Encryption.getMessage();

		encodedMessage = Encryption.encodeMessage(message, primeMultiple, relativePrime);
		System.out.println("\n");
		key.writeDecryptionKeyToFile();
		Encryption.writeEncryptedMessageToFile(encodedMessage);
		System.out.println("Your encrypted message is this: ");
		for (int i = 0; i < encodedMessage.size(); i++) {
			System.out.print(" " + encodedMessage.get(i));
		}
	}

}
