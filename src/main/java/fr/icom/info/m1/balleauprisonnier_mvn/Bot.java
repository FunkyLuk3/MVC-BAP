package fr.icom.info.m1.balleauprisonnier_mvn;

import javafx.scene.canvas.GraphicsContext;

public class Bot extends  Player{
    /**
     * Constructeur du Joueur
     *
     * @param gc    ContextGraphic dans lequel on va afficher le joueur
     * @param color couleur du joueur
     * @param xInit
     * @param yInit position verticale
     * @param side
     */
    Bot(GraphicsContext gc, String color, int xInit, int yInit, String side) {
        super(gc, color, xInit, yInit, side);
    }
}