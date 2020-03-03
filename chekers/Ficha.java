import javax.swing.JOptionPane;
import java.util.ArrayList;
/**
 * Write a description of class Ficha here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ficha
{
    private Circle ficha;
    private int row;
    private int column;
    private boolean color;
    private boolean seleccion;
    private boolean king;
    public Ficha(){
        row = 0;
        column = 0;
        seleccion = false;
        color = false;
        king = false;
    }
    
    public void getKing(){
        this.king = true;
    }
    
    public Ficha(int x, int y, String color){
        ficha= new Circle(x, y, color);
    }
    
    public void darColor(boolean color){
        this.color = color;
    }
    
    public boolean getColorBoolenao(){
        return this.color;
    }
    
    public void setFicha(int row, int column){
        this.row = row;
        this.column = column;
    }
    
    public void setSeleccion(int row, int column){
        this.row = row;
        this.column = column;
    }

    public void makeVisible(){
        ficha.makeVisible();
    }
    
    public void makeInvisible(){
        ficha.makeInvisible();
    }
    
    public void changeColor(String color){
        ficha.changeColor(color);
    }
    
    public void moveAXyMoverAY(int moveX,int moveY){
        ficha.moveHorizontal(moveX);
        ficha.moveVertical(moveY);
    }
    
    public int getRow(){
        return this.row;
    }
    
    public int getColumn(){
        return this.column;
    }
    
    public void setPossion(boolean seleccion){
        this.seleccion = seleccion;
    }
    
    public boolean getSelect(){
        return this.seleccion;
    }
    
    public String getColor(){
        return ficha.getColor();
    }
    
    public void remove (){
        ficha.makeInvisible();
    }
    
    public void shift(int top, int right,int moveTopOrDown, int moveLeftOrRigth){
        moveAXyMoverAY(top,right);
        this.row = row+moveTopOrDown;
        this.column = column+moveLeftOrRigth;
    }
    
}
