package game.model.character;

import game.model.teardrop.Bow;
import game.model.teardrop.Teardrop;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableNumberValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

public class Bowman extends Enemy {
	 static Teardrop bow = new Bow ("Bow", 0.25, 4);
	 IntegerProperty x;

	public Bowman() {
		super ("Bowman", 15, 0, bow);
	}

	public void attack() {
	}

	public IntegerProperty getX() {
		return x;
	}

	public void setX(IntegerProperty x) {
		this.x = x;
	}
}
