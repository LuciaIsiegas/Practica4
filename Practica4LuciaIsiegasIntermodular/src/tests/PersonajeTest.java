package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clases.Enemigo;
import clases.Guerrero;

public class PersonajeTest {

	static Guerrero guerrero;
	static Guerrero guerrero2;
	static Enemigo enemigo;
	static Enemigo enemigo2;
	
	@BeforeEach
	void crearPersonaje() {
		guerrero = new Guerrero("Pepe", 100);
		guerrero.setVida(50);
		guerrero.setAtaque(10);
		guerrero.setDefensa(5);
		
		guerrero2 = new Guerrero("Marta", 100);
		guerrero2.setVida(0);
		
		enemigo = new Enemigo();
		enemigo.setVida(50);
		enemigo.setAtaque(3);
		enemigo.setDefensa(5);
		
		enemigo2 = new Enemigo();
		enemigo2.setVida(50);
		enemigo2.setAtaque(7);
		enemigo2.setDefensa(11);
	}
	
	@Test
	void atacarTest() {
		guerrero.atacar(enemigo);
		assertEquals(45, enemigo.getVida());
		
		guerrero.atacar(enemigo2);
		assertEquals(50, enemigo2.getVida());
	}
	
	@Test
	void resetearTest() {
		guerrero.resetear();
		assertEquals(100, guerrero.getVida());
	}
	
	@Test
	void muertoTest() {
		assertFalse(guerrero.muerto());
		assertTrue(guerrero2.muerto());
	}
}
