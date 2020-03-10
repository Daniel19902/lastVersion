import java.awt.*;

/**
 * A triangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Triangle extends Figura{
    private int height;
    private int width;

    /**
     * Create a new triangle at default position with default color.
     */
    public Triangle(){
        super();
        height = 20;
        width = 20;
    }
    
    public Triangle(int x, int y, String color){
        super(x,y,"blue");
        this.height = 20;
        this.width = 20;
    }
    
    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidht must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }

    /*
     * Draw the triangle with current specifications on screen.
     */
    public void draw(){
        if(isVisible()) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { getXPosition(), getXPosition() + (width/2), getXPosition() - (width/2) };
            int[] ypoints = { getYPosition(), getYPosition() + height, getYPosition() + height };
            canvas.draw(this, getColor(), new Polygon(xpoints, ypoints, 3));
            canvas.wait(10);
        }
    }

}
