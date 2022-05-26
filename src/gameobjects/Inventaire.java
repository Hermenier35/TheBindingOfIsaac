package gameobjects;

import java.util.LinkedList;
import java.util.List;

import gameWorld.Room;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Inventaire {
	private List<Objet> objets;
	private Vector2 size;
	private Vector2 positionDime;
	private Vector2 positionNickel;
	private Vector2 positionCoin;
	private Vector2 positionKey;
	private Vector2 positionBotm;
	private Vector2 positionBombe;
	private int argent;
	private int dime;
	private int nickel;
	private int coin;
	private int key;
	private int botm;
	private int bombe;
	
	
	
	
	public Inventaire() {
		this.objets = new LinkedList<Objet>();
		this.size = RoomInfos.TILE_SIZE.scalarMultiplication(0.3);
		this.positionDime = Room.positionFromTileIndex(0, 7);
		this.positionNickel = Room.positionFromTileIndex(0, 7);
		this.positionCoin = Room.positionFromTileIndex(0, 7);
		this.positionKey = Room.positionFromTileIndex(0, 7);
		this.positionBotm = Room.positionFromTileIndex(0, 7);
		this.positionBombe = Room.positionFromTileIndex(0, 7);
		this.positionDime.addX(-0.035);
		this.positionDime.addY(0.040);
		this.positionNickel.addX(-0.035);
		this.positionCoin.addX(-0.035);
		this.positionCoin.addY(-0.040);
		this.positionKey.addX(-0.035);
		this.positionKey.addY(-0.080);
		this.positionBotm.addX(-0.035);
		this.positionBotm.addY(-0.120);
		this.positionBombe.addX(-0.035);
		this.positionBombe.addY(-0.160);
		this.dime = 2;
		this.nickel = 3;
		this.coin = 4;
		setArgent();
		
	}
	

	
	public void drawGameObject()
	{	
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.picture(positionDime.getX(), positionDime.getY(), ImagePaths.DIME, size.getX(), size.getY(), 0);
		StdDraw.text(positionText(positionDime).getX(), positionText(positionDime).getY(), dime + "");
		StdDraw.picture(positionNickel.getX(), positionNickel.getY(), ImagePaths.NICKEL, size.getX(), size.getY(), 0);
		StdDraw.text(positionText(positionNickel).getX(), positionText(positionNickel).getY(), nickel + "");
		StdDraw.picture(positionCoin.getX(), positionCoin.getY(), ImagePaths.COIN, size.getX(), size.getY(), 0);
		StdDraw.text(positionText(positionCoin).getX(), positionText(positionCoin).getY(), coin + "");
		StdDraw.picture(positionKey.getX(), positionKey.getY(), ImagePaths.KEY, size.getX(), size.getY(), 0);
		StdDraw.text(positionText(positionKey).getX(), positionText(positionKey).getY(), key + "");
		StdDraw.picture(positionBotm.getX(), positionBotm.getY(), ImagePaths.BLOOD_OF_THE_MARTYR, size.getX(), size.getY(), 0);
		StdDraw.text(positionText(positionBotm).getX(), positionText(positionBotm).getY(), botm + "");
		StdDraw.picture(positionBombe.getX(), positionBombe.getY(), ImagePaths.BOMB, size.getX(), size.getY(), 0);
		StdDraw.text(positionText(positionBombe).getX(), positionText(positionBombe).getY(), bombe + "");
	}
	
	private Vector2 positionText(Vector2 position) {
		Vector2 pos = new Vector2(position);
		pos.addX(0.035);
		return pos;
	}
	public List<Objet> getObjets() {
		return objets;
	}

	public void setObjets(List<Objet> objets) {
		this.objets = objets;
	}

	public int getArgent() {
		return argent;
	}

	public void setArgent() {
		this.argent = 10 * dime + 5 * nickel + coin;
	}
	
	public void setPiece() {
		this.dime = this.argent / 10;
		this.nickel = (this.argent % 10) / 5;
		this.coin = (this.argent % 10) % 5; 
	}
	
	public void setArgent(int argent) {
		this.argent = argent;
	}
	public int getDime() {
		return dime;
	}

	public void setDime(int dime) {
		this.dime = dime;
	}

	public int getNickel() {
		return nickel;
	}

	public void setNickel(int nickel) {
		this.nickel = nickel;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getBotm() {
		return botm;
	}

	public void setBotm(int botm) {
		this.botm = botm;
	}



	public int getBombe() {
		return bombe;
	}

	public void setBombe(int bombe) {
		this.bombe = bombe;
	}
	
}
