package game.vue.enemy;

import javafx.scene.image.Image;

import java.io.File;

public class ViewIceBokoblinSword {

	public Image up() {
        File image_ice_bokoblin_sword_up = new File("src/game/resources/enemy/icesword.png");
        Image ice_bokoblin_sword_up = new Image(image_ice_bokoblin_sword_up.toURI().toString());
        return ice_bokoblin_sword_up;
    }
	
	public Image down() {
    	File image_ice_bokoblin_sword_down = new File("src/game/resources/enemy/icesword.png");
        Image ice_bokoblin_sword_down = new Image(image_ice_bokoblin_sword_down.toURI().toString());
        return ice_bokoblin_sword_down;
    }

    public Image left() {
    	File image_ice_bokoblin_sword_left = new File("src/game/resources/enemy/icesword.png");
        Image ice_bokoblin_sword_left = new Image(image_ice_bokoblin_sword_left.toURI().toString());
        return ice_bokoblin_sword_left;
    }

    public Image right() {
    	File image_ice_bokoblin_sword_right = new File("src/game/resources/enemy/icesword.png");
        Image ice_bokoblin_sword_right = new Image(image_ice_bokoblin_sword_right.toURI().toString());
        return ice_bokoblin_sword_right;
    }
}
