package fr.icom.info.m1.balleauprisonnier_mvn.controller;

import javafx.scene.canvas.Canvas;

/**
 * Classe gerant le terrain de jeu.
 * 
 */
public class Field extends Canvas
{
	final int width;
	final int height;

	/**
	 * Canvas dans lequel on va dessiner le jeu.
	 *
	 * @param w largeur du canvas
	 * @param h hauteur du canvas
	 */
	public Field(int w, int h)
	{
		super(w, h);
		width = w;
		height = h;
	}

}
