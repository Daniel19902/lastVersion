import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Write a description of class Checkers here.
 *
 * @author (your name)
 * @version (a version number or a date)
 * holaaaa  que hace
 */
public class Checkers
{
    private Table zonaJuego;
    private Table zonaConfiguracion;
    private boolean juegoOConfiguracion;
    private HashMap<String, String> save;
    /**
     * Checkers realiza el tablero de configuracion y de juego
     * @param width un entero para determinar el tamaño del tablero (width*width)
     */
   
    public Checkers(int width){
        juegoOConfiguracion = true;
        save = new HashMap<String, String>();
        zonaJuego = new Table(width,30,30);
        zonaConfiguracion = new Table(width,width*30+60,30);
    }
    
    public void makeVisible(){
        zonaJuego.makeVisible();
        zonaConfiguracion.makeVisible();
    }
    public void add(boolean white, boolean king ,int row, int column){
        if (juegoOConfiguracion)zonaConfiguracion.add(white ,king ,row ,column);
        else JOptionPane.showMessageDialog(null,"metodo no permitido para este tablero");
    }
    
    private void addSwap(boolean white, boolean king ,int row, int column){
        if (juegoOConfiguracion)zonaConfiguracion.add(white ,king ,row ,column);
        else zonaJuego.add(white ,king ,row ,column);
    }
    
    public void add(boolean white, int [][] men){
        if (juegoOConfiguracion){
            for(int i = 0; i<men.length; i++){
                zonaConfiguracion.add(white, false, men[i][0], men[i][1]);
            }
        }else JOptionPane.showMessageDialog(null,"metodo no permitido para este tablero");
    }
    
    public void select(int row, int column){
        if (juegoOConfiguracion) zonaConfiguracion.select(row,column);
        else zonaJuego.select(row,column);
    }
    
    public void shift(boolean top, boolean right){
        boolean giveMovement = true;
        if (juegoOConfiguracion)zonaConfiguracion.shift(top,right, giveMovement);
        else zonaJuego.shift(top,right, giveMovement);
    }
    
    public void jump(boolean top, boolean right){
        boolean giveMovement = false;
        if (juegoOConfiguracion)zonaConfiguracion.jump(top, right, giveMovement);
        else zonaJuego.shift(top,right, giveMovement);
    }
    
    public void move(String notacion){
        char movimiento;
        char guion = notacion.charAt(1);
        char equis = notacion.charAt(2);
        if (guion == '-' || guion == 'x'){
            movimiento = guion;
        }else movimiento = equis;
        ArrayList<Integer> move = passToStringToInt(notacion);
        if (juegoOConfiguracion)zonaConfiguracion.move(notacion, movimiento, move);
        else zonaJuego.move(notacion, movimiento, move);
    }

    public String write(){
        String write = zonaConfiguracion.write();
        return write;
    }
    
    public void save(String name){
        String write = zonaConfiguracion.write();
        save.put(name,write);
    }
    
    public String recover(String name){
        String recover = save.get(name);
        return recover;
    }
    
    public void read(String checherboard){
        zonaConfiguracion.read(checherboard);
    }
    
    public void remove(int row, int column){
        if (juegoOConfiguracion)zonaConfiguracion.remove(row,column);
        else JOptionPane.showMessageDialog(null,"metodo no permitido para este tablero");
    }
    
    private void removeSwap(int row, int column){
        if (juegoOConfiguracion)zonaJuego.remove(row,column);
        else zonaConfiguracion.remove(row,column);
    }
    
    public void remove(int [][] pieces){
        if (juegoOConfiguracion)zonaConfiguracion.remove(pieces);
        else JOptionPane.showMessageDialog(null,"metodo no permitido para este tablero");
    }
    
    public void swap(){
        if (juegoOConfiguracion)juegoOConfiguracion = false;
        else juegoOConfiguracion = true;
        swapTableros();
        System.out.println(juegoOConfiguracion);
    }
    
    private void swapTableros(){
        Ficha[][] configuracion = null;
        if (juegoOConfiguracion)configuracion = zonaJuego.getListaDeFichas();
        else configuracion = zonaConfiguracion.getListaDeFichas();
        for(int i = 0; i< configuracion.length; i++){
            for (int j = 0; j <configuracion.length; j++){
                if (configuracion[i][j]!= null){
                    addSwap(configuracion[i][j].getColorBoolenao(),false,i,j);
                    System.out.println(juegoOConfiguracion);
                    removeSwap(i,j);
                }
            }
        }
    }
    
    private ArrayList<Integer> passToStringToInt(String notacion){
        ArrayList<Integer> move;
        move = new ArrayList<Integer>();
        String[] jumps = notacion.split("x");
        String[] shift = notacion.split("-");
        //inicio de convertir String a int
        char letra = notacion.charAt(1);
        char letra2 = notacion.charAt(2);
        if (letra == '-' || letra2 == '-'){
            move.add(Integer.parseInt(shift[0]));
            move.add(Integer.parseInt(shift[1]));
            
        }else{
            for(int i = 0; i< jumps.length; i++){
                 move.add(Integer.parseInt(jumps[i]));
            }
        } 
        return move;
    }
    public void add (int[] add, boolean white){
        for(int i = 0; i<add.length; i++){
            zonaConfiguracion.addAuto(add[i],white);
        }
    }
}
