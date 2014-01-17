package com.game.elektriksheep.core;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

import com.game.elektriksheep.utils.Color;
import com.game.elektriksheep.utils.Constants;
import com.game.elektriksheep.utils.EndPlace;

public class NuclearPlant extends EndPlace {
	private Paint paint = new Paint();
	private float health;
	private int sheepNumber;
	private Color color;
	private Point topLeft;
	public float health() {
		return health;
	}
	public int sheepNumber() {
		return sheepNumber;
	}
	public NuclearPlant(Color color, Point topLeft) {
		this.color = color;
		this.health = 10000;
		this.sheepNumber = 0;
		this.paint.setColor(color.code());
		this.topLeft = topLeft;
	}
	@Override
	public void draw(Canvas canvas, int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void drawBackground(Canvas canvas, int width, int height) {
		// Wrapping Dimensions
		Point plantTopLeft = new Point(width*topLeft.x/100, height*topLeft.y/100);
		int plantWidth = width*10/100;
		int plantHeight = height*10/100;
		canvas.drawRect(plantTopLeft.x, plantTopLeft.y, plantTopLeft.x+plantWidth, plantTopLeft.y+plantHeight, Constants.defaultPaintUnfilled);
		// Draw Sign
		Point signTopLeft = new Point(plantTopLeft);
		signTopLeft.x += plantWidth*2/3;
		int signSize = plantWidth/3;
		Rect externalRect = new Rect(signTopLeft.x, signTopLeft.y, signTopLeft.x+signSize, signTopLeft.y+signSize);
		canvas.drawRect(externalRect, paint);
		RectF externalRectF = new RectF(externalRect);
		canvas.drawArc(externalRectF, 0, 60, true, Constants.defaultPaint);
		canvas.drawArc(externalRectF, 120, 60, true, Constants.defaultPaint);
		canvas.drawArc(externalRectF, 240, 60, true, Constants.defaultPaint);
		// Draw Nuclear Plant
		int plantCenterX = plantTopLeft.x + plantWidth/2;
		int plantCenterY = plantTopLeft.y + plantHeight/2;
		canvas.drawCircle(plantCenterX, plantCenterY, plantWidth/2, paint);
		canvas.drawCircle(plantCenterX, plantCenterY, plantWidth/5, Constants.defaultPaint);
		canvas.drawText(""+(int)health, plantTopLeft.x, plantTopLeft.y + plantHeight , Constants.defaultPaint);
	}
	@Override
	public void addSheep(Sheep sheep) {
		++sheepNumber;
		if (sheep.color() == color)
			health += sheep.health();
		else
			health -= sheep.health();
	}
	public void decrHealth(int elapsedTime) {
		health -= (elapsedTime*10)/1000.0;
	}
	@Override
	public void drawForeground(Canvas canvas, int width, int height) {
		// TODO Auto-generated method stub
		
	}
}
