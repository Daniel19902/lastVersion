
/**
 * Write a description of class King here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class King
{
    private Triangle king;
    private int row;
    private int column;
    
    public King(int x, int y){
        king = new Triangle(x, y);
    }
    
    public void makeVisible(){
        king.makeVisible();
    }
    
    public void getRow(int row){
        this.row = row;
    }
    
    public void getColumn(int column){
        this.row = column;
    }
}
