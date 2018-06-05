package controleur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
public class Controleur implements Initializable {
	private boolean anim = false;
	private Timeline gameLoop;
	private int temps;
	@FXML
	private int[][] map;

	@FXML
	private TilePane tilePaneFX;
	@FXML
	private ImageView joueur;
	@FXML
	void deplacement(KeyEvent event) {
		if((event.getCode() == KeyCode.UP || event.getCode() == KeyCode.Z) && 
				map[(int) (joueur.getLayoutY()/16-3)][(int) (joueur.getLayoutX()/16)]!=2
				&& map[(int) (joueur.getLayoutY()/16)][(int) (joueur.getLayoutX()/16)]!=10
				&& map[(int) (joueur.getLayoutY()/16-1)][(int) (joueur.getLayoutX()/16)]!=12
				&& map[(int) (joueur.getLayoutY()/16-1)][(int) (joueur.getLayoutX()/16)]!=15) {
			joueur.setLayoutY(joueur.getLayoutY()-16);
			joueur.setImage(new Image("/ressources/joueur_H.png"));
		}
		if((event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) && map[(int) (joueur.getLayoutY()/16+1)][(int) (joueur.getLayoutX()/16+1)]!=1 
				&& map[(int) (joueur.getLayoutY()/16)][(int) (joueur.getLayoutX()/16)]!=5
				&& map[(int) (joueur.getLayoutY()/16+1)][(int) (joueur.getLayoutX()/16)]!=14)
		{
			joueur.setLayoutY(joueur.getLayoutY()+16);
			joueur.setImage(new Image("/ressources/joueur_B.png"));
		}
		if((event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.Q) && map[(int) (joueur.getLayoutY()/16)][(int) (joueur.getLayoutX()/16-2)]!=55
				&& map[(int) (joueur.getLayoutY()/16)][(int) (joueur.getLayoutX()/16)]!=4
				&& map[(int) (joueur.getLayoutY()/16)][(int) (joueur.getLayoutX()/16)]!=9
				&& map[(int) (joueur.getLayoutY()/16)][(int) (joueur.getLayoutX()/16)]!=11) {
			joueur.setLayoutX(joueur.getLayoutX()-16);
			joueur.setImage(new Image("/ressources/joueur_G.png"));
		}
		if((event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) && map[(int) (joueur.getLayoutY()/16)][(int) (joueur.getLayoutX()/16)]!=3
				&& map[(int) (joueur.getLayoutY()/16)][(int) (joueur.getLayoutX()/16+4)]!=56
				&& map[(int) (joueur.getLayoutY()/16)][(int) (joueur.getLayoutX()/16)]!=8
				&& map[(int) (joueur.getLayoutY()/16)][(int) (joueur.getLayoutX()/16)]!=13) {
			joueur.setLayoutX(joueur.getLayoutX()+16);
			joueur.setImage(new Image("/ressources/joueur_D.png"));
		}
		if (event.getCode() == KeyCode.ENTER) {
			System.out.println("J'ai envie d'intéragir avec toi, mais il faut attendre !");
		}
		System.out.println("Identifiant de case : " + map[(int) (joueur.getLayoutY()/16)][(int) (joueur.getLayoutX()/16)]+" | Coordonnées :  "+ (int) joueur.getLayoutY()/16+" (Hauteur, Largeur) "+(int) joueur.getLayoutX()/16);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initAnimation();
		gameLoop.play();
		joueur.setImage(new Image("/ressources/joueur_H.png"));
		joueur.setFocusTraversable(true);
		map = new int[][] { {55,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,56},
			{55,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,56},
			{55,-1,-1,-1,-1,-1,-1,-1,11,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,11,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,11,-1,8,-1,-1,-1,-1,-1,-1,-1,-1,9,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,11,-1,8,-1,-1,-1,-1,-1,-1,-1,-1,9,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,11,-1,-1,10,10,10,10,10,10,10,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,11,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,11,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,11,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,11,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,11,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,12,12,12,12,12,12,12,11,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,13,14,14,14,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,13,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,13,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,13,15,15,15,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,13,14,14,14,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,5,5,-1,-1,-1,-1,5,5,-1,-1,-1,-1,-1,13,-1,-1,-1,-1,-1,-1,56},
			{55,-1,-1,-1,-1,-1,-1,-1,-1,-1,3,-1,-1,4,-1,-1,3,-1,-1,4,-1,-1,-1,-1,13,-1,-1,-1,-1,-1,-1,56},
			{55,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,56},
			{55,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,56}};
			Image im = new Image("/ressources/Maison.png");
			for (int i = 0; i < 30; i++) {
				for (int j = 0; j < 30; j++) {
					ImageView imw = new ImageView(im);
					switch (map[i][j]) {
					case 1:
						imw.setViewport(new Rectangle2D(0, 0, 16, 16));
						break;
					case 2:
						imw.setViewport(new Rectangle2D(1 * 16, 0, 16, 16));
						break;
					case 3:
						imw.setViewport(new Rectangle2D(2 * 16, 0, 16, 16));
						break;
					case 4:
						imw.setViewport(new Rectangle2D(3 * 16, 0, 16, 16));
						break;
					case 5:
						imw.setViewport(new Rectangle2D(4 * 16, 0, 16, 16));
						break;
					case 6:
						imw.setViewport(new Rectangle2D(5 * 16, 0, 16, 16));
						break;
					case 7:
						imw.setViewport(new Rectangle2D(6 * 16, 0, 16, 16));
						break;
					case 8:
						imw.setViewport(new Rectangle2D(7 * 16, 0, 16, 16));
						break;
					case 9:
						imw.setViewport(new Rectangle2D(16 * 16, 0, 16, 16));
						break;
					case 10:
						imw.setViewport(new Rectangle2D(9 * 16 , 0, 16, 16));
						break;
					case 11:
						imw.setViewport(new Rectangle2D(0 * 16, 1 * 16, 16, 16));
						break;
					case 12:
						imw.setViewport(new Rectangle2D(1 * 16, 1 * 16, 16, 16));
						break;
					case 13:
						imw.setViewport(new Rectangle2D(2 * 16, 1 * 16, 16, 16));
						break;
					case 14:
						imw.setViewport(new Rectangle2D(3 * 16, 1 * 16, 16, 16));
						break;
					case 15:
						imw.setViewport(new Rectangle2D(4 * 16, 1 * 16, 16, 16));
						break;
					case 16:
						imw.setViewport(new Rectangle2D(5 * 16, 1 * 16, 16, 16));
						break;
					case 17:
						imw.setViewport(new Rectangle2D(6 * 16, 1 * 16, 16, 16));
						break;
					case 116:
						imw.setViewport(new Rectangle2D(7 * 16, 1 * 16, 16, 16));
						break;
					case 19:
						imw.setViewport(new Rectangle2D(16 * 16, 1 * 16, 16, 16));
						break;
					case 20:
						imw.setViewport(new Rectangle2D(9 * 16, 1 * 16, 16, 16));
						break;
					case 21:
						imw.setViewport(new Rectangle2D(0 , 2 * 16, 16, 16));
						break;
					case 22:
						imw.setViewport(new Rectangle2D(1 * 16, 2 * 16, 16, 16));
						break;
					case 23:
						imw.setViewport(new Rectangle2D(2 * 16, 2 * 16, 16, 16));
						break;
					case 24:
						imw.setViewport(new Rectangle2D(3 * 16, 2 * 16, 16, 16));
						break;
					case 25:
						imw.setViewport(new Rectangle2D(4 * 16, 2 * 16, 16, 16));
						break;
					case 26:
						imw.setViewport(new Rectangle2D(5 * 16, 2 * 16, 16, 16));
						break;
					case 27:
						imw.setViewport(new Rectangle2D(1 * 16, 2 * 16, 16, 16));
						break;
					case 216:
						imw.setViewport(new Rectangle2D(7 * 16, 0, 16, 16));
						break;
					case 29:
						imw.setViewport(new Rectangle2D(1 * 16, 0, 16, 16));
						break;
					case 30:
						imw.setViewport(new Rectangle2D(4 * 16, 0, 16, 16));
						break;
					case 31:
						imw.setViewport(new Rectangle2D(5 * 16, 0, 16, 16));
						break;
					case 32:
						imw.setViewport(new Rectangle2D(6 * 16, 0, 16, 16));
						break;
					case 33:
						imw.setViewport(new Rectangle2D(0, 0, 16, 16));
						break;
					}
					tilePaneFX.getChildren().add(imw);
				}
			}
	}
	private void initAnimation() {
		gameLoop = new Timeline();
		temps=0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		if(anim) {
			KeyFrame animup = new KeyFrame(
					Duration.seconds(60), 
					(deplacement ->{
						if(temps == 1) {
							joueur.setImage(new Image("/ressources/joueur_H.png"));
						}
						else if(temps == 2) {
							joueur.setImage(new Image("/ressources/joueur_H.png"));
						}
						else if(temps == 3) {
							joueur.setImage(new Image("/ressources/joueur_H.png"));
						}
						else if(temps == 4) {
							joueur.setImage(new Image("/ressources/joueur_H.png"));
						}
						temps++;
					})
					);
			gameLoop.getKeyFrames().add(animup);
		}
	}
}