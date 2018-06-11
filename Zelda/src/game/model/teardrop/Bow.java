package game.model.teardrop;

public class Bow extends Teardrop {

	private int reach;
	
	public Bow(String name, double attack) {
		super(name, attack);
	}
	
	public Bow(String name, double attack, int reach) {
		super(name, attack);
		this.setReach(reach);
	}

	public int getReach() {
		return reach;
	}

	public void setReach(int reach) {
		this.reach = reach;
	}
}
