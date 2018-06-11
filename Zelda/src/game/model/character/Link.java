package game.model.character;

import game.model.teardrop.Teardrop;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;

import javafx.scene.input.KeyEvent;

public class Link extends Character {
	
	private ArrayList<Teardrop> inventoryofarms;
	private Teardrop teardrop;
	
	public Link(String name, double heart, double armor, Teardrop teardrop) {
		super("Link", 3, 3, teardrop);
		this.inventoryofarms = new ArrayList<Teardrop>();
	}
	
	public void gatherTeardrop(Teardrop teardrop) {
		this.teardrop = null;
		this.teardrop = teardrop;
	}

	public void activateArmor(KeyEvent event) {
		if (event.getCode() == KeyCode.TAB) {
			this.armor = 3;
		}
		
		else if ((event.getCode() == KeyCode.TAB)) {
			this.armor = 5;
		}
	}
}
