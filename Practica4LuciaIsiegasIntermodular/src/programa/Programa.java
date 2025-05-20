package programa;

import java.util.Scanner;
import clases.Juego;

public class Programa {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Juego juego = new Juego();

		juego.jugar(sc);

		sc.close();

	}

}
