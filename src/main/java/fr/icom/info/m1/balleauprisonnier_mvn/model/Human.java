package fr.icom.info.m1.balleauprisonnier_mvn.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import fr.icom.info.m1.balleauprisonnier_mvn.controller.Field;

public class Human extends Player
{
    public double angle = 90; // rotation du joueur, devrait toujour être en 0 et 180

    public Image directionArrow;
    ImageView PlayerDirectionArrow;

    /**
     * Constructeur du Joueur
     *
     * @param field terrain dans lequel on va afficher le joueur
     * @param color couleur du joueur
     * @param xInit
     * @param yInit position verticale
     * @param side
     */

    public Human(Field field, String color, double xInit, double yInit, String side)
    {
        super(field, color, xInit, yInit, side);



        directionArrow = new Image("assets/PlayerArrowDown.png");

        if(side == "top")
        {
            angle = 0;
        }
        else
        {
            angle = 180;
        }

        PlayerDirectionArrow = new ImageView();
        PlayerDirectionArrow.setImage(directionArrow);
        PlayerDirectionArrow.setFitWidth(10);
        PlayerDirectionArrow.setPreserveRatio(true);
        PlayerDirectionArrow.setSmooth(true);
        PlayerDirectionArrow.setCache(true);
    }

    /**
     *  Rotation du joueur vers la gauche
     */
    public void turnLeft()
    {
        if (angle > 0 && angle < 180)
        {
            angle += 1;
        }
        else {
            angle += 1;
        }
    }


    /**
     *  Rotation du joueur vers la droite
     */
    public void turnRight()
    {
        if (angle > 0 && angle < 180)
        {
            angle -=1;
        }
        else {
            angle -= 1;
        }
    }

    public void shoot(Ball ball)
    {
        if (ball.player_holding_the_ball == this)
        {
            sprite.playShoot();

            ball.setAngle(this.angle+90);
            ball.setSpeed(8);

            ball.player_holding_the_ball = null;
            ball.isThrown = true;
        }
    }

    /**
     *  Deplacement en mode boost
     */
    void boost()
    {
        x += step*2;
        spriteAnimate();
    }
}

