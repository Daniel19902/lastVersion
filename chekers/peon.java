
/**
 * Write a description of class peon here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class peon extends Ficha
{

    public peon(int x, int y, String color){
        super(x,y,new Circle(x, y, color));
    }
    
    public void shift(int top, int right,int moveTopOrDown, int moveLeftOrRigth){
        moveAXyMoverAY(top,right);
        setRow(getRow()+moveTopOrDown);
        setColumn(getColumn()+moveLeftOrRigth);
    }
    
}
