package fr.icom.info.m1.balleauprisonnier_mvn.controller;

import javafx.scene.canvas.Canvas;

/**
 * Classe gerant le terrain de jeu.
 * C'est un singleton
 */
public class Field extends Canvas
{
	private static Field instance_of_field;
	private double topside_y_limit;
	private double botside_y_limit;
	/**
	 * Canvas dans lequel on va dessiner le jeu.
	 *
	 * @param w largeur du canvas
	 * @param h hauteur du canvas
	 */
	private Field(double w, double h)
	{
		super(w, h);

		// the topside of the field belongs to the top team
		topside_y_limit = h/2. - 64;

		botside_y_limit = h/2.;
	}

	// Accesseurs
	public static Field getInstance()
	{
		return instance_of_field;
	}

	public double getTopsideYLimit()
	{
		return topside_y_limit;
	}

	public double getBotsideYLimit()
	{
		return botside_y_limit;
	}

	// Methode qui va crée l'instance (ne fait rien si elle est déjà créée
	public static void createInstance(double w, double h)
	{
		instance_of_field = new Field(w, h);
	}
}
