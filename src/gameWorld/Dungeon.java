package gameWorld;

import java.util.ArrayList;
import java.util.List;

public class Dungeon {
	private List<Etage> etages;
	
	public Dungeon(int nbrEtage) {
		etages = new ArrayList<Etage>();
		for(int i = 0; i < nbrEtage; i++) {
			etages.add(new Etage(i));
		}
	}

	public List<Etage> getEtages() {
		return etages;
	}

	public void setEtages(List<Etage> etages) {
		this.etages = etages;
	}
}
