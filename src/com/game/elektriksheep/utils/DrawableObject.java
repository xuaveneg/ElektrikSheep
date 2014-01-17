package com.game.elektriksheep.utils;

import com.game.elektriksheep.views.LevelView;

import android.graphics.Canvas;

public abstract class DrawableObject {
	public DrawableObject() {
		LevelView.addDrawableObject(this);
	}
	public abstract void draw(Canvas canvas, int width, int height);
	public abstract void drawBackground(Canvas canvas, int width, int height);
	public abstract void drawForeground(Canvas canvas, int width, int height);
}
