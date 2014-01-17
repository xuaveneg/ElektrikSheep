package com.game.elektriksheep.core;

import android.graphics.Canvas;

import com.game.elektriksheep.utils.Place;

public class SinglePlace extends Place {
	private int health;
	private int peopleNumber;
	public int health() {
		return health;
	}
	public int peopleNumber() {
		return peopleNumber;
	}
	private SinglePlace(int health, int bratsNumber) {
		this.health = health;
		this.peopleNumber = bratsNumber;
	}
	static private SinglePlace instance = null;
	static public void initialize(int health, int peopleNumber) {
		if (instance == null)
			instance = new SinglePlace(health, peopleNumber);
	}
	static public SinglePlace instance() {
		return instance;
	}
	@Override
	public void draw(Canvas canvas, int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void drawBackground(Canvas canvas, int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void drawForeground(Canvas canvas, int width, int height) {
		// TODO Auto-generated method stub
		
	}
}
