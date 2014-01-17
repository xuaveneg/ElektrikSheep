package com.game.elektriksheep.utils;

import android.graphics.Paint;
import android.graphics.Paint.Style;

public class Constants {
	static public Paint defaultPaint;
	static public Paint pathPaint;
	static public Paint textPaint;
	static public Paint defaultPaintUnfilled;
	static public Paint centerPlacePaint;
	static public void initialize() {
		defaultPaint = new Paint();
		defaultPaint.setColor(0xFF000000);
		defaultPaintUnfilled = new Paint(defaultPaint);
		defaultPaintUnfilled.setStyle(Style.STROKE);
		defaultPaintUnfilled.setStrokeWidth(3);
		pathPaint = new Paint(defaultPaint);
		pathPaint.setStrokeWidth(5);
		textPaint = new Paint(defaultPaint);
		centerPlacePaint = new Paint();
		centerPlacePaint.setColor(0xFF88421D);
	}
}
