package fr.icom.info.m1.balleauprisonnier_mvn.controller;

import java.util.ArrayList;

import fr.icom.info.m1.balleauprisonnier_mvn.model.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;




public class Controller extends Group
{
    /** Tableau traçant les evenements */
    ArrayList<String> input = new ArrayList<String>();

    AnimationTimer input_manager;

    Field field;

    PlayerTeam teamA;
    PlayerTeam teamB;

    Ball ball;


    public Controller(Field f, PlayerTeam tA, PlayerTeam tB, Ball b)
    {
        input = new ArrayList<String>();
        field = f;
        teamA = tA;
        teamB = tB;
        ball = b;

        GraphicsContext gc = f.getGraphicsContext2D();
        double width = f.getWidth();
        double height = f.getHeight();

        /** permet de capturer le focus et donc les evenements clavier et souris */
        this.setFocusTraversable(true);

        /**
         * Event Listener du clavier
         * quand une touche est pressee on la rajoute a la liste d'input
         *
         */
        this.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent e)
            {
                String code = e.getCode().toString();
                // only add once... prevent duplicates
                if ( !input.contains(code) )
                    input.add( code );
            }
        });

        /**
         * Event Listener du clavier
         * quand une touche est relachee on l'enleve de la liste d'input
         *
         */
        this.setOnKeyReleased( new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent e)
            {
                String code = e.getCode().toString();
                input.remove( code );
            }
        });

        //
        input_manager = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // Mouvement des joueurs
                playersMovement();
            }
        };

        // On lance le manager d'input, la methode handle sera appelée à chaque frame
        input_manager.start();
    }

    public void playersMovement()
    {
        // Les seuls joueurs qui peuvent bouger
        Human hA = teamA.getHumanPlayer();
        Human hB = teamB.getHumanPlayer();

        if (input.contains("K"))
            hA.moveLeft();

        if (input.contains("M"))
            hA.moveRight();

        if (input.contains("O"))
            hA.moveUp();

        if (input.contains("L"))
            hA.moveDown();

        if (input.contains("P"))
            hA.turnLeft();

        if (input.contains("I"))
            hA.turnRight();

        if (input.contains("CONTROL"))
            hA.shoot(ball);

        if (input.contains("Q"))
            hB.moveLeft();

        if (input.contains("D"))
            hB.moveRight();

        if (input.contains("Z"))
            hB.moveUp();

        if (input.contains("S"))
            hB.moveDown();

        if (input.contains("A"))
            hB.turnLeft();

        if (input.contains("E"))
            hB.turnRight();

        if (input.contains("SPACE"))
            hB.shoot(ball);
    }
}

