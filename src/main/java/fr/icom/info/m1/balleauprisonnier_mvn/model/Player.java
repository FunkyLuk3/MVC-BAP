package fr.icom.info.m1.balleauprisonnier_mvn.model;


import fr.icom.info.m1.balleauprisonnier_mvn.view.Sprite;
import fr.icom.info.m1.balleauprisonnier_mvn.controller.Field;
import javafx.scene.canvas.GraphicsContext;
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
	double y; 	  // position verticale du joueur
	double angle = 90; // rotation du joueur, devrait toujour être en 0 et 180
	double step;    // pas d'un joueur
	String playerColor;


	// On une image globale du joueur
	Image directionArrow;
	public Sprite sprite;
	ImageView PlayerDirectionArrow;

	String side;
	boolean hasBall;

	Field field;

	/**
	 * Constructeur du Joueur
	 *
	 * @param field le terrain dans lequel on va afficher le joueur
	 * @param color couleur du joueur
	 * @param yInit position verticale
	 */
	public  boolean getHasBall(){
		return false;
	}

	public double getX(){
		return this.x;
	}

	public double getY(){
		return this.y;
	}

	Player(Field field, String color, double xInit, double yInit, String side)
	{
		// Tous les joueurs commencent au centre du canvas, 
		x = xInit;
		y = yInit;
		this.field = field;
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
		step = 2;//randomGenerator.nextFloat();

		// Pour commencer les joueurs ont une vitesse / un pas fixe
		// step = 1;

	}

	/**
	 *  Affichage du joueur
	 */
	public void display()
	{
	}

	private void rotate(GraphicsContext gc, double angle, double px, double py){
	}

	/**
	 *  Deplacement du joueur vers la gauche, on cantonne le joueur sur le plateau de jeu
	 */

	public void moveLeft(){
	}

	/**
	 *  Deplacement du joueur vers la droite
	 */
	public void moveRight(){
	}


	/**
	 *  Rotation du joueur vers la gauche
	 */
	public void turnLeft(){
	}

	public void setHasBall(boolean hasBall){}

	/**
	 *  Rotation du joueur vers la droite
	 */
	public void turnRight(){
	}


	public double shoot(){
		return 0;
	}

	/**
	 *  Deplacement en mode boost
	 */
	void boost() {
	}

	void spriteAnimate(){
	}

	public void moveUp() {
	}

	public void moveDown() {
	}

	public void kill() {
		sprite.setImage(null);
	}
}
