package fr.icom.info.m1.balleauprisonnier_mvn.controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.lang.Math;

public class Ball {

    double x;
    double y;
    double angle;
    double speed;

    Image projectile;
    ImageView projectileBall;
    GraphicsContext graphicsContext;

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Ball(GraphicsContext graphicsContext, double x, double y, double angle, double speed){
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.speed = speed;
        this.graphicsContext = graphicsContext;

        this.projectile = new Image("assets/ball.png", 25, 25, false, false);

        projectileBall = new ImageView();
        projectileBall.setImage(projectile);
        projectileBall.setFitWidth(10);
        projectileBall.setPreserveRatio(true);
        projectileBall.setSmooth(true);
        projectileBall.setCache(true);

    }

    public void deplacement(){
        x += speed*Math.cos(angle*(Math.PI)/180);
        y += speed*Math.sin(angle*(Math.PI)/180);
    }

    void display()
    {
        graphicsContext.save(); // saves the current state on stack, including the current transform
        graphicsContext.drawImage(projectile, x, y);
        graphicsContext.restore(); // back to original state (before rotation)
    }

    boolean out()
    {
        if (x > 600 || x < 0 || y > 600 || y < 0) {
            return true;
        }
        return  false;
    }
}

