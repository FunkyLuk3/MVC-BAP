package fr.icom.info.m1.balleauprisonnier_mvn.model;

import fr.icom.info.m1.balleauprisonnier_mvn.controller.Field;

public class Bot extends  Player{
    /**
     * Constructeur du Joueur
     *
     * @param field terrain dans lequel on va afficher le joueur
     * @param color couleur du joueur
     * @param xInit
     * @param yInit position verticale
     * @param side
     */
    public Bot(Field field, String color, double xInit, double yInit, String side) {
        super(field, color, xInit, yInit, side);
    }
}