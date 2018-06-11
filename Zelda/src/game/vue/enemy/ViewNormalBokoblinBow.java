package game.vue.enemy;

import javafx.scene.image.Image;

import java.io.File;

public class ViewNormalBokoblinBow {

    public Image up() {
        File image_normal_bokoblin_bow_up = new File("src/game/resources/enemy/basicbowman.png");
        Image normal_bokoblin_bow_up = new Image(image_normal_bokoblin_bow_up.toURI().toString());
        return normal_bokoblin_bow_up;
    }

    public Image down() {
    	File image_normal_bokoblin_bow_down = new File("src/game/resources/enemy/basicbowman.png");
        Image normal_bokoblin_bow_down = new Image(image_normal_bokoblin_bow_down.toURI().toString());
        return normal_bokoblin_bow_down;
    }

    public Image left() {
    	File image_normal_bokoblin_bow_left = new File("src/game/resources/enemy/basicbowman.png");
        Image normal_bokoblin_bow_left = new Image(image_normal_bokoblin_bow_left.toURI().toString());
        return normal_bokoblin_bow_left;
    }

    public Image right() {
    	File image_normal_bokoblin_bow_right = new File("src/game/resources/enemy/basicbowman.png");
        Image normal_bokoblin_bow_right = new Image(image_normal_bokoblin_bow_right.toURI().toString());
        return normal_bokoblin_bow_right;
    }
}
