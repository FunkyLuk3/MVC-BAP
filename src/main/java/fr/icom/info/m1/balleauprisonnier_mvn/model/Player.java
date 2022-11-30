package fr.icom.info.m1.balleauprisonnier_mvn.model;


import fr.icom.info.m1.balleauprisonnier_mvn.view.Sprite;
import fr.icom.info.m1.balleauprisonnier_mvn.view.Field;
import javafx.scene.image.Image;
import javafx.util.Duration;


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

	public double getX(){
		return this.x;
	}

	public double getY(){
		return this.y;
	}

	/**
	 * Constructeur du Joueur
	 *
	 * @param color couleur du joueur
	 * @param yInit position verticale
	 */
	Player(String color, double xInit, double yInit, String side)
	{
		// Tous les joueurs commencent au centre du canvas, 
		x = xInit;
		y = yInit;
		playerColor=color;

		this.side = side;

		Image tilesheetImage = new Image("assets/orc.png");
		sprite = new Sprite(tilesheetImage, 0,0, Duration.seconds(.2), side);
		sprite.setX(x);
		sprite.setY(y);

		step = 2;
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
		} else if (x > Field.getInstance().getWidth() - 80) {
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
		} else if (x > Field.getInstance().getWidth() - 80) {
			x = 520;
		}
	}

	public void moveUp()
	{
		spriteAnimate();
		y -= step;

		if(side.equals("top"))
		{
			if (y < 10)
			{
				y = 10;
			}
			else if (y > Field.getInstance().getTopsideYLimit())
			{
				y = Field.getInstance().getTopsideYLimit();
			}
		}
		else if (side.equals("bottom"))
		{
			if (y < Field.getInstance().getBotsideYLimit())
			{
				y = Field.getInstance().getBotsideYLimit();
			}
			else if (y >  Field.getInstance().getHeight() - 64)
			{
				y =  Field.getInstance().getHeight() - 64;
			}
		}
	}

	public void moveDown()
	{
		spriteAnimate();
		y += step;

		if(side.equals("top"))
		{
			if (y < 10)
			{
				y = 10;
			}
			else if (y > Field.getInstance().getTopsideYLimit())
			{
				y = Field.getInstance().getTopsideYLimit();
			}
		}
		else if (side.equals("bottom"))
		{
			if (y < Field.getInstance().getBotsideYLimit())
			{
				y = Field.getInstance().getBotsideYLimit();
			}
			else if (y >  Field.getInstance().getHeight() - 64)
			{
				y =  Field.getInstance().getHeight() - 64;
			}
		}
	}

	void spriteAnimate()
	{
		//System.out.println("Animating sprite");
		if(!sprite.isRunning) {sprite.playContinuously();}
	}

	// renvoie un boolean indiquant si le balle passée en parametre touche ce joueur
	public boolean isTouched(Ball b)
	{
		return (b.x + b.radius > x && b.x < x + 64) 		// La balle touche le joueur "verticalement"
			&& (b.y + b.radius > y && b.y < y + 64);		// La balle touche le joueur "horizontalement"
	}
}
