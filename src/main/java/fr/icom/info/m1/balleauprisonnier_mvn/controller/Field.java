package fr.icom.info.m1.balleauprisonnier_mvn.controller;

import javafx.scene.canvas.Canvas;

/**
 * Classe gerant le terrain de jeu.
 * 
 */
public class Field extends Canvas
{
	public double topside_y_limit;
	public double botside_y_limit;
	/**
	 * Canvas dans lequel on va dessiner le jeu.
	 *
	 * @param w largeur du canvas
	 * @param h hauteur du canvas
	 */
	public Field(int w, int h)
	{
		super(w, h);

		// the topside of the field belongs to the top team
		topside_y_limit = h/2 - 64;

		botside_y_limit = h/2;
	}

}
