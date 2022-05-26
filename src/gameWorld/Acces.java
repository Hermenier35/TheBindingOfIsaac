package gameWorld;

import libraries.Vector2;

public class Acces {
	private Door porte;
	private Room piece;
	private Vector2 position;
	
	public Acces(Door porte, Room piece, Vector2 position) {
		this.porte = porte;
		this.piece = piece;
		this.position = position;
	}

	public Door getPorte() {
		return porte;
	}

	public void setPorte(Door porte) {
		this.porte = porte;
	}

	public Room getPiece() {
		return piece;
	}

	public void setPiece(Room piece) {
		this.piece = piece;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
}
