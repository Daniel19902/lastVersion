import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.HashMap;
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
    private HashMap<Integer ,Integer[]> posiciones;
    public Table(int width,int moverX, int moverY){
        posiciones = new HashMap<Integer ,Integer[]>();
        tamaño = width;
        casillas = new Rectangle[width][width];
        listaDeFichas = new Ficha[width][width];
        table(width,moverX,moverY);
        selecction = new int[2];
        tableNumbers();
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
                crearFicha(row,column,white,king);
            }else{
                JOptionPane.showMessageDialog(null,"ya hay una ficha en esa posición");
            }
        }else{
            if (listaDeFichas[row][column] == null){
                crearFicha(row,column,white,king);
            }else{
                JOptionPane.showMessageDialog(null,"ya hay una ficha en esa posición");
            }
        }
    }
    
    private void crearFicha(int row, int column, boolean white, boolean king ){
        Rectangle asignarFicha = casillas[row][column];
        if (white){
            String color = "green";
            if (!king){
                Ficha peon = new peon(asignarFicha.getXPosition(),asignarFicha.getYPosition(),color);
                peon.darColor(true);
                peon.setFicha(row,column);
                listaDeFichas[row][column] = peon;
                peon.makeVisible();
            }else{
                Figura triangle = new Triangle(asignarFicha.getXPosition()+15,asignarFicha.getYPosition()+3,"blue");
                Ficha peonKing = new King(asignarFicha.getXPosition(),asignarFicha.getYPosition(),color,triangle);
                peonKing.darColor(true);
                peonKing.setFicha(row,column);
                listaDeFichas[row][column] = peonKing;
                peonKing.makeVisible();
                triangle.makeVisible();
            }
        }else{
            String color = "red";
            Ficha peon = new peon(asignarFicha.getXPosition(),asignarFicha.getYPosition(),color);
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
        }else{
            JOptionPane.showMessageDialog(null,"there are not toket to select");
        }
    }
    
    private void desSelect(int top, int right){
        Ficha desSelect = listaDeFichas[selecction[0]+top][selecction[1]+right];
        if (desSelect.getColorBoolenao()){
            desSelect.changeColor("green");
        }else{
            desSelect.changeColor("red");
        }
    }
    
    public void remove(int row, int column){
        if (removeFicha(row,column)){
            listaDeFichas[row][column].remove();
            listaDeFichas[row][column] = null;
        }else JOptionPane.showMessageDialog(null,"there are not toket to remove");
    }
    
    public void remove(int[][] pieces){
        for (int i = 0; i< pieces.length; i++){
            remove(pieces[i][0],pieces[i][1]);
        }
    }
    
    public void shift(boolean top, boolean right, boolean giveMovement){
        int[] movement = giveMovement(top, right, giveMovement);
        if (verificarMovimiento(movement[0], movement[1])){
            Rectangle move = casillas[selecction[0]+movement[0]][selecction[1]+movement[1]];
            moveToken(movement[0], movement[1], move);
            desSelect(movement[0], movement[1]);
        }else{
             JOptionPane.showMessageDialog(null,"movement not allowed");
        }
    }    
    
    private void moveToken(int arriba, int derecha, Rectangle move){
        Ficha moverToken = listaDeFichas[selecction[0]][selecction[1]];
        listaDeFichas[selecction[0]][selecction[1]].makeInvisible();
        listaDeFichas[selecction[0]][selecction[1]].shift(move.getXPosition(),move.getYPosition(),arriba,derecha);
        listaDeFichas[selecction[0]+arriba][selecction[1]+derecha] = listaDeFichas[selecction[0]][selecction[1]];
        listaDeFichas[selecction[0]+arriba][selecction[1]+derecha].makeVisible();
        listaDeFichas[selecction[0]][selecction[1]] = null;
    }
    
    private int [] giveMovement(boolean top, boolean right, boolean giveMovement){
        int [] movement = new int [2];
        movement[0] = darPosicionDeMovimiento(top,giveMovement); movement[1] = darPosicionDeMovimiento(right,giveMovement);
        return movement;
    }
   
    public void jump(boolean top, boolean right, boolean giveMovement){
        if (checkMovementJump(top, right)){
            int [] movement = giveMovement(top, right, !giveMovement);
            shift(top, right, giveMovement);
            remove(selecction[0]+movement[0], selecction[1]+movement[1]);
        }
    }
    
    public boolean checkMovementJump(boolean top, boolean right){
        int[] movement = giveMovement(top, right, true);
        int[] movement2 = giveMovement(top, right, false); 
        boolean checkMovement = true;
        System.out.println(selecction[0]+"  "+selecction[1]);
        if (listaDeFichas[selecction[0]+movement[0]][selecction[1]+movement[1]] == null) checkMovement = false;
        else if (listaDeFichas[selecction[0]+movement2[0]][selecction[1]+movement2[1]] !=null)checkMovement = false;
        else if (listaDeFichas[selecction[0]][selecction[1]].getColorBoolenao() == listaDeFichas[selecction[0]+movement[0]][selecction[1]+movement[1]].getColorBoolenao())checkMovement = false;
        return checkMovement;
    }
    
    public void autoSelect(ArrayList<Integer> move, int i){
        Integer[] posicion = posiciones.get(move.get(i));
        select(posicion[0],posicion[1]);
    }
    
    public void addAuto(int add,boolean white){
        Integer[] posicion = posiciones.get(add);
        add(white, true, posicion[0],posicion[1]);
    }
    
    public void move(String notacion, char movimiento, ArrayList<Integer> move){
        boolean top = true;
        boolean right = true;
        if (movimiento == '-'){
            autoSelect(move,0);
            if (move.get(0) > move.get(1))top = false;
            right = rigth(move.get(0),move.get(1));
            shift(top,right,true);
        }else{
            for (int i = 0; i < move.size()-1; i++){
                autoSelect(move,i);
                if (move.get(i)>move.get(i+1))top= false;
                right = jump(move.get(i),move.get(i+1));
                jump(top, right, false);
            }
        }
    }
    
    private void tableNumbers(){
        int contador = 1;
        for (int i = 0 ; i < 8 ; i++){
            for (int j = 1-i%2 ; j < 8 ; j+=2){
                posiciones.put(contador , new Integer[] {i,j});
                contador+=1;
            }
        }
    }
    
    private boolean rigth(int shift , int shift2){
        boolean right;
        double decimal = (double)shift/4;
        int entero = (int)decimal;
        if (decimal - entero != 0){
            if (entero%2 == 0){
                if(shift-shift2 == -4 || shift-shift2 == 4)right = false;
                else right = true;
            }else{
                if(shift-shift2 == -4 || shift-shift2 == 4)right = true;
                else right = false;
            }
        }else{
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
        boolean right = true;
        if (jump - jump2 == -9 || jump - jump2 == 7){
            right = true;
        }
        else if (jump -jump2 == 9 || jump - jump2 == -7){
            right = false;
        }
        return right;
    }
    
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
                System.out.println(checherboard);
                char bOW = checherboard.charAt(recorre);
                if (bOW == 'b' || bOW == 'B')add(false,false,i,j);
                else if (bOW == 'w' || bOW == 'W')add(true,false,i,j);
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
    
    public Ficha[][] getListaDeFichas(){
        return listaDeFichas;
    }
    
    private int darPosicionDeMovimiento(boolean mover, boolean shift){
        int moveTopOrRigth = 0;
        if (shift){
            if (mover){
                moveTopOrRigth = 1;
            }else moveTopOrRigth = -1;
        }else{
            if (mover){
                moveTopOrRigth = 2;
            }else moveTopOrRigth = -2;
        }
        return moveTopOrRigth;
    }

    private boolean verificarMovimiento(int top, int right){
        Ficha color = listaDeFichas[selecction[0]][selecction[1]];
        boolean confirmarMovimiento = true;
        //if (!color.isKing()){
        //    if (color.getColorBoolenao() && top == -1)confirmarMovimiento = false;
        //    else if (!color.getColorBoolenao() && top == 1)confirmarMovimiento = false;
        if (listaDeFichas[color.getRow()+top][color.getColumn()+right] != null)confirmarMovimiento = false;
        return confirmarMovimiento;
    }
}
