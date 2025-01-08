package exe_Ex3;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Image;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class MyMap2DTest {
	MyMap2D TMap = new MyMap2D(10);

	@Test
	void testDeltaX() {
		Point2D p1 = new Point2D(1, 2);
		Point2D p2 = new Point2D(3, 4);
		int expectedResult = 2;
		int actualResult = MyMap2D.deltaX(p1, p2);
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void testDeltaY() {
		
		Point2D p1 = new Point2D(1, 2);
		Point2D p2 = new Point2D(3, 4);
		int expectedResult = 2;
		int actualResult = MyMap2D.deltaY(p1, p2);
		assertEquals(expectedResult, actualResult);
	}


	@Test
	void testDrawSegment() {
		 Point2D p1 = new Point2D(1,2);
		    Point2D p2 = new Point2D(5,3);
		    int v = 3;
		    TMap.drawSegment(p1, p2, v);
  // Verify that the pixels along the segment are set to the correct color
		    int x = p1.ix();
		    int y = p1.iy();
		    while (!p1.close2equals(p2, 0.1)) {
		      assertEquals(v, TMap.getPixel(x, y));
		      x += (p1.ix() < p2.ix() ? 1 : -1);
		      y += (p1.iy() < p2.iy() ? 1 : -1);
		    }
		    assertEquals(v, TMap.getPixel(p2.ix(), p2.iy()));
// Verify that the surrounding pixels are not modified unless it's the segment path.
		    assertEquals(0, TMap.getPixel(p1.ix()+1, p1.iy()));
		    assertEquals(0, TMap.getPixel(p1.ix(), p1.iy()-1));
		    assertEquals(0, TMap.getPixel(p1.ix(), p1.iy()+1));
		    assertEquals(0, TMap.getPixel(p2.ix()+1, p2.iy()));
		    assertEquals(0, TMap.getPixel(p2.ix(), p2.iy()-1));
		    assertEquals(0, TMap.getPixel(p2.ix(), p2.iy()+1));
	}
	@Test
	void testDrawRect() {
		TMap.fill(Color.white.getRGB());
		Point2D p1 = new Point2D(2, 2);
		Point2D p2 = new Point2D(5, 5);
		int color = Color.BLACK.getRGB();
		TMap.drawRect(p1, p2, color);
// Check that the pixels within the rectangle have been set to the correct color
		for (int i = 2; i <= 5; i++) {
			for (int j = 2; j <= 5; j++) {
				assertEquals(color, TMap.getPixel(i, j));
	}}}

@Test
void testDrawCircle() {
	TMap.fill(Color.white.getRGB());
	 Point2D p = new Point2D(3,3);
	    double rad = 5;
	    int col = Color.black.getRGB();
	    TMap.drawCircle(p, rad, col);
	  // Verify that the pixels in the circle are set to the correct color
	    for (int i = 0; i < 10; i++) {
	      for (int j = 0; j < 10; j++) {
	        if (p.distance(new Point2D(i, j)) <= rad) {
	          // Verify that the pixel is set to the correct color
	          assertEquals(col, TMap.getPixel(i, j));
	        } else {
	          // Verify that the pixel is not modified
	          assertEquals(Color.white.getRGB(), TMap.getPixel(i, j));
	        }}}}
	
@Test
void testFillPoint2DInt() {
		    Point2D p = new Point2D(5,4);
		    TMap.setPixel(p.ix(), p.iy(), 5);
		    int new_v = 3;
		    int result = TMap.fill(p, new_v);
	// Test if the result is what we expected
		    if (result == new_v) {
		      System.out.println("Test passed");
		    } else {
		      System.out.println("Test failed");
		    }
  // Verify that the pixel value has been changed
		    assertEquals(new_v, TMap.getPixel(p.ix(), p.iy()));
		  }

@Test
void testFillIntIntInt() {
	TMap.fill(Color.white.getRGB());
		    int x = 1;
		    int y = 3;
		    TMap.setPixel(x, y, 5);
		    int new_v = 3;
		    TMap.fill(x, y, new_v);
		    // Verify that the pixel value has been changed
		    for (int i = 0; i < 10; i++) {
		    	for (int j = 0; j <10; j++) {
		    		assertEquals(new_v, TMap.getPixel(x, y));}}	
				}
				
@Test
void testShortestPath() {
	//Checks if it manages to pass a barrier that blocks all but one pixel of passage
	TMap.fill(Color.white.getRGB());
	Point2D p1 = new Point2D(0,0);
	Point2D p2 = new Point2D(9,0);
	Point2D r1 = new Point2D(4,0);
	Point2D r2 = new Point2D(5,8);
	int color = Color.BLACK.getRGB();
	TMap.drawRect(r1, r2, color);
	TMap.shortestPath(p1, p2);
	assertEquals(TMap.getPixel(p1),TMap.getPixel(p2));}
	


@Test
void testNextGenGol() {
	//Checks a predetermined move of 3 points that turn from row to column
	//according to the rules of the game
	TMap.fill(Color.white.getRGB());
	Point2D p1 = new Point2D(2, 3);
	Point2D p2 = new Point2D(2, 4) ;
	Point2D p3 = new Point2D(2, 5) ;
	TMap.setPixel(p1, Color.black.getRGB());
	TMap.setPixel(p2, Color.black.getRGB());
	TMap.setPixel(p3, Color.black.getRGB());
	TMap.nextGenGol();
	assertEquals(TMap.getPixel(p2), Color.black.getRGB());
	assertEquals(TMap.getPixel(1,4), Color.black.getRGB());
	assertEquals(TMap.getPixel(3,4), Color.black.getRGB());
}}

	
	
	
	
	
	
	
	
	