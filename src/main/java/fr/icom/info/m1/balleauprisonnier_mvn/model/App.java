package fr.icom.info.m1.balleauprisonnier_mvn.model;


import fr.icom.info.m1.balleauprisonnier_mvn.controller.Controller;
import fr.icom.info.m1.balleauprisonnier_mvn.controller.Field;
import fr.icom.info.m1.balleauprisonnier_mvn.view.View;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;

/**
 * Classe principale de l'application 
 * s'appuie sur javafx pour le rendu
 */
public class App extends Application
{

	/**
	 * En javafx start() lance l'application
	 * On cree le SceneGraph de l'application ici
	 * @see <a href="https://docs.oracle.com/javafx/2/scenegraph/jfxpub-scenegraph.htm">...</a>
	 *
	 */
	@Override
	public void start(Stage stage) throws Exception
	{
		// Nom de la fenetre
		stage.setTitle("BalleAuPrisonnier");
		// Couleurs possibles
		String[] colorMap = new String[] {"blue", "green", "orange", "purple", "yellow"};

		int height = 600;
		int width = 600;

		int nb_bots_per_team = 2;

		Group root = new Group();
		Scene scene = new Scene( root );

		// On cree le terrain de jeu et on l'ajoute a la racine de la scene
		Field gameField = new Field(width, height );
		root.getChildren().add( gameField );

		// On cree les deux equipes
		PlayerTeam teamA = new PlayerTeam(nb_bots_per_team, gameField, colorMap[0], "top");
		PlayerTeam teamB = new PlayerTeam(nb_bots_per_team, gameField, colorMap[0], "bottom");

		// On ajoute les sprites de tous les joueurs à la scene
		ArrayList<Player> players = teamA.getPlayers();
		players.addAll(teamB.getPlayers());

		// On cree la balle
		Ball ball = new Ball(gameField, 100,100,0,0);

		Controller controller = new Controller(gameField, teamA, teamB, ball);

		View view = new View(gameField);

		// On met les opérations à effectuer chaque frame dans le handle de cet animation timer
		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{
				view.setGameActors(players, ball);
				view.drawGame();
			}
		}.start();

		// On ajoute la scene a la fenetre et on affiche
		stage.setScene( scene );
		stage.show();
	}

	public static void main(String[] args)
	{
		//System.out.println( "Hello World!" );
		Application.launch(args);
	}
}