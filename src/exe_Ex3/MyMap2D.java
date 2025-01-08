package exe_Ex3;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//ID1: 207565573 ID2: 207492315
/**
 * This class implements the Map2D interface.
 * You should change (implement) this class as part of Ex3. */
public class MyMap2D implements Map2D{
	private int[][] _map;

	public MyMap2D(int w, int h) {init(w,h);}
	public MyMap2D(int size) {this(size,size);}

	public MyMap2D(int[][] data) { 
		this(data.length, data[0].length);
		init(data);
	}
	@Override
	public void init(int w, int h) {
		_map = new int[w][h];

	}
	@Override
	public void init(int[][] arr) {
		init(arr.length,arr[0].length);
		for(int x = 0;x<this.getWidth()&& x<arr.length;x++) {
			for(int y=0;y<this.getHeight()&& y<arr[0].length;y++) {
				this.setPixel(x, y, arr[x][y]);
			}
		}
	}
	@Override
	public int getWidth() {return _map.length;}
	@Override
	public int getHeight() {return _map[0].length;}
	@Override
	public int getPixel(int x, int y) { return _map[x][y];}
	@Override
	public int getPixel(Point2D p) { 
		return this.getPixel(p.ix(),p.iy());
	}

	public void setPixel(int x, int y, int v) {_map[x][y] = v;}
	public void setPixel(Point2D p, int v) { 
		setPixel(p.ix(), p.iy(), v);
	}

	public static int deltaX(Point2D p1, Point2D p2) {
		return p2.ix() - p1.ix();
	}

	public static int deltaY(Point2D p1, Point2D p2) {
		return p2.iy() - p1.iy();
	}

	@Override
	public void drawSegment(Point2D p1, Point2D p2, int v) {
		Point2D source = null, dest = null, addingPoint = null;
		//Find the least steep slope
		if (Math.abs(deltaY(p1, p2)) <= Math.abs(deltaX(p1, p2))) {
			source = p1.ix() < p2.ix() ? p1 : p2;
			dest = source.equals(p1) ? p2 : p1;
			double slope = (double)deltaY(source, dest) / (double)deltaX(source, dest);
			addingPoint = new Point2D(1, slope);
		}
		else {
			source = p1.iy() < p2.iy() ? p1 : p2;
			dest = source.equals(p1) ? p2 : p1;
			double slope = (double)deltaX(source, dest) / (double)deltaY(source, dest);
			addingPoint = new Point2D(slope, 1);
		}
		//setting the closest pixel to the slope.
		while(!source.close2equals(dest, 0.1)) {
			System.out.println(source.toString());
			setPixel(source.ix(), source.iy(), v);
			source.move(addingPoint);
		}
		setPixel(p2, v);
	}

	@Override
	public void drawRect(Point2D p1, Point2D p2, int col) {
		//Find all the quadrilaterals of the Rect.
		int minX = Math.min(p1.ix(), p2.ix()), maxX = Math.max(p1.ix(), p2.ix());
		int minY = Math.min(p1.iy(), p2.iy()), maxY = Math.max(p1.iy(), p2.iy());
		//seting all the pixels between the quadrilaterals.
		for (int i = minX; i <= maxX; ++i)
			for (int j = minY; j <= maxY; ++j)
				setPixel(i, j, col);
	}

	@Override
	public void drawCircle(Point2D p, double rad, int col) {
		int M = getWidth(), N = getHeight();
		//set all the pixles in the map that there distance to p is shorter from rad.	
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (p.distance(new Point2D(i, j)) <= rad) {
					setPixel(i, j, col);
				}
			}
		}
	}

	@Override
	public int fill(Point2D p, int new_v) {
		//find the int vulue of p(x,y) 
		return fill(p.ix(),p.iy(),new_v );
	}


	@Override
	public int fill(int x, int y, int new_v) {
		Queue <Point2D> lary = new LinkedList<>();
		int col = this.getPixel(x, y);
		//stop if u dount have pixels in original color to paint.	
		while (this.getPixel(x, y) == col || !lary.isEmpty()) {
			// If a pixel is in the original color then color it,
			// and put all its neighbors in the original color into the Queue. 
			if(this.getPixel(x, y)== col) {
				setPixel(x,y, new_v);}

			if (y+1<_map.length&& this.getPixel(x, y+1)==col &&!lary.contains(new Point2D(x,y+1))){
				lary.add(new Point2D(x,y+1));}

			if (y-1>=0&&this.getPixel(x, y-1)==col &&!lary.contains(new Point2D(x,y-1))){
				lary.add(new Point2D(x,y-1));}

			if (x+1<_map.length&&this.getPixel(x+1, y)==col &&!lary.contains(new Point2D(x+1,y))){
				lary.add(new Point2D(x+1,y));}

			if (x-1>=0&&this.getPixel(x-1, y)==col &&!lary.contains(new Point2D(x-1,y))){
				lary.add(new Point2D(x-1,y));}
			// check the next neighbor
			if (!lary.isEmpty()) {
				x= lary.peek().ix();
				y= lary.poll().iy();
			}}
		return 0;		
	}
	
	private boolean SPSteps (int[][] ariel,int R) {
		//  if the pixel nigbers are in the vulue of "-1"'
		//	and replace it with the distance from p1.
		boolean dest = false;
		for (int i = 0; i < ariel.length; i++) {
			for (int j = 0; j <ariel.length; j++) {
				if (ariel[i][j] == R) {
					if(j+1 < ariel.length && ariel[i][j+1]== -1) { 
						ariel[i][j+1] = R+1;
						dest =  true;
					}
					if(j-1 >= 0 && ariel[i][j-1]== -1) {
						ariel[i][j-1] = R+1;
						dest =  true;
					}
					if(i+1 < ariel.length && ariel[i+1][j]== -1) {
						ariel[i+1][j] = R+1;
						dest =  true;
					}
					if(i-1 >= 0 && ariel[i-1][j]== -1) {
						ariel[i-1][j] = R+1;
						dest =  true;
					}
				}
			}
		}
		return dest;
	}

	private Point2D SPGoBack ( int[][] ariel, Point2D B) {
		// chacking who is the closest nigbern to p1 and send it back.
		Point2D dest = null;
		int BR = ariel[B.ix()][B.iy()]; 

		if(B.iy()-1 >= 0 && ariel[B.ix()][B.iy()-1]== BR -1) {
			dest = new Point2D(B.ix(), B.iy()-1);
		}else
			if((B.iy()+1 < getHeight()) && (ariel[B.ix()][B.iy()+1])== (BR -1)) {
				dest = new Point2D(B.ix(), B.iy()+1);
			}else
				if(B.ix()-1 >= 0 && (ariel[B.ix()-1][B.iy()]== BR -1)) {
					dest = new Point2D(B.ix()-1, B.iy());
				}else
					if(B.ix()+1 < getWidth() && ariel[B.ix()+1][B.iy()]== BR -1) {
						dest = new Point2D(B.ix()+1, B.iy());

					}
		return dest;
	}

	/**
	 *
	 */
	@Override
	public Point2D[] shortestPath(Point2D p1, Point2D p2) {
		// TODO Auto-generated method stub
		//setting a map clone and prevanting errors.
		Point2D[]dest = null;
		int[][] ariel = new int[getHeight()][getWidth()];
		if (!(p1.ix()==p2.ix()&& p1.iy()== p2.iy()) && getPixel(p1)== getPixel(p2)) {
			//mapping all the map cloun for blocks and open ways
			for (int i = 0; i < this.getWidth(); i++) {
				for (int j = 0; j < this.getHeight(); j++) {
					if (getPixel(i,j) != getPixel(p1)) {
						ariel[i][j] = -2;
					}else 
						ariel[i][j] = -1;
				}
			}
			// chacking the distance of every pixel frop p1.
			ariel[p1.ix()][p1.iy()] = 0;
			for (int R = 0;ariel[p2.ix()][p2.iy()] == -1; R++) {
				if (!SPSteps(ariel,R)) {
				}}
			// find the shortest path from p2 - back to p1		
			if(ariel[p2.ix()][p2.iy()]!= -1) {
				int RMax = ariel[p2.ix()][p2.iy()];
				dest = new Point2D[RMax];
				dest[0] = p2;
				for (int i = 1; i < RMax ; i++) {
					dest[i] = SPGoBack(ariel,dest [i-1]);
				}
			}
		}
		return dest;
	}


	public int shortestPathDist(Point2D p1, Point2D p2) {
		// TODO Auto-generated method stub
		Point2D [] way = shortestPath (p1, p2);
		return way.length-1;
	}
	
	// painting pixels in white
	void killCell(int[][] grid, int x, int y) {
		grid[x][y] = WHITE;
	}
	// painting pixels in black
	void liveCell(int[][] grid, int x, int y) {
		grid[x][y] = BLACK;
	}
	// checking if the pixel color is not white
	boolean isCellAlive(int[][] grid, int x, int y) {
		return grid[x][y] != WHITE;
	}

	@Override
	public void nextGenGol() {
		int M = getWidth(), N = getHeight();
		int[][] previos = _map.clone();
		int[][] dest = new int[M][N];
		for (int l = 0; l < M; l++) {
			for (int m = 0; m < N; m++) {
	// finding no Of Neighbours that arent white.
				int aliveNeighbours = 0;
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						if (i == 0 && j == 0) continue;
						if ((l + i >= 0 && l + i < M) && (m + j >= 0 && m + j < N)) {
							aliveNeighbours += isCellAlive(previos, l + i, m + j) ? 1 : 0;
						}}}
				//game of life ruls
				if (isCellAlive(previos, l, m) && aliveNeighbours < 2) {
					killCell(dest, l, m);
				}
				else if (isCellAlive(previos, l, m) && aliveNeighbours > 3) {
					killCell(dest, l, m);
				}
				else if (!isCellAlive(previos, l, m) && aliveNeighbours == 3) {
					liveCell(dest, l, m);
				} else {
					dest[l][m] = previos[l][m];
				}
			}
		}
		// update the map with the new generation
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!isCellAlive(dest, i, j)) {
					this.setPixel(i, j, WHITE);
				} else {
					this.setPixel(i, j, BLACK);
				}
			}
		}
	}


	@Override
	public void fill(int c) {
		for(int x = 0;x<this.getWidth();x++) {
			for(int y = 0;y<this.getHeight();y++) {
				this.setPixel(x, y, c);
			}
		}

	}

}
