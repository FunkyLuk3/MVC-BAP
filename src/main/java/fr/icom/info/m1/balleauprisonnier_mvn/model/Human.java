package fr.icom.info.m1.balleauprisonnier_mvn.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;
import fr.icom.info.m1.balleauprisonnier_mvn.controller.Field;

public class Human extends Player
{
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
     * @param field terrain dans lequel on va afficher le joueur
     * @param color couleur du joueur
     * @param xInit
     * @param yInit position verticale
     * @param side
     */

    public Human(Field field, String color, double xInit, double yInit, String side) {
        super(field, color, xInit, yInit, side);
    }
    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    public void display()
    {
        field.getGraphicsContext2D().save(); // saves the current state on stack, including the current transform
        rotate(field.getGraphicsContext2D(), angle, x + directionArrow.getWidth() / 2, y + directionArrow.getHeight() / 2);
        field.getGraphicsContext2D().drawImage(directionArrow, x, y);
        field.getGraphicsContext2D().restore(); // back to original state (before rotation)
    }

    /**
     *  Deplacement du joueur vers la gauche, on cantonne le joueur sur le plateau de jeu
     */

    public void moveLeft()
    {
        if (x < 10)
        {
            x = 10;
        } else if (x > 520) {
            x = 520;
        }
        spriteAnimate();
        x -= step;
    }

    /**
     *  Deplacement du joueur vers la droite
     */
    public void moveRight()
    {
        if (x < 10)
        {
            x = 10;
        } else if (x > 520) {
            x = 520;
        }
        spriteAnimate();
        x += step;
    }

    public void moveUp()
    {
        if (y < 10 && side=="top") {
            y = 10;
        } else if (y > 520 && side=="bottom") {
            y = 520;
        }
        if (y > 520/2 && side=="top") {
            y = 520/2;
        } else if (y < 520/2 && side=="bottom") {
            y = 520/2;
        }
        spriteAnimate();
        y += step;
    }

    public void moveDown()
    {
        if (y < 10 && side=="top") {
            y = 10;
        } else if (y > 520 && side=="bottom") {
            y = 520;
        }
        if (y > 520/2 && side=="top") {
            y = 520/2;
        } else if (y < 520/2 && side=="bottom") {
            y = 520/2;
        }
        spriteAnimate();
        y -= step;
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
        if (hasBall)
        {
            sprite.playShoot();
            hasBall = false;

            double ball_shoot_angle;
            if (side == "top")
            {
                ball_shoot_angle = this.angle+90;
            }
            else
            {
                ball_shoot_angle =  this.angle-90;
            }

            ball.setAngle(ball_shoot_angle);
            ball.setSpeed(8);
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

    void spriteAnimate(){
        //System.out.println("Animating sprite");
        if(!sprite.isRunning) {sprite.playContinuously();}
        sprite.setX(x);
        sprite.setY(y);
    }

}

