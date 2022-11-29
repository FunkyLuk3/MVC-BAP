package fr.icom.info.m1.balleauprisonnier_mvn.view;

import fr.icom.info.m1.balleauprisonnier_mvn.controller.Field;
import fr.icom.info.m1.balleauprisonnier_mvn.model.Ball;
import fr.icom.info.m1.balleauprisonnier_mvn.model.Bot;
import fr.icom.info.m1.balleauprisonnier_mvn.model.Human;
import fr.icom.info.m1.balleauprisonnier_mvn.model.Player;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;


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

        // On dessine d'abord les flèches des humains
        drawArrows();

        // les joueurs
        for (Player p : players)
        {
            // Le viewport de sprite permet d'isoler la bonne partie du sprite
            Rectangle2D rect = p.sprite.getViewport();

            gc.drawImage(p.sprite.getImage(),
                    rect.getMinX(), rect.getMinY(),     // position dans le sprite
                    rect.getWidth(), rect.getHeight(),  // dimensions dans le sprite
                    p.getX(), p.getY(),                 // position dans le gc
                    rect.getWidth(),rect.getHeight());  // dimensions dans le gc


        }

        // la balle
        gc.drawImage(ball.getImage(), ball.getX(), ball.getY());
    }

    private void drawArrows()
    {
        for(Player p : players)
        {
            if(p instanceof Human)
            {
                field.getGraphicsContext2D().save(); // saves the current state on stack, including the current transform
                rotate(field.getGraphicsContext2D(), ((Human) p).angle, p.getX() + ((Human) p).directionArrow.getWidth() / 2, p.getY() + ((Human) p).directionArrow.getHeight() / 2);
                field.getGraphicsContext2D().drawImage(((Human) p).directionArrow, p.getX(), p.getY());
                field.getGraphicsContext2D().restore(); // back to original state (before rotation)
            }
        }
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py)
    {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
