package game.controller;

import game.model.character.Bowman;
import game.model.character.Link;
import game.model.character.Character;
import game.model.maps.Field;
import game.model.teardrop.Sword;
import game.model.teardrop.Teardrop;
import game.vue.enemy.ViewEnemy;
import game.vue.enemy.ViewNormalBokoblinSword;
import game.vue.maps.ViewMap;
import game.vue.player.ViewPlayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class Taskmaster implements Initializable {

    private Teardrop sword = new Teardrop ("sword",5);

    private Character link = new Link("",3,3,sword);
    private Character bokoblinsword = new Bowman ();

    // Leave condition
    private boolean leavegameescape = false;
    private boolean leavegamey = false;

    // Views
    private ViewMap mv = new ViewMap();
    private ViewPlayer pv = new ViewPlayer();
    private ViewEnemy ve = new ViewNormalBokoblinSword();

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
    private Field dirtroom = new Field("src/game/model/maps /dirt/dirtroom.csv");
    private Field dirtboss = new Field("src/game/model/maps/dirt/dirtboss.csv");

    // Characters & Enemys


    @FXML
    private TilePane tilePaneFX;
    @FXML
    private ImageView player;
    @FXML
    private ImageView bokoblin;

    // Déplacement des personnages

    public void movement(KeyEvent event) {

        // ------------------------------ DEPLACEMENTS ------------------------------

        // Déplacement vers le haut
    	
        System.err.println("[!] Identifier movement (x/16) : " + (int) player.getLayoutX() / 16);
        System.err.println("[!] Identifier movement (x) : " + (int) player.getLayoutX());
        System.err.println("[!] Identifier movement (y/16) : " + (int) player.getLayoutY() / 16);
        System.err.println("[!] Identifier movement (y) : " + (int) player.getLayoutY());

        

        if ((event.getCode() == KeyCode.UP || event.getCode() == KeyCode.Z)) {
            if (mapbuff[((int) player.getLayoutY() / 16) - 1][(int) (player.getLayoutX() / 16)] == -1
                    || (mapbuff[((int) player.getLayoutY() / 16) - 1][(int) (player.getLayoutX() / 16)] == 2) // Village -> Maison
                    || (mapbuff[((int) player.getLayoutY() / 16) - 1][(int) (player.getLayoutX() / 16)] == 5) // Plaine -> Chemin de Glace
                    || (mapbuff[((int) player.getLayoutY() / 16) - 1][(int) (player.getLayoutX() / 16)] == 10) // Chemin de Feu -> Plaine
                    || (mapbuff[((int) player.getLayoutY() / 16) - 1][(int) (player.getLayoutX() / 16)] == 11) // Chemin de Glace -> Donjon de Glace
                    || (mapbuff[((int) player.getLayoutY() / 16) - 1][(int) (player.getLayoutX() / 16)] == 13) // Chemin de Feu -> Donjon de Feu
                    || (mapbuff[((int) player.getLayoutY() / 16) - 1][(int) (player.getLayoutX() / 16)] == 16)) {
            	
                player.setLayoutY(player.getLayoutY() - 16);
                bokoblin.setLayoutY(bokoblin.getLayoutY()- 16);
                player.setImage(pv.up());
            }
            else {
                player.setImage(pv.down());
            }
            System.err.println("[!] Identifier of movement : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] " + "\n-----------------------------------");
        }

        // Déplacement vers le bas

        if ((event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S)) {
            if (mapbuff[((int) player.getLayoutY() / 16) + 1][(int) (player.getLayoutX() / 16)] == -1
                    || (mapbuff[((int) player.getLayoutY() / 16) + 1][(int) (player.getLayoutX() / 16)] == 1)  // Maison -> Village
                    || (mapbuff[((int) player.getLayoutY() / 16) + 1][(int) (player.getLayoutX() / 16)] == 7) // Plaine -> Chemin de Feu
                    || (mapbuff[((int) player.getLayoutY() / 16) + 1][(int) (player.getLayoutX() /16)] == 8) // Chemin de Glace -> Plaine
                    || (mapbuff[((int) player.getLayoutY() / 16) + 1][(int) (player.getLayoutX() / 16)] == 15)) { // Donjon de Terre -> Boss de Terre
                player.setLayoutY(player.getLayoutY() + 16);
                bokoblin.setLayoutY(bokoblin.getLayoutY() + 16);
                player.setImage(pv.down());
            }
            else {
                player.setImage(pv.up());
            }
            System.err.println("[!] Identifier of movement : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] " + "\n-----------------------------------");

        }

        // Déplacement vers la gauche

        if ((event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.Q)) {
            if (mapbuff[(int) (player.getLayoutY() / 16)][((int) (player.getLayoutX() / 16)) - 1] == -1
                    || (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16) - 1] == 4) // Plaine -> Village
                    || (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16) -1] == 9)) { // Chemin de Terre -> Plaine
                player.setLayoutX(player.getLayoutX() - 16);
                bokoblin.setLayoutX(bokoblin.getLayoutX() - 16);
                player.setImage(pv.left());
            }
            else {
                player.setImage(pv.right());
            }
            System.err.println("[!] Identifier of movement : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] " + "\n-----------------------------------");

        }

        // Déplacement vers la droite

        if ((event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D)) {
            if (mapbuff[(int) (player.getLayoutY() / 16)][((int) (player.getLayoutX() / 16)) + 1] == -1
                    || (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16) + 1] == 3) // Village -> Plaine
                    || (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16) + 1] == 6) // Plaine -> Chemin de Terre
                    || (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16) + 1] == 12) // Chemin de Terre -> Donjon de Terre
                    || (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16) + 1] == 14)) { // Donjon de Glace -> Boss de Glace
                player.setLayoutX(player.getLayoutX() + 16);
                bokoblin.setLayoutX(bokoblin.getLayoutX() + 16);
                player.setImage(pv.right());
            }
            else {
                player.setImage(pv.left());
            }
            System.err.println("[!] Identifier of movement : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] " + "\n-----------------------------------");

        }


        if ((event.getCode() == KeyCode.ENTER)) {
            link.attack(bokoblinsword,sword);
            System.out.print("\n" + bokoblinsword.getHeart());
            if (!(bokoblinsword.isAlive())) {
                bokoblin.setImage(ve.isDead());
            }
        }


       /* if ((!(event.getCode() == KeyCode.Y)) && ((!(event.getCode() == KeyCode.ESCAPE))) && ((!(event.getCode() == KeyCode.N)))
           && ((!(event.getCode() == KeyCode.ENTER)))) {
            System.err.println("[!] Identifier of movement : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] " + "\n-----------------------------------");
        } */


        // ------------------------------ CHANGEMENT DE MAP ------------------------------

        //Changement de map : Maison -> Village

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 1) {
            System.err.println("[!] Warning, you have changed map [!]" + "\n[!] Loading house to town [!]" + "\n[!] Identifier of the map change box : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] ");

            tilePaneFX.getChildren().remove(mv.house());
            tilePaneFX.getChildren().add(mv.town());
            player.setLayoutX(272);
            player.setLayoutY(336);
            player.setImage(pv.down());

            town.initMap();
            copyField(town.getMap());

            for (int i = 0; i < town.getMap().length; i++) {
                for (int j = 0; j < town.getMap()[i].length; j++) {
                    System.out.print(town.getMap()[i][j] + ",");
                }
                System.out.println();
            }


        }

        // Changement de map : Village -> Maison

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 2) {
            System.err.println("[!] Warning, you have changed map [!]" + "\n[!] Loading house to town [!]" + "\n[!] Identifier of the map change box : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] ");
            tilePaneFX.getChildren().remove(mv.town());
            tilePaneFX.getChildren().add(mv.house());
            player.setLayoutX(240);
            player.setLayoutY(464);
            player.setImage(pv.up());

            house.initMap();
            copyField(house.getMap());
            for (int i = 0; i < house.getMap().length; i++) {
                for (int j = 0; j < house.getMap()[i].length; j++) {
                    System.out.print(house.getMap()[i][j] + ",");
                }
                System.out.println();
            }


        }

        // Changement de map : Village -> Plaine

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 3) {
            System.err.println("[!] Warning, you have changed map [!]" + "\n[!] Loading town to plain [!]" + "\n[!] Identifier of the map change box : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] ");

            tilePaneFX.getChildren().remove(mv.town());
            tilePaneFX.getChildren().add(mv.plain());
            player.setLayoutX(16);
            player.setLayoutY(322);
            player.setImage(pv.right());
            bokoblin.setImage(ve.up());
            bokoblin.setLayoutX(156);
            bokoblin.setLayoutY(322);

            plain.initMap();
            copyField(plain.getMap());

            for (int i = 0; i < plain.getMap().length; i++) {
                for (int j = 0; j < plain.getMap()[i].length; j++) {
                    System.out.print(plain.getMap()[i][j] + ",");
                }
                System.out.println();
            }


        }

        // Changement de map : Plaine -> Village

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 4) {
            System.err.println("[!] Warning, you have changed map [!]" + "\n[!] Loading plain to town [!]" + "\n[!] Identifier of the map change box : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] ");

            tilePaneFX.getChildren().remove(mv.plain());
            tilePaneFX.getChildren().add(mv.town());
            player.setLayoutX(656);
            player.setLayoutY(176);
            player.setImage(pv.left());

            town.initMap();
            copyField(town.getMap());
            for (int i = 0; i < town.getMap().length; i++) {
                for (int j = 0; j < town.getMap()[i].length; j++) {
                    System.out.print(town.getMap()[i][j] + ",");
                }
                System.out.println();
            }


        }

        // Changement de map : Plaine -> Chemin de Glace

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 5) {
            System.err.println("[!] Warning, you have changed map [!]" + "\n[!] Loading plain to iceway [!]" + "\n[!] Identifier of the map change box : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] ");

            tilePaneFX.getChildren().remove(mv.plain());
            tilePaneFX.getChildren().add(mv.iceway());
            player.setLayoutX(400);
            player.setLayoutY(560);
            player.setImage(pv.up());

            iceway.initMap();
            copyField(iceway.getMap());
            for (int i = 0; i < iceway.getMap().length; i++) {
                for (int j = 0; j < iceway.getMap()[i].length; j++) {
                    System.out.print(iceway.getMap()[i][j] + ",");
                }
                System.out.println();
            }


        }

        // Changement de map : Plaine -> Chemin de Terre

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 6) {
            System.err.println("[!] Warning, you have changed map [!]" + "\n[!] Loading plain to dirtway [!]" + "\n[!] Identifier of the map change box : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] ");

            tilePaneFX.getChildren().remove(mv.plain());
            tilePaneFX.getChildren().add(mv.dirtway());
            player.setLayoutX(32);
            player.setLayoutY(176);
            player.setImage(pv.right());

            dirtway.initMap();
            copyField(dirtway.getMap());
            for (int i = 0; i < dirtway.getMap().length; i++) {
                for (int j = 0; j < dirtway.getMap()[i].length; j++) {
                    System.out.print(dirtway.getMap()[i][j] + ",");
                }
                System.out.println();
            }


        }

        // Changement de map : Plaine -> Chemin de Feu

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 7) {
            System.err.println("[!] Warning, you have changed map [!]" + "\n[!] Loading plain to fireway [!]" + "\n[!] Identifier of the map change box : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] ");

            tilePaneFX.getChildren().remove(mv.plain());
            tilePaneFX.getChildren().add(mv.fireway());
            player.setLayoutX(272);
            player.setLayoutY(16);
            player.setImage(pv.down());

            fireway.initMap();
            copyField(fireway.getMap());
            for (int i = 0; i < fireway.getMap().length; i++) {
                for (int j = 0; j < fireway.getMap()[i].length; j++) {
                    System.out.print(fireway.getMap()[i][j] + ",");
                }
                System.out.println();
            }


        }

        // Changement de map : Chemin de Feu -> Plaine

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 10) {
            System.err.println("[!] Warning, you have changed map [!]" + "\n[!] Loading fireway to plain [!]" + "\n[!] Identifier of the map change box : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] ");

            tilePaneFX.getChildren().remove(mv.fireway());
            tilePaneFX.getChildren().add(mv.plain());
            player.setLayoutX(452);
            player.setLayoutY(736);
            player.setImage(pv.up());

            plain.initMap();
            copyField(plain.getMap());

            for (int i = 0; i < plain.getMap().length; i++) {
                for (int j = 0; j < plain.getMap()[i].length; j++) {
                    System.out.print(plain.getMap()[i][j] + ",");
                }
                System.out.println();
            }
        }

        // Changement de map : Chemin de Terre -> Plaine

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 9) {
            System.err.println("[!] Warning, you have changed map [!]" + "\n[!] Loading dirtway to plain [!]" + "\n[!] Identifier of the map change box : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] ");

            tilePaneFX.getChildren().remove(mv.dirtway());
            tilePaneFX.getChildren().add(mv.plain());
            player.setLayoutX(960);
            player.setLayoutY(304);
            player.setImage(pv.left());

            plain.initMap();
            copyField(plain.getMap());

            for (int i = 0; i < plain.getMap().length; i++) {
                for (int j = 0; j < plain.getMap()[i].length; j++) {
                    System.out.print(plain.getMap()[i][j] + ",");
                }
                System.out.println();
            }
        }

        // Changement de map : Chemin de Glace -> Plaine

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 8) {
            System.err.println("[!] Warning, you have changed map [!]" + "\n[!] Loading iceway to plain [!]" + "\n[!] Identifier of the map change box : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] ");

            tilePaneFX.getChildren().remove(mv.iceway());
            tilePaneFX.getChildren().add(mv.plain());
            player.setLayoutX(464);
            player.setLayoutY(16);
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

        // Changement de map : Chemin de Glace -> Donjon de Glace

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 11) {
            System.err.println("[!] Warning, you have changed map [!]" + "\n[!] Loading iceway to iceroom [!]" + "\n[!] Identifier of the map change box : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] ");

            tilePaneFX.getChildren().remove(mv.iceway());
            tilePaneFX.getChildren().add(mv.iceroom());
            player.setLayoutX(82);
            player.setLayoutY(720);
            player.setImage(pv.up());

            iceroom.initMap();
            copyField(iceroom.getMap());

            for (int i = 0; i < iceroom.getMap().length; i++) {
                for (int j = 0; j < iceroom.getMap()[i].length; j++) {
                    System.out.print(iceroom.getMap()[i][j] + ",");
                }
                System.out.println();
            }
        }

        // Changement de map : Donjon de Glace -> Boss De Glace

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 14) {
            System.err.println("[!] Warning, you have changed map [!]" + "\n[!] Loading iceroom to iceboss [!]" + "\n[!] Identifier of the map change box : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] ");

            tilePaneFX.getChildren().remove(mv.iceroom());
            tilePaneFX.getChildren().add(mv.iceboss());
            player.setLayoutX(960);
            player.setLayoutY(304);
            player.setImage(pv.up());

            iceboss.initMap();
            copyField(iceboss.getMap());

            for (int i = 0; i < iceboss.getMap().length; i++) {
                for (int j = 0; j < iceboss.getMap()[i].length; j++) {
                    System.out.print(iceboss.getMap()[i][j] + ",");
                }
                System.out.println();
            }
        }

        // Changement de map : Chemin de Feu -> Donjon de Feu

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 13) {
            System.err.println("[!] Warning, you have changed map [!]" + "\n[!] Loading fireway to fireroom [!]" + "\n[!] Identifier of the map change box : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] ");

            tilePaneFX.getChildren().remove(mv.fireway());
            tilePaneFX.getChildren().add(mv.fireroom());
            player.setLayoutX(400);
            player.setLayoutY(432);
            player.setImage(pv.up());

            fireroom.initMap();
            copyField(fireroom.getMap());

            for (int i = 0; i < fireroom.getMap().length; i++) {
                for (int j = 0; j < fireroom.getMap()[i].length; j++) {
                    System.out.print(fireroom.getMap()[i][j] + ",");
                }
                System.out.println();
            }
        }

        // Changement de map : Donjon de Feu -> Boss de Feu

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 16) {
            System.err.println("[!] Warning, you have changed map [!]" + "\n[!] Loading fireroom to fireboss [!]" + "\n[!] Identifier of the map change box : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] ");

            tilePaneFX.getChildren().remove(mv.fireroom());
            tilePaneFX.getChildren().add(mv.fireboss());
            player.setLayoutX(608);
            player.setLayoutY(384);
            player.setImage(pv.up());

            fireboss.initMap();
            copyField(fireboss.getMap());

            for (int i = 0; i < fireboss.getMap().length; i++) {
                for (int j = 0; j < fireboss.getMap()[i].length; j++) {
                    System.out.print(fireboss.getMap()[i][j] + ",");
                }
                System.out.println();
            }
        }

        // Changement de map : Chemin de Terre -> Donjon de Terre

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 12) {
            System.err.println("[!] Warning, you have changed map [!]" + "\n[!] Loading dirtway to dirtroom [!]" + "\n[!] Identifier of the map change box : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] ");

            tilePaneFX.getChildren().remove(mv.dirtway());
            tilePaneFX.getChildren().add(mv.dirtroom());
            player.setLayoutX(32);
            player.setLayoutY(112);
            player.setImage(pv.right());

            dirtroom.initMap();
            copyField(dirtroom.getMap());

            for (int i = 0; i < dirtroom.getMap().length; i++) {
                for (int j = 0; j < dirtroom.getMap()[i].length; j++) {
                    System.out.print(dirtroom.getMap()[i][j] + ",");
                }
                System.out.println();
            }
        }

        // Changement de map : Donjon de Terre -> Boss de Terre

        if (mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] == 15) {
            System.err.println("[!] Warning, you have changed map [!]" + "\n[!] Loading dirtroom to dirtboss [!]" + "\n[!] Identifier of the map change box : " + mapbuff[((int) player.getLayoutY() / 16)][(int) (player.getLayoutX() / 16)] + " [!] ");

            tilePaneFX.getChildren().remove(mv.dirtroom());
            tilePaneFX.getChildren().add(mv.dirtboss());
            player.setLayoutX(112);
            player.setLayoutY(96);
            player.setImage(pv.down());

            dirtboss.initMap();
            copyField(dirtboss.getMap());

            for (int i = 0; i < dirtboss.getMap().length; i++) {
                for (int j = 0; j < dirtboss.getMap()[i].length; j++) {
                    System.out.print(dirtboss.getMap()[i][j] + ",");
                }
                System.out.println();
            }
        }



        // Leave the game with KeyEvent //

        if (event.getCode() == KeyCode.ESCAPE && leavegamey) {
            leavegameescape = false;
        }

        if (event.getCode() == KeyCode.ESCAPE && !leavegamey) {
            leavegameescape = true;
            System.err.println("[!] You will leave the game, are you sure (y/n) ? [!]");
        }

        if (event.getCode() == KeyCode.Y && leavegameescape) {
            leavegamey = true;
        }

        if (event.getCode() == KeyCode.Y && !leavegameescape) {
            leavegamey = false;
        }

        if (event.getCode() == KeyCode.N) {
            leavegameescape = false;
            leavegamey = false;
        }

        if ((leavegameescape && leavegamey)) {
            System.err.println("[!] See you soon [!]");
            System.exit(0);
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
      //  bokoblin.translateXProperty().bind(bokoblinsword.getX());
        for (int i = 0; i < mapbuff.length; i++) {
            for (int j = 0; j < mapbuff[i].length; j++) {
                System.out.print(mapbuff[i][j] + ",");
            }
            System.out.println();
        }
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
}
