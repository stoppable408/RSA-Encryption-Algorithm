# RSA-Encryption-Algorithm

Project "RSA Encryption Algorithm" contains the code for a program that encodes a message using the RSA algorithm, and writes both the encrypted code and the decryption keys to separate files for the ability to decode the message later.

The program starts by generating two random prime numbers 'p' and 'q'. the next step is to multiply 'p' and 'q' to produce 'N'. his number is a part of the public key that is known to everyone it then finds an totient that is coprime to the value of (p - 1)(q - 1). after the totient is produced, the program produces 'e' which is coprime to the totient. 'd' is then produced, which is the modular multiplicative inverse of (e mod "totient"). 

The program then takes the message, and changes each character into it's numeric equivalent according to the ASCII table. It then encodes each number with the formula (c(message) = message^e mod N).

The message that's stored in the file is c(message). the decoding keys 'd' and 'N' are stored in a separate file.

When you open the file, you will be asked to either encode a message or decode a message. If you choose to decode a message, you will have to provide the keys, and the encrypted message.


For more information about RSA Encryption go here: 
https://en.wikipedia.org/wiki/RSA_(cryptosystem)
