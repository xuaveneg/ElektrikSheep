package com.game.elektriksheep.views;

import java.util.LinkedList;
import com.game.elektriksheep.R;

import com.game.elektriksheep.core.LevelThread;
import com.game.elektriksheep.utils.Constants;
import com.game.elektriksheep.utils.MovingDirection;
import com.game.elektriksheep.utils.DrawableObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class LevelView extends SurfaceView implements SurfaceHolder.Callback {
	
	public LevelView(Context context) {
		super(context);
		getHolder().addCallback(this);
	}
	
	public LevelView(Context context, AttributeSet attrs) {
		super(context, attrs);
		getHolder().addCallback(this);
	}
	
	public LevelView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		getHolder().addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		
	}
	
	LevelThread level;
	
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// Initialize state
		level = new LevelThread(0, this);
		level.start();
	}
	
	static private LinkedList<DrawableObject> toDraw = new LinkedList<DrawableObject>();
	static public void addDrawableObject(DrawableObject drawable) {
		toDraw.add(drawable);
	}
	
	public void initializeRender() {
		SurfaceHolder holder = getHolder();
		Canvas canvas = holder.lockCanvas();
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
		canvas.drawBitmap(background, null, new Rect(0, 0, width, height), null);
		for (DrawableObject drawable : toDraw) {
			drawable.drawBackground(canvas, width, height);
		}
		holder.unlockCanvasAndPost(canvas);
	}
	
	public void render() {
		SurfaceHolder holder = getHolder();
		Canvas canvas = holder.lockCanvas();
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
		canvas.drawBitmap(background, null, new Rect(0, 0, width, height), null);
		for (DrawableObject drawable : toDraw) {
			drawable.drawBackground(canvas, width, height);
		}
		for (DrawableObject drawable : toDraw) {
			drawable.draw(canvas, width, height);
		}
		for (DrawableObject drawable : toDraw) {
			drawable.drawForeground(canvas, width, height);
		}
		canvas.drawText("Remaining "+(level.nSheep()-level.nEndedSheep())+" Sheep", 0, height, Constants.defaultPaint);
		holder.unlockCanvasAndPost(canvas);
	}	
	
	float startX;
	float startY;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		switch(action) {
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			startY = event.getY();
			return true;
		case MotionEvent.ACTION_UP:
			float deltaX = event.getX() - startX;
			float deltaY = event.getY() - startY;
			if (Math.abs(deltaX) + Math.abs(deltaY) < 100)
				level.clickDone((int)((event.getX()*100)/getWidth()), (int)((event.getY()*100)/getHeight()));
			if (deltaY > 0)
				level.swipeDone(MovingDirection.Down);
			if (deltaY < 0)
				level.swipeDone(MovingDirection.Up);
			return true;
		}
		return super.onTouchEvent(event);
	}
}
