package com.game.elektriksheep.utils;

public enum MovingDirection {
	None,
	Up,
	Down;
	public MovingDirection inverse() {
		switch(this) {
		case Up:
			return Down;
		case Down:
			return Up;
		default:
			return None;
		}
	}
}
