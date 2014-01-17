package com.game.elektriksheep.utils;

public enum Color {
	None  (50,50,0xFF000000),
	Blue  (55,45,0xFF0000FF),
	Green (45,55,0xFF00FF00),
	Yellow(60,40,0xFF00FFFF),
	Red   (40,60,0xFFFF0000),
	White (50,50,0xFF000000),
	Black (30,70,0xFFFFFFFF);
	private int speed;
	private int agressivity;
	private int code;
	public int speed() {
		return speed;
	}
	public int aggressivity() {
		return agressivity;
	}
	public int code() {
		return code;
	}
	private Color(int speed, int agressivity, int code) {
		speed = Math.max(Math.min(100, speed), 0);
		agressivity = Math.max(Math.min(100, agressivity), 0);
		this.speed = speed;
		this.agressivity = agressivity;
		this.code = code;
	}
}
