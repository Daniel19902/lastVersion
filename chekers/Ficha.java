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
    public Ficha(){
        row = 0;
        column = 0;
        seleccion = false;
        color = false;
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

    /**
    public void jump(boolean top, boolean rigth, Ficha[][] listaDeFichas, Rectangle[][] casillas, int row , int column, boolean desSelect){
        Ficha mover = listaDeFichas[row][column];
        int moveTopOrDown = darPosicionDeMovimientoJump(top);
        int moveLeftOrRigth = darPosicionDeMovimientoJump(rigth);
        boolean verificarMovimiento = verificarMovimientoJump(top, rigth, mover.color, moveTopOrDown, moveLeftOrRigth, listaDeFichas, row, column);
        // movimiento
        if (verificarMovimiento){
            Rectangle casillaDestino = casillas[row+moveTopOrDown][column+moveLeftOrRigth];
            mover.moveAXyMoverAY(casillaDestino.getPositionX(),casillaDestino.getPositionY());
            listaDeFichas[row+moveTopOrDown][column+moveLeftOrRigth] = mover;
            if (desSelect)listaDeFichas[row+moveTopOrDown][column+moveLeftOrRigth].setPossion(false);
            listaDeFichas[row][column] = null;
            if (desSelect){
                if (listaDeFichas[row+moveTopOrDown][column+moveLeftOrRigth].color)listaDeFichas[row+moveTopOrDown][column+moveLeftOrRigth].changeColor("green");
                else listaDeFichas[row+moveTopOrDown][column+moveLeftOrRigth].changeColor("red");
            }
        }else JOptionPane.showMessageDialog(null,"movimiento no permitido");
    }
    */
    public int darPosicionDeMovimientoJump(boolean mover){
        int moveTopOrRigth = 0;
        if (mover){
            moveTopOrRigth = 2;
        }else{
            moveTopOrRigth = -2;
        }
        return moveTopOrRigth;
    }
    /**
    private boolean verificarMovimientoJump(boolean top, boolean right, boolean color, int moveTopOrDown, int moveLeftOrRigth, Ficha[][] listaDeFichas, int row , int column){
        boolean confirmarMovimiento = true;
        int matarTop = darPosicionDeMovimiento(top);
        int matarRigth = darPosicionDeMovimiento(right);
        if (listaDeFichas[row+matarTop][column+matarRigth] != null && listaDeFichas[row][column].color && listaDeFichas[row+moveTopOrDown][column+moveLeftOrRigth] == null){
            listaDeFichas[row+matarTop][column+matarRigth].makeInvisible();
            listaDeFichas[row+matarTop][column+matarRigth] = null;
        }else if (listaDeFichas[row+matarTop][column+matarRigth] != null && !listaDeFichas[row][column].color && listaDeFichas[row+moveTopOrDown][column+moveLeftOrRigth] == null){
            listaDeFichas[row+matarTop][column+matarRigth].makeInvisible();
            listaDeFichas[row+matarTop][column+matarRigth] = null;
        }else confirmarMovimiento = false;
        if (color && moveTopOrDown == -2)confirmarMovimiento = false;
        else if (!color && moveTopOrDown == 2)confirmarMovimiento = false;
        else if (listaDeFichas[row+moveTopOrDown][column+moveLeftOrRigth] != null)confirmarMovimiento = false;
        return confirmarMovimiento;
    }
    */
}
