package fr.icom.info.m1.balleauprisonnier_mvn.view;

import fr.icom.info.m1.balleauprisonnier_mvn.controller.Field;
import fr.icom.info.m1.balleauprisonnier_mvn.model.Ball;
import fr.icom.info.m1.balleauprisonnier_mvn.model.Human;
import fr.icom.info.m1.balleauprisonnier_mvn.model.Player;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;


import java.util.ArrayList;

public class View
{
    private Field field;

    private ArrayList<Player> players;

    Ball ball;

    // Constructor
    public View(Field f)
    {
        this.field = f;
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
            if (p != null)
            {
                // Le viewport de sprite permet d'isoler la bonne partie du sprite
                Rectangle2D rect = p.sprite.getViewport();

                gc.drawImage(p.sprite.getImage(),
                        rect.getMinX(), rect.getMinY(),     // position dans le sprite
                        rect.getWidth(), rect.getHeight(),  // dimensions dans le sprite
                        p.getX(), p.getY(),                 // position dans le gc
                        rect.getWidth(),rect.getHeight());  // dimensions dans le gc
            }
        }

        // la balle
        gc.drawImage(ball.getImage(), ball.getX(), ball.getY());
    }

    public void drawEndOfGame(String winning_side)
    {
        String endgame_message;

        if(winning_side.equals("top"))
        {
            endgame_message = "L'équipe du haut a gagné !";
        }
        else
        {
            endgame_message = "L'équipe du bas a gagné !";
        }

        GraphicsContext gc = field.getGraphicsContext2D();
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(Font.font(null, FontWeight.BOLD, null, 35));

        gc.fillText(endgame_message,
                    Math.round(field.getWidth()  / 2),
                    Math.round(field.getHeight() / 2));
    }

    private void drawArrows()
    {
        for(Player p : players)
        {
            if(p instanceof Human)
            {
                GraphicsContext gc = field.getGraphicsContext2D();

                gc.save(); // saves the current state on stack, including the current transform
                rotate(gc, ((Human) p).angle, p.getX() + ((Human) p).directionArrow.getWidth() / 2, p.getY() + ((Human) p).directionArrow.getHeight() / 2);
                gc.drawImage(((Human) p).directionArrow, p.getX(), p.getY());
                gc.restore(); // back to original state (before rotation)
            }
        }
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py)
    {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
