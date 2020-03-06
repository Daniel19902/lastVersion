import java.awt.*;

/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes (Modified)
 * @version 1.0  (15 July 2000)()
 */


 
public class Rectangle extends Figura{

    private int height;
    private int width;
    
    /**
     * Create a new rectangle at default position with default color.
     */
    public Rectangle(){
        super();
        height = 30;
        width = 40;
    }
    
    public Rectangle(int height,int width,int xPosition,int yPosition,String color){
        super(xPosition,yPosition,color);
        this.height = height;
        this.width = width;
    }
    
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }
    
    public void draw() {
        if(isVisible()) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, getColor(),
                new java.awt.Rectangle(getXPosition(), getYPosition(), width, height));
            canvas.wait(10);
        }
    }
}

