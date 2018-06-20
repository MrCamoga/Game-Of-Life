package com.camoga.gol;

public class GameOfLife {

	private Level level;
	
	public GameOfLife(boolean windowed) {
		if(windowed) new Window(this, 100, 100);
		level = new Level(100, 100);
	}
	
	public void tick() {
		level.tick();
	}
	
	public int[] render() {		
		return level.getPixels();
	}
	
	public static void main(String[] args) {
		new GameOfLife(true);
	}
}