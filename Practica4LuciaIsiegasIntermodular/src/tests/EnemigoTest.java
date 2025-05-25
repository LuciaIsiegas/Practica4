package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clases.Enemigo;

public class EnemigoTest {

	static Enemigo enemigo = new Enemigo();
	@BeforeAll
	static void crearEnemigo() {
		enemigo.iniciarEnemigo("Pepe");
		enemigo.setVidaInicial(1);
		enemigo.setVida(1);
		enemigo.setAtaque(10);
		enemigo.setDefensa(3);
	}
	@Test
	void iniciarEnemigoTest() {
		crearEnemigo();
		assertEquals("Pepe", enemigo.getNombre());
		assertEquals(1, enemigo.getVidaInicial());
		assertEquals(1, enemigo.getVida());
		assertEquals(10, enemigo.getAtaque());
		assertEquals(3, enemigo.getDefensa());
	}
}
