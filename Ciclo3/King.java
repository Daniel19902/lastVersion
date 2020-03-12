
/**
 * Write a description of class King here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class King extends Ficha{
    private Figura king;
    
    public King(int x, int y, String color, Figura t){
        super(x, y, new Circle(x, y, color),true);
        this.king = t;
    }
    
    public void shift(int top, int right,int moveTopOrDown, int moveLeftOrRigth){
        king.moveAXyMoverAY(top+15,right+3);
        moveAXyMoverAY(top,right);
        setRow(getRow()+moveTopOrDown);
        setColumn(getColumn()+moveLeftOrRigth);
        
    }
   
}
