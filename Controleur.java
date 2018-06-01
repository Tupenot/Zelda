package controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.javafx.collections.MappingChange.Map;

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
import app.Main;

public class Controleur implements Initializable {

	private Timeline gameLoop;

	private int temps;

	private boolean anim = false;
	private boolean changementmap1 = false;
	private boolean changementmap2 = false;
	private boolean changementmap3 = false;
	private boolean changementmap4 = false;
	private boolean changementmap5 = false;

	@FXML
	private int[][] map;
	private int[][] mapvillage;
	private int[][] plaine;
	private int[][] cfeu;
	private int[][] cglace;
	private int[][] cterre;

	private Image im = new Image("/ressources/Maison.png");
	private ImageView imw = new ImageView(im);
	private Image imvill = new Image("/ressources/Village.png");
	private ImageView imwvill = new ImageView(imvill);
	private Image implaine = new Image("/ressources/plaine.png");
	private ImageView imwplaine = new ImageView(implaine);
	private Image imcfeu = new Image("/ressources/Cfeu.png");
	private ImageView imwcfeu = new ImageView(imcfeu);
	private Image imcglace = new Image("/ressources/Cglace.png");
	private ImageView imwcglace = new ImageView(imcglace);
	private Image imcterre = new Image("/ressources/Cterre.png");
	private ImageView imwcterre = new ImageView(imcterre);

	@FXML
	private TilePane tilePaneFX;

	@FXML
	private ImageView joueur;

	@FXML
	void deplacement(KeyEvent event) {

		////////// CHEAT CODES //////////

		if (event.getCode() == KeyCode.ESCAPE) {
			System.exit(0);
		}

		if ((event.getCode() == KeyCode.O)) {
			changementmap1 = false;
			changementmap2 = false;
			changementmap3 = false;
			changementmap4 = false;
			changementmap5 = false;

			tilePaneFX.getChildren().remove(imwvill);
			tilePaneFX.getChildren().remove(imwplaine);
			tilePaneFX.getChildren().remove(imwcfeu);
			tilePaneFX.getChildren().remove(imwcglace);
			tilePaneFX.getChildren().remove(imwcterre);
			tilePaneFX.getChildren().add(imw);

			joueur.setImage(new Image("/ressources/joueur_D.png"));
			joueur.setLayoutX(144);
			joueur.setLayoutY(144);
		}

		if ((event.getCode() == KeyCode.P)) {
			changementmap1 = true;
			changementmap2 = false;
			changementmap3 = false;
			changementmap4 = false;
			changementmap5 = false;

			tilePaneFX.getChildren().remove(imw);
			tilePaneFX.getChildren().remove(imwplaine);
			tilePaneFX.getChildren().remove(imwcfeu);
			tilePaneFX.getChildren().remove(imwcglace);
			tilePaneFX.getChildren().remove(imwcterre);
			tilePaneFX.getChildren().add(imwvill);

			joueur.setImage(new Image("/ressources/joueur_B.png"));
			joueur.setLayoutX(272);
			joueur.setLayoutY(336);
		}

		if ((event.getCode() == KeyCode.M)) {
			changementmap1 = false;
			changementmap2 = true;
			changementmap3 = false;
			changementmap4 = false;
			changementmap5 = false;

			tilePaneFX.getChildren().remove(imw);
			tilePaneFX.getChildren().remove(imwvill);
			tilePaneFX.getChildren().remove(imwcfeu);
			tilePaneFX.getChildren().remove(imwcglace);
			tilePaneFX.getChildren().remove(imwcterre);
			tilePaneFX.getChildren().add(imwplaine);

			joueur.setImage(new Image("/ressources/joueur_D.png"));
			joueur.setLayoutX(16);
			joueur.setLayoutY(322);
		}

		if ((event.getCode() == KeyCode.L)) {
			changementmap1 = false;
			changementmap2 = false;
			changementmap3 = true;
			changementmap4 = false;
			changementmap5 = false;

			tilePaneFX.getChildren().remove(imw);
			tilePaneFX.getChildren().remove(imwvill);
			tilePaneFX.getChildren().remove(imwplaine);
			tilePaneFX.getChildren().remove(imwcglace);
			tilePaneFX.getChildren().remove(imwcterre);
			tilePaneFX.getChildren().add(imwcfeu);

			joueur.setImage(new Image("/ressources/joueur_B.png"));
			joueur.setLayoutX(272);
			joueur.setLayoutY(16);
		}

		if (event.getCode() == KeyCode.K) {
			changementmap1 = false;
			changementmap2 = false;
			changementmap3 = false;
			changementmap4 = true;
			changementmap5 = false;

			tilePaneFX.getChildren().remove(imw);
			tilePaneFX.getChildren().remove(imwvill);
			tilePaneFX.getChildren().remove(imwplaine);
			tilePaneFX.getChildren().remove(imwcfeu);
			tilePaneFX.getChildren().remove(imwcterre);
			tilePaneFX.getChildren().add(imwcglace);

			joueur.setLayoutX(400);
			joueur.setLayoutY(560);
			joueur.setImage(new Image("/ressources/joueur_H.png"));
		}

		if (event.getCode() == KeyCode.J) {
			changementmap1 = false;
			changementmap2 = false;
			changementmap3 = false;
			changementmap4 = false;
			changementmap5 = true;

			tilePaneFX.getChildren().remove(imw);
			tilePaneFX.getChildren().remove(imwvill);
			tilePaneFX.getChildren().remove(imwplaine);
			tilePaneFX.getChildren().remove(imwcfeu);
			tilePaneFX.getChildren().remove(imwcglace);
			tilePaneFX.getChildren().add(imwcterre);

			joueur.setLayoutX(32);
			joueur.setLayoutY(176);
			joueur.setImage(new Image("/ressources/joueur_D.png"));
		}

		//////////DEPLACEMENT MAP N°1 //////////

		if (!changementmap1 && !changementmap2 && !changementmap3 && !changementmap4 && !changementmap5) {
			if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.Z) {
				if (((int) joueur.getLayoutY() / 16) - 1 > 0 && map[((int) joueur.getLayoutY() / 16) - 1][(int) (joueur.getLayoutX() / 16)] == -1) {
					joueur.setLayoutY(joueur.getLayoutY() - 16);
					joueur.setImage(new Image("/ressources/joueur_H.png"));
				}
			}

			if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
				if ((((int) joueur.getLayoutY() / 16) + 1 < 31) && map[((int) joueur.getLayoutY() / 16) + 1][(int) (joueur.getLayoutX() / 16)] == -1 || 
						map[((int) joueur.getLayoutY() / 16) + 1][(int) (joueur.getLayoutX() / 16)] == 20) {
					joueur.setLayoutY(joueur.getLayoutY() + 16);
					joueur.setImage(new Image("/ressources/joueur_B.png"));
				}
			}

			if ((event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.Q)) {
				if (((int) (joueur.getLayoutX() / 16)) > 3 && map[(int) (joueur.getLayoutY() / 16)][((int) (joueur.getLayoutX() / 16)) - 1] == -1) {
					joueur.setLayoutX(joueur.getLayoutX() - 16);
					joueur.setImage(new Image("/ressources/joueur_G.png"));
				}
			}

			if ((event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D)) {
				if (((int) (joueur.getLayoutX() / 16)) + 1 < 32 && map[(int) (joueur.getLayoutY() / 16)][((int) (joueur.getLayoutX() / 16)) + 1] == -1) {
					joueur.setLayoutX(joueur.getLayoutX() + 16);
					joueur.setImage(new Image("/ressources/joueur_D.png"));
				}
			}

			if (map[((int) joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16)] == 20) {
				changementmap1 = true;
				changementmap2 = false;
				changementmap4 = false;
				changementmap5 = false;

				tilePaneFX.getChildren().remove(imw);
				tilePaneFX.getChildren().add(imwvill);

				joueur.setLayoutX(272);
				joueur.setLayoutY(336);
				joueur.setImage(new Image("/ressources/joueur_B.png"));
			}

			System.out.println("Identifiant de case (1) : " + map[(int) (joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16)] + 
					" | Coordonnées :  " + (int) joueur.getLayoutY() / 16 + " (Hauteur, Largeur) " + (int) joueur.getLayoutX() / 16);
		}

		////////// DEPLACEMENT MAP N°2 //////////

		else if (changementmap1 && !changementmap2 && !changementmap3 && !changementmap4 && !changementmap5) {
			if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.Z) {
				if (((int) joueur.getLayoutY() / 16) - 1 > 0 && mapvillage[((int) joueur.getLayoutY() / 16) - 1][(int) (joueur.getLayoutX() / 16)] == -1 || 
						mapvillage[((int) joueur.getLayoutY() / 16 - 1)][(int) (joueur.getLayoutX() / 16)] == 21) {
					joueur.setLayoutY(joueur.getLayoutY() - 16);
					joueur.setImage(new Image("/ressources/joueur_H.png"));
				}
			}

			if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
				if ((((int) joueur.getLayoutY() / 16) < 29) && mapvillage[((int) joueur.getLayoutY() / 16) + 1][(int) (joueur.getLayoutX() / 16)] == -1) {
					joueur.setLayoutY(joueur.getLayoutY() + 16);
					joueur.setImage(new Image("/ressources/joueur_B.png"));
				}
			}

			if ((event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.Q)) {
				if (((int) (joueur.getLayoutX() / 16)) > 13 && mapvillage[(int) (joueur.getLayoutY() / 16)][((int) (joueur.getLayoutX() / 16)) - 1] == -1) {
					joueur.setLayoutX(joueur.getLayoutX() - 16);
					joueur.setImage(new Image("/ressources/joueur_G.png"));
				}
			}
			if ((event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D)) {
				if (((int) (joueur.getLayoutX() / 16)) < 42 && mapvillage[(int) (joueur.getLayoutY() / 16)][((int) (joueur.getLayoutX() / 16)) + 1] == -1 || 
						mapvillage[((int) joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16) + 1] == 20) {
					joueur.setLayoutX(joueur.getLayoutX() + 16);
					joueur.setImage(new Image("/ressources/joueur_D.png"));
				}
			}

			if (mapvillage[((int) joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16)] == 20) {
				changementmap1 = false;
				changementmap2 = true;
				changementmap4 = false;
				changementmap5 = false;

				tilePaneFX.getChildren().remove(imwvill);
				tilePaneFX.getChildren().add(imwplaine);

				joueur.setLayoutX(16);
				joueur.setLayoutY(322);
				joueur.setImage(new Image("/ressources/joueur_D.png"));
			}

			if (mapvillage[((int) joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16)] == 21) {
				changementmap1 = false;
				changementmap2 = false;
				changementmap4 = false;
				changementmap5 = false;

				tilePaneFX.getChildren().remove(imwvill);
				tilePaneFX.getChildren().add(imw);

				joueur.setLayoutX(240);
				joueur.setLayoutY(464);
				joueur.setImage(new Image("/ressources/joueur_H.png"));
			}

			System.out.println("Identifiant de case (2) : " + mapvillage[(int) (joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16)] + 
					" | Coordonnées :  " + (int) joueur.getLayoutX() / 16 + " (Hauteur, Largeur) " + (int) joueur.getLayoutY() / 16);
		}

		////////// DEPLACEMENT MAP N°3 //////////

		else if (changementmap2 && !changementmap3 && !changementmap1 && !changementmap4 && !changementmap5) {
			if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.Z) {
				if (((int) joueur.getLayoutY() / 16) - 1 > 0 && plaine[((int) joueur.getLayoutY() / 16) - 1][(int) (joueur.getLayoutX() / 16)] == 10 || 
						plaine[((int) joueur.getLayoutY() / 16) - 1][(int) (joueur.getLayoutX() / 16)] == 24) {
					joueur.setLayoutY(joueur.getLayoutY() - 16);
					joueur.setImage(new Image("/ressources/joueur_H.png"));
				}
			}

			if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
				if (((int) joueur.getLayoutY() / 16) - 1 < 45 && plaine[((int) joueur.getLayoutY() / 16) + 1][(int) (joueur.getLayoutX() / 16)] == 10 || 
						plaine[((int) joueur.getLayoutY() /16) + 1][(int) (joueur.getLayoutX() / 16)] == 22) {
					joueur.setLayoutY(joueur.getLayoutY() + 16);
					joueur.setImage(new Image("/ressources/joueur_B.png"));
				}
			}

			if ((event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.Q)) {
				if (((int) (joueur.getLayoutX() / 16)) > 1 && plaine[(int) (joueur.getLayoutY() / 16)][((int) (joueur.getLayoutX() / 16)) - 1] == 10 || 
						plaine[((int) joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16) - 1] == 20) {
					joueur.setLayoutX(joueur.getLayoutX() - 16);
					joueur.setImage(new Image("/ressources/joueur_G.png"));
				}
			}
			if ((event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D)) {
				if (((int) (joueur.getLayoutX() / 16)) + 1 < 61 && plaine[(int) (joueur.getLayoutY() / 16)][((int) (joueur.getLayoutX() / 16)) + 1] == 10 || 
						(plaine[((int) joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16) + 1] == 26)) {
					joueur.setLayoutX(joueur.getLayoutX() + 16);
					joueur.setImage(new Image("/ressources/joueur_D.png"));
				}
			}

			if (plaine[((int) joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16)] == 20) {
				changementmap1 = true;
				changementmap2 = false;
				changementmap4 = false;
				changementmap5 = false;

				tilePaneFX.getChildren().remove(imwplaine);
				tilePaneFX.getChildren().add(imwvill);

				joueur.setLayoutX(656);
				joueur.setLayoutY(176);
				joueur.setImage(new Image("/ressources/joueur_G.png"));
			}

			if (plaine[((int) joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16)] == 22) {
				changementmap1 = false;
				changementmap2 = false;
				changementmap3 = true;
				changementmap4 = false;
				changementmap5 = false;

				tilePaneFX.getChildren().remove(imwplaine);
				tilePaneFX.getChildren().add(imwcfeu);

				joueur.setLayoutX(272);
				joueur.setLayoutY(16);
				joueur.setImage(new Image("/ressources/joueur_B.png"));
			}

			if (plaine[((int) joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16)] == 24) {
				changementmap1 = false;
				changementmap2 = false;
				changementmap3 = false;
				changementmap4 = true;
				changementmap5 = false;

				tilePaneFX.getChildren().remove(imwplaine);
				tilePaneFX.getChildren().add(imwcglace);

				joueur.setLayoutX(400);
				joueur.setLayoutY(560);
				joueur.setImage(new Image("/ressources/joueur_H.png"));
			}

			if (plaine[((int) joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16)] == 26) {
				changementmap1 = false;
				changementmap2 = false;
				changementmap3 = false;
				changementmap4 = false;
				changementmap5 = true;

				tilePaneFX.getChildren().remove(imwplaine);
				tilePaneFX.getChildren().add(imwcterre);

				joueur.setLayoutX(32);
				joueur.setLayoutY(176);
				joueur.setImage(new Image("/ressources/joueur_D.png"));
			}

			System.out.println("Identifiant de case (3) : " + plaine[(int) (joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16)] + " | Coordonnées :  " + (int) joueur.getLayoutX() / 16 + " (Hauteur, Largeur) " + (int) joueur.getLayoutY() / 16);

		}

		////////// DEPLACEMENT MAP N°4 //////////

		else if (changementmap3 && !changementmap2 && !changementmap1 && !changementmap4 && !changementmap5) {

			if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.Z) {
				if (((int) joueur.getLayoutY() / 16) - 1 > 0 && cfeu[((int) joueur.getLayoutY() / 16) - 1][(int) (joueur.getLayoutX() / 16)] == -1 || 
						cfeu[((int) joueur.getLayoutY() / 16 - 1)][(int) (joueur.getLayoutX() / 16)] == 23) {
					joueur.setLayoutY(joueur.getLayoutY() - 16);
					joueur.setImage(new Image("/ressources/joueur_H.png"));
				}
			}

			if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
				if (((int) joueur.getLayoutY() / 16) + 1 < 30 && cfeu[((int) joueur.getLayoutY() / 16) + 1][(int) (joueur.getLayoutX() / 16)] == -1) {
					joueur.setLayoutY(joueur.getLayoutY() + 16);
					joueur.setImage(new Image("/ressources/joueur_B.png"));
				}
			}
			if ((event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.Q)) {
				if (((int) (joueur.getLayoutX() / 16)) > 1 && cfeu[(int) (joueur.getLayoutY() / 16)][((int) (joueur.getLayoutX() / 16)) - 1] == -1) {
					joueur.setLayoutX(joueur.getLayoutX() - 16);
					joueur.setImage(new Image("/ressources/joueur_G.png"));
				}
			}
			if ((event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D)) {
				if (((int) (joueur.getLayoutX() / 16)) + 1 < 36 && cfeu[(int) (joueur.getLayoutY() / 16)][((int) (joueur.getLayoutX() / 16)) + 1] == -1) {
					joueur.setLayoutX(joueur.getLayoutX() + 16);
					joueur.setImage(new Image("/ressources/joueur_D.png"));
				}
			}

			if (cfeu[((int) joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16)] == 23) {
				changementmap1 = false;
				changementmap2 = true;
				changementmap3 = false;
				changementmap4 = false;
				changementmap5 = false;

				tilePaneFX.getChildren().remove(imwcfeu);
				tilePaneFX.getChildren().add(imwplaine);

				joueur.setLayoutX(452);
				joueur.setLayoutY(736);
				joueur.setImage(new Image("/ressources/joueur_H.png"));
			}

			System.out.println("Identifiant de case (4) : " + cfeu[(int) (joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16)] + 
					" | Coordonnées :  " + (int) joueur.getLayoutX() / 16 + " (Hauteur, Largeur) " + (int) joueur.getLayoutY() / 16);
		}

		////////// DEPLACEMENT MAP N°5 //////////

		else if (changementmap4 && !changementmap3 && !changementmap2 && !changementmap1 && !changementmap5) {
			if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.Z) {
				if (((int) joueur.getLayoutY() / 16) - 1 > 0 && cglace[((int) joueur.getLayoutY() / 16) - 1][(int) (joueur.getLayoutX() / 16)] == 926) {
					joueur.setLayoutY(joueur.getLayoutY() - 16);
					joueur.setImage(new Image("/ressources/joueur_H.png"));
				}
			}

			if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
				if (((int) joueur.getLayoutY() / 16) + 1 < 37 && cglace[((int) joueur.getLayoutY() / 16) + 1][(int) (joueur.getLayoutX() / 16)] == 926 || 
						cglace[((int) joueur.getLayoutY() / 16) + 1][(int) (joueur.getLayoutX() / 16)] == 25) {
					joueur.setLayoutY(joueur.getLayoutY() + 16);
					joueur.setImage(new Image("/ressources/joueur_B.png"));
				}
			}

			if ((event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.Q)) {
				if (((int) (joueur.getLayoutX() / 16)) > 1 && cglace[(int) (joueur.getLayoutY() / 16)][((int) (joueur.getLayoutX() / 16)) - 1] == 926) {
					joueur.setLayoutX(joueur.getLayoutX() - 16);
					joueur.setImage(new Image("/ressources/joueur_G.png"));
				}
			}

			if ((event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D)) {
				if (((int) (joueur.getLayoutX() / 16)) + 1 < 43 && cglace[(int) (joueur.getLayoutY() / 16)][((int) (joueur.getLayoutX() / 16)) + 1] == 926) {
					joueur.setLayoutX(joueur.getLayoutX() + 16);
					joueur.setImage(new Image("/ressources/joueur_D.png"));
				}
			}

			if (cglace[((int) joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16)] == 25) {
				changementmap1 = false;
				changementmap2 = true;
				changementmap3 = false;
				changementmap4 = false;
				changementmap5 = false;

				tilePaneFX.getChildren().remove(imwcglace);
				tilePaneFX.getChildren().add(imwplaine);

				joueur.setLayoutX(464);
				joueur.setLayoutY(16);
				joueur.setImage(new Image("/ressources/joueur_B.png"));
			}

			System.out.println("Identifiant de case (5) : " + cglace[(int) (joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16)] + 
					" | Coordonnées :  " + (int) joueur.getLayoutX() / 16 + " (Hauteur, Largeur) " + (int) joueur.getLayoutY() / 16);

		}

		////////// DEPLACEMENT MAP N°6 //////////

		else if (changementmap5 && !changementmap4 && !changementmap3 && !changementmap2 && !changementmap1) {
			if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.Z) {
				if (((int) joueur.getLayoutY() / 16) - 1 > 0 && cterre[((int) joueur.getLayoutY() / 16) - 1][(int) (joueur.getLayoutX() / 16)] == 212) {
					joueur.setLayoutY(joueur.getLayoutY() - 16);
					joueur.setImage(new Image("/ressources/joueur_H.png"));
				}
			}

			if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
				if (((int) joueur.getLayoutY() / 16) + 1 < 37 && cterre[((int) joueur.getLayoutY() / 16) + 1][(int) (joueur.getLayoutX() / 16)] == 212) {
					joueur.setLayoutY(joueur.getLayoutY() + 16);
					joueur.setImage(new Image("/ressources/joueur_B.png"));
				}
			}
			if ((event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.Q)) {
				if (((int) (joueur.getLayoutX() / 16)) > 1 && cterre[(int) (joueur.getLayoutY() / 16)][((int) (joueur.getLayoutX() / 16)) - 1] == 212 || 
						(cterre[((int) joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16) - 1] == 27)) {
					joueur.setLayoutX(joueur.getLayoutX() - 16);
					joueur.setImage(new Image("/ressources/joueur_G.png"));
				}
			}
			if ((event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D)) {
				if (((int) (joueur.getLayoutX() / 16)) + 1 < 43 && cterre[(int) (joueur.getLayoutY() / 16)][((int) (joueur.getLayoutX() / 16)) + 1] == 212) {
					joueur.setLayoutX(joueur.getLayoutX() + 16);
					joueur.setImage(new Image("/ressources/joueur_D.png"));
				}
			}

			if (cterre[((int) joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16)] == 27) {
				changementmap1 = false;
				changementmap2 = true;
				changementmap3 = false;
				changementmap4 = false;
				changementmap5 = false;

				tilePaneFX.getChildren().remove(imwcterre);
				tilePaneFX.getChildren().add(imwplaine);

				joueur.setLayoutX(960);
				joueur.setLayoutY(304);
				joueur.setImage(new Image("/ressources/joueur_G.png"));
			}

			System.out.println("Identifiant de case (6) : " + cterre[(int) (joueur.getLayoutY() / 16)][(int) (joueur.getLayoutX() / 16)] + 
					" | Coordonnées :  " + (int) joueur.getLayoutX() / 16 + " (Hauteur, Largeur) " + (int) joueur.getLayoutY() / 16);
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tilePaneFX.getChildren().add(imw);

		System.out.println(changementmap1);
		System.out.println(changementmap2);
		System.out.println(changementmap3);
		System.out.println(changementmap4);
		System.out.println(changementmap5);

		joueur.setImage(new Image("/ressources/joueur_B.png"));
		joueur.setLayoutX(220);
		joueur.setLayoutY(176);

		initAnimation();
		gameLoop.play();

		joueur.setFocusTraversable(true);
		maps1();
		maps2();

	}

	public void maps1() {
		map = new int[][] {
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, // 0
			{-1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1}, // 1
			{-1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1}, // 2
			{-1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1}, // 3
			{-1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1}, // 4
			{-1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1}, // 5
			{-1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1}, // 6
			{-1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1}, // 7
			{-1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, -1, -1, 10, 10, -1, -1}, // 8
			{-1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, -1, -1, 10, 10, 10, -1, 10, 10, -1, -1}, // 9
			{-1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, 10, -1, 10, 10, -1, -1}, // 10
			{-1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, 10, -1, 10, 10, -1, -1}, // 11
			{10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, 10, -1, 10, 10, -1, -1}, // 12
			{10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, 10, -1, 10, 10, -1, -1}, // 13
			{10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, 10, -1, 10, 10, -1, -1}, // 14
			{10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, 10, -1, 10, 10, -1, -1}, // 15
			{10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, -1, -1, -1, -1, 10, -1, 10, 10, -1, -1}, // 16
			{10, 10, -1, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, 10, 10, 10, -1, 10, 10, -1, -1}, // 17
			{10, 10, -1, 10, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, -1, -1, 10, 10, -1, -1}, // 18
			{10, 10, -1, 10, -1, -1, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1}, // 19
			{10, 10, -1, 10, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1}, // 20
			{10, 10, -1, 10, -1, -1, -1, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, -1, -1}, // 21
			{10, 10, -1, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1}, // 22
			{10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1}, // 23
			{10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, -1, -1}, // 24
			{10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1}, // 25
			{10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1}, // 26
			{10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, -1, -1}, // 27
			{10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1}, // 28
			{10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1}, // 29
			{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 20, 20, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1}, // 30
			{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1}  // 31
			//0  1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26  27  28  29  30  31
		};

		mapvillage = new int[][] {
			{-1, 10, 10, 10, 48, 10, 10, 10, 48, 10, 10, 10, 48, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1}, //0
			{-1, 70, 71, 72, 73, 70, 71, 72, 73, 70, 71, 72, 73, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1}, //1
			{-1, 10, 10, 97, 98, 10, 10, 97, 98, 10, 10, 97, 98, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1}, //2
			{-1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1}, //3
			{-1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 83, 84, 85, 86, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1}, //4
			{-1, 10, 10, 10, 48, 10, 10, 10, 48, 10, 10, 10, 48, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, 58, 59, 60, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1}, //5
			{-1, 70, 71, 72, 73, 70, 71, 72, 73, 70, 71, 72, 73, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, 83, 84, 85, 86, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //6
			{-1, 10, 10, 97, 98, 10, 10, 97, 98, 10, 10, 97, 98, 10, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //7
			{-1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //8
			{-1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //9
			{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 20, -1}, //10
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 20, -1}, //11
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 20, -1}, //12
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //13
			{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //14
			{-1, 10, 10, 10, 48, 10, 10, 10, 48, 10, 10, 10, 48, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //15
			{-1, 70, 71, 72, 73, 70, 71, 72, 73, 70, 71, 72, 73, 10, 10, 26, 27, 28, 29, 30, 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //16
			{-1, 10, 10, 97, 98, 10, 10, 97, 98, 10, 10, 97, 98, 10, 10, 51, 52, 53, 54, 55, 56, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //17
			{-1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 76, 77, 78, 79, 80, 81, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1}, //18
			{-1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 63, 64, 65, 66, 67, 68, -1, -1, -1, -1}, //19
			{-1, 10, 10, 10, 48, 10, 10, 10, 48, 10, 10, 10, 48, 10, 10, 10, 21, 21, 21, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 88, 89, 90, 91, 92, 93, -1, -1, -1, -1}, //20
			{-1, 70, 71, 72, 73, 70, 71, 72, 73, 70, 71, 72, 73, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1}, //21
			{-1, 10, 10, 97, 98, 10, 10, 97, 98, 10, 10, 97, 98, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1}, //22
			{-1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //23
			{-1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //24
			{-1, 10, 10, 10, 48, 10, 10, 10, 48, 10, 10, 10, 48, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //25
			{-1, 70, 71, 72, 73, 70, 71, 72, 73, 70, 71, 72, 73, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //26
			{-1, 10, 10, 97, 98, 10, 10, 97, 98, 10, 10, 97, 98, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //27
			{-1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //28
			{-1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //29
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}  //30
			//0  1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26  27  28  29  30  31  32  33  34  35  36  37  38  39  40  41  42  43
		};

		plaine = new int[][] {
			{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 24, 24, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, -1, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, -1, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{10, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, -1, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, 10, 10},
			{10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, -1, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10},
			{10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, -1, -1, -1, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10},
			{10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, -1, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10},
			{10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, -1, -1, 10, -1, 10, -1, 10, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10},
			{10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, -1, -1, -1, -1, 10, -1, 10, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, 10, 10},
			{10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{-1, -1, -1, -1, 10, 10, 10, 10, -1, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, 10, 10},
			{-1, -1, -1, -1, 10, 10, 10, 10, -1, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, -1, 10, 10},
			{-1, -1, -1, -1, 10, 10, 10, 10, 10, -1, 10, 10, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, 10, 10},
			{-1, -1, -1, -1, 10, 10, 10, 10, 10, 10, -1, 10, -1, -1, -1, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{-1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 26, 10},
			{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 26, 10},
			{20, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 26, 10},
			{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{-1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{-1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{-1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, 10, 10, 10, 10},
			{-1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, 10, 10, 10, 10},
			{-1, 20, 20, -1, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{10, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, 10, 10},
			{10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10},
			{10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10},
			{10, 10, 10, 10, -1, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10},
			{10, 10, 10, -1, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10},
			{10, 10, -1, 10, 10, 10, 10, 10, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, 10, 10},
			{10, -1, 10, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{10, -1, 10, 10, 10, 10, 10, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{-1, -1, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{-1, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{-1, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{-1, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{-1, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{-1, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{-1, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{-1, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{-1, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, 10, 10, 10, -1, 10, 10, 22, 22, 10, -1, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			{-1, -1, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, -1, 10, 10, 10, 10, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}
			//0  1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26  27  28  29  30  31  32  33  34  35  36  37  38  39  40  41  42  43  44  45  46  47  48  49  50  51  52  53  54  55  56  57  58  59  60  61  62
		};

	}

	public void maps2() {
		cfeu = new int[][] {
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 23, 23, 23, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //0
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //1
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //2
			{-1, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //3
			{-1, 10, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //4
			{-1, 10, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //5
			{-1, 10, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //6
			{-1, 10, 10, 10, -1, -1, -1, -1, 30, 10, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //7
			{-1, -1, -1, -1, -1, -1, -1, -1, 30, 30, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //8
			{-1, -1, -1, -1, -1, -1, -1, -1, 30, 30, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //9
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //10
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //11
			{-1, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //12
			{-1, 10, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //13
			{-1, 10, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //14
			{-1, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //15
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //16
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //17
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, 30, 30, 30, 30, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //18
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //19
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 30, 30, 30, 30, 20, 20, 30, 30, 30, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //21
			{-1, -1, 30, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //22
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //23
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 30, 30, 30, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //24
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //25
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //26
			{-1, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //27
			{-1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //28
			{-1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //29
			{-1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10, 10, 10, 10, -1, -1, -1, -1, -1, -1, 30, 30, 30, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //30
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //31
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //32 
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //33
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //34
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //35
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //36
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //37
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //38
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //39
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //40
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}  //41
			//0  1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26  27  28  29  30  31  32  33  34  35  36  37  38  39  40  41  42  43  44  45  46  47  48  49
		};

		cglace = new int[][]{
			{926, 926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1495, 1495, 1495, 1495, 1495, 1495, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1495, 1495, 1495, 1495, 1495, 1495, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1495, 1495, 1495, 1495, 1495, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1495, 1495, 1495, 1495, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1870, 1870, 926, 1747, 1746, 1747, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1870, 926, 1747, 1747, 1747, 1747, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 1747, 1747, 1747, 1747, 1747, 1742, 1490, 1491, 1742, 1742, 1742, 1742, 1742, 1742, 1490, 1491, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1747, 1747, 1747, 1747, 1747, 1747, 1742, 1553, 1554, 1743, 1744, 1745, 1746, 1747, 1742, 1553, 1554, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1747, 1747, 1747, 1747, 1747, 1747, 1742, 1616, 1617, 1806, 1807, 1808, 1809, 1810, 1742, 1616, 1617, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1747, 1747, 1747, 1747, 1747, 1747, 1742, 1679, 1680, 1869, 1870, 926, 1872, 1873, 1742, 1679, 1680, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1747, 1747, 1747, 1747, 1747, 1747, 1490, 1491, 1931, 1932, 1933, 1934, 1935, 1936, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1747, 1747, 1747, 1747, 1747, 1747, 1553, 1554, 1994, 1995, 1996, 1997, 1998, 1999, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1747, 1747, 1747, 1747, 1747, 1747, 1616, 1617, 2057, 2058, 2059, 2060, 2061, 2062, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1747, 1747, 1747, 1745, 1747, 1747, 1679, 1680, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, -1, -1, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1747, 1747, 1747, 1747, 1747, 1747, 1742, 1742, 1742, 1742, 1742, 1742, 1743, 1744, 1745, 1746, 1747, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1747, 1747, 1747, 1747, 1747, 1747, 1742, 1742, 1742, 1742, 1742, 1805, 1806, 1807, 1808, 1809, 1810, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1747, 1747, 1747, 1747, 1747, 1747, 1742, 1742, 1742, 1742, 1742, 1868, 1869, 1870, 926, 1872, 1873, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1747, 1747, 1747, 1747, 1747, 1747, 1742, 1742, 1742, 1742, 1742, 1931, 1932, 1933, 1934, 1935, 1936, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{2057, 2058, 1747, 1747, 1747, 2062, 1742, 1742, 1742, 1742, 1742, 1994, 1995, 1996, 1997, 1998, 1999, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1742, 1742, 1742, 1742, 1490, 1491, 1742, 1742, 1742, 1742, 1742, 2057, 2058, 2059, 2060, 2061, 2062, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1742, 1747, 1747, 1747, 1553, 1554, 1742, 1742, 1742, 1743, 1744, 1745, 1746, 1747, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1747, 1747, 1747, 1747, 1616, 1617, 1742, 1742, 1805, 1806, 1807, 1808, 1809, 1810, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1747, 1747, 1747, 1747, 1679, 1680, 1742, 1742, 1868, 1869, 1870, 926, 1872, 1873, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1747, 1747, 1747, 1747, 1747, 1747, 1742, 1742, 1931, 1932, 1933, 1934, 1935, 1936, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1495, 1495, 1747, 1747, 1747, 1742, 1742, 1742, 1742, 1995, 1996, 1997, 1998, 1999, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 25, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1495, 1495, 1495, 1742, 1742, 1742, 1742, 1742, 1742, 2058, 2059, 2060, 2061, 2062, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{1495, 1495, 1495, 1495, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926},
			{926, 926, 926, 926, 926, 926, 926, 1742, 1742, 1742, 1742, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926, 926}
		};

		cterre = new int[][]{
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 133, 89, 212, 133, 89, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 27, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 27, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 133, 89, 212, 133, 89, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 133, 89, 212, 133, 89, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 133, 89, 212, 133, 89, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212},
			{212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212}
		};


	}

	private void initAnimation() {
		gameLoop = new Timeline();
		temps = 0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);

		if (anim) {
			KeyFrame animup = new KeyFrame(
					Duration.seconds(60), (deplacement -> {
						if (temps == 1) {
							joueur.setImage(new Image("/ressources/joueur_H.png"));
						} else if (temps == 2) {
							joueur.setImage(new Image("/ressources/joueur_H.png"));
						} else if (temps == 3) {
							joueur.setImage(new Image("/ressources/joueur_H.png"));
						} else if (temps == 4) {
							joueur.setImage(new Image("/ressources/joueur_H.png"));
						}
						temps++;
					})
					);
			gameLoop.getKeyFrames().add(animup);
		}
	}
}