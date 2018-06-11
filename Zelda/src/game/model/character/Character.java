package game.model.character;


import javafx.scene.image.ImageView;
import game.model.teardrop.Teardrop;


public class Character {

	private String name;
	private double heart;
	protected double armor;
	private Teardrop teardrop;
	private ImageView player;
	public Character(String name, double heart, double armor, Teardrop teardrop) {
		this.setName(name);
		this.setHeart(heart);
		this.setArmor(armor);
		this.teardrop = teardrop;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//public double getHeart() {
	//	return heart;
	//}

	public String getHeart() {
		if (this.heart == 0.0) {
			return heart + " coeur restant\n" + "Le personnage que vous essayez d'attaquer est déjà mort\n";
		}

		else {
			return heart + " coeurs restants";
		}
	}

	public void setHeart(double heart) {
		this.heart = heart;
	}

	public double getArmor() {
		return armor;
	}

	public void setArmor(double armor) {
		this.armor = armor;
	}

	public void attack(Character p1, Teardrop a1) {
		double damage = a1.getAttack();
		p1.guard(damage);
	}

	public void guard(double damage) {

		if (isAlive()) {
			if (armorTrue()) {
				if (this.armor <= damage) {
					damage -= this.armor;
					this.armor = 0;
					this.heart -= damage;
				} else {
					this.armor -= damage;
				}
			} else {
				this.heart -= damage;
			}
		}
	}

	public boolean armorTrue() {
		boolean armureActive = false;
		
		return armureActive ;
	}

	public boolean isAlive() {
		boolean isAlive = true;
		if (this.heart <= 0.0) {
			return !isAlive;
		}
		else {
			return isAlive;
		}
	}
}
