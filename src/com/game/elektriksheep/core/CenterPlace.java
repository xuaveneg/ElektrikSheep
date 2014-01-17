package com.game.elektriksheep.core;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Canvas.VertexMode;
import android.graphics.Point;

import com.game.elektriksheep.utils.Constants;
import com.game.elektriksheep.utils.EndPlace;
import com.game.elektriksheep.utils.MovingDirection;

public class CenterPlace extends EndPlace {
	private Path up;
	private Path down;
	public Path path(MovingDirection direction) {
		if (direction == MovingDirection.Down)
			return down;
		if (direction == MovingDirection.Up)
			return up;
		return null;
	}
	private CenterPlace(Path up, Path down) {
		this.up = up;
		this.down = down;
	}
	static private CenterPlace instance = null;
	static public void initialize(Path up, Path down) {
		if (instance == null)
			instance = new CenterPlace(up, down);
	}
	static public CenterPlace instance() {
		return instance;
	}
	@Override
	public void draw(Canvas canvas, int width, int height) {
	}
	@Override
	public void drawBackground(Canvas canvas, int width, int height) {
	}
	@Override
	public void addSheep(Sheep sheep) {
	}
	@Override
	public void drawForeground(Canvas canvas, int width, int height) {
		Point topLeft = new Point((width*40)/100, (height*40)/100);
		int thisWidth = (width*20)/100;
		int thisHeight = (height*20)/100;
		int color = 0xFF000000 | (LevelThread.lastNuclearedColor.code()*Math.max(1-1000/LevelThread.elapsedLastNuclearedTime, 0));
		float verts[] = {
				topLeft.x+thisWidth/4, topLeft.y+thisHeight/4,
				topLeft.x-thisWidth/4, topLeft.y,
				topLeft.x-thisWidth/4, topLeft.y+thisHeight/2,
				topLeft.x+thisWidth/4, topLeft.y+3*thisHeight/4,
				topLeft.x-thisWidth/4, topLeft.y+thisHeight/2,
				topLeft.x-thisWidth/4, topLeft.y+thisHeight,
				
				topLeft.x+3*thisWidth/4, topLeft.y+thisHeight/4,
				topLeft.x+5*thisWidth/4, topLeft.y,
				topLeft.x+5*thisWidth/4, topLeft.y+thisHeight/2,
				topLeft.x+3*thisWidth/4, topLeft.y+3*thisHeight/4,
				topLeft.x+5*thisWidth/4, topLeft.y+thisHeight/2,
				topLeft.x+5*thisWidth/4, topLeft.y+thisHeight};
		int colors[] = {
				color, Color.TRANSPARENT, Color.TRANSPARENT,
				color, Color.TRANSPARENT, Color.TRANSPARENT,
				color, Color.TRANSPARENT, Color.TRANSPARENT,
				color, Color.TRANSPARENT, Color.TRANSPARENT,
				color, Color.TRANSPARENT, Color.TRANSPARENT,
				color, Color.TRANSPARENT, Color.TRANSPARENT,
				color, Color.TRANSPARENT, Color.TRANSPARENT,
				color, Color.TRANSPARENT, Color.TRANSPARENT
		};
		canvas.drawVertices(VertexMode.TRIANGLES, 24, verts, 0, null, 0, colors, 0, null, 0, 0, Constants.defaultPaint);
		canvas.drawRect(topLeft.x, topLeft.y, topLeft.x+thisWidth, topLeft.y+thisHeight, Constants.centerPlacePaint);
	}
}
