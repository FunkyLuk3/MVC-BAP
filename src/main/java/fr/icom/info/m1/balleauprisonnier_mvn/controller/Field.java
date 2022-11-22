package fr.icom.info.m1.balleauprisonnier_mvn.controller;


import java.util.ArrayList;

import com.sun.tools.javac.util.ArrayUtils;
import fr.icom.info.m1.balleauprisonnier_mvn.model.Bot;
import fr.icom.info.m1.balleauprisonnier_mvn.model.Human;
import fr.icom.info.m1.balleauprisonnier_mvn.model.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.model.Ball;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Classe gerant le terrain de jeu.
 * 
 */
public class Field extends Canvas {

	/** Joueurs */
	ArrayList<Player> teamA = new ArrayList<Player>();
	ArrayList<Player> teamB = new ArrayList<Player>();

	Ball ball;
	/** Couleurs possibles */
	String[] colorMap = new String[] {"blue", "green", "orange", "purple", "yellow"};
	/** Tableau traçant les evenements */
	ArrayList<String> input = new ArrayList<String>();


	final GraphicsContext gc;
	final int width;
	final int height;

	/**
	 * Canvas dans lequel on va dessiner le jeu.
	 *
	 * @param scene Scene principale du jeu a laquelle on va ajouter notre Canvas
	 * @param w largeur du canvas
	 * @param h hauteur du canvas
	 */
	public Field(Scene scene, int w, int h)
	{
		super(w, h);
		width = w;
		height = h;

		/** permet de capturer le focus et donc les evenements clavier et souris */
		this.setFocusTraversable(true);

		gc = this.getGraphicsContext2D();

		/** On initialise le terrain de jeu */
		teamA.add(new Human(gc, colorMap[0], w/2, h-80, "bottom"));
		teamA.add(new Bot(gc, colorMap[0], w/4, h-80, "bottom"));
		teamA.add(new Bot(gc, colorMap[0], 3*w/4, h-80, "bottom"));

		for (int i = 0; i < teamA.size(); i++) {
			teamA.get(i).display();
		}
		teamA.get(0).setHasBall(true);

		ball = new Ball(gc,w/2, h-50,0, 0);
		ball.setSide("bottom");
		ball.display();

		teamB.add(new Human(gc, colorMap[1], w/2, 20, "top"));
		teamB.add(new Bot(gc, colorMap[1], w/4, 20, "top"));
		teamB.add(new Bot(gc, colorMap[1], 3*w/4, 20, "top"));

		for (int i = 0; i < teamB.size(); i++) {
			teamB.get(i).display();
		}

		/**
		 * Event Listener du clavier
		 * quand une touche est pressee on la rajoute a la liste d'input
		 *
		 */
		this.setOnKeyPressed(
				new EventHandler<KeyEvent>()
				{
					public void handle(KeyEvent e)
					{
						String code = e.getCode().toString();
						// only add once... prevent duplicates
						if ( !input.contains(code) )
							input.add( code );
					}
				});

		/**
		 * Event Listener du clavier
		 * quand une touche est relachee on l'enleve de la liste d'input
		 *
		 */
		this.setOnKeyReleased(
				new EventHandler<KeyEvent>()
				{
					public void handle(KeyEvent e)
					{
						String code = e.getCode().toString();
						input.remove( code );
					}
				});

		/**
		 *
		 * Boucle principale du jeu
		 *
		 * handle() est appelee a chaque rafraichissement de frame
		 * soit environ 60 fois par seconde.
		 *
		 */
		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{
				// On nettoie le canvas a chaque frame
				gc.setFill( Color.LIGHTGRAY);
				gc.fillRect(0, 0, width, height);

				// Deplacement et affichage des joueurs
				for (int i = 0; i < teamA.size(); i++) {
					if (input.contains("K")) {
						teamA.get(i).moveLeft();
					}
					if (input.contains("M")) {
						teamA.get(i).moveRight();
					}
					if (input.contains("L")) {
						teamA.get(i).moveUp();
					}
					if (input.contains("O")) {
						teamA.get(i).moveDown();
					}
					if (input.contains("P")) {
						teamA.get(i).turnLeft();
					}
					if (input.contains("I")) {
						teamA.get(i).turnRight();
					}
					if (input.contains("CONTROL") && teamA.get(i).getHasBall()) {
						ball.setAngle(teamA.get(i).shoot());
						ball.setSpeed(0.5);
					}
					if (!teamA.get(i).getHasBall()) {
						ball.deplacement();
						ball.display();
					}
					teamA.get(i).display();
					updateBall();
				}

				for (int i = 0; i < teamB.size(); i++) {
					if (input.contains("Q"))
					{
						teamB.get(i).moveLeft();
					}
					if (input.contains("D"))
					{
						teamB.get(i).moveRight();
					}
					if (input.contains("A"))
					{
						teamB.get(i).turnLeft();
					}
					if (input.contains("E"))
					{
						teamB.get(i).turnRight();
					}
					if (input.contains("S")) {
						teamB.get(i).moveUp();
					}
					if (input.contains("Z")) {
						teamB.get(i).moveDown();
					}
					if (input.contains("SPACE") && teamB.get(i).getHasBall())
					{
						ball.setAngle(teamB.get(i).shoot());
						ball.setSpeed(0.5);
					}
					if (!teamB.get(i).getHasBall()) {
						ball.deplacement();
						ball.display();
					}
					teamB.get(i).display();
					updateBall();
				}

				for (int i = 0; i < teamB.size(); i++) {
					if (ball.getSide() == "bottom") {
						if (ball.touch(teamB.get(i))) {
							teamB.remove(i);
						}
					}
				}

				for (int i = 0; i < teamA.size(); i++) {
					if (ball.getSide() == "top") {
						if (ball.touch(teamA.get(i))) {
							teamA.remove(i);
						}
					}
				}

			}
		}.start(); // On lance la boucle de rafraichissement

	}

	public java.lang.Integer playerWithBall(){
		for (int i = 0; i < teamA.size(); i++) {
			if (teamA.get(i).getHasBall()) {
				return i;
			}else if (teamB.get(i).getHasBall()) {
				return i;
			}
		}
		return null;
	}
	public void updateBall(){
		if(ball.getIsThrown()){
			ball.deplacement();
		} else if (ball.getY() <= 0){
			ball.setSide("top");
			ball.setSpeed(0);
			teamB.get(0).setHasBall(true);
			ball.setX(teamB.get(0).getX());
			ball.setY(teamB.get(0).getY());
		} else if (ball.getY() >= height) {
			ball.setSide("bottom");
			ball.setSpeed(0);
			teamA.get(0).setHasBall(true);
			ball.setX(teamA.get(0).getX());
			ball.setY(teamA.get(0).getY());
		}

		if (ball.getX() <= 0 ){
			ball.bounce();
		} else if (ball.getX() >= width) {
			ball.bounce();
		}

		if (teamA.get(0).getHasBall()) {
			ball.setX(teamA.get(0).getX());
			ball.setY(teamA.get(0).getY());
		} else if (teamB.get(0).getHasBall()) {
			ball.setX(teamB.get(0).getX());
			ball.setY(teamB.get(0).getY());
		}

		ball.display();
	}

	public ArrayList<Player> getJoueurs() {
		ArrayList<Player> joueurs = new ArrayList<Player>();

		for (int i = 0; i < teamA.size(); i++) {
			joueurs.add(teamA.get(i));
		}
		for (int i = 0; i < teamB.size(); i++) {
			joueurs.add(teamB.get(i));
		}

		return joueurs;
	}
}
