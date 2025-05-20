package clases;

import programa.Utilidades;

public class Enemigo extends Personaje{

	public Enemigo() {
		super();
	}
	
	public void iniciarEnemigo(String nombre) {		
		this.setNombre(nombre);
		this.setVidaInicial(Utilidades.numAleatorio(20, 100));
		this.setVida(getVidaInicial());
		this.setAtaque(Utilidades.numAleatorio(2, 10));
		this.setDefensa(Utilidades.numAleatorio(1, 3));
	}

	
	

}
