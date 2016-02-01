package com.gamsion.chris.utilities.passwordGen.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Generator {
	public static final Random r = new Random();

	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yy.dd.MM_HH.mm.ss");
		String filename = format.format(new Date()) + ".txt";
		File save = new File(new File(".").getAbsolutePath()
				+ "/PasswordGenerator");
		System.out.println(save);
		save.mkdir();
		save = new File(new File(".").getAbsolutePath() + "/PasswordGenerator/"
				+ filename);
		StringBuilder builder = new StringBuilder();
		try {

			save.createNewFile();
			System.out
					.println("Please enter how many passwords you want generated.");
			Scanner in = new Scanner(System.in);
			String input1 = in.nextLine();
			System.out
					.println("Please enter the number of uppercases you want.");
			String input2 = in.nextLine();
			System.out
					.println("Please enter the number of lowercases you want.");
			String input3 = in.nextLine();
			System.out.println("Please enter the number of numbers you wants.");
			String input4 = in.nextLine();
			try {
				int numberofpasswords = Integer.parseInt(input1.trim());
				int uppercases = Integer.parseInt(input2.trim());
				int lowercases = Integer.parseInt(input3.trim());
				int numbers = Integer.parseInt(input4.trim());
				PasswordRequirements requirements = new PasswordRequirements(
						uppercases, lowercases, numbers);
				for (int i = 0; i < numberofpasswords; i++) {
					String generated = generate(requirements)
							+ System.lineSeparator();
					builder.append(generated);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			FileWriter fw = new FileWriter(save);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(builder.toString());
			bw.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String generate(PasswordRequirements requires) {
		int length = requires.getCapitalRequire() + requires.getLowerRequire()
				+ requires.getNumberRequire();
		char[] characters = new char[length];
		List<String> capitals = generateList(CharType.CAPITAL,
				requires.getCapitalRequire());
		List<String> lowercases = generateList(CharType.LOWERCASE,
				requires.getLowerRequire());
		List<String> numbers = generateList(CharType.NUMBER,
				requires.getNumberRequire());
		Iterator<String> capitalsit = capitals.iterator();
		Iterator<String> lowercasesit = lowercases.iterator();
		Iterator<String> numbersit = numbers.iterator();
		for (int i = 0; i < characters.length; i++) {

			if (capitalsit.hasNext() || lowercasesit.hasNext()
					|| numbersit.hasNext()) {
				switch (r.nextInt(3)) {
				case 0:
					if (numbersit.hasNext()) {
						characters[i] = numbersit.next().charAt(0);
					} else if (capitalsit.hasNext()) {
						characters[i] = capitalsit.next().charAt(0);
					} else if (lowercasesit.hasNext()) {
						characters[i] = lowercasesit.next().charAt(0);
					} else {
						System.out.println("All out of characters!");
					}
					break;
				case 1:
					if (capitalsit.hasNext()) {
						characters[i] = capitalsit.next().charAt(0);
					} else if (lowercasesit.hasNext()) {
						characters[i] = lowercasesit.next().charAt(0);
					} else if (numbersit.hasNext()) {
						characters[i] = numbersit.next().charAt(0);
					} else {
						System.out.println("All out of characters!");
					}
					break;
				case 2:
					if (lowercasesit.hasNext()) {
						characters[i] = lowercasesit.next().charAt(0);
					} else if (numbersit.hasNext()) {
						characters[i] = numbersit.next().charAt(0);
					} else if (capitalsit.hasNext()) {
						characters[i] = capitalsit.next().charAt(0);
					} else {
						System.out.println("All out of characters!");
					}
					break;
				}
			} else {
				break;
			}

		}

		return String.valueOf(characters);
	}

	public static List<String> generateList(CharType type, int number) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < number; i++) {
			list.add(String.valueOf(getChar(type)));
		}

		return list;
	}

	public static boolean checkIfNumber(char chara) {
		if (chara > 47 && chara < 58)
			return true;
		return false;
	}

	public static boolean checkIfCapital(char chara) {
		if (chara > 64 && chara < 91)
			return true;
		return false;
	}

	public static boolean checkIfLower(char chara) {
		if (chara > 96 && chara < 123)
			return true;
		return false;
	}

	public static char getNumber() {
		char chara = (char) (48 + r.nextInt(10));
		return chara;
	}

	public static char getLowercase() {
		char chara = (char) (97 + r.nextInt(26));
		return chara;
	}

	public static char getUppercase() {
		char chara = (char) (65 + r.nextInt(26));
		return chara;
	}

	public static CharType getType(char chara) {
		if (chara > 47 && chara < 58)
			return CharType.NUMBER;
		if (chara > 64 && chara < 91)
			return CharType.CAPITAL;
		if (chara > 96 && chara < 123)
			return CharType.LOWERCASE;
		return CharType.UNDEFINED;
	}

	public static char getChar(CharType type) {
		if (type == CharType.NUMBER) {
			return getNumber();
		} else if (type == CharType.CAPITAL) {
			return getUppercase();
		} else if (type == CharType.LOWERCASE) {
			return getLowercase();
		} else {
			switch (r.nextInt(3)) {
			case 0:
				return getUppercase();
			case 1:
				return getNumber();
			case 2:
				return getLowercase();
			}
		}
		return 'N';
	}
}