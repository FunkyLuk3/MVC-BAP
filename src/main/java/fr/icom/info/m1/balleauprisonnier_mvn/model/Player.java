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
	double step;    // pas d'un joueur
	String playerColor;


	// On une image globale du joueur
	public Sprite sprite;


	String side;


	Field field;


	public  boolean getHasBall(){
		return false;
	}

	public double getX(){
		return this.x;
	}

	public double getY(){
		return this.y;
	}

	/**
	 * Constructeur du Joueur
	 *
	 * @param field le terrain dans lequel on va afficher le joueur
	 * @param color couleur du joueur
	 * @param yInit position verticale
	 */
	Player(Field field, String color, double xInit, double yInit, String side)
	{
		// Tous les joueurs commencent au centre du canvas, 
		x = xInit;
		y = yInit;
		this.field = field;
		playerColor=color;

		this.side = side;

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

	public void moveLeft()
	{
		spriteAnimate();
		x -= step;

		if (x < 10)
		{
			x = 10;
		} else if (x > field.getWidth() - 80) {
			x = 520;
		}
	}

	/**
	 *  Deplacement du joueur vers la droite
	 */
	public void moveRight()
	{
		spriteAnimate();
		x += step;

		if (x < 10)
		{
			x = 10;
		} else if (x > field.getWidth() - 80) {
			x = 520;
		}
	}

	public void moveUp()
	{
		spriteAnimate();
		y -= step;

		if(side == "top")
		{
			if (y < 10)
			{
				y = 10;
			}
			else if (y > field.topside_y_limit)
			{
				y = field.topside_y_limit;
			}
		}
		else if (side == "bottom")
		{
			if (y < field.botside_y_limit)
			{
				y = field.botside_y_limit;
			}
			else if (y > field.getHeight() - 64)
			{
				y = field.getHeight() - 64;
			}
		}
	}

	public void moveDown()
	{
		spriteAnimate();
		y += step;

		if(side == "top")
		{
			if (y < 10)
			{
				y = 10;
			}
			else if (y > field.topside_y_limit)
			{
				y = field.topside_y_limit;
			}
		}
		else if (side == "bottom")
		{
			if (y < field.botside_y_limit)
			{
				y = field.botside_y_limit;
			}
			else if (y > field.getHeight() - 64)
			{
				y = field.getHeight() - 64;
			}
		}
	}

	void spriteAnimate()
	{
		//System.out.println("Animating sprite");
		if(!sprite.isRunning) {sprite.playContinuously();}
	}

	public void kill() {
		sprite.setImage(null);
	}
}
