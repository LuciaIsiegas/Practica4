package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clases.Enemigo;
import clases.Guerrero;
import clases.Juego;
import clases.Mago;
import clases.Personaje;
import interfaces.Jugable;

public class JuegoTest {

	static Juego juego;
	static Enemigo enemigo;
	static Enemigo enemigo2;
	static Personaje jugador;
//	static String[] nombreEnemigos = {"El Decapitador", "Furia Centinela", "Dama Tóxica"};
	static ArrayList<Enemigo> enemigos;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private File archivoPuntuacion;
	private static final String JUGADOR_TEST = "jugador test";
	
	@BeforeEach
	void crearJuego() {
		juego = new Juego();
		enemigo = new Enemigo();
		enemigo2 = new Enemigo();
		enemigos = new ArrayList<Enemigo>();
//		Juego.setNombreEnemigos(nombreEnemigos);
		juego.setEnemigos(enemigos);
		enemigos.add(enemigo);
		enemigos.add(enemigo2);
		
		 System.setOut(new PrintStream(outContent));
	     archivoPuntuacion = new File("mejorPuntuacion.txt");
		
	}
	
	@AfterEach
	void eleminar() throws IOException {
//		new File("mejorPuntuacion.txt").delete();
        new File("partidaGuardada.txt").delete();
        
        System.setOut(originalOut);
        if (archivoPuntuacion.exists()) {
            archivoPuntuacion.delete();
        }
	}
	
	@Test
	void nombreAleatorioTest() {
		String nombre = Juego.nombreAleatorio();
		assertNotNull(nombre);
		assertTrue(nombre.length() > 0);
//		for (int i = 0; i < 10; i++) {
//			String nombreGenerado = Juego.nombreAleatorio();
//			assertTrue(contieneNombre(nombreEnemigos, nombreGenerado));
//		}
	}
	
//	private boolean contieneNombre(String[] nombreEnemigos, String nombreGenerado) {
//		for (String nombre : nombreEnemigos) {
//			if (nombre.equals(nombreGenerado)) {
//				return true;
//			}
//		}
//		return false;
//	}
	
	@Test
	void iniciarJuegoTest() {
		String input = "3\njugador test\n2\n";
        Scanner sc = new Scanner(input);

        juego.iniciarJuego(sc);

        assertNotNull(juego.getJugador());
        assertEquals("jugador test", juego.getJugador().getNombre());
        assertTrue(juego.getJugador() instanceof Guerrero);
        assertEquals(3, juego.getnRondas());
        assertEquals(0, juego.getRonda());
        assertEquals(3, juego.getEnemigos().size());
	}
	
	@Test
	void iniciarPartidaGuardadaTest() {
		juego.setnRondas(5);
		juego.setRonda(2);
		juego.nuevoGuerrero(JUGADOR_TEST);
		
		juego.guardarPartida();
		juego.cargarPartida();
		
		String input = "s";
		Scanner sc = new Scanner(input);
		
		juego.iniciarJuego(sc);
		
		assertEquals(5, juego.getnRondas());
        assertEquals(2, juego.getRonda());
        assertEquals("jugador test", juego.getJugador().getNombre());
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
		juego.nuevoGuerrero(JUGADOR_TEST);
		jugador = juego.getJugador();
		assertTrue(jugador instanceof Guerrero);
		assertEquals("jugador test", jugador.getNombre());
		assertTrue(jugador.getVida() >= 100 && jugador.getVida() <= 200);
	}
	
	@Test
	void nuevoMagoTest() {
		juego.nuevoMago(JUGADOR_TEST);
		jugador = juego.getJugador();
		assertTrue(jugador instanceof Mago);
		assertEquals("jugador test", jugador.getNombre());
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
		juego.elegirClase(JUGADOR_TEST, opcionMago);
		assertTrue(juego.getJugador() instanceof Mago);
		
		Scanner opcionGuerrero = new Scanner("2");
		juego.elegirClase(JUGADOR_TEST, opcionGuerrero);
		assertTrue(juego.getJugador() instanceof Guerrero);
	}
	
	@Test
	void realizarAtaqueTest() {
		juego.nuevoGuerrero(JUGADOR_TEST);
		juego.getJugador().setAtaque(100);
		
		enemigo.setVida(100);
		enemigo.setDefensa(5);
		
		juego.realizarAtaque();
		
		assertEquals(5, enemigo.getVida());
	}
	
	@Test
	void realizarCuraTest() {
		juego.nuevoMago(JUGADOR_TEST);
		Mago magoCreado = (Mago) juego.getJugador();
		magoCreado.setVidaInicial(100);
		magoCreado.setMagia(5);
		
		juego.realizarCura();
		
		assertTrue(magoCreado instanceof Jugable);
		assertEquals(20, magoCreado.getVida());
		assertEquals(4, magoCreado.getMagia());
		
		jugador = new Enemigo();
		assertFalse(jugador instanceof Jugable);
	}
	
	@Test
	void guardarYCargarPartidaTest() {
		juego.setnRondas(5);
		juego.setRonda(2);
		juego.nuevoGuerrero(JUGADOR_TEST);
		
		juego.guardarPartida();
		
		Juego juegoCargado = new Juego();
		assertTrue(juegoCargado.cargarPartida());
		assertEquals(5, juegoCargado.getnRondas());
		assertEquals(2, juegoCargado.getRonda());
		assertEquals("jugador test", juegoCargado.getJugador().getNombre());
		assertEquals(2, juegoCargado.getEnemigos().size());
	}
	
	@Test
	void borrarPartidaGuardadTest() {
		juego.setnRondas(1);
		juego.guardarPartida();
		
		juego.borrarPartidaGuardada();
		
		Juego juegoCargado = new Juego();
		assertFalse(juegoCargado.cargarPartida());
	}
	
	@Test
	void enemigoAtacaTest() {
		juego.nuevoMago(JUGADOR_TEST);
		Mago magoCreado = (Mago) juego.getJugador();
		magoCreado.setVida(100);
		magoCreado.setDefensa(5);
		
		enemigo.setAtaque(10);
		
		juego.enemigoAtaca();
		
		assertEquals(95, magoCreado.getVida());
	}
	
//	@Test
//	void jugarTest() {
//		String input = "1\nTest\n2\n1\nn\n";
//	    Scanner sc = new Scanner(input);
//		
//		enemigo.setVida(100);
//		enemigo.setDefensa(5);
//		enemigo.setAtaque(10);
//		
//		juego.jugar(sc);
//		juego.getJugador().setAtaque(100);
//		juego.getJugador().setDefensa(5);
//		
//		assertEquals(5, enemigo.getVida());
//	}
	
	@Test
    void escribirMejorPuntuacionTest() throws IOException {
        juego.setRonda(3);
        juego.nuevoGuerrero(JUGADOR_TEST);
        
        juego.escribirMejorPuntuacion();
        
        File archivo = new File("mejorPuntuacion.txt");
        assertTrue(archivo.exists());
        
        BufferedReader leerArchivo = new BufferedReader(new FileReader(archivo));
        String linea = leerArchivo.readLine();
        assertTrue(linea.contains(JUGADOR_TEST));
        assertTrue(linea.contains("3"));
        leerArchivo.close();
    }
	
	@Test
	void mostrarMejorPuntuacionArchivoNoExisteTest() {
        if (archivoPuntuacion.exists()) {
            archivoPuntuacion.delete();
        }
        
        juego.mostrarMejorPuntuacion();
        
        assertTrue(true);
    }

	@Test
    void mostrarMejorPuntuacionArchivoVacioTest() throws IOException {
        archivoPuntuacion.createNewFile();
        
        juego.mostrarMejorPuntuacion();
        
        assertTrue(true);
    }

    @Test
    void mostrarMejorPuntuacionConDatosTest() throws IOException {
        try (PrintWriter escribirArchivo = new PrintWriter(archivoPuntuacion)) {
            escribirArchivo.println("Nombre: Jugador1, rondas superadas: 5");
        }
        
        juego.mostrarMejorPuntuacion();
        
        String salida = outContent.toString();
        assertTrue(salida.contains("Record actual"));
        assertTrue(salida.contains("Jugador1"));
        assertTrue(salida.contains("5"));
    }

    @Test
    void mostrarMejorPuntuacionFormatoIncorrectoTest() throws IOException {
        try (PrintWriter escribirArchivo = new PrintWriter(archivoPuntuacion)) {
            escribirArchivo.println("Este no es el formato esperado");
        }
        
        juego.mostrarMejorPuntuacion();
        
        assertTrue(true);
    }

    @Test
    void mostrarMejorPuntuacionMultiplesLineasTest() throws IOException {
        try (PrintWriter escribirArchivo = new PrintWriter(archivoPuntuacion)) {
            escribirArchivo.println("Nombre: Jugador1, rondas superadas: 5");
            escribirArchivo.println("Nombre: Jugador2, rondas superadas: 10");
        }
        
        juego.mostrarMejorPuntuacion();
        
        String salida = outContent.toString();
        assertTrue(salida.contains("Jugador1") || salida.contains("Jugador2"));
    }
	
}
