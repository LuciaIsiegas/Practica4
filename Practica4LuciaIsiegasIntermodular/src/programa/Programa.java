package programa;

import java.util.Scanner;
import clases.Juego;

public class Programa {

	private static final String VOLVER_A_JUGAR = "¿Volver a jugar? ";
	private static final String FIN_JUEGO = "Fin del juego";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Juego juego = new Juego();

		char seguir = 0;
		do {
			// INICIAMOS EL JUEGO RESETEANDO AL JUGADOR
			juego.iniciarJuego(sc);
			juego.jugarPartida(sc);
			seguir = Utilidades.getCharSiNo(VOLVER_A_JUGAR, sc);
			juego.getJugador().resetear();
		} while (seguir == 's' || seguir == 'S');

		System.out.println(FIN_JUEGO);

		sc.close();

	}

}
