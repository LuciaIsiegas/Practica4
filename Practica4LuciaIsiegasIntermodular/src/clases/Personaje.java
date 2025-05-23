package clases;

import java.io.Serializable;

public abstract class Personaje implements Serializable {
	private static final long serialVersionUID = 1571423440978840022L;
	private int vida;
	private int vidaInicial;
	private int ataque;
	private int defensa;
	private String nombre;

	public Personaje() {}

	public Personaje(String nombre, int vida, int ataque, int defensa) {
		this.nombre = nombre;
		this.vida = vida;
		this.ataque = ataque;
		this.defensa = defensa;
		this.vidaInicial = vida;
	}

	public void atacar(Personaje otro) {
		otro.vida -= (ataque - otro.defensa) < 0 ? 0 : (ataque - otro.defensa);
	}

	public void resetear() {
		vida = vidaInicial;
	}

	public boolean muerto() {
		return vida <= 0;
	}

	@Override
	public String toString() {
		return nombre + " => Vida: " + vida + "/" + vidaInicial;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getVidaInicial() {
		return vidaInicial;
	}

	public void setVidaInicial(int vidaInicial) {
		this.vidaInicial = vidaInicial;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
