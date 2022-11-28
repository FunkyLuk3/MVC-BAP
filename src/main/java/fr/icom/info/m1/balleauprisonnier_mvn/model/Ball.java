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

    public Ball(Field field, double x, double y, double angle, double speed){
        this.radius = 25/2;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.speed = speed;
        this.field = field;

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

    public void deplacement(){

        this.speed -= 0.0012*(this.speed*this.speed) ;

        x += speed*Math.cos(angle*(Math.PI)/180);
        y += speed*Math.sin(angle*(Math.PI)/180);
    }

    public boolean out(double height)
    {
        if (this.y > height) {
            return true;
        } else if (this.y < 0) {
            return true;
        }
        return  false;
    }

    public void bounce(){
        this.angle = 180 - this.angle;
        this.speed = this.speed + 0.5;
    }

    public boolean touch(Player player){
        return (player.getX() + 10 <= x + radius && player.getX() + 55 >= x + radius &&
                player.getY() <= y + radius && player.getY() + 70 >= y + radius);
    }

    public void updateBall(ArrayList<Player> teamA, ArrayList<Player> teamB)
    {
        // si la balle a le statut "lancée", elle se déplace
        if(isThrown)
        {
            deplacement();
        }

        // Après le déplacement on vérifie si elle est toujours dans les limites du terrain
        if (y <= 0)
        {
            side = "top";
            speed = 0.;
            teamB.get(0).setHasBall(true);
        }
        else if (y >= field.getHeight())
        {
            side = "bottom";
            speed = 0.;
            teamA.get(0).setHasBall(true);
        }

        if (x <= 0 || x >= field.getWidth())
        {
            bounce();
        }

        if (teamA.get(0).getHasBall())
        {
            x = teamA.get(0).getX();
            y = teamA.get(0).getY();
        }
        else if (teamB.get(0).getHasBall())
        {
            x = teamB.get(0).getX();
            y = teamB.get(0).getY();
        }
    }
}

