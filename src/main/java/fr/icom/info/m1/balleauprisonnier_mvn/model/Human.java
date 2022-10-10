package fr.icom.info.m1.balleauprisonnier_mvn.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;

public class Human extends Player{
    @Override
    public boolean getHasBall() {
        return this.hasBall;
    }

    public void setHasBall(boolean hasBall){
        this.hasBall = hasBall;
    }

    /**
     * Constructeur du Joueur
     *
     * @param gc    ContextGraphic dans lequel on va afficher le joueur
     * @param color couleur du joueur
     * @param xInit
     * @param yInit position verticale
     * @param side
     */

    public Human(GraphicsContext gc, String color, int xInit, int yInit, String side) {
        super(gc, color, xInit, yInit, side);
    }
    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    public void display()
    {
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

    public double shoot(){
        sprite.playShoot();
        hasBall = false;
        if (side == "top") {
            return this.angle+90;
        }
        return this.angle-90;
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

