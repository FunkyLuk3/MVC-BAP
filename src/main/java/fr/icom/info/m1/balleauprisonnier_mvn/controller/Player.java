package fr.icom.info.m1.balleauprisonnier_mvn.controller;


import fr.icom.info.m1.balleauprisonnier_mvn.view.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.Random;

/**
 * 
 * Classe gerant un joueur
 *
 */
public class Player
{
	double x;       // position horizontale du joueur
	final double y; 	  // position verticale du joueur
	double angle = 90; // rotation du joueur, devrait toujour être en 0 et 180
	double step;    // pas d'un joueur
	String playerColor;

	// On une image globale du joueur
	Image directionArrow;
	Sprite sprite;
	ImageView PlayerDirectionArrow;

	String side;

	GraphicsContext graphicsContext;

	/**
	 * Constructeur du Joueur
	 *
	 * @param gc ContextGraphic dans lequel on va afficher le joueur
	 * @param color couleur du joueur
	 * @param yInit position verticale
	 */
	Player(GraphicsContext gc, String color, int xInit, int yInit, String side)
	{
		// Tous les joueurs commencent au centre du canvas, 
		x = xInit;
		y = yInit;
		graphicsContext = gc;
		playerColor=color;

		angle = 0;
		this.side = side;

		// On charge la representation du joueur
		if(this.side=="top"){
			directionArrow = new Image("assets/PlayerArrowDown.png");
		}
		else{
			directionArrow = new Image("assets/PlayerArrowUp.png");
		}

		PlayerDirectionArrow = new ImageView();
		PlayerDirectionArrow.setImage(directionArrow);
		PlayerDirectionArrow.setFitWidth(10);
		PlayerDirectionArrow.setPreserveRatio(true);
		PlayerDirectionArrow.setSmooth(true);
		PlayerDirectionArrow.setCache(true);

		Image tilesheetImage = new Image("assets/orc.png");
		sprite = new Sprite(tilesheetImage, 0,0, Duration.seconds(.2), side);
		sprite.setX(x);
		sprite.setY(y);
		//directionArrow = sprite.getClip().;

		// Tous les joueurs ont une vitesse aleatoire entre 0.0 et 1.0
		Random randomGenerator = new Random();
		step = randomGenerator.nextFloat();

		// Pour commencer les joueurs ont une vitesse / un pas fixe
		// step = 1;

	}

	/**
	 *  Affichage du joueur
	 */
	void display()
	{
	}

	private void rotate(GraphicsContext gc, double angle, double px, double py){
	}

	/**
	 *  Deplacement du joueur vers la gauche, on cantonne le joueur sur le plateau de jeu
	 */

	void moveLeft(){
	}

	/**
	 *  Deplacement du joueur vers la droite
	 */
	void moveRight(){
	}


	/**
	 *  Rotation du joueur vers la gauche
	 */
	void turnLeft(){
	}


	/**
	 *  Rotation du joueur vers la droite
	 */
	void turnRight(){
	}


	void shoot(){
	}

	/**
	 *  Deplacement en mode boost
	 */
	void boost() {
	}

	void spriteAnimate(){
	}

}
