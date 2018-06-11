package game.model.teardrop;

public class Teardrop {
	
	private String name;
	private double attack;
	
	public Teardrop(String name, double attack) {
		this.setName(name);
		this.setAttack(attack);
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAttack() {
		return attack;
	}

	public void setAttack(double attack) {
		this.attack = attack;
	}	
}
