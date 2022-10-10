package fr.icom.info.m1.balleauprisonnier_mvn.controller;


import java.util.ArrayList;

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
	Player[] teamA = new Player[3];
	Player [] teamB = new Player[3];

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
		teamA[1] = new Bot(gc, colorMap[0], w/4, h-50, "bottom");
		teamA[1].display();
		teamA[0] = new Human(gc, colorMap[0], w/2, h-50, "bottom");
		teamA[0].setHasBall(true);
		teamA[0].display();
		teamA[2] = new Bot(gc, colorMap[0], 3*w/4, h-50, "bottom");
		teamA[2].display();

		ball = new Ball(gc,w/2, h-50,0, 0);
		ball.display();

		teamB[1] = new Bot(gc, colorMap[1], w/4, 20, "top");
		teamB[1].display();
		teamB[0] = new Human(gc, colorMap[1], w/2, 20, "top");
		teamB[0].display();
		teamB[2] = new Bot(gc, colorMap[1], 3*w/4, 20, "top");
		teamB[2].display();


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
				for (int i = 0; i < teamA.length; i++) {
					if (input.contains("LEFT")) {
						teamA[i].moveLeft();
					}
					if (input.contains("RIGHT")) {
						teamA[i].moveRight();
					}
					if (input.contains("UP")) {
						teamA[i].turnLeft();
					}
					if (input.contains("DOWN")) {
						teamA[i].turnRight();
					}
					if (input.contains("CONTROL") && teamA[i].getHasBall()) {
						ball.setAngle(teamA[i].shoot());
						ball.setSpeed(0.5);
					}
					if (!teamA[i].getHasBall()) {
						ball.deplacement();
						ball.display();
					}
					teamA[i].display();
				}
				for (int i = 0; i < teamB.length; i++) {
					if (input.contains("Q"))
					{
						teamB[i].moveLeft();
					}
					if (input.contains("D"))
					{
						teamB[i].moveRight();
					}
					if (input.contains("S"))
					{
						teamB[i].turnLeft();
					}
					if (input.contains("Z"))
					{
						teamB[i].turnRight();
					}
					if (input.contains("SPACE") && teamB[i].getHasBall())
					{
						ball.setAngle(teamB[i].shoot());
						ball.setSpeed(0.5);
					}
					if (!teamB[i].getHasBall()) {
						ball.deplacement();
						ball.display();
					}
					teamB[i].display();
				}
			}
		}.start(); // On lance la boucle de rafraichissement

	}

	public java.lang.Integer playerWithBall(){
		for (int i = 0; i < teamA.length; i++) {
			if (teamA[i].getHasBall()) {
				return i;
			}else if (teamB[i].getHasBall()) {
				return i;
			}
		}
		return null;
	}
	public void updateBall(){
		if(ball.getIsThrown()){
			ball.deplacement();
		} else if(ball.getSide() == "undefined"){
			ball.deplacement();
		}
		ball.display();
	}

	public Player[] getJoueurs() {
		Player [] joueurs = new Player[teamA.length + teamB.length];

		for (int i = 0; i < joueurs.length; i++) {
			if (i< teamA.length){
				joueurs[i] = teamA[i];
			}
			if (i>= teamA.length){
				joueurs[i] = teamB[i-teamA.length];
			}
		}

		return joueurs;
	}
}
