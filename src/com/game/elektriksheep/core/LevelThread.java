package com.game.elektriksheep.core;

import java.util.EnumMap;
import java.util.LinkedList;

import android.graphics.Point;

import com.game.elektriksheep.utils.Color;
import com.game.elektriksheep.utils.Constants;
import com.game.elektriksheep.utils.EndPlace;
import com.game.elektriksheep.utils.MovingDirection;
import com.game.elektriksheep.utils.Orientation;
import com.game.elektriksheep.utils.Place;
import com.game.elektriksheep.views.LevelView;

public class LevelThread extends Thread {
	private EnumMap<Color, NuclearPlant> nuclearPlant;
	private MovingDirection swipe;
	private Point click = new Point();
	private boolean clickDone;
	public boolean isPresent(Color color) {
		if (!nuclearPlant.containsKey(color))
			return false;
		return nuclearPlant.get(color) != null;
	}
	public void swipeDone(MovingDirection direction) {
		swipe = direction;
	}
	public void clickDone(int clickX, int clickY) {
		clickDone = true;
		click.x = clickX;
		click.y = clickY;
	}
	public LevelThread(int levelNumber, LevelView levelView) {
		super();
		// Give relevant informations to the new Thread
		this.levelNumber = levelNumber;
		this.levelView = levelView;
	}
	
	// Constants
	private LevelView levelView;
	// Runtime constants
	private Sheep waitingSheep;
	private int nNuclearedSheep;
	private int nEndedSheep;
	private LinkedList<Sheep> outSheep = new LinkedList<Sheep>();
	static private long lastLoopTime;
	static private long lastNuclearedTime;
	static public Color lastNuclearedColor;
	static public int elapsedLastNuclearedTime;
	// Level Defined constants
	private int levelNumber;
	private int nSheep;
	private int maxDelay;
	private int minDelay;
	private Color colors[];
	public int nSheep() {
		return nSheep;
	}
	public int nEndedSheep() {
		return nEndedSheep;
	}
	private LinkedList<Path> paths = new LinkedList<Path>();
	@Override
	public void run() {
		// Initialize State
		initialize();
		// Initialize Rendering
		levelView.initializeRender();
		while (nEndedSheep < nSheep) {
			// Input Done in LevelView
			// Update State
			update();
			// Render
			levelView.render();
		}
	}
	
	private void initialize() {
		Constants.initialize();
		// Level-specific state
		switch(levelNumber) {
		case 0:
			this.maxDelay = 10;
			this.minDelay = 5;
			this.nSheep = 5;
			break;
		case 1:
		case 2:
			this.maxDelay = 7;
			this.minDelay = 5;
			this.nSheep = 10;
			break;
		case 3:
			this.minDelay = 4;
			this.maxDelay = 6;
			this.nSheep = 15;
			break;
		case 4:
			this.minDelay = 4;
			this.maxDelay = 5;
			this.nSheep = 20;
			break;
		case 5:
			this.minDelay = 3;
			this.maxDelay = 5;
			this.nSheep = 25;
			break;
		case 6:
		case 7:
		case 8:
			this.minDelay = 2;
			this.maxDelay = 4;
			this.nSheep = 30;
			break;
		case 9:
			this.minDelay = 1;
			this.maxDelay = 3;
			this.nSheep = 40;
			break;
		case 10:
			this.minDelay = 1;
			this.maxDelay = 2;
			this.nSheep = 50;
			break;
		}
		Path centerPlaceDown = null;
		Path centerPlaceUp = null;
		switch(levelNumber) {
		case 10:
		case 9:
		case 8:
			break;
		case 7:
			break;
		case 6:
		case 5:
		case 4:
		case 3:
		case 2:
			break;
		case 1:
		case 0:
			colors = new Color[2];
			colors[0] = Color.Blue;
			colors[1] = Color.Green;
			NuclearPlant bluePlant = new NuclearPlant(Color.Blue, new Point(10, 45));
			NuclearPlant greenPlant = new NuclearPlant(Color.Green, new Point(80, 45));
			centerPlaceUp = new Path(50, new Point(50, 40), Orientation.vertical);
			Path centerPlaceUpThenLeft = new Path(100, new Point(30, 30), Orientation.horizontal);
			Path centerPlaceUpThenRight = new Path(100, new Point(50, 30), Orientation.horizontal);
			Path centerPlaceUpThenLeftThenDown = new Path(100, new Point(30, 50), Orientation.vertical);
			Path centerPlaceUpThenRightThenDown = new Path(100, new Point(70, 50), Orientation.vertical);
			Path toBluePlant = new Path(50, new Point(20, 50), Orientation.horizontal);
			Path toGreenPlant = new Path(50, new Point(70, 50), Orientation.horizontal);
			paths.add(centerPlaceUp);
			paths.add(centerPlaceUpThenLeft);
			paths.add(centerPlaceUpThenLeftThenDown);
			paths.add(centerPlaceUpThenRight);
			paths.add(centerPlaceUpThenRightThenDown);
			paths.add(toBluePlant);
			paths.add(toGreenPlant);
			new Connection(centerPlaceUp, null, null, null);
			new Connection(null, centerPlaceUpThenRight, centerPlaceUp, centerPlaceUpThenLeft);
			new Connection(null, centerPlaceUpThenLeft, centerPlaceUpThenLeftThenDown, null);
			new Connection(centerPlaceUpThenLeftThenDown, null, null, toBluePlant);
			new Connection(null, toBluePlant, null, bluePlant);
			new Connection(null, null, centerPlaceUpThenRightThenDown, centerPlaceUpThenRight);
			new Connection(centerPlaceUpThenRightThenDown, toGreenPlant, null, null);
			new Connection(null, greenPlant, null, toGreenPlant);
			break;
		}
		// General initialization
		CenterPlace.initialize(centerPlaceUp, centerPlaceDown);
		lastLoopTime = System.currentTimeMillis();
		lastNuclearedTime = lastLoopTime;
		lastNuclearedColor = Color.White;
		this.swipe = MovingDirection.None;
	}
	
	private void update() {
		long curLoopTime = System.currentTimeMillis();
		int elapsedTime = (int)(curLoopTime - lastLoopTime);
		lastLoopTime = curLoopTime;
		elapsedLastNuclearedTime = (int)(curLoopTime - lastNuclearedTime);
		// TODO : Check Collisions
		// Move Sheep
		LinkedList<Sheep> newOutSheep = new LinkedList<Sheep>();
		for(Sheep s : outSheep) {
			s.setPosition(s.position() + (s.color().speed()*elapsedTime)/1000);
			Path walkingPath = s.path();
			Connection crossingConnection = walkingPath.connection(s.direction());
			if (s.position() > walkingPath.length()) {
				s.setPosition(s.position() - walkingPath.length());
				if (crossingConnection.openedSize() != 2) {
					s.invertDirection();
				} else {
					Place otherOpened = crossingConnection.otherOpened(walkingPath);
					Path otherOpenedPath = (otherOpened instanceof Path ? (Path)otherOpened : null);
					if (otherOpenedPath == null) {// otherOpened not a Path
						++nEndedSheep;
						((EndPlace)otherOpened).addSheep(s);// Add Sheep to the end point
						s.end();
						continue;
					}
					s.setPath(otherOpenedPath);
					s.setDirection(otherOpenedPath.direction(crossingConnection).inverse());
				}
			}
			newOutSheep.add(s);
		}
		outSheep = newOutSheep;
		// Recognized swipe
		if ((waitingSheep != null) && (swipe != MovingDirection.None)) {
			Path goingTo = CenterPlace.instance().path(swipe);
			if (goingTo != null) {
				waitingSheep.setPath(goingTo);
				waitingSheep.setDirection(swipe);
				outSheep.add(waitingSheep);
				waitingSheep = null;
			}
		}
		swipe = MovingDirection.None;
		// Recognized click
		if (clickDone) {
			int lowestDistToExtremity = 40;
			Path lowestPath = null;
			MovingDirection lowestExtremity = MovingDirection.None;
			for (Path p : paths) {
				int toUp = Math.abs(p.up().x - click.x) + Math.abs(p.up().y - click.y);
				int toDown = Math.abs(p.down().x - click.x) + Math.abs(p.down().y - click.y);
				if (Math.min(toUp, toDown) < lowestDistToExtremity) {
					lowestDistToExtremity = Math.min(toUp, toDown);
					MovingDirection extremity = (toUp < toDown ? MovingDirection.Up : MovingDirection.Down);
					if (Math.abs(p.extremity(extremity).x - click.x) > Math.abs(p.extremity(extremity).y - click.y)) {
						if (p.extremity(extremity).x > click.x && p.connection(extremity).left() instanceof Path) {
							lowestPath = (Path)p.connection(extremity).left();
							lowestExtremity = MovingDirection.Up;
						} else if (p.connection(extremity).right() instanceof Path) {
							lowestPath = (Path)p.connection(extremity).right();
							lowestExtremity = MovingDirection.Down;
						}
					} else {
						if (p.extremity(extremity).y > click.y && p.connection(extremity).up() instanceof Path) {
							lowestPath = (Path)p.connection(extremity).up();
							lowestExtremity = MovingDirection.Down;
						} else if (p.connection(extremity).down() instanceof Path) {
							lowestPath = (Path)p.connection(extremity).down();
							lowestExtremity = MovingDirection.Up;
						}
					}
				}
			}
			if (lowestPath != null) {
				if (lowestPath.orientation() == Orientation.horizontal && lowestExtremity == MovingDirection.Up)
					lowestPath.connection(lowestExtremity).toggleLeft();
				if (lowestPath.orientation() == Orientation.horizontal && lowestExtremity == MovingDirection.Down)
					lowestPath.connection(lowestExtremity).toggleRight();
				if (lowestPath.orientation() == Orientation.vertical && lowestExtremity == MovingDirection.Up)
					lowestPath.connection(lowestExtremity).toggleDown();
				if (lowestPath.orientation() == Orientation.vertical && lowestExtremity == MovingDirection.Down)
					lowestPath.connection(lowestExtremity).toggleUp();
			}
		}
		clickDone = false;
		// Sheep Nucleared
		if ((curLoopTime - lastNuclearedTime > minDelay*1000) && (nNuclearedSheep < nSheep)) {// TODO : Find formula
			lastNuclearedColor = colors[(int)(Math.random()*colors.length)]; // TODO : Find formula
			lastNuclearedTime = curLoopTime;
			++nNuclearedSheep;
			if (waitingSheep != null) {
				++nEndedSheep;
				if (lastNuclearedColor.aggressivity() > waitingSheep.aggressivity())
					waitingSheep.end();
					waitingSheep = new Sheep(lastNuclearedColor);
			} else {
				waitingSheep = new Sheep(lastNuclearedColor);
			}
		}
	}
}
