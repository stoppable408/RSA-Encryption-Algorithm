import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class Decryption {

	private int firstPrime = generatePrimeNumber();
	private int secondPrime = generatePrimeNumber();
	private int primeMultiple = firstPrime * secondPrime;
	private int relativePrime = relativePrimeFactorize(firstPrime, secondPrime);
	private int encodingTotient = (firstPrime - 1) * (secondPrime - 1);
	private int decodingInteger = getDecodingInteger(encodingTotient, relativePrime);

	public void setPrimeMultiple(int primeMultiple) {
		this.primeMultiple = primeMultiple;
	}

	public void setDecodingInteger(int decodingInteger) {
		this.decodingInteger = decodingInteger;
	}

	public int getPrimeMultiple() {
		return primeMultiple;
	}

	public int getRelativePrime() {
		return relativePrime;
	}

	private static int generatePrimeNumber() {
		Calendar calendar = Calendar.getInstance();
		long seed = calendar.getTimeInMillis();
		Random random = new Random(seed);

		int numberToBeCheckedForPrimeness;
		while (true) {
			numberToBeCheckedForPrimeness = random.nextInt(200) + 10;
			if (isPrime(numberToBeCheckedForPrimeness)) {
				return numberToBeCheckedForPrimeness;
			}
		}
	}

	private static boolean isPrime(int number) {
		for (int i = 2; i < number; i++) {
			if (number % i == 0)
				return false;
		}
		return true;
	}

	private static int relativePrimeFactorize(int firstPrime, int secondPrime) {
		int limit = (firstPrime - 1) * (secondPrime - 1);
		boolean isRelativePrime;
		Calendar calendar = Calendar.getInstance();
		long seed = calendar.getTimeInMillis();
		Random random = new Random(seed);
		int relativePrime;
		ArrayList<Integer> primeFactorsOfLimit = new ArrayList<Integer>();
		for (int i = 2; i < limit; i++) {
			if (limit % i == 0) {
				if (isPrime(i)) {
					primeFactorsOfLimit.add(i);
				}
			}
		}
		do {
			isRelativePrime = true;
			relativePrime = (random.nextInt(limit)) + 10;
			for (int i = 2; i < relativePrime; i++) {
				if (relativePrime % i == 0) {
					if (isPrime(i)) {
						if (primeFactorsOfLimit.contains(i)) {
							isRelativePrime = false;
							break;
						}
					}
				}
			}
		} while (!isRelativePrime);
		return relativePrime;
	}

	static int getDecodingInteger(int encodingTotient, int relativePrime) {
		int integerForDecoding = 0;
		for (int i = 1; i < encodingTotient; i++) {
			if (((relativePrime * i) % encodingTotient) == 1) {
				integerForDecoding = i;
			}

		}
		return integerForDecoding;
	}

	public static char decodeCharacter(int decodingInteger, long message, int primeMultiple) {
		BigInteger bigIntegerOfPrimeMultiple = BigInteger.valueOf(primeMultiple);
		BigInteger bigIntegerOfMessage = BigInteger.valueOf(message);
		BigInteger bigIntegerOfCharacterToDecode;

		bigIntegerOfCharacterToDecode = bigIntegerOfMessage.pow(decodingInteger);
		bigIntegerOfCharacterToDecode = bigIntegerOfCharacterToDecode.mod(bigIntegerOfPrimeMultiple);
		int integerOfASCII = bigIntegerOfCharacterToDecode.intValue();
		char decodedCharacter = (char) integerOfASCII;
		return decodedCharacter;
	}

	public void decodeMessage(ArrayList<Long> encodedMessage) {
		System.out.println("\n\n");
		for (int i = 0; i < encodedMessage.size(); i++) {
			System.out.print(decodeCharacter(decodingInteger, encodedMessage.get(i), primeMultiple));
		}

	}

	public void writeDecryptionKeyToFile() {
		PrintWriter output = null;
		try {
			output = new PrintWriter("C:\\Users\\Solomon\\Documents\\GitHub\\Miscellaneous\\Directory\\Key.txt");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		output.println(decodingInteger);
		output.println(primeMultiple);
		output.println();
		output.close();

	}
}
