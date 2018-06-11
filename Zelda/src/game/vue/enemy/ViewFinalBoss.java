package game.vue.enemy;

import javafx.scene.image.Image;

import java.io.File;

public class ViewFinalBoss {

	public Image up() {
        File image_final_boss_up = new File("src/game/resources/enemy/1ereformebossfinal.png");
        Image final_boss_up = new Image(image_final_boss_up.toURI().toString());
        return final_boss_up;
    }

    public Image down() {
    	File image_final_boss_down = new File("src/game/resources/enemy/1ereformebossfinal.png");
        Image final_boss_down = new Image(image_final_boss_down.toURI().toString());
        return final_boss_down;
    }

    public Image left() {
    	File image_final_boss_left = new File("src/game/resources/enemy/1ereformebossfinal.png");
        Image final_boss_left = new Image(image_final_boss_left.toURI().toString());
        return final_boss_left;
    }

    public Image right() {
    	File image_final_boss_right = new File("src/game/resources/enemy/1ereformebossfinal.png");
        Image final_boss_right = new Image(image_final_boss_right.toURI().toString());
        return final_boss_right;
    }
}