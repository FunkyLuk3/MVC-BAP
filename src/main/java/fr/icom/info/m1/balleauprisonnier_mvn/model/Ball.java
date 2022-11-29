package fr.icom.info.m1.balleauprisonnier_mvn.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import fr.icom.info.m1.balleauprisonnier_mvn.controller.Field;

import java.lang.Math;
import java.util.ArrayList;

public class Ball
{

    double x;
    double y;
    double angle;
    double speed;

    double radius;

    boolean isThrown;

    Human player_holding_the_ball;

    String side;

    Field field;

    Image projectile;
    ImageView projectileBall;
    GraphicsContext graphicsContext;

    public boolean getIsThrown(){
        return this.isThrown;
    }
    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Ball(Field field, double x, double y, double angle, double speed)
    {
        this.radius = 25./2;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.speed = speed;
        this.field = field;
        this.isThrown = false;
        this.player_holding_the_ball = null;


        this.projectile = new Image("assets/ball.png", 25, 25, false, false);

        projectileBall = new ImageView();
        projectileBall.setImage(projectile);
        projectileBall.setFitWidth(10);
        projectileBall.setPreserveRatio(true);
        projectileBall.setSmooth(true);
        projectileBall.setCache(true);
    }

    public Image getImage()
    {
        return projectileBall.getImage();
    }

    public void move()
    {
        // La balle ne bouge que si elle est lancée
        if(isThrown)
        {
            this.speed -= 0.0012*(this.speed*this.speed);

            // Tant que la balle se déplace au dessus d'un certain seuil, elle avance
            if (speed > 1)
            {
                x += speed*Math.cos(angle*(Math.PI)/180);
                y += speed*Math.sin(angle*(Math.PI)/180);

                isOutOfBound();
            }
            else
            {
                // Sinon, on arrête la balle et elle peut être ramassée
                speed = 0;
                isThrown = false;
            }
        }
    }

    public void isOutOfBound()
    {
        // On vérifie d'abord les Y
        if (this.y > field.getHeight())
        {
            // balle sort par le bas du terrain
            // Elle revient à l'équipe du bas
            isThrown = false;
            player_holding_the_ball = null;
            x = field.getWidth()/2;
            y = 3 * field.getHeight()/4;
        }
        else if (this.y < 0)
        {
            // balle sort par le haut du terrain
            // Elle revient à l'équipe du haut
            isThrown = false;
            player_holding_the_ball = null;
            x = field.getWidth()/2;
            y = field.getHeight()/4;
        }

        // La balle rebondit sur les murs (les côtés du terrains
        if (this.x + radius > field.getWidth() || this.x < 0)
        {
            bounce();
        }
    }

    public void bounce()
    {
        this.angle = 180 - this.angle;
        this.speed = this.speed + 0.5;
    }

    public boolean touch(Player player){
        return (player.getX() + 10 <= x + radius && player.getX() + 55 >= x + radius &&
                player.getY() <= y + radius && player.getY() + 70 >= y + radius);
    }

    // le comportement de la balle, sera appelé chaque frame
    public void update(PlayerTeam teamA, PlayerTeam teamB)
    {

        // avant tout, on bouge la balle
        move();

        // Si la balle est toujours lancée, on vérifie si elle touche des joueurs
        if (isThrown)
        {
            // On retire les joueurs touchés par la balle
            if(side != teamA.getSide())
            {
                teamA.checkBallCollisions(this);
            }
            else
            {
                teamB.checkBallCollisions(this);
            }
        }
        else
        {
            // Ici, les joueurs (humains) peuvent ramasser la balle
            if(teamA.getHumanPlayer().isTouched(this))
            {
                player_holding_the_ball = teamA.getHumanPlayer();
                side = player_holding_the_ball.side;
            }
            else if (teamB.getHumanPlayer().isTouched(this))
            {
                player_holding_the_ball = teamB.getHumanPlayer();
                side = player_holding_the_ball.side;
            }

            // si la balle est tenue, elle est à la même position que le joueur
            if (player_holding_the_ball != null)
            {
                x = player_holding_the_ball.x;
                y = player_holding_the_ball.y;
            }
        }
    }
}

