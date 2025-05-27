package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import programa.Utilidades;

public class UtilidadesTest {
	
	@Test
	public void validSiNoTest() {
		String input = "s";
		boolean inputValido = Utilidades.validSiNo(input);
		assertTrue(inputValido);
		
		input = "Si";
		inputValido = Utilidades.validSiNo(input);
		assertTrue(inputValido);
		
		input = "Ni";
		inputValido = Utilidades.validSiNo(input);
		assertTrue(inputValido);
		
		input = "n";
		inputValido = Utilidades.validSiNo(input);
		assertTrue(inputValido);
		
		input = "Tal vez";
		inputValido = Utilidades.validSiNo(input);
		assertFalse(inputValido);
	}
	
	@Test
	public void isIntTest() {
		String input = "Si";
		boolean inputValido = Utilidades.isInt(input);
		assertFalse(inputValido);
		
		input = "2";
		inputValido = Utilidades.isInt(input);
		assertTrue(inputValido);
	}
	
	@Test
	public void elegirEntreTest() {
		int min = 1;
		int max = 5;
		
		Scanner sc = new Scanner("3");
		int input = 3;
		boolean inputValido = input >= min && input <= max;
		assertTrue(inputValido);
		assertEquals(input, Utilidades.elegirEntre("Valido", min, max, sc));
		
	}

}
