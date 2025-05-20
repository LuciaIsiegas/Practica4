package clases;

import interfaces.Jugable;

public class Guerrero extends Personaje implements Jugable{
	private static final int FUERZA = 15;
	private static final int DEFENSA = 10;
	private static final int POCIONES = 2;
	
	private int pociones = POCIONES;
	
	
	public Guerrero(String nombre, int vida) {
		super(nombre, vida, FUERZA, DEFENSA);
	}
	
	@Override
	public void curar() {
		if (pociones > 0) {
			this.setVida(this.getVidaInicial());
			pociones--;
		}
	}
	
	@Override
	public void resetear() {
		super.resetear();
		pociones = POCIONES;
	}
	
	@Override
	public String toString() {
		return super.toString() + "; Pociones: " + pociones;
	}

}
