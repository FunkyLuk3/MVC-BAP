package fr.icom.info.m1.balleauprisonnier_mvn.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;

public class Human extends Player{
    /**
     * Constructeur du Joueur
     *
     * @param gc    ContextGraphic dans lequel on va afficher le joueur
     * @param color couleur du joueur
     * @param xInit
     * @param yInit position verticale
     * @param side
     */

    Ball ball;

    public Human(GraphicsContext gc, String color, int xInit, int yInit, String side) {
        super(gc, color, xInit, yInit, side);
        this.ball = new Ball(graphicsContext,x+30, y+30, 0, 0);
    }
    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    public void display()
    {
        ball.display();
        graphicsContext.save(); // saves the current state on stack, including the current transform
        rotate(graphicsContext, angle, x + directionArrow.getWidth() / 2, y + directionArrow.getHeight() / 2);
        graphicsContext.drawImage(directionArrow, x, y);
        graphicsContext.restore(); // back to original state (before rotation)
    }

    /**
     *  Deplacement du joueur vers la gauche, on cantonne le joueur sur le plateau de jeu
     */

    public void moveLeft()
    {
        if (x > 10 && x < 520)
        {
            spriteAnimate();
            x -= step;
        }
        ball.setX(x+30);
    }

    /**
     *  Deplacement du joueur vers la droite
     */
    public void moveRight()
    {
        if (x > 10 && x < 520)
        {
            spriteAnimate();
            x += step;
        }
        ball.setX(x+30);
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


    public void shoot(){
        sprite.playShoot();
        if(side=="top"){
            ball.setAngle(angle+90);
        }
        else{
            ball.setAngle(angle-90);
        }
        ball.setSpeed(3);
        if(ball.out()) {
            ball.setX(x);
            ball.setY(y);
        }
        ball.deplacement();
    }

    /**
     *  Deplacement en mode boost
     */
    void boost()
    {
        x += step*2;
        spriteAnimate();
    }

    void spriteAnimate(){
        //System.out.println("Animating sprite");
        if(!sprite.isRunning) {sprite.playContinuously();}
        sprite.setX(x);
        sprite.setY(y);
    }
}

