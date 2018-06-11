package game.vue.enemy;

import game.model.character.Enemy;
import javafx.scene.image.Image;

import java.io.File;

public class ViewNormalBokoblinSword implements ViewEnemy {
	
	public Image up() {
		File image_normal_bokoblin_sword_up = new File("src/game/resources/enemy/basicsword.png");
		Image normal_bokoblin_sword_up = new Image(image_normal_bokoblin_sword_up.toURI().toString());
		return normal_bokoblin_sword_up;
	}
	
	public Image down() {
		File image_normal_bokoblin_sword_down = new File("src/game/resources/enemy/basicsword.png");
		Image normal_bokoblin_sword_down = new Image(image_normal_bokoblin_sword_down.toURI().toString());
		return normal_bokoblin_sword_down;
	}
	
	public Image left() {
		File image_normal_bokoblin_sword_left = new File("src/game/resources/enemy/basicsword.png");
		Image normal_bokoblin_sword_left = new Image(image_normal_bokoblin_sword_left.toURI().toString());
		return normal_bokoblin_sword_left;
	}
	
	public Image right() {
		File image_normal_bokoblin_sword_right = new File("src/game/resources/enemy/basicsword.png");
		Image normal_bokoblin_sword_right = new Image(image_normal_bokoblin_sword_right.toURI().toString());
		return normal_bokoblin_sword_right;
	}

	public Image isDead () {
		File image_normal_bokoblin_sword_dead = new File ("");
		Image normal_bokoblin_sword_dead = new Image (image_normal_bokoblin_sword_dead.toURI().toString());
		return normal_bokoblin_sword_dead;
	}

}
