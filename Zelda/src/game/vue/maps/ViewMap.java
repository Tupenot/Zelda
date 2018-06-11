package game.vue.maps;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewMap {
	private Image house = new Image("/game/resources/house/house.png");
	private ImageView ihouse = new ImageView(house);
	private Image town = new Image("/game/resources/town/town.png");
	private ImageView itown = new ImageView(town);
	private Image plain = new Image("/game/resources/plain/plain.png");
	private ImageView iplain = new ImageView(plain);
	private Image fireway = new Image("/game/resources/fire/fireway.png");
	private ImageView ifireway = new ImageView(fireway);
	private Image iceway = new Image("/game/resources/ice/iceway.png");
	private ImageView iiceway = new ImageView(iceway);
	private Image dirtway = new Image("/game/resources/dirt/dirtway.png");
	private ImageView idirtway = new ImageView(dirtway);
	private Image fireroom = new Image("/game/resources/fire/fireroom.png");
	private ImageView ifireroom = new ImageView(fireroom);
	private Image fireboss = new Image("/game/resources/fire/fireboss.png");
	private ImageView ifireboss = new ImageView(fireboss);
	private Image dirtroom = new Image("/game/resources/dirt/dirtroom.png");
	private ImageView idirtroom = new ImageView(dirtroom);
	private Image dirtboss = new Image("/game/resources/dirt/dirtboss.png");
	private ImageView idirtboss = new ImageView(dirtboss);
	private Image iceroom = new Image("/game/resources/ice/iceroom.png");
	private ImageView iiceroom = new ImageView(iceroom);
	private Image iceboss = new Image("/game/resources/ice/iceboss.png");
	private ImageView iiceboss = new ImageView(iceboss);

	public ImageView house() {
	    return ihouse;
	}
	public ImageView town() {
	    return itown;
	}
	public ImageView plain() {
	    return iplain;
	}
	public ImageView fireway() {
	    return ifireway;
	}
	public ImageView iceway() {
	    return iiceway;
	}
	public ImageView dirtway() {
	    return idirtway;
	}
	public ImageView fireroom () {
	    return ifireroom;
	}
	public ImageView iceroom () {
	    return iiceroom;
	}
	public ImageView dirtroom () {
	    return idirtroom;
	}
	public ImageView fireboss () {
	    return ifireboss;
	}
	public ImageView iceboss () {
	    return iiceboss;
	}
	public ImageView dirtboss () {
	    return idirtboss;
	}
	public ImageView finalboss () {
	    return ihouse;
	}



}
