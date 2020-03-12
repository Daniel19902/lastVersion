import javax.swing.JOptionPane;
import java.util.ArrayList;
/**
 * Write a description of class Ficha here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Ficha
{
    private int row;
    private int column;
    private boolean seleccion;
    private boolean isWhite;
    private Figura ficha;
    private boolean isKing;
    
    public Ficha(){
        seleccion = false;
        isWhite = false;
        isKing = false;
    }
    
    public Ficha(int row, int column, Figura f, boolean king){
        this();
        this.row = row;
        this.column = column;
        this.isKing = king;
        this.ficha = f;
    }
    
    public void moveAXyMoverAY(int moveX,int moveY){
        ficha.moveHorizontal(moveX);
        ficha.moveVertical(moveY);
    }
    
    public boolean isKing(){
        return this.isKing;
    }
    
    public void setRow(int row){
        this.row = row;
    }
    
    public void setColumn(int column){
        this.column = column;
    }
    
    
    public void darColor(boolean color){
        this.isWhite = color;
    }
    
    public boolean getColorBoolenao(){
        return this.isWhite;
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
    
    public abstract void shift(int top, int right,int moveTopOrDown, int moveLeftOrRigth);
    
}
