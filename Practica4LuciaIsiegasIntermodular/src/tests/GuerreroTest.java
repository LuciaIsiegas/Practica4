package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clases.Guerrero;

public class GuerreroTest {
	
	private static final String NOMBRE_TEST = "Pepe";
	static Guerrero guerrero;
	static Guerrero guerrero2;
	
	@BeforeEach
	void crearGuerrero() {
		guerrero = new Guerrero(NOMBRE_TEST, 100);
		guerrero.setVida(80);
		guerrero.setPociones(1);
		guerrero2 = new Guerrero(NOMBRE_TEST, 100);
		guerrero2.setVida(80);
		guerrero2.setPociones(0);
	}
	
	@Test
	void curarTest() {
		guerrero.curar();
		assertEquals(100, guerrero.getVida());
		assertEquals(0, guerrero.getPociones());
		
		guerrero2.curar();
		assertEquals(80, guerrero2.getVida());
		assertEquals(0, guerrero2.getPociones());
	}
	
	@Test
	void resetearTest() {
		guerrero.resetear();
		assertEquals(100, guerrero.getVida());
		assertEquals(2, guerrero.getPociones());
	}
	
	@Test
	void toStringTest() {
		guerrero.setNombre(NOMBRE_TEST);
		guerrero.setVidaInicial(100);
		guerrero.setVida(80);
		guerrero.setPociones(2);
		
		assertEquals("Pepe => Vida: 80/100; Pociones: 2", guerrero.toString());
		
	}
}
