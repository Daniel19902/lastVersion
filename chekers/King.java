
/**
 * Write a description of class King here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class King extends Ficha{
    private Triangle king;
    
    public King(int x, int y, String color){
        super(x, y, new Circle(x, y, color));
        king = new Triangle();
    }
    
    public void makeVisible(){
        king.makeVisible();
    }
    
    public void shift(int top, int right,int moveTopOrDown, int moveLeftOrRigth){
        moveAXyMoverAY(top,right);
        setRow(getRow()+moveTopOrDown);
        setColumn(getColumn()+moveLeftOrRigth);
        king.moveAXyMoverAY(top,right);
    }
   
}
