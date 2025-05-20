package clases;

import interfaces.Jugable;

public class Mago extends Personaje implements Jugable {
	private static final int FUERZA_MAGIA = 20;
	private static final int FUERZA_SIN_MAGIA = 5;
	private static final int DEFENSA = 5;
	private static final int MAGIA = 10;

	private int magia;

	public Mago(String nombre, int vida) {
		super(nombre, vida, FUERZA_MAGIA, DEFENSA);
		this.magia = MAGIA;
	}

	@Override
	public void atacar(Personaje otro) {
		int damage = 0;
		if (magia > 0) {
			damage = FUERZA_MAGIA - otro.getDefensa() < 0 ? 0 : (FUERZA_MAGIA - otro.getDefensa());
			magia--;
		} else {
			damage = FUERZA_SIN_MAGIA - otro.getDefensa() < 0 ? 0 : (FUERZA_SIN_MAGIA - otro.getDefensa());
		}
		otro.setVida(otro.getVida() - damage);
	}

	@Override
	public void curar() {
		if (magia > 0) {
			this.setVida(FUERZA_MAGIA <= this.getVidaInicial() ? FUERZA_MAGIA : this.getVidaInicial());
			magia--;
		}
	}

	@Override
	public void resetear() {
		super.resetear();
		magia = MAGIA;
	}

	@Override
	public String toString() {
		return super.toString() + "; Magia: " + magia;
	}
}
