package com.game.elektriksheep.core;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.game.elektriksheep.utils.Color;
import com.game.elektriksheep.utils.DrawableObject;
import com.game.elektriksheep.utils.MovingDirection;

public class Sheep extends DrawableObject {
	private Paint paint;
	private MovingDirection direction;
	private int position;
	private Color color;
	private Path path;
	private int health;
	private boolean ended = false;
	public void end() {
		ended = true;
	}
	public MovingDirection direction() {
		return direction;
	}
	public int position() {
		return position;
	}
	public Color color() {
		return color;
	}
	public Path path() {
		return path;
	}
	public int health() {
		return health;
	}
	public int speed() {
		return color.speed();
	}
	public int aggressivity() {
		return color.aggressivity();
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public void setDirection(MovingDirection direction) {
		this.direction = direction;
	}
	public void invertDirection() {
		direction = direction.inverse();
	}
	public void setPath(Path path) {
		this.path = path;
	}
	public Sheep(Color color) {
		this.direction = MovingDirection.None;
		this.position = 0;
		this.color = color;
		this.paint = new Paint();
		this.paint.setColor(color.code());
		this.path = null;
		this.health = 100;
	}
	public void draw(Canvas canvas, int width, int height) {
		if (path == null || ended)
			return;
		int centerX = (direction == MovingDirection.Down ? path.up().x : path.down().x);
		int centerY = (direction == MovingDirection.Down ? path.up().y : path.down().y);
		int coeff = (direction == MovingDirection.Down ? -1 : 1);
		int semiSheepWidth = width*3/100;
		int semiSheepHeight = height*1/100;
		if (direction == MovingDirection.Up) {
			semiSheepHeight = -semiSheepHeight;
			semiSheepWidth = -semiSheepWidth;
		}
		switch(path.orientation()) {
		case horizontal:
			centerX += coeff*(path.up().x - path.down().x)*position/path.length();
			centerX = (centerX * width) / 100;
			centerY = (centerY * height) / 100;
			canvas.drawRect(centerX-semiSheepWidth, centerY-semiSheepHeight, centerX+semiSheepWidth, centerY+semiSheepHeight, paint);
			canvas.drawLine(centerX+semiSheepWidth, centerY, centerX+3*semiSheepWidth/2, centerY, paint);
			canvas.drawOval(new RectF(centerX+3*semiSheepWidth/2, centerY-semiSheepHeight/2, centerX+5*semiSheepWidth/2, centerY+semiSheepHeight/2), paint);
			break;
		case vertical:
			centerY += coeff*(path.up().y - path.down().y)*position/path.length();
			centerX = (centerX * width) / 100;
			centerY = (centerY * height) / 100;
			canvas.drawRect(centerX-semiSheepHeight, centerY-semiSheepWidth, centerX+semiSheepHeight, centerY+semiSheepWidth, paint);
			canvas.drawLine(centerX, centerY+semiSheepWidth, centerX, centerY+3*semiSheepWidth/2, paint);
			canvas.drawOval(new RectF(centerX-semiSheepHeight/2, centerY+3*semiSheepWidth/2, centerX+semiSheepHeight/2, centerY+5*semiSheepWidth/2), paint);
			break;
		}
	}
	public void drawBackground(Canvas canvas, int width, int height) {		
	}
	@Override
	public void drawForeground(Canvas canvas, int width, int height) {
		// TODO Auto-generated method stub
		
	}
}
