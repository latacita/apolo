package es.unican.moses.apolo.logic;

import java.io.Serializable;

public class PaqueteDatos implements Serializable{
	
	/** AUTOGENERADO */
	private static final long serialVersionUID = 1L;
	private boolean mesa;
	private boolean estanteria;
	private boolean album;
	
	public PaqueteDatos(){
		mesa = false;
		estanteria = false;
		album = false;
	}

	public boolean isMesa() {
		return mesa;
	}
	public void setMesa(boolean mesa) {
		this.mesa = mesa;
	}
	public boolean isEstanteria() {
		return estanteria;
	}
	public void setEstanteria(boolean estanteria) {
		this.estanteria = estanteria;
	}
	public boolean isAlbum() {
		return album;
	}
	public void setAlbum(boolean album) {
		this.album = album;
	}
}
