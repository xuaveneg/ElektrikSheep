package com.game.elektriksheep.core;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Shader;

import com.game.elektriksheep.utils.Constants;
import com.game.elektriksheep.utils.MovingDirection;
import com.game.elektriksheep.utils.Orientation;
import com.game.elektriksheep.utils.Place;

public class Path extends Place {
	private Paint paint;
	private int length;
	private Connection connectionUp;
	private Connection connectionDown;
	private Orientation orientation;
	public int length() {
		return length;
	}
	public Connection connection(MovingDirection direction) {
		switch(direction) {
		case Up:
			return connectionUp;
		case Down:
			return connectionDown;
		default:
			return null;
		}
	}
	public void setConnection(MovingDirection direction, Connection connection) {
		switch(direction) {
		case Up:
			connectionUp = connection;
			break;
		case Down:
			connectionDown = connection;
			break;
		default:
			return;
		}
	}
	public Orientation orientation() {
		return orientation;
	}
	public MovingDirection direction(Connection connection) {
		if (connection == connectionUp)
			return MovingDirection.Up;
		if (connection == connectionDown)
			return MovingDirection.Down;
		return null;
	}
	public Path(int length, Point down, Orientation orientation) {
		this.paint = new Paint(Constants.pathPaint);
		this.length = length;
		this.orientation = orientation;
		this.down = down;
		this.up = new Point(down);
		switch(orientation) {
		case horizontal:
			this.up.x += length/5;
			break;
		case vertical:
			this.up.y -= length/5;
		}
	}
	
	private Point down;
	private Point up;
	public Point down() {
		return down;
	}
	public Point up() {
		return up;
	}
	public Point extremity(MovingDirection direction) {
		switch(direction) {
		case Down:
			return down;
		default:
			return up;
		}
	}
	
	@Override
	public void draw(Canvas canvas, int width, int height) {
	}

	@Override
	public void drawBackground(Canvas canvas, int width, int height) {
		int upColor;
		int downColor;
		switch(orientation) {
		case horizontal:
			upColor = (connectionUp.leftOpened() ? Color.GREEN : Color.RED);
			downColor = (connectionDown.rightOpened() ? Color.GREEN : Color.RED);
			break;
		default:
			upColor = (connectionUp.downOpened() ? Color.GREEN : Color.RED);
			downColor = (connectionDown.upOpened() ? Color.GREEN : Color.RED);
			break;
		}
		int realUpX = (up.x*width)/100;
		int realUpY = (up.y*height)/100;
		int realDownX = (down.x*width)/100;
		int realDownY = (down.y*height)/100;
		paint.setShader(new LinearGradient(realUpX, realUpY, realDownX, realDownY, upColor, downColor, Shader.TileMode.REPEAT));
		canvas.drawLine(realDownX, realDownY, realUpX, realUpY, paint);
	}
	@Override
	public void drawForeground(Canvas canvas, int width, int height) {
		// TODO Auto-generated method stub
		
	}

}
