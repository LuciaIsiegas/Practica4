package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clases.Enemigo;
import clases.Mago;

public class MagoTest {

	private static final String NOMBRE_TEST = "Pepa";
	static Mago mago;
	static Mago mago2;
	static Mago mago3;
	static Enemigo enemigo;
	static Enemigo enemigo2;
	static Enemigo enemigo3;
	static Enemigo enemigo4;
	
	@BeforeEach
	void crearPersonaje() {
		mago = new Mago(NOMBRE_TEST, 100);
		mago.setVida(80);
		mago.setMagia(3);
		
		mago2 = new Mago(NOMBRE_TEST, 100);
		mago2.setVida(80);
		mago2.setMagia(0);
		
		mago3 = new Mago(NOMBRE_TEST, 15);
		mago3.setVida(10);
		mago3.setMagia(5);
		
		enemigo = new Enemigo();
		enemigo.setVida(50);
		enemigo.setDefensa(0);
		
		enemigo2 = new Enemigo();
		enemigo2.setVida(50);
		enemigo2.setDefensa(25);
		
		enemigo3 = new Enemigo();
		enemigo3.setVida(50);
		enemigo3.setDefensa(0);
		
		enemigo4 = new Enemigo();
		enemigo4.setVida(50);
		enemigo4.setDefensa(25);
	}
	
	@Test
	void atacarTest() {
		mago.atacar(enemigo);
		assertEquals(2, mago.getMagia());
		assertEquals(30, enemigo.getVida());
		
		mago.atacar(enemigo2);
		assertEquals(1, mago.getMagia());
		assertEquals(50, enemigo2.getVida());
		
		mago2.atacar(enemigo3);
		assertEquals(0, mago2.getMagia());
		assertEquals(45, enemigo3.getVida());
		
		mago2.atacar(enemigo4);
		assertEquals(0, mago2.getMagia());
		assertEquals(50, enemigo4.getVida());
	}
	
	@Test
	void curarTest() {
		mago.curar();
		assertEquals(20, mago.getVida());
		assertEquals(2, mago.getMagia());
		
		mago2.curar();
		assertEquals(80, mago2.getVida());
		assertEquals(0, mago2.getMagia());
		
		mago3.curar();
		assertEquals(15, mago3.getVida());
		assertEquals(4, mago3.getMagia());
	}
	
	@Test
	void resetearTest() {
		mago.resetear();
		assertEquals(100, mago.getVida());
		assertEquals(10, mago.getMagia());
	}
	
	@Test
	void toStringTest() {
		mago.setNombre(NOMBRE_TEST);
		mago.setVidaInicial(100);
		mago.setVida(70);
		mago.setMagia(8);
		
		assertEquals("Pepa => Vida: 70/100; Magia: 8", mago.toString());
	}
}
