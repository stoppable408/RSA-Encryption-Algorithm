import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Encryption {

	public static ArrayList<Long> encodedMessage = new ArrayList<Long>();

	static char[] getMessage() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please type the message you wish to encrypt: ");
		String message = input.nextLine();
		char[] messageArray = message.toCharArray();
		input.close();
		return messageArray;
	}

	static ArrayList<Long> encodeMessage(char[] messageArray, int primeMultiple, int relativePrime) {
		ArrayList<Long> encodedMessage = new ArrayList<Long>();
		for (int i = 0; i < messageArray.length; i++) {
			encodedMessage.add(encodeCharacter(messageArray[i], primeMultiple, relativePrime));
		}
		return encodedMessage;

	}

	static long encodeCharacter(char letterToEncode, int primeMultiple, int relativePrime) {

		int letterInASCII = letterToEncode;
		BigInteger bigIntOfPrimeMultiple = BigInteger.valueOf(primeMultiple);
		BigInteger bigIntLetterInASCII = BigInteger.valueOf(letterInASCII);

		BigInteger encoding = bigIntLetterInASCII.pow(relativePrime);
		encoding = encoding.mod(bigIntOfPrimeMultiple);

		long encodedCharacter = encoding.longValue();
		return encodedCharacter;

	}

	public ArrayList<Long> getEncodedMessage() {
		return encodedMessage;
	}

	public static void writeEncryptedMessageToFile(ArrayList<Long> encodedMessage) {
		PrintWriter output = null;
		try {
			output = new PrintWriter(
					"C:\\Users\\Solomon\\Documents\\GitHub\\Miscellaneous\\Directory\\encryptedmessage.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < encodedMessage.size(); i++) {
			output.print(encodedMessage.get(i) + " ");
		}
		output.close();
	}

	public static ArrayList<Long> readEncodedMessageFromFile() {
		String linesOfEncodedText = null;
		String[] arrayOfIndividualCharacters = null;
		FileReader reader = null;
		BufferedReader bufferedreader = null;
		ArrayList<Long> arrayOfIndividualNumbers = new ArrayList<Long>();
		long temp = 0;

		try {
			reader = new FileReader(
					"C:\\Users\\Solomon\\Documents\\GitHub\\Miscellaneous\\Directory\\encryptedmessage.txt");
			bufferedreader = new BufferedReader(reader);
			System.out.println("\n");
			while ((linesOfEncodedText = bufferedreader.readLine()) != null) {
				arrayOfIndividualCharacters = linesOfEncodedText.split(" ");
			}
			for (int i = 0; i < arrayOfIndividualCharacters.length; i++) {
				if (!arrayOfIndividualCharacters[i].equalsIgnoreCase(""))
					temp = Long.valueOf(arrayOfIndividualCharacters[i]);
				arrayOfIndividualNumbers.add(temp);
			}

			bufferedreader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrayOfIndividualNumbers;
	}
}
