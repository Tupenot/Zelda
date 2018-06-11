package game.vue.enemy;

import game.model.character.Enemy;
import javafx.scene.image.Image;

import java.io.File;

public interface ViewEnemy {
	
	public Image up();
	
	public Image down();
	
	public Image left();
	
	public Image right();

	public Image isDead();
}
