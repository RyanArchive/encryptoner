// Encryption and decryption of texts

import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Encryptoner {
	public static void main(String[] args) {
		Menu menu = new Menu();
		Scanner sc = new Scanner(System.in);
		String[] choices = {"Encrypt File", "Read File", "Exit"}; 
		
		menu.setHeader("Encryptoner\n-----------");
		menu.setMode('A');
		menu.setChoices(choices);
		menu.setPrompt("Choose:");

		while(true) {
			System.out.println();
			menu.display();
			char toDo = sc.nextLine().charAt(0);

			switch(toDo) {
				case 'A':
				case 'a':
					Encrypt.encrypt();
					break;
				case 'B':
				case 'b':
					Reader.read();
					break;
				case 'C':
				case 'c':
					System.exit(0);
			}
		}
	}
}

// Encrypting the text writen from a text file
class Encrypt {
	public static void encrypt() {
		Scanner sc = new Scanner(System.in);
		String[] words = new String[1];

		System.out.print("\nEnter file name: ");
		String file1 = sc.nextLine();
		file1 += ".txt";

		String file2 = "secret_" + file1;

		try {
			words = ReadWriteText.read(file1);

			for (int x = 0; x < words.length; x++) {
				String container = "";

				for (int y = 0; y < words[x].length(); y++)
					container += (char)(words[x].charAt(y) + 4);

				words[x] = container;
			}

			ReadWriteText.write(file2, words, false);
			System.out.println("The " + file1 + " has now encrypted\nPlease open " + file2);
		} catch(FileNotFoundException e) {
			System.out.println("ERROR");
		} catch(IOException e) {
			System.out.println("ERROR");
		}
	}
}

// Decrypting the encrypted text and displaying it
// If you try to decrypt the original text, it will turn to encrypted text (characters decreased by 4)
class Reader {
	public static void read() {
		Scanner sc = new Scanner(System.in);
		String[] words = new String[1];

		System.out.print("\nEnter file name: ");
		String file = sc.nextLine();
		file += ".txt";

		System.out.println("Opening " + file);

		try {
			words = ReadWriteText.read(file);
		} catch (FileNotFoundException e) {								// File should exist in the same folder of the program
			System.out.println("ERROR");
		}

		System.out.println("---------------------------");

		for (int x = 0; x < words.length; x++) {
			for (int y = 0; y < words[x].length(); y++)
				System.out.print((char)(words[x].charAt(y) - 4));

			System.out.println();
		}

		System.out.println("---------------------------");
	}
}

// Used for a uniform structure of displaying texts
class Menu {
	private String header;
	private char mode;
	private String[] choices;
	private String prompt;

	public void setHeader(String h) {
		header = h;
	}

	public void setMode(char m) {
		mode = m;
	}

	public void setChoices(String[] c) {
		choices = new String[c.length];
		
		for (int x = 0; x < choices.length; x++) {
			choices[x] = c[x];
		} 
	}

	public void setPrompt(String p) {
		prompt = p;
	}

	public Menu() {}
	
	public Menu(String[] c) {
		setHeader("");
		setChoices(c);
		setPrompt("");
	}
	
	public void display() {
		int modeNum = Character.getNumericValue(mode);
		char modeLet = mode;
		String modeS = "";

		System.out.println(header);

		for (int x = 1; x <= choices.length; x++) {
			switch (mode) {
				case '1':
					modeS = Integer.toString(modeNum);
					modeNum++;
					break;
				case 'a':
				case 'A':
					modeS = Character.toString(modeLet);
					modeLet++;
					break;
			}

			System.out.println(modeS + ". " + choices[x - 1]);
		}

		System.out.print("\n" + prompt + " ");
	}
}