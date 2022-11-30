package fr.icom.info.m1.balleauprisonnier_mvn.model;

import fr.icom.info.m1.balleauprisonnier_mvn.view.Field;

public class Bot extends  Player
{
    // booleen pour determiner la logique de mouvement du bot
    private boolean is_moving_right;

    /**
     * Constructeur du Joueur
     *
     * @param color couleur du joueur
     * @param xInit
     * @param yInit position verticale
     * @param side
     */
    public Bot(String color, double xInit, double yInit, String side) {
        super(color, xInit, yInit, side);

        is_moving_right = true;
    }

    public void move()
    {
        // on déplace le bot
        if(is_moving_right)
        {
            moveRight();
        }
        else
        {
            moveLeft();
        }
    }

    /**
     *  Deplacement du bot vers la gauche
     */
    @Override
    public void moveLeft()
    {
        spriteAnimate();
        x -= step;

        if (x < 10)
        {
            x = 10;
            is_moving_right = true;
        }
        else if (x >  Field.getInstance().getWidth() - 80)
        {
            x =  Field.getInstance().getWidth() - 80;
        }
    }

    /**
     *  Deplacement du bot vers la droite
     */
    @Override
    public void moveRight()
    {
        spriteAnimate();
        x += step;
        // dans le cas du bot,
        if (x < 10)
        {
            x = 10;
        }
        else if (x >  Field.getInstance().getWidth() - 80)
        {
            x =  Field.getInstance().getWidth() - 80;
            is_moving_right = false;
        }
    }

}