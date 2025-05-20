package clases;

import java.util.ArrayList;
import java.util.Scanner;
// QUERÍA IMPORTAR LOS METODOS DE ESTA MANERA PARA NO PONER Metodos. CONSTANTEMENTE
// PERO NO ESTABA SEGURA DE CÓMO ERA MÁS CORRECTO ASI QUE LO HE DEJADO COMO LO HE HECHO HASTA AHORA
// import static programa.Metodos.*;
import programa.Utilidades;


public class Juego {
	private static String[] nombreEnemigos = 
		{"El Decapitador", "Furia Centinela", "Dama Tóxica", "El General de Fuego Negro", "Furiosa", "Rata Callejera"};
	private ArrayList<Enemigo> enemigos;
	private Personaje jugador;
	private int nRondas;
	private int ronda;
	
	private static final String BARRAS = "*****************************************************************";
	private static final String N_RONDAS = "Bienvenido al juego:\n"
			+ "¿Cuántas rondas quieres jugar? ";
	private static final String MOSTRAR_RONDA = "Ronda: %d/%d \nEstás luchando contra: %s \nEres: %s \n";
	private static final String PIDE_NOMBRE = "Introduce tu nombre: ";
	private static final String ELIGE_CLASE = "Elige tu clase:\n"
			+ "1. Mago\n"
			+ "2. Guerrero\n"
			+ "Elige (1, 2): ";
	private static final String ELIGE_ACCION = "Acciones:\n"
			+ "1. Atacar\n"
			+ "2. Curar\n"
			+ "Elige: ";
	private static final String ATACA = " ataca a ";
	private static final String CURA = " se cura";
	private static final String ENEMIGO_VENCIDO = "Enemigo vencido!!";
	private static final String PERDIDO = "Has perdido";
	private static final String GANADO = "Has ganado";
	private static final String VOLVER_A_JUGAR = "¿Volver a jugar? ";
	private static final String FIN_JUEGO= "Fin del juego";
	
	
	public Juego() {
		enemigos = new ArrayList<Enemigo>();
	}
	
	public static String nombreAleatorio() {
		return nombreEnemigos[Utilidades.numAleatorio(0, (nombreEnemigos.length - 1))];
	}
	
	public void iniciarJuego(Scanner sc) {
		System.out.println(BARRAS);
		ronda = 0;
		// MI CODIGO
		enemigos.clear();
		nRondas = Utilidades.getInt(N_RONDAS, sc);
		String nombre = Utilidades.getString(PIDE_NOMBRE, sc);
		elegirClase(nombre, sc);
		//
		for (int i = 0; i < nRondas; i++) {
			Enemigo enemigo = new Enemigo();
			enemigo.iniciarEnemigo(nombreAleatorio());
			enemigos.add(enemigo);
		}
		
	}
	
	public Enemigo getSiguiente() {
		return enemigos.get(0);
	}
	
	public boolean terminarRonda() {
		Personaje enemigo = getSiguiente();
		if (enemigo.muerto()) {
			enemigos.remove(0);
			ronda++;
		}
		return enemigo.muerto();
	}
	
	public void nuevoGuerrero(String nombre) {
		jugador = new Guerrero(nombre, Utilidades.numAleatorio(100, 200));
	}
	
	public void nuevoMago(String nombre) {
		jugador = new Mago(nombre, Utilidades.numAleatorio(50, 150));
	}
	
	public boolean esFinalJuego() {
		return enemigos.isEmpty();
	}
	
	// MI CODIGO
	public void elegirClase(String nombre, Scanner sc) {
		int opcion = Utilidades.elegirEntre(ELIGE_CLASE, 1, 2, sc);
		switch(opcion) {
		case 1:
			nuevoMago(nombre);
			break;
		case 2:
			nuevoGuerrero(nombre);
		}
	}
	
	public void mostrarRonda() {
		System.out.printf(MOSTRAR_RONDA, (getRonda() + 1), getnRondas(), getSiguiente(), getJugador());
	}
	
	public void realizarAtaque() {
		getJugador().atacar(getSiguiente());
		System.out.println(getJugador().getNombre() + ATACA + getSiguiente().getNombre());
	}
	
	public void realizarCura() {
		getJugador().curar();
		System.out.println(getJugador().getNombre() + CURA);
	}
	
	public void actuar(Scanner sc) {
		int accion = Utilidades.elegirEntre(ELIGE_ACCION, 1, 2, sc);
		switch(accion) {
		case 1:
			realizarAtaque();
			break;
		case 2:
			realizarCura();
		}
	}
	
	public void enemigoAtaca() {
		getSiguiente().atacar(getJugador());
		System.out.println(getSiguiente().getNombre() + ATACA + getJugador().getNombre());
	}
	
	public void ejecutarRonda(Scanner sc) {
		mostrarRonda();
		actuar(sc);
		enemigoAtaca();
		if (terminarRonda()) {
			System.out.println(ENEMIGO_VENCIDO);
		}
	}
	
	public void jugarPartida(Scanner sc) {
		iniciarJuego(sc);
		do {
			ejecutarRonda(sc);
		} while (!esFinalJuego() && !jugador.muerto());
		System.out.println(jugador.muerto() ? PERDIDO : GANADO);
	}
	
	public void jugar(Scanner sc) {
		char seguir = 0;
		do {
			jugarPartida(sc);
			seguir = Utilidades.getCharSiNo(VOLVER_A_JUGAR, sc);
			jugador.resetear();
		} while (seguir == 's' || seguir == 'S');
		System.out.println(FIN_JUEGO);
	}
	
	
	public Personaje getJugador() {
		return jugador;
	}
	public void setJugador(Personaje jugador) {
		this.jugador = jugador;
	}
	public int getnRondas() {
		return nRondas;
	}
	public void setnRondas(int nRondas) {
		this.nRondas = nRondas;
	}
	public int getRonda() {
		return ronda;
	}
	public void setRonda(int ronda) {
		this.ronda = ronda;
	}
	
	
	

}
