package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clases.Enemigo;
import clases.Guerrero;
import clases.Juego;
import clases.Mago;
import clases.Personaje;

public class JuegoTest {

	static Juego juego;
	static Enemigo enemigo;
	static Enemigo enemigo2;
	static Personaje jugador;
	static String[] nombreEnemigos = {"El Decapitador", "Furia Centinela", "Dama Tóxica"};
	static ArrayList<Enemigo> enemigos;
	
	@BeforeEach
	void crearJuego() {
		juego = new Juego();
		enemigo = new Enemigo();
		enemigo2 = new Enemigo();
		enemigos = new ArrayList<Enemigo>();
		Juego.setNombreEnemigos(nombreEnemigos);
		juego.setEnemigos(enemigos);
		enemigos.add(enemigo);
		enemigos.add(enemigo2);
		
	}
	
	@Test
	void nombreAleatorioTest() {
		for (int i = 0; i < 10; i++) {
			String nombreGenerado = Juego.nombreAleatorio();
			assertTrue(contieneNombre(nombreEnemigos, nombreGenerado));
		}
	}
	
	private boolean contieneNombre(String[] nombreEnemigos, String nombreGenerado) {
		for (String nombre : nombreEnemigos) {
			if (nombre.equals(nombreGenerado)) {
				return true;
			}
		}
		return false;
	}
	
	@Test
	void getSiguienteTest() {
		assertEquals(enemigo, juego.getSiguiente());
	}
	
	@Test
	void terminarRondaTest() {
		juego.setRonda(1);
		enemigo.setVida(0);
		assertTrue(juego.terminarRonda());
		assertEquals(2, juego.getRonda());
		
		enemigo2.setVida(3);
		assertFalse(juego.terminarRonda());
	}
	
	@Test
	void nuevoGuerreroTest() {
		juego.nuevoGuerrero("Juan");
		jugador = juego.getJugador();
		assertTrue(jugador instanceof Guerrero);
		assertEquals("Juan", jugador.getNombre());
		assertTrue(jugador.getVida() >= 100 && jugador.getVida() <= 200);
	}
	
	@Test
	void nuevoMagoTest() {
		juego.nuevoMago("Pepe");
		jugador = juego.getJugador();
		assertTrue(jugador instanceof Mago);
		assertEquals("Pepe", jugador.getNombre());
		assertTrue(jugador.getVida() >= 50 && jugador.getVida() <= 150);
	}
	
	@Test
	void esFinalJuegoTest() {
		assertFalse(juego.esFinalJuego());
		
		enemigos.clear();
		assertTrue(juego.esFinalJuego());
	}
	
	@Test
	void elegirClaseTest() {
		Scanner opcionMago = new Scanner("1");
		juego.elegirClase("Pepe", opcionMago);
		assertTrue(juego.getJugador() instanceof Mago);
		
		Scanner opcionGuerrero = new Scanner("2");
		juego.elegirClase("Pepe", opcionGuerrero);
		assertTrue(juego.getJugador() instanceof Guerrero);
	}
	
	@Test
	void realizarAtaqueTest() {
		juego.nuevoGuerrero("Pepe");
		jugador.setAtaque(100);
		enemigo.setVida(100);
		enemigo.setDefensa(5);
		juego.realizarAtaque();
		assertEquals(5, enemigo.getVida());
	}
	
}
