package com.game.elektriksheep.core;

import com.game.elektriksheep.utils.MovingDirection;
import com.game.elektriksheep.utils.Place;

public class Connection {
	private Place up;
	private Place down;
	private Place right;
	private Place left;
	private boolean upOpened;
	private boolean downOpened;
	private boolean rightOpened;
	private boolean leftOpened;
	private int size;
	private Place opened[] = new Place[2];
	private int openedSize;
	public int size() {
		return size;
	}
	public Place up() {
		return up;
	}
	public Place down() {
		return down;
	}
	public Place right() {
		return right;
	}
	public Place left() {
		return left;
	}
	public int openedSize() {
		return openedSize;
	}
	public boolean upOpened() {
		return upOpened;
	}
	public boolean downOpened() {
		return downOpened;
	}
	public boolean rightOpened() {
		return rightOpened;
	}
	public boolean leftOpened() {
		return leftOpened;
	}
	public Place otherOpened(Place firstPlace) {
		if (openedSize != 2)
			return null;
		if (firstPlace == opened[0])
			return opened[1];
		if (firstPlace == opened[1])
			return opened[0];
		return null;
	}
	private void updateOpened() {
		int index = 0;
		if (up != null && upOpened && index < 2)
			opened[index++] = up;
		if (down != null && downOpened && index < 2)
			opened[index++] = down;
		if (left != null && leftOpened && index < 2)
			opened[index++] = left;
		if (right != null && rightOpened && index < 2)
			opened[index++] = right;
	}
	public void toggleUp() {
		if (size <= 2)
			return;
		if (up == null)
			return;
		upOpened = !upOpened;
		if (upOpened) {
			if (openedSize < 2)
				opened[openedSize] = up;
			++openedSize;
		} else {
			updateOpened();
			--openedSize;
		}
	}
	public void toggleDown() {
		if (size <= 2)
			return;
		if (down == null)
			return;
		downOpened = !downOpened;
		if (downOpened) {
			if (openedSize < 2)
				opened[openedSize] = down;
			++openedSize;
		} else {
			updateOpened();
			--openedSize;
		}
	}
	public void toggleRight() {
		if (size <= 2)
			return;
		if (right == null)
			return;
		rightOpened = !rightOpened;
		if (rightOpened) {
			if (openedSize < 2)
				opened[openedSize] = right;
			++openedSize;
		} else {
			updateOpened();
			--openedSize;
		}
	}
	public void toggleLeft() {
		if (size <= 2)
			return;
		if (left == null)
			return;
		leftOpened = !leftOpened;
		if (leftOpened) {
			if (openedSize < 2)
				opened[openedSize] = left;
			++openedSize;
		} else {
			updateOpened();
			--openedSize;
		}
	}
	public Connection(Place up, Place right, Place down, Place left) {
		this.up = up;
		this.down = down;
		this.right = right;
		this.left = left;
		this.size = 0;
		if (up != null) {
			++this.size;
			if (up instanceof Path)
				((Path)up).setConnection(MovingDirection.Down, this);
		}
		if (down != null) {
			++this.size;
			if (down instanceof Path)
				((Path)down).setConnection(MovingDirection.Up, this);
		}
		if (left != null) {
			++this.size;
			if (left instanceof Path)
				((Path)left).setConnection(MovingDirection.Up, this);
		}
		if (right != null) {
			++this.size;
			if (right instanceof Path)
				((Path)right).setConnection(MovingDirection.Down, this);
		}
		boolean opened = false;
		this.openedSize = 0;
		if (size <= 2) {
			openedSize = size;
			opened = true;
			int index = 0;
			if (up != null)
				this.opened[index++] = up;
			if (down != null)
				this.opened[index++] = down;
			if (right != null)
				this.opened[index++] = right;
			if (left != null)
				this.opened[index++] = left;
		}
		this.upOpened =  opened;
		this.downOpened = opened;
		this.rightOpened = opened;
		this.leftOpened = opened;
	}
}
