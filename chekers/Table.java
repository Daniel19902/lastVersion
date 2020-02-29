import javax.swing.JOptionPane;
import java.util.ArrayList;
/**
 * Write a description of class Table here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Table
{
    private Rectangle[][] casillas;
    private Ficha[][] listaDeFichas;
    private int  tamaño;
    private int[] selecction;
    public Table(int width,int moverX, int moverY){
        tamaño = width;
        casillas = new Rectangle[width][width];
        listaDeFichas = new Ficha[width][width];
        table(width,moverX,moverY);
        selecction = new int[2];
    }
    public void table(int width, int moverX, int moverY){
        String colorActual =  "black";
        for (int i = 0; i < width; i++){
            for (int j = 0; j < width; j++  ){
                String color ="";
                if (width%2 == 0 && j ==0 && i>0)color = colorActual;
                else if (colorActual.equals("black"))color = "white";
                else if (colorActual.equals("white"))color = "black";
                casillas[i][j] = new Rectangle(30,30,moverX+30*j,moverY*i+30,color);
                casillas[i][j].makeVisible();
                colorActual = color;
            }
        }
    
    }

    public void makeVisible(){
            for (int i = 0; i < casillas.length; i++){
                for (int j = 0; j < casillas.length; j++){
                    casillas[i][j].makeVisible();
                }
            }
    }

    public void add(boolean white,boolean king ,int row, int column){
        if (white){
            if (listaDeFichas[row][column] == null){
                crearFicha(row,column,white);
            }else{
                JOptionPane.showMessageDialog(null,"ya hay una ficha en esa posición");
            }
        }else{
            if (listaDeFichas[row][column] == null){
                crearFicha(row,column,white);
            }else{
                JOptionPane.showMessageDialog(null,"ya hay una ficha en esa posición");
            }
        }
    }
        
    private void crearFicha(int row, int column, boolean white ){
        Rectangle asignarFicha = casillas[row][column];
        if (white){
            String color = "green";
            Ficha peon = new Ficha(asignarFicha.getPositionX(),asignarFicha.getPositionY(),color);
            peon.darColor(true);
            peon.setFicha(row,column);
            listaDeFichas[row][column] = peon;
            peon.makeVisible();
        }else{
            String color = "red";
            Ficha peon = new Ficha(asignarFicha.getPositionX(),asignarFicha.getPositionY(),color);
            peon.setFicha(row,column);
            listaDeFichas[row][column] = peon;
            peon.makeVisible();
        } 
    }
    
    public void select(int row , int column){
        if (listaDeFichas[row][column] != null){
            Ficha seleccion = listaDeFichas[row][column];
            this.selecction[0] = row; this.selecction[1]= column;
            seleccion.setPossion(true);
            seleccion.changeColor("blue");
        }
    }
    
    public void remove(int row, int column){
        if (removeFicha(row,column)){
            listaDeFichas[row][column].remove();
            listaDeFichas[row][column] = null;
        }else JOptionPane.showMessageDialog(null,"no hay ficha para remover");
    }
    
    public void remove(int[][] pieces){
        for (int i = 0; i< pieces.length; i++){
            remove(pieces[i][0],pieces[i][1]);
        }
    }
    
    public void shift(boolean top, boolean right){
        Rectangle move = casillas[selecction[0]+darPosicionDeMovimiento(top)][selecction[1]+darPosicionDeMovimiento(right)];
        if (verificarMovimiento(selecction[0],selecction[1])){
            listaDeFichas[selecction[0]][selecction[1]].shift(move.getPositionX(),move.getPositionY(),move.getPositionX(),move.getPositionY());
            listaDeFichas[selecction[0]][selecction[1]] = null;
            return;
        }      
    }
    /**
    public void jump(boolean top, boolean right, boolean desSelect){
        for(int i = 0; i<listaDeFichas.length; i++){
            for (int j = 0; j<listaDeFichas.length; j++){
                if (listaDeFichas[i][j]!= null){
                    if(listaDeFichas[i][j].getSelect()){
                        listaDeFichas[i][j].jump(top,right,listaDeFichas,casillas,i,j,desSelect);
                        return;
                    }
                }
            }
        }
    }
    
    public void move(String notacion, char movimiento,ArrayList<Integer> move){
        boolean top = true;
        boolean right = true;
        boolean desSelect = false;
        if (movimiento == '-'){
            right = rigth(move.get(0),move.get(1));
            if (move.get(0) > move.get(1))top = false;
            else top = true;
            shift(top,right);
        }else{
            for (int i = 0; (int) i<(move.size()-1); i++){
                if (move.get(i) > move.get(i+1)) top = false;
                else top = true;
                right = jump(move.get(i),move.get(i+1));
                System.out.println("yo"+" "+move.get(i)+" "+move.get(i+1));
                if (i == move.size()-2) desSelect = true;
                jump(top,right,desSelect);
            }
        }
    }
    */
    public String write(){
        String write = "";
        for (int i = 0; i < listaDeFichas.length;i++){
            for (int j = 0; j < listaDeFichas[i].length; j++){
                
                if (casillas[i][j].getColor() == "black" && listaDeFichas[i][j] == null){
                    write += '.';
                }else if (casillas[i][j].getColor() == "black" && listaDeFichas[i][j].getColor() == "green"){
                    write += 'b';
                }else if  (casillas[i][j].getColor() == "black" && listaDeFichas[i][j].getColor() == "red"){
                    write += 'w';
                }else write += '-';
            }
        }
        return write;
    }
    
    public void read(String checherboard){
        int recorre = 0 ;
        for (int i = 0; i< tamaño; i++){
            for (int j = 0; j< tamaño;j++){
                char bOW = checherboard.charAt(recorre);
                if (bOW == 'b' || bOW == 'B')add(false,true,i,j);
                else if (bOW == 'w' || bOW == 'W')add(true,true,i,j);
                recorre += 1;
            }
        }
    }
    
    private boolean removeFicha(int row, int column){
        boolean quitarFicha = false;
        if (listaDeFichas[row][column] != null){
            return quitarFicha = true;
        }else return quitarFicha;
    }
    
    private boolean rigth(int shift , int shift2){
        boolean right;
        double decimal = (double)shift/4;
        double decimal2 = decimal;
        int entero = (int)decimal2;
        if (decimal - entero != 0){
            if (entero%2 == 0){
                System.out.println("entreyo"+" "+decimal+" "+entero);
                if(shift-shift2 == -4 || shift-shift2 == 4)right = false;
                else right = true;
            }else{
                System.out.println("entreyo"+" "+decimal+" "+entero);
                if(shift-shift2 == -4 || shift-shift2 == 4)right = true;
                else right = false;
            }
        }else{
            System.out.println("entre2"+" "+decimal+" "+entero);
            entero -=1;
            if (entero%2 == 0){
                if(shift-shift2 == -4 || shift-shift2 == 4)right = false;
                else right = true;
            }else{
                if(shift-shift2 == -4 || shift-shift2 == 4)right = true;
                else right = false;
            }
        }
        return right;
    }
    
    private boolean jump(int jump, int jump2){
        System.out.println("yo");
        boolean right = true;
        if (jump - jump2 == -9 || jump - jump2 == 7){
            right = true;
        }
        else if (jump -jump2 == 9 || jump - jump2 == -7){
            right = false;
        }
        System.out.println("right"+" "+right);
        return right;
    }
    
    public Ficha[][] getListaDeFichas(){
        return listaDeFichas;
    }
    
    private int darPosicionDeMovimiento(boolean mover){
        int moveTopOrRigth = 0;
        if (mover){
            moveTopOrRigth = 1;
        }else{
            moveTopOrRigth = -1;
        }
        return moveTopOrRigth;
    }
    
     private boolean verificarMovimiento(int top, int right){
        Ficha color = listaDeFichas[selecction[0]][selecction[1]];
        boolean confirmarMovimiento = true;
        if (color.getColorBoolenao() && top == -1)confirmarMovimiento = false;
        else if (!color.getColorBoolenao() && top == 1)confirmarMovimiento = false;
        else if (listaDeFichas[color.getRow()+top][color.getColumn()+right] != null)confirmarMovimiento = false;
        return confirmarMovimiento;
    }
}
