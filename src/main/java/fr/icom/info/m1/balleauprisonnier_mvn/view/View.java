package fr.icom.info.m1.balleauprisonnier_mvn.view;

import fr.icom.info.m1.balleauprisonnier_mvn.controller.Field;
import fr.icom.info.m1.balleauprisonnier_mvn.model.Ball;
import fr.icom.info.m1.balleauprisonnier_mvn.model.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;


import java.util.ArrayList;

public class View extends Group
{
    enum CurrentView
    {
        Menu,
        Game
    }

    CurrentView current_view;
    private Field field;

    private ArrayList<Player> players;

    Ball ball;

    private AnimationTimer game_view;

    private AnimationTimer menu_view;

    // Constructor
    public View(Field f)
    {
        this.field = f;

        game_view = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                drawGame();
            }
        };

        menu_view = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // les menus si on en arrive là :)
            }
        };
    }

    // Accesseurs
    public void setGameActors(ArrayList<Player> p, Ball b)
    {
        players = p;
        ball = b;
    }

    // Methodes

    // Methode qui va dessiner les joueurs et la balle
    public void drawGame()
    {
        GraphicsContext gc = field.getGraphicsContext2D();

        gc.clearRect(0, 0, field.getWidth() , field.getHeight());

        // les joueurs
        for (Player p : players)
        {

            gc.drawImage(p.sprite.getImage(), 0, 0, 64, 64, p.getX(), p.getY(), 64,64);
        }

        // la balle
        gc.drawImage(ball.getImage(), ball.getX(), ball.getY());
    }
}
