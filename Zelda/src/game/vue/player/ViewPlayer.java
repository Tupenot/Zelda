package game.vue.player;

import javafx.scene.image.Image;

import java.io.File;

public class ViewPlayer {

    public Image up() {
        File image_link_up = new File("src/game/resources/player/player_up.png");
        Image link_up = new Image(image_link_up.toURI().toString());
        return link_up;
    }

    public Image down() {
        File image_link_down = new File("src/game/resources/player/player_down.png");
        Image link_down = new Image(image_link_down.toURI().toString());
        return link_down;
    }

    public Image left() {
        File image_link_left = new File("src/game/resources/player/player_left.png");
        Image link_left = new Image(image_link_left.toURI().toString());
        return link_left;
    }

    public Image right() {
        File image_link_right = new File("src/game/resources/player/player_right.png");
        Image link_right = new Image(image_link_right.toURI().toString());
        return link_right;
    }
}
