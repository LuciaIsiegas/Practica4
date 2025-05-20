package programa;

import java.util.Scanner;

public class Utilidades {

	public static final String INCORRECTO = "Selección no valida";

	public static int numAleatorio(int inicial, int limite) {
		return (int) (Math.random() * (limite - inicial + 1) + inicial);
	}

	// VALIDO PARA CUALQUIER INPUT
	public static String getString(String msg, Scanner sc) {
		String input = "";
		do {
			System.out.print(msg);
			input = sc.nextLine();
		} while (input.isEmpty());
		return input;
	}

	public static char getCharSiNo(String msg, Scanner sc) {
		String aux = "";
		do {
			aux = getString(msg, sc);
		} while (!validSiNo(aux));
		return aux.charAt(0);
	}

	// VALIDO PARA CUALQUIER INT
	public static int getInt(String msg, Scanner sc) {
		String aux = "";
		do {
			aux = getString(msg, sc);
		} while (!isInt(aux));
		return Integer.parseInt(aux);
	}

	public static boolean isInt(String aux) {
		try {
			Integer.parseInt(aux);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public static boolean validSiNo(String input) {
		return (input.charAt(0) == 's' || input.charAt(0) == 'S' || input.charAt(0) == 'n' || input.charAt(0) == 'N');
	}

	// SIRVE PARA ESCOGER UNA ACCION O PERSONAJE
	public static int elegirEntre(String msg, int min, int max, Scanner sc) {
		int accion = 0;
		do {
			accion = getInt(msg, sc);
			if (accion > max || accion < min) {
				System.out.println(INCORRECTO);
			}
		} while (accion > max || accion < min);
		return accion;
	}

}
