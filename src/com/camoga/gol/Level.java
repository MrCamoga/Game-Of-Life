package com.camoga.gol;

/**
 * Level is cyclic like if it were the surface of a torus
 *
 */
public class Level {
	
	private int steps = 0;
	
//	enum BorderType {
//		ALIVE(1), DEAD(0);
//		
//		private int state;
//		
//		BorderType(int state) {
//			this.state = state;
//		}
//		
//		public int getState() {
//			return state;
//		}
//	}
	
	private int[] pixels;
	private int[] newpixels;
	private int width, height;
//	private BorderType type = BorderType.ALIVE;
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;

		pixels = new int[width*height];
		newpixels = new int[width*height];
		
		pixels[5] = 1;
		pixels[6+width] = 1;
		pixels[7+width] = 1;
		pixels[5+2*width] = 1;
		pixels[6+2*width] = 1;
	}
	
	public void tick() {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				int numOfNeighbours = countAliveNeighbours(x, y, 1);
				switch (getState(x,y)) {
					case 0:
						if(numOfNeighbours == 3) newpixels[x+y*width] = 1;
						else newpixels[x+y*width] = 0;
						break;
					case 1:
						if(numOfNeighbours != 2 && numOfNeighbours != 3) newpixels[x+y*width] = 0;
						else newpixels[x+y*width] = 1;
						break;
				}
			}
		}
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = newpixels[i];
		}
		steps++;
	}
	
	public int countAliveNeighbours(int xo, int yo, int SIZE) {
		int total = 0;
		for(int y = -SIZE; y <= SIZE; y++) {
			for(int x = -SIZE; x <= SIZE; x++) {
				if(x==0&&y==0) continue;
				total += getState(xo+x, yo+y);
			}
		}
		
		return total;
	}
	
	public int getState(int x, int y) {
		return pixels[(x+width)%width + ((y+height)%height)*width];
	}
	
	public int[] getPixels() {
		return pixels;
	}
	
}
