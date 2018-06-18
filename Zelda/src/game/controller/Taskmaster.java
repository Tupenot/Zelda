package game.controller;

import game.model.LeaveGame;
import game.model.character.Bowman;
import game.model.character.Character;
import game.model.character.Link;
import game.model.maps.Field;
import game.model.teardrop.Teardrop;
import game.vue.boss.ViewFireBoss;
import game.vue.enemy.ViewEnemy;
import game.vue.enemy.ViewNormalBokoblinSword;
import game.vue.maps.ViewMap;
import game.vue.player.ViewPlayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class Taskmaster implements Initializable {
    private Scene scene;
    private Teardrop sword = new Teardrop("sword", 5);

    private Character link = new Link("", 3, 3, sword);
    private Character bokoblinsword = new Bowman();

    // Leave

    LeaveGame leavegame = new LeaveGame();

    // Views
    private ViewMap mv = new ViewMap();
    private ViewPlayer pv = new ViewPlayer();
    private ViewEnemy ve = new ViewNormalBokoblinSword();
    private ViewFireBoss befire = new ViewFireBoss();

    // Game map
    private int[][] mapbuff;

    // Fields
    private Field house = new Field("src/game/model/maps/other/housemap.csv");
    private Field town = new Field("src/game/model/maps/other/town.csv");
    private Field plain = new Field("src/game/model/maps/other/plain.csv");
    private Field fireway = new Field("src/game/model/maps/fire/fireway.csv");
    private Field fireroom = new Field("src/game/model/maps/fire/fireroom.csv");
    private Field fireboss = new Field("src/game/model/maps/fire/fireboss.csv");
    private Field iceway = new Field("src/game/model/maps/ice/iceway.csv");
    private Field iceroom = new Field("src/game/model/maps/ice/iceroom.csv");
    private Field iceboss = new Field("src/game/model/maps/ice/iceboss.csv");
    private Field dirtway = new Field("src/game/model/maps/dirt/dirtway.csv");
    private Field dirtroom = new Field("src/game/model/maps/dirt/dirtroom.csv");
    private Field dirtboss = new Field("src/game/model/maps/dirt/dirtboss.csv");

    @FXML
    private TilePane tilePaneFX;
    @FXML
    private BorderPane borderpane;
    @FXML
    private ImageView player;
    @FXML
    private ImageView bokoblin;
    @FXML
    private HBox heart;
    @FXML
    private ImageView heartview;
   
    // Déplacement des personnages


    public void movement(KeyEvent event) {

        // ------------------------------ DEPLACEMENTS ------------------------------


        // Liste de tous les identifiants présents dans le jeu + fonctionnalités

        // -1 -> Tous les déplacements
        // 1  -> Point de changement de map : Maison -> Village
        // 2  -> Point de changement de map : Village -> Maison
        // 3  -> Point de changement de map : Village -> Plaine
        // 4  -> Point de changement de map : Plaine -> Village
        // 5  -> Point de changement de map : Plaine -> Chemin de Glace
        // 6  -> Point de changement de map : Plaine -> Chemin de Terre
        // 7  -> Point de changement de map : Plaine -> Chemin de Feu
        // 8  -> Point de changement de map : Chemin de Glace -> Plaine
        // 9  -> Point de changement de map : Chemin de Terre -> Plaine
        // 10 -> Point de changement de map : Chemin de Feu -> Plaine
        // 11 -> Point de changement de map : Chemin de Glace -> Donjon de Glace
        // 12 -> Point de changement de map : Chemin de Terre -> Donjon de Terre
        // 13 -> Point de changement de map : Chemin de Feu -> Donjon de Feu
        // 14 -> Point de changement de map : Donjon de Glace -> Boss de Glace
        // 15 -> Point de changement de map : Donjon de Terre -> Boss de Terre
        // 16 -> Point de changement de map : Donjon de Feu -> Boss de feu
        // 79 -> Retour au point de départ (pour le donjon de glace) : Si le joueur passe dessus, il retourne au point de départ du donjon
        // 89 -> Chute coûtant de la vie : Si le joueur passe dessus, il revient au point de départ de l'endroit dans lequel il était et perd de la vie
        // 99 -> Collisions (endroits auxquels il n'a pas le droit d'accéder)

        System.err.println("[!] Identifier movement (x/16) : " + (int) player.getLayoutX() / 16);
        System.err.println("[!] Identifier movement (x) : " + (int) player.getLayoutX());
        System.err.println("[!] Identifier movement (y/16) : " + (int) player.getLayoutY() / 16);
        System.err.println("[!] Identifier movement (y) : " + (int) player.getLayoutY());

        // Leave game checker

        leavegame.checker(event);

        // Déplacement vers le haut

        if ((event.getCode() == KeyCode.UP || event.getCode() == KeyCode.Z)) {
            if (mapbuff[((int) player.getLayoutY() / 16) - 1][(int) (player.getLayoutX() / 16)] == -1
                    || (mapbuff[((int) player.getLayoutY() / 16) - 1][(int) (player.getLayoutX() / 16)] == 2) // Village -> Maison
                    || (mapbuff[((int) player.getLayoutY() / 16) - 1][(int) (player.getLayoutX() / 16)] == 5) // Plaine -> Chemin de Glace
                    || (mapbuff[((int) player.getLayoutY() / 16) - 1][(int) (player.getLayoutX() / 16)] == 10) // Chemin de Feu -> Plaine
                    || (mapbuff[((int) player.getLayoutY() / 16) - 1][(int) (player.getLayoutX() / 16)] == 11) // Chemin de Glace -> Donjon de Glace
                    || (mapbuff[((int) player.getLayoutY() / 16) - 1][(int) (player.getLayoutX() / 16)] == 13) // Chemin de Feu -> Donjon de Feu
                    || (mapbuff[((int) player.getLayoutY() / 16) - 1][(int) (player.getLayoutX() / 16)] == 16) // Donjon de Feu -> Boss de feu
                    || (mapbuff[((int) player.getLayoutY() / 16) - 1][(int) (player.getLayoutX() / 16)] == 79)
                    || (mapbuff[((int) player.getLayoutY() / 16) - 1][(int) (player.getLayoutX() / 16)] == 89)) {
                player.setLayoutY(player.getLayoutY() - 16);
                bokoblin.setLayoutY(bokoblin.getLayoutY() - 16);
                player.setImage(pv.up());
            } else {
                player.setImage(pv.down());
            }

            System.out.println();
        }

        // Déplacement vers le bas

        if ((event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S)) {
            if (mapbuff[((int) player.getLayoutY() / 16) + 1][(int) (player.getLayoutX() / 16)] == -1
                    || (mapbuff[((int) player.getLayoutY() / 16) + 1][(int) (player.getLayoutX() / 16)] == 1)  // Maison -> Village
                    || (mapbuff[((int) player.getLayoutY() / 16) + 1][(int) (player.getLayoutX() / 16)] == 7) // Plaine -> Chemin de Feu
                    || (mapbuff[((int) player.getLayoutY() / 16) + 1][(int) (player.getLayoutX() / 16)] == 8) // Chemin de Glace -> Plaine
                    || (mapbuff[((int) player.getLayoutY() / 16) + 1][(int) (player.getLayoutX() / 16)] == 15) // Donjon de Terre -> Boss de Terre
                    || (mapbuff[((int) player.getLayoutY() / 16) + 1][(int) (player.getLayoutX() / 16)] == 79)
                    || (mapbuff[((int) player.getLayoutY() / 16) + 1][(int) (player.getLayoutX() / 16)] == 89)) {
                player.setLayoutY(player.getLayoutY() + 16);
                bokoblin.setLayoutY(bokoblin.getLayoutY() + 16);
                player.setImage(pv.down());
            } else {
                player.setImage(pv.up());
            }
        }

        // Déplacement vers la gauche

        if ((event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.Q)) {
            if (mapbuff[(int) (player.getLayoutY() / 16)][((int) (player.getLayoutX() / 16)) - 1] == -1
                    || (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16) - 1] == 4) // Plaine -> Village
                    || (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16) - 1] == 9) // Chemin de Terre -> Plaine
                    || (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16) - 1] == 79)
                    || (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16) - 1] == 89)) {
                player.setLayoutX(player.getLayoutX() - 16);
                bokoblin.setLayoutX(bokoblin.getLayoutX() - 16);
                player.setImage(pv.left());
            } else {
                player.setImage(pv.right());
            }
        }

        // Déplacement vers la droite

        if ((event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D)) {
            if (mapbuff[(int) (player.getLayoutY() / 16)][((int) (player.getLayoutX() / 16)) + 1] == -1
                    || (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16) + 1] == 3) // Village -> Plaine
                    || (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16) + 1] == 6) // Plaine -> Chemin de Terre
                    || (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16) + 1] == 12) // Chemin de Terre -> Donjon de Terre
                    || (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16) + 1] == 14) // Donjon de Glace -> Boss de Glace
                    || (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16) + 1] == 79)
                    || (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16) + 1] == 89)) {
                player.setLayoutX(player.getLayoutX() + 16);
                bokoblin.setLayoutX(bokoblin.getLayoutX() + 16);
                player.setImage(pv.right());
            } else {
                player.setImage(pv.left());
            }
        }
        if ((event.getCode() == KeyCode.H)) {
            if (!(heartview.isVisible())) {
                heartview.setVisible(true);
            } else {
                heartview.setVisible(false);
            }

        }


        if ((event.getCode() == KeyCode.ENTER)) {
            link.attack(bokoblinsword, sword);
            System.out.print("\n" + bokoblinsword.getHeart());
            if (!(bokoblinsword.isAlive())) {
                bokoblin.setImage(ve.isDead());
            }
        }


        if ((!(event.getCode() == KeyCode.Y)) && ((!(event.getCode() == KeyCode.ESCAPE))) && ((!(event.getCode() == KeyCode.N)))
                && ((!(event.getCode() == KeyCode.ENTER)))) {
            System.err.println("[!] Identifier of movement : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] " + "\n-----------------------------------");
        }

        // Identifiant 79 : Joueur retourne au début du donjon

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 79) {
        	this.changeScene(mv.iceroom(), mv.iceroom(), 82, 720, pv.up(), iceroom, 0.8, 2);
        }

        // Identifiant 89 : Joueur retourne au centre de la plaine

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 89) {
            tilePaneFX.getChildren().remove(mv.dirtroom());
            tilePaneFX.getChildren().remove(mv.dirtboss());
            tilePaneFX.getChildren().remove(mv.iceroom());
            tilePaneFX.getChildren().remove(mv.iceboss());
            tilePaneFX.getChildren().remove(mv.fireway());
            tilePaneFX.getChildren().remove(mv.fireroom());
            tilePaneFX.getChildren().remove(mv.fireboss());
            tilePaneFX.getChildren().add(mv.plain());

            player.setLayoutX(448);
            player.setLayoutY(322);
            player.setImage(pv.down());

            plain.initMap();
            copyField(plain.getMap());

            for (int i = 0; i < plain.getMap().length; i++) {
                for (int j = 0; j < plain.getMap()[i].length; j++) {
                    System.out.print(plain.getMap()[i][j] + ",");
                }
                System.out.println();
            }
               
        }        
        
        // ------------------------------ CHANGEMENT DE MAP ------------------------------

        // Changement de map : Maison -> Village
        // OK

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 1) {
            this.changeScene(mv.house(), mv.town(), 272, 336, pv.down(), town, 0, 0);
        }

        // Changement de map : Village -> Maison
        // OK

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 2) {
            this.changeScene(mv.town(), mv.house(), 240, 464, pv.up(), house, -1, 0);
        }

        // Changement de map : Village -> Plaine
        // OK

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 3) {
            this.changeScene(mv.town(), mv.plain(), 16, 322, pv.right(), plain, 1, 0.5);
        }

        // Changement de map : Plaine -> Village
        // OK

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 4) {
            this.changeScene(mv.plain(), mv.town(), 656, 176, pv.left(), town, 0, 0);
        }

        // Changement de map : Plaine -> Chemin de Glace
        // OK
        
        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 5) {
            this.changeScene(mv.plain(), mv.iceway(), 400, 560, pv.up(), iceway, 1, 1.5);
        }

        // Changement de map : Plaine -> Chemin de Terre
        // OK
        
        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 6) {
            this.changeScene(mv.plain(), mv.dirtway(), 32, 176, pv.right(), dirtway, -0.5, 0.8);
        }

        // Changement de map : Plaine -> Chemin de Feu
        // OK
        
        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 7) {
            this.changeScene(mv.plain(), mv.fireway(), 272, 16, pv.down(), fireway, 0.5, 1.5);
        }

        // Changement de map : Chemin de Feu -> Plaine
        // OK

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 10) {
            this.changeScene(mv.fireway(), mv.plain(), 452, 736, pv.up(), plain, 1, 0.5);
        }

        // Changement de map : Chemin de Terre -> Plaine
        // OK
        
        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 9) {
            this.changeScene(mv.dirtway(), mv.plain(), 960, 304, pv.left(), plain, 1, 0.5);
        }

        // Changement de map : Chemin de Glace -> Plaine
        // OK
        
        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 8) {
          this.changeScene(mv.iceway(), mv.plain(), 464, 16, pv.down(), plain, 1, 0.5);
        }

        // Changement de map : Chemin de Glace -> Donjon de Glace
        // OK
        
        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 11) {
           this.changeScene(mv.iceway(), mv.iceroom(), 82, 720, pv.up(), iceroom, 0.8, 2);
        }

        // Changement de map : Donjon de Glace -> Boss De Glace
        // NOT OK : Spawn + Collisions
        
        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 14) {
           this.changeScene(mv.iceroom(), mv.iceboss(), 344, 536, pv.up(), iceboss, 0, 1);
        }

        // Changement de map : Chemin de Feu -> Donjon de Feu
        // OK

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 13) {
            this.changeScene(mv.fireway(), mv.fireroom(), 368, 640, pv.down(), fireroom, -8, -18);
        }

        // Changement de map : Donjon de Feu -> Boss de Feu

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 16) {
            this.changeScene(mv.fireroom(), mv.fireboss(), 608, 384, pv.up(), fireboss, 0, 1);
          //  firebossview.setImage(befire.spawn());
            
        }

        // Changement de map : Chemin de Terre -> Donjon de Terre
        
        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 12) {
        	this.changeScene(mv.dirtway(), mv.dirtroom(), 32, 112, pv.right(), dirtroom, 2, 2);
        }

        // Changement de map : Donjon de Terre -> Boss de Terre
        
        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 15) {
            this.changeScene(mv.dirtroom(), mv.dirtboss(), 112, 96, pv.down(), dirtboss, 4, 4);
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        house.initMap();
        mapbuff = house.getMap();
        tilePaneFX.getChildren().add(mv.house());
        player.setImage(pv.down());
        player.setLayoutX(220);
        player.setLayoutY(176);
        player.setFocusTraversable(true);
        heartview.setImage(pv.heart3());
    }

    public void copyField(int[][] source) {
        mapbuff = new int[source.length][source[0].length];
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[i].length; j++) {
                mapbuff[i][j] = source[i][j];
            }
        }
        System.out.println("Length of the loaded map : " + source.length + "\nHeight of the loaded map : " + source[0].length);
    }

    public void changeScene(ImageView old, ImageView newImage, int startX, int startY, Image direction, Field field, double fixwidth, double fixheight) {
        System.err.println("[!] Warning, you have changed map [!]" + "\n[!] Loading town to plain [!]" + "\n[!] Identifier of the map change box : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] ");
        tilePaneFX.getChildren().remove(old);
        tilePaneFX.getChildren().add(newImage);
        player.setLayoutX(startX);
        player.setLayoutY(startY);
        player.setImage(direction);
        field.initMap();
        copyField(field.getMap());
        for (int i = 0; i < field.getMap().length; i++) {
            for (int j = 0; j < field.getMap()[i].length; j++) {
                System.out.print(field.getMap()[i][j] + ",");
            }
            System.out.println();
        }
        this.player.getScene().getWindow().setWidth(16 * (field.getMap()[0].length + fixwidth));
        this.player.getScene().getWindow().setHeight(16 * (field.getMap().length + fixheight));
    }
}